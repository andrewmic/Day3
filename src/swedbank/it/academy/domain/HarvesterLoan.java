package swedbank.it.academy.domain;

public class HarvesterLoan extends VehicleLoan {
    private int capacity;
    public static int lowRiskHarvesterLoansCounter = 0;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
