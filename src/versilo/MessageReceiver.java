package versilo;

import versilo.http.HttpHandler;

import java.io.IOException;
import java.util.Arrays;

public class MessageReceiver implements Runnable {

    private String host;
    private int port;
    private int refreshRate = 2;

    MessageReceiver(String newHost, int newPort) {
        host = newHost;
        port = newPort;
    }

    public void run() {
        HttpHandler httpHandler = new HttpHandler(host, port);
        int lastMessageId = 0;

        while (true) {

            // Waits refreshRate seconds to probe for new messages

            try {
                Thread.sleep(1000 * refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            httpHandler.newRequest();
            httpHandler.sendPost("/getMessages.lua");
            httpHandler.sendUserAgent("Versilo/1.0");
            httpHandler.sendHost(host);
            httpHandler.sendContentType("application/x-www-form-urlencoded");
            httpHandler.sendBody("id=" + lastMessageId + "\t\n");

            String responseCode = null;
            String[] responseArray = null;

            try {
                responseArray = httpHandler.getResponse();
                System.out.println(Arrays.toString(responseArray));
            } catch (IOException e) {
                e.printStackTrace();
            }

            responseCode = httpHandler.getResponseCode(responseArray[0]);

            // Check for HTTP Bad Request
            // TODO: Try-Catch for exiting method properly

            if (responseCode.compareTo("200") != 0) {
                System.out.println("Not 200");

            }

            String responseBody = httpHandler.getResponseBody(responseArray);

            //TODO: responseBody is a JSon. Parse it.


            httpHandler.closeSocket();
        }
    }
}