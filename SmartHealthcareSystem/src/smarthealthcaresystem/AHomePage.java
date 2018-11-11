/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author protagonist26
 */
public class AHomePage {
    Parent adminPage;
    Scene adminScene;
    
    public void startAHP(Stage window) throws IOException {
        adminPage = FXMLLoader.load(getClass().getResource("AHomePageView.fxml"));
        adminScene = new Scene(adminPage, window.getWidth(), window.getHeight());
        window.setScene(adminScene);
        window.show();
    }
    
}
