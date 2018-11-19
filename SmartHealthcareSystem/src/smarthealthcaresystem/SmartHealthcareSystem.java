/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.smarthealthcaresystem");
    private static FileHandler fh;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        window.setWidth(1024);
        window.setHeight(768);
        Parent root = FXMLLoader.load(getClass().getResource("SHSView.fxml"));
        homepageScene = new Scene(root, window.getWidth(), window.getHeight());
        window.setTitle("Smart Healthcare System");
        window.setScene(homepageScene);
        window.show();
    }
    
    public static FileHandler getFHandler(){
        return fh;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            fh = new FileHandler("elogs.log");
            launch(args);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
    }
    
}
