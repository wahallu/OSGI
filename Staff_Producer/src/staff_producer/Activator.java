package staff_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<?> serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Staff Producer Started");

        
        StaffSalaryService staffService = new StaffSalaryServiceImpl(); 
        serviceRegistration = bundleContext.registerService(StaffSalaryService.class.getName(), staffService, null);

        System.out.println("StaffService Registered Successfully");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping Staff Producer");

        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }

        System.out.println("Staff Producer Stopped");
    }
}
