package customertableview;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class customer_tableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboareas;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private Button btnarea;

    @FXML
    private Button btnpaper;

    @FXML
    private Button btnshow;

    @FXML
    private TableView<UserBean> table;

    @SuppressWarnings("unchecked")
	@FXML
    void doFetchArea(ActionEvent event) {
    	TableColumn<UserBean, String> mob=new TableColumn<UserBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//bean wali field ka name
    	mob.setMinWidth(50);
    	
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean wali field ka name
    	name.setMinWidth(50);
    	
    	TableColumn<UserBean, String> address=new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean wali field ka name
    	address.setMinWidth(50);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Papers");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(50);
    	
    	TableColumn<UserBean, String> price=new TableColumn<UserBean, String>("Prices");
    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	price.setMinWidth(50);
    	    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(50);
    	
    	TableColumn<UserBean, Date> curdate=new TableColumn<UserBean, Date>("Date of Joining");
    	curdate.setCellValueFactory(new PropertyValueFactory<>("curdate"));
    	curdate.setMinWidth(50);
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mob,name,address,paper,price,hawker,curdate);
    	ObservableList<UserBean> ary=getAreas();
    	
    	table.setItems(null);
    	
    	table.setItems(ary);


    }

  
	@FXML
    void doFetchPaper(ActionEvent event) {
		TableColumn<UserBean, String> mob=new TableColumn<UserBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//bean wali field ka name
    	mob.setMinWidth(50);
    	
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean wali field ka name
    	name.setMinWidth(50);
    	
    	TableColumn<UserBean, String> address=new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean wali field ka name
    	address.setMinWidth(50);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Papers");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(50);
    	
    	TableColumn<UserBean, String> price=new TableColumn<UserBean, String>("Prices");
    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	price.setMinWidth(50);
    	    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(50);
    	
    	TableColumn<UserBean, Date> curdate=new TableColumn<UserBean, Date>("Date of Joining");
    	curdate.setCellValueFactory(new PropertyValueFactory<>("curdate"));
    	curdate.setMinWidth(50);
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mob,name,address,paper,price,hawker,curdate);
    	ObservableList<UserBean> ary=getPapers();
    	
    	table.setItems(null);
    	
    	table.setItems(ary);

    }

    @FXML
    void doPaper(ActionEvent event) {

    }

    @SuppressWarnings("unchecked")
	@FXML
    void doShow(ActionEvent event) {
    	TableColumn<UserBean, String> mob=new TableColumn<UserBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//bean wali field ka name
    	mob.setMinWidth(50);
    	
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean wali field ka name
    	name.setMinWidth(50);
    	
    	TableColumn<UserBean, String> address=new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean wali field ka name
    	address.setMinWidth(50);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Papers");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(50);
    	
    	TableColumn<UserBean, String> price=new TableColumn<UserBean, String>("Prices");
    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	price.setMinWidth(50);
    	    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(50);
    	
    	TableColumn<UserBean, Date> curdate=new TableColumn<UserBean, Date>("Date of Joining");
    	curdate.setCellValueFactory(new PropertyValueFactory<>("curdate"));
    	curdate.setMinWidth(50);
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mob,name,address,paper,price,hawker,curdate);
    	ObservableList<UserBean> ary=getAllRecords();
    	
    	table.setItems(null);
    	
    	table.setItems(ary);

    }

    @FXML
    void doarea(ActionEvent event) {

    }
    PreparedStatement pstmt;
    Connection con;
    ObservableList<UserBean> getAllRecords()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from customers");
    	ResultSet rs=pstmt.executeQuery();
    	while(rs.next())
    		{
    			String mob=rs.getString("mob");
    			String name=rs.getString("name");
    			String address=rs.getString("address");
    			String paper=rs.getString("paper");
    			String price=rs.getString("price");
    			String hawker=rs.getString("hawker");
    			Date curdate=rs.getDate("curdate");
    			UserBean ref=new UserBean(mob,name,address,paper,price,hawker,curdate);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    	}
		return ary;
    }
    ObservableList<UserBean> getAreas(){
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try
    	{
    		pstmt=con.prepareStatement("select * from customers where area like ?");
    		pstmt.setString(1,"%"+comboareas.getSelectionModel().getSelectedItem()+"%");
    		ResultSet rs=pstmt.executeQuery();
        	while(rs.next())
        		{
        		String mob=rs.getString("mob");
    			String name=rs.getString("name");
    			String address=rs.getString("address");
    			String paper=rs.getString("paper");
    			String price=rs.getString("price");
    			String hawker=rs.getString("hawker");
    			Date curdate=rs.getDate("curdate");
    			UserBean ref=new UserBean(mob,name,address,paper,price,hawker,curdate);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exc) {
    	}return ary;
    	}
    	 ObservableList<UserBean> getPapers(){
    	    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	    	try
    	    	{
    	    		pstmt=con.prepareStatement("select * from customers where paper like ?");
    	    		pstmt.setString(1,"%"+combopaper.getSelectionModel().getSelectedItem()+"%");
    	    		ResultSet rs=pstmt.executeQuery();
    	        	while(rs.next())
    	        		{
    	        		String mob=rs.getString("mob");
    	    			String name=rs.getString("name");
    	    			String address=rs.getString("address");
    	    			String paper=rs.getString("paper");
    	    			String price=rs.getString("price");
    	    			String hawker=rs.getString("hawker");
    	    			Date curdate=rs.getDate("curdate");
    	    			UserBean ref=new UserBean(mob,name,address,paper,price,hawker,curdate);
    	    			ary.add(ref);
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
    	try {
			pstmt=con.prepareStatement("select distinct paper from papers");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("paper");
				combopaper.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			pstmt=con.prepareStatement("select distinct area from customers");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("area");
				comboareas.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }
}

