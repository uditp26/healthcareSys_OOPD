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
import java.sql.SQLException;
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
public class RegPage2Controller implements Initializable {
    
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.ahomepagecontroller");
    
    RegPage2 regPage2;
    RegPageController controller;
    PHomePageController controller2;
    Stage window;
    PHomePage pHomePage;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private String query;
    
    private static String fname_static;
    private static String uuid_static;
    
    @FXML
    Label fname;
    
    @FXML
    Button submit;
    
    @FXML
    TextField uname;
    
    @FXML
    TextField pword;
    
    @FXML
    TextField cpword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartHealthcareSystem shs = new SmartHealthcareSystem();
        controller = new RegPageController();
        controller2 = new PHomePageController();
        pHomePage = new PHomePage();
        fname.setText("Welcome " + controller.getfname_static());
        logger.addHandler(shs.getFHandler());
        logger.setLevel(Level.ALL);
    }
    
    public void onClickListener(ActionEvent event){
        try{
            if(event.getSource() == submit){
                window = (Stage) submit.getScene().getWindow();
                if(checkDB() && validateInput()){
                    updateDB();
                    setFname_static(controller.getfname_static());
                    controller2.setFname_static(fname_static);
                    pHomePage.startLog(window);
                }
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"" , e);
            query = "DELETE FROM patientdetails_table WHERE phone='" + controller.getPhone_static() + "';";
            try {
                rs = stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(RegPage2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private boolean validateInput(){
        if(!uname.getText().matches("[a-z][a-z_0-9]*")){
            uname.setText("");
            uname.setPromptText("Not a valid username!");
            return false;
        }
        if(!pword.getText().equals(cpword.getText())){
            cpword.setText("");
            cpword.setPromptText("Password does not match!");
            System.out.println("Password does not match!");
            return false;
        }
        setUuid_static(uname.getText());
        return true;
    }
    
    private boolean checkDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT * FROM shs_schema.patientlogincred_table WHERE username='" + uname.getText() + "';";
        rs = stmt.executeQuery(query);
        if(!rs.next()){
            con.close();
            return true;
        }
        else{
            System.out.println("Username already exists!");
            uname.setText("");
            uname.setPromptText("Username already exists!");
            con.close();
            return false;
        }
    }
    
    private void updateDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "INSERT INTO shs_schema.patientlogincred_table VALUES('" + uname.getText() + "', '" + pword.getText() + "', '" + controller.getPhone_static() + "', NULL, NULL, NULL);";
        stmt.executeUpdate(query);
        System.out.println("patientlogincred_table Updated");
        con.close();
    }

    public static String getFname_static() {
        return fname_static;
    }
    
    public static void setFname_static(String aFname_static) {
        fname_static = aFname_static;
    }
    
    public boolean checkURecords(String fname, String lname) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT * FROM patientdetails_table WHERE fname = '" + fname + "' AND lname = '" + lname + "';";
        rs = stmt.executeQuery(query);
        return rs.next();
    }

    /**
     * @return the uuid_static
     */
    public static String getUuid_static() {
        return uuid_static;
    }

    /**
     * @param aUuid_static the uuid_static to set
     */
    public static void setUuid_static(String aUuid_static) {
        uuid_static = aUuid_static;
    }
}
