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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author protagonist26
 */
public class SHSController implements Initializable{
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private static final String admin_name = "shsadmin";
    private static final String login_password = "Secure1*";
    private String query;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    
    Stage window;
    RegPage regPage;
    PHomePage php;
    AHomePage ahp;
    //DHomePage dhp;
    
    
    @FXML
    Button signbtn;
    
    @FXML
    Button logbtn;
    
    @FXML
    TextField uid;
    
    @FXML
    PasswordField pwd;
    
    @FXML
    ToggleGroup utype;
    
    @FXML
    RadioButton patient;
    
    @FXML
    RadioButton doctor;
    
    @FXML
    RadioButton admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regPage = new RegPage();
        php = new PHomePage();
        ahp = new AHomePage();
        //dhp = new DHomePage();
    }
    
    @FXML
    public void onClickListener(ActionEvent event) throws Exception{
        if(event.getSource() == signbtn){
            window = (Stage) signbtn.getScene().getWindow();
            regPage.startReg(window);   
        }
        else if(event.getSource() == logbtn){
            window = (Stage) logbtn.getScene().getWindow();
            switch(determineUser()){
                case 1:
                    if(lookUpDB(1)){
                        System.out.println("Patient credentials exists! Login successful.");
                        php.startLog(window);
                    }
                    else{
                        System.out.println("Patient login failed!");
                    }
                    break;
                case 2:
                    if(lookUpDB(2)){
                        System.out.println("Doctor credentials exists! Login successful.");
                        //
                    }
                    else{
                        System.out.println("Doctor login failed!");
                    }
                    //
                    break;
                case 3:
                    if(uid.getText().equals(admin_name) && pwd.getText().equals(login_password)){
                        System.out.println("Admin login successful!");
                        ahp.startAHP(window);
                    }
                    else{
                        System.out.println("Admin login failed!");
                    }
                    break;
                default:
            }
        }
    }
    
    private int determineUser(){
        if(utype.getSelectedToggle() == patient){
            return 1;
        }
        else if(utype.getSelectedToggle() == doctor){
            return 2;
        }
        else if(utype.getSelectedToggle() == admin){
            return 3;
        }
        else{
            return -1;
        }
    }
    
    private boolean lookUpDB(int i) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        if(i==1){
            query = "SELECT * FROM shs_schema.patientlogincred_table WHERE username='" + uid.getText() + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }
        else {
            /*query = "SELECT * FROM shs_schema.doctorlogincred_table WHERE username='" + uid.getText() + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }*/
        }
        return false;
    }
    
}
