/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class PHomePageController implements Initializable {
    
    SmartHealthcareSystem shs;
    RegPage2Controller regPage2Controller;
    Stage window;
    SHSController controller;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private String query;
    
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.phomepagecontroller");
    private Button backButton;
    private Button request_appointment;
    private Alert alert;

    
    private static String fname_static;
    private static String uuid_static;
    private String did_reapp; 
    
    @FXML
    Label welcome;
    
    @FXML
    GridPane gridpane;
    
    @FXML
    Label name;
    
    @FXML
    BorderPane bPane;
    
    @FXML
    Button edit_profile;
    
    @FXML
    Button search_doctor;
    
    @FXML
    Button shsapp;
    
    @FXML
    Button view_profile;
    
    @FXML
    Button logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        shs = new SmartHealthcareSystem();
        regPage2Controller = new RegPage2Controller();
        controller = new SHSController();
        logger.addHandler(shs.getFHandler());
        logger.setLevel(Level.ALL);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(50);
        gridpane.getColumnConstraints().add(cc);
        //view_doctor.setVisible(false);
        if(controller.getUname_static() == null){
            uuid_static = regPage2Controller.getUuid_static();
        }
        else{
            uuid_static = controller.getUname_static();
        }
        if(fname_static == null){
            try {
                welcome.setText("Welcome " + getNamefromDB(controller.getUname_static()));
            } catch (Exception ex) {
                Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            welcome.setText("Welcome " + fname_static);
        }
    }    
    
    public void onClickListener(ActionEvent event){
        try{
            if(event.getSource() == edit_profile){
                //view_doctor.setVisible(false);
                name.setText("EDIT PROFILE");
                gridpane.getChildren().clear();
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, username, password);
                stmt = con.createStatement();
                String user_name =  controller.getUname_static();
                query = "SELECT patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.address, patientdetails_table.email, patientlogincred_table.password FROM patientdetails_table,patientlogincred_table WHERE patientdetails_table.phone=patientlogincred_table.phone AND patientlogincred_table.username='" + uuid_static + "';";
                rs = stmt.executeQuery(query);
                String f_name="",l_name="",add_ress="",e_mail="",pass_word="";
                while(rs.next()){
                    f_name = rs.getString("fname");
                    l_name = rs.getString("lname");
                    add_ress = rs.getString("address");
                    e_mail = rs.getString("email");
                    pass_word = rs.getString("password");
                }
                
                Label fname = new Label("First Name");
                fname.setFont(new Font("Arial",18));
                gridpane.add(fname,0,0);
                TextField tf1 = new TextField();
                tf1.setText(f_name);
                gridpane.add(tf1,1,0);
                Label lname = new Label("Last Name");
                lname.setFont(new Font("Arial",18));
                gridpane.add(lname,0,1);
                TextField tf2 = new TextField();
                tf2.setText(l_name);
                gridpane.add(tf2,1,1);
                Label address = new Label("Address");
                address.setFont(new Font("Arial",18));
                gridpane.add(address,0,2);
                TextField tf3 = new TextField();
                tf3.setText(add_ress);
                gridpane.add(tf3,1,2);
                Label email = new Label("E-Mail");
                email.setFont(new Font("Arial",18));
                gridpane.add(email,0,3);
                TextField tf5 = new TextField();
                tf5.setText(e_mail);
                gridpane.add(tf5,1,3);
                Label passw_ord = new Label("Password");
                passw_ord.setFont(new Font("Arial",18));
                gridpane.add(passw_ord,0,4);
                TextField tf6 = new TextField();
                tf6.setText(pass_word);
                //TextField tf6 = new TextField();
                gridpane.add(tf6,1,4);
                Button submit = new Button("Submit");
                gridpane.add(submit,1,5);
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        boolean flag = true;
                        if(!tf1.getText().matches("[a-zA-Z]+")){
                            tf1.setText("");
                            tf1.setPromptText("Not a valid name!");
                            flag = false;
                        }
                        if(!tf2.getText().matches("[a-zA-Z]+")){
                            tf2.setText("");
                            tf2.setPromptText("Not a valid name!");
                            flag = false;
                        }
                        if(!tf5.getText().matches("[a-z][a-z_0-9_.]*[@][a-z_.]+")){
                            tf5.setText("");
                            tf5.setPromptText("Not a valid email!");
                            flag = false;
                        }
                        if("".equals(tf3.getText())){
                            tf3.setText("");
                            tf3.setPromptText("Empty address not allowed");
                            flag = false;
                        }
                        if("".equals(tf6.getText())){
                            tf6.setText("");
                            tf6.setPromptText("Empty password not allowed");
                            flag = false;
                        }
                        if(flag){
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection(URL, username, password);
                                stmt = con.createStatement();
                                query = "SELECT phone FROM patientlogincred_table WHERE username = '" + uuid_static + "';";
                                rs = stmt.executeQuery(query);
                                rs.next();
                                query = "UPDATE patientdetails_table SET fname = '" + tf1.getText() + "', lname = '" + tf2.getText() + "', address = '" + tf3.getText() + "', email = '" + tf5.getText() + "' WHERE phone = '" + rs.getString("phone") + "';";
                                stmt.executeUpdate(query);
                                System.out.println("patientdetails_table updated!");
                                query = "UPDATE patientlogincred_table SET password = '" + tf6.getText() + "' WHERE username = '" + uuid_static + "';";
                                stmt.executeUpdate(query);
                                System.out.println("patientlogincred_table updated!");
                            } 
                            catch (ClassNotFoundException | SQLException ex) {
                                Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
            }
            else if(event.getSource() == logout){
                window = (Stage) logout.getScene().getWindow();
                shs.start(window);
            }
            else if(event.getSource() == search_doctor){
                //view_doctor.setVisible(false);
                name.setText("SELECT FILTER");
                gridpane.getChildren().clear();
                ToggleGroup group = new ToggleGroup();
                RadioButton rb1 = new RadioButton("Doctor ID");
                rb1.setUserData("Doctor ID");
                rb1.setToggleGroup(group);
                RadioButton rb2 = new RadioButton("Doctor Name");
                rb2.setUserData("Doctor Name");
                rb2.setToggleGroup(group);
                RadioButton rb3 = new RadioButton("Doctor Address");
                rb3.setUserData("Doctor Address");
                rb3.setToggleGroup(group);
                RadioButton rb4 = new RadioButton("Doctor Specialization");
                rb4.setUserData("Doctor Specialization");
                rb4.setToggleGroup(group);
                Button submit = new Button("Submit");
                gridpane.add(rb1,0,0);
                gridpane.add(rb2,0,1);
                gridpane.add(rb3,0,2);
                gridpane.add(rb4,0,3);
                gridpane.add(submit,1,4);
                TextField id1 = new TextField();
                id1.setPromptText("Enter Doctor ID");
                TextField id2 = new TextField();
                id2.setPromptText("Enter Doctor Name");
                TextField id3 = new TextField();
                id3.setPromptText("Enter Doctor Address");
                //MenuButton id4 = new MenuButton("Enter Doctor Specialization");
                //id4.getItems().addAll(new MenuItem("Orthopedic"), new MenuItem("General Physician"), new MenuItem("Cardiologist"), new MenuItem("Endocrinologists"));
                TextField id4 = new TextField();
                id4.setPromptText("Enter Doctor Specialization");
                //TextField id4 = new TextField();
                //id4.setPromptText("Enter Doctor Specialization");
                group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                        if(group.getSelectedToggle().getUserData().toString().equals("Doctor ID")){
                            //TextField id = new TextField();
                            //id.setPromptText("Enter Doctor ID");
                            gridpane.getChildren().remove(id2);
                            gridpane.getChildren().remove(id3);
                            gridpane.getChildren().remove(id4);
                            gridpane.add(id1,1,0);
                            submit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        String id = id1.getText();
                                        //gridpane.getChildren().clear();
                                        Class.forName("com.mysql.jdbc.Driver");
                                        con = DriverManager.getConnection(URL, username, password);
                                        stmt = con.createStatement();
                                        query = "SELECT fname,lname,did FROM doctordetails_table WHERE did='" + id + "'";
                                        rs = stmt.executeQuery(query);
                                        try {
                                            if(rs.wasNull()){
                                                //id1.setPromptText("Doctor ID not found!!");
                                                Alert alert = new Alert(AlertType.INFORMATION, "Doctor ID not found!!!", ButtonType.OK);
                                                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                                alert.show();
                                            }
                                            else {
                                                gridpane.getChildren().clear();
                                                name.setText("Search results");
                                                String first_name="",last_name="",did="";
                                                ArrayList<Label> dids = new ArrayList();
                                                int count = 0;
                                                while(rs.next()){
                                                    first_name = rs.getString("fname");
                                                    last_name = rs.getString("lname");
                                                    did = rs.getString("did");
                                                    dids.add(new Label(did));
                                                    String full_name = first_name + " " + last_name;
                                                    gridpane.add(new Label(full_name),1,count);
                                                    gridpane.add(dids.get(dids.size()-1),0,count);
                                                    count += 1;
                                                    dids.get(dids.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                        @Override
                                                        public void handle(MouseEvent event) {
                                                            //view_doctor.setVisible(true);
                                                            for(int i=0;i<dids.size();i++){
                                                                if(event.getSource() == dids.get(i)){
                                                                    try {
                                                                        did_reapp = dids.get(i).getText();
                                                                        gridpane.getChildren().clear();
                                                                        Class.forName("com.mysql.jdbc.Driver");
                                                                        con = DriverManager.getConnection(URL, username, password);
                                                                        stmt = con.createStatement();
                                                                        query = "SELECT fname,lname,address,phone,email,department,dutystart,dutyend FROM doctordetails_table WHERE did='" + dids.get(i).getText() + "'";
                                                                        rs = stmt.executeQuery(query);
                                                                        while(rs.next()){
                                                                            name.setText("DOCTOR PROFILE");
                                                                            
                                                                            Label df_name = new Label("First Name");
                                                                            df_name.setFont(new Font("Arial",18));
                                                                            gridpane.add(df_name,0,0);
                                                                            Label df_name_value = new Label(rs.getString("fname"));
                                                                            df_name_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(df_name_value,1,0);
                                                                            
                                                                            Label dl_name = new Label("Last Name");
                                                                            dl_name.setFont(new Font("Arial",18));
                                                                            gridpane.add(dl_name,0,1);
                                                                            Label dl_name_value = new Label(rs.getString("lname"));
                                                                            dl_name_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(dl_name_value,1,1);
                                                                            
                                                                            Label daddress = new Label("Address");
                                                                            daddress.setFont(new Font("Arial",18));
                                                                            gridpane.add(daddress,0,2);
                                                                            Label daddress_value = new Label(rs.getString("address"));
                                                                            daddress_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(daddress_value,1,2);
                                                                            
                                                                            Label dphone = new Label("Phone");
                                                                            dphone.setFont(new Font("Arial",18));
                                                                            gridpane.add(dphone,0,3);
                                                                            Label dphone_value = new Label(String.valueOf(rs.getLong("phone")));
                                                                            dphone_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(dphone_value,1,3);
                                                                            
                                                                            Label demail = new Label("Email");
                                                                            demail.setFont(new Font("Arial",18));
                                                                            gridpane.add(demail,0,4);
                                                                            Label demail_value = new Label(rs.getString("email"));
                                                                            demail_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(demail_value,1,4);
                                                                            
                                                                            Time ds = rs.getTime("dutystart");
                                                                            Time de = rs.getTime("dutyend");
                                                                            String visiting_time = String.valueOf(ds) + " - " + String.valueOf(de);
                                                                            Label dvisitingtime = new Label("Visiting time");
                                                                            dvisitingtime.setFont(new Font("Arial",18));
                                                                            gridpane.add(dvisitingtime,0,5);
                                                                            Label dvisitingtime_value = new Label(visiting_time);
                                                                            dvisitingtime_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(dvisitingtime_value,1,5);
                                                                            
                                                                            Label dcategory = new Label("Department");
                                                                            dcategory.setFont(new Font("Arial",18));
                                                                            gridpane.add(dcategory,0,6);
                                                                            Label dcategory_value = new Label(rs.getString("department"));
                                                                            dcategory_value.setFont(new Font("Lucida sans",16));
                                                                            gridpane.add(dcategory_value,1,6);
                                                                        }
                                                                        backButton = new Button("Back");
                                                                        gridpane.add(backButton, 0, 7);
                                                                        backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                            @Override
                                                                            public void handle(MouseEvent event) {
                                                                                gridpane.getChildren().clear();
                                                                                bPane.getLeft().setDisable(false);
                                                                            }
                                                                        });
                                                                        request_appointment = new Button("Request Appointment");
                                                                        gridpane.add(request_appointment, 1, 7);
                                                                        request_appointment.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                            @Override
                                                                            public void handle(MouseEvent event) {
                                                                                try {
                                                                                    setAppointment();
                                                                                    gridpane.getChildren().clear();
                                                                                } catch (Exception ex) {
                                                                                    Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                                }
                                                                            }

                                                                        });
                                                                    }
                                                                    catch (ClassNotFoundException | SQLException ex) {
                                                                        Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });
                                                    dids.get(dids.size()-1).setOnMouseEntered(new EventHandler<MouseEvent>() {
                                                        @Override
                                                        public void handle(MouseEvent event) {
                                                            dids.get(dids.size()-1).setStyle("-fx-background-color:#dae7f3;");
                                                        }
                                                    });
                                                    dids.get(dids.size()-1).setOnMouseExited(new EventHandler<MouseEvent>() {
                                                        @Override
                                                        public void handle(MouseEvent event) {
                                                            dids.get(dids.size()-1).setStyle("-fx-background-color:transparent;");
                                                        }
                                                    });
                                                }
                                            }
                                        } catch (SQLException ex) {
                                            Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                       
                                    } 
                                    catch (ClassNotFoundException | SQLException ex) {
                                        Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }
                        else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Name")){
                            //TextField id = new TextField();
                            //id.setPromptText("Enter Doctor Name");
                            gridpane.getChildren().remove(id1);
                            gridpane.getChildren().remove(id3);
                            gridpane.getChildren().remove(id4);
                            gridpane.add(id2,1,1);
                            submit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        con = DriverManager.getConnection(URL, username, password);
                                        stmt = con.createStatement();
                                        String name1 = id2.getText();
                                        String[] search_name = name1.split(" ");
                                        if (search_name.length == 1){
                                            query = "SELECT did,fname,lname FROM doctordetails_table WHERE fname='" + search_name[0] + "' OR lname='" + search_name[0] + "'";
                                        }
                                        else{
                                            query = "SELECT did,fname,lname FROM doctordetails_table WHERE fname='" + search_name[0] + "' AND lname='" + search_name[1] + "'";
                                        }
                                        rs = stmt.executeQuery(query);
                                        if(rs.wasNull()){
                                            //id2.setPromptText("Doctor Name not found!!!");
                                            Alert alert = new Alert(AlertType.INFORMATION, "Doctor name not found!!", ButtonType.OK);
                                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                            alert.show();
                                        }
                                        else {
                                            gridpane.getChildren().clear();
                                            name.setText("Search Results");
                                            String first_name="",last_name="",did="";
                                            ArrayList<Label> dids = new ArrayList();
                                            int count = 0;
                                            while(rs.next()){
                                                first_name = rs.getString("fname");
                                                last_name = rs.getString("lname");
                                                did = rs.getString("did");
                                                dids.add(new Label(did));
                                                String full_name = first_name + " " + last_name;
                                                gridpane.add(new Label(full_name),1,count);
                                                gridpane.add(dids.get(dids.size()-1),0,count);
                                                count += 1;
                                                dids.get(dids.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        //view_doctor.setVisible(true);
                                                        for(int i=0;i<dids.size();i++){
                                                            if(event.getSource() == dids.get(i)){
                                                                try {
                                                                    did_reapp = dids.get(i).getText();
                                                                    gridpane.getChildren().clear();
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                    con = DriverManager.getConnection(URL, username, password);
                                                                    stmt = con.createStatement();
                                                                    query = "SELECT fname,lname,address,phone,email,department,dutystart,dutyend FROM doctordetails_table WHERE did='" + dids.get(i).getText() + "'";
                                                                    rs = stmt.executeQuery(query);
                                                                    while(rs.next()){
                                                                        name.setText("DOCTOR PROFILE");
                                                                        Label df_name = new Label("First Name");
                                                                        df_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(df_name,0,0);
                                                                        Label df_name_value = new Label(rs.getString("fname"));
                                                                        df_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(df_name_value,1,0);
                                                                        
                                                                        Label dl_name = new Label("Last Name");
                                                                        dl_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(dl_name,0,1);
                                                                        Label dl_name_value = new Label(rs.getString("lname"));
                                                                        dl_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dl_name_value,1,1);
                                                                        
                                                                        Label daddress = new Label("Address");
                                                                        daddress.setFont(new Font("Arial",18));
                                                                        gridpane.add(daddress,0,2);
                                                                        Label daddress_value = new Label(rs.getString("address"));
                                                                        daddress_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(daddress_value,1,2);
                                                                        
                                                                        Label dphone = new Label("Phone");
                                                                        dphone.setFont(new Font("Arial",18));
                                                                        gridpane.add(dphone,0,3);
                                                                        Label dphone_value = new Label(String.valueOf(rs.getLong("phone")));
                                                                        dphone_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dphone_value,1,3);
                                                                        
                                                                        Label demail = new Label("Email");
                                                                        demail.setFont(new Font("Arial",18));
                                                                        gridpane.add(demail,0,4);
                                                                        Label demail_value = new Label(rs.getString("email"));
                                                                        demail_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(demail_value,1,4);
                                                                        
                                                                        Time ds = rs.getTime("dutystart");
                                                                        Time de = rs.getTime("dutyend");
                                                                        String visiting_time = String.valueOf(ds) + " - " + String.valueOf(de);
                                                                        Label dvisitingtime = new Label("Visiting time");
                                                                        dvisitingtime.setFont(new Font("Arial",18));
                                                                        gridpane.add(dvisitingtime,0,5);
                                                                        Label dvisitingtime_value = new Label(visiting_time);
                                                                        dvisitingtime_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dvisitingtime_value,1,5);
                                                                        
                                                                        Label dcategory = new Label("Department");
                                                                        dcategory.setFont(new Font("Arial",18));
                                                                        gridpane.add(dcategory,0,6);
                                                                        Label dcategory_value = new Label(rs.getString("department"));
                                                                        dcategory_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dcategory_value,1,6);
                                                                    }
                                                                    backButton = new Button("Back");
                                                                    gridpane.add(backButton, 0, 7);
                                                                    backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            gridpane.getChildren().clear();
                                                                            bPane.getLeft().setDisable(false);
                                                                        }
                                                                    });
                                                                    request_appointment = new Button("Request Appointment");
                                                                    gridpane.add(request_appointment, 1, 7);
                                                                    request_appointment.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            try {
                                                                                setAppointment();
                                                                                gridpane.getChildren().clear();
                                                                            } catch (Exception ex) {
                                                                                Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                        }
                                                                        
                                                                    });
                                                                } 
                                                                catch (ClassNotFoundException | SQLException ex) {
                                                                    Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseEntered(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:#dae7f3;");
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseExited(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:transparent;");
                                                    }
                                                });
                                            }
                                        }
                                       
                                    } catch (ClassNotFoundException | SQLException ex) {
                                        Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }
                        else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Address")){
                            //TextField id = new TextField();
                            //id.setPromptText("Enter Doctor Address");
                            gridpane.getChildren().remove(id1);
                            gridpane.getChildren().remove(id2);
                            gridpane.getChildren().remove(id4);
                            gridpane.add(id3,1,2);
                            submit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        String address = id3.getText();
                                        gridpane.getChildren().clear();
                                        Class.forName("com.mysql.jdbc.Driver");
                                        con = DriverManager.getConnection(URL, username, password);
                                        stmt = con.createStatement();
                                        query = "SELECT did,fname,lname FROM doctordetails_table WHERE address='" + address + "'";
                                        rs = stmt.executeQuery(query);
                                        if(rs.wasNull()){
                                            Alert alert = new Alert(AlertType.INFORMATION, "Doctor address not found", ButtonType.OK);
                                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                            alert.show();
                                            //id3.setPromptText("Doctor address not found!!!!");
                                        }
                                        else {
                                           gridpane.getChildren().clear();
                                           name.setText("Search Results");
                                           String first_name="",last_name="",did="";
                                           ArrayList<Label> dids = new ArrayList();
                                           int count = 0;
                                           while(rs.next()){
                                                first_name = rs.getString("fname");
                                                last_name = rs.getString("lname");
                                                did = rs.getString("did");
                                                dids.add(new Label(did));
                                                String full_name = first_name + " " + last_name;
                                                gridpane.add(new Label(full_name),1,count);
                                                gridpane.add(dids.get(dids.size()-1),0,count);
                                                count += 1;
                                                dids.get(dids.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        //view_doctor.setVisible(true);
                                                        for(int i=0;i<dids.size();i++){
                                                            if(event.getSource() == dids.get(i)){
                                                                try {
                                                                    did_reapp = dids.get(i).getText();
                                                                    gridpane.getChildren().clear();
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                    con = DriverManager.getConnection(URL, username, password);
                                                                    stmt = con.createStatement();
                                                                    query = "SELECT fname,lname,address,phone,email,department,dutystart,dutyend FROM doctordetails_table WHERE did='" + dids.get(i).getText() + "'";
                                                                    rs = stmt.executeQuery(query);
                                                                    while(rs.next()){
                                                                        name.setText("DOCTOR PROFILE");
                                                                        
                                                                        Label df_name = new Label("First Name");
                                                                        df_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(df_name,0,0);
                                                                        Label df_name_value = new Label(rs.getString("fname"));
                                                                        df_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(df_name_value,1,0);
                                                                        
                                                                        Label dl_name = new Label("Last Name");
                                                                        dl_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(dl_name,0,1);
                                                                        Label dl_name_value = new Label(rs.getString("lname"));
                                                                        dl_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dl_name_value,1,1);
                                                                        
                                                                        Label daddress = new Label("Address");
                                                                        daddress.setFont(new Font("Arial",18));
                                                                        gridpane.add(daddress,0,2);
                                                                        Label daddress_value = new Label(rs.getString("address"));
                                                                        daddress_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(daddress_value,1,2);
                                                                        
                                                                        Label dphone = new Label("Phone");
                                                                        dphone.setFont(new Font("Arial",18));
                                                                        gridpane.add(dphone,0,3);
                                                                        Label dphone_value = new Label(String.valueOf(rs.getLong("phone")));
                                                                        dphone_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dphone_value,1,3);
                                                                        
                                                                        Label demail = new Label("Email");
                                                                        demail.setFont(new Font("Arial",18));
                                                                        gridpane.add(demail,0,4);
                                                                        Label demail_value = new Label(rs.getString("email"));
                                                                        demail_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(demail_value,1,4);
                                                                        
                                                                        Time ds = rs.getTime("dutystart");
                                                                        Time de = rs.getTime("dutyend");
                                                                        String visiting_time = String.valueOf(ds) + " - " + String.valueOf(de);
                                                                        Label dvisitingtime = new Label("Visiting time");
                                                                        dvisitingtime.setFont(new Font("Arial",18));
                                                                        gridpane.add(dvisitingtime,0,5);
                                                                        Label dvisitingtime_value = new Label(visiting_time);
                                                                        dvisitingtime_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dvisitingtime_value,1,5);
                                                                        
                                                                        Label dcategory = new Label("Department");
                                                                        dcategory.setFont(new Font("Arial",18));
                                                                        gridpane.add(dcategory,0,6);
                                                                        Label dcategory_value = new Label(rs.getString("department"));
                                                                        dcategory_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dcategory_value,1,6);
                                                                    }
                                                                    backButton = new Button("Back");
                                                                    gridpane.add(backButton, 0, 7);
                                                                    backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            gridpane.getChildren().clear();
                                                                            bPane.getLeft().setDisable(false);
                                                                        }
                                                                    });
                                                                    request_appointment = new Button("Request Appointment");
                                                                    gridpane.add(request_appointment, 1, 7);
                                                                    request_appointment.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            try {
                                                                                setAppointment();
                                                                                gridpane.getChildren().clear();
                                                                            } catch (Exception ex) {
                                                                                Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                        }
                                                                        
                                                                    });
                                                                } 
                                                                catch (ClassNotFoundException | SQLException ex) {
                                                                    Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseEntered(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:#dae7f3;");
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseExited(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:transparent;");
                                                    }
                                                });
                                            } 
                                        }
                                    } 
                                    catch (ClassNotFoundException | SQLException ex) {
                                        Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }
                        else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Specialization")){
                            //TextField id = new TextField();
                            //id.setPromptText("Enter Doctor Specialization");
                            gridpane.getChildren().remove(id1);
                            gridpane.getChildren().remove(id2);
                            gridpane.getChildren().remove(id3);
                            gridpane.add(id4,1,3);
                            submit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        String cat = id4.getText();
                                        gridpane.getChildren().clear();
                                        Class.forName("com.mysql.jdbc.Driver");
                                        con = DriverManager.getConnection(URL, username, password);
                                        stmt = con.createStatement();
                                        query = "SELECT did,fname,lname FROM doctordetails_table WHERE department='" + cat + "'";
                                        rs = stmt.executeQuery(query);
                                        if(rs.wasNull()){
                                            Alert alert = new Alert(AlertType.INFORMATION, "Doctor Specialization not found!", ButtonType.OK);
                                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                            alert.show();
                                        }
                                        else {
                                            
                                            gridpane.getChildren().clear();
                                            name.setText("Search Results");
                                            String first_name="",last_name="",did="";
                                            ArrayList<Label> dids = new ArrayList();
                                            int count = 0;
                                            while(rs.next()){
                                                first_name = rs.getString("fname");
                                                last_name = rs.getString("lname");
                                                did = rs.getString("did");
                                                dids.add(new Label(did));
                                                String full_name = first_name + " " + last_name;
                                                gridpane.add(new Label(full_name),1,count);
                                                gridpane.add(dids.get(dids.size()-1),0,count);
                                                count += 1;
                                                dids.get(dids.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        //view_doctor.setVisible(true);
                                                        for(int i=0;i<dids.size();i++){
                                                            if(event.getSource() == dids.get(i)){
                                                                try {
                                                                    did_reapp = dids.get(i).getText();
                                                                    gridpane.getChildren().clear();
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                    con = DriverManager.getConnection(URL, username, password);
                                                                    stmt = con.createStatement();
                                                                    query = "SELECT fname,lname,address,phone,email,department,dutystart,dutyend FROM doctordetails_table WHERE did='" + dids.get(i).getText() + "'";
                                                                    rs = stmt.executeQuery(query);
                                                                    while(rs.next()){
                                                                        name.setText("DOCTOR PROFILE");
                                                                        
                                                                        Label df_name = new Label("First Name");
                                                                        df_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(df_name,0,0);
                                                                        Label df_name_value = new Label(rs.getString("fname"));
                                                                        df_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(df_name_value,1,0);
                                                                        
                                                                        Label dl_name = new Label("Last Name");
                                                                        dl_name.setFont(new Font("Arial",18));
                                                                        gridpane.add(dl_name,0,1);
                                                                        Label dl_name_value = new Label(rs.getString("lname"));
                                                                        dl_name_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dl_name_value,1,1);
                                                                        
                                                                        Label daddress = new Label("Address");
                                                                        daddress.setFont(new Font("Arial",18));
                                                                        gridpane.add(daddress,0,2);
                                                                        Label daddress_value = new Label(rs.getString("address"));
                                                                        daddress_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(daddress_value,1,2);
                                                                        
                                                                        Label dphone = new Label("Phone");
                                                                        dphone.setFont(new Font("Arial",18));
                                                                        gridpane.add(dphone,0,3);
                                                                        Label dphone_value = new Label(String.valueOf(rs.getLong("phone")));
                                                                        dphone_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dphone_value,1,3);
                                                                        
                                                                        Label demail = new Label("Email");
                                                                        demail.setFont(new Font("Arial",18));
                                                                        gridpane.add(demail,0,4);
                                                                        Label demail_value = new Label(rs.getString("email"));
                                                                        demail_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(demail_value,1,4);
                                                                        
                                                                        Time ds = rs.getTime("dutystart");
                                                                        Time de = rs.getTime("dutyend");
                                                                        String visiting_time = String.valueOf(ds) + " - " + String.valueOf(de);
                                                                        Label dvisitingtime = new Label("Visiting time");
                                                                        dvisitingtime.setFont(new Font("Arial",18));
                                                                        gridpane.add(dvisitingtime,0,5);
                                                                        Label dvisitingtime_value = new Label(visiting_time);
                                                                        dvisitingtime_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dvisitingtime_value,1,5);
                                                                        
                                                                        Label dcategory = new Label("Department");
                                                                        dcategory.setFont(new Font("Arial",18));
                                                                        gridpane.add(dcategory,0,6);
                                                                        Label dcategory_value = new Label(rs.getString("department"));
                                                                        dcategory_value.setFont(new Font("Lucida sans",16));
                                                                        gridpane.add(dcategory_value,1,6);
                                                                    }
                                                                    backButton = new Button("Back");
                                                                    gridpane.add(backButton, 0, 7);
                                                                    backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            gridpane.getChildren().clear();
                                                                            bPane.getLeft().setDisable(false);
                                                                        }
                                                                    });
                                                                    request_appointment = new Button("Request Appointment");
                                                                    gridpane.add(request_appointment, 1, 7);
                                                                    request_appointment.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                                                        @Override
                                                                        public void handle(MouseEvent event) {
                                                                            try {
                                                                                setAppointment();
                                                                                gridpane.getChildren().clear();
                                                                            } catch (Exception ex) {
                                                                                Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                        }
                                                                        
                                                                    });
                                                                } 
                                                                catch (ClassNotFoundException | SQLException ex) {
                                                                    Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseEntered(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:#dae7f3;");
                                                    }
                                                });
                                                dids.get(dids.size()-1).setOnMouseExited(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        dids.get(dids.size()-1).setStyle("-fx-background-color:transparent;");
                                                    }
                                                });
                                            }
                                        }
                                    } 
                                    catch (ClassNotFoundException | SQLException ex) {
                                        Logger.getLogger(PHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }
                    }
                });
                
            }
            
            else if(event.getSource() == shsapp){
                gridpane.getChildren().clear();
                String did, dname, pid;
                alert = new Alert(AlertType.CONFIRMATION, "Appoint Doctor Through SHS?", ButtonType.NO, ButtonType.YES);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                Optional<ButtonType> choice = alert.showAndWait();
                if(choice.get() == ButtonType.YES){
                    if(!checkPAppointment()){
                        query = "SELECT did, COUNT(*) AS mcd from patientlogincred_table WHERE username != '" + uuid_static + "' GROUP BY did ORDER BY mcd;";
                        rs = stmt.executeQuery(query);
                        if(rs.next()){
                            did = rs.getString("did");
                            query = "SELECT phone FROM patientlogincred_table WHERE username = '" + uuid_static + "';";
                            rs = stmt.executeQuery(query);
                            rs.next();
                            String phone = rs.getString("phone");
                            System.out.println(phone);
                            query = "SELECT COUNT(*) AS NP FROM patientdetails_table where phone != '" + phone + "';";
                            rs = stmt.executeQuery(query);
                            rs.next();
                            int np = rs.getInt("NP");
                            query = "SELECT fname, lname, department from doctordetails_table WHERE did = '" + did_reapp + "';";
                            rs = stmt.executeQuery(query);
                            rs.next();
                            pid = getPatientID(rs.getString("department"), np);
                            System.out.println(did);
                            query = "Select fname, lname FROM doctordetails_table WHERE did = '" + did + "';";
                            rs = stmt.executeQuery(query);
                            rs.next();
                            dname = rs.getString("fname") + " " + rs.getString("lname");
                            query = "UPDATE patientlogincred_table SET pid = '" + pid + "', did = '" + did + "';";
                            stmt.executeUpdate(query);
                            System.out.println("patientlogincred_table updated!");
                            alert = new Alert(AlertType.INFORMATION, dname + " has been appointed as your doctor.", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.show();
                        }
                        else{
                            alert = new Alert(AlertType.INFORMATION, "Sorry, no doctor available at the moment. Please try again later.", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.show();
                        }
                    }
                    else{
                        alert = new Alert(AlertType.WARNING, "Doctor has been already appointed to you! Please request for an appointment manually.", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                    }
                }
            }
            else if(event.getSource() == view_profile){
                //view_doctor.setVisible(false);
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, username, password);
                stmt = con.createStatement();
                query = "SELECT phone FROM patientlogincred_table WHERE username = '" + uuid_static + "'";
                rs = stmt.executeQuery(query);
                long ph_one = 0;
                while(rs.next()){
                    ph_one = rs.getLong("phone");
                }
                query = "SELECT fname,lname,address,dob,email FROM patientdetails_table WHERE phone = '" + String.valueOf(ph_one) + "'";
                rs = stmt.executeQuery(query);
                String f_name="",l_name="",add_ress="",e_mail="";
                //long p_hone=0;
                Date dt = null;
                while(rs.next()){
                    f_name = rs.getString("fname");
                    l_name = rs.getString("lname");
                    add_ress = rs.getString("address");
                    dt = rs.getDate("dob");
                    e_mail = rs.getString("email");
                }
                
                name.setText("MY PROFILE");
                gridpane.getChildren().clear();
                Label fname = new Label("First Name");
                fname.setFont(new Font("Arial",18));
                gridpane.add(fname,0,0);
                Label fname_value = new Label(f_name);
                fname_value.setFont(new Font("Lucida sans",16));         
                gridpane.add(fname_value,1,0);                
                //TextField tf1 = new TextField();
                //gridpane.add(tf1,1,0);
                Label lname = new Label("Last Name");
                lname.setFont(new Font("Arial",18));
                gridpane.add(lname,0,1);
                Label lname_value = new Label(l_name); 
                lname_value.setFont(new Font("Lucida sans",16));                                               
                gridpane.add(lname_value,1,1);               
                //TextField tf2 = new TextField();
                //gridpane.add(tf2,1,1);
                Label address = new Label("Address");
                address.setFont(new Font("Arial",18));
                gridpane.add(address,0,2);
                Label address_value = new Label(add_ress);  
                address_value.setFont(new Font("Lucida sans",16)); 
                gridpane.add(address_value,1,2);                         
                //TextField tf3 = new TextField();
                //gridpane.add(tf3,1,2);
                Label phone = new Label("Phone Number");
                phone.setFont(new Font("Arial",18));
                gridpane.add(phone,0,3);
                Label phone_value = new Label(String.valueOf(ph_one));  
                phone_value.setFont(new Font("Lucida sans",16)); 
                gridpane.add(phone_value,1,3);                    
                //TextField tf4 = new TextField();
                //gridpane.add(tf4,1,3);
                Label email = new Label("E-Mail");
                email.setFont(new Font("Arial",18));
                gridpane.add(email,0,4);
                Label email_value = new Label(e_mail);
                email_value.setFont(new Font("Lucida sans",16)); 
                gridpane.add(email_value,1,4);
                Label dob = new Label("Date Of Birth");
                dob.setFont(new Font("Arial",18));
                gridpane.add(dob,0,5);
                Label dob_value = new Label(String.valueOf(dt));
                dob_value.setFont(new Font("Lucida sans",16)); 
                gridpane.add(dob_value,1,5);
                
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"" , e);
        }
    }
    
    private boolean checkPAppointment() throws SQLException{
        System.out.println(controller.getUname_static());
        query = "SELECT did from patientlogincred_table WHERE username = '" + uuid_static + "';";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            if(rs.getString("did") ==  null){
                return false;
            }
        }
        return true;
    }
    
    private void setAppointment() throws Exception{
        query = "SELECT phone FROM patientlogincred_table WHERE username = '" + uuid_static + "';";
        rs = stmt.executeQuery(query);
        rs.next();
        String phone = rs.getString("phone");
        System.out.println(phone);
        query = "SELECT COUNT(*) AS NP FROM patientdetails_table where phone != '" + phone + "';";
        rs = stmt.executeQuery(query);
        rs.next();
        int np = rs.getInt("NP");
        query = "SELECT fname, lname, department from doctordetails_table WHERE did = '" + did_reapp + "';";
        rs = stmt.executeQuery(query);
        rs.next();
        String dname = rs.getString("fname") + " " + rs.getString("lname");
        String pid = getPatientID(rs.getString("department"), np);
        query = "UPDATE patientlogincred_table SET pid = '" + pid + "', did = '" + did_reapp + "' WHERE phone = '" + phone + "';";
        stmt.executeUpdate(query);
        System.out.println("patientlogincred_table updated");
        alert = new Alert(AlertType.INFORMATION, dname + " has been appointed as your new doctor.", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
        
    }
    
    private String getPatientID(String dept, int np){
        if(dept.equalsIgnoreCase("Cardiology")){
            return "CAR" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Chaplaincy")){
            return "CHAP" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Gastroenterology")){
            return "GAST" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Gynecology")){
            return "GYN" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Haematology")){
            return "HAM" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Nephrology")){
            return "NEPH" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Neurology")){
            return "NEU" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Oncology")){
            return "ONC" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Ophthalmology")){
            return "OPT" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Orthopaedic")){
            return "ORT" + (np+1);
        }
        else if(dept.equalsIgnoreCase("Pharmacy")){
            return "PHM" + (np+1);
        }
        else{
            return "TRAU" + (np+1);
        }
    }
    
    public String getNamefromDB(String uname) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT shs_schema.patientdetails_table.fname FROM shs_schema.patientdetails_table, shs_schema.patientlogincred_table WHERE shs_schema.patientlogincred_table.username='" + uname + "' AND shs_schema.patientlogincred_table.phone=shs_schema.patientdetails_table.phone;";
        rs = stmt.executeQuery(query);
        rs.next();
        uname = rs.getString("fname");
        //con.close();
        return uname;
    }

    /**
     * @return the fname_static
     */
    public static String getFname_static() {
        return fname_static;
    }

    /**
     * @param aFname_static the fname_static to set
     */
    public static void setFname_static(String aFname_static) {
        fname_static = aFname_static;
    }
    
}