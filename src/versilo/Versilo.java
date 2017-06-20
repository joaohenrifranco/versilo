package versilo;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class Versilo {
    public static void main(String[] args) throws MalformedURLException, UnknownHostException {

        String host = "127.0.0.1";
        int port = 8080;

        //Creates and runs receiver thread
        MessageReceiver messageReceiver = new MessageReceiver(host, port);
        Thread messageReceiverThread = new Thread(messageReceiver);
        messageReceiverThread.start();

        //Creates and runs sender thread
        MessageSender messageSender = new MessageSender(host, port);
        Thread messageSenderThread = new Thread(messageSender);
        messageSenderThread.start();

        //Creates and runs GUI thread
        GUI chatGUI = new GUI();
        Thread guiThread = new Thread(chatGUI);
        guiThread.start();
    }
}