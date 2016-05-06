var express = require('express');
var bodyParser = require('body-parser');
var ejs = require('ejs');
var url = require('url');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var app = express();
var router = express.Router();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(cookieParser());
app.use(session({secret: '1234567890QWERTY'}));
var router = express.Router();
app.set('view engine','ejs');
app.set('views', '.');

var allUserArray = [];
var language_list = ["C","Java","Python","Node Js"];
var days_list = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
var hairColor_list = ["Black","Brunette","Blond","White","other"];

app.get('/', function (req, res) {
  var uname = req.cookies.uname;
  if(uname)
    res.render('index', {user: uname});
  else
    res.render('login');

});

app.get('/login', function (req, res) {
  res.render('login');
});

//input user name
app.post('/login', function (req, res) {

  var fname = req.body['fname'];
  var lname = req.body['lname'];

  //if none of the inputs are empty, if epmty redirect to login page again
  if(!fname=="" && !lname=="")
  {
    var fname=fname+" "+lname;

    res.cookie('uname', fname);
    res.render('index', {user: fname}); 
  }
  else
  {
     res.render('login');
  }
 

});

//from <aref>
app.get('/multform1', function (req, res) {
  var fname = req.session.fname;
  console.log('In multiple form page 1 - ('+ req.method +') firstname in session '+fname);
  res.render('multform1', {firstname: fname});
});

//from multform2 <prev> button to multform1
app.post('/multform1', function (req, res) {
  var fname = req.session.fname;
  console.log('In multiple form page 1 - ('+ req.method +') firstname in session '+  fname);
  res.render('multform1', {firstname: fname});
});

//from multform1 <next> button to multform2
app.post('/multform2', function(req, res) {
  if(req.body['fname'])
    req.session.fname = req.body['fname'];
  var lname = req.session.lname;
  console.log('In multiple form page 2 - ('+ req.method +') lastname in session '+ lname);
  res.render('multform2', {lastname: lname});
});

app.post('/multform3', function(req, res) {
  if(req.body['lname'])
    req.session.lname = req.body['lname'];
  var selectedLanguage = req.session.selectedLanguage;
  console.log('In multiple form page 3 - ('+ req.method +') lang in session ' + selectedLanguage);
  res.render('multform3', {language_list: language_list, selectedLanguage: selectedLanguage}); 
});

app.post('/multform4', function(req, res) {
  if(req.body['languages'])
    req.session.selectedLanguage = req.body['languages'];
  var selectedDays= req.session.selectedDays;
  console.log('In multiple form page 4 - ('+ req.method +') days in session ' + selectedDays);
  res.render('multform4', {days_list: days_list, selectedDays: selectedDays});
});

app.post('/multform5', function(req, res) {
  if(req.body['days'])
    req.session.selectedDays = req.body['days'];
  var selectedHairColors= req.session.selectedHairColors;

   console.log('In multiple form page 5 - ('+ req.method +') days in session ' + selectedHairColors);
  res.render('multform5', {hairColor_list: hairColor_list, selectedHairColors: selectedHairColors});
});

app.post('/multform6', function(req, res) {
  if(req.body['hairColor'])
    req.session.selectedHairColors = req.body['hairColor'];
  var fname = req.session.fname;
  var lname = req.session.lname;
  var selectedLang = req.session.selectedLanguage;
  var selectedDays= req.session.selectedDays;
  var selectedHairColors= req.session.selectedHairColors;

  res.render('multform6', {fname: fname ,lname: lname, selectedLang: selectedLang, selectedDays: selectedDays, selectedHairColors: selectedHairColors});
});

app.post('/cancel', function (req, res) {

  req.session.destroy();

  var uname = req.cookies.uname;
  res.render('index', {user: uname});
});


app.get('/display', function (req, res) {

  if(allUserArray.length===0){
       res.render('error', {code: 'Nothing to Display', message:'Currently there are no user to display'});
  }else{
     res.render('display', {user: req.cookies.uname,records: allUserArray});
  }

 
});

app.post('/post_coder', function (req, res) {
  storeRecord(req, res);
});

app.use(function(err, req, res, next) {

  console.log('errorcode: '+ err.status)
  var errorCode = err.status || 500;
  var errorMessage = '';

  if(errorCode === 400)
  {
    errorMessage = 'This is Bad Request';
  }
  else if(errorCode === 404){
    errorMessage = 'Resource Not Found';
  }
  else if(errorCode === 405){
    errorMessage = 'Method not allowed';
  }
  else if(errorCode === 500){
    errorMessage = 'Internal Server Error';
  }
  else{
    errorMessage = 'Some Other Error';
  }
 
  res.status(errorCode);

     res.render('error', { code : errorCode, message: errorMessage });
});


app.listen(8081);
console.log('Server is listening to port 8081')

function storeRecord(req, res) {

  var fname = req.session.fname;
  var lname = req.session.lname;
  var languages = req.session.selectedLanguage;
  var daysOfWeek= req.session.selectedDays;
  var hairColor= req.session.selectedHairColors;

  if(!fname == '' && !lname == '')
     allUserArray.push({fname: fname ,lname: lname, languages: languages, days_list: daysOfWeek, hairColor: hairColor});

  req.session.destroy();

  var uname = req.cookies.uname;
  res.render('index', {user: uname});
}


