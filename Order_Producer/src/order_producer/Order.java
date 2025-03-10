package order_producer;

public class Order {
    private String orderId;
    private String customerName;
    private String[] items;
    private double totalPrice;

    public Order() {
    }

    public Order(String orderId, String customerName, String[] items, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
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
    public String[] getItems() {
        return items;
    }
    public void setItems(String[] items) {
        this.items = items;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{orderId=").append(orderId)
          .append(", customerName='").append(customerName).append('\'')
          .append(", items=");
        if (items != null) {
            sb.append("[");
            for (int i = 0; i < items.length; i++) {
                sb.append(items[i]);
                if (i < items.length - 1) sb.append(", ");
            }
            sb.append("]");
        } else {
            sb.append("null");
        }
        sb.append(", totalPrice=").append(totalPrice)
          .append("}");
        return sb.toString();
    }
}
