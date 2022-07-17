package Hawkers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import connection.ConnectToDatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class hawker_managerController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgview;

    @FXML
    private Button btnpic;

    @FXML
    private Label lblname;

    @FXML
    private ComboBox<String> comboname;

    @FXML
    private Label lblcontact;

    @FXML
    private TextField txtcontact;

    @FXML
    private Label lblage;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtsalary;

    @FXML
    private Label lbladdress;

    @FXML
    private DatePicker datepick;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ListView<String> listarea;

    @FXML
    private Label lblselect;

    @FXML
    private Button btnnew;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsearch;

    @FXML
    private Button btnselect;
    String filepath;

    @FXML
    void docomboarea(ActionEvent event) {
    	listarea.getItems().removeAll(listarea.getItems());
    	//listfill.getItems().clear();
    	if((comboarea.getSelectionModel().getSelectedIndex())==1){
           	System.out.println("combo Bathinda");
        	ArrayList<String> book=new ArrayList<String>(Arrays.asList("Nai Basti","Ganesha Basti","Vishal Nagar"));
     		listarea.getItems().addAll(book);
     		listarea.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     		
     		
     		
    	}else if((comboarea.getSelectionModel().getSelectedIndex())==2){
    		
        	System.out.println("combo Gidderbaha");
        	ArrayList<String> comp=new ArrayList<String>(Arrays.asList("Factory Road","Sabji Mandi","Near Civil hospital"));
     		listarea.getItems().addAll(comp);
     		listarea.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     		
    	}

    }

    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pstmt=con.prepareStatement("delete from hawkers where name=?");
			pstmt.setString(1, comboname.getEditor().getText());
			
			pstmt.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText("Record Deleted");
				alert.show();
			//lblmsg.setText("Delete"+count);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    @FXML
    void donew(ActionEvent event) {
    	txtage.setText("");
    	txtaddress.setText("");
    	txtsalary.setText("");
    	txtcontact.setText("");
    	lblselect.setText("Selected areas");
    	
    	Image pic=null;
    	File imgselected=new File("");
		
		try {
			pic=new Image(new FileInputStream(imgselected));
			imgview.setImage(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//comboname.setEditable(true);
    }

    @FXML
    void dopic(ActionEvent event) throws FileNotFoundException {
    	FileChooser fc=new FileChooser();
    	File fc1=fc.showOpenDialog(null);
      
    	fc.getExtensionFilters().addAll( 
    			new FileChooser.ExtensionFilter("All Images","."),
    			new FileChooser.ExtensionFilter("Jpg","*.jpg"),
    			new FileChooser.ExtensionFilter("Png","*.png"),
    			new FileChooser.ExtensionFilter(".",".")
    			
    			
    			);
    	URL pic=null;
	try {
		pic =fc1.toURI().toURL();
	} catch (MalformedURLException e) {
		 Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please Upload Image");
			alert.show();
		e.printStackTrace();
	}
    imgview.setImage(new Image(pic.toExternalForm()));
    	filepath= fc1.getAbsolutePath();
    	 System.out.println(filepath);
    	
    }


    @FXML
    void dosave(ActionEvent event) {

    	try {
    		
						pstmt=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?,?,?)");
		
             			pstmt.setString(1, comboname.getEditor().getText());
         				pstmt.setString(2, txtcontact.getText());
         				pstmt.setString(3, txtaddress.getText());
         				pstmt.setString(4, lblselect.getText());
         				pstmt.setString(5, filepath);
         				pstmt.setInt(6, Integer.parseInt(txtage.getText()));
         	            pstmt.setFloat(7, Float.parseFloat(txtsalary.getText()));
         	            LocalDate ldob=datepick.getValue();
         	            java.sql.Date swdatepic=java.sql.Date.valueOf(ldob);
         	            pstmt.setDate(8, swdatepic);
         	            pstmt.executeUpdate();
         	          
         	            Alert alert = new Alert(AlertType.INFORMATION);
         				alert.setTitle("Information");
         				alert.setContentText("Record Saved");
         				alert.show();
             		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void dosearch(ActionEvent event) {
    	String name= comboname.getEditor().getText();
    	System.out.println(name);
    	try {
    		pstmt=con.prepareStatement("select * from hawkers where name=?");
    		pstmt.setString(1, name);
    		
    		ResultSet table=pstmt.executeQuery();
    		boolean jasus=false;
    		while(table.next())
    		{
    			jasus=true;
    			String contact=table.getString("mobile");
    			int age=table.getInt("age");
    			String address=table.getString("address");
    			//String pic=table.getString("pic");
    			float salary=table.getFloat("salary");
    			String area=table.getString("area");
    			txtcontact.setText(contact);
    			txtage.setText(String.valueOf(age));
    			txtaddress.setText(address);
    			txtsalary.setText(String.valueOf(salary));
    			lblselect.setText(area);
    			java.sql.Date dobb=table.getDate("dob");
    			LocalDate ldob=dobb.toLocalDate();
    			datepick.setValue(ldob);
    			
    			File imgselected=new File(table.getString("pic"));
    			System.out.println(imgselected);
				Image picc=null;
				try {
					picc=new Image(new FileInputStream(imgselected));
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
                imgview.setImage(picc);
                filepath=imgselected.getAbsolutePath();
                System.out.println("j"+imgselected);
    		}
    		if(jasus==false) {
    			//lblmsg.setText("entered wrong record");
    			System.out.println("no record exist");
    			 Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Information");
    				alert.setContentText("No Record Exist");
    				alert.show();
    			
    		}
    		else
    		{
    			//lblmsg.setText("Searched");
    			System.out.println("searched");
    		}
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    }

    @FXML
    void doselect(ActionEvent event) {
    	String all="";
    	String all2="";
    	  ObservableList<String>selItems=listarea.getSelectionModel().getSelectedItems();
    	  ObservableList<Integer>selIndx=listarea.getSelectionModel().getSelectedIndices();
    	  
    	  for (String string : selItems)
    	  {
    		  
    		  all=all+string+",";
    		  System.out.println("selected item :"+string);
    	  }
    	  for (int indx : selIndx)
    	  {
    		  all2=all2+indx+",";
    	  }
    	  lblselect.setText(all);
    	 
    }

    @FXML
    void doupdate(ActionEvent event) {
    	
    	try {
			
    		pstmt=con.prepareStatement("update hawkers set mobile=? , address=?,area=?,pic=?,age=?,salary=?,dob=? where name=?");
    		pstmt.setString(8, comboname.getEditor().getText());
			pstmt.setString(1, txtcontact.getText());
			pstmt.setString(2, txtaddress.getText());
			pstmt.setString(3, lblselect.getText());
			pstmt.setString(4, filepath);
			pstmt.setInt(5, Integer.parseInt(txtage.getText()));
            pstmt.setFloat(6, Float.parseFloat(txtsalary.getText()));
            LocalDate ldob=datepick.getValue();
            java.sql.Date swdatepic=java.sql.Date.valueOf(ldob);
            pstmt.setDate(7, swdatepic);
            
            pstmt.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setContentText("Record Updated");
			alert.show();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

    }
    PreparedStatement pstmt;
Connection con;
    @FXML
    void initialize() {
    	con=ConnectToDatabase.getConnection();
    	try {
			pstmt=con.prepareStatement("select name from hawkers");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String titlee=table.getString("name");
				comboname.getItems().addAll(titlee);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        
        ArrayList<String>lst=new ArrayList<String>(Arrays.asList("select","Bathinda","Gidderbah"));
 		comboarea.getItems().addAll(lst);
 		comboarea.getSelectionModel().select(0);
 		
 		listarea.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 		//comboname.setEditable(false);
    }
}

