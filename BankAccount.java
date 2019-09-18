
package CollectionInDatabase;
import SerializeDeserializeUtility.*;
import java.io.Serializable;

public class BankAccount implements Serializable {

    private int actNumber;
    private String actName, actType;
    private double actBalance;
    
    public BankAccount(String actName, double openingAmount){
        this.actNumber = 0;
        this.actType = "";
        this.actName = actName;
        this.actBalance = openingAmount;
    }
    
    public BankAccount(int actNumber, String actName, String actType, 
            double openingAmount){
        this.actNumber = actNumber;
        this.actName = actName;
        this.actType = actType;
        this.actBalance = openingAmount;
    }
    
    public void deposit(double amount){ 
        actBalance += amount; 
        System.out.println("N%11.2f deposited");
        System.out.printf("Your account balance: N%11.2f \n", actBalance);
    }
    
    public void withdraw(double amount){ 
        if(amount >= (1000 + actBalance)){
            actBalance -= amount;
            System.out.println("N" + amount + " successfully withdrawn");
            System.out.printf("Your account balance: N%11.2f \n", actBalance);
        } 
        else{
            System.out.printf("Sorry, Insufficient balance: N%11.2", actBalance);
        }
    }
    
    public int getActNumber() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public double getAccountBalance() {
        return actBalance;
    }    
    
    public void displayBalance(){
        System.out.printf("Your account balance is N%11.2f", actBalance);
    }
    
    @Override
    public String toString(){
        String output = "";
        output += "Account Number:\t\t" + getActNumber()+ "\n";
        output += "Account Name:\t\t" + getActName() + "\n";
        output += "Account Type:\t\t" + getActType() + "\n";
        output += "Account Balance:\t" + 
                String.format("N%.2f", getAccountBalance()) + "\n";
        
        return(output);
    }
}
