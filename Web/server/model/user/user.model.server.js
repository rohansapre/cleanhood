/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require('mongoose');
var q = require('q');
mongoose.Promise = q.Promise;
var userSchema = require('./user.schema.server');
var userModel = mongoose.model('User', userSchema);

<<<<<<< e869586cb1aa7b43a9c6bb35844faebe1d44c633
userModel.createUser = createUser;
userModel.deleteUser = deleteUser;
userModel.updateUser = updateUser;
userModel.findUserById = findUserById;
userModel.findUserByCredentials = findUserByCredentials;

module.exports = userModel;

function createUser(user) {
    return userModel.create(user);
}

function deleteUser(userId) {
    return userModel.findByIdAndRemove(userId);
}

function updateUser(userId, user) {
    return userModel.update(userId, user);
}

function findUserById(userId) {
    return userModel.findById(userId);
}

function findUserByCredentials(username, password) {
    return userModel.findOne({username: username, password: password});
}
=======
module.exports = userModel;

>>>>>>> added all schemas
