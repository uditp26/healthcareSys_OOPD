/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author protagonist26
 */
public class SHSController implements Initializable{
    Stage window;
    RegPage regPage;
    
    @FXML
    Button signbtn;
    
    @FXML
    Button logbtn;
    
    @FXML
    TextField uid;
    
    @FXML
    PasswordField pwd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regPage = new RegPage();
    }
    
    @FXML
    public void onClickListener(ActionEvent event) throws IOException{
        if(event.getSource() == signbtn){
            window = (Stage) signbtn.getScene().getWindow();
            regPage.startReg(window);
            
        }
        else if(event.getSource() == logbtn){
        
        }
    }
    
}
