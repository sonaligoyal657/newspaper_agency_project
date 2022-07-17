package bill_collector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;

import connection.ConnectToDatabase;
import customertableview.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Bill_collectController {

    @FXML
    private ComboBox<String> combomob;

    @FXML
    private Button btnbill;

    @FXML
    private ListView<String> lstdate;

    @FXML
    private ListView<String> lstamount;

    @FXML
    private TextField txtbill;

    @FXML
    private Button btnpay;

    @FXML
    void doBill(ActionEvent event) {    
    	lstdate.getItems().clear();
    	lstamount.getItems().clear();
    	txtbill.setText("");
    	float sum=0;
    	String mob=combomob.getSelectionModel().getSelectedItem();
    	try {
    		pstmt=con.prepareStatement("select dateofbill,amount from billing where status='0' and cust_mob=?");
    		pstmt.setString(1, mob);
    		ResultSet rs=pstmt.executeQuery();
    		while(rs.next())
    		{
    			lstdate.getItems().addAll(rs.getString("dateofbill"));
    			float p=rs.getFloat("amount");
    			sum+=p;	
    			lstamount.getItems().add(Float.toString(sum));	
    			
    		}
    		txtbill.setText(String.valueOf(sum));
    	}
    	catch(Exception exp){
    	}

    }

    @FXML
    void doPay(ActionEvent event) {
    	try{
			pstmt=con.prepareStatement("update billing set status='1' where  cust_mob=? and status='0'");
		pstmt.setString(1,combomob.getEditor().getText());
		pstmt.executeUpdate();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("Bill Paid");
		alert.show();

		}
		catch(Exception exp)
		{
			
		}
    	
    	

    }
    PreparedStatement pstmt;
    Connection con;
    
    
    @FXML
    void initialize() {
    	con=ConnectToDatabase.getConnection();
    	
    	try {
			pstmt=con.prepareStatement("select cust_mob from billing ");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("cust_mob");
				combomob.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }

}
