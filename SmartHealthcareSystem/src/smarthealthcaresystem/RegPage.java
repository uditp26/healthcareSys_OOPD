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
public class RegPage {
    Parent rpage;
    Scene regpageScene;
    
    public void startReg(Stage window) throws IOException {
        rpage = FXMLLoader.load(getClass().getResource("RegPageView.fxml"));
        regpageScene = new Scene(rpage, window.getWidth(), window.getHeight());
        window.setScene(regpageScene);
        window.show();
    }
    
}
