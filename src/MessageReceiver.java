public class MessageReceiver implements Runnable {

    private String host;
    private int port;
    private int lastMessageId = 0;

    public MessageReceiver(String newHost, int newPort) {
        host = newHost;
        port = newPort;
    }

    public void run() {
        HttpHandler httpHandler = new HttpHandler(host, port);

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