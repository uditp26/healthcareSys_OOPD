/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.io.IOException;
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
import java.util.logging.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

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
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.shscontroller");
    private String query;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private Boolean flag, flag2;
    private Alert alert;
    
    private static String uname_static;
    private static String dname_static;
    private static String did_static;
    
    Stage window;
    RegPage regPage;
    PHomePage php;
    AHomePage ahp;
    DRegPage dregPage;
    DHomePage dhp;
    DHomePageController dhpcontroller;
    
    
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
        flag = false;
        flag2 = false;
        regPage = new RegPage();
        php = new PHomePage();
        ahp = new AHomePage();
        dregPage = new DRegPage();
        dhp = new DHomePage();
        dhpcontroller = new DHomePageController();
        try {
            logger.addHandler(SmartHealthcareSystem.getFHandler());
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"" , e);
        }
    }
    
    @FXML
    public void onClickListener(ActionEvent event){
        try{
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
                            setUname_static(uid.getText());
                            php.startLog(window);
                        }
                        else{
                            System.out.println("Patient login failed!");
                        }
                        break;
                    case 2:
                        if(lookUpDB(2)){
                            if(flag){
                                setDid_static(uid.getText());
                                dhpcontroller.setDname_static(uid.getText());
                                dregPage.startReg(window);
                            }
                            else{
                                System.out.println("Doctor credentials exists! Login successful.");
                                dhpcontroller.setDname_static(uid.getText());
                                dhp.startReg(window);
                            }
                        }
                        else{
                            if(flag2 == true){
                                dhpcontroller.setDname_static(uid.getText());
                                dhp.startReg(window);
                            }
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
                        alert = new Alert(AlertType.INFORMATION, "Unknown User!", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                }
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, "", e);
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
    
    public boolean lookUpDB(int i) throws Exception{
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
            query = "SELECT fname, password FROM shs_schema.doctordetails_table WHERE did='" + uid.getText() + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                setDname_static(rs.getString("fname"));
                if(rs.getString("password") == null){
                    flag = true;
                    return true;
                }
                flag2 = true;
            }
        }
        return false;
    }

    public static String getUname_static() {
        return uname_static;
    }

    public static void setUname_static(String aUname_static) {
        uname_static = aUname_static;
    }

    /**
     * @return the did_static
     */
    public static String getDid_static() {
        return did_static;
    }

    /**
     * @param aDid_static the did_static to set
     */
    public static void setDid_static(String aDid_static) {
        did_static = aDid_static;
    }

    /**
     * @return the dname_static
     */
    public static String getDname_static() {
        return dname_static;
    }

    /**
     * @param aDname_static the dname_static to set
     */
    public static void setDname_static(String aDname_static) {
        dname_static = aDname_static;
    }
    
    public String fetchName(String phone) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT fname, lname FROM patientdetails_table WHERE phone = '" + phone + "';";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            return rs.getString("fname") + " " + rs.getString("lname"); 
        }
        return null;
    }
    
    public int fetchPCount() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT COUNT(*) AS pc FROM patientdetails_table;";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            return rs.getInt("pc"); 
        }
        return -1;
    }
    
}
