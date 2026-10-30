package service;
import java.util.ArrayList;
import exception.ClaimNotAllowedException;
import exception.ClaimNotFoundException;
import exception.CustomerNotFoundException;
import exception.InvalidClaimStateException;
import exception.PolicyNotFoundException;
import model.Claim;
import model.ClaimAssessment;
import model.ClaimDecision;
import model.Customer;
import model.Payout;
import model.Policy;
public class ClaimService {
    private ArrayList<Claim> claims = new ArrayList<>();
    private ArrayList<ClaimAssessment> assessments = new ArrayList<>();
    private ArrayList<ClaimDecision> decisions = new ArrayList<>();
    private ArrayList<Payout> payouts = new ArrayList<>();
    private CustomerService customerService;
    private PolicyService policyService;
    private int nextClaimId = 401;
    private int nextAssessmentId = 501;
    private int nextDecisionId = 601;
    private int nextPayoutId = 701;
    public ClaimService(CustomerService customerService, PolicyService policyService) {
        this.customerService = customerService;
        this.policyService = policyService;
    }
    public Claim findClaim(int claimId) throws ClaimNotFoundException {
        for (Claim claim : claims) {
            if (claim.getClaimId() == claimId) {
                return claim;
            }
        }
        throw new ClaimNotFoundException("Claim with ID " + claimId + " not found.");
    }
    public Claim fileClaim(int customerId, int policyId, double amount, String description)
    throws CustomerNotFoundException, PolicyNotFoundException, ClaimNotAllowedException {
        Customer customer = customerService.findCustomer(customerId);
        Policy policy = policyService.findPolicy(policyId);
        if (policy.getCustomer().getCustomerId() != customer.getCustomerId()) {
            throw new ClaimNotAllowedException("This policy does not belong to the given customer.");
        }
        if (!policy.isActive()) {
            throw new ClaimNotAllowedException(
            "Policy is not active. Pay the premium to activate it before filing a claim.");
        }
        if (!policy.getPremiumStatus().equals("PAID")) {
            throw new ClaimNotAllowedException(String.format(
            "Premium is not fully paid (status: %s, balance Rs. %.2f). Claim not allowed.", policy.getPremiumStatus(), policy.getPremiumBalance()));
        }
        Claim claim = new Claim(nextClaimId, customer, policy, amount, description);
        nextClaimId++;
        claims.add(claim);
        return claim;
    }
    public ClaimAssessment assessClaim(int claimId, String assessorName) throws ClaimNotFoundException, InvalidClaimStateException {
        Claim claim = findClaim(claimId);
        if (!claim.getStatus().equals("FILED")) {
            throw new InvalidClaimStateException(
            "Claim cannot be assessed. Current status: " + claim.getStatus());
        }
        Policy policy = claim.getPolicy();
        double remaining = policy.getRemainingCoverage();
        boolean eligible;
        double approvedAmount;
        String remarks;
        if (claim.getClaimAmount() <= remaining) {
            eligible = true;
            approvedAmount = claim.getClaimAmount();
            remarks = "Claim amount is within the remaining coverage of the policy.";
        }
        else {
            eligible = false;
            approvedAmount = 0;
            remarks = String.format("Claim amount Rs. %.2f exceeds the remaining coverage of Rs. %.2f.", claim.getClaimAmount(), remaining);
        }
        ClaimAssessment assessment = new ClaimAssessment(nextAssessmentId, claim, assessorName, eligible, approvedAmount, remarks);
        nextAssessmentId++;
        assessments.add(assessment);
        claim.setAssessment(assessment);
        claim.setStatus("ASSESSED");
        claim.addEvent("ASSESSED", "Assessed by " + assessorName + " - recommendation: "
        + (eligible ? "APPROVE" : "REJECT"));
        return assessment;
    }
    public ClaimDecision decideClaim(int claimId)
    throws ClaimNotFoundException, InvalidClaimStateException {
        Claim claim = findClaim(claimId);
        if (!claim.getStatus().equals("ASSESSED")) {
            throw new InvalidClaimStateException(
            "Claim must be assessed first. Current status: " + claim.getStatus());
        }
        ClaimAssessment assessment = claim.getAssessment();
        boolean approved = assessment.isEligible();
        ClaimDecision decision = new ClaimDecision(nextDecisionId, claim, approved, assessment.getApprovedAmount(), assessment.getRemarks());
        nextDecisionId++;
        decisions.add(decision);
        claim.setDecision(decision);
        if (approved) {
            claim.setStatus("APPROVED");
            claim.addEvent("APPROVED", "Claim approved for Rs. "
            + String.format("%.2f", decision.getApprovedAmount()));
        }
        else {
            claim.setStatus("REJECTED");
            claim.addEvent("REJECTED", decision.getReason());
        }
        return decision;
    }
    public Payout processPayout(int claimId)
    throws ClaimNotFoundException, InvalidClaimStateException {
        Claim claim = findClaim(claimId);
        if (claim.getStatus().equals("PAID")) {
            throw new InvalidClaimStateException("Payout for this claim is already processed.");
        }
        if (claim.getStatus().equals("REJECTED")) {
            throw new InvalidClaimStateException("Payout not possible. The claim was rejected.");
        }
        if (!claim.getStatus().equals("APPROVED")) {
            throw new InvalidClaimStateException(
            "Payout not possible. Claim is not approved yet (status: " + claim.getStatus() + ").");
        }
        double amount = claim.getDecision().getApprovedAmount();
        Payout payout = new Payout(nextPayoutId, claim, amount);
        nextPayoutId++;
        payouts.add(payout);
        claim.getPolicy().addPayout(amount);
        claim.setPayout(payout);
        claim.setStatus("PAID");
        claim.addEvent("PAID", "Payout of Rs. " + String.format("%.2f", amount) + " processed");
        return payout;
    }
}
