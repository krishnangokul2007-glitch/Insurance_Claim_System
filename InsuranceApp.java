package main;
import exception.InsuranceException;
import model.Claim;
import model.ClaimAssessment;
import model.ClaimDecision;
import model.Customer;
import model.Payout;
import model.Policy;
import model.PremiumPayment;
import service.ClaimService;
import service.CustomerService;
import service.PolicyService;
import service.PremiumService;
import util.InputHelper;
public class InsuranceApp {
    private static CustomerService customerService = new CustomerService();
    private static PolicyService policyService = new PolicyService(customerService);
    private static PremiumService premiumService = new PremiumService(policyService);
    private static ClaimService claimService = new ClaimService(customerService, policyService);
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = InputHelper.readInt("Enter your choice (1-8): ", 1, 8);
            switch (choice) {
                case 1:
                addCustomer();
                break;
                case 2:
                createPolicy();
                break;
                case 3:
                recordPremium();
                break;
                case 4:
                fileClaim();
                break;
                case 5:
                assessClaim();
                break;
                case 6:
                decideClaim();
                break;
                case 7:
                processPayout();
                break;
                case 8:
                System.out.println("Thank you for using the system. Goodbye!");
                running = false;
                break;
            }
        }
    }
    private static void showMenu() {
        System.out.println();
        System.out.println("===== INSURANCE POLICY & CLAIMS MANAGEMENT =====");
        System.out.println("1. Add Customer");
        System.out.println("2. Create Policy");
        System.out.println("3. Record Premium");
        System.out.println("4. File Claim");
        System.out.println("5. Assess Claim");
        System.out.println("6. Decide Claim");
        System.out.println("7. Process Payout");
        System.out.println("8. Exit");
        System.out.println("================================================");
    }
    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        String name = InputHelper.readName("Enter name: ");
        String phone = InputHelper.readPhone("Enter phone (10 digits): ");
        String email = InputHelper.readEmail("Enter email: ");
        Customer customer = customerService.addCustomer(name, phone, email);
        System.out.println("\nCustomer added successfully!");
        customer.display();
    }
    private static void createPolicy() {
        System.out.println("\n--- Create Policy ---");
        try {
            int customerId = InputHelper.readId("Enter customer ID: ");
            customerService.findCustomer(customerId);
            System.out.println("Policy types: 1. Health 2. Vehicle 3. Life");
            int type = InputHelper.readInt("Choose policy type (1-3): ", 1, 3);
            double sumInsured = InputHelper.readAmount("Enter sum insured (Rs.): ");
            Policy policy = policyService.createPolicy(customerId, type, sumInsured);
            System.out.println("\nPolicy created successfully! Pay the premium to activate it.");
            policy.display();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void recordPremium() {
        System.out.println("\n--- Record Premium ---");
        try {
            int policyId = InputHelper.readId("Enter policy ID: ");
            double amount = InputHelper.readAmount("Enter premium amount paid (Rs.): ");
            PremiumPayment payment = premiumService.recordPremium(policyId, amount);
            payment.displayReceipt();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void fileClaim() {
        System.out.println("\n--- File Claim ---");
        try {
            int customerId = InputHelper.readId("Enter customer ID: ");
            int policyId = InputHelper.readId("Enter policy ID: ");
            double amount = InputHelper.readAmount("Enter claim amount (Rs.): ");
            String description = InputHelper.readText("Enter claim description: ");
            Claim claim = claimService.fileClaim(customerId, policyId, amount, description);
            claim.display();
            claim.displayTimeline();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void assessClaim() {
        System.out.println("\n--- Assess Claim ---");
        try {
            int claimId = InputHelper.readId("Enter claim ID: ");
            String assessor = InputHelper.readName("Enter assessor name: ");
            ClaimAssessment assessment = claimService.assessClaim(claimId, assessor);
            assessment.display();
            assessment.getClaim().displayTimeline();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void decideClaim() {
        System.out.println("\n--- Decide Claim ---");
        try {
            int claimId = InputHelper.readId("Enter claim ID: ");
            ClaimDecision decision = claimService.decideClaim(claimId);
            decision.display();
            decision.getClaim().displayTimeline();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void processPayout() {
        System.out.println("\n--- Process Payout ---");
        try {
            int claimId = InputHelper.readId("Enter claim ID: ");
            Payout payout = claimService.processPayout(claimId);
            payout.displayConfirmation();
            payout.getClaim().displayTimeline();
        }
        catch (InsuranceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
