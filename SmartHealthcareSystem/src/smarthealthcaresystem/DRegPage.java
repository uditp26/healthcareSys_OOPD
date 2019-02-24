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
public class DRegPage {
    Parent drpage;
    Scene dregpageScene;
    
    public void startReg(Stage window) throws IOException {
        drpage = FXMLLoader.load(getClass().getResource("DRegPageView.fxml"));
        dregpageScene = new Scene(drpage, window.getWidth(), window.getHeight());
        window.setScene(dregpageScene);
        window.show();
    }
}
