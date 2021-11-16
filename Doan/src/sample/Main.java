package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader login = new FXMLLoader(getClass().getResource("login.fxml"));

        Parent root = login.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 550, 312));

        Controller control = login.getController();
        control.setPrimaryStage(primaryStage);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }



}
