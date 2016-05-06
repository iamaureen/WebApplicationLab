ISHRAT AHMED
1209600207

-npm install restify
-run: node start.js
-use postman

see all authors: localhost:8080/getAuthor
create author: localhost:8080/createAuthor [input: id,fname,lname]
see specific author: localhost:8080/getAuthor/*id* [i.e. localhost:8080/getAuthor/6]
update author(by firstname or lastname): localhost:8080/updateAuthor/*id* [input fname,lname via postman]
delete author: localhost:8080/deleteAuthor/1


see all books: localhost:8080/getBook
see specific book: localhost:8080/getBook/*isbn* [i.e. localhost:8080/getBook/123]
create books: localhost:8080/createBook [input: isbn, title, year, publisher, author]
update book(by title, year, publisher): localhost:8080/updateBook/*isbn* [input via postman]
add author to a book: localhost:8080/addAuthor/*isbn* [input author via postman]
delete a book: localhost:8080/deleteBook/*isbn*
retrieve book by substring of a title: localhost:8080/getBookTitle/*substring* [i.e. localhost:8080/getBookTitle/spring]