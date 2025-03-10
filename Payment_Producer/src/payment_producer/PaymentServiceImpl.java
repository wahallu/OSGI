package payment_producer;


import payment_producer.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private List<Payment> paymentList = new ArrayList<>();

    @Override
    public void processPayment(Payment payment) {
        // Simulating processing payment
        payment.setPaymentStatus("Processed");
        paymentList.add(payment);
        System.out.println("Payment processed: " + payment);
    }

    @Override
    public Payment viewPaymentDetails(String paymentId) {
        // Find and return the payment with the given paymentId
        for (Payment payment : paymentList) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentList;
    }

    @Override
    public void updatePaymentStatus(String paymentId, String status) {
        // Find the payment and update the status
        for (Payment payment : paymentList) {
            if (payment.getPaymentId().equals(paymentId)) {
                payment.setPaymentStatus(status);
                System.out.println("Payment status updated: " + payment);
                return;
            }
        }
        System.out.println("Payment not found");
    }

    @Override
    public void refundPayment(String paymentId) {
        // Find the payment and set it as refunded
        for (Payment payment : paymentList) {
            if (payment.getPaymentId().equals(paymentId)) {
                payment.setPaymentStatus("Refunded");
                System.out.println("Payment refunded: " + payment);
                return;
            }
        }
        System.out.println("Payment not found for refund");
    }
}
