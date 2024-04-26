package com.example.shapest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class Shape extends Pane {
    public Shape(double size) {
        setPrefSize(size, size);
    }

    public void setFill(Color newColor) {
    }
}
