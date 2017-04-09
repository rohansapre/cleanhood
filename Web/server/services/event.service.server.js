/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model){

    var EventModel = Model.EventModel;

    var multer = require('multer');
    var storage = multer.diskStorage({
        destination: function (req, file, cb) {
            cb(null, __dirname + "/../../public/uploads")
        },
        filename: function (req, file, cb) {
            var extArray = file.mimetype.split("/");
            var extension = extArray[extArray.length - 1];
            cb(null, "widget_image_" + Date.now() + "." + extension)
        }
    });
    var upload = multer({"storage": storage});

    app.post("/api/event", createEvent);
    app.get("/api/event", findAllEvents);
    app.get("/api/event/:eid", findEventById);
    app.post("/api/upload", upload.single('myFile'), uploadImage);



    function createEvent(req, res) {
        var event = req.body;
        EventModel
            .create(event)
            .then(function(newEvent) {
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


};