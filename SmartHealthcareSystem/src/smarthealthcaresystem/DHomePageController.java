package smarthealthcaresystem;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import static smarthealthcaresystem.SHSController.setDname_static;


/**
 * FXML Controller class
 *
 * @author protagonist26
 */
public class DHomePageController implements Initializable {
    private static final String username = "root";      // Change username if not root
    private static final String password = "password";        // Enter your MySQL password here
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shs_schema";
    private String query;
    private ResultSet rs;
    private Connection con;
    private Statement stmt;
   
    private ResultSet rs1;
    private Connection con1;
    private Statement stmt1;
    private String query1;
    private String query2;
    
    private Statement stmt2;
    private Boolean flag;
    private Boolean flag2;
    private static String dname;
    private static String static_did;
    Stage window;
    SmartHealthcareSystem shs;
    private DRegPageController dRegPagec;
    private String fname1;
    private String lname1;
    private String address1;
    private String phone1;
    private String email1;
    private String issurgeon1;
    private String category1;
    private String dutystart1;
    private String dutyend1;
    private String ishod1;
    private String department1;
    public static TableView<Patient> table;
    private int rcount ;
    ArrayList<RadioButton> ch;
    ArrayList<String> doctorIDs;
    ArrayList<String> dIDs;
    ArrayList<String> patientIDs;
    @FXML
    Region region;
    @FXML
    GridPane gridpane;
    
    @FXML
    GridPane gridPane1;
    
    @FXML
    Label name;
    
    @FXML
    ToggleGroup doctors;
    
    @FXML
    Label h1;
    
    @FXML
    Label h2;
    
    @FXML
    Label h3;
    
    @FXML
    RadioButton chr;
    
    @FXML
    Label sno;
    
    @FXML
    Label gender;
    
    @FXML
    Label h4;
    
    @FXML
    Label h5;
    
    @FXML
    Label welcome;
    
    @FXML
    Button edit_profile;
    
    @FXML
    Button refer_patient;
    
    @FXML
    Label disease;
    
    @FXML
    Button display_patient;
    
    @FXML
    Button discharge_patient;
    
    @FXML
    Button choose_doctor;
    
    @FXML
    Button request_appointment;
    
    @FXML
    Button view_profile;
    
    @FXML
    Button logout;
    
    @FXML
    VBox vbox;
    @FXML
    BorderPane borderpane;
    @FXML
    Label l1;
    
