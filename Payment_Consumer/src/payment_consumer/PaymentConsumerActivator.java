package payment_consumer;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import payment_producer.PaymentService;
import payment_producer.Payment;

public class PaymentConsumerActivator implements BundleActivator {

    ServiceReference<PaymentService> serviceReference;
    private Scanner scan;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Payment Consumer Start");

        // Retrieve the reference to the PaymentService
        serviceReference = context.getServiceReference(PaymentService.class);

        // Get the service instance
        PaymentService paymentService = context.getService(serviceReference);

        scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n--------------Choose an option from Payment Management--------------");
            System.out.println("1. Process Payment");
            System.out.println("2. View Payment Details");
            System.out.println("3. Refund Payment");
            System.out.println("4. Update Payment Status");
            System.out.println("5. View All Payments");
            System.out.println("6. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Process Payment
                    processPayment(paymentService);
                    break;
                case 2:
                    // View Payment Details
                    viewPaymentDetails(paymentService);
                    break;
                case 3:
                    // Refund Payment
                    refundPayment(paymentService);
                    break;
                case 4:
                    // Update Payment Status
                    updatePaymentStatus(paymentService);
                    break;
                case 5:
                    // View All Payments
                    paymentService.getAllPayments().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void processPayment(PaymentService paymentService) {
        System.out.println("\nEnter the payment details:");

        // Get all details for the Payment object
        System.out.print("Enter payment ID: ");
        String paymentId = scan.nextLine();

        System.out.print("Enter customer ID: ");
        String customerId = scan.nextLine();

        System.out.print("Enter amount: ");
        double amount = scan.nextDouble();
        scan.nextLine(); // Consume newline character

        System.out.print("Enter payment date (YYYY-MM-DD): ");
        String paymentDate = scan.nextLine();

        System.out.print("Enter payment status: ");
        String paymentStatus = scan.nextLine();

        // Create a Payment object using the user inputs
        Payment payment = new Payment(paymentId, customerId, amount, paymentDate, paymentStatus);

        // Process the payment
        paymentService.processPayment(payment);
        System.out.println("Payment processed successfully!");
    }


    private void viewPaymentDetails(PaymentService paymentService) {
        System.out.println("\nEnter the Payment ID to view details:");
        String paymentId = scan.nextLine();
        Payment payment = paymentService.viewPaymentDetails(paymentId);
        if (payment != null) {
            System.out.println("Payment details: " + payment);
        } else {
            System.out.println("Payment not found!");
        }
    }

    private void refundPayment(PaymentService paymentService) {
        System.out.println("\nEnter the Payment ID to refund:");
        String refundId = scan.nextLine();
        paymentService.refundPayment(refundId);
        System.out.println("Payment refunded successfully!");
    }

    private void updatePaymentStatus(PaymentService paymentService) {
        System.out.println("\nEnter the Payment ID to update status:");
        String statusId = scan.nextLine();

        System.out.println("Enter the new status:");
        String status = scan.nextLine();

        paymentService.updatePaymentStatus(statusId, status);
        System.out.println("Payment status updated successfully!");
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Payment Consumer Stop");
        context.ungetService(serviceReference);
    }
}
