/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class AHomePageController implements Initializable {

    SmartHealthcareSystem shs;
    Stage window;
    HBox hbox;
    Region region1, region2;
    GridPane gridPane;
    Label dname; 
    
    @FXML
    Label name;
    
    @FXML
    Button add_doc;
    
    @FXML
    Button view_patient;
    
    @FXML
    Button view_doc;
    
    @FXML
    Button reassign_doc;
    
    @FXML
    Button logout;
    
    @FXML
    BorderPane borderPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        shs = new SmartHealthcareSystem();
    }    
    
    public void onClickListener(ActionEvent event) throws Exception{
        if(event.getSource() == add_doc){
            //node = borderPane.getCenter();
            //borderPane
            hbox = new HBox();
            region1 = new Region();
            region2 = new Region();
            gridPane = new GridPane();
            hbox.getChildren().add(region1);
            hbox.getChildren().add(gridPane);
            hbox.getChildren().add(region2);
        }
        else if(event.getSource() == view_doc){
            
        }
        else if(event.getSource() == view_patient){
            
        }
        else if(event.getSource() == reassign_doc){
            
        }
        else if(event.getSource() == logout){
            window = (Stage) logout.getScene().getWindow();
            shs.start(window);
        }
    }
}


