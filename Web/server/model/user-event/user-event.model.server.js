/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require("mongoose");
var q = require("q");
var userEventSchema = require("./user-event.schema.server");
var UserEventModel = mongoose.model("UserEventModel", userEventSchema);


UserEventModel.createParticipation = createParticipation;


module.exports = UserEventModel;

function createParticipation(userId, eventId) {
    var deffered = q.defer();

    UserEventModel
        .create({_user:userId, _event:eventId})
        .then(function(err, participation) {
            if(err) {
                deffered.reject(err);
            }
            else {
                deffered.resolve(participation);
            }
        });

    return deffered.promise;
}