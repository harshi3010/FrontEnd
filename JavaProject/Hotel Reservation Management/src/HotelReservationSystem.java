import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "harshi";

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        //Load Driver Classes
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Driver's loaded Successfully...");
        }

        //Connection Create - it is an interface in DriverManager class ,
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to DB Created Successfully..");
            while (true) {
                System.out.println();
                System.out.println("HOTEL RESERVATION SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.print("Enter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserverRoom(connection, sc);
                        break;
                    case 2:
                        viewReservation(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, sc);
                        break;
                    case 4:
                        updateReservation(connection, sc);
                        break;
                    case 5:
                        deleteReservation(connection, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.Try again");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void reserverRoom(Connection connection,Scanner sc) {
        try {
            System.out.print("Enter Guest Name:");
            String guestName = sc.next();
            sc.nextLine();
            System.out.print("Enter Room Number:");
            int roomNumber = sc.nextInt();
            System.out.print("Enter Contact Number:");
            String contactNumber = sc.next();

            String sqlQuery = "INSERT INTO reservations(guest_name,room_number,contact_number)" + "VALUES('" + guestName + "'," + roomNumber + ",'" + contactNumber + "')";
//        String sqlQuery1 = "INSERT INTO reservation(quest_name,room_number,contact_number) VALUES('guestName','roomNumber', 'contactNumber');";

            try (Statement stmt = connection.createStatement()) {

                int rowAffected = stmt.executeUpdate(sqlQuery);
                if (rowAffected > 0) {
                    System.out.println("Reservation Successful." + rowAffected + "row(s) affected");
                } else {
                    System.out.println("Reservation failed...");
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void viewReservation(Connection connection) throws SQLException{
        String sql = "select reservation_id,guest_name,room_number,contact_number,reservation_date from reservations";

        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-------------+------------------+----------------------+---------------------+");
            System.out.println("| Reservation ID |  Guest      |   Room  Number   |      Contact Number  |    Reservation Date  ");
            System.out.println("+----------------+-------------+------------------+----------------------+---------------------+");

            while(rs.next()){
                int reservationId = rs.getInt("reservation_id");
                String guestName = rs.getString("guest_name");
                int roomNumber = rs.getInt("room_number");
                String contactNumber = rs.getString("contact_number");
                String reservationDate = rs.getTimestamp("reservation_date").toString();

            //format
            System.out.printf("| %14d | %12s |  %11d |  %17s | %17s |\n",
                    reservationId,guestName,roomNumber,contactNumber, reservationDate);
            }
            System.out.println("+----------------+-------------+------------------+----------------------+---------------------+");
        }
    }

    private static void getRoomNumber(Connection connection,Scanner sc){
        try{
            System.out.print("Enter reservation ID: ");
            int reservationId = sc.nextInt();
            System.out.print("Enter Guest Name:");
            String guestName = sc.next();


            String sql1 = "SELECT room_number FROM reservations " +
                    "WHERE reservation_id = " + reservationId +
                    " AND guest_name = '" + guestName + "'";

            try(Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql1)) {
                if (rs.next()) {
                    int roomNumber = rs.getInt("room_number");
                    System.out.println("Room number for reservation ID " + reservationId + "and Guest " + guestName + "is: " + roomNumber);
                } else {
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection connection, Scanner sc) {
        try {
            System.out.print("Enter reservation ID to update:");
            int reservationId = sc.nextInt();
            sc.nextLine();  //consume the newline character

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }
            System.out.print("Enter new guest name:");
            String newGuestName = sc.nextLine();
            System.out.print("Enter new room number:");
            int newRoomNumber = sc.nextInt();
            System.out.print("Enter new contact number:");
            String newContactNumber = sc.next();

            String sql = "UPDATE reservations SET guest_name = '" + newGuestName + "', " +
                    "room_number = " + newRoomNumber + ", " +
                    "contact_number = '" + newContactNumber + "'" +
                    "WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedrow = statement.executeUpdate(sql);
                if (affectedrow > 0) {
                    System.out.println("Reservation Update successful." + affectedrow + "row(s) affected");
                } else {
                    System.out.println("Reservation Update Failed..");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



    private static void deleteReservation(Connection connection,Scanner sc){
        try {
            System.out.print("Enter reservation ID to delete:");
            int reservationId = sc.nextInt();

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for given ID.");
            }

            String sql = "delete from reservations where reservation_id  = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedrow = statement.executeUpdate(sql);

                if (affectedrow > 0) {
                    System.out.println("Reservation Deleted successfully!");
                } else {
                        System.out.println("Reservation deletion failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static  boolean reservationExists(Connection connection,int reservationId){
        try{
            String sql = "select reservation_id from reservations where reservation_id = " + reservationId;

            try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
                return rs.next();  //if there is a result; the reservation exists
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void exit() throws InterruptedException{
        System.out.print("Existing System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using Hotel Reservation System!!!");
    }

}
