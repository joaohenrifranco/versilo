package versilo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application implements Runnable {

    private String host;
    private int port;

    private String username;

    private ObservableList<String> messagesArray = FXCollections.observableArrayList();
    private int lastMessageId = 0;

    private Stage window;
    private Scene sceneChat;

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;
    private Thread messageReceiverThread;

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Versilo");

        // GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane gridLogIn = new GridPane();
        gridLogIn.setPadding(new Insets(200, 100, 100, 150));
        gridLogIn.setVgap(8);
        gridLogIn.setHgap(10);

        // Username text field and label
        Label usernameLabel = new Label("Username");
        TextField usernameInput = new TextField("Jorge");
        GridPane.setConstraints(usernameLabel, 0, 0);
        GridPane.setConstraints(usernameInput, 1, 0);

        // IP text field and label
        Label ipLabel = new Label("Server IP");
        TextField ipInput = new TextField("127.0.0.1");
        GridPane.setConstraints(ipLabel, 0, 1);
        GridPane.setConstraints(ipInput, 1, 1);

        // PORT text field and label
        Label portLabel = new Label("Port");
        TextField portInput = new TextField("8080");
        GridPane.setConstraints(portLabel, 0, 2);
        GridPane.setConstraints(portInput, 1, 2);

        // Connect button for IP and port
        Button connectButton = new Button("Connect");
        GridPane.setConstraints(connectButton, 0, 3, 1, 2);
        connectButton.setOnAction(e -> {
            window.setScene(sceneChat);

            host = ipInput.getText();
            port = Integer.parseInt(portInput.getText());
            username = usernameInput.getText();

            messageSender = new MessageSender(host, port, username);

            messageReceiver = new MessageReceiver(host, port, lastMessageId, messagesArray);
            messageReceiverThread = new Thread(messageReceiver);
            messageReceiverThread.start();
        });

        // Message input box
        TextArea messageInput = new TextArea();
        messageInput.setPrefWidth(350);
        messageInput.setPrefHeight(100);
        messageInput.setWrapText(true);
        GridPane.setConstraints(messageInput, 0, 1);

        // Send button
        Button sendButton = new Button("Send");
        GridPane.setConstraints(sendButton, 1, 1);
        sendButton.setOnAction(e -> {
            messageSender.sendMessage(messageInput.getText());
            messageInput.clear();
        });

        // Messages view
        ListView<String> messageList = new ListView<String>(messagesArray);
        messageList.setPrefWidth(400);
        messageList.setPrefHeight(400);
        GridPane.setConstraints(messageList, 0, 0);

        // Adding to the grid and creating scene
        grid.getChildren().addAll(messageInput, sendButton, messageList);
        sceneChat = new Scene(grid, 500, 500);

        gridLogIn.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameInput, usernameLabel, connectButton);
        Scene sceneLogIn = new Scene(gridLogIn, 500, 500);

        // Assigning first scene
        window.setScene(sceneLogIn);
        window.show();
    }

}
