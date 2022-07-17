package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnpaper;

    @FXML
    private Button btnhawkmanager;

    @FXML
    private Button btncustomer;

    @FXML
    private Button btngenerate;

    @FXML
    private Button btnhistory;

    @FXML
    private Button btncollect;

    @FXML
    private Button btncustmanager;

    @FXML
    private Button btnhawkers;

    @FXML
    void doCollect(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("bill_collector/Bill_collect.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btncustmanager.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doCustManager(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer_manager/Customer_form.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btncustmanager.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


    }

    @FXML
    void doCustomer(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customertableview/customer_table.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btncustomer.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doGenerate(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("bill_generator/Bill_generate.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btngenerate.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


   
    }

    @FXML
    void doHawkManager(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer_manager/Customer_form.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btncustmanager.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doHawkers(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("hawkertableview/Hawkertable.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btnhawkers.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doHistory(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("/.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btnhistory.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doPaper(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("paper_master/PaperMaster.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
		//to hide the opened window
			/*
			   Scene scene1=(Scene)btnpaper.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }
    

    @FXML
    void initialize() {
       

    }
}
