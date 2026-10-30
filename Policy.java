package model;
import util.DateUtil;
public abstract class Policy {
    private int policyId;
    private Customer customer;
    private double sumInsured;
    private double amountPaid;
    private double totalPaidOut;
    private String status;
    private String startDate;
    public Policy(int policyId, Customer customer, double sumInsured) {
        this.policyId = policyId;
        this.customer = customer;
        this.sumInsured = sumInsured;
        this.amountPaid = 0;
        this.totalPaidOut = 0;
        this.status = "INACTIVE";
        this.startDate = DateUtil.today();
    }
    // Each policy type calculates these in its own way
    public abstract String getPolicyType();
    public abstract double calculatePremium();
    public abstract double getMaxClaimAmount();
    public int getPolicyId() {
        return policyId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public double getSumInsured() {
        return sumInsured;
    }
    public double getAmountPaid() {
        return amountPaid;
    }
    public String getStatus() {
        return status;
    }
    public boolean isActive() {
        return status.equals("ACTIVE");
    }
    public double getPremiumBalance() {
        double balance = calculatePremium() - amountPaid;
        if (balance < 0) {
            return 0;
        }
        return balance;
    }
    public String getPremiumStatus() {
        if (amountPaid == 0) {
            return "PENDING";
        }
        else if (getPremiumBalance() > 0) {
            return "PARTIAL";
        }
        return "PAID";
    }
    public double getRemainingCoverage() {
        return getMaxClaimAmount() - totalPaidOut;
    }
    // Policy becomes ACTIVE after the first premium payment
    public void addPremium(double amount) {
        amountPaid = amountPaid + amount;
        status = "ACTIVE";
    }
    public void addPayout(double amount) {
        totalPaidOut = totalPaidOut + amount;
    }
    public void display() {
        System.out.println("Policy ID	: " + policyId);
        System.out.println("Policy Type		: " + getPolicyType());
        System.out.println("Customer		: " + customer.getName() + " (ID " + customer.getCustomerId() + ")");
        System.out.println("Start Date	: " + startDate);
        System.out.printf("Sum Insured	: Rs. %.2f%n", sumInsured);
        System.out.printf("Premium Due		: Rs. %.2f%n", calculatePremium());
        System.out.printf("Max Claim Limit : Rs. %.2f%n", getMaxClaimAmount());
        System.out.println("Policy Status : " + status);
        System.out.println("Premium Status : " + getPremiumStatus());
    }
}
