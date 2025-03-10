package delivery_consumer;

import java.util.Scanner;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import delivery_publisher.Delivery;
import delivery_publisher.DeliveryService;
import java.util.InputMismatchException;

public class DeliveryConsumerActivator implements BundleActivator {

    private ServiceReference serviceReference;
    private DeliveryService deliveryService;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting DeliveryConsumer Bundle...");
        serviceReference = context.getServiceReference(DeliveryService.class.getName());
        if (serviceReference == null) {
            System.out.println("Delivery Service is not available. Please ensure the Delivery Producer bundle is active.");
            return;
        }
        deliveryService = (DeliveryService) context.getService(serviceReference);

        handleActions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping DeliveryConsumer Bundle...");
        context.ungetService(serviceReference);
    }

    /**
     * Presents an interactive menu to the user to perform delivery management actions.
     */
    private void handleActions() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-------------------------------------------------");
            System.out.println("Delivery Management - Please choose an action:");
            System.out.println("1. Insert a New Delivery Order");
            System.out.println("2. Get Delivery Order by ID");
            System.out.println("3. Update Delivery Order Status");
            System.out.println("4. Cancel Delivery Order");
            System.out.println("5. Mark Order as Delivered");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        handleInsertDeliveryOrder(scanner);
                        break;
                    case 2:
                        handleGetDeliveryOrder(scanner);
                        break;
                    case 3:
                        handleUpdateDeliveryOrderStatus(scanner);
                        break;
                    case 4:
                        handleCancelDeliveryOrder(scanner);
                        break;
                    case 5:
                        handleMarkOrderAsDelivered(scanner);
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting Delivery Management Consumer.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        // Do not close the scanner to avoid closing System.in
    }

    private void handleInsertDeliveryOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Delivery Address: ");
        String deliveryAddress = scanner.nextLine();
        System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
        String deliveryDate = scanner.nextLine();

        // Assuming a default status of "Pending" for a new delivery order.
        Delivery delivery = new Delivery(orderId, customerName, deliveryAddress, "Pending", deliveryDate);
        deliveryService.insertDeliveryOrder(delivery);
        System.out.println("Delivery order inserted successfully!");
    }

    private void handleGetDeliveryOrder(Scanner scanner) {
        System.out.print("Enter Order ID to retrieve: ");
        String orderId = scanner.nextLine();
        Delivery delivery = deliveryService.getDeliveryOrderById(orderId);
        if (delivery != null) {
            System.out.println("Delivery Order Details: " + delivery);
        } else {
            System.out.println("Delivery order not found with ID: " + orderId);
        }
    }

    private void handleUpdateDeliveryOrderStatus(Scanner scanner) {
        System.out.print("Enter Order ID to update status: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter new status: ");
        String newStatus = scanner.nextLine();
        deliveryService.updateDeliveryOrderStatus(orderId, newStatus);
        System.out.println("Delivery order status updated successfully!");
    }

    private void handleCancelDeliveryOrder(Scanner scanner) {
        System.out.print("Enter Order ID to cancel the delivery: ");
        String orderId = scanner.nextLine();
        deliveryService.cancelDeliveryOrder(orderId);
        System.out.println("Delivery order cancelled successfully!");
    }

    private void handleMarkOrderAsDelivered(Scanner scanner) {
        System.out.print("Enter Order ID to mark as delivered: ");
        String orderId = scanner.nextLine();
        deliveryService.markOrderAsDelivered(orderId);
        System.out.println("Delivery order marked as delivered successfully!");
    }
}
