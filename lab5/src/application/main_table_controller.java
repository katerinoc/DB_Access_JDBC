package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.postgresql.util.PSQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lab5.BD_Access;

public class main_table_controller {

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField add_brand;

	    @FXML
	    private TextField update_brand;

	    @FXML
	    private TextField add_vendor_code;

	    @FXML
	    private TextField add_price;

	    @FXML
	    private TextField update_price;

	    @FXML
	    private Button update;

	    @FXML
	    private Button add_element;

	    @FXML
	    private TextField search_brand;

	    @FXML
	    private Button delete_item;

	    @FXML
	    private TextArea text;

	    @FXML
	    private TextField delete_brand;

	    @FXML
	    private Button search_item;

	    @FXML
	    private Button delete_table;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        
         BD_Access.show(text);
         search_item.setOnAction(event -> {try {
        	BD_Access.search(search_brand.getText(), text);
        	 
}
 catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}catch
	(NumberFormatException e) {
	Alert alert = new Alert(Alert.AlertType.ERROR);
   alert.setTitle("Error");
   alert.setHeaderText("Error");
   alert.setContentText("Поле пусто!");
   alert.showAndWait();
   } 

}); 
         add_element.setOnAction(event -> {try {
         	BD_Access.add(Integer.parseInt(add_vendor_code.getText()),add_brand.getText(),Integer.parseInt(add_price.getText()));
         	BD_Access.show(text);
 }
 
         catch
     	(PSQLException e) {
     	Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Error");
         alert.setContentText("Недостаточно прав!");
         alert.showAndWait();
         } 
         catch
      	(NumberFormatException e) {
      	Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Error");
          alert.setContentText("Поле пусто или неправильный формат!");
          alert.showAndWait();
          } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 }); 
         delete_table.setOnAction(event -> {try {
          	BD_Access.clearPhones(); 
          	BD_Access.show(text);
  }
         catch
      	(PSQLException e) {
      	Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Error");
          alert.setContentText("Недостаточно прав!");
          alert.showAndWait();
          } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

  }); 
         delete_item.setOnAction(event -> {try {
        	 if(delete_brand.getText()!=null) { 	
        	BD_Access.delete_phone(delete_brand.getText()); 
           	BD_Access.show(text);}
           	else {
           	Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Error");
               alert.setContentText("Поле пусто или неправильный формат!");
               alert.showAndWait();
               } 
          
   }
         catch
      	(PSQLException e) {
      	Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Error");
          alert.setContentText("Недостаточно прав!");
          alert.showAndWait();
          } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

   }); 
        update.setOnAction(event -> {try {
            	BD_Access.update_price(update_brand.getText(),Integer.parseInt(update_price.getText())); 
            	BD_Access.show(text);
    }
        catch
     	(PSQLException e) {
     	Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Error");
         alert.setContentText("Недостаточно прав!");
         alert.showAndWait();
         } catch
      	(NumberFormatException e) {
      	Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Error");
          alert.setContentText("Поле пусто или неправильный формат!");
          alert.showAndWait();
          } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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

