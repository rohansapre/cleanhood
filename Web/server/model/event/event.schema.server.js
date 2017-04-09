/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require("mongoose");

var eventSchema = mongoose.Schema({
    creatorID: String,
    eventID: String,
    initialPicURL: String,
    endPicURL: String,
    date: Date,
    time: String,
    purpose: String,
    eventName: String,
    numParticipants: Number,
    closed: Boolean,
    // eventTags: [String],
    location: String,
    dateCreated: {type: Date, default: Date.now()}
}, {collection: event.collection});

module.exports = eventSchema;