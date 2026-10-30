package service;
import java.util.ArrayList;
import exception.CustomerNotFoundException;
import exception.PolicyNotFoundException;
import model.Customer;
import model.HealthPolicy;
import model.LifePolicy;
import model.Policy;
import model.VehiclePolicy;
public class PolicyService {
    private ArrayList<Policy> policies = new ArrayList<>();
    private CustomerService customerService;
    private int nextId = 201;
    public PolicyService(CustomerService customerService) {
        this.customerService = customerService;
    }
    // type: 1 = Health, 2 = Vehicle, 3 = Life
    public Policy createPolicy(int customerId, int type, double sumInsured) throws CustomerNotFoundException {
        Customer customer = customerService.findCustomer(customerId);
        Policy policy;
        if (type == 1) {
            policy = new HealthPolicy(nextId, customer, sumInsured);
        }
        else if (type == 2) {
            policy = new VehiclePolicy(nextId, customer, sumInsured);
        }
        else {
            policy = new LifePolicy(nextId, customer, sumInsured);
        }
        nextId++;
        policies.add(policy);
        customer.addPolicy(policy);
        return policy;
    }
    public Policy findPolicy(int policyId) throws PolicyNotFoundException {
        for (Policy policy : policies) {
            if (policy.getPolicyId() == policyId) {
                return policy;
            }
        }
        throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
    }
}
