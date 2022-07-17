package bill_generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import connection.ConnectToDatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Bill_generateController {

    @FXML
    private ComboBox<String> combomob;

    @FXML
    private Button btnget;

    @FXML
    private ListView<String> lstpaper;

    @FXML
    private ListView<String> lstprice;

    @FXML
    private Button btnbill;
    
    @FXML
    private TextField txtBill;

    @FXML
    private TextField txtdays;

    @FXML
    private Button btnsave;

    LocalDate ldoj;
    @FXML
    void doBill(ActionEvent event) {
    	float bill=0;
    	ObservableList<String> lst=lstprice.getItems();
    	for(String p:lst)
    	{
    		bill=bill+Float.parseFloat(p);
    	}
		Duration d=Duration.between(ldoj.atStartOfDay(),LocalDate.now().atStartOfDay());
		long noofdays=d.toDays();
		
		bill=bill*noofdays;
		txtBill.setText(String.valueOf(bill));
		txtdays.setText(String.valueOf(noofdays));
		try{
			pstmt=con.prepareStatement("update customers set curdate=DATE_ADD(CURRENT_DATE,INTERVAL 1 DAY) where mobile=?");
		pstmt.setString(1,combomob.getEditor().getText());
		pstmt.executeUpdate();
		}
		catch(Exception exp)
		{
			
		}
	
    	
    }

    @FXML
    void doGet(ActionEvent event) {
    	lstpaper.getItems().clear();
    	lstprice.getItems().clear();
    	txtBill.setText("");
    	txtdays.setText("");
    	try {
			pstmt=con.prepareStatement("select * from customers where mob=?");
			pstmt.setString(1,combomob.getEditor().getText());
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				java.sql.Date doj=table.getDate("curdate");
				ldoj=doj.toLocalDate();
				String []papers=table.getString("paper").split(",");
				lstpaper.getItems().addAll(papers);
				
				String []prices=table.getString("price").split(",");
				lstprice.getItems().addAll(prices);
				
				
						
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doSave(ActionEvent event) {
    	try {
			pstmt=con.prepareStatement("insert into billing(cust_mob,noofdays,dateofbill,amount,status) values(?,?,?,?,'0')");
			pstmt.setString(1,combomob.getEditor().getText());
			pstmt.setInt(2,Integer.parseInt(txtdays.getText()));
			pstmt.setString(3,ldoj.toString());
				pstmt.setFloat(4, Float.parseFloat(txtBill.getText()));
				pstmt.executeUpdate();
				
				 Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setContentText("Record Saved");
					alert.show();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize()
    {
    	 con=ConnectToDatabase.getConnection();
    	 try {
 			pstmt=con.prepareStatement("select mob from customers");
 			ResultSet table=pstmt.executeQuery();
 			while(table.next())
 			{
 				String titlee=table.getString("mob");
 				combomob.getItems().addAll(titlee);
 				
 			}
    	 }
    	 catch(Exception exc)
    	 {
    	 }
    	 }
    }