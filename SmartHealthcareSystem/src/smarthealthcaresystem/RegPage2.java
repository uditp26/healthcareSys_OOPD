/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author protagonist26
 */
public class RegPage2 {
    Parent rpage2;
    Scene regpage2Scene;

    public RegPage2() {}
    
    public void startReg2(Stage window) throws IOException {
        rpage2 = FXMLLoader.load(getClass().getResource("RegPage2View.fxml"));
        regpage2Scene = new Scene(rpage2, window.getWidth(), window.getHeight());
        window.setScene(regpage2Scene);
        window.show();
    }
    
}
