/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = requrie('mongoose');
var userInterestSchema = mongoose.Schema({
    _user: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
    _interest: { type: mongoose.Schema.Types.ObjectId, ref: 'Interest' },
    dateCreated: { type: Date, default: Date.now() }
}, {collection: 'user-interest'});

module.exports = userInterestSchema;