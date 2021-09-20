

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PoorMansScreenSaver extends Application {
    private MyWindow myWindow = new MyWindow();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(myWindow, 200, 150);
        primaryStage.setTitle("Screensaver"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        myWindow.requestFocus();

        myWindow.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                myWindow.pause();
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                myWindow.play();
            }
        });
        myWindow.setOnKeyPressed((e -> {
            if (e.isControlDown()) {
                myWindow.increaseSpeed();
            }
        }));
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class MyWindow extends Pane {
    public final double radius = 20;
    private double dx = 1, dy = 1;
    private int heigthAndWidth = 20;
    private int x = 10, y = 10;
    private Rectangle rectangle = new Rectangle(x, y, heigthAndWidth, heigthAndWidth);
    private Timeline animation;

    public MyWindow() {
        rectangle.setFill(Color.GREEN);
        getChildren().add(rectangle);
        rectangle.setOnMouseClicked((e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                rectangle.setHeight(rectangle.getHeight()+1);
                rectangle.setWidth(rectangle.getWidth()+1);
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Increasing speed...");
                increaseSpeed();
            }

        }));



            // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveRectangle()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveRectangle() {
        // Check boundaries
        if (x < 0 || x > getWidth() - heigthAndWidth) {
            dx *= -1; // Change rect move direction
        }
        if (y < 0 || y > getHeight() - heigthAndWidth) {
            dy *= -1; // Change rect move direction
        }

        // Adjust rect position
        x += dx;
        y += dy;
        rectangle.setX(x);
        rectangle.setY(y);
    }
}
