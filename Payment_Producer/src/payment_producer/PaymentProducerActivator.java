package payment_producer;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import payment_producer.PaymentService;
import payment_producer.PaymentServiceImpl;

public class PaymentProducerActivator implements BundleActivator {

    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Payment Producer Started");

        // Creating an instance of PaymentServiceImpl
        PaymentService paymentService = new PaymentServiceImpl();

        // Registering the service in OSGi container
        serviceRegistration = context.registerService(PaymentService.class.getName(), paymentService, null);
        System.out.println("Payment Service registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Payment Producer Stopped");

        // Unregister the service
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
