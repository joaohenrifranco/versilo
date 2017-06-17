import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpHandler {

    private String host;
    private int port;
    private Socket socket;
    private PrintWriter streamWriter;

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
            streamWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void sendPost(String path) {
        streamWriter.print("POST " + path + " HTTP/1.0\t\n");
    }

    public void sendUserAgent(String userAgent) {
        streamWriter.print("User-Agent: " + userAgent + "\t\n");
    }

    public void sendHost(String requestHost) {
        streamWriter.print("Host: " + requestHost + "\t\n");
    }

    public void sendContentType(String contentType) {
        streamWriter.print("Content-Type: " + contentType + "\t\n");
    }

    public void sendBody(String body) {
        streamWriter.print(body + "\t\n");
        streamWriter.flush();
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
