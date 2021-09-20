import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;


public class Pendelum extends Application {

    public void start(Stage primaryStage) {
        PendulumPane pane = new PendulumPane(); // du må lage klassen PendulumPane

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Pendulum"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            pane.next();
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        pane.setOnMouseClicked(e -> {
            if (animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            } else {
                animation.pause();
            }
        });

        pane.requestFocus();
        pane.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.UP) {
                increaseSpeed(animation);
            } else if (e.getCode() == KeyCode.DOWN) {
                decreaseSpeed(animation);
            }
        });

    }

    public void increaseSpeed(Timeline t){
        t.setRate(t.getRate()+0.1);
    }

    public void decreaseSpeed(Timeline t){
        if ( t.getRate() > 0) {
            t.setRate(t.getRate() - 0.1);
        }
    }

    public static void main(String[] args) { launch(args); }
}

class PendulumPane extends Pane {

    private Circle staticCircle = new Circle(150, 30, 10, Color.BLACK);
    private Circle movingCircle = new Circle(20, Color.BLACK);

    private int pendulumRadius = 100;
    private int staringAngle = 120;
    private int angle = 120;
    private int direction = 1;

    private Line line = new Line(150, 30, getxBall(staringAngle), getyBall(staringAngle));

    public PendulumPane() {
        getChildren().addAll(staticCircle, movingCircle, line);
        next();
    }

    public void next() {
        double x = getxBall(angle);
        double y = getyBall(angle);

        movingCircle.setCenterX(x);
        movingCircle.setCenterY(y);
        line.setEndX(x);
        line.setEndY(y);
        nextAngle();
    }

    private void nextAngle() {

        if (angle > staringAngle) {
            direction = -1;
        } else if (angle < (180 - staringAngle)) {
            direction = 1;
        }

        angle += direction;
    }

    private double getxBall(int angle) {
        return 150 + pendulumRadius * Math.cos(Math.toRadians(angle));
    }

    private double getyBall(int angle) {
        return 30 + pendulumRadius * Math.sin(Math.toRadians(angle));
    }

}
