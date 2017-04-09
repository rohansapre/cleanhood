/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var userSchema = mongoose.Schema({
    username: { type: String, required: true, index: { unique: true } },
    password: { type: String, required: true },
    email: { type: String, unique: true },
    firstName: String,
    lastName: String,
    picture: String,
    token: String,
    dateCreated: { type: Date, default: Date.now() }
}, {collection: 'user'});

module.exports = userSchema;