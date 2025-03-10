package delivery_publisher;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Delivery Producer Bundle Started");

        // Create the DeliveryServiceImpl instance
        DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

        // Register the service
        serviceRegistration = context.registerService(DeliveryService.class.getName(), deliveryService, null);
        System.out.println("DeliveryService is now registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Delivery Producer Bundle Stopped");

        // Unregister the service
        serviceRegistration.unregister();
        System.out.println("DeliveryService is unregistered.");
    }
}