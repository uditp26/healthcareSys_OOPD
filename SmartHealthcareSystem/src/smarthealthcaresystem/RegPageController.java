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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class RegPageController implements Initializable {
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";     
    private String query;
    private boolean flag;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private String guardian, emphone;
    
    private static String fname_static;
    private static String phone_static;
    
    Stage window;
    SmartHealthcareSystem shs;
    RegPage2 regPage2;
    
    @FXML
    Button backbtn;
    
    @FXML
    Button registerbtn;
    
    @FXML
    ToggleGroup gender;
    
    @FXML
    RadioButton male;
    
    @FXML
    RadioButton female;
    
    @FXML
    RadioButton trans;
    
    @FXML
    ToggleGroup location;
    
    @FXML
    RadioButton opd;
    
    @FXML
    RadioButton local;
    
    @FXML
    ToggleGroup condition;
    
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
        regPage2 = new RegPage2();
        flag = true;
    }
    
    @FXML
    public void onClickListener(ActionEvent event) throws Exception{
        if(event.getSource() == registerbtn){
            window = (Stage) registerbtn.getScene().getWindow();
            if(checkDB() && validateInput()){
                System.out.println("Initiating Registration Process...");
                updateDB();
                regPage2.startReg2(window);
            }
            else{
                System.out.println("Registration interrupted - invalid input(s).");
                flag = true;
            }
        }
        else if(event.getSource() == backbtn){
            window = (Stage) backbtn.getScene().getWindow();
            shs.start(window);
        }
    }
    
    public void setfname_static(String fname){
        fname_static = fname;
    }
    
    public String getfname_static(){
        return fname_static;
    }
    
    public static String getPhone_static() {
        return phone_static;
    }

    public static void setPhone_static(String aPhone_static) {
        phone_static = aPhone_static;
    }
    
    private boolean checkDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT * FROM shs_schema.patientdetails_table WHERE phone='" + phone.getText() + "';";
        rs = stmt.executeQuery(query);
        if(!rs.next()){
            con.close();
            return true;
        }
        else{
            System.out.println("User already registered with this phone number!");
            phone.setText("");
            phone.setPromptText("Enter new mobile number!");
            con.close();
            return false;
        }
    }
    
    private boolean validateInput(){
        if(!fname.getText().matches("[a-zA-Z]+")){
            fname.setText("");
            fname.setPromptText("Not a valid name!");
            flag = false;
        }
        
        setfname_static(fname.getText());
        
        if(!lname.getText().matches("[a-zA-Z]+")){
            lname.setText("");
            lname.setPromptText("Not a valid name!");
            flag = false;
        }
        
        if(gender.getSelectedToggle() == null){
            // show alert dialog box
            flag = false;
        }
        if(dob.getValue() == null){
            dob.setPromptText("No date selected!");
            flag = false;
        }
        if(!(phone.getText().length() == 10 && phone.getText().matches("[0-9]+"))){
            phone.setText("");
            phone.setPromptText("Not a valid mobile number!");
            flag = false;
        }
        
        setPhone_static(phone.getText());
        
        if(!email.getText().matches("[a-z][a-z_0-9_.]*[@][a-z_.]+")){
            email.setText("");
            email.setPromptText("Not a valid email!");
            flag = false;
        }
        if(addr.getText().equals("")){
            addr.setPromptText("Can't leave address field empty!");
            flag = false;
        }
        
        if(location.getSelectedToggle() == null){
            // show alert dialog box
            flag = false;
        }
        
        if(condition.getSelectedToggle() == null){
            // show alert dialog box
            flag = false;
        }
        if(!accomp.getText().matches("[a-zA-Z]*")){
            accomp.setText("");
            accomp.setPromptText("Not a valid name!");
            flag = false;
        }
        if(!(ephone.getText().length() == 0 || (ephone.getText().matches("[0-9]+") && ephone.getText().length() == 10))){
            ephone.setText("");
            ephone.setPromptText("Not a valid mobile number!");
            flag = false;
        }
        return flag;
    }
    
    private void updateDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        if(completeQuery()){
            query = "INSERT INTO shs_schema.patientdetails_table VALUES('" + fname.getText() + "', '" + lname.getText() + "', '" + getLToggleValue() + "', '" + addr.getText() + "', '" + phone.getText() + "', '" + getCToggleValue() + "', '" + getGToggleValue() + "', '" + dob.getValue() + "', '" + accomp.getText() + "', '" + ephone.getText() + "', '" + email.getText() + "');";
        }
        else{
            query = "INSERT INTO shs_schema.patientdetails_table (fname, lname, location, address, phone, cndtion, gender, dob, email) VALUES('" + fname.getText() + "', '" + lname.getText() + "', '" + getLToggleValue() + "', '" + addr.getText() + "', '" + phone.getText() + "', '" + getCToggleValue() + "', '" + getGToggleValue() + "', '" + dob.getValue() + "', '" + email.getText() + "');";
        }
        stmt.executeUpdate(query);
        System.out.println("Table Updated");
        /*query = "SELECT * FROM patientdetails_table;";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString("fname"));
        }*/
        con.close();
    }
    
    private boolean completeQuery(){
        if(accomp.getText().equals("")){
            return false;
        }
        if(ephone.getText().equals("")){
            return false;
        }
        return true;
    }
    
    private String getLToggleValue(){
        if(location.getSelectedToggle() == opd){
            return "OPD";
        }
        else{
            return "Local";
        }
    }
    
    private String getCToggleValue(){
        if(condition.getSelectedToggle() == critic){
            return "C";
        }
        else{
            return "NC";
        }
    }
    
    private String getGToggleValue(){
        if(gender.getSelectedToggle() == male){
            return "M";
        }
        else if(gender.getSelectedToggle() == female){
            return "F";
        }
        else{
            return "T";
        }
    }
    
}
