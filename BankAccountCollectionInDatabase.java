
package CollectionInDatabase;


import SerializeDeserializeUtility.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankAccountCollectionInDatabase {
    private static MyDBaseClass myDB;
    public static void main(String[] args){ 
        try{
            String conStr = 
                    "jdbc:mysql://localhost:3306/ebookshop?useSSL=false";
            String uname = "root";
            String pswd = "";
            Scanner sc = new Scanner(System.in);
            myDB = new MyDBaseClass();
            myDB.setConnectionString(conStr);
            myDB.setUsername(uname);
            myDB.setPswd(pswd);
            myDB.connect();
            
            String createDBSQL = "CREATE SCHEMA IF NOT EXISTS BankAccounts;";
            String createTableSQL = "CREATE TABLE "
                    + "IF NOT EXISTS Customers("
                    + "AccountNumber INT NOT NULL,"
                    + "Fullname VARCHAR(50) NOT NULL,"
                    + "AccountType VARCHAR(10) NOT NULL,"
                    + "Balance DOUBLE(11,2) NOT NULL,"
                    + "PRIMARY KEY(AccountNumber));";
            
            myDB.runNonQuery(createDBSQL);
            myDB.runNonQuery(createTableSQL);
            myDB.runNonQuery("DELETE FROM Customers;");
            
            //FETCHING / SELECTING RECORDS FROM DATABASE
            
        
            
            System.out.println("ADEYEMI ADEDOYIN SIMEON -- 209188\n");
            List<BankAccount> accountList = new ArrayList<>();
            
            BankAccount doyinAccount = new BankAccount(100,"Adedoyin Simeon",
                    "Current",25000);
            BankAccount waleAccount = new BankAccount(101,"Badejo Olawale",
                    "Savings",12000);
            BankAccount seunAccount = new BankAccount(102,"Oluwadele Seun",
                    "Savings",22000);

            accountList.add(doyinAccount);
            accountList.add(waleAccount);
            accountList.add(seunAccount);
            
            //Store customer details into database table
            saveCustomers(accountList);
            System.out.println("Customers successfully saved into Database\n");
            
            //Retrieve saved customers into collection
            ResultSet result = myDB.runQuery("SELECT * FROM Customers");
            List<BankAccount> retrievedCustomers = new ArrayList<>();
            retrievedCustomers = retrieveCustomers(result);
   
            System.out.println("RETRIEVED ACCOUNT DETAILS");
            System.out.println("*************************\n");
            int counter = 0;
            for(BankAccount customer : retrievedCustomers){
                System.out.println(++counter + ".) " + customer);
            }

        } catch(Exception ex){
            System.out.println("Something went wrong "+ ex.getMessage());
        } finally{
            myDB.closeConnection();
        }
    }
    
    public static void saveCustomers(List<BankAccount> customers){
        for(BankAccount customer : customers){
            String sql = "INSERT INTO Customers(AccountNumber,Fullname,"
                    + "AccountType,Balance) VALUES(" + customer.getActNumber()
                    + ",'" + customer.getActName() + "','" + 
                    customer.getActType() + "'," + customer.getAccountBalance()
                    + ");";
            int answer = myDB.runNonQuery(sql);    
        }
    }
    
    public static ArrayList<BankAccount> retrieveCustomers(ResultSet result){
        try{
            ArrayList<BankAccount> accounts = new ArrayList<>();
            int recordCounter = 0;
            while(result.next()){
                BankAccount currentCustomer = new BankAccount(
                        result.getInt("AccountNumber"),
                        result.getString("Fullname"),
                        result.getString("AccountType"),
                        result.getFloat("Balance"));
                
                accounts.add(currentCustomer);
                recordCounter++;
                
            }    
            
            System.out.println("\nTotal of " + recordCounter + 
                    " customers fetched");
            System.out.println();
            
            return accounts;
            
        } catch(SQLException ex1){
            System.out.println("Error: " + ex1.getMessage());
            return null;
        }        
    }
}
