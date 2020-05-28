package application;
import lab5.BD_Access;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab5.BD_Access;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class create_bd_window_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OK;
    
    @FXML
    private TextField name_bd;


    @FXML
    void initialize() {
        assert OK != null : "fx:id=\"OK\" was not injected: check your FXML file 'create_bd_window.fxml'.";
OK.setOnAction(event -> {
    		
    		try {
				BD_Access.createbd(name_bd.getText());
				  OK.getScene().getWindow().hide();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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


