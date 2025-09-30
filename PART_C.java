//Menu-Based Employee Management System Using File Handling
import java.io.*;
import java.util.*;
public class PART_C{
    static final String FILE_NAME = "data.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Delete Employee by ID");
            System.out.println("5. Update Employee by ID");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addEmployee(sc);
                case 2 -> displayEmployees();
                case 3 -> searchEmployee(sc);
                case 4 -> deleteEmployee(sc);
                case 5 -> updateEmployee(sc);
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    static void addEmployee(Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            writer.write(id + "," + name + "," + designation + "," + salary);
            writer.newLine();
            System.out.println("Employee Added Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void displayEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Employee Records ---");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("ID: " + data[0] + " | Name: " + data[1] +
                                   " | Designation: " + data[2] + " | Salary: " + data[3]);
            }
        } catch (IOException e) {
            System.out.println("No employees found.");
        }
    }
    static void searchEmployee(Scanner sc) {
        System.out.print("Enter ID to search: ");
        String searchId = sc.nextLine();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(searchId)) {
                    System.out.println("Found: ID: " + data[0] + " | Name: " + data[1] +
                                       " | Designation: " + data[2] + " | Salary: " + data[3]);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Employee not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
    static void deleteEmployee(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        String deleteId = sc.nextLine();
        List<String> lines = new ArrayList<>();
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(deleteId)) {
                    deleted = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            if (deleted)
                System.out.println("Employee deleted successfully.");
            else
                System.out.println("Employee not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void updateEmployee(Scanner sc) {
        System.out.print("Enter ID to update: ");
        String updateId = sc.nextLine();
        List<String> lines = new ArrayList<>();
        boolean updated = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(updateId)) {
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Designation: ");
                    String newDes = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    sc.nextLine();

                    lines.add(updateId + "," + newName + "," + newDes + "," + newSalary);
                    updated = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            if (updated)
                System.out.println("Employee updated successfully.");
            else
                System.out.println("Employee not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
