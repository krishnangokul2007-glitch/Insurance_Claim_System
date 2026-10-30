package model;
public class LifePolicy extends Policy {
    public LifePolicy(int policyId, Customer customer, double sumInsured) {
        super(policyId, customer, sumInsured);
    }
    @Override
    public String getPolicyType() {
        return "Life";
    }
    @Override
    public double calculatePremium() {
        return getSumInsured() * 0.02;
    }
    @Override
    public double getMaxClaimAmount() {
        return getSumInsured();
    }
}
