/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var eventInterestSchema = mongoose.Schema({
    _event: { type: mongoose.Schema.Types.ObjectId, ref: 'Event' },
    _interest: { type: mongoose.Schema.Types.ObjectId, ref: 'Interest' },
    dateCreated: { type: Date, default: Date.now() }
}, {collection: 'event-interest'});

module.exports = eventInterestSchema;