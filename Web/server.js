var express = require('express');
var app = express();
app.use(express.static(__dirname + '/public'));
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//socket
var http = require('http').Server(app);
var io = require('socket.io')(http);

io.on('connection', function(socket){
    console.log('a user connected');
});

require ("./setup/app.js")(app);

var server = require('./server/app.js');
server(app);

var port = process.env.PORT || 3000;

http.listen(port);