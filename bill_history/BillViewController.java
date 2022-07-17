package bill_history;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectToDatabase;
import customertableview.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillViewController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomobid;

    @FXML
    private RadioButton radpaid;

    @FXML
    private RadioButton radunpaid;

    @FXML
    private Button btnshow;

    @FXML
    private TableView<UserBean2> tableview;

    @SuppressWarnings("unchecked")
	@FXML
    void doFetchAll(ActionEvent event) {
    	TableColumn<UserBean2, Integer> billid=new TableColumn<UserBean2, Integer>("Bill id");
    	billid.setCellValueFactory(new PropertyValueFactory<>("billid"));//bean wali field ka name
    	billid.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> cust_mob=new TableColumn<UserBean2, String>("Customer Mobile");
    	cust_mob.setCellValueFactory(new PropertyValueFactory<>("cust_mob"));//bean wali field ka name
    	cust_mob.setMinWidth(50);
    	
    	TableColumn<UserBean2, Integer> noofdays=new TableColumn<UserBean2, Integer>("No.of Days");
    	noofdays.setCellValueFactory(new PropertyValueFactory<>("noofdays"));//bean wali field ka name
    	noofdays.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> dateofbill=new TableColumn<UserBean2, String>("Date of Bill");
    	dateofbill.setCellValueFactory(new PropertyValueFactory<>("dateofbill"));
    	dateofbill.setMinWidth(50);
    	
    	TableColumn<UserBean2,Float> amount=new TableColumn<UserBean2, Float>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(50);
    	    	
    	
    	
    	tableview.getColumns().clear();
    	tableview.getColumns().addAll(billid,cust_mob,noofdays,dateofbill,amount);
    	ObservableList<UserBean2> ary=getAllRecords();
    	
    	tableview.setItems(null);
    	
    	tableview.setItems(ary);
    }

    @FXML
    void doFetchPay(ActionEvent event) {
    	TableColumn<UserBean2, Integer> billid=new TableColumn<UserBean2, Integer>("Bill id");
    	billid.setCellValueFactory(new PropertyValueFactory<>("billid"));//bean wali field ka name
    	billid.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> cust_mob=new TableColumn<UserBean2, String>("Customer Mobile");
    	cust_mob.setCellValueFactory(new PropertyValueFactory<>("cust_mob"));//bean wali field ka name
    	cust_mob.setMinWidth(50);
    	
    	TableColumn<UserBean2, Integer> noofdays=new TableColumn<UserBean2, Integer>("No.of Days");
    	noofdays.setCellValueFactory(new PropertyValueFactory<>("noofdays"));//bean wali field ka name
    	noofdays.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> dateofbill=new TableColumn<UserBean2, String>("Date of Bill");
    	dateofbill.setCellValueFactory(new PropertyValueFactory<>("dateofbill"));
    	dateofbill.setMinWidth(50);
    	
    	TableColumn<UserBean2,Float> amount=new TableColumn<UserBean2, Float>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(50);
    	    	
    	
    	
    	tableview.getColumns().clear();
    	tableview.getColumns().addAll(billid,cust_mob,noofdays,dateofbill,amount);
    	ObservableList<UserBean2> ary=getPaid();
    	
    	tableview.setItems(null);
    	
    	tableview.setItems(ary);

    }

    @FXML
    void doFetchUnpay(ActionEvent event) {
    	TableColumn<UserBean2, Integer> billid=new TableColumn<UserBean2, Integer>("Bill id");
    	billid.setCellValueFactory(new PropertyValueFactory<>("billid"));//bean wali field ka name
    	billid.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> cust_mob=new TableColumn<UserBean2, String>("Customer Mobile");
    	cust_mob.setCellValueFactory(new PropertyValueFactory<>("cust_mob"));//bean wali field ka name
    	cust_mob.setMinWidth(50);
    	
    	TableColumn<UserBean2, Integer> noofdays=new TableColumn<UserBean2, Integer>("No.of Days");
    	noofdays.setCellValueFactory(new PropertyValueFactory<>("noofdays"));//bean wali field ka name
    	noofdays.setMinWidth(50);
    	
    	TableColumn<UserBean2, String> dateofbill=new TableColumn<UserBean2, String>("Date of Bill");
    	dateofbill.setCellValueFactory(new PropertyValueFactory<>("dateofbill"));
    	dateofbill.setMinWidth(50);
    	
    	TableColumn<UserBean2,Float> amount=new TableColumn<UserBean2, Float>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(50);
    	    	
    	
    	
    	tableview.getColumns().clear();
    	tableview.getColumns().addAll(billid,cust_mob,noofdays,dateofbill,amount);
    	ObservableList<UserBean2> ary=getUnpaid();
    	
    	tableview.setItems(null);
    	
    	tableview.setItems(ary);

    }
    ObservableList<UserBean2> getAllRecords()
    {
    	ObservableList<UserBean2> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from billing");
    	ResultSet rs=pstmt.executeQuery();
    	while(rs.next())
    		{
    			int billid=rs.getInt("billid");
    			String cust_mob=rs.getString("cust_mob");
    			int noofdays=rs.getInt("noofdays");
    			String dateofbill=rs.getString("dateofbill");
    			float amount=rs.getFloat("amount");
    			
    			UserBean2 ref=new UserBean2(billid,cust_mob,noofdays,dateofbill,amount);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    	}
		return ary;
    }
    ObservableList<UserBean2> getPaid()
    {
    	ObservableList<UserBean2> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from billing");
    	ResultSet rs=pstmt.executeQuery();
    	while(rs.next())
    		{
    			int billid=rs.getInt("billid");
    			String cust_mob=rs.getString("cust_mob");
    			int noofdays=rs.getInt("noofdays");
    			String dateofbill=rs.getString("dateofbill");
    			float amount=rs.getFloat("amount");
    			
    			UserBean2 ref=new UserBean2(billid,cust_mob,noofdays,dateofbill,amount);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    	}
		return ary;
    }
    ObservableList<UserBean2> getUnpaid()
    {
    	ObservableList<UserBean2> ary=FXCollections.observableArrayList();
    	int status=0;
    	try
    	{
    	pstmt=con.prepareStatement("select * from billing where status=? and cust_mob like ?");
    	if(radpaid.isSelected())
    	{
    		status=1;
    	}
    	else
    		status=0;
    	pstmt.setInt(1,status);
    	pstmt.setString(1,"%"+combomobid.getSelectionModel().getSelectedItem()+"%");
    	ResultSet rs=pstmt.executeQuery();
    	while(rs.next())
    		{
    			int billid=rs.getInt("billid");
    			String cust_mob=rs.getString("cust_mob");
    			int noofdays=rs.getInt("noofdays");
    			String dateofbill=rs.getString("dateofbill");
    			float amount=rs.getFloat("amount");
    			
    			UserBean2 ref=new UserBean2(billid,cust_mob,noofdays,dateofbill,amount);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    	}
		return ary;
    }
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() {
    	con=ConnectToDatabase.getConnection();
    	try {
			pstmt=con.prepareStatement("select cust_mob from billing");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("cust_mob");
				combomobid.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
