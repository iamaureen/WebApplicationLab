var express = require('express');
var url = require('url');

var bodyParser = require('body-parser');

var app = express();


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.set('view engine','ejs');
app.set('views', '.');

var allUserArray = [];
var success = "User added.";

app.get('/', function (req, res) {
    res.locals.status = null;
    res.locals.count = null;
    res.render('index');
});

app.get('/coders', function (req, res) {

    var query = url.parse(req.url, true).query;
    var color = '';

    var foundUsers = [];

    //  query the results
    if (Object.keys(query).length === 0){
        foundUsers = allUserArray;
    }
    else {
        var temp = [];
        for (var i in allUserArray){
            var counter = 0;
            //single answer
            if (query['firstname']) {
                if (allUserArray[i]['fname'] === query['firstname']){
                    counter++;
                }
            }

            if (query['lastname']) {
                if (allUserArray[i]['lname'] === query['lastname']){
                    counter++;
                }
            }
            //multiple answer
            if (query['language']) {
                for (var j in query['language']) {
                    if (allUserArray[i]['languages'].indexOf(query['language'][j]) > -1) {
                        counter++;
                        break;

                    }
                }
            }
            //multiple answer
            if (query['days']) {
                for (var j in query['days']) {
                    if (allUserArray[i]['days'].indexOf(query['days'][j]) > -1) {
                        counter++;
                        break;

                    }
                }
            }
            if (query['hair'])//single answer
                  {
                if (allUserArray[i]['hairColor'] === query['hair']){
                    counter++;
                }
            }
            console.log(counter);
            if (counter > 1){
                temp.push(allUserArray[i]);
            }
        }
        foundUsers = temp;
    }

    // Detect user-agent from headers
    var userAgent = req.headers['user-agent'];
    if (userAgent.indexOf("Chrome") > -1) {
        color = 'pink';
    }
    else {
        color = '';
    }

    res.status(200);

    res.set({
        'Cache-Control': 'no-cache, no-store, must-revalidate',
        'Pragma': 'no-cache'
    });


    res.render('ShowUser', {userList:foundUsers,color:color});


});
//post from the form
app.post('/post_coder', function (req, res) {
    if (!req.body['fname'] == '' && !req.body['lname'] == '') {
        allUserArray.push(req.body);
    }
    res.render('index', {status:success, count:'Total Number of User Added: ' + allUserArray.length});

    res.end();
});


app.listen(8081);
