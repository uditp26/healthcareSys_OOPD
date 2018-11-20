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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class DRegPageController implements Initializable {
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.ahomepagecontroller");
    
    RegPage2 regPage2;
    RegPageController controller;
    DHomePageController controller2;
    SHSController controller3;
    Stage window;
    PHomePage pHomePage;
    DHomePage dHomePage;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private String query;
    
    private static String dname_static;
    
    @FXML
    Label dname;
    
    @FXML
    Button submit;
    
    @FXML
    TextField pword;
    
    @FXML
    TextField cpword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartHealthcareSystem shs = new SmartHealthcareSystem();
        controller = new RegPageController();
        controller2 = new DHomePageController();
        controller3 = new SHSController();
        dHomePage = new DHomePage();
        dname.setText("Welcome " + controller3.getDname_static());
        logger.addHandler(shs.getFHandler());
        logger.setLevel(Level.ALL);
    }
    
    public void onClickListener(ActionEvent event){
        try{
            if(event.getSource() == submit){
                window = (Stage) submit.getScene().getWindow();
                if(validateInput()){
                    updateDB();
                    setDname_static(controller3.getDname_static());
                    controller2.setDname_static(dname_static);
                    dHomePage.startReg(window);
                }
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"" , e);            
        }
        
    }
    
    private boolean validateInput(){
        if(!pword.getText().equals(cpword.getText())){
            cpword.setText("");
            cpword.setPromptText("Password does not match!");
            System.out.println("Password does not match!");
            return false;
        }
        return true;
    }
    
    private void updateDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "UPDATE shs_schema.doctordetails_table SET password = '" + pword.getText() + "' WHERE did = '" + controller3.getDid_static() + "';";
        stmt.executeUpdate(query);
        System.out.println("doctordetails_table Updated");
        con.close();
    }

    public static String getDname_static() {
        return dname_static;
    }
    
    public static void setDname_static(String aFname_static) {
        dname_static = aFname_static;
    }

    public String checkDBStatus(String did) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT fname, lname FROM doctordetails_table WHERE did = '" + did + "';";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            return rs.getString("fname") + " " + rs.getString("lname");
        }
        return null;
    }
    
}
