/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var userEventSchema = mongoose.Schema({
    _user: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
    _event: { type: mongoose.Schema.Types.ObjectId, ref: 'Event' },
    dateCreated: { type: Date, default: Date.now() }
}, {collection: 'user-event'});

module.exports = userEventSchema;