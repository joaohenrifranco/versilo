package versilo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpHandler {

    private String host;
    private int port;
    private Socket socket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public HttpHandler(String newHost, int newPort) {
        host = newHost;
        port = newPort;
    }

    public void newRequest() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void sendPost(String path) {
        socketOut.print("POST " + path + " HTTP/1.0\r\n");
    }

    public void sendUserAgent(String userAgent) {
        socketOut.print("User-Agent: " + userAgent + "\r\n");
    }

    public void sendHost(String requestHost) {
        socketOut.print("Host: " + requestHost + "\r\n");
    }

    public void sendContentType(String contentType) {
        socketOut.print("Content-Type: " + contentType + "\r\n");
    }

    public void sendBody(String body) {
        socketOut.print("\r\n" + body);
        socketOut.flush();
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() throws IOException {
        StringBuilder builder = new StringBuilder();
        String aux;

        while ((aux = socketIn.readLine()) != null) {
            builder.append(aux);
        }

        return builder.toString();
    }

//TODO: implement this. Right now its a dummy method

    public String getBody(String httpResponse) {
        return httpResponse;
    }
}

