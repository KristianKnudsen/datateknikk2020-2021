import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Sjakkbrett extends Application {

    public void start(Stage primaryStage) throws Exception{

        Pane pane = new Pane();

        Scene scene = new Scene(pane, 300, 300);

        primaryStage.setTitle("Sjakkbrett");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();


        for ( int j = 0; j<= 8; j++ ) {

            for (int i = 0; i <= 8; i += 1) {

                if((i+j)%2!=0) {

                    Rectangle rectangle = new Rectangle();
                    rectangle.widthProperty().bind(scene.widthProperty().divide(8));
                    rectangle.heightProperty().bind(scene.heightProperty().divide(8));
                    rectangle.xProperty().bind(scene.widthProperty().divide(8).multiply(i));
                    rectangle.yProperty().bind(scene.heightProperty().divide(8).multiply(j));

                    pane.getChildren().add(rectangle);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
