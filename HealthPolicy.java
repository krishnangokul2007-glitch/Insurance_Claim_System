package model;
public class HealthPolicy extends Policy {
    public HealthPolicy(int policyId, Customer customer, double sumInsured) {
        super(policyId, customer, sumInsured);
    }
    @Override
    public String getPolicyType() {
        return "Health";
    }
    @Override
    public double calculatePremium() {
        return getSumInsured() * 0.05;
    }
    @Override
    public double getMaxClaimAmount() {
        return getSumInsured();
    }
}
