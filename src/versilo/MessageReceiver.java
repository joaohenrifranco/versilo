package versilo;

public class MessageReceiver implements Runnable {

    private String host;
    private int port;

    MessageReceiver(String newHost, int newPort) {
        host = newHost;
        port = newPort;
    }

    public void run() {
        HttpHandler httpHandler = new HttpHandler(host, port);
        int lastMessageId = 0;

        while (true) {

            httpHandler.newRequest();
            httpHandler.sendPost("/getMessages");
            httpHandler.sendUserAgent("Versilo/1.0");
            httpHandler.sendHost(host);
            httpHandler.sendContentType("application/x-www-form-urlencoded");
            httpHandler.sendBody("id=" + lastMessageId + "\t\n");
            httpHandler.closeSocket();

        }

    }
}