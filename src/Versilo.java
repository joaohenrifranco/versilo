public class Versilo {
    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 8080;

        //Creates and runs sender thread
        MessageSender messageSender = new MessageSender(host, port);
        Thread messageSenderThread = new Thread(messageSender);
        messageSenderThread.run();

//        //Creates and runs receiver thread
//        MessageReceiver messageReceiver = new MessageReceiver(host, port);
//        Thread messageReceiverThread = new Thread(messageReceiver);
//        messageReceiverThread.run();

    }
}