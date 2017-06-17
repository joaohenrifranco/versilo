// This class runs as a thread reading what is typed from the keyboard and
// sending it as a post http request after every new line.

import java.util.Scanner;

public class MessageSender implements Runnable {

    private String host;
    private int port;

    public MessageSender(String newHost, int newPort) {
        host = newHost;
        port = newPort;
    }


    public void run() {

        HttpHandler httpHandler = new HttpHandler(host, port);

        while (true) {
            System.out.println("Enter message:");

            // Reads from keyboard to string
            Scanner keyboardScanner = new Scanner(System.in);
            String messageString = keyboardScanner.nextLine();

            httpHandler.newRequest();
            httpHandler.sendPost("/sendMessage");
            httpHandler.sendUserAgent("Versilo/1.0");
            httpHandler.sendHost(host);
            httpHandler.sendContentType("application/x-www-form-urlencoded");
            httpHandler.sendBody("msg=\"" + messageString + "\"");
            httpHandler.closeSocket();
        }
    }
}
