package order_producer;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private Map<String, Order> orderStore = new HashMap<>();

    @Override
    public void placeOrder(Order order) {
        if (order != null && order.getOrderId() != null) {
            orderStore.put(order.getOrderId(), order);
            System.out.println("Order placed: " + order);
        }
    }

    @Override
    public Order getOrder(String orderId) {
        return orderStore.get(orderId);
    }
}