package lab5;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;




public class BD_Access {
	    static Connection con1=null;

	    static Connection con2=null;
	    static Connection con=null;
	    static Statement statement = null;
	    static  String db_name;
	    static ResultSet rs1=null;
	    static CallableStatement cstat1 = null;
		private static final String PATH = "src/database_name.txt";
		
		public static void access_for_admin() throws ClassNotFoundException, SQLException {
		
	         
		String db_url = "jdbc:postgresql://127.0.0.1:5432/",
				
				username = "postgres", userPass = "l";
		
		Class.forName("org.postgresql.Driver");
		
		con1 = DriverManager.getConnection(db_url,username,userPass);	
		con = con1;	
       
       
}
		public static Integer access_for_guest() throws IOException   {
			          
				if (Files.exists(Paths.get(PATH))) {
					BufferedReader reader = new BufferedReader(new FileReader(PATH));
					String currentLine;
		        
						while((currentLine = reader.readLine()) != null) {
						    if (!currentLine.trim().isEmpty()) {
						        db_name = currentLine;
						
}
						}
					
				 
				
				reader.close(); }
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Error");
		            alert.setContentText("Error");
		            alert.showAndWait();	
				
				}
				
				String db_url = "jdbc:postgresql://127.0.0.1:5432/"+ db_name,
						
