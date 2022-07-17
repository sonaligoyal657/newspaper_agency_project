package paper_master;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import connection.ConnectToDatabase;

public class PaperMasterController {

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private TextField txtprice;

    @FXML
    private Button btnfetch;

    @FXML
    private Button btnnew;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btnrem;
    
    @FXML
    private Label lblresult;


    @FXML
    void doFetch(ActionEvent event) {
    	String paperitem=(String)combopaper.getEditor().getText();
    	try 
    	{
			pstmt=con.prepareStatement("select * from papers where paper=?");
			pstmt.setString(1,paperitem);
			//0 or 1 records possibility
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				float price= table.getFloat("price");
				txtprice.setText(String.valueOf(price));
				
			}
			else
				lblresult.setText("No entry found");
			
    	} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }


    @FXML
    void doRemove(ActionEvent event) {
    	String paperitem=(String)combopaper.getSelectionModel().getSelectedItem();
    	try {
			pstmt=con.prepareStatement("delete from papers where paper=? ");
			pstmt.setString(1,paperitem);
			int count=	pstmt.executeUpdate();//to fire query on table-to save
			
		lblresult.setText(count+" Records Deleted...");
			
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void doSave(ActionEvent event) {
    	try {
			pstmt=con.prepareStatement("insert into papers values(?,?)");
			pstmt.setString(1,combopaper.getEditor().getText());
			pstmt.setFloat(2, Float.parseFloat(txtprice.getText()));
			pstmt.executeUpdate();	
			lblresult.setText("Saved Successfully!");
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	String paperitem=(String)combopaper.getSelectionModel().getSelectedItem();
		
    	try {
			pstmt=con.prepareStatement("update papers set price=? where paper=?");
			
			pstmt.setString(2, paperitem);
			pstmt.setFloat(1, Float.parseFloat(txtprice.getText()));
			
			int count=pstmt.executeUpdate();//to fire query on table-to save
			lblresult.setText(count+" Records Updated");
			
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void donew(ActionEvent event) {
    	txtprice.clear();  	

    }
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() {
        con=ConnectToDatabase.getConnection();
        try {
        pstmt=con.prepareStatement("select paper from papers");
		ResultSet table=pstmt.executeQuery();
		while(table.next())
		{
			String titlee=table.getString("paper");
			combopaper.getItems().addAll(titlee);
			
       
     }
}
        catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
