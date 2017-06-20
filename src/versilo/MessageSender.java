// This class runs as a thread reading what is typed from the keyboard and
// sending it as a post http request after every new line.

package versilo;

import versilo.http.HttpHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MessageSender {

    private String host;
    private int port;
    private String username;

    MessageSender(String host, int port, String username) {
        this.host = host;
        this.port = port;
        this.username = username;
    }

    public void sendMessage(String messageString) {

        HttpHandler httpHandler = new HttpHandler(host, port);

        httpHandler.newRequest();
        httpHandler.sendPost("/sendMessage.lua");
        httpHandler.sendUserAgent("Versilo/1.0");
        httpHandler.sendHost(host);
        httpHandler.sendContentType("application/x-www-form-urlencoded");
        try {
            httpHandler.sendBody(URLEncoder.encode("msg=" + messageString + "&" + "sender=" + username, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpHandler.closeSocket();
    }
}
