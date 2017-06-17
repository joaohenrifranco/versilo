import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Versilo {
    public static void main(String[] args)
            throws IOException {

        System.out.println("Enter message:");
        Scanner keyboardScanner = new Scanner(System.in);

        while (keyboardScanner.hasNextLine()) {
            String messageString = keyboardScanner.nextLine();

            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Socket opened");

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print("POST /sendMessage HTTP/1.0\t\n");
            pw.print("User-Agent: Versilo/1.0\t\n");
            pw.print("Host: 127.0.0.1\t\n");
            pw.print("Content-Type: application/x-www-form-urlencoded\t\n");
            pw.print("msg=\"" + messageString + "\"");
            pw.flush();
            System.out.println("Http Request sent");

            socket.close();
            System.out.println("Socket closed");

        }
    }
}