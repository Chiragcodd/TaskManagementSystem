import java.sql.*;

public class TaskDAO {

    public static void addTask(Task task, int userId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO tasks(title,description,status,user_id) VALUES (?,?,?,?)")) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            ps.setInt(4, userId);
            ps.executeUpdate();

            System.out.println("Task Added Successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add task!");
            e.printStackTrace();
        }
    }

    public static void viewTasks(int userId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM tasks WHERE user_id=? ORDER BY id ASC")) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- TASK LIST ---");
            System.out.printf("%-5s %-25s %-40s %-10s\n", "ID", "Title", "Description", "Status");
            System.out.println("-------------------------------------------------------------------------------");

            boolean hasTasks = false;
            while (rs.next()) {
                hasTasks = true;
                System.out.printf("%-5d %-25s %-40s %-10s\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"));
            }

            if (!hasTasks) {
                System.out.println("No tasks found for this user.");
            }
            System.out.println("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Failed to fetch tasks!");
            e.printStackTrace();
        }
    }

    public static void deleteTask(int taskId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM tasks WHERE id=?")) {

            ps.setInt(1, taskId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Task Deleted Successfully!");
            } else {
                System.out.println("Task not found!");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete task!");
            e.printStackTrace();
        }
    }

    public static void updateTask(int taskId, String newTitle, String newDesc, String newStatus) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE tasks SET title=?, description=?, status=? WHERE id=?")) {

            ps.setString(1, newTitle);
            ps.setString(2, newDesc);
            ps.setString(3, newStatus);
            ps.setInt(4, taskId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Task Updated Successfully!");
            } else {
                System.out.println("Task not found!");
            }
        } catch (Exception e) {
            System.out.println("Failed to update task!");
            e.printStackTrace();
        }
    }
}