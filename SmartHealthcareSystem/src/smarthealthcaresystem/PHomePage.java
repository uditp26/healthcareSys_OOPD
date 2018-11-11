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
 * @author SONY
 */
public class PHomePage{
    Parent ppage;
    Scene ppagescene;
    
    public void startLog(Stage window) throws IOException {
        ppage = FXMLLoader.load(getClass().getResource("PHomePageView.fxml"));
        ppagescene = new Scene(ppage, window.getWidth(), window.getHeight());
        window.setScene(ppagescene);
        window.show();
    }
    
}
