/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class RegPageController implements Initializable {
    private static final String username = "root";      // Change username if not root
    private static final String password = "";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";     
    private String query;
    
    Stage window;
    SmartHealthcareSystem shs;
    
    @FXML
    Button backbtn;
    
    @FXML
    Button registerbtn;
    
    @FXML
    RadioButton male;
    
    @FXML
    RadioButton female;
    
    @FXML
    RadioButton trans;
    
    @FXML
    RadioButton local;
    
    @FXML
    RadioButton opd;
    
    @FXML
    RadioButton critic;
    
    @FXML
    RadioButton ncritic;
    
    @FXML
    TextField fname;
    
    @FXML
    TextField lname;
    
    @FXML
    TextField phone;
    
    @FXML
    TextField email;
    
    @FXML
    TextField accomp;
    
    @FXML
    TextField ephone;
    
    @FXML
    TextArea addr;
    
    @FXML
    DatePicker dob;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shs = new SmartHealthcareSystem();
    }
    
    @FXML
    public void onClickListener(ActionEvent event) throws Exception{
        if(event.getSource() == registerbtn){
            window = (Stage) backbtn.getScene().getWindow();
            System.out.println("Initiating Registration Process...");
            //updateDB();
        }
        else if(event.getSource() == backbtn){
            window = (Stage) backbtn.getScene().getWindow();
            shs.start(window);
        }
    }
    
    private void updateDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement stmt = con.createStatement();
        //query = "INSERT INTO patientdetails_table VALUES('" + fname.getText() + "', '" + lname.getText() + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "', '" +  + "');"
        //stmt.executeUpdate(query);
        System.out.println("Table Updated");
        /*query = "SELECT * FROM patientdetails_table;";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString("fname"));
        }*/
        con.close();
    }
    
}
