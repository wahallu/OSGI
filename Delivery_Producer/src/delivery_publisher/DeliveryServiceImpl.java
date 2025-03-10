package delivery_publisher;

import java.util.HashMap;
import java.util.Map;

public class DeliveryServiceImpl implements DeliveryService {
    private Map<String, Delivery> deliveryOrders;

    public DeliveryServiceImpl() {
        deliveryOrders = new HashMap<>();
    }

    @Override
    public void insertDeliveryOrder(Delivery delivery) {
        deliveryOrders.put(delivery.getOrderId(), delivery);
        System.out.println("New delivery order inserted: " + delivery);
    }

    @Override
    public Delivery getDeliveryOrderById(String orderId) {
        return deliveryOrders.get(orderId);
    }

    @Override
    public void updateDeliveryOrderStatus(String orderId, String status) {
        Delivery delivery = deliveryOrders.get(orderId);
        if (delivery != null) {
            delivery.setStatus(status);
            System.out.println("Delivery order updated: " + delivery);
        } else {
            System.out.println("Order not found!");
        }
    }

    @Override
    public void cancelDeliveryOrder(String orderId) {
        Delivery delivery = deliveryOrders.remove(orderId);
        if (delivery != null) {
            System.out.println("Delivery order canceled: " + delivery);
        } else {
            System.out.println("Order not found!");
        }
    }

    @Override
    public void markOrderAsDelivered(String orderId) {
        Delivery delivery = deliveryOrders.get(orderId);
        if (delivery != null) {
            delivery.setStatus("Delivered");
            System.out.println("Delivery order marked as delivered: " + delivery);
        } else {
            System.out.println("Order not found!");
        }
    }
}