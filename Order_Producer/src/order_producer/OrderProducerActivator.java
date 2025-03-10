package order_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class OrderProducerActivator implements BundleActivator {

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Order Producer Bundle...");

        OrderService orderService = new OrderServiceImpl();
        registration = context.registerService(OrderService.class.getName(), orderService, null);

        System.out.println("Order Service registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Order Producer Bundle...");

        registration.unregister();

        System.out.println("Order Service unregistered.");
    }
}