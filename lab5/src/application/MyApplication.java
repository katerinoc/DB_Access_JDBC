package application;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
	    primaryStage.setTitle("My application");
		primaryStage.setScene(new Scene(root,700,400));
		primaryStage.show();
	}
	public static void main(String[] args) {launch(args);}

}



