/**
 * Created by rohansapre on 4/8/17.
 */
module.exports = function (app,io) {
    var UserModel= require('./model/user/user.model.server');
    var EventModel= require('./model/event/event.model.server');
    var EventInterestModel=require('./model/event-interest/event-interest.model.server');
    var UserEventModel = require('./model/user-event/user-event.model.server');
    var UserInterestModel = require('./model/user-interest/user-interest.model.server');

    var model ={
        UserEventModel:UserEventModel,
        EventModel:EventModel,
        UserModel:UserModel,
        EventInterestModel:EventInterestModel,
        UserInterestModel:UserInterestModel
    };

    require('./services/association.service.server')(app,model);
    require('./services/event.service.server')(app,model);
    require('./services/user.service.server.js')(app,model);
};