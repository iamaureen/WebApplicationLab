
                         var mongoose = require('mongoose');
                         var Model = require('./model');
                        var ObjectId = mongoose.Types.ObjectId;



                        var routes = {
                        	
                        	//gets all the author list
                        	getAllAuthor : function(req, res, next) {
                        		console.log("get all author")
                        		Model.Author.find(function (err, author) {
                            if (err){
                                res.status(500)
                                res.json({
                                        type: false,
                                        message: err
                                    })
                            } 
                            else{
                                res.status(200)
                                res.json({
                                        type: true,
                                        data: author
                                    })
                            }
                            
                          })
                        	},
                        	//gets all the Book list
                        	getAllBook : function(req, res, next) {
                        		console.log("get all book")
                        		Model.Book.find(function (err, book) {
                           if (err){
                                res.status(500)
                                res.json({
                                        type: false,
                                        message: err
                                    })
                            } 
                            else{
                                res.status(200)
                                res.json({
                                        type: true,
                                        data: book
                                    })
                            }
                          })
                        	},
                        	//creates a author: number 1
                        	createAuthor : function(req, res, next) {
                                console.log("1. creates a author")
                            var authorModel = new Model.Author(req.body);
                            authorModel.save(function(err, author) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                                    res.status(201);
                                    res.json({
                                        type: true,
                                        data: author
                                    })
                                }
                            })
                        	},
                        	//creates a book, number 2
                        	createBook : function(req, res, next) {
                                console.log("2. Creates a book")
                            var bookModel = new Model.Book(req.body);
                            if(!req.body.author==""){
                                    bookModel.save(function(err, book) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                                    res.status(201);
                                    res.json({
                                        type: true,
                                        data: book
                                    })
                                }
                            })
                            }
                            else{
                                res.status(404);
                                    res.json({
                                        type: true,
                                        message: "author cannot be empty, insert again"
                                    })
                            }
                        
                        	},

                            //update author based on firstname or lastname, number 3
                        updateAuthor : function(req, res, next) {
                            
                            console.log("3. Update author based on iD")
                        Model.Author.findOne({"id":req.params.id}, function(err, author) {

                                if (err){
                                    res.status(500);
                                    res.send(err);
                                }
                                else{
                                    if(!req.body.fname=="")
                                {
                                    author.fname = req.body.fname;
                                }
                                if(!req.body.lname=="")
                                {
                                    author.lname = req.body.lname;
                                }
                                  
                                // save the author
                                author.save(function(err) {
                                    if (err){
                                        res.status(500)
                                res.json({
                                        type: false,
                                        message: err
                                    })
                                    }
                                    else{
                                       res.status(200)
                                res.json({
                                        type: true,
                                        message: "Author Updated with ID: "+req.params.id
                                    })
                                    }
                                        

                                    
                                });
                                }
                            });
                        },
                       
                        //update book based on publisher, title or year, number 4
                        updateBook : function(req, res, next) {
                            console.log("6. Update Book with isbn: "+req.params.isbn)
                             Model.Book.findOne({"isbn":req.params.isbn}, function(err, book) {

                                if (err){
                                    res.status(500);
                                    res.send(err);
                                }
                                else{
                                    if(!req.body.publisher=="")
                                {
                                    book.publisher = req.body.publisher;
                                }
                                if(!req.body.title=="")
                                {
                                    book.title = req.body.title;
                                }
                                if(!req.body.year=="")
                                {
                                    book.year = req.body.year;
                                }
                                  
                                // save the book
                                book.save(function(err) {
                                      if (err){
                                        res.status(500)
                                res.json({
                                        type: false,
                                        message: err
                                    })
                                    }
                                    else{
                                       res.status(200)
                                res.json({
                                        type: true,
                                        message: "Book Updated with ID: "+req.params.isbn,
                                        data: book
                                    })
                                    }
                                      
                                        

                                    
                                });
                                }

                            });
                              

                        },

                        //add author to a book, number 5
                            addAuthor : function(req, res, next) {
                              console.log('5. add author to a book with isbn : '+req.params.isbn);
                            Model.Book.findOne({"isbn": req.params.isbn}, function(err, book) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                                    if(book)
                                    {
                                         book.author.push(req.body.author);
                                        book.save(function(err) {
                                        if (err){
                                            res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                        }
                                        else{
                                            res.status(200)
                                             res.json({
                                            type: true,
                                            message: "author added" ,
                                            data: book

                                        })
                                        }

                                       
                                    });
                                    }
                                    else{
                                        res.status(404)
                                        res.json({
                                            type:false,
                                            message: "No book found with this isbn "+ req.params.isbn
                                        })
                                    }
                                       
                                         
                                    
                                }
                            })
                        },
                        	//delete a book by ISBN, number 6
                        	deleteBook : function(req, res, next) {
                        	  console.log('6. deleted Book: '+req.params.isbn);
                            Model.Book.findOneAndRemove({"isbn": req.params.isbn}, function(err, book) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                        				res.status(200);
                        				res.json({
                                            type: false,
                                            data: "Book: " + req.params.isbn + " deleted"
                                        })
                                       
                                    
                                }
                            })
                        },
                        //deletes an author if there is no books with that author, number 7
                        deleteAuthor: function(req, res, next){
                            console.log("7. Delete Author base on ID")
                            Model.Author.findOne({"id":req.params.id}, function(err, author){
                                if(err){
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                }else{
                                    if(author){
                                        Model.Book.findOne({author:author.fname}, function(err,book){
                                            if(book){
                                                res.json({
                                                            type: false,
                                                            data: "Cannot delete, author; there is a book with the same author"
                                                        })
                                            }
                                            else{
                                                author.remove(function(err){
                                                    if(err){
                                                        res.status(500);
                                                        res.json({
                                                            type: false,
                                                            data: "Error in removing Author"
                                                        })
                                                    }
                                                    else{
                                                        res.status(200);
                                                        res.json({
                                                            type: false,
                                                            data: "Successfully removed Author"
                                                        })
                                                    }
                                                })
                                            }
                                        })
                                    }
                                    else{
                                        res.status(404);
                                    res.json({
                                        type: false,
                                        message: "No Author found with this ID" + req.params.id
                                    })
                                    }
                                }
                            })
                        },

                        //retrieve author by ID, number 8
                          getAuthor : function(req, res, next) {
                              console.log('searched id: '+req.params.id);
                            Model.Author.findOne({"id": req.params.id}, function(err, author) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                                    if (author) {
                                        res.status(200);
                                        res.json({
                                            type: true,
                                            data: author
                                        })
                                    } else {
                                        res.status(404);
                                        res.json({
                                            type: false,
                                            data: "Author: " + req.params.id + " not found"
                                        })
                                    }
                                }
                            })
                        },


                        		//retrieve book by ISBN, number 9
                          getBook : function(req, res, next) {
                        	  console.log('searched isbn: '+req.params.isbn);
                            Model.Book.findOne({"isbn": req.params.isbn}, function(err, book) {
                                if (err) {
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                } else {
                                    if (book) {
                        				res.status(200);
                                        res.json({
                                            type: true,
                                            data: book
                                        })
                                    } else {
                                        res.status(404);
                                        res.json({
                                            type: false,
                                            data: "Book: " + req.params.isbn + " not found"
                                        })
                                    }
                                }
                            })
                        }, 

                        getBookTitle : function(req,res,next){
                            substring= req.params.t

                            Model.Book.find({title:new RegExp(substring)}, function(err,books){
                                if(err){
                                    res.status(500);
                                    res.json({
                                        type: false,
                                        data: "Error occured: " + err
                                    })
                                }
                                else{
                                    if(books){
                                        res.status(200);
                                        res.json({
                                        type: true,
                                        data: books
                                    })
                                    }
                                    else{
                                        res.status(404);
                                        res.json({
                                        type: false,
                                        data: "No book found with this substring"
                                    })
                                    }
                                }
                            })
                        }
                        	
                        	
                        	
                         
                          
                        };

                        module.exports = routes;