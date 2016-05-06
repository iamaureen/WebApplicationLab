var mongoose = require('mongoose');

var bookSchema = new mongoose.Schema({
	
	title: String,
    isbn : {
    type: String,
    required: true,
    unique: true
    },
   publisher: String,
   year: Number,
   author:{
    type: Array,
    required: true
  }
  });
var bookModel = mongoose.model('Book', bookSchema)
module.exports.Book = bookModel;

var authorSchema = new mongoose.Schema({
  id : {
	required: true,
    type: Number,
    unique: true
  },
  fname: String,
  lname: String
  }
  );

var authorModel = mongoose.model('Author', authorSchema);
module.exports.Author = authorModel;