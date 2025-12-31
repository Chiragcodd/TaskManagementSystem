import java.sql.*;

public class UserDAO {

    public static boolean register(User user) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO users(username,password) VALUES (?,?)")) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

            System.out.println("Registration Successful!");
            return true;
        } catch (SQLException e) {
            System.out.println("User already exists!");
            return false;
        }
    }

    public static int login(String username, String password) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT id FROM users WHERE username=? AND password=?")) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Login failed due to DB error!");
            e.printStackTrace();
        }
        return -1;
    }
}