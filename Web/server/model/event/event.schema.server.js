/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require("mongoose");

var eventSchema = mongoose.Schema({
    name: String,
    purpose: String,
    date: String,
    time: String,
    location: String,
    numParticipants: { type: Number, default: 1 },
    closed: {type: Boolean, default: false },
    initialPicURL: String,
    endPicURL: String,
    _user: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
    dateCreated: {type: Date, default: new Date()}
}, {collection: 'event'});

module.exports = eventSchema;