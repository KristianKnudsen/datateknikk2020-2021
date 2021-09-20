import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TowerOfHanoi extends Application {

    private TextField inputField = new TextField();
    private TextArea outputField = new TextArea();

    private Button btn = new Button("Find moves");
    private HBox hb = new HBox();

    private GridPane gridPane = new GridPane();

    public void start(Stage primaryStage) throws Exception {

        setStyles();

        Scene scene = new Scene(gridPane, 400, 500);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Tower Of Hanoi");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void writeToField(String s){
        outputField.appendText(s + "\n");
    }

    private void setStyles(){

        inputField.setPromptText("Number of disks");

        hb.getChildren().addAll(inputField, btn);

        gridPane.setHgap(5);
        gridPane.setVgap(5);

        inputField.setPrefWidth(310);

        btn.setPrefWidth(80);
        btn.setOnAction(e ->
                moveDisks(Integer.parseInt(inputField.getText()))
                );

        outputField.setMaxWidth(390);
        outputField.setPrefHeight(455);
        outputField.setEditable(false);

        gridPane.add(hb, 1, 1);
        gridPane.add(outputField, 1, 2);

    }
    int calls = 1;

    private void moveDisks(int n){
        outputField.setText("");
        calls = 1;

        writeToField("Moves are:");
        moveDisks(n, 'A', 'B', 'C');
        writeToField("\nNumber of calls to the method is:  " + calls);

    }

    private void moveDisks(int n, char fromTower,
                                 char toTower, char auxTower) {

        if (n == 1) {

            writeToField("Move number: " + calls + " - " + "Move disk "
                    + n + " from " + fromTower + " to " + toTower);

        }
        else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            calls++;

            writeToField("Move number: " + calls + " - " + "Move disk "
                    + n + " from " + fromTower + " to " + toTower );
            calls++;
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}