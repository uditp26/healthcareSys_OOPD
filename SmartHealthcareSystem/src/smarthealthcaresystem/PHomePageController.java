/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    Stage window;
    RegPage2Controller controller;
    
    @FXML
    Label welcome;
    
    @FXML
    GridPane gridpane;
    
    @FXML
    Label name;
    
    @FXML
    Button edit_profile;
    
    @FXML
    Button search_doctor;
    
    @FXML
    Button view_doctor;
    
    @FXML
    Button choose_doctor;
    
    @FXML
    Button request_appointment;
    
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
        controller = new RegPage2Controller();
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(50);
        gridpane.getColumnConstraints().add(cc);
        view_doctor.setVisible(false);
        choose_doctor.setVisible(false);
        request_appointment.setVisible(false);
        welcome.setText("Welcome " + controller.getFname_static());
    }    
    
    public void onClickListener(ActionEvent event) throws Exception{
        if(event.getSource() == edit_profile){
            view_doctor.setVisible(false);
            choose_doctor.setVisible(false);
            name.setText("EDIT PROFILE");
            gridpane.getChildren().clear();
            Label fname = new Label("First Name");
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
            Label password = new Label("Password");
            password.setFont(new Font("Arial",18));
            gridpane.add(password,0,5);
            PasswordField tf6 = new PasswordField();
            //TextField tf6 = new TextField();
            gridpane.add(tf6,1,5);
            Button submit = new Button("Submit");
            gridpane.add(submit,1,6);
        }
        else if(event.getSource() == logout){
            window = (Stage) logout.getScene().getWindow();
            shs.start(window);
        }
        else if(event.getSource() == search_doctor){
            view_doctor.setVisible(false);
            choose_doctor.setVisible(false);
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
            MenuButton id4 = new MenuButton("Enter Doctor Specialization");
            id4.getItems().addAll(new MenuItem("Orthopedic"), new MenuItem("General Physician"), new MenuItem("Cardiologist"), new MenuItem("Endocrinologists"));
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
                    }
                    else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Name")){
                        //TextField id = new TextField();
                        //id.setPromptText("Enter Doctor Name");
                        gridpane.getChildren().remove(id1);
                        gridpane.getChildren().remove(id3);
                        gridpane.getChildren().remove(id4);
                        gridpane.add(id2,1,1);
                    }
                    else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Address")){
                        //TextField id = new TextField();
                        //id.setPromptText("Enter Doctor Address");
                        gridpane.getChildren().remove(id1);
                        gridpane.getChildren().remove(id2);
                        gridpane.getChildren().remove(id4);
                        gridpane.add(id3,1,2);
                    }
                    else if(group.getSelectedToggle().getUserData().toString().equals("Doctor Specialization")){
                        //TextField id = new TextField();
                        //id.setPromptText("Enter Doctor Specialization");
                        gridpane.getChildren().remove(id1);
                        gridpane.getChildren().remove(id2);
                        gridpane.getChildren().remove(id3);
                        gridpane.add(id4,1,3);
                    }
                }
            });
            Label doctor1 = new Label("Dr. Lewis Erickson");
            Label doctor2 = new Label("Dr. Lewis Ford");
            Label doctor3 = new Label("Dr. Lewis Tesla");
            submit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gridpane.getChildren().clear();
                    Text doctor_name = new Text();
                    doctor_name.setText("DOCTOR NAME");
                    doctor_name.setUnderline(true);
                    doctor_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    gridpane.add(doctor_name,0,0);
                    Text doctor_spec = new Text();
                    doctor_spec.setText("DOCTOR SPECIALIZATION");
                    doctor_spec.setUnderline(true);
                    doctor_spec.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    gridpane.add(doctor_spec,1,0);
                    doctor1.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctor1,0,1);
                    Label doctorspec1 = new Label("Orthodpedic");
                    doctorspec1.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctorspec1,1,1);
                    doctor2.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctor2,0,2);
                    Label doctorspec2 = new Label("General Physician");
                    doctorspec2.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctorspec2,1,2);
                    doctor3.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctor3,0,3);
                    Label doctorspec3 = new Label("Cardiologist");
                    doctorspec3.setFont(new Font("Lucida sans",16));
                    gridpane.add(doctorspec3,1,3);
                }
            });
            doctor1.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    view_doctor.setVisible(true);
                    choose_doctor.setVisible(true);
                }
            });
            doctor1.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    doctor1.setStyle("-fx-background-color:#dae7f3;");
                }
            });
            doctor1.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    doctor1.setStyle("-fx-background-color:transparent;");
                }   
            });
        }
        else if(event.getSource() == view_doctor){
            //name.setText("");
            gridpane.getChildren().clear(); 
            name.setText("DOCTOR PROFILE");
            //gridpane.getChildren().clear();
            Label df_name = new Label("First Name");
            df_name.setFont(new Font("Arial",18));
            gridpane.add(df_name,0,0);
            Label df_name_value = new Label("Dr. Lewis");
            df_name_value.setFont(new Font("Lucida sans",16));
            gridpane.add(df_name_value,1,0);
            Label dl_name = new Label("Last Name");
            dl_name.setFont(new Font("Arial",18));
            gridpane.add(dl_name,0,1);
            Label dl_name_value = new Label("Erickson");
            dl_name_value.setFont(new Font("Lucida sans",16));
            gridpane.add(dl_name_value,1,1);
            Label daddress = new Label("Address");
            daddress.setFont(new Font("Arial",18));
            gridpane.add(daddress,0,2);
            Label daddress_value = new Label("Okhla Industrial Estate");
            daddress_value.setFont(new Font("Lucida sans",16));
            gridpane.add(daddress_value,1,2);
            Label dphone = new Label("Phone");
            dphone.setFont(new Font("Arial",18));
            gridpane.add(dphone,0,3);
            Label dphone_value = new Label("9999999991");
            dphone_value.setFont(new Font("Lucida sans",16));
            gridpane.add(dphone_value,1,3);
            Label demail = new Label("Email");
            demail.setFont(new Font("Arial",18));
            gridpane.add(demail,0,4);
            Label demail_value = new Label("lewiserickson1971@gmail.com");
            demail_value.setFont(new Font("Lucida sans",16));
            gridpane.add(demail_value,1,4);
            Label dvisitingtime = new Label("Visiting time");
            dvisitingtime.setFont(new Font("Arial",18));
            gridpane.add(dvisitingtime,0,5);
            Label dvisitingtime_value = new Label("10:00 am - 11:30 am");
            dvisitingtime_value.setFont(new Font("Lucida sans",16));
            gridpane.add(dvisitingtime_value,1,5); 
            Button back = new Button("Back");
            gridpane.add(back,1,6);
        }
        else if(event.getSource() == choose_doctor){
            name.setText("");
            gridpane.getChildren().clear();
            //CalenderPicker dateTime = new CalenderPicker();
        }
        else if(event.getSource() == request_appointment){
            name.setText("");
            gridpane.getChildren().clear();
        }
        else if(event.getSource() == view_profile){
            view_doctor.setVisible(false);
            choose_doctor.setVisible(false);
            name.setText("MY PROFILE");
            gridpane.getChildren().clear();
            Label fname = new Label("First Name");
            fname.setFont(new Font("Arial",18));
            gridpane.add(fname,0,0);
            Label fname_value = new Label("Ayushmann");
            fname_value.setFont(new Font("Lucida sans",16));         
            gridpane.add(fname_value,1,0);                
            //TextField tf1 = new TextField();
            //gridpane.add(tf1,1,0);
            Label lname = new Label("Last Name");
            lname.setFont(new Font("Arial",18));
            gridpane.add(lname,0,1);
            Label lname_value = new Label("Kaul"); 
            lname_value.setFont(new Font("Lucida sans",16));                                               
            gridpane.add(lname_value,1,1);               
            //TextField tf2 = new TextField();
            //gridpane.add(tf2,1,1);
            Label address = new Label("Address");
            address.setFont(new Font("Arial",18));
            gridpane.add(address,0,2);
            Label address_value = new Label("Vaishali Ghaziabad");  
            address_value.setFont(new Font("Lucida sans",16)); 
            gridpane.add(address_value,1,2);                         
            //TextField tf3 = new TextField();
            //gridpane.add(tf3,1,2);
            Label phone = new Label("Phone Number");
            phone.setFont(new Font("Arial",18));
            gridpane.add(phone,0,3);
            Label phone_value = new Label("9910411681");  
            phone_value.setFont(new Font("Lucida sans",16)); 
            gridpane.add(phone_value,1,3);                    
            //TextField tf4 = new TextField();
            //gridpane.add(tf4,1,3);
            Label email = new Label("E-Mail");
            email.setFont(new Font("Arial",18));
            gridpane.add(email,0,4);
            Label email_value = new Label("kaul.ayushmaan@gmail.com");
            email_value.setFont(new Font("Lucida sans",16)); 
            gridpane.add(email_value,1,4);
        }
    }
}