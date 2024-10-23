abstract class Approver {
    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void processRequest(ExpenseRequest request);
}

class TeamLead extends Approver {
    @Override
    public void processRequest(ExpenseRequest request) {
        if (request.getAmount() <= 500) {
            System.out.println("Team Lead approved the expense request of $" + request.getAmount());
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);
        }
    }
}

class Manager extends Approver {
    @Override
    public void processRequest(ExpenseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("Manager approved the expense request of $" + request.getAmount());
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);
        }
    }
}

class Director extends Approver {
    @Override
    public void processRequest(ExpenseRequest request) {
        if (request.getAmount() <= 20000) {
            System.out.println("Director approved the expense request of $" + request.getAmount());
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);
        }
    }
}

class CEO extends Approver {
    @Override
    public void processRequest(ExpenseRequest request) {
        if (request.getAmount() > 20000) {
            System.out.println("CEO approved the expense request of $" + request.getAmount());
        }
    }
}


class ExpenseRequest {
    private double amount;
    private String purpose;

    public ExpenseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }
}

public class Main {
    public static void main(String[] args) {
        Approver teamLead = new TeamLead();
        Approver manager = new Manager();
        Approver director = new Director();
        Approver ceo = new CEO();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        ExpenseRequest request1 = new ExpenseRequest(450, "Team Lunch");
        ExpenseRequest request2 = new ExpenseRequest(4000, "Office Supplies");
        ExpenseRequest request3 = new ExpenseRequest(12000, "New Computers");
        ExpenseRequest request4 = new ExpenseRequest(25000, "Company Event");

        teamLead.processRequest(request1);
        teamLead.processRequest(request2);
        teamLead.processRequest(request3);
        teamLead.processRequest(request4);
    }
}
