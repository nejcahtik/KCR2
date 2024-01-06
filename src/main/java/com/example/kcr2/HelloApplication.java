package com.example.kcr2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Izposojevalnica avtomobilov Avtek");
        stage.setScene(scene);
        stage.show();
    }

    private FileInputStream getResourcesPath(String resName, ResourceType resType) throws FileNotFoundException {
        //todo: relative path
        String resPath = "/home/nejc/IdeaProjects/kcr2/src/main/resources/";
        switch(resType) {
            case IMAGE:
                return new FileInputStream(resPath + "images/" + resName);
            default:
                throw new IllegalArgumentException("resource type not yet supported");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

enum ResourceType {
    IMAGE
}