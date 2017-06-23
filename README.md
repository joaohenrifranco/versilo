# Versilo
This client application, named Versilo was developed using Java (JDK 1.8). Among its features are having configurable IP and server port, making it possible to choose a nickname and a graphical user interface. The program was modularized into five classes: Versilo.java, GUI.java, MessageReceiver.java, MessageSender.java and HttpHandler.java. It as developed as a college project.

### Versilo.java: 
Contains main(), responsible for calling a thread with the graphical interface, implemented in the class described below.
 
### GUI.java: 
Class that implements the graphical client interface. Created using the JavaFX library, it is divided into two Scenes. In the first one an IP, a Port and a username are requested to the user. The program then calls the MessageSender and MessageReceiver classes, passing in the received information as parameters. In it we have two main fields, one responsible for showing all the messages already sent to the server, returned by MessageReceiver, and another responsible for receiving a message written by the user and sending it to the MessageSender, using the SendMessage () method.
 
### MessageSender.java: 
Class responsible for sending messages to the server. Its main method is invoked by the GUI every time the user clicks the send message button. The method receives from the GUI the address of the server, the port, the message to be sent, and the username. From there it follows the steps for sending a standard HTTP POST: it opens the TCP socket, sends the request headers through it, sends the body (with message and nickname), and finally closes the socket. Each of these steps is accomplished with the help of methods of class HttpHandler.java. The request is sent to the /sendMessage.lua path, a script available on the server that handles receiving and storing messages.
 
### MessageReceiver.java: 
This class was built to be run on a thread, sending requests at regular intervals of x milliseconds to the server, requesting messages that the client does not already have. In this request, the client sends an ID corresponding to the last message received. The server responds with all necessary messages, and the client ID is updated. In the body of the HTTP response handled by the handler, received messages come in JSON format. They are parsed, and sent to the view in the GUI. The JSON handling was done using the org.json open source java library.
 
### HttpHandler.java: 
Although Java already had native libraries that implement the sending, receiving and manipulation of http messages, this class was developed with the purpose of manually implementing these functions, increasing the understanding of protocol operation. The class uses the Java Socket library. The methods include: newRequest, which opens the socket and creates objects to read and send streams through it, methods to send header parts like sendPost, sendUserAgent, etc., a sendBody method, sending the request body and a closeSocket, To close the connection. Finally we have the getResponse that receives in the incoming stream http responses, and the getResponseBody, which is a parser for a response returning only the body.
