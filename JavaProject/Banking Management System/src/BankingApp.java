import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    private static final String url = "jdbc:mysql://localhost:3306/banking_database";
    private static final String username ="root";

    private static final String password = "harshi";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Loaded Successfully");
    }catch (ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
    try(Connection con = DriverManager.getConnection(url,username,password)){
        Scanner sc = new Scanner(System.in);

        User user = new User(con,sc);
        Accounts accounts = new Accounts(con,sc);
        AccountManager accountManager = new AccountManager(con,sc);

        String email;
        long account_number;

        while(true){
            System.out.println("*** WELCOME TO BANKING SYSTEM ***");
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your Choice:");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    user.register();
                    break;
                case 2:
                    email = user.login();
                    if(email!=null){
                        System.out.println();
                        System.out.println("User Logged In!");
                        if(!accounts.accountExist(email)){
                            System.out.println();
                            System.out.println("1. Open a new Bank Account");
                            System.out.println("2. Exit");
                            System.out.print("Enter your choice:");
                            int choice1 = sc.nextInt();
                            if(choice1 == 1){
                                account_number = accounts.openAccount(email);
                                System.out.println("Account Created Successfully");
                                System.out.println("Your Account Number is:" + account_number);
                            }else{
                                break;
                            }
                        }
                        account_number = accounts.getAccountNumber(email);
                        int choice2 = 0;
                        while (choice2!=5){
                            System.out.println("1. Debit Money");
                            System.out.println("2. Credit Money");
                            System.out.println("3. Transfer Money");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Logout");
                            System.out.print("Enter your choice:");
                            choice2 = sc.nextInt();
                            switch (choice2){
                                case 1:
                                    accountManager.debit_money(account_number);
                                    break;
                                case 2:
                                    accountManager.credit_money(account_number);
                                    break;
                                case 3:
                                    accountManager.transfer_money(account_number);
                                    break;
                                case 4:
                                    accountManager.check_balance(account_number);
                                case 5:
                                    break;
                                default:
                                    System.out.println("Enter Valid Choice!");
                                    break;
                            }
                        }
                    }else{
                        System.out.println("Incorrect Email and Password!");
                    }
                case 3:
                    System.out.println("THANK YOU FOR USING BANKING SYSTEM!!! ");
                    System.out.print("Existing System");
                    int i = 5;
                    while(i!=0){

                        System.out.print(".");
                        Thread.sleep(450);
                        i--;
                    }
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }catch (SQLException | InterruptedException e){
        System.out.println(e.getMessage());
    }
    }
}
