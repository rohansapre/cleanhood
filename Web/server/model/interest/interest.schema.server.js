/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var interestSchema = mongoose.Schema({
    name: String,
    dateCreated: { type: Date, default: Date.now() }
}, {collection: 'interest'});

module.exports = interestSchema;