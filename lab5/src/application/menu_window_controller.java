package application;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lab5.BD_Access;

public class menu_window_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button open_bd;

    @FXML
    private Button delete_bd;

    @FXML
    private Button create_bd;
    
    @FXML
    private Button close_conn;
    

    @FXML
    void initialize() {
       
        create_bd.setOnAction(event -> {
        	try {
                openNewWindow("create_bd_window.fxml");
               
                
        }
        catch
        	(Exception e) {
                e.printStackTrace();
            }


    });  
        
        open_bd.setOnAction(event -> {
        	try {
                openNewWindow("main_table.fxml");
                //open_bd.getScene().getWindow().hide();
               
                
        }
        catch
        	(Exception e) {
                e.printStackTrace();
            }


    });  
        close_conn.setOnAction(event -> {try {
            BD_Access.close();
            close_conn.getScene().getWindow().hide();
            
    }
    catch
    	(Exception e) {
            e.printStackTrace();
        }

    }); 
        delete_bd.setOnAction(event -> {
        	try {
               BD_Access.deleteDB();
               
                
        }
        catch
        	(Exception e) {
                e.printStackTrace();
            }


    });  
    }
    
public void openNewWindow(String window) throws IOException {
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(window));
        try {
        }catch(Exception e) {
            e.printStackTrace();
        }
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
}
