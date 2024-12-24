import java.sql.*;

public class CarRentalSystem {

    public static void main(String[] args) {
        DatabaseHelper dbHelper = new DatabaseHelper();

        // Register a new user
        dbHelper.insertUser("john_doe", "password123");

        // List available cars
        System.out.println("Available Cars:");
        dbHelper.getAvailableCars();

        // Rent a car for a user (user_id = 1)
        dbHelper.rentCar(1, 2);  // User 1 rents car with car_id = 2

        // List user's rented cars
        System.out.println("\nUser's Rented Cars:");
        dbHelper.getRentedCars(1);
    }
}
