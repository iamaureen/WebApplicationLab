1.SockServer1
SockClient1

run the server, then run the client using the command
java SockClient1 <number>
result is shown in the cmd

2.SockServer2
SockClient2

java SockClient2 <number>
adds number continuously
java SockClient2 <reset>
Result is = 0

3.SockServer3
SockClient3

java SockClient3 <clientID> <number>

4.SockServer4
SockClient4

java SockClient4 <number>

instead of passing the input stream as byte array, its passed
as string, later parsed into integer, so no chance of data loss.
changed the input/output class to solve the integer encoding problem.

5.SockServer5
SockClient5

java SockClient5 <clientID> <number>

stores the xml file in D folder.
and restores back when the server is pulled up

6.SockServer6
SockClient6

java SockClient6 <clientID> <number>

tried implementing the thread using threadpool. 
