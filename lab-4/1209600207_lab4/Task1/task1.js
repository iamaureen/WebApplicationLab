var http = require('http');
var url = require('url');
var fs = require('fs');
var querystring = require('querystring');

//variable declarations
var error = "Cannot Display the Form - Check Again";
var success = "User added.";
var warn = "Input Cannot be Empty";
var data = '';
var allUserArray = [];
var inputData = '';



http.createServer(function (req, res) {
    //get pathanme to find 'coders'
    var pathname = url.parse(req.url)['pathname'];
    console.log('pathname: ' + pathname);

    //displays the web form using get method and '/'
    if (req.method === 'GET' &&  req.url == '/') {
        fs.readFile('index.html', function(err, data) {
            if (err) {
                data = '<html><body><h4>' + error + '</h4></br></body></html></br>';
            }
            res.writeHead(200, {
                'Content-Type': 'text/html',
                'Content-Length': data.length
            });
            res.write(data);
        });
    }
    else if (req.method === 'GET' && pathname == '/coders'){
        // Parse GET Query
        var query = url.parse(req.url, true).query;
        console.log('passed url: ' + JSON.stringify(query));
        console.log('passed query: ' + query['firstname'] + ' query length: ' + (Object.keys(query).length));

        var foundUsers = [];
        //  query the results
        var isMatching = true;
        if (Object.keys(query).length === 0){
            foundUsers = allUserArray;  //no query parameter-return all the results
        } else if (Object.keys(query).length === 1){
            isMatching = true;
        } else {
            var temporaryArray = [];
            for (var i in allUserArray){
                var counter = 0;
                // single answer
                if (query['firstname'] && allUserArray[i]['fname'] === query['firstname']) {
                    console.log('matched first name');
                    counter++;
                }
                // single answer
                if (query['lastname'] && allUserArray[i]['lname'] === query['lastname']) {
                    console.log('matched last name');
                    counter++;
                }
                //multiple answer
                if (query['language']) {
                    for (var j in query['languages']) {
                        if (allUserArray[i]['languages'].indexOf(query['language'][j]) > -1) {
                            console.log('matched language');
                            counter++;
                            break;
                        }
                    }
                }
                // multiple answer
                if (query['days']) {
                    if (allUserArray[i]['days'].indexOf(query['language'][j]) > -1){
                        console.log('matched days name');
                        counter++;
                        break;
                    }
                }
                // single answer
                if (query['hair'] && allUserArray[i]['hairColor'] === query['hair']) {
                    console.log('matched color name');
                    counter++;
                }
                console.log(counter);
                if (counter > 1){
                    temporaryArray.push(allUserArray[i]);
                }
            }
            foundUsers = temporaryArray;
        }


      // GET Query no-cache settings
        res.writeHead(200, {
            'Content-Type': 'text/html',
            'Cache-Control': 'private, no-cache, no-store, must-revalidate',
            'Pragma': 'no-cache'
        });

        // Detect user-agent from headers and if chrome-background pinks
        var userAgent = req.headers['user-agent'];
        var display_page = '';

        if (userAgent.indexOf("Chrome") > -1) {
            display_page += '<html><body bgcolor=pink><h3>List of User Added: </h3> ';
        } else {
            display_page += '<html><body>';
        }

        //add the list of users present
        if (foundUsers.length > 0){
            for (var i in foundUsers) {
                display_page += (Number(i) + 1) + '. FirstName: ' + foundUsers[i]['fname'] + '</br> LastName: ' + foundUsers[i]['lname'] + '</br> Language: ' + foundUsers[i]['languages'] + '</br> Available Days: ' + foundUsers[i]['days'] + '</br> Hair Color: ' + foundUsers[i]['hairColor'] + '</br>';
                display_page += '---------------------------------------------------</br>';

            }

        }
        else {
            if (isMatching) {
                display_page += 'More than one query paramter needed for the query</br>';
            }
            display_page += 'No user entry to display';
        }
        display_page += '</body></html>';

        res.write(display_page);
        res.end();
    }

    if (req.method === 'POST' && req.url === '/post_coder') {
        req.on('data', function(params) {
            inputData += params;
        });

        req.on('end', function() {
            data = querystring.parse(inputData);
            console.log(JSON.stringify(data));
            inputData = '';

            if (!data['fname'] == '' && !data['lname'] == '') {
                console.log('has first and last name');
                allUserArray.push(data);
                var success_msg = '<html><body>' +
                    success + '</br>' +
                    'Total Number of User Added:' + allUserArray.length + '</br>' +
                    '</body></html></br>';
                fs.readFile('index.html', function(err, data1){
                    if (err){

                        data1 = '<html><body><h4>' + error + '</h4></br></body></html></br>';
                    }
                    else {
                        data1 = success_msg + data1;
                    }
                    res.writeHead(200, {
                        'Content-Type': 'text/html',
                        'Content-Length': data1.length
                    });
                    res.write(data1);
                });


            }
            else {
                console.log("fname or lname is missing");

                fs.readFile('index.html', function(err, data2){
                    if (err){

                        data2 = '<html><body><h4>' + error + '</h4></br></body></html></br>';
                    }
                    else {
                        data2 = '<html><body>' +
                        warn + '</br>' +
                        '</body></html></br>' + data2;
                    }
                    res.writeHead(200, {
                        'Content-Type': 'text/html',
                        'Content-Length': data2.length
                    });
                    res.write(data2);
                });
            }

            data = '';
        });
    }
}).listen(8081);
