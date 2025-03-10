package delivery_publisher;

public class Delivery {
    private String orderId;
    private String customerName;
    private String deliveryAddress;
    private String status;
    private String deliveryDate;

    // Constructor
    public Delivery(String orderId, String customerName, String deliveryAddress, String status, String deliveryDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.deliveryDate = deliveryDate;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status='" + status + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}