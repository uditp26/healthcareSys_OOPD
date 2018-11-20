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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class AHomePageController implements Initializable {

    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private static Logger logger = Logger.getLogger("smarthealthcaresystem.ahomepagecontroller");
    private String query;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
    private int rcount;
    
    SmartHealthcareSystem shs;
    AHomePage ahp;
    Stage window;
    Scene scene;
    Label sno, pname, gender, disease, dname, h1, h2, h3, h4, h5, doc[];
    TextField tfdoc[];
    TextArea addr;
    ToggleGroup surgeon, category, hod, doctors;
    RadioButton yes, no, jresident, sresident, specialist, sspecialist, yeah, nah, chr;
    HBox hBox1, hBox2;
    VBox vBox1;
    Button saveButton, backButton, reassignButton;
    Boolean flag;
    ArrayList<RadioButton> ch;
    ArrayList<String> doctorIDs;
    ArrayList<String> dIDs;
    ArrayList<String> patientIDs;
    
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
    
    @FXML
    GridPane gridPane;
    
    @FXML
    ScrollBar sbar;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        shs = new SmartHealthcareSystem();
        ahp = new AHomePage();
        flag = true;
        logger.addHandler(shs.getFHandler());
        logger.setLevel(Level.ALL);
        scene = ahp.getAdminScene();
        sbar.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println(oldValue + " " + newValue);
                borderPane.getCenter().setLayoutY(-newValue.doubleValue());
            }
            
        });
    }    
    
    public void onClickListener(ActionEvent event){
        try{
            if(event.getSource() == add_doc){
                gridPane.getChildren().clear();
                doc = new Label[12];
                for(int i=0;i<12;i++){
                    doc[i] = new Label();
                }
                tfdoc = new TextField[8];
                for(int i=0;i<8;i++){
                    tfdoc[i] = new TextField();
                }
                tfdoc[5].setPromptText("HH:MM");
                tfdoc[6].setPromptText("HH:MM");
                addr = new TextArea();
                surgeon = new ToggleGroup();
                hod = new ToggleGroup();
                yes = new RadioButton();
                no = new RadioButton();
                yeah = new RadioButton();
                nah = new RadioButton();
                yes.setText("Yes");
                yeah.setText("Yes");
                no.setText("No");
                nah.setText("No");
                yes.setToggleGroup(surgeon);
                no.setToggleGroup(surgeon);
                yeah.setToggleGroup(hod);
                nah.setToggleGroup(hod);
                hBox1 = new HBox();
                hBox2 = new HBox();
                hBox1.getChildren().addAll(yes, no);
                hBox2.getChildren().addAll(yeah, nah);
                category = new ToggleGroup();
                jresident = new RadioButton();
                jresident.setText("Junior Resident");
                jresident.setToggleGroup(category);
                sresident = new RadioButton();
                sresident.setText("Senior Resident");
                sresident.setToggleGroup(category);
                specialist = new RadioButton();
                specialist.setText("Specialist");
                specialist.setToggleGroup(category);
                sspecialist = new RadioButton();
                sspecialist.setText("Senior Specialist");
                sspecialist.setToggleGroup(category);
                vBox1 = new VBox();
                vBox1.getChildren().addAll(jresident, sresident, specialist, sspecialist);
                saveButton = new Button("Save");
                doc[0].setText("ID");
                doc[1].setText("First Name");
                doc[2].setText("Last Name");
                doc[3].setText("Address");
                doc[4].setText("Phone");
                doc[5].setText("Email");
                doc[6].setText("Surgeon?");
                doc[7].setText("Category");
                doc[8].setText("Duty Start");
                doc[9].setText("Duty End");
                doc[10].setText("HOD?");
                doc[11].setText("Department");
                for(int i=0;i<12;i++){
                    gridPane.add(doc[i], 1, i);
                }
                gridPane.getColumnConstraints().get(4).setMinWidth(200);
                gridPane.add(tfdoc[0], 4, 0);
                gridPane.add(tfdoc[1], 4, 1);
                gridPane.add(tfdoc[2], 4, 2);
                gridPane.add(addr, 4, 3);
                gridPane.add(tfdoc[3], 4, 4);
                gridPane.add(tfdoc[4], 4, 5);
                gridPane.add(hBox1, 4, 6);
                gridPane.add(vBox1, 4, 7);
                gridPane.add(tfdoc[5], 4, 8);
                gridPane.add(tfdoc[6], 4, 9);
                gridPane.add(hBox2, 4, 10);
                gridPane.add(tfdoc[7], 4, 11);
                gridPane.add(saveButton, 3, 12);
                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            if(validateInput()){
                                updateDB();
                                resetFields();
                            }
                            else{
                                System.out.println("DB updation interrupted - invalid input(s).");
                                flag = true;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(AHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            else if(event.getSource() == view_doc){
                gridPane.getChildren().clear();
                rcount = 0;
                h1 = new Label("DID");
                h1.setFont(new Font("Arial", 18));
                h1.setAlignment(Pos.CENTER);
                h2 = new Label("Doctor");
                h2.setFont(new Font("Arial", 18));
                h2.setAlignment(Pos.CENTER);
                h3 = new Label("Categoy");
                h3.setFont(new Font("Arial", 18));
                h3.setAlignment(Pos.CENTER);
                h4 = new Label("Duty Start");
                h4.setFont(new Font("Arial", 18));
                h4.setAlignment(Pos.CENTER);
                h5 = new Label("Duty End");
                h5.setFont(new Font("Arial", 18));
                h5.setAlignment(Pos.CENTER);
                gridPane.getColumnConstraints().get(0).setMinWidth(100);
                gridPane.getColumnConstraints().get(1).setMinWidth(180);
                gridPane.getColumnConstraints().get(2).setMinWidth(180);
                gridPane.getColumnConstraints().get(3).setMinWidth(180);
                gridPane.getColumnConstraints().get(4).setMinWidth(180);
                gridPane.add(h1, 0, rcount);
                gridPane.add(h2, 1, rcount);
                gridPane.add(h3, 2, rcount);
                gridPane.add(h4, 3, rcount);
                gridPane.add(h5, 4, rcount);
                rcount += 1;
                fetchDoctors();
                updateDPage();
                con.close();
            }
            else if(event.getSource() == view_patient){
                gridPane.getChildren().clear();
                rcount = 0;
                h1 = new Label("S.No");
                h1.setFont(new Font("Arial", 18));
                h1.setAlignment(Pos.CENTER);
                h2 = new Label("Patient");
                h2.setFont(new Font("Arial", 18));
                h2.setAlignment(Pos.CENTER);
                h3 = new Label("Gender");
                h3.setFont(new Font("Arial", 18));
                h3.setAlignment(Pos.CENTER);
                h4 = new Label("Disease");
                h4.setFont(new Font("Arial", 18));
                h4.setAlignment(Pos.CENTER);
                h5 = new Label("Location");
                h5.setFont(new Font("Arial", 18));
                h5.setAlignment(Pos.CENTER);
                gridPane.getColumnConstraints().get(0).setMinWidth(100);
                gridPane.getColumnConstraints().get(1).setMinWidth(180);
                gridPane.getColumnConstraints().get(2).setMinWidth(180);
                gridPane.getColumnConstraints().get(3).setMinWidth(180);
                gridPane.getColumnConstraints().get(4).setMinWidth(180);
                gridPane.add(h1, 0, rcount);
                gridPane.add(h2, 1, rcount);
                gridPane.add(h3, 2, rcount);
                gridPane.add(h4, 3, rcount);
                gridPane.add(h5, 4, rcount);
                rcount += 1;
                fetchPatients();
                updatePPage();
                con.close();
            }
            else if(event.getSource() == reassign_doc){
                gridPane.getChildren().clear();
                rcount = 0;
                h1 = new Label("S.No");
                h1.setFont(new Font("Arial", 18));
                h1.setAlignment(Pos.CENTER);
                h2 = new Label("Patient");
                h2.setFont(new Font("Arial", 18));
                h2.setAlignment(Pos.CENTER);
                h3 = new Label("Gender");
                h3.setFont(new Font("Arial", 18));
                h3.setAlignment(Pos.CENTER);
                h4 = new Label("Disease");
                h4.setFont(new Font("Arial", 18));
                h4.setAlignment(Pos.CENTER);
                h5 = new Label("Doctor");
                h5.setFont(new Font("Arial", 18));
                h5.setAlignment(Pos.CENTER);
                gridPane.getColumnConstraints().get(0).setMinWidth(100);
                gridPane.getColumnConstraints().get(1).setMinWidth(180);
                gridPane.getColumnConstraints().get(2).setMinWidth(180);
                gridPane.getColumnConstraints().get(3).setMinWidth(180);
                gridPane.getColumnConstraints().get(4).setMinWidth(180);
                gridPane.add(h1, 0, rcount);
                gridPane.add(h2, 1, rcount);
                gridPane.add(h3, 2, rcount);
                gridPane.add(h4, 3, rcount);
                gridPane.add(h5, 4, rcount);
                rcount += 1;
                fetchRPatients();
                updatePRPage();
                //con.close();
            }
            else if(event.getSource() == logout){
                window = (Stage) logout.getScene().getWindow();
                shs.start(window);
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"" , e);            
        }
    }
    
    private void fetchPatients() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender, patientlogincred_table.disease, patientdetails_table.location FROM patientdetails_table, patientlogincred_table WHERE patientlogincred_table.phone=patientdetails_table.phone;";
        rs = stmt.executeQuery(query);
    }
    
    private void fetchRPatients() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT patientlogincred_table.pid, patientlogincred_table.did, patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender, patientlogincred_table.disease, doctordetails_table.fname, doctordetails_table.lname  FROM patientdetails_table, patientlogincred_table, doctordetails_table  WHERE patientlogincred_table.phone=patientdetails_table.phone AND patientlogincred_table.did=doctordetails_table.did;";
        rs = stmt.executeQuery(query);
    }
    
    private void fetchDoctors() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "SELECT did, fname, lname, category, dutystart, dutyend FROM doctordetails_table;";
        rs = stmt.executeQuery(query);
    }
    
    private void updatePRPage() throws Exception{
        ArrayList<Label> pnames = new ArrayList();
        ArrayList<Label> dnames = new ArrayList();
        patientIDs = new ArrayList<>();
        dIDs = new ArrayList<>();
        while(rs.next()){
            patientIDs.add(rs.getString("patientlogincred_table.pid"));
            dIDs.add(rs.getString("patientlogincred_table.did"));
            sno = new Label(""+rcount);
            sno.setFont(new Font("Arial", 16));
            sno.setAlignment(Pos.CENTER);
            pnames.add(new Label(rs.getString("patientdetails_table.fname") + " " + rs.getString("patientdetails_table.lname")));
            pnames.get(pnames.size()-1).setFont(new Font("Arial", 16));
            pnames.get(pnames.size()-1).setAlignment(Pos.CENTER);
            gender = new Label(rs.getString("gender"));
            gender.setFont(new Font("Arial", 16));
            gender.setAlignment(Pos.CENTER);
            disease = new Label(rs.getString("disease"));
            disease.setFont(new Font("Arial", 16));
            disease.setAlignment(Pos.CENTER);
            dnames.add(new Label(rs.getString("doctordetails_table.fname") + " " + rs.getString("doctordetails_table.lname")));
            dnames.get(dnames.size()-1).setFont(new Font("Arial", 16));
            dnames.get(dnames.size()-1).setAlignment(Pos.CENTER);
            gridPane.getColumnConstraints().get(0).setMinWidth(100);
            gridPane.getColumnConstraints().get(1).setMinWidth(180);
            gridPane.getColumnConstraints().get(2).setMinWidth(180);
            gridPane.getColumnConstraints().get(3).setMinWidth(180);
            gridPane.getColumnConstraints().get(4).setMinWidth(180);
            gridPane.add(sno, 0, rcount);
            gridPane.add(pnames.get(pnames.size()-1), 1, rcount);
            gridPane.add(gender, 2, rcount);
            gridPane.add(disease, 3, rcount);
            gridPane.add(dnames.get(pnames.size()-1), 4, rcount);
            rcount += 1;
            pnames.get(pnames.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    for(int i=0;i<pnames.size();i++){
                        if(event.getSource() == pnames.get(i)){
                            try {
                                buildReassignScene(pnames.get(i), dnames.get(i), patientIDs.get(i), dIDs.get(i));
                            } catch (SQLException ex) {
                                Logger.getLogger(AHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
        }    
    }
    
    private void updatePPage() throws Exception{
        while(rs.next()){
            sno = new Label(""+rcount);
            sno.setFont(new Font("Arial", 16));
            sno.setAlignment(Pos.CENTER);
            pname = new Label(rs.getString("patientdetails_table.fname") + " " + rs.getString("patientdetails_table.lname"));
            pname.setFont(new Font("Arial", 16));
            pname.setAlignment(Pos.CENTER);
            gender = new Label(rs.getString("gender"));
            gender.setFont(new Font("Arial", 16));
            gender.setAlignment(Pos.CENTER);
            disease = new Label(rs.getString("disease"));
            disease.setFont(new Font("Arial", 16));
            disease.setAlignment(Pos.CENTER);
            dname = new Label(rs.getString("location"));
            dname.setFont(new Font("Arial", 16));
            dname.setAlignment(Pos.CENTER);
            gridPane.getColumnConstraints().get(0).setMinWidth(100);
            gridPane.getColumnConstraints().get(1).setMinWidth(180);
            gridPane.getColumnConstraints().get(2).setMinWidth(180);
            gridPane.getColumnConstraints().get(3).setMinWidth(180);
            gridPane.getColumnConstraints().get(4).setMinWidth(180);
            gridPane.add(sno, 0, rcount);
            gridPane.add(pname, 1, rcount);
            gridPane.add(gender, 2, rcount);
            gridPane.add(disease, 3, rcount);
            gridPane.add(dname, 4, rcount);
            rcount += 1;
        }    
    }
    
    private void buildReassignScene(Label pclicked, Label dclicked, String pid, String did) throws SQLException{
        gridPane.getChildren().clear();
        backButton = new Button("Back");
        reassignButton = new Button("Reassign");
        borderPane.getLeft().setDisable(true);
        gridPane.getColumnConstraints().get(0).setMinWidth(100);
        gridPane.getColumnConstraints().get(1).setMinWidth(180);
        gridPane.getColumnConstraints().get(2).setMinWidth(180);
        gridPane.getColumnConstraints().get(3).setMinWidth(180);
        gridPane.getColumnConstraints().get(4).setMinWidth(180);
        gridPane.add(new Label("Patient:"), 1, 0);
        gridPane.add(new Label("Doctor:"), 1, 1);
        gridPane.add(new Label("List of doctors:"), 1, 2);
        gridPane.add(new Label(""), 1, 3);
        gridPane.add(new Label("Doctor"), 1, 4);
        gridPane.add(new Label("Dept."), 2, 4);
        gridPane.add(new Label("Duty Start"), 3, 4);
        gridPane.add(new Label("Duty End"), 4, 4);
        gridPane.add(pclicked, 3, 0);
        gridPane.add(dclicked, 3, 1);
        int row = listDoctors(5, dclicked, did);
        gridPane.add(backButton, 2, row);
        gridPane.add(reassignButton, 3, row);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                gridPane.getChildren().clear();
                borderPane.getLeft().setDisable(false);
            }
        });
        reassignButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                for(int i=0;i<ch.size();i++){
                    if(doctors.getSelectedToggle() == ch.get(i)){
                        try {
                            updateRDB(doctorIDs.get(i), pid);
                        } catch (SQLException ex) {
                            Logger.getLogger(AHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("doctordetails_table updated!");
                        break;    
                    }
                }
                gridPane.getChildren().clear();
                borderPane.getLeft().setDisable(false); 
            }
        });
    }
    
   private void updateRDB(String did, String pid) throws SQLException{
       query = "UPDATE patientlogincred_table SET did = '" + did + "' WHERE pid = '" + pid + "';";
       stmt.executeUpdate(query);
       System.out.println("patientlogincred_table Updated");
       con.close();
   }
   
   public boolean checkUpdateRDB(String did) throws Exception{
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection(URL, username, password);
       stmt = con.createStatement();
       query = "SELECT * FROM patientlogincred_table WHERE did = '" + did + "';";
       rs = stmt.executeQuery(query);
       return rs.next();
   }
    
    private int listDoctors(int row, Label dclicked, String did) throws SQLException{
        /*String temp = dclicked.getText();
        String arr[];
        arr = temp.split(" ");
        query = "SELECT dutystart, dutyend from doctordetails_table WHERE fname = '" + arr[0] + "' AND lname = '" + arr[1] + "';";      // assuming doctor names to be distinct
        Time t1, t2;
        rs = stmt.executeQuery(query);
        if(rs.next()){
            t1 = rs.getTime("dutystart");
            t2 = rs.getTime("dutyend");
        }*/
        doctors = new ToggleGroup();
        ch = new ArrayList<>();
        doctorIDs = new ArrayList<>();
        query = "SELECT did, fname, lname, department, dutystart, dutyend from doctordetails_table WHERE did != '" + did + "' ORDER BY dutystart, dutyend;";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            chr = new RadioButton();
            chr.setToggleGroup(doctors);
            ch.add(chr);
            doctorIDs.add(rs.getString("did"));
            gridPane.getColumnConstraints().get(0).setMinWidth(100);
            gridPane.getColumnConstraints().get(1).setMinWidth(180);
            gridPane.getColumnConstraints().get(2).setMinWidth(180);
            gridPane.getColumnConstraints().get(3).setMinWidth(180);
            gridPane.getColumnConstraints().get(4).setMinWidth(180);
            gridPane.add(chr, 0, row);
            gridPane.add(new Label(rs.getString("fname") + " " + rs.getString("lname")), 1, row);
            gridPane.add(new Label(rs.getString("department")), 2, row);
            gridPane.add(new Label(rs.getString("dutystart")), 3, row);
            gridPane.add(new Label(rs.getString("dutyend")), 4, row);
            row += 1;
        }
        return row;
    }
    
    private void updateDPage() throws Exception{
        while(rs.next()){
            h1 = new Label(rs.getString("did"));
            h1.setFont(new Font("Arial", 16));
            h1.setAlignment(Pos.CENTER);
            h2 = new Label(rs.getString("fname") + " " + rs.getString("lname"));
            h2.setFont(new Font("Arial", 16));
            h2.setAlignment(Pos.CENTER);
            h3 = new Label(rs.getString("category"));
            h3.setFont(new Font("Arial", 16));
            h3.setAlignment(Pos.CENTER);
            h4 = new Label(rs.getString("dutystart"));
            h4.setFont(new Font("Arial", 16));
            h4.setAlignment(Pos.CENTER);
            h5 = new Label(rs.getString("dutyend"));
            h5.setFont(new Font("Arial", 16));
            h5.setAlignment(Pos.CENTER);
            gridPane.getColumnConstraints().get(0).setMinWidth(100);
            gridPane.getColumnConstraints().get(1).setMinWidth(180);
            gridPane.getColumnConstraints().get(2).setMinWidth(180);
            gridPane.getColumnConstraints().get(3).setMinWidth(180);
            gridPane.getColumnConstraints().get(4).setMinWidth(180);
            gridPane.add(h1, 0, rcount);
            gridPane.add(h2, 1, rcount);
            gridPane.add(h3, 2, rcount);
            gridPane.add(h4, 3, rcount);
            gridPane.add(h5, 4, rcount);
            rcount += 1;
        }
    }
    
    private void updateDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        query = "INSERT INTO shs_schema.doctordetails_table VALUES('" + tfdoc[0].getText() + "', '" + tfdoc[1].getText() + "', '" + tfdoc[2].getText() + "', '" + addr.getText() + "', '" + tfdoc[3].getText() + "', '" + tfdoc[4].getText() + "', '" + getSToggleValue() + "', '" + getCToggleValue() + "', '" + tfdoc[5].getText() + "', '" + tfdoc[6].getText() + "', NULL, '" + getHToggleValue() + "', '" + tfdoc[7].getText() + "');";
        stmt.executeUpdate(query);
        System.out.println("doctordetails_table Updated");
        con.close();
    }
    
    private String getSToggleValue(){
        if(surgeon.getSelectedToggle() == yes){
            return "Yes";
        }
        else{
            return "No";
        }
    }
    
    private String getHToggleValue(){
        if(hod.getSelectedToggle() == yeah){
            return "Yes";
        }
        else{
            return "No";
        }
    }
    
    private String getCToggleValue(){
        if(category.getSelectedToggle() == jresident){
            return "JR";
        }
        else if(category.getSelectedToggle() == sresident){
            return "SR";
        }
        else if(category.getSelectedToggle() == specialist){
            return "SP";
        }
        else{
            return "SSP";
        }
    }
    
    private void resetFields(){
        tfdoc[0].setText("");
        tfdoc[0].setPromptText("");
        tfdoc[1].setText("");
        tfdoc[1].setPromptText("");
        tfdoc[2].setText("");
        tfdoc[2].setPromptText("");
        tfdoc[3].setText("");
        tfdoc[3].setPromptText("");
        tfdoc[4].setText("");
        tfdoc[4].setPromptText("");
        tfdoc[5].setText("");
        tfdoc[5].setPromptText("");
        tfdoc[6].setText("");
        tfdoc[6].setPromptText("");
        tfdoc[7].setText("");
        tfdoc[7].setPromptText("");
        addr.setText("");
        surgeon.getSelectedToggle().setSelected(false);
        category.getSelectedToggle().setSelected(false);
        hod.getSelectedToggle().setSelected(false);
        
    }
    
    private boolean validateInput(){
        
        if(tfdoc[0].getText().matches("")){
            tfdoc[0].setText("");
            tfdoc[0].setPromptText("Id field empty!");
            flag = false;
        }
        
        if(!tfdoc[1].getText().matches("[a-zA-Z]+")){
            tfdoc[1].setText("");
            tfdoc[1].setPromptText("Not a valid name!");
            flag = false;
        }
        
        if(!tfdoc[2].getText().matches("[a-zA-Z]+")){
            tfdoc[2].setText("");
            tfdoc[2].setPromptText("Not a valid name!");
            flag = false;
        }
        
        if(!(tfdoc[3].getText().length() == 10 && tfdoc[3].getText().matches("[0-9]+"))){
            tfdoc[3].setText("");
            tfdoc[3].setPromptText("Not a valid mobile number!");
            flag = false;
        }
        
        if(!tfdoc[4].getText().matches("[a-z][a-z_0-9_.]*[@][a-z_.]+")){
            tfdoc[4].setText("");
            tfdoc[4].setPromptText("Not a valid email!");
            flag = false;
        }
        
        if(!tfdoc[5].getText().matches("[0-9][0-9]:[0-9][0-9]")){
            tfdoc[5].setText("");
            tfdoc[5].setPromptText("Not a valid time!");
            flag = false;
        }
        
        if(!tfdoc[6].getText().matches("[0-9][0-9]:[0-9][0-9]")){
            tfdoc[6].setText("");
            tfdoc[6].setPromptText("Not a valid time!");
            flag = false;
        }
        
        if(tfdoc[7].getText().matches("")){
            tfdoc[7].setText("");
            tfdoc[7].setPromptText("Department Field empty!");
            flag = false;
        }
        
        if(surgeon.getSelectedToggle() == null){
            flag = false;
        }
        
        if(category.getSelectedToggle() == null){
            flag = false;
        }
        
        if(hod.getSelectedToggle() == null){
            flag = false;
        }
        
        return flag;
    }
}


