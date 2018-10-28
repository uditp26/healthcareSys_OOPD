/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
    
/**
 *
 * @author SONY
 */
public class SmartHealthcareSystem extends Application {
    Stage window;
    Scene homepageScene;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("SHSView.fxml"));
        homepageScene = new Scene(root, window.getWidth(), window.getHeight());
        window.setTitle("Smart Healthcare System");
        window.setScene(homepageScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
