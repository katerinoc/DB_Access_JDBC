package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab5.BD_Access;

public class create_table_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField vendor_code;

    @FXML
    private TextField price;

    @FXML
    private TextField brand;
    
    @FXML
    private Button OK;

    @FXML
    void initialize() {
        assert vendor_code != null : "fx:id=\"vendor_code\" was not injected: check your FXML file 'create_table_window.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'create_table_window.fxml'.";
        assert brand != null : "fx:id=\"brand\" was not injected: check your FXML file 'create_table_window.fxml'.";

        
     /*   OK.setOnAction(event -> {try {
        	
       BD_Access.add(Integer.parseInt(vendor_code.getText()),brand.getText(),Integer.parseInt(price.getText()),text);
       openNewWindow("main_table.fxml");
}
catch
	(Exception e) {
        e.printStackTrace();
    }

}); */
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

