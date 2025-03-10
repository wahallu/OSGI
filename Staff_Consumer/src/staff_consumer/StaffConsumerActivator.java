package staff_consumer;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import staff_producer.StaffSalaryService;


public class StaffConsumerActivator implements BundleActivator {

    private ServiceReference serviceReference;
    private StaffSalaryService staffSalaryService;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting StaffConsumer Bundle...");
        serviceReference = context.getServiceReference(StaffSalaryService.class.getName());
        if (serviceReference == null) {
            System.out.println("Staff Salary Service is not available. Please ensure the Staff Producer bundle is active.");
            return;
        }
        staffSalaryService = (StaffSalaryService) context.getService(serviceReference);

        handleActions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping StaffConsumer Bundle...");
        context.ungetService(serviceReference);
    }

    /**
     * Presents an interactive menu to the user to perform staff management actions.
     */
    private void handleActions() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-------------------------------------------------");
            System.out.println("Staff Management - Please choose an action:");
            System.out.println("1. Add Staff Salary");
            System.out.println("2. View All Salaries");
            System.out.println("3. Calculate Total Salaries");
            System.out.println("4. Get Salary by Role");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        staffSalaryService.addSalaryDetails();
                        break;
                    case 2:
                        staffSalaryService.displaySalaries();
                        break;
                    case 3:
                        staffSalaryService.calculateTotalSalaries();
                        break;
                    case 4:
                        System.out.print("Enter Role (e.g., Chef, Waiter, Manager, Cashier): ");
                        String role = scanner.nextLine();
                        staffSalaryService.getSalaryByRole(role);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting Staff Management Consumer.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
        // Do not close the scanner to avoid closing System.in
    }
}