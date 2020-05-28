package application;

import lab5.BD_Access;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login_window_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField pass_field;

    @FXML
    private TextField login_field;

    @FXML
    private Button login_button;

    @FXML
    void check() throws IOException, ClassNotFoundException, SQLException {
    	
    	if(login_field.getText().matches("postgres")  || pass_field.getText().matches("l")) {
    		BD_Access.access_for_admin();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Connection is created");
            alert.showAndWait();	
    		openNewWindow("menu_window.fxml");
    		
        }
    	else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Error");
            alert.showAndWait();	
    	}

    }
    
    
    @FXML
    void initialize() {
        assert pass_field != null : "fx:id=\"pass_field\" was not injected: check your FXML file 'login_window.fxml'.";
        assert login_field != null : "fx:id=\"login_field\" was not injected: check your FXML file 'login_window.fxml'.";
        assert login_button != null : "fx:id=\"login_button\" was not injected: check your FXML file 'login_window.fxml'.";

        login_button.setOnAction(event -> {try {
    		check();
    		login_button.getScene().getWindow().hide();
    		
            
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


