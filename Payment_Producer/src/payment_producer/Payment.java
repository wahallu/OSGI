package payment_producer;


public class Payment {
    private String paymentId;
    private String customerId;
    private double amount;
    private String paymentDate;
    private String paymentStatus;

    public Payment(String paymentId, String customerId, double amount, String paymentDate, String paymentStatus) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", customerId=" + customerId + ", amount=" + amount 
                + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + "]";
    }
}

