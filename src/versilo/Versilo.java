package versilo;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class Versilo {
    public static void main(String[] args) throws MalformedURLException, UnknownHostException {

        GUI chatGUI = new GUI();
        Thread guiThread = new Thread(chatGUI);
        guiThread.start();

    }
}