//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class MessageReceiver implements Runnable {
//
//    private String host;
//    private int port;
//    private int lastMessageId = 0;
//
//    public MessageReceiver(String newHost, int newPort){
//        host = newHost;
//        port = newPort;
//    }
//
//    public void run() {
//
//        while(true) {
//            sendGet(lastMessageId);
//            lastMessageId = receiveMessages();
//        }
//
//        }
//
//    public void sendGet(int lastMessageId) {
//
//        // Opens socket
//
//        Socket socket = null;
//
//        try {
//            socket = new Socket("127.0.0.1", 8080);
//        } catch (IOException e) {
//            System.out.println("Message Receiver: Could not open socket");
//        }
//
//        if (socket != null) {
//
//            // Sends http get request
//
//            PrintWriter streamWriter = null;
//            try {
//                streamWriter = new PrintWriter(socket.getOutputStream());
//                streamWriter.print("GET  /getMessages?lastId=" + lastMessageId + "HTTP/1.0\t\n");
//                streamWriter.print("User-Agent: Versilo/1.0\t\n");
//                streamWriter.print("Host: 127.0.0.1\t\n");
//                streamWriter.print("Content-Type: application/x-www-form-urlencoded\t\n");
//                streamWriter.flush();
//
//            } catch (IOException e) {
//                System.out.println("Failed to get messages at ID: " + lastMessageId);
//            }
//
//            // Closes socket
//
//            try {
//                socket.close();
//                System.out.println("Socket closed");
//            } catch (IOException e) {
//                System.out.println("Failed to close socket");
//            }
//        }
//    }
//
////    public int receiveMessages() {
////
////        Socket socket = null;
////        try {
////            socket = new Socket("127.0.0.1", 8080);
////        } catch (IOException e) {
////            System.out.println("Message Receiver: Could not open socket");
////        }
////
////        if (socket != null) {
////
////        }
////
////    }
//}
