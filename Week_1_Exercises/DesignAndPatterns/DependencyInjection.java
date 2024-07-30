interface CustomerRepository {
    String findCustomerById(String customerId);
}

class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String customerId) {
        if (customerId.equals("1")) {
            return "John Doe";
        } else {
            return "Customer not found";
        }
    }
}

class CustomerService {
    private CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public String getCustomerDetails(String customerId) {
        return customerRepo.findCustomerById(customerId);
    }
}

public class DependencyInjection {
    public static void main(String[] args) {
        var customerRepo = new CustomerRepositoryImpl();
        var customerService = new CustomerService(customerRepo);
        var customerDetails = customerService.getCustomerDetails("1");
        System.out.println("Customer Details: " + customerDetails);
    }
}
