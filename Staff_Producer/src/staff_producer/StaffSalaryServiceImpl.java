package staff_producer;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffSalaryServiceImpl implements StaffSalaryService{
	
	private ArrayList<StaffSalary> salaryDetails = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private static final String[] ROLES = {"Chef", "Waiter", "Manager", "Cashier"};

    public StaffSalaryServiceImpl() {
        salaryDetails.add(new StaffSalary("101", "Devindi", "Chef", 2000.0, getCurrentDate(), "March"));
        
    }

    @Override
    public void displaySalaries() {
        System.out.println("| ID | Name  | Role   | Salary | Month  | Date  |");
        for (StaffSalary salary : salaryDetails) {
            System.out.printf("| %s | %s | %s | %.2f | %s | %s |\n",
                salary.getId(), salary.getStaffName(), salary.getRole(),
                salary.getSalary(), salary.getMonth(), salary.getDate());
        }
    }

    @Override
    public void addSalaryDetails() {
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Select Role: ");
        String role = selectRole();
        
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        
        String id = String.valueOf(System.currentTimeMillis());
        
        StaffSalary newSalary = new StaffSalary(id, name, role, salary, getCurrentDate(), "March");
        salaryDetails.add(newSalary);
        System.out.println("Staff salary details added successfully!");
    }

    @Override
    public void calculateTotalSalaries() {
        double totalSalary = 0;
        for (StaffSalary salary : salaryDetails) {
            totalSalary += salary.getSalary();
        }
        System.out.println("Total Salaries Paid: " + totalSalary);
    }

    @Override
    public void getSalaryByRole(String role) {
        double totalSalaryForRole = 0;
        for (StaffSalary salary : salaryDetails) {
            if (salary.getRole().equalsIgnoreCase(role)) {
                totalSalaryForRole += salary.getSalary();
            }
        }
        System.out.println("Total salary paid for " + role + ": " + totalSalaryForRole);
    }

    private String selectRole() {
        int choice;
        while (true) {
            System.out.println("Select Staff Role:");
            for (int i = 0; i < ROLES.length; i++) {
                System.out.println((i + 1) + ". " + ROLES[i]);
            }
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= ROLES.length) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        return ROLES[choice - 1];
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }
	
	

}