						username = "guest1", userPass = "l";
				
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
				con2 = DriverManager.getConnection(db_url,username,userPass);
				con = con2;
			} catch (SQLException e) {
				Integer flag = 0;
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Error");
	            alert.setContentText("Error");
	            alert.showAndWait();	
	            return flag;
			}
			return 1;
		}
		
		
		public static void init() throws SQLException {
			Statement statement = null;
			
			//создание таблицы
			statement = con1.createStatement();
			String SQL = "create or replace function createtablePhones ()\r\n"+ 
					"returns void\r\n"+
					"as\r\n"+
						"$$\r\n"+
						"begin\r\n"+
						"create table phone (id int, brand text,price int);\r\n"+
						"return;\r\n"+
						"end\r\n"+
						"$$ language plpgsql";
					

			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
			
			//вывод всех записей
			statement = con1.createStatement();
			SQL = "CREATE OR REPLACE FUNCTION showall1 ()\r\n" + 
					"RETURNS table(id int,brand text,price int) AS $$\r\n" + 
					"\r\n" + 
					"BEGIN\r\n" + 
					"return query\r\n" + 
					"SELECT * FROM phone;\r\n" + 
					"return;\r\n" + 
					"END\r\n" + 
					"$$ LANGUAGE plpgsql";
			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
			
            //очистка записей таблицы
			statement = con1.createStatement();
			SQL = "create or replace function clearPhones ()\r\n" +
			"returns void\r\n" +
			"as\r\n" +
			"$$\r\n" +
			"begin\r\n" +
			"TRUNCATE phone;\r\n" +
			"return;\r\n"+
			"end\r\n" +
			"$$ language plpgsql" ;

			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
            
			//добавление записей
			statement = con1.createStatement();
			SQL = "create or replace function insertPhone (id int, brand text,price int)\r\n" +
			"returns void\r\n" +
			"as\r\n" +
			"$$\r\n" +
			"begin\r\n" +
			"insert into phone values (id, brand,price);\r\n" +
			"end\r\n" +
			"$$ language plpgsql" ;

			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
			
			//удаление записи
			statement = con1.createStatement();
			SQL ="CREATE OR REPLACE FUNCTION deletephone(givenbrand text)\r\n" +
					"RETURNS void\r\n"+
					"AS\r\n" +
					"$$\r\n" +
					"BEGIN\r\n" +
					"DELETE FROM phone WHERE brand=givenbrand;\r\n" +
                     "return;\r\n"+
					"END\r\n" +
					"$$ LANGUAGE plpgsql";
			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
			
			//обновление
			statement = con1.createStatement();
			SQL = "CREATE OR REPLACE FUNCTION updateprices(givenbrand text,givenprice int)\r\n" +
					"RETURNS void\r\n"+
					"AS $$\r\n" +
			"BEGIN\r\n" +
			"UPDATE phone SET price=givenprice WHERE brand=givenbrand;\r\n" +
			 "return;\r\n"+
			"END\r\n" +
			"$$ LANGUAGE plpgsql";
			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}
			
			//поиск
			statement = con1.createStatement();
			SQL = "CREATE OR REPLACE FUNCTION get_brand1(givenbrand text)\r\n"+
			"RETURNS table(id int,brand text,price int) AS $$\r\n"+

			"BEGIN\r\n"+
			"return query\r\n"+
			"SELECT *\r\n"+
			"FROM phone\r\n"+
			"WHERE phone.brand=givenbrand;\r\n"+

			"return;\r\n"+
			"END\r\n"+
			"$$ LANGUAGE plpgsql";
			statement.executeUpdate(SQL);
			
			statement = con1.createStatement();
			SQL = "CREATE OR REPLACE FUNCTION granted()\r\n"+
			"RETURNS void AS $$\r\n"+

			"BEGIN\r\n"+
			
			"Grant SELECT ON \r\n"+
			" phone\r\n"+
			"to guest1;\r\n"+

			"return;\r\n"+
			"END\r\n"+
			"$$ LANGUAGE plpgsql";
			statement.executeUpdate(SQL);
			if(statement!=null){
			statement.close();
			}





			}
		public static void createbd(String str) throws SQLException, ClassNotFoundException  {
			try {
				if (Files.exists(Paths.get(PATH))) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Error");
		            alert.setContentText("Error!");
		            alert.showAndWait();
				  }
				  
				  else {
						  Files.createFile(Paths.get(PATH));			  
				  
						  BufferedWriter writer = new BufferedWriter(new PrintWriter(new FileOutputStream(PATH, true)));
			                
			                writer.write(stringing(str));
			                writer.flush();
			                writer.close();
						  Statement statement = null;
						  statement = con1.createStatement();
						  String SQL = "CREATE DATABASE " + str;
						  statement.executeUpdate(SQL);
						  String db_url = "jdbc:postgresql://127.0.0.1:5432/" + str,
									
									username = "postgres", userPass = "l";
							
							Class.forName("org.postgresql.Driver");
							
							con1 = DriverManager.getConnection(db_url,username,userPass);
							con = con1;
							init();
							
						   cstat1= con.prepareCall("{ call createtablePhones()}");
						   cstat1.executeQuery();
						   cstat1.close();
						   cstat1 = con.prepareCall("{ call granted()}");
						   cstat1.executeQuery();
							
							
						  Alert alert = new Alert(Alert.AlertType.INFORMATION);
			                alert.setTitle("Success");
			                alert.setHeaderText("Success");
			                alert.setContentText("База данных создана");
			                alert.showAndWait();
						 
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (PSQLException e) {
				// TODO Auto-generated catch block
				
			}
	        finally {
	            if(statement!=null){
	                statement.close();
	            }
	            
	            
	        }   
		}
		static String stringing(String str) {
	    	 StringBuilder sb = new StringBuilder();
	    	
	         sb.append(str);
	         return sb.toString();
	     }
		public static void deleteDB() throws SQLException, IOException, ClassNotFoundException {
	        try {
	        	BufferedReader reader = new BufferedReader(new FileReader(PATH));
	        	String db_url = "jdbc:postgresql://127.0.0.1:5432/",
						
						username = "postgres", userPass = "l";
				
				Class.forName("org.postgresql.Driver");
				con.close();
				con1 = DriverManager.getConnection(db_url,username,userPass);
				con = con1;
	            if (Files.exists(Paths.get(PATH))) {
	                
	                Statement statement = null;
	                String currentLine;
		            while((currentLine = reader.readLine()) != null) {
		                if (!currentLine.trim().isEmpty()) {
		                    db_name = currentLine;
					
				}
		            }reader.close();
					  statement = con1.createStatement();
					  String SQL = "DROP DATABASE " + db_name;
					  statement.executeUpdate(SQL);
					  File file = new File(PATH);

			          file.delete();
                     
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Success");
	                alert.setHeaderText("Success");
	                alert.setContentText("База данных удалена");
	                alert.showAndWait();
	            } else {
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("Error");
	                alert.setHeaderText("Error");
	                alert.setContentText("Невозможно произвести операцию удаления");
	                alert.showAndWait();
	            }
	        } catch (PSQLException e) {
	        	/*Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Error");
	            alert.setContentText("Недостаточно прав!");
	            alert.showAndWait();*/
	        	e.printStackTrace();
	        }
		}public static void close() throws SQLException {
		
            if(statement!=null){
                statement.close();
            }

            if(cstat1!=null){
                cstat1.close();
            }
            if(con==con1){
                con1.close();
            }
            if(con==con2){
                con2.close();
            }
            
    }
		
		
		public static void add(Integer vendor_code, String brand, Integer price) throws SQLException {
			
			CallableStatement cstat=null;
			cstat1=con.prepareCall("{call insertPhone(?,?,?)}");
			cstat1.setInt(1,vendor_code);
			cstat1.setString(2,brand);
			cstat1.setInt(3,price);
		    cstat1.executeQuery();
			
		}
	
			
				
public static void search(String brand, TextArea area) throws SQLException, ClassNotFoundException{
	
	CallableStatement cstat1 = null;
	System.out.println(brand);
	String a="";
	cstat1 = con.prepareCall("{ call get_brand1(?)}");
	cstat1.setString(1,brand);
	rs1 = cstat1.executeQuery();
	
	while(rs1.next()) {
		a=a+ rs1.getInt(1) + "                " + rs1.getString(2)+"   "+rs1.getInt(3)+"\n";
		}
	area.setText(a);
	}
	public static void show(TextArea area) throws SQLException, ClassNotFoundException{
				CallableStatement cstat1 = null;
		cstat1 = con.prepareCall("{ call showall1() }");
		String a = "";
		rs1 = cstat1.executeQuery();
		while(rs1.next()) {
			a=a+ rs1.getInt(1) + "                " + rs1.getString(2)+"   "+rs1.getInt(3)+"\n";
			}
		area.setText(a);
		
		
		
	}
	
	public static void clearPhones() throws SQLException {
		
		cstat1=con.prepareCall("{call clearPhones()}");
	    cstat1.executeQuery();
		
		
		
		
	}
public static void delete_phone(String brand) throws SQLException {
		
		cstat1=con.prepareCall("{call deletephone(?)}");
		cstat1.setString(1,brand);
	    cstat1.executeQuery();
		
		
		
		
	}

public static void update_price(String brand,Integer price) throws SQLException {
	
	cstat1=con.prepareCall("{call updateprices(?,?)}");
	cstat1.setString(1,brand);
	cstat1.setInt(2,price);
    cstat1.executeQuery();
   
	
	
	
	
}
	
	
	
}
	
			
		

	//}

