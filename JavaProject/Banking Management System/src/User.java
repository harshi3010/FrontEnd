import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection con;
    private Scanner sc;

    public User(Connection con,Scanner sc){
        this.con = con;
        this.sc = sc;
    }

    public void register(){
        sc.nextLine();
        System.out.print("Full Name:");
        String fullName = sc.nextLine();
        System.out.print("Email:");
        String email = sc.nextLine();
        System.out.print("Password:");
        String password = sc.nextLine();

        if(userExist(email)){
            System.out.println("User Already Exist for this Email Address!!!");
            return;
        }
        String register_query = "INSERT INTO User(fullname, email, password) VALUES(?, ?, ?)";

        try{
            PreparedStatement preparedStatement = con.prepareStatement(register_query);
            preparedStatement.setString(1,fullName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);

            int registerUser = preparedStatement.executeUpdate();
            if(registerUser>0){
                System.out.println("Resgistration Successfull!");
            }else{
                System.out.println("Registeration Failed!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

    public String login(){
        sc.nextLine();
        System.out.print("Email:");
        String email = sc.next();
        System.out.print("Password:");
        String password = sc.next();
        sc.nextLine();
        String login_query = "select * from user where email = ? and password = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(login_query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return email;
            }else {
                return null;
            }
        }catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    public boolean userExist(String email) {
        String query = "select * from user where email = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
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
}
