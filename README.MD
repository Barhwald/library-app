LIBRARY APP

This is an app made for a library.

Consists of 4 entities: Book (a specific title), Book copies (existing copies of a title), Reader, Loan. 

It lets the user do the following:
1. Add a reader.
2. See all readers.
3. Add a title.
4. See a specific title.
5. See all titles.
6. Add a book copy.
7. See all copies.
8. See all copies of a specific book (no matter their status).
9. See how many AVAILABLE copies there are of a specific book.
10. Change the status of a book copy.
11. Loan a book copy.
12. See all loans.
13. Return a book copy.

Requests for Postman:
1. POST http://localhost:8080/v1/readers
2. GET http://localhost:8080/v1/readers
3. POST http://localhost:8080/v1/books
4. GET http://localhost:8080/v1/books/{bookId}
5. GET http://localhost:8080/v1/books
6. POST http://localhost:8080/v1/copies
7. GET http://localhost:8080/v1/copies
8. GET http://localhost:8080/v1/copies/{bookId}
9. GET http://localhost:8080/v1/books/{bookId}/available
10. PUT http://localhost:8080/v1/copies/{bookCopyId}
11. POST http://localhost:8080/v1/loans
12. GET http://localhost:8080/v1/loans
13. PUT http://localhost:8080/v1/loans/{loanId}