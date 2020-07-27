var http = require('http');
var https = require('https')
var fs = require("fs");
 
http.createServer(function(request, response) {}).listen(80);

https.createServer(function(request, response) {}).listen(443)