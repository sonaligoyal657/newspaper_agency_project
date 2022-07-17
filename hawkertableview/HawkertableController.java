package hawkertableview;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.ConnectToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HawkertableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnshow;

    @FXML
    private TableView<UserBean1> table;
    @SuppressWarnings("unchecked")
	@FXML
    void doShow(ActionEvent event) {
    	
    	
    	TableColumn<UserBean1, String> name=new TableColumn<UserBean1, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean wali field ka name
    	name.setMinWidth(50);
    	
    	TableColumn<UserBean1, String> mobile=new TableColumn<UserBean1, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean wali field ka name
    	mobile.setMinWidth(50);
    	
    	TableColumn<UserBean1, String> address=new TableColumn<UserBean1, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean wali field ka name
    	address.setMinWidth(80);
    	
    	TableColumn<UserBean1, String> area=new TableColumn<UserBean1, String>("Areas");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	area.setMinWidth(150);
    	
    	TableColumn<UserBean1, String> age=new TableColumn<UserBean1, String>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));
    	age.setMinWidth(40);
    	    	
    	TableColumn<UserBean1,Float> salary=new TableColumn<UserBean1, Float>("salary");
    	salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	salary.setMinWidth(50);
    	
    	TableColumn<UserBean1, Date> dob=new TableColumn<UserBean1, Date>("Date of Joining");
    	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    	dob.setMinWidth(40);
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(name,mobile,address,area,age,salary,dob);
    	ObservableList<UserBean1> ary=getAllRecords();
    	
    	table.setItems(null);
    	
    	table.setItems(ary);

    }
    PreparedStatement pstmt;
    Connection con;
   
    	
    	    ObservableList<UserBean1> getAllRecords()
    	    {
    	    	ObservableList<UserBean1> ary=FXCollections.observableArrayList();
    	    	try
    	    	{
    	    	pstmt=con.prepareStatement("select * from hawkers");
    	    	ResultSet rs=pstmt.executeQuery();
    	    	while(rs.next())
    	    		{
    	    			String name=rs.getString("name");
    	    			String mobile=rs.getString("mobile");
    	    			String address=rs.getString("address");
    	    			String area=rs.getString("area");
    	    			String age=rs.getString("age");
    	    			Float salary=rs.getFloat("salary");
    	    			
    	    			Date dob=rs.getDate("dob");
    	    			UserBean1 ref=new UserBean1(name,mobile,address,area,age,salary,dob);
    	    			ary.addAll(ref);
    	    		}
    	    	}
    	    	catch(Exception exp)
    	    	{
    	    	}
    			return ary;
    	    }
    	    
    	    @FXML
    	    void initialize() {
    	    	con=ConnectToDatabase.getConnection();

    }

  
}
