import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {

    private Connection con;
    private Scanner sc;

    //Constructor
    public AccountManager(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void debit_money(long account_number) throws SQLException{
        sc.nextLine();
        System.out.print("Enter Amount:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin:");
        String security_pin = sc.nextLine();
        try{
            con.setAutoCommit(false);
            if(account_number!=0){
                PreparedStatement preparedStatement = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    double current_balance = rs.getDouble("balance");
                    if(amount<=current_balance){
                        String debit_amountQuery = "Update accounts set balance = balance - ? where account_number = ?";
                        PreparedStatement preparedStatement1 = con.prepareStatement(debit_amountQuery);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setLong(2,account_number);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if(rowsAffected>0){
                            System.out.println("Rs." + amount + "debited successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("Transaction failed.");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance.");
                    }
                }else{
                    System.out.println("Invalid Security Pin.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        con.setAutoCommit(true);
    }

    public void credit_money(long account_number) throws SQLException{
        sc.nextLine();
        System.out.print("Enter Amount:");
        Double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin:");
        String security_pin = sc.next();
        try{
            con.setAutoCommit(false);
            if(account_number!=0){
               PreparedStatement preparedStatement = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    double current_balance = rs.getDouble("balance");
                    String credit_amountQuery = "update accounts set balance = balance + ? where account_number = ?";
                    PreparedStatement preparedStatement1 = con.prepareStatement(credit_amountQuery);
                    preparedStatement1.setDouble(1,amount);
                    preparedStatement1.setLong(2,account_number);
                    int affectedRows = preparedStatement1.executeUpdate();
                    if(affectedRows>0){
                        System.out.println("Rs." + amount + " credited successfully.");
                        con.commit();
                        con.setAutoCommit(true);
                    }else{
                        System.out.println("Transaction Failed..");
                        con.rollback();
                        con.setAutoCommit(true);
                    }
                }else{
                    System.out.println("Invalid Security Pin");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        con.setAutoCommit(true);
    }

    public void check_balance(long account_number){
        sc.nextLine();
        System.out.print("Enter Security Pin:");
        String security_pin = sc.next();
        sc.nextLine();
        try{
            PreparedStatement preparedStatement = con.prepareStatement("select balance from accounts where account_number = ? and security_pin = ?");
            preparedStatement.setLong(1,account_number);
            preparedStatement.setString(2,security_pin);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                double balance = rs.getDouble("balance");
                System.out.println("Balance: "+balance);
            }else{
                System.out.println("Invalid Security Pin!!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void transfer_money(long sender_account_number) throws SQLException{
        sc.nextLine();
        System.out.print("Enter Receiver Account Number:");
        long receiver_account_number = sc.nextLong();
        System.out.print("Enter Amount:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin:");
        String security_pin = sc.nextLine();

        try{
            con.setAutoCommit(false);
            if(sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement preparedStatement = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
                preparedStatement.setLong(1,sender_account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    double current_balance = rs.getDouble("balance");
                    if(amount<=current_balance) {
                        //Write debit and credit queries
                        String debit_query = "Update accounts set balance = balance - ? where account_number = ?";
                        String credit_query = "Update accounts set balance = balance + ? where account_number = ?";

                        //debit and credit preparedStatement
                        PreparedStatement debitps = con.prepareStatement(debit_query);
                        PreparedStatement creditps = con.prepareStatement(credit_query);

                        //set the value for debit credit prepared statement
                        debitps.setDouble(1,amount);
                        debitps.setLong(2,sender_account_number);
                        creditps.setDouble(1,amount);
                        creditps.setLong(2,receiver_account_number);

                       //execute queries
                        int rowAffected1 = debitps.executeUpdate();
                        int rowAffected2 = creditps.executeUpdate();
                        if(rowAffected1>0 && rowAffected2>0){
                            System.out.println("Transaction Successful!.");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("Transaction Failed.");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Security Pin.");
                }
            }else{
                System.out.println("Invalid Account Number!");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        con.setAutoCommit(true);
    }

}
