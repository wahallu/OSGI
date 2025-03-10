package payment_producer;



import payment_producer.Payment;
import java.util.List;

public interface PaymentService {
    void processPayment(Payment payment);
    Payment viewPaymentDetails(String paymentId);
    List<Payment> getAllPayments();
    void updatePaymentStatus(String paymentId, String status);
    void refundPayment(String paymentId);
}

