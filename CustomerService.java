package service;
import java.util.ArrayList;
import exception.CustomerNotFoundException;
import model.Customer;
public class CustomerService {
    private ArrayList<Customer> customers = new ArrayList<>();
    private int nextId = 101;
    public Customer addCustomer(String name, String phone, String email) {
        Customer customer = new Customer(nextId, name, phone, email);
        nextId++;
        customers.add(customer);
        return customer;
    }
    public Customer findCustomer(int customerId) throws CustomerNotFoundException {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
    }
}
