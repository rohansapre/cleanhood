/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model){

    var UserEventModel = Model.UserEventModel;

    app.post("/api/createParticipation", createParticipation);


    function createParticipation(req, res) {
        var eventId = req.body.eventID;
        var userId = req.body.userID;

        UserEventModel
            .createParticipation(userId, eventId)
            .then(function(participation) {
                res.sendStatus(200);
            }, function (err) {
                res.sendStatus(500).send(err);
            })
    }


};