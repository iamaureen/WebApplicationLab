// simplesockclient to go with simplesockserver.js
var sock = require('net').Socket();

sock.on('data', function(data) {

	var d_json = JSON.parse(data);
	//console.log(d_json);
	if(d_json['i']===1) {
		console.log("You Entered: "+d_json['point1']);
	   	console.log("Sum is: "+d_json['point2']);
	}
	else if(d_json['i']===2) {
		console.log("You Entered: "+d_json['point1']);
	   	console.log("ASU: Sum is: "+d_json['ASU']);
	   	console.log("UA: Sum is: "+d_json['UA']);
	   	console.log("NAU: Sum is: "+d_json['NAU']);
	}
	else{
		console.log("Error: "+d_json['msg']);
	}
	 
	 
}).on('error', function (e) {
console.log("Error connecting to server");
});

sock.on('close', function() {
	sock.destroy(); // kill client after server's response
	console.log('Connection closed');
});
// now make a request
sock.connect(3000);

var clientID=process.argv[2];
var command=process.argv[3];
var value=process.argv[4];
var arr=[];

arr.push({
	Client:clientID,
	Operator: command,
	Value: value
});


sock.write(JSON.stringify(arr));
//sock.write(value);
sock.end();


