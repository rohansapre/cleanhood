/**
 * Created by rohansapre on 4/8/17.
 */
var mongoose = require("mongoose");
var q = require("q");
var eventSchema = require("./event.schema.server");
var eventModel = mongoose.model("EventModel", eventSchema);



module.exports = eventModel