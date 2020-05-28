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

public class Main_window_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admin;

    @FXML
    private Button guest;
  
    @FXML
    private Button close_conn;

    @FXML
    void initialize() {
        assert admin != null : "fx:id=\"admin\" was not injected: check your FXML file 'main_window.fxml'.";
        assert guest != null : "fx:id=\"guest\" was not injected: check your FXML file 'main_window.fxml'.";

    
    admin.setOnAction(event -> {try {
        openNewWindow("login_window.fxml");
        
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
    guest.setOnAction(event -> {try {
    	BD_Access.access_for_guest();
    	if (BD_Access.access_for_guest() != 0) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Connection is created");
        alert.showAndWait();	
        openNewWindow("menu_window.fxml");}
        
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

