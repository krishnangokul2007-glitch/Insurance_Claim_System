package model;
import util.DateUtil;
public class ClaimAssessment {
    private int assessmentId;
    private Claim claim;
    private String assessorName;
    private boolean eligible;
    private double approvedAmount;
    private String remarks;
    private String assessmentDate;
    public ClaimAssessment(int assessmentId, Claim claim, String assessorName, boolean eligible, double approvedAmount, String remarks) {
        this.assessmentId = assessmentId;
        this.claim = claim;
        this.assessorName = assessorName;
        this.eligible = eligible;
        this.approvedAmount = approvedAmount;
        this.remarks = remarks;
        this.assessmentDate = DateUtil.now();
    }
    public int getAssessmentId() {
        return assessmentId;
    }
    public Claim getClaim() {
        return claim;
    }
    public String getAssessorName() {
        return assessorName;
    }
    public boolean isEligible() {
        return eligible;
    }
    public double getApprovedAmount() {
        return approvedAmount;
    }
    public String getRemarks() {
        return remarks;
    }
    public void display() {
        System.out.println();
        System.out.println("=========== CLAIM ASSESSMENT ===========");
        System.out.println("Assessment ID : " + assessmentId);
        System.out.println("Claim ID			: " + claim.getClaimId());
        System.out.println("Assessor		: " + assessorName);
        System.out.println("Date	: " + assessmentDate);
        System.out.printf("Claim Amount : Rs. %.2f%n", claim.getClaimAmount());
        System.out.printf("Remaining Cover: Rs. %.2f%n", claim.getPolicy().getRemainingCoverage());
        System.out.println("Recommendation : " + (eligible ? "APPROVE" : "REJECT"));
        System.out.println("Remarks	: " + remarks);
        System.out.println("========================================");
    }
}
