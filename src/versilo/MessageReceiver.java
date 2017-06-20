package versilo;

import javafx.collections.ObservableList;
import versilo.http.HttpHandler;

import java.io.IOException;

public class MessageReceiver implements Runnable {

    private String host;
    private int port;
    private int lastMessageId;
    private ObservableList<String> messagesArray;

    MessageReceiver(String newHost, int newPort, int newLastMessageId, ObservableList<String> newMessagesArray) {
        host = newHost;
        port = newPort;
        messagesArray = newMessagesArray;
        lastMessageId = newLastMessageId;
    }

    public void run() {
        HttpHandler httpHandler = new HttpHandler(host, port);

        while (true) {

            // Waits x millis to probe for new messages

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            httpHandler.newRequest();
            httpHandler.sendPost("/getMessages.lua");
            httpHandler.sendUserAgent("Versilo/1.0");
            httpHandler.sendHost(host);
            httpHandler.sendContentType("application/x-www-form-urlencoded");
            httpHandler.sendBody("id=" + lastMessageId + "\r\n");

            String response = null;

            try {
                response = httpHandler.getResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(response);

            httpHandler.closeSocket();

        }
    }
}