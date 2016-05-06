var restify = require('restify');
var routes = require('./routes');
function createServer() {
	var server = restify.createServer({
		name:'booktown'
	});
	server.use(restify.queryParser({ mapParams: false }));
	server.use(restify.bodyParser());
	server.get('/',function root(req,res,next){
		res.writeHead(200, {'Content-Type': 'application/json'});
		res.end('welcome to booktown application');
	});
	//Author CRUD operations
	server.post('/createAuthor/',routes.createAuthor);
	server.get('/getAuthor/',routes.getAllAuthor);
	server.get('/getAuthor/:id',routes.getAuthor);
	server.put('/updateAuthor/:id',routes.updateAuthor);
	server.del('/deleteAuthor/:id',routes.deleteAuthor);
	
	//Book CRUD operatons
	server.post('/createBook/', routes.createBook);	
	server.get('/getBook/:isbn',routes.getBook);
	server.get('/getBook/',routes.getAllBook);
	server.put('/addAuthor/:isbn',routes.addAuthor);
	server.put('/updateBook/:isbn',routes.updateBook);
	server.del('/deleteBook/:isbn',routes.deleteBook); //
	server.get('/getBookTitle/:t',routes.getBookTitle);
	
	
	
	return server;
}

module.exports = {
    createServer: createServer
};