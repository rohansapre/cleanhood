/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require("mongoose");
var q = require("q");
var eventSchema = require("./event.schema.server");
var EventModel = mongoose.model("EventModel", eventSchema);

EventModel.createEvent = createEvent;
EventModel.findAllEvents = findAllEvents;
EventModel.findEventById = findEventById;
EventModel.updateInitialPicture = updateInitialPicture;

module.exports = EventModel;

function createEvent(event) {
    var deffered = q.defer();
    EventModel
        .create(event, function(err, newEvent) {
            if(err){
                deffered.reject(err);
            }
            else{
                deffered.resolve(newEvent);
            }
        });
    return deffered.promise;
}


function findEventById(eid) {
    var deffered = q.defer();

    EventModel
        .findById(eid, function(err, event) {
           if(err) {
               deffered.reject(err);
           }
           else{
               deffered.resolve(event);
           }
        });
    return deffered.promise;
}


function findAllEvents() {
    var deffered = q.defer();
    EventModel
        .find()
        .sort({dateCreated: -1})
        .exec(function(err, allEvents) {
            if(err){
                deffered.reject(err);
            }
            else {
                console.log(allEvents)
                deffered.resolve(allEvents);
            }
        });
    return deffered.promise;
}

function updateInitialPicture(eventId, url) {
    var deffered = q.defer();
    EventModel.update({_id: eventId}, {$set: {initialPicURL: url}}, function (err, event) {
        if(err)
            deffered.reject(err);
        else
            deffered.resolve(event);
    });
    return deffered.promise;
}