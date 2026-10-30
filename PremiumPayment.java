package model;
import util.DateUtil;
public class PremiumPayment {
    private int paymentId;
    private Policy policy;
    private double amount;
    private String paymentDate;
    private double balanceAfter;
    private String premiumStatusAfter;
    public PremiumPayment(int paymentId, Policy policy, double amount) {
        this.paymentId = paymentId;
        this.policy = policy;
        this.amount = amount;
        this.paymentDate = DateUtil.now();
        this.balanceAfter = policy.getPremiumBalance();
        this.premiumStatusAfter = policy.getPremiumStatus();
    }
    public int getPaymentId() {
        return paymentId;
    }
    public Policy getPolicy() {
        return policy;
    }
    public double getAmount() {
        return amount;
    }
    public void displayReceipt() {
        System.out.println();
        System.out.println("========== PREMIUM PAYMENT RECEIPT ==========");
        System.out.println("Receipt No		 : " + paymentId);
        System.out.println("Date	: " + paymentDate);
        System.out.println("Customer		: " + policy.getCustomer().getName());
        System.out.println("Policy ID	: " + policy.getPolicyId() + " (" + policy.getPolicyType() + ")");
        System.out.printf("Total Premium : Rs. %.2f%n", policy.calculatePremium());
        System.out.printf("Amount Paid Now : Rs. %.2f%n", amount);
        System.out.printf("Total Paid	: Rs. %.2f%n", policy.getAmountPaid());
        System.out.printf("Balance Due		: Rs. %.2f%n", balanceAfter);
        System.out.println("Premium Status : " + premiumStatusAfter);
        System.out.println("Policy Status : " + policy.getStatus());
        System.out.println("=============================================");
    }
}
