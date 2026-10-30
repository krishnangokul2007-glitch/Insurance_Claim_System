package model;
import java.util.ArrayList;
import util.DateUtil;
public class Claim {
    private int claimId;
    private Customer customer;
    private Policy policy;
    private double claimAmount;
    private String description;
    private String status;
    private ArrayList<String> timeline = new ArrayList<>();
    private ClaimAssessment assessment;
    private ClaimDecision decision;
    private Payout payout;
    public Claim(int claimId, Customer customer, Policy policy, double claimAmount, String description) {
        this.claimId = claimId;
        this.customer = customer;
        this.policy = policy;
        this.claimAmount = claimAmount;
        this.description = description;
        this.status = "FILED";
        addEvent("FILED", "Claim filed for Rs. " + String.format("%.2f", claimAmount));
    }
    public int getClaimId() {
        return claimId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Policy getPolicy() {
        return policy;
    }
    public double getClaimAmount() {
        return claimAmount;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ClaimAssessment getAssessment() {
        return assessment;
    }
    public void setAssessment(ClaimAssessment assessment) {
        this.assessment = assessment;
    }
    public ClaimDecision getDecision() {
        return decision;
    }
    public void setDecision(ClaimDecision decision) {
        this.decision = decision;
    }
    public Payout getPayout() {
        return payout;
    }
    public void setPayout(Payout payout) {
        this.payout = payout;
    }
    public void addEvent(String eventStatus, String note) {
        timeline.add(DateUtil.now() + " | " + eventStatus + " | " + note);
    }
    public void displayTimeline() {
        System.out.println();
        System.out.println("---------- CLAIM STATUS TIMELINE (Claim " + claimId + ")	");
        for (String event : timeline) {
            System.out.println(event);
        }
        System.out.println("Current Status: " + status);
        System.out.println("	");
    }
    public void display() {
        System.out.println();
        System.out.println("============== CLAIM FILED ==============");
        System.out.println("Claim ID	: " + claimId);
        System.out.println("Customer		: " + customer.getName() + " (ID " + customer.getCustomerId() + ")");
        System.out.println("Policy	: " + policy.getPolicyId() + " (" + policy.getPolicyType() + ")");
        System.out.printf("Claim Amount : Rs. %.2f%n", claimAmount);
        System.out.println("Description : " + description);
        System.out.println("Status	: " + status);
        System.out.println("=========================================");
    }
}
