/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model,io){

    var gcm = require('node-gcm');
    var base64 = require('node-base64-image');

    var EventModel = Model.EventModel;

    var cli = require('twilio')("AC509204bc8838c826ec818b178031da98", "63a9bd9bd24591beb0a73e97599d435a");

    app.post("/api/event", createEvent);
    app.get("/api/event", findAllEvents);
    app.get("/api/event/:eid", findEventById);
    app.post("/api/sendMessage", sendMessage);
    app.post("/api/upload", uploadImage);


    function createEvent(req, res) {
        var event = req.body;
        EventModel
            .create(event)
            .then(function(newEvent) {
                // io.on('connection', function(socket){
                //     console.log("Socket event trigger");
                //     socket.emit('event', newEvent);
                //     res.json(newEvent);
                // });

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
            .then(function (events) {
                allEvents = {eventList:events};
                console.log(allEvents);
                res.json(allEvents);
            }, function(err) {
                res.sendStatus(500).send(err);
            });
    }


    function uploadImage(req, res) {
        var eid = req.body.eid;
        var image = req.body.eImage;
        eid = eid.replace(/['"]+/g, '');
        var imageName = eid;
        console.log(imageName);
        var imageBuffer = new Buffer(image, 'base64');
        var options = {
            filename: __dirname + '/../../public/uploads/' + imageName
        };
        base64.decode(imageBuffer, options, function (err, success) {
            if(err) {
                res.sendStatus(500).send(err);
            }
            else {
                console.log(success);
                var url = '/Web/public/uploads/' + imageName + '.jpg';
                EventModel
                    .updateInitialPicture(eid, url)
                    .then(function (event) {
                        res.json(url);
                    }, function (error) {
                        res.sendStatus(500).send(error);
                    });
            }
        });
    }

    function sendMessage(req, res) {
        var num = req.body.num;
        var event = req.body.event;
        console.log(cli);

        cli.messages
            .create({
            to: num,
            from: '+14403791185',
            body: 'Hello from Cleanhood! Join the cleanup revolution! Check out this upcoming event: ' + event
        })
            .then(function(){
                res.sendStatus(200);
            })

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