package customer_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import connection.ConnectToDatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

public class Customer_formController {

    @FXML
    private TextField txtname;

    @FXML
    private Button btnsearch;

    @FXML
    private TextField txtaddress;

    @FXML
    private ListView<String> lstpapers;

    @FXML
    private ListView<String> lstprices;

    @FXML
    private ComboBox<String> selareas;

    @FXML
    private TextField txthawker;
    @FXML
    private ComboBox<String> combomob;


    @FXML
    private Button btnclr;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndel;

    @FXML
    void doClear(ActionEvent event) {
    	txtaddress.setText("");
    	txtname.setText("");
    	txthawker.setText("");
    	combomob.getEditor().clear();
    	lstpapers.getItems().clear();
    	lstprices.getItems().clear();
    	try {
    		pstmt=con.prepareStatement("select * from papers");
			papername=pstmt.executeQuery();
			while(papername.next())
			{
				lstpapers.getItems().addAll(papername.getString("paper"));
				lstprices.getItems().addAll(papername.getString("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doDel(ActionEvent event) {
    	try {
			pstmt=con.prepareStatement("delete from customers where mobile=?");
			pstmt.setString(1,combomob.getEditor().getText());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doFetch(ActionEvent event) {
    	String name= selareas.getSelectionModel().getSelectedItem();
    	try {
			pstmt=con.prepareStatement("select name from hawkers where area like ?");
			pstmt.setString(1,"%"+name+"%");
			ResultSet hawkerr=pstmt.executeQuery();
			while(hawkerr.next())
			{
				String h=hawkerr.getString("name");
				txthawker.setText(h);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doSave(ActionEvent event) {
    	ObservableList <String> list1=lstpapers.getSelectionModel().getSelectedItems();
    	for(String p:list1)
    	{
    		listpaper=listpaper+p+",";
    	}
    	
    	ObservableList <String> list2=lstprices.getSelectionModel().getSelectedItems();
    	for(String p2:list2)
    	{
    		listprice=listprice+p2+",";
    	}
    	

    	String name= selareas.getSelectionModel().getSelectedItem();
    	try {
			pstmt=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,current_date())");
			pstmt.setString(2,txtname.getText());
			pstmt.setString(1, combomob.getEditor().getText());
			pstmt.setString(3,txtaddress.getText());
			pstmt.setString(4,listpaper);
			pstmt.setString(5, listprice);
			pstmt.setString(6,txthawker.getText());
			pstmt.setString(7, name);
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

	@FXML
    void doSearch(ActionEvent event) {
    	String mob= combomob.getEditor().getText();
    	lstpapers.getItems().clear();
    	lstprices.getItems().clear();
    	try {
			pstmt=con.prepareStatement("select * from customers where mob=?");
			pstmt.setString(1,mob);
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				String name=table.getString("name");
				String address=table.getString("address");
				String hawker=table.getString("hawker");
				String paper=table.getString("paper");
				String price=table.getString("price");
				txtname.setText(name);
				txtaddress.setText(address);
				txthawker.setText(hawker);
				String []a=paper.split(",");
				ArrayList<String> paper1=new ArrayList<String>(Arrays.asList(a));
				for(String x:paper1)
				{
					lstpapers.getItems().add(x);
				}
				String []b =price.split(",");
				ArrayList<String> price1=new ArrayList<String>(Arrays.asList(b));
				for(String y:price1)
				{
					lstprices.getItems().add(y);
				}
			}
			else
			{
			 Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information");
				alert.setContentText("Record Doesn't exist");
				alert.show();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	

    }
    
    PreparedStatement pstmt;    
    Connection con;
    String listpaper="";
    String listprice="";
    ResultSet papername;
    ResultSet areas;
    @FXML
    void initialize() {
        con=ConnectToDatabase.getConnection();
    	
    	
        lstpapers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstprices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
			pstmt=con.prepareStatement("select * from papers");
			papername=pstmt.executeQuery();
			while(papername.next())
			{
				lstpapers.getItems().addAll(papername.getString("paper"));
				lstprices.getItems().addAll(papername.getString("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pstmt=con.prepareStatement("select distinct area from hawkers");
			areas=pstmt.executeQuery();
			while(areas.next())
			{
				String Areas=areas.getString("area");
				String []a=Areas.split(",");
				ArrayList<String> area1=new ArrayList<String>(Arrays.asList(a));
				for(String x:area1)
				{
					selareas.getItems().addAll(x);
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			pstmt=con.prepareStatement("select mob from customers");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("mob");
				combomob.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        
        
        
        
     }
    @FXML
    void selPrice(MouseEvent event) {
    	lstprices.getSelectionModel().clearSelection();
    	ObservableList<Integer> ob=lstpapers.getSelectionModel().getSelectedIndices();
    	for(int i:ob)
    	{
    		lstprices.getSelectionModel().select(i);
    	}

    }


}
