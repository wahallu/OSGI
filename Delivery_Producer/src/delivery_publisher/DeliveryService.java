package delivery_publisher;

public interface DeliveryService {
    void insertDeliveryOrder(Delivery delivery);
    Delivery getDeliveryOrderById(String orderId);
    void updateDeliveryOrderStatus(String orderId, String status);
    void cancelDeliveryOrder(String orderId);
    void markOrderAsDelivered(String orderId);
}