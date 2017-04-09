/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var q = require('q');
mongoose.Promise = q.Promise;
var userSchema = require('./user.schema.server');
var userModel = mongoose.model('User', userSchema);

module.exports = userModel;

