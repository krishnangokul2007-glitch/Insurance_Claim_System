package model;
import util.DateUtil;
public class Payout {
    private int payoutId;
    private Claim claim;
    private double amount;
    private String payoutDate;
    public Payout(int payoutId, Claim claim, double amount) {
        this.payoutId = payoutId;
        this.claim = claim;
        this.amount = amount;
        this.payoutDate = DateUtil.now();
    }
    public int getPayoutId() {
        return payoutId;
    }
    public Claim getClaim() {
        return claim;
    }
    public double getAmount() {
        return amount;
    }
    public void displayConfirmation() {
        System.out.println();
        System.out.println("=========== PAYOUT CONFIRMATION ===========");
        System.out.println("Payout ID : " + payoutId);
        System.out.println("Date	: " + payoutDate);
        System.out.println("Claim ID			: " + claim.getClaimId());
        System.out.println("Paid To		: " + claim.getCustomer().getName());
        System.out.println("Policy ID : " + claim.getPolicy().getPolicyId());
        System.out.printf("Payout Amount: Rs. %.2f%n", amount);
        System.out.println("Status	 : PAID");
        System.out.printf("Coverage Left: Rs. %.2f%n", claim.getPolicy().getRemainingCoverage());
        System.out.println("===========================================");
    }
}
