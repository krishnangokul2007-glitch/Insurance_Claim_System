package model;
public class VehiclePolicy extends Policy {
    public VehiclePolicy(int policyId, Customer customer, double sumInsured) {
        super(policyId, customer, sumInsured);
    }
    @Override
    public String getPolicyType() {
        return "Vehicle";
    }
    @Override
    public double calculatePremium() {
        return getSumInsured() * 0.04;
    }
    @Override
    public double getMaxClaimAmount() {
        return getSumInsured() * 0.80;
    }
}
