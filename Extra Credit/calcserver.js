    // simple socket server
    var net = require('net');
    var sleep = require('sleep');
    var EventEmitter = require('events').EventEmitter;

    //event listener
    var lumberjack = new EventEmitter();

    function cb() {
        console.log("I saw a lumberjack!");
    }

    lumberjack.addListener('NAU', cb);

    var client,op,val,ans_asu=0,ans_ua=0,ans_nau=0,d1,error;

    var server = net.createServer(function (sock) {
        console.log("Incoming connection accepted");
        sock.on('data', function (d) {
        	error=0;

            console.log("Received from client: " + d);

            //obtain value from client
            var d_json = JSON.parse(d);
            client = d_json[0].Client; 
            op = d_json[0].Operator;
            val = d_json[0].Value;

            //parse the value to integer
            if(typeof val==="undefined") d1=0;
            else d1=parseInt(val);

            //check whether client input is okay or not
            if(client=="ASU"||client=="UA"||client=="NAU"){}
            else{
                error=1;
            }
            //check if <val> is null or not
                console.log("input: "+val+", after parse: "+d1);
                if(Number.isNaN(val) || typeof val == "undefined"){
                    error=1;
            }

            // compare operator and do the action based on client

            if(op == 'a') { 
                if(client=="ASU") ans_asu += d1;
                if(client=="UA") ans_ua += d1; 
                if(client=="NAU") ans_nau += d1;  
            }
            else if(op == 'm'){ 
                if(client=="ASU") ans_asu -= d1;
                if(client=="UA") ans_ua -= d1; 
                if(client=="NAU") ans_nau -= d1;
            }
            else if(op == 's'){	
                if(d1===0){}
                else{
                    if(client=="ASU") ans_asu = d1;
                    if(client=="UA") ans_ua  = d1; 
                    if(client=="NAU") ans_nau = d1;
                }
                 
            }
            else if(op == 'q'){
                error=0;
                var sock_reply = {
                i:2,
                point1: d.toString(),
                ASU: ans_asu,
                UA: ans_ua,
                NAU: ans_nau
            };

            sock.write(JSON.stringify(sock_reply));
            	console.log("connection closing");
            	server.close();
            }
            //check whether op is either of the four values-no
            else{ 
                 error=1;
            }


            //point 3:delay based on client id
            if(client == "ASU"){
            	//delay 30 seconds
                if(error===1){
                    var sock_reply = {
                        i:3,
                        msg: "Invalid request specification"
                    };
                    sock.write(JSON.stringify(sock_reply));
                }
                else{
                    sleep.sleep(30);
                    var sock_reply = {
                            i:1,
                            point1: d.toString(),
                            point2: ans_asu
                        };
                        console.log(sock_reply);
                        console.log("------------------------");
                        sock.write(JSON.stringify(sock_reply));

                    //causes to client socket to close forcefully. so used sleep instead.
                     /*setTimeout(function(){
                        console.log(sock_reply);
                        console.log("------------------------");
                        sock.write(JSON.stringify(sock_reply));
                      },30000);*/    
                }
                

            }
            else if(client=="UA"){
            	//do immediately,before any work in the event queue
            	if(error===1){
                    var sock_reply = {
                        i:3,
                        msg: "Invalid request specification"
                    };
                    sock.write(JSON.stringify(sock_reply));
                }
                else{
                    var sock_reply = {
                        i:1,
                        point1: d.toString(),
                        point2: ans_ua
                    };

                   console.log(sock_reply);
                   console.log("------------------------");
                   sock.write(JSON.stringify(sock_reply));
                }
                
            }
            else if(client == "NAU"){
            	//execute normally
            	
            	lumberjack.emit('NAU', 'BETA');
                if(error===1){
                     var sock_reply = {
                        i:3,
                        msg: "Invalid request specification"
                    };
                    sock.write(JSON.stringify(sock_reply));
                }
                else{
                   /* process.nextTick(function(){
                        var sock_reply = {
                            i:1,
                            point1: d.toString(),
                            point2: ans_nau
                        };

                       console.log(sock_reply);
                       console.log("------------------------");
                       sock.write(JSON.stringify(sock_reply));
                    });*/
                    var sock_reply = {
                            i:1,
                            point1: d.toString(),
                            point2: ans_nau
                        };

                       console.log(sock_reply);
                       console.log("------------------------");
                       sock.write(JSON.stringify(sock_reply));
                    
                }
                
            }
            else{
                 var sock_reply = {
                        i:3,
                        msg: "Invalid request specification"
                    };
                    sock.write(JSON.stringify(sock_reply));
            }

        }).on('error', function (e) {
    	console.log("Some kind of server error, "+e.stack);
        });
    }).listen(3000, function() { 
      console.log('server is listening');
    });


