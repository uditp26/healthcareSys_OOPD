package smarthealthcaresystem;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class DHomePage {
   
    
    Parent rpage;
    Stage window;
    Scene homepageScene;
    Scene regpageScene;
    public static TableView<Patient> table;
    public void startReg(Stage window) throws IOException {
            rpage = FXMLLoader.load(getClass().getResource("DHomePageView.fxml"));
            regpageScene = new Scene(rpage, window.getWidth(), window.getHeight());
            window.setScene(regpageScene);
            window.show();
        }
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        
        Parent root = FXMLLoader.load(getClass().getResource("DHomePageView.fxml"));
        homepageScene = new Scene(root, window.getWidth(), window.getHeight());
        window.setTitle("Smart Healthcare System");
        window.setScene(homepageScene);
        window.show();
    }
}
