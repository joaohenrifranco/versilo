package versilo;

import javafx.collections.ObservableList;
<<<<<<< HEAD
import javafx.scene.control.*;
import org.json.JSONObject;
=======
>>>>>>> fc5a597da913c1281b864f1aef07130737c5f511
import versilo.http.HttpHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MessageReceiver implements Runnable {

    private String host;
    private int port;
    private int lastMessageId;
    private ObservableList<String> messagesArray;

    MessageReceiver(String host, int port, int lastMessageId, ObservableList<String> messagesArray) {
        this.host = host;
        this.port = port;
        this.messagesArray = messagesArray;
        this.lastMessageId = lastMessageId;
    }

    public void run() {
        HttpHandler httpHandler = new HttpHandler(host, port);

        while (true) {

            // Waits x millis to probe for new messages

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            httpHandler.newRequest();
            httpHandler.sendPost("/getMessages.lua");
            httpHandler.sendUserAgent("Versilo/1.0");
            httpHandler.sendHost(host);
            httpHandler.sendContentType("application/x-www-form-urlencoded");
            try {
                httpHandler.sendBody(URLEncoder.encode("id=" + lastMessageId, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String httpResponse = null;

            try {
                httpResponse = httpHandler.getResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String httpResponseBody = httpHandler.getBody(httpResponse);


            JSONObject jsonObject = new JSONObject(httpResponseBody);
            JSONArray arrayJSON = jsonObject.getJSONArray("messages");
             for(int i = 0 ; i < arrayJSON.length(); i++){
                 messagesArray.add(arrayJSON.getJSONObject(i).getString("msg"));
             }
            System.out.print(httpResponseBody);

            //JSONObject jsonObject = new JSONObject(httpResponseBody);

            httpHandler.closeSocket();

        }
    }
}