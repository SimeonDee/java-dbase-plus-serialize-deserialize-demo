package CollectionInDatabase;
//Abstraction of Database 
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyDBaseClass {

    private String connectionString;
    private String Username;
    private String Pswd;
    private Connection conn;
    private Statement stmt;

    
    //Default Constructor
    public MyDBaseClass(){
        this.connectionString = "";
        this.Username = "";
        this.Pswd = "";
    }
    
    //Other Constructor
    public MyDBaseClass(String connectionString, 
            String username, String pswd){
        this.connectionString = connectionString;
        this.Username = username;
        this.Pswd = pswd;
    }
    
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    public String getConnectionString() {
        return connectionString;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPswd(String Pswd) {
        this.Pswd = Pswd;
    }
    
    public void connect(){
        try{
            conn = DriverManager.getConnection(this.connectionString, 
                    this.Username,this.Pswd);
            stmt = conn.createStatement();
            
            System.out.println("Success: "
                    + "Connection established to MYSQL database");
            
        } catch(SQLException e){
            System.out.println("Oops!..."+e.getMessage());
        }
    }
    
    public ResultSet runQuery(String selectSqlStr){
        if(conn != null && stmt != null){
            try {
                ResultSet result = stmt.executeQuery(selectSqlStr);
                return (result);
            
            } catch (SQLException ex) {
                Logger.getLogger(MyDBaseClass.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.out.println("Oops!..."+ex.getMessage());
            }
        }
        return null;
    }
    
    public int runNonQuery(String insertUpdateDeleteSqlStr){
            if(conn != null && stmt != null){
                try {
                    int result = stmt.executeUpdate(insertUpdateDeleteSqlStr);
                    return (result);
            
                } catch(SQLException ex) {
                    Logger.getLogger(MyDBaseClass.class.getName()).log(Level.SEVERE,
                            null, ex);
                    System.out.println("Oops!..." + ex.getMessage());
                } catch(Exception ex1){
                    System.out.println("Oops!..." + ex1.getMessage());
                } 
            }
        return 0;
    }
    
    public void closeConnection(){
        try{
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
