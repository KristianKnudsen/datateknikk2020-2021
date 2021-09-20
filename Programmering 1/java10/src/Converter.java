import com.sun.jdi.PrimitiveValue;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Converter extends Application {

    private TextField box1 = new TextField();
    private TextField box2 = new TextField();

    private Button btnD2B = new Button("Decimal to Binary");
    private Button btnD2H = new Button("Decimal to Hex");
    private Button btnB2D = new Button("Binary to Decimal");
    private Button btnH2D = new Button("Hex to Decimal");

    public void start(Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        box1.autosize();
        box2.autosize();

        box1.setPrefWidth(300);

        gridPane.add(new Label("Input:"), 0, 0);
        gridPane.add(box1, 1, 0);
        gridPane.add(new Label("Output:"), 0, 1);
        gridPane.add(box2, 1, 1);
        gridPane.add(btnD2B,0,2);
        gridPane.add(btnD2H,1,2);
        gridPane.add(btnB2D,0,3);
        gridPane.add(btnH2D,1,3);


        gridPane.setAlignment(Pos.CENTER);
        box1.setAlignment(Pos.BOTTOM_LEFT);
        box2.setAlignment(Pos.BOTTOM_LEFT);
        box2.setEditable(false);

        GridPane.setHalignment(btnD2B, HPos.CENTER);
        GridPane.setHalignment(btnD2H, HPos.CENTER);
        GridPane.setHalignment(btnB2D, HPos.CENTER);
        GridPane.setHalignment(btnH2D, HPos.CENTER);

        btnD2B.setOnAction(e ->

                box2.setText(dec2Bin(Integer.parseInt(box1.getText())))

                );

        btnD2H.setOnAction(e ->

                box2.setText(dec2Hex(Integer.parseInt(box1.getText())))

                );

        btnB2D.setOnAction(e ->

            box2.setText(Integer.toString(bin2Dec(box1.getText())))

        );

        btnH2D.setOnAction( e ->

                box2.setText(Integer.toString(hex2Dec(box1.getText())))

                );

        Scene scene = new Scene(gridPane, 480, 250);
        primaryStage.setTitle("Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private String dec2Bin(int value){

        if (value == 0){
            return "";
        }

        return dec2Bin(value/2) + value%2 ;
    }

    private String dec2Hex(int value){

        if (value == 0){
            return "";
        }

        return dec2Hex(value/16) + "0123456789ABCDEF".charAt(value%16);
    }


    private int bin2Dec(String string){
        return bin2Dec(string, 0, string.length());
    }

    private int bin2Dec(String string, int low, int high ){
        if (low == high){
            return 0;
        }
        return (string.charAt(low)-'0') * (int)Math.pow(2, high-low-1) + bin2Dec( string, low+1, high );

    }

    private int hex2Dec(String string){
        return hex2Dec(string, 0, string.length());
    }

    private int hex2Dec(String string, int low, int high){
        if (low == high){
            return 0;
        }

        return ("0123456789ABCDEF".indexOf(string.charAt(low)) )
                * (int)Math.pow(16,high-low-1)+hex2Dec(string, low+1, high);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