    @FXML
    Button backButton;
    @FXML
    Button referButton;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane1=new GridPane();
        dRegPagec = new DRegPageController();
        ColumnConstraints cc = new ColumnConstraints();
        shs=new SmartHealthcareSystem();
        cc.setPercentWidth(50);
        gridpane=new GridPane();
        borderpane.setRight(gridpane);
        try {
            //1 for name
            if(dRegPagec.getDname_static() != null){
                welcome.setText("Welcome " + dRegPagec.getDname_static());
                welcome.setFont(new Font("Arial",14));
            }
            else{
                dname=lookUpDB(1);
                welcome.setText("Welcome, " + dname);
                welcome.setFont(new Font("Arial",14));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Label f=new Label();
        f.setText("Patients : ");
        f.setFont(new Font("Arial",22));
        TableColumn<Patient, String> p_id = new TableColumn<>("Patient ID");
        p_id.setMinWidth(200);
        p_id.setCellValueFactory(new PropertyValueFactory<>("pid"));

       //StackOverflow 
        TableColumn<Patient, Double> f_name = new TableColumn<>("First Name");
        f_name.setMinWidth(100);
        f_name.setCellValueFactory(new PropertyValueFactory<>("fname"));

 
        TableColumn<Patient, String> l_name = new TableColumn<>("Last Name");
        l_name.setMinWidth(100);
        l_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
        TableColumn<Patient, String> l_ocation = new TableColumn<>("Location");
        l_ocation.setMinWidth(100);
        l_ocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        TableColumn<Patient, String> quantityColumn2 = new TableColumn<>("Phone");
        quantityColumn2.setMinWidth(100);
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        TableColumn<Patient, String> quantityColumn3 = new TableColumn<>("Condition");
        quantityColumn3.setMinWidth(100);
        quantityColumn3.setCellValueFactory(new PropertyValueFactory<>("condition"));
        
        TableColumn<Patient, Character> quantityColumn4 = new TableColumn<>("Gender");
        quantityColumn4.setMinWidth(100);
        quantityColumn4.setCellValueFactory(new PropertyValueFactory<>("gender"));
        

        table = new TableView<>();
        try
        {
            table.setItems(getPatient());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        table.getColumns().addAll(p_id, f_name, l_name,l_ocation,quantityColumn2,quantityColumn3,quantityColumn4);
        borderpane.setRight(table);
    }
    public ObservableList<Patient> getPatient() throws Exception{
        ObservableList<Patient> products = FXCollections.observableArrayList();
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);

        stmt = con.createStatement();
        stmt2 = con.createStatement();
        String q;
        query = "SELECT * FROM shs_schema.patientlogincred_table WHERE did='" + static_did + "';";
        rs = stmt.executeQuery(query);
//        System.out.println("Hello");
        while(rs.next()){
            q=rs.getString("phone");
//            System.out.println("Hello");
            query1 = "SELECT * FROM shs_schema.patientdetails_table WHERE phone='" + q + "';";
            rs1=stmt2.executeQuery(query1);
            while(rs1.next()){
//                System.out.println("Hello");
                products.add(new Patient(rs.getString("pid"),rs1.getString("fname"),rs1.getString("lname"),rs1.getString("address"),rs1.getString("phone"),rs1.getString("cndtion"),rs1.getString("gender")));
            }
            

        }
       
       
        return products;
    }

    
    public void onClickListener(ActionEvent event) throws Exception{
        
        if(event.getSource() == edit_profile){
//            vbox.setVisible(false);
            gridpane=new GridPane();
            name.setText("EDIT PROFILE");
//            borderpane.setRight(gridpane);
//            gridpane.getChildren().clear();
            Label fname = new Label("First Name");
           gridpane.setHgap(10);
           gridpane.setVgap(10);
            fname.setFont(new Font("Arial",18));
            gridpane.add(fname,0,0);
      
            
            TextField tf1 = new TextField();
            
            gridpane.add(tf1,1,0);
            Label lname = new Label("Last Name");
            lname.setFont(new Font("Arial",18));
            gridpane.add(lname,0,1);
            TextField tf2 = new TextField();
            gridpane.add(tf2,1,1);
            Label address = new Label("Address");
            address.setFont(new Font("Arial",18));
            gridpane.add(address,0,2);
            TextField tf3 = new TextField();
            gridpane.add(tf3,1,2);
            Label phone = new Label("Phone Number");
            phone.setFont(new Font("Arial",18));
            gridpane.add(phone,0,3);
            TextField tf4 = new TextField();
            gridpane.add(tf4,1,3);
            Label email = new Label("E-Mail");
            email.setFont(new Font("Arial",18));
            gridpane.add(email,0,4);
            TextField tf5 = new TextField();
            gridpane.add(tf5,1,4);
            Label visitingtime = new Label("Visiting Start");
            visitingtime.setFont(new Font("Arial",18));
            gridpane.add(visitingtime,0,5);
            
            TextField tf6 = new TextField();
            
            gridpane.add(tf6,1,5);
            Label visitingtime1 = new Label("Visiting End");
            visitingtime1.setFont(new Font("Arial",18));
            gridpane.add(visitingtime1,0,6);
            
            TextField tf8 = new TextField();
            
            gridpane.add(tf8,1,6);
            
            
            Label password = new Label("Password");
            password.setFont(new Font("Arial",18));
            gridpane.add(password,0,7);
            PasswordField tf7 = new PasswordField();
            //TextField tf6 = new TextField();
            gridpane.add(tf7,1,7);
            Button submit = new Button("Submit");
           
            submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int count=0;
                try
                {
                    
                System.out.println("TF1 : "+tf1.getText());
                if(!(tf1.getText().trim().isEmpty()))
                {System.out.println("DD1");
                    count++;
                    lookUpdateDB(1,tf1.getText());
                }
                if(!(tf2.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD2");
                    lookUpdateDB(2,tf2.getText());
                }
                if(!(tf3.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD3");
                    lookUpdateDB(3,tf3.getText());
                }
                if(!(tf4.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD4");
                    lookUpdateDB(4,tf4.getText());
                }
                if(!(tf5.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD5");
                    lookUpdateDB(5,tf5.getText());
                }
                if(!(tf6.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD6");
                    lookUpdateDB(6,tf6.getText());
                }
                if (!(tf7.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD7");
                    lookUpdateDB(7,tf7.getText());
                }
                if (!(tf8.getText().trim().isEmpty()))
                {count++;
                System.out.println("DD7");
                    lookUpdateDB(8,tf8.getText());
                }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                System.out.println("Count : "+count);
                  
                if(count>=1)
                {
                    Alert alert = new Alert(AlertType.INFORMATION, "Update Successfull here", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                }
  
            }
            });
            
            gridpane.add(submit,0,8);
            gridpane.setPadding(new Insets(100,0,100,100));
            
            gridpane.setMinHeight(Control.USE_COMPUTED_SIZE);
            gridpane.setMinWidth(Control.USE_COMPUTED_SIZE);
            
            
            borderpane.setCenter(gridpane);
            
        }
        else if(event.getSource() == logout){
            
            window = (Stage) logout.getScene().getWindow();
            shs.start(window);
        }
        else if(event.getSource() == refer_patient){
//            vbox.setVisible(false);
            gridPane1.getChildren().clear();
        
//            name.setText("REFER PATIENT");
//            gridpane.getChildren().clear();
//            Label refer=new Label("Refering Patient ID");
//            refer.setFont(new Font("Arial",18));
//            gridpane.add(refer,0,0);
//            TextField tf1 = new TextField();
//            gridpane.add(tf1,1,0);
//            Label referd=new Label("Refered Doctor ID");
//            referd.setFont(new Font("Arial",18));
//            gridpane.add(referd,0,1);
//            TextField tf11 = new TextField();
//            gridpane.add(tf11,1,1);
//            Button submit1 = new Button("Submit");
            
            
            ////
            
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
                
                gridPane1.add(h1, 0, rcount);
                gridPane1.add(h2, 1, rcount);
                gridPane1.add(h3, 2, rcount);
                gridPane1.add(h4, 3, rcount);
                gridPane1.add(h5, 4, rcount);
                
                rcount += 1;
                try
                {
                if(validated())
                {
                    System.out.println("Hello1");
                          
                    fetchRPatients();
                }
                else
                {
                    System.out.println("Hello2");
                    fetchRPatients1(fetch());
                    
                }
                System.out.println("14");
                System.out.println("Data1 "+fetch());
                
                updatePRPage();
                }
                catch(Exception e)
                {
                    System.out.println("Here "+e);
                }
                borderpane.setRight(gridPane1);
            ////
//            submit1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) 
//            {
//
//                int count=0;
//             
//                //stackoverflow  
//                if(count>=1)
//                {
//                    Alert alert = new Alert(AlertType.INFORMATION, "Update Successfull here", ButtonType.OK);
//                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//                    alert.show();
//                }
//  
//            }
//            });
//            gridpane.add(submit1,0,2);
            gridpane.setHgap(10);
            gridpane.setVgap(10);
            gridpane.setPadding(new Insets(100,0,100,100));
            borderpane.setCenter(gridpane);

        }
        else if(event.getSource() == display_patient){
            name.setText("PATIENT DETAILS");
            
        TableColumn<Patient, String> p_id = new TableColumn<>("Patient ID");
        p_id.setMinWidth(200);
        p_id.setCellValueFactory(new PropertyValueFactory<>("pid"));
        
        
        TableColumn<Patient, Double> f_name = new TableColumn<>("First Name");
        f_name.setMinWidth(100);
        f_name.setCellValueFactory(new PropertyValueFactory<>("fname"));

 
        TableColumn<Patient, String> l_name = new TableColumn<>("Last Name");
        l_name.setMinWidth(100);
        l_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
        TableColumn<Patient, String> l_ocation = new TableColumn<>("Location");
        l_ocation.setMinWidth(100);
        l_ocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        TableColumn<Patient, String> quantityColumn2 = new TableColumn<>("Phone");
        quantityColumn2.setMinWidth(100);
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        TableColumn<Patient, String> quantityColumn3 = new TableColumn<>("Condition");
        quantityColumn3.setMinWidth(100);
        quantityColumn3.setCellValueFactory(new PropertyValueFactory<>("condition"));
        
        TableColumn<Patient, Character> quantityColumn4 = new TableColumn<>("Gender");
        quantityColumn4.setMinWidth(100);
        quantityColumn4.setCellValueFactory(new PropertyValueFactory<>("gender"));
        

        table = new TableView<>();
        try
        {
            table.setItems(getPatient());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        table.getColumns().addAll(p_id, f_name, l_name,l_ocation,quantityColumn2,quantityColumn3,quantityColumn4);
        borderpane.setRight(table);
            
        }
        else if(event.getSource() == view_profile){
//            

            name.setText("MY PROFILE");
            gridpane.getChildren().clear();
            Label fname = new Label("Name");
            fname.setFont(new Font("Arial",18));
           
            gridpane.add(fname,0,0);
            try {   
            fname1=lookUpDB(3);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                
            lname1=lookUpDB(4);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label fname_value = new Label(fname1+" " +lname1); 
            fname_value.setFont(new Font("Arial",18));
            gridpane.add(fname_value,1,0);              
                     
            Label address = new Label("Address");
            address.setFont(new Font("Arial",18));
            gridpane.add(address,0,1);
            try {
                
            address1=lookUpDB(5);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label address_value = new Label(address1);
            address_value.setFont(new Font("Arial",18));
            gridpane.add(address_value,1,1);
        
            Label phone = new Label("Phone Number");
            phone.setFont(new Font("Arial",18));
            gridpane.add(phone,0,2);
            try {
                
            phone1=lookUpDB(6);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label phone_value = new Label(phone1);
            phone_value.setFont(new Font("Arial",18));
            gridpane.add(phone_value,1,2);
            Label email = new Label("E-Mail");
            email.setFont(new Font("Arial",18));
            gridpane.add(email,0,3);
            try {
                
            email1=lookUpDB(7);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label email_value = new Label(email1);
            email_value.setFont(new Font("Arial",18));
            gridpane.add(email_value,1,3);
            
            Label issurg = new Label("Surgeon");
            issurg.setFont(new Font("Arial",18));
            gridpane.add(issurg,0,4);
            try {
                
            issurgeon1=lookUpDB(8);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value = new Label(issurgeon1);
            issur_value.setFont(new Font("Arial",18));
            gridpane.add(issur_value,1,4);
            Label category2 = new Label("Category");
            category2.setFont(new Font("Arial",18));
            gridpane.add(category2,0,5);
            try {             
            category1=lookUpDB(9);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value1 = new Label(category1);
            issur_value1.setFont(new Font("Arial",18));
            gridpane.add(issur_value1,1,5);
            //
            
            Label category3 = new Label("Duty Start");
            category3.setFont(new Font("Arial",18));
            gridpane.add(category3,0,6);
            try {             
            dutystart1=lookUpDB(10);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value2 = new Label(dutystart1);
            issur_value2.setFont(new Font("Arial",18));
            gridpane.add(issur_value2,1,6);
            
            ///
            Label category4 = new Label("Duty End");
            category4.setFont(new Font("Arial",18));
            gridpane.add(category4,0,7);
            try {             
            dutyend1=lookUpDB(11);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value3 = new Label(dutyend1);
            issur_value3.setFont(new Font("Arial",18));
            gridpane.add(issur_value3,1,7);
            
            ////
            Label category5 = new Label("HOD");
            category5.setFont(new Font("Arial",18));
            gridpane.add(category5,0,8);
            try {             
            ishod1=lookUpDB(12);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value4 = new Label(ishod1);
            issur_value4.setFont(new Font("Arial",18));
            gridpane.add(issur_value4,1,8);
            
            ////
            
            Label category6 = new Label("Department");
            category6.setFont(new Font("Arial",18));
            gridpane.add(category6,0,9);
            try {             
            department1=lookUpDB(13);
            } catch (Exception ex) {
                Logger.getLogger(DHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label issur_value5 = new Label(department1);
            issur_value5.setFont(new Font("Arial",18));
            gridpane.add(issur_value5,1,9);
            gridpane.setHgap(10);
            gridpane.setVgap(10);
            gridpane.setPadding(new Insets(100,0,100,150));
            borderpane.setCenter(gridpane);
           
        }
        else if(event.getSource() == discharge_patient)
        {

            name.setText("DISCHARGE PATIENT");
            gridpane.getChildren().clear();
            Label refer=new Label("Patient ID");
            refer.setFont(new Font("Arial",18));
            gridpane.add(refer,0,0);
            TextField tf1 = new TextField();
//            String t;
//            String s;
            gridpane.add(tf1,1,0);
            Label referd=new Label("Comment");
            referd.setFont(new Font("Arial",18));
            gridpane.add(referd,0,1);
            TextField tf11 = new TextField();
            tf11.setPrefHeight(80);
            gridpane.add(tf11,1,1);
            
            Label referd1=new Label("Disease");
            referd1.setFont(new Font("Arial",18));
            gridpane.add(referd1,0,2);
            TextField tf12 = new TextField();
            tf12.setPrefHeight(80);
            gridpane.add(tf12,1,2);
            Button submit1 = new Button("Discharge");
            submit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                String t=tf1.getText();
                String s=tf11.getText();
                String y=tf12.getText();
                try
                {
                insertdata(t,s,y);
                }
                catch(Exception e)
                {
                    System.out.println("Error 1 : " + e);
                }
            }
            });
            gridpane.add(submit1,0,3);
            gridpane.setHgap(10);
            gridpane.setVgap(10);
            gridpane.setPadding(new Insets(100,0,100,100));
            borderpane.setCenter(gridpane);
            
            
        }
    }
    //
    private void insertdata(String t,String s,String y) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        stmt1= con.createStatement();
        query = "INSERT INTO shs_schema.patienthistory_table VALUES('" + t + "', '" + static_did + "', '" + y + "', '" + s+"');";
        stmt.executeUpdate(query);
        //query = "INSERT into patienthistory_table(pid,did,disease,comment) VALUES ("+t+", "+static_did+", "+s+ ", "+y+");";
        stmt2 = con.createStatement();
        query2 = "SELECT * FROM patientlogincred_table WHERE pid = '"+ t + "' AND did = '"+ static_did+"';";
        rs1 = stmt2.executeQuery(query2);
        if (rs1.next())
        {
            query1 = "DELETE FROM shs_schema.patientlogincred_table WHERE username = '" + rs1.getString("username") + "';";

            stmt1.executeUpdate(query1);
        }
       // query3 = "DELETE FROM shs_schema.patientlogincred_table WHERE username = '" + rs2.getString("username") + "';";
        
        
        
        
        
        Alert alert = new Alert(AlertType.INFORMATION, "Patient Discharged", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
   
        
    }
    
    //
    private void fetchRPatients() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        System.out.println("11");
        query = "SELECT doctordetails_table.department, doctordetails_table.did, doctordetails_table.fname, doctordetails_table.phone,patientlogincred_table.pid, patientlogincred_table.did, patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender, patientlogincred_table.disease, doctordetails_table.fname, doctordetails_table.lname  FROM patientdetails_table, patientlogincred_table, doctordetails_table  WHERE patientlogincred_table.phone=patientdetails_table.phone AND patientlogincred_table.did=doctordetails_table.did;";
        System.out.println("12");
        rs = stmt.executeQuery(query);
        
        
    }

    //
   private String fetch() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection(URL, username, password);
        stmt1 = con1.createStatement();
        query1 = "SELECT * FROM doctordetails_table WHERE doctordetails_table.did = '"+ static_did + "';";
        //query = "SELECT patientlogincred_table.pid, patientlogincred_table.did, patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender, patientlogincred_table.disease, doctordetails_table.fname, doctordetails_table.lname  FROM patientdetails_table, patientlogincred_table, doctordetails_table  WHERE patientlogincred_table.phone=patientdetails_table.phone AND patientlogincred_table.did=doctordetails_table.did;";
        rs1 = stmt1.executeQuery(query1);
        if (rs1.next())
        {
            return rs1.getString("department");
        }
        return "";
    }
   //
    
     private void fetchRPatients1(String data) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
        stmt = con.createStatement();
        System.out.println("Data"+data);
        query = "SELECT patientlogincred_table.pid, patientlogincred_table.did, patientdetails_table.fname, patientdetails_table.lname, patientdetails_table.gender, patientlogincred_table.disease, doctordetails_table.department, doctordetails_table.fname, doctordetails_table.lname  FROM patientdetails_table, patientlogincred_table, doctordetails_table  WHERE patientlogincred_table.phone=patientdetails_table.phone AND patientlogincred_table.did=doctordetails_table.did AND doctordetails_table.department ='"+ data +"';";
        rs=stmt.executeQuery(query);
       
    }
    //
    
    
    
    //1
    private void updatePRPage() throws Exception{
        ArrayList<Label> pnames = new ArrayList();
        ArrayList<Label> dnames = new ArrayList();
        patientIDs = new ArrayList<>();
        dIDs = new ArrayList<>();
        while(rs.next()){
            System.out.println("Dep: "+rs.getString("doctordetails_table.department"));
            
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
            
            gridPane1.add(sno, 0, rcount);
            gridPane1.add(pnames.get(pnames.size()-1), 1, rcount);
            gridPane1.add(gender, 2, rcount);
            gridPane1.add(disease, 3, rcount);
            gridPane1.add(dnames.get(pnames.size()-1), 4, rcount);
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
    
    //
    private boolean validated() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection(URL, username, password);
        stmt1 = con1.createStatement();
        query1 = "SELECT * FROM doctordetails_table WHERE doctordetails_table.did = '"+ static_did +"';";
        rs1 = stmt1.executeQuery(query1);
        System.out.println("ello3");
        
        
        if (rs1.next())
        {
            System.out.println("Yes "+rs1.getString("category"));
        if (rs1.getString("category").equals("JR"))
        {
            System.out.println("False");
            return false;
        }}
        System.out.println("True");
        return true;
        
    }
    //
    //
    private void buildReassignScene(Label pclicked, Label dclicked, String pid, String did) throws SQLException{
        gridPane1.getChildren().clear();
        
        backButton = new Button("Back");
        referButton = new Button("Refer");
      
       
        gridPane1.add(new Label("Patient:"), 1, 0);
        gridPane1.add(new Label("Doctor:"), 1, 1);
        gridPane1.add(new Label("List of doctors:"), 1, 2);
        gridPane1.add(new Label(""), 1, 3);
        gridPane1.add(new Label("Doctor"), 1, 4);
        gridPane1.add(new Label("Dept."), 2, 4);
        gridPane1.add(new Label("Duty Start"), 3, 4);
        gridPane1.add(new Label("Duty End"), 4, 4);
        gridPane1.add(pclicked, 3, 0);
        gridPane1.add(dclicked, 3, 1);
        int row = listDoctors(5, dclicked, did);
        gridPane1.add(backButton, 2, row);
        gridPane1.add(referButton, 3, row);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                gridPane1.getChildren().clear();
                borderpane.getLeft().setDisable(false);
            }
        });
        referButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
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
                        Alert alert = new Alert(AlertType.INFORMATION, "Referred", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                        break;    
                    }
                }
                gridPane1.getChildren().clear();
                borderpane.getLeft().setDisable(false); 
            }
        });
    }
    //
    private void updateRDB(String did, String pid) throws SQLException{
       query = "UPDATE patientlogincred_table SET did = '" + did + "' WHERE pid = '" + pid + "';";
       stmt.executeUpdate(query);
       System.out.println("patientlogincred_table Updated");
       con.close();
   }
    //
    //
    private int listDoctors(int row, Label dclicked, String did) throws SQLException{
    
        doctors = new ToggleGroup();
        ch = new ArrayList<>();
        doctorIDs = new ArrayList<>();
        try
        {
        if (validated())
        {
            query = "SELECT did, fname, lname, department, dutystart, dutyend from doctordetails_table WHERE did != '" + did + "' ORDER BY dutystart, dutyend;";

        }
        else
        {
            query = "SELECT did, fname, lname, department, dutystart, dutyend from doctordetails_table WHERE did != '" + did + "' AND department ='" + fetch() + "' ORDER BY dutystart, dutyend;";

        }
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e);
        }
        //query = "SELECT did, fname, lname, department, dutystart, dutyend from doctordetails_table WHERE did != '" + did + "' AND  ORDER BY dutystart, dutyend;";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            chr = new RadioButton();
            chr.setToggleGroup(doctors);
            ch.add(chr);
            doctorIDs.add(rs.getString("did"));
            gridPane1.add(chr, 0, row);
            gridPane1.add(new Label(rs.getString("fname") + " " + rs.getString("lname")), 1, row);
            gridPane1.add(new Label(rs.getString("department")), 2, row);
            gridPane1.add(new Label(rs.getString("dutystart")), 3, row);
            gridPane1.add(new Label(rs.getString("dutyend")), 4, row);
            row += 1;
        }
        return row;
    }
    //
    
    
    private void lookUpdateDB(int i,String data) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);

        stmt = con.createStatement();
        if(i==1){
            query = "update shs_schema.doctordetails_table set fname= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        }
        else if (i==2)
            
        {   query = "update shs_schema.doctordetails_table set lname= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        }else if(i==3)
        {
            query = "update shs_schema.doctordetails_table set address= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        } else if (i==4)
        {
            query = "update shs_schema.doctordetails_table set phone= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        }else if(i==5)
        {
            query = "update shs_schema.doctordetails_table set email= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        }
        else if(i==6)
        {
            query = "update shs_schema.doctordetails_table set dutystart= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        }else if(i==7)
        {
            query = "update shs_schema.doctordetails_table set password= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
            
        } else if (i==8)
        {
            query = "update shs_schema.doctordetails_table set dutyend= '"+ data +"' where did ='" + static_did+"';";
//            rs = stmt.executeQuery(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
        
            preparedStmt.executeUpdate();
        }
    
    }
    void setDname_static(String dname_static) {
        static_did=dname_static; //To change body of generated methods, choose Tools | Templates.
    }
    private String lookUpDB(int i) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);

        stmt = con.createStatement();
        if(i==1){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("fname") +" " + rs.getString("lname"));
            }
        }
        else if (i==2){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("did"));
            }
        }
        else if (i==3){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("fname"));
            }
        }
        else if (i==4){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("lname"));
            }
        }
        else if (i==5){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("address"));
            }
        }
        else if (i==6){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("phone"));
            }
        }
        else if (i==7){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("email"));
            }
        }
        else if (i==8){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("issurgeon"));
            }
        }
        else if (i==9){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("category"));
            }
        }
        else if (i==10){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("dutystart"));
            }
        }
        else if (i==11){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("dutyend"));
            }
        }
        else if (i==12){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("ishod"));
            }
        }
        else if (i==13){
            query = "SELECT * FROM shs_schema.doctordetails_table WHERE did='" + static_did + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
               return (rs.getString("department"));
            }
        }
        return null;
    }

	public boolean checkDID(String did) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL, username, password);
		stmt = con.createStatement();
		query = "SELECT * FROM doctordetails_table WHERE did = '" + did + "';";
		rs = stmt.executeQuery(query);
		return rs.next();
	    }
}