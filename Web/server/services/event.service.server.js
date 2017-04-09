/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model,io){

    var gcm = require('node-gcm');

    var EventModel = Model.EventModel;

    var twilioAPI = require('twilio-api');
    var cli = new twilioAPI.Client("AC509204bc8838c826ec818b178031da98", "63a9bd9bd24591beb0a73e97599d435a");

    var multer = require('multer');
    var storage = multer.diskStorage({
        destination: function (req, file, cb) {
            cb(null, __dirname + "/../../public/uploads")
        },
        filename: function (req, file, cb) {
            var extArray = file.mimetype.split("/");
            var extension = extArray[extArray.length - 1];
            cb(null, "event_image_" + Date.now() + "." + extension)
        }
    });
    var upload = multer({"storage": storage});

    app.post("/api/event", createEvent);
    app.get("/api/event", findAllEvents);
    app.get("/api/event/:eid", findEventById);
    app.post("/api/upload", upload.single('myFile'), uploadImage);
    app.post("/api/sendMessage", sendMessage);


    function createEvent(req, res) {
        var event = req.body;
        EventModel
            .create(event)
            .then(function(newEvent) {
                io.on('connection', function(socket){
                   console.log("Socket event trigger");
                    socket.emit('event', newEvent);
                });
                res.json(newEvent);
            }, function(err) {
                res.sendStatus(500).send(err)
            });
    }

    function findEventById(req, res) {
        var eid = req.params.eid;

        EventModel
            .findById(eid)
            .then(function (event) {
                res.json(event);
            }, function(err) {
                res.sendStatus(500).send(err);
            });
    }

    function findAllEvents(req, res) {

        EventModel
            .findAllEvents()
            .then(function (allEvents) {
                res.json(allEvents);
            }, function(err) {
                res.sendStatus(500).send(err);
            });
    }


    function uploadImage(req, res) {
        var eid = req.body.eid;

        if(req.file){
            var myFile = req.file;
            var destination = myFile.destination;

            EventModel
                .findEventById(eid)
                .then(function(event) {
                    event.initialPicURL = req.protocol + '://' + req.get('host') + "/uploads/" + myFile.filename;
                    event.save();
                    res.sendStatus(200);
                }, function(err) {
                    res.sendStatus(500).send(err);
                });
        }
    }

    function sendMessage(req, res) {
        var num = req.body.num;
        var event = req.body.event;

        cli.messages.create({
            to: num,
            from: '+14403791185',
            body: 'Hello from Cleanhood! Join the cleanup revolution!'
        });
    }


    function sendNotifications(registrationTokens) {
        var message = new gcm.Message();
        message.addData({
            key1: 'Welcome to push notification',
            key2: 'How do you feel now?'
        });

        var sender = new gcm.Sender(process.env.GOOGLE_API_KEY);

        sender.send(message, { registrationTokens: registrationTokens }, function (err, response) {
            if(err)
                console.log(err);
            else
                console.log(response);
        });
    }


};