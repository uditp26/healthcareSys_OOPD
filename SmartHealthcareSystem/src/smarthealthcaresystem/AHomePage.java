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
    private Parent adminPage;
    private Scene adminScene;
    
    public void startAHP(Stage window) throws IOException {
        adminPage = FXMLLoader.load(getClass().getResource("AHomePageView.fxml"));
        setAdminScene(new Scene(getAdminPage(), window.getWidth(), window.getHeight()));
        window.setScene(getAdminScene());
        window.show();
    }

    /**
     * @return the adminScene
     */
    public Scene getAdminScene() {
        return adminScene;
    }

    /**
     * @param adminScene the adminScene to set
     */
    public void setAdminScene(Scene adminScene) {
        this.adminScene = adminScene;
    }

    /**
     * @return the adminPage
     */
    public Parent getAdminPage() {
        return adminPage;
    }
    
}
