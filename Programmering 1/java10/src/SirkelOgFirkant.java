import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.*;


public class SirkelOgFirkant extends Application {

    BorderPane borderPane;
    final int SIZE = 720;
    final int SS = SIZE/9; //Size sides

    Shape shape1;
    Shape shape2;

    Pane centerPane = new Pane();

    public void start(Stage primaryStage) {

        borderPane = new BorderPane();

        borderPane.setCenter(getCenterPane());
        borderPane.setBottom(getBottomPane());
        borderPane.setLeft(getLeftPane());
        borderPane.setTop(getTopLabel("Koordinater"));
        borderPane.setRight(getRightPane());

        Scene scene = new Scene(borderPane, SIZE, SIZE);

        primaryStage.setTitle("Tegn rektangel og sirkel");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public Pane getRightPane(){
        Pane rightSide = new Pane();

        rightSide.getChildren().addAll(getRightLabel(), getRoterBtn());

        return rightSide;
    }

    public Pane getBottomPane(){
        Pane bottomSide = new Pane();

        bottomSide.getChildren().addAll(
                getBottomLabel(),
                getFirkantBtn(),
                getSirkelBtn() );

        return bottomSide;
    }

    public Pane getLeftPane(){
        Pane leftSide = new Pane();
        leftSide.getChildren().addAll( getLeftLabel(), getRedBtn(), getBlueBtn() );
        return leftSide;
    }

    public Pane getCenterPane(){
        return centerPane;
    }

    public void setCenterPane(Pane p){
        this.centerPane = p;
    }

    public void setShapeColor(int r, int g, int b){
        if ( shape1 != null ) {
            Color c = Color.rgb(r, g, b);
            getShape1().setFill(c);
        }
    }

    public Shape getShape1(){
        return shape1;
    }

    public Shape getShape2(){
        return shape2;
    }

    public void setShape1(Shape s){
        this.shape1 = s;
    }

    public void setShape2(Shape s){
        this.shape2 = s;
    }

    public Button getFirkantBtn(){
        Button btn = new Button();
        btn.setText("Firkant");
        btn.setPrefSize(SS*2, SS);
        btn.setLayoutY(3);
        btn.setLayoutX(SIZE/2.0);
        btn.setOnAction(e -> {
            drawShape(getRectangle());
        });

        return btn;
    }

    public Button getRedBtn(){
        Button btn = new Button();
        btn.setText("Rød");
        btn.setOnAction(e -> {
            setShapeColor(255,0,0);
        });
        btn.setLayoutY(SIZE/2.0-SS*2);
        btn.setPrefSize(SS-3,SS);

        return btn;
    }

    public Button getBlueBtn(){
        Button btn = new Button();
        btn.setText("Blå");
        btn.setOnAction(e -> {
            setShapeColor(0,0,255);
        });
        btn.setPrefSize(SS-3,SS);
        btn.setLayoutY(SIZE/2.0-SS);

        return btn;
    }

    public Button getSirkelBtn(){
        Button btn = new Button();
        btn.setText("Sirkel");
        btn.setPrefSize(SS*2, SS);
        btn.setLayoutY(3);
        btn.setLayoutX( (SIZE/2.0)-SS*2);

        btn.setOnAction(e -> {
            drawShape(getCircle());
        });

        return btn;
    }

    public Button getRoterBtn(){
        Button btn = new Button();
        btn.setText("Roter");
        btn.setOnAction(e -> {
            if ( getShape1() != null ) {
                rotateShape(getShape1(), 45);
            }
        });

        btn.setPrefSize(SS-3,SS);
        btn.setLayoutY(SIZE/2.0-SS*1.5);
        btn.setLayoutX(3);

        return btn;
    }

    public void rotateShape(Shape s, int degrees){

            s.getTransforms().add(new Rotate(degrees, getShapeX(s), getShapeY(s)));
            updateCenter();

    }

    public int getShapeX(Shape s){

        if ( s instanceof Rectangle ){
            return (int) ((Rectangle) s).getX();
        } else {
            return (int)( (Circle) s).getCenterX();
        }

    }

    public int getShapeY(Shape s){

        if ( s instanceof Rectangle ){
            return (int) ((Rectangle) s).getY();
        } else {
            return (int)( (Circle) s).getCenterY();
        }

    }

    public void updateCenter(){
        getCenterPane().getChildren().clear();

      if ( getShape1() == null ) {}
      else if ( getShape1() != null && getShape2() == null ){
          getCenterPane().getChildren().add(getShape1());
          getShape1().setStrokeWidth(2);
      } else {
          getCenterPane().getChildren().addAll(getShape1(), getShape2());
          getShape1().setStrokeWidth(2);
          getShape2().setStrokeWidth(0);
      }

      borderPane.setCenter(getCenterPane());
      updateCoords();

    }

    public void updateCoords(){

        int s1x = 0, s1y = 0, s2x, s2y;

        if ( getShape1() != null ) {
            s1x = getShapeX(getShape1());
            s1y = getShapeY(getShape1());
            borderPane.setTop(getTopLabel("Current shape: (" + s1x + ", " +s1y + ")"));
        }

        if ( getShape2() != null ) {
            s2x = getShapeX(getShape2());
            s2y = getShapeY(getShape2());

            borderPane.setTop(getTopLabel("Current shape: (" + s1x + ", " +s1y + ")" +
                    " \nSecondary shape: (" + s2x + ", " +s2y + ")"));
        }

    }

    public void drawShape(Shape s){

        if( getShape1() != null) {
            setShape2(getShape1());
        }

        setShape1(s);

        getShape1().setOnMouseClicked(e -> {

            if ( e.getSource() != getShape1() ) {
                if( getShape1() != null && getShape2() != null) {
                    Shape temp = getShape2();
                    setShape2(getShape1());
                    setShape1(temp);

                }
            }
            updateCenter();

        });

        updateCenter();
    }

    public Circle getCircle(){
        Circle circle = new Circle();

        int radius = SS;

        circle.setCenterX(getRandInCenter(radius, radius));
        circle.setCenterY(getRandInCenter(radius, radius));
        circle.setRadius(radius);
        circle.setStroke(Color.GREEN);
        circle.setStrokeWidth(0);

        return circle;
    }

    public Rectangle getRectangle(){

        Rectangle rectangle = new Rectangle();
        rectangle.setX(getRandInCenter(SS*2));
        rectangle.setY(getRandInCenter(SS));
        rectangle.setWidth(SS*2);
        rectangle.setHeight(SS);
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(0);

        return rectangle;

    }

    public int getRandInCenter( int lengthOfObject ){
        return (int)( Math.random() *(SIZE-SS*2-lengthOfObject));
    }

    public int getRandInCenter( int lengthOfObject, int negativeLOF ){
        return (int)( Math.random() *(SIZE-SS*2-lengthOfObject-negativeLOF) )+negativeLOF;
    }

    public Label getTopLabel(String s){
        Label lbl = new Label(s);
        lbl.setPrefHeight(SS);
        lbl.prefWidthProperty().bind(borderPane.widthProperty());
        lbl.setStyle("-fx-border-width: 0 0 3 0;" +
                " -fx-border-style: solid;" +
                " -fx-font-weight: bold;" +
                " -fx-background-color: #eeeef5");
        lbl.setAlignment(Pos.BASELINE_CENTER);
        return lbl;
    }

    public Label getRightLabel(){
        Label lbl = new Label();
        lbl.setPrefWidth(SS);
        lbl.prefHeightProperty().bind(borderPane.heightProperty().subtract(SS*2));
        lbl.setStyle("-fx-border-width: 0 0 0 3;" +
                " -fx-border-style: solid;" +
                " -fx-font-weight: bold;" +
                " -fx-background-color: #eeeef5");
        lbl.setAlignment(Pos.BASELINE_CENTER);
        return lbl;
    }

    public Label getLeftLabel(){
        Label lbl = new Label();
        lbl.setPrefWidth(SS);
        lbl.prefHeightProperty().bind(borderPane.heightProperty().subtract(SS*2));
        lbl.setStyle("-fx-border-width: 0 3 0 0;" +
                " -fx-border-style: solid;" +
                " -fx-font-weight: bold;" +
                " -fx-background-color: #eeeef5");
        lbl.setAlignment(Pos.BASELINE_CENTER);
        return lbl;
    }

    public Label getBottomLabel(){
        Label lbl = new Label();
        lbl.setPrefHeight(SS);
        lbl.prefWidthProperty().bind(borderPane.widthProperty());
        lbl.setStyle("-fx-border-width: 3 0 0 0;" +
                " -fx-border-style: solid;" +
                " -fx-font-weight: bold;" +
                " -fx-background-color: #eeeef5");
        return lbl;
    }

    public static void main(String[] args) {
        launch(args);
    }

}








