package model;
import util.DateUtil;
public class ClaimDecision {
    private int decisionId;
    private Claim claim;
    private boolean approved;
    private double approvedAmount;
    private String reason;
    private String decisionDate;
    public ClaimDecision(int decisionId, Claim claim, boolean approved, double approvedAmount, String reason) {
        this.decisionId = decisionId;
        this.claim = claim;
        this.approved = approved;
        this.approvedAmount = approvedAmount;
        this.reason = reason;
        this.decisionDate = DateUtil.now();
    }
    public int getDecisionId() {
        return decisionId;
    }
    public Claim getClaim() {
        return claim;
    }
    public boolean isApproved() {
        return approved;
    }
    public double getApprovedAmount() {
        return approvedAmount;
    }
    public String getReason() {
        return reason;
    }
    public void display() {
        System.out.println();
        System.out.println("============ CLAIM DECISION ============");
        System.out.println("Decision ID : " + decisionId);
        System.out.println("Claim ID		: " + claim.getClaimId());
        System.out.println("Date	: " + decisionDate);
        System.out.println("Decision	: " + (approved ? "APPROVED" : "REJECTED"));
        if (approved) {
            System.out.printf("Approved Amount: Rs. %.2f%n", approvedAmount);
        }
        System.out.println("Reason	: " + reason);
        System.out.println("========================================");
    }
}
