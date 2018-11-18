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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    Stage window;
    //HBox hbox;
    //Region region1, region2;
    //GridPane gridpane;
    Label sno, pname, gender, disease, dname, h1, h2, h3, h4, h5, doc[];
    TextField tfdoc[];
    TextArea addr;
    ToggleGroup surgeon, category;
    RadioButton yes, no, jresident, sresident, specialist, sspecialist;
    HBox hBox1;
    VBox vBox1;
    Button saveButton, backButton, reassignButton;
    Boolean flag;
    
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        shs = new SmartHealthcareSystem();
        flag = true;
        /*hbox = new HBox();
        region1 = new Region();
        gridpane = new GridPane();
        region2 = new Region();*/
        logger.addHandler(shs.getFHandler());
        logger.setLevel(Level.ALL);
    }    
    
    public void onClickListener(ActionEvent event){
        try{
            if(event.getSource() == add_doc){
                gridPane.getChildren().clear();
                doc = new Label[10];
                for(int i=0;i<10;i++){
                    doc[i] = new Label();
                }
                tfdoc = new TextField[7];
                for(int i=0;i<7;i++){
                    tfdoc[i] = new TextField();
                }
                tfdoc[5].setPromptText("HH:MM");
                tfdoc[6].setPromptText("HH:MM");
                addr = new TextArea();
                surgeon = new ToggleGroup();
                yes = new RadioButton();
                no = new RadioButton();
                yes.setText("Yes");
                no.setText("No");
                yes.setToggleGroup(surgeon);
                no.setToggleGroup(surgeon);
                hBox1 = new HBox();
                hBox1.getChildren().addAll(yes, no);
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
                for(int i=0;i<10;i++){
                    gridPane.add(doc[i], 1, i);
                }
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
                gridPane.add(saveButton, 3, 12);
                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            //if(validateInput()){
                            updateDB();
                            resetFields();
                            //}
                            //else{
                            //System.out.println("DB updation interrupted - invalid input(s).");
                            //flag = true;
                            //}
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
                h5 = new Label("Doctor");
                h5.setFont(new Font("Arial", 18));
                h5.setAlignment(Pos.CENTER);
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
                gridPane.add(h1, 0, rcount);
                gridPane.add(h2, 1, rcount);
                gridPane.add(h3, 2, rcount);
                gridPane.add(h4, 3, rcount);
                gridPane.add(h5, 4, rcount);
                rcount += 1;
                fetchPatients();
                updatePRPage();
                con.close();
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
        query = "SELECT patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender FROM patientdetails_table, patientlogincred_table WHERE patientlogincred_table.phone=shs_schema.patientdetails_table.phone;";
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
        while(rs.next()){
            sno = new Label(""+rcount);
            sno.setFont(new Font("Arial", 16));
            sno.setAlignment(Pos.CENTER);
            pnames.add(new Label(rs.getString("fname") + " " + rs.getString("lname")));
            pnames.get(pnames.size()-1).setFont(new Font("Arial", 16));
            pnames.get(pnames.size()-1).setAlignment(Pos.CENTER);
            gender = new Label(rs.getString("gender"));
            gender.setFont(new Font("Arial", 16));
            gender.setAlignment(Pos.CENTER);
            //disease = new Label(rs.getString("");
            //disease.setFont(new Font("Arial", 16));
            //disease.setAlignment(Pos.CENTER);
            //dname = new Label(rs.getString("");
            //disease.setFont(new Font("Arial", 16));
            //disease.setAlignment(Pos.CENTER);
            gridPane.add(sno, 0, rcount);
            gridPane.add(pnames.get(pnames.size()-1), 1, rcount);
            gridPane.add(gender, 2, rcount);
            //gridPane.add(disease, 3, rcount);
            //gridPane.add(doctor, 4, rcount);
            rcount += 1;
            pnames.get(pnames.size()-1).setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    for(int i=0;i<pnames.size();i++){
                        if(event.getSource() == pnames.get(i)){
                            buildReassignScene(pnames.get(i));
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
            pname = new Label(rs.getString("fname") + " " + rs.getString("lname"));
            pname.setFont(new Font("Arial", 16));
            pname.setAlignment(Pos.CENTER);
            gender = new Label(rs.getString("gender"));
            gender.setFont(new Font("Arial", 16));
            gender.setAlignment(Pos.CENTER);
            //disease = new Label(rs.getString("");
            //disease.setFont(new Font("Arial", 16));
            //disease.setAlignment(Pos.CENTER);
            //dname = new Label(rs.getString("");
            //disease.setFont(new Font("Arial", 16));
            //disease.setAlignment(Pos.CENTER);
            gridPane.add(sno, 0, rcount);
            gridPane.add(pname, 1, rcount);
            gridPane.add(gender, 2, rcount);
            //gridPane.add(disease, 3, rcount);
            //gridPane.add(doctor, 4, rcount);
            rcount += 1;
        }    
    }
    
    private void buildReassignScene(Label pclicked){
        gridPane.getChildren().clear();
        backButton = new Button("Back");
        reassignButton = new Button("Reassign");
        borderPane.getLeft().setDisable(true);
        gridPane.add(new Label("Patient:"), 1, 0);
        gridPane.add(new Label("Doctor:"), 1, 1);
        gridPane.add(pclicked, 3, 0);
        //gridPane.add(doctor, 3, 1);
        listDoctors();
        gridPane.add(backButton, 2, 2);
        gridPane.add(reassignButton, 3, 2);
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
                //updateDB
            }
        });
    }
    
    private void listDoctors(){
    
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
        query = "INSERT INTO shs_schema.doctordetails_table VALUES('" + tfdoc[0].getText() + "', '" + tfdoc[1].getText() + "', '" + tfdoc[2].getText() + "', '" + addr.getText() + "', '" + tfdoc[3].getText() + "', '" + tfdoc[4].getText() + "', '" + getSToggleValue() + "', '" + getCToggleValue() + "', '" + tfdoc[5].getText() + "', '" + tfdoc[6].getText() + "');";
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
        tfdoc[1].setText("");
        tfdoc[2].setText("");
        tfdoc[3].setText("");
        tfdoc[4].setText("");
        tfdoc[5].setText("");
        tfdoc[6].setText("");
        addr.setText("");
        surgeon.getSelectedToggle().setSelected(false);
        category.getSelectedToggle().setSelected(false);
    }
    
    /*private boolean validateInput(){
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
        
        if(tfdoc)
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
        
        if(gender.getSelectedToggle() == null){
            // show alert dialog box
            flag = false;
        }
        if(dob.getValue() == null){
            dob.setPromptText("No date selected!");
            flag = false;
        }
        if(!(tfdoc[3].getText().length() == 10 && tfdoc[3].getText().matches("[0-9]+"))){
            tfdoc[3].setText("");
            tfdoc[3].setPromptText("Not a valid mobile number!");
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
    }*/
}


