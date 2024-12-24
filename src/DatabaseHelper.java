import java.sql.*;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
    private static final String USER = "root";
    private static final String PASSWORD = "password";  // Replace with your actual MySQL password

    // Establish connection to the database
    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert a new user into the database
    public void insertUser(String username, String password) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a new car into the database
    public void insertCar(String carModel, String carType, double pricePerDay) {
        String query = "INSERT INTO Cars (car_model, car_type, price_per_day) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, carModel);
            stmt.setString(2, carType);
            stmt.setDouble(3, pricePerDay);
            stmt.executeUpdate();
            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get available cars for rent
    public void getAvailableCars() {
        String query = "SELECT * FROM Cars WHERE status = 'Available'";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int carId = rs.getInt("car_id");
                String carModel = rs.getString("car_model");
                String carType = rs.getString("car_type");
                double price = rs.getDouble("price_per_day");
                System.out.println("Car ID: " + carId + ", Model: " + carModel + ", Type: " + carType + ", Price: " + price + " per day");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rent a car for a user
    public void rentCar(int userId, int carId) {
        String updateCarQuery = "UPDATE Cars SET status = 'Rented' WHERE car_id = ?";
        String insertRentQuery = "INSERT INTO Rentals (user_id, car_id) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt1 = conn.prepareStatement(updateCarQuery);
             PreparedStatement stmt2 = conn.prepareStatement(insertRentQuery)) {
            
            stmt1.setInt(1, carId);
            stmt1.executeUpdate();

            stmt2.setInt(1, userId);
            stmt2.setInt(2, carId);
            stmt2.executeUpdate();

            System.out.println("Car rented successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get rented cars for a user
    public void getRentedCars(int userId) {
        String query = "SELECT c.car_model, c.car_type FROM Cars c JOIN Rentals r ON c.car_id = r.car_id WHERE r.user_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String carModel = rs.getString("car_model");
                String carType = rs.getString("car_type");
                System.out.println("Model: " + carModel + ", Type: " + carType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
