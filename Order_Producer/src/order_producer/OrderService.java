package order_producer;

public interface OrderService {
	
	void placeOrder(Order order);
	
    Order getOrder(String orderId);

}
