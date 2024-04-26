package com.example.shapest;
import javafx.scene.input.MouseEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.Random;

public class ShapeSlider extends Application {

    private Pane pane;
    private Shape[] shapes = new Shape[3];
    private Button previousButton;
    private Button nextButton;
    private Button changeBackgroundButton;
    private int currentIndex;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private Random random = new Random();
    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) {
        // Create shapes
        shapes[0] = createCircle(100, 150, 50);
        shapes[1] = createRectangle(100, 150, 100, 100);
        shapes[2] = createTriangle(100, 150, 100);

        // Set random initial shape
        currentIndex = random.nextInt(shapes.length);
        shapes[currentIndex].setVisible(true);

        // Set up the pane
        pane = new Pane();
        pane.getChildren().addAll(shapes);

        // Buttons
        previousButton = createStyledButton("Previous");
        previousButton.setOnAction(e -> showPreviousShape());
        previousButton.setLayoutX(10);
        previousButton.setLayoutY(10);

        nextButton = createStyledButton("Next");
        nextButton.setOnAction(e -> showNextShape());
        nextButton.setLayoutX(120);
        nextButton.setLayoutY(10);

        changeBackgroundButton = createStyledButton("Change Background");
        changeBackgroundButton.setOnAction(e -> changeBackgroundColor());
        changeBackgroundButton.setLayoutX(230);
        changeBackgroundButton.setLayoutY(10);

        pane.getChildren().addAll(previousButton, nextButton, changeBackgroundButton);

        // Create the scene
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Shape Slider");
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: linear-gradient(#f9f9f9, #e3e3e3),\n" +
                "                    linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d0d0d0 100%),\n" +
                "                    linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-background-radius: 5,4,3;\n" +
                "    -fx-padding: 7 30 7 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 4, 0.0 , 0 , 2 );\n" +
                "    -fx-text-fill: red;");

        return button;
    }

    private void changeBackgroundColor() {
        Color newColor = generateRandomColor();
        shapes[currentIndex].setFill(newColor);
    }

    private Color generateRandomColor() {
        int index = random.nextInt(colors.length);
        return colors[index];
    }

    private Circle createCircle(double centerX, double centerY, double radius) {
        Circle circle = new Circle(centerX, centerY, radius, Color.RED);
        circle.setOnMouseClicked(e -> changeBackgroundColor());
        circle.setOnMousePressed(this::handleMousePressed);
        circle.setOnMouseDragged(this::handleMouseDragged);
        circle.setOnMouseReleased(this::handleMouseReleased);
        circle.setVisible(false); // Initially hide the shape
        return circle;
    }

    private Rectangle createRectangle(double x, double y, double width, double height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.GREEN);
        rectangle.setOnMouseClicked(e -> changeBackgroundColor());
        rectangle.setOnMousePressed(this::handleMousePressed);
        rectangle.setOnMouseDragged(this::handleMouseDragged);
        rectangle.setOnMouseReleased(this::handleMouseReleased);
        rectangle.setVisible(false); // Initially hide the shape
        return rectangle;
    }

    private Shape createTriangle(double x, double y, double size) {
        double[] points = {x, y - size / 2, x + size / 2, y + size / 2, x - size / 2, y + size / 2};
        Shape triangle = new javafx.scene.shape.Polygon(points);
        triangle.setFill(Color.BLUE);
        triangle.setOnMouseClicked(e -> changeBackgroundColor());
        triangle.setOnMousePressed(this::handleMousePressed);
        triangle.setOnMouseDragged(this::handleMouseDragged);
        triangle.setOnMouseReleased(this::handleMouseReleased);
        triangle.setVisible(false); // Initially hide the shape
        return triangle;
    }

    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX() - ((Shape) event.getSource()).getLayoutX();
        yOffset = event.getSceneY() - ((Shape) event.getSource()).getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        ((Shape) event.getSource()).setLayoutX(event.getSceneX() - xOffset);
        ((Shape) event.getSource()).setLayoutY(event.getSceneY() - yOffset);
    }

    private void handleMouseReleased(MouseEvent event) {
        // No action needed on mouse release
    }

    private void showPreviousShape() {
        shapes[currentIndex].setVisible(false);
        currentIndex = (currentIndex - 1 + shapes.length) % shapes.length;
        shapes[currentIndex].setVisible(true);
    }

    private void showNextShape() {
        shapes[currentIndex].setVisible(false);
        currentIndex = (currentIndex + 1) % shapes.length;
        shapes[currentIndex].setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
