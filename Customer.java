package model;
import java.util.ArrayList;
public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private String email;
    private ArrayList<Policy> policies = new ArrayList<>();
    public Customer(int customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public int getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public ArrayList<Policy> getPolicies() {
        return policies;
    }
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
    public void display() {
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name		: " + name);
        System.out.println("Phone	 : " + phone);
        System.out.println("Email	: " + email);
    }
}
