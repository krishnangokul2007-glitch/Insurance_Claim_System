package service;
import java.util.ArrayList;
import exception.InvalidPaymentException;
import exception.PolicyNotFoundException;
import model.Policy;
import model.PremiumPayment;
public class PremiumService {
    private ArrayList<PremiumPayment> payments = new ArrayList<>();
    private PolicyService policyService;
    private int nextId = 301;
    public PremiumService(PolicyService policyService) {
        this.policyService = policyService;
    }
    public PremiumPayment recordPremium(int policyId, double amount) throws PolicyNotFoundException, InvalidPaymentException {
        Policy policy = policyService.findPolicy(policyId);
        if (policy.getPremiumBalance() == 0) {
            throw new InvalidPaymentException("Premium for this policy is already fully paid.");
        }
        if (amount > policy.getPremiumBalance()) {
            throw new InvalidPaymentException(
            String.format("Amount is more than the balance due (Rs. %.2f).", policy.getPremiumBalance()));
        }
        policy.addPremium(amount);
        PremiumPayment payment = new PremiumPayment(nextId, policy, amount);
        nextId++;
        payments.add(payment);
        return payment;
    }
}
