package order_consumer;

import java.util.InputMismatchException;
import java.util.Scanner;
import order_producer.Order;
import order_producer.OrderService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class OrderConsumerActivator implements BundleActivator {

    private ServiceReference serviceReference;
    private OrderService orderService;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting OrderConsumer Bundle...");
        serviceReference = context.getServiceReference(OrderService.class.getName());
        
        if (serviceReference == null) {
        	System.out.println("Order Service is not available. Please ensure the Order Producer bundle is active.");
        }
        
        orderService = (OrderService) context.getService(serviceReference);

        handleActions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping OrderConsumer Bundle...");
        context.ungetService(serviceReference);
    }

    /**
     * Presents an interactive menu to the user to perform order management actions.
     */
    private void handleActions() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("-------------------------------------------------");
            System.out.println("Order Management - Please choose an action:");
            System.out.println("1. Place a New Order");
            System.out.println("2. Retrieve an Order by ID");
            System.out.println("3. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        handlePlaceOrder(scanner);
                        break;
                    case 2:
                        handleRetrieveOrder(scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting Order Management Consumer.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    /**
     * Handles the process of placing a new order by prompting the user for order details.
     * 
     * @param scanner the Scanner to read user input
     */
    private void handlePlaceOrder(Scanner scanner) {
        try {
            System.out.print("Enter Order ID: ");
            String orderId = scanner.nextLine();

            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter Items (comma separated): ");
            String itemsInput = scanner.nextLine();
            String[] items = itemsInput.split("\\s*,\\s*");

            System.out.print("Enter Total Price: ");
            double totalPrice = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            Order order = new Order(orderId, customerName, items, totalPrice);
            orderService.placeOrder(order);
            System.out.println("Order placed successfully!");
        } catch (Exception e) {
            System.out.println("Error while placing order: " + e.getMessage());
            scanner.nextLine(); // clear buffer if needed
        }
    }

    /**
     * Handles retrieving an order by its ID and displaying the order details.
     * 
     * @param scanner the Scanner to read user input
     */
    private void handleRetrieveOrder(Scanner scanner) {
        System.out.print("Enter Order ID to retrieve: ");
        String orderId = scanner.nextLine();
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            System.out.println("Order Details:");
            System.out.println(order);
        } else {
            System.out.println("Order not found with ID: " + orderId);
        }
    }
}