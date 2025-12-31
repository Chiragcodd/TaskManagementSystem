import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("     WELCOME TO TASK MANAGER      ");
        System.out.println("==================================");

        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- REGISTER ---");
                    System.out.print("Username: ");
                    String regUser = sc.nextLine();
                    System.out.print("Password: ");
                    String regPass = sc.nextLine();
                    UserDAO.register(new User(regUser, regPass));
                    System.out.println("----------------------------");
                    break;

                case 2:
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Username: ");
                    String loginUser = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPass = sc.nextLine();
                    int userId = UserDAO.login(loginUser, loginPass);

                    if (userId > 0) {
                        System.out.println("\nLogin Successful! Welcome " + loginUser + "!");
                        boolean logout = false;

                        while (!logout) {
                            System.out.println("\n--- USER MENU ---");
                            System.out.println("1. Add Task");
                            System.out.println("2. View Tasks");
                            System.out.println("3. Delete Task");
                            System.out.println("4. Update Task");
                            System.out.println("5. Logout");
                            System.out.print("Enter your choice: ");
                            int ch = sc.nextInt();
                            sc.nextLine(); // consume newline

                            switch (ch) {
                                case 1:
                                    System.out.println("\n--- ADD TASK ---");
                                    System.out.print("Title: ");
                                    String t = sc.nextLine();
                                    System.out.print("Description: ");
                                    String d = sc.nextLine();
                                    TaskDAO.addTask(new Task(t, d, "Pending"), userId);
                                    System.out.println("----------------------------");
                                    break;

                                case 2:
                                    TaskDAO.viewTasks(userId);
                                    break;

                                case 3:
                                    System.out.print("Enter Task ID to delete: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    TaskDAO.deleteTask(id);
                                    System.out.println("----------------------------");
                                    break;

                                case 4:
                                    System.out.print("Enter Task ID to update: ");
                                    int updateId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("New Title: ");
                                    String newTitle = sc.nextLine();
                                    System.out.print("New Description: ");
                                    String newDesc = sc.nextLine();
                                    System.out.print("New Status (Pending/Done): ");
                                    String newStatus = sc.nextLine();
                                    TaskDAO.updateTask(updateId, newTitle, newDesc, newStatus);
                                    System.out.println("----------------------------");
                                    break;

                                case 5:
                                    System.out.println("Logging out...");
                                    logout = true;
                                    break;

                                default:
                                    System.out.println("Invalid choice! Try again.");
                            }
                        }

                    } else {
                        System.out.println("Invalid login! Please try again.");
                    }
                    break;

                case 3:
                    System.out.println("\nThank you for using Task Manager!");
                    System.out.println("==================================");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}
