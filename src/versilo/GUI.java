package versilo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application implements Runnable {

    Stage window;
    Scene sceneChat, sceneLogIn;

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Seu Jorge");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane gridLogIn = new GridPane();
        gridLogIn.setPadding(new Insets(200, 100, 100, 150));
        gridLogIn.setVgap(8);
        gridLogIn.setHgap(10);

        //Message input box
        TextArea messageInput = new TextArea();
        messageInput.setPrefWidth(350);
        messageInput.setPrefHeight(100);
        messageInput.setWrapText(true);
        GridPane.setConstraints(messageInput, 0, 1);

        //Send button
        Button sendButton = new Button("Send");
        GridPane.setConstraints(sendButton, 1, 1);

        //IP Button and label
        Label ipLabel = new Label("Server IP");
        TextField ipInput = new TextField();
        GridPane.setConstraints(ipLabel, 0, 0);
        GridPane.setConstraints(ipInput, 1, 0);

        //PORT Button and label
        Label portLabel = new Label("Port");
        TextField portInput = new TextField();
        GridPane.setConstraints(portLabel, 0, 1);
        GridPane.setConstraints(portInput, 1, 1);

        //Ok button
        Button okButton = new Button("Ok");
        GridPane.setConstraints(okButton, 0, 2, 1, 2);
        okButton.setOnAction(e -> window.setScene(sceneChat));

        //Messages view
        ListView<String> messageList = new ListView<String>();
        messageList.getItems().addAll("Olar", "Tudo bem?", "Sim");
        messageList.setPrefWidth(400);
        messageList.setPrefHeight(400);
        GridPane.setConstraints(messageList, 0, 0);


        //Adding to the grid and creating scene
        grid.getChildren().addAll(messageInput, sendButton, messageList);
        sceneChat = new Scene(grid, 500, 500);
        gridLogIn.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, okButton);
        sceneLogIn = new Scene(gridLogIn, 500, 500);

        //Assigning first scene
        window.setScene(sceneLogIn);
        window.show();
    }

}