package swedbank.it.academy.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleLoan extends Loan{
    private Date manufactured;
    private String model;
    private int maximumAge;
    public static int normalRiskVehicleLoansCounter = 0;
    public static int expiredHighRiskVehicleLoansCounter = 0;
    public static int highestDuration = 0;


    public Date getManufactured() {
        return manufactured;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public int getAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public void setInterestRate(BigDecimal interestRate){
        if(getRiskType() == LoanRiskType.HIGH_RISK){
            this.interestRate = BigDecimal.valueOf(1.5);
        }
        else if (getRiskType() == LoanRiskType.NORMAL_RISK){
            this.interestRate = BigDecimal.valueOf(1.0);
        }
        else if(getRiskType() == LoanRiskType.LOW_RISK){
            this.interestRate = BigDecimal.valueOf(0.8);

        }
    }
}
