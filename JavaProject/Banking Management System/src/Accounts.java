import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection con;
    private Scanner sc;

    public Accounts(Connection con,Scanner sc){
        this.con = con;
        this.sc = sc;
    }
    public boolean accountExist(String email){
        String accountExist = "select account_number from accounts where email1 = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(accountExist);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public long openAccount(String email){
        if(!accountExist(email)){
            String openAccountQuery = "Insert into accounts(account_number,fullname,email1,balance,security_pin) values (?,?,?,?,?)";
            sc.nextLine();
            System.out.print("Enter FullName:");
            String fullName = sc.nextLine();
            System.out.print("Enter Initial Amount:");
            double balance = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter Security Pin:");
            String securityPin = sc.nextLine();
            try{
                long accountNumber = generateAccountNumber();
                PreparedStatement preparedStatement = con.prepareStatement(openAccountQuery);
                preparedStatement.setLong(1,accountNumber);
                preparedStatement.setString(2,fullName);
                preparedStatement.setString(3,email);
                preparedStatement.setDouble(4,balance);
                preparedStatement.setString(5,securityPin);

                int insertAccount = preparedStatement.executeUpdate();
                if(insertAccount>0){
                    return accountNumber;
                }else{
                    throw new RuntimeException("Account Creation/Open Failed...");
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        throw new RuntimeException("Account Already Exist");
    }

    public long getAccountNumber(String email){
        String query = "SELECT account_number from Accounts WHERE email1 = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("account_number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Account Number Doesn't Exist!");
    }

    private long generateAccountNumber(){
        try{
            String sql = "select account_number from accounts order by account_number DESC LIMIT 1";   //first it will sort account_number by descending order then give only 1 account
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                long lastAccountNumber = rs.getLong("account_number");
                return lastAccountNumber+1;
            }else{
                return 10000100;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 10000100;
    }

}
