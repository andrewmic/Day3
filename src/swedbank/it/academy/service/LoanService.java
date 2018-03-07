package swedbank.it.academy.service;

import swedbank.it.academy.domain.*;
import swedbank.it.academy.util.DateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by p998feq on 2018.03.06.
 */
public class LoanService implements LoanServiceInterface{

    private Loan[] loans;
    private BigDecimal highRiskLoans = BigDecimal.ZERO;
    private BigDecimal averageLoanCost = BigDecimal.ZERO;
    private int count;
    private BigDecimal temp = BigDecimal.ZERO;
    private int res = 0;
    private int maximumAgeOfLowRiskLoanedVehicles = 0;
    private int tempMaximumAgeOfLowRiskLoanedVehicles = 0;

    public LoanService(Loan[] loans) {
        this.loans = loans;
    }

    //TASK1
    public Loan[] getHighRiskLoans() {
        for (Loan loan : loans) {
            if((loan.getRiskType() == LoanRiskType.HIGH_RISK) == loan.isValid())
                Loan.highRiskCounter++;
        }
        return loans;
    }

    public BigDecimal getAverageLoanCost(){
        for (Loan loan : loans)
            averageLoanCost = averageLoanCost.add(loan.getPrice().multiply(BigDecimal.ONE.add(loan.getInterestRate().divide(BigDecimal.valueOf(100)))));

        return averageLoanCost.divide(BigDecimal.valueOf(loans.length)).setScale(1, RoundingMode.CEILING);
    }

    public BigDecimal getAverageLoanCost(LoanRiskType loanRiskType){
        count = 0;
        averageLoanCost = BigDecimal.ZERO;
        for (Loan loan : loans){
            if((loanRiskType == loan.getRiskType()) == loan.isValid()) {
                count++;
                averageLoanCost = averageLoanCost.add(loan.getPrice().add(loan.getPrice().multiply(loan.getInterestRate().divide(BigDecimal.valueOf(100)))));
            }
        }
        return averageLoanCost.divide(BigDecimal.valueOf(count)).setScale(0, RoundingMode.CEILING);
    }

    public BigDecimal getAverageCostOfHighRiskLoans(){
        count = 0;
        for (Loan loan : loans){
            if((LoanRiskType.HIGH_RISK == loan.getRiskType()) == loan.isValid()) {
                count++;
                highRiskLoans = highRiskLoans.add(loan.getPrice().add(loan.getPrice().multiply(loan.getInterestRate().divide(BigDecimal.valueOf(100)))));
            }
        }
        return highRiskLoans.divide(BigDecimal.valueOf(count)).setScale(0, RoundingMode.CEILING);
    }

    public BigDecimal getMaximumPriceOfNonExpiredLoans(){
        for (Loan loan : loans){
            if((DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date())) == loan.isValid()){
                temp.equals(loan.getPrice());
                break;
            }
        }
        for (Loan loan : loans){
            if((DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date())) == loan.isValid()){
                res = loan.getPrice().compareTo(temp);
                if (res == 1){
                    temp = loan.getPrice();
                }
            }
        }
        return temp;
    }
    //TASK2
    public Loan[] getNormalRiskVehicleLoans(){
        for (Loan loan: loans) {
            if ((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.NORMAL_RISK) == loan.isValid()) {
                VehicleLoan.normalRiskVehicleLoansCounter++;
            }
        }
        return loans;
    }

    public int getMaximumAgeOfLowRiskLoanedVehicles() {

        for (Loan loan: loans) {
            if ((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.LOW_RISK) == loan.isValid()) {

                tempMaximumAgeOfLowRiskLoanedVehicles = ((int)DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365);

                break;
            }
        }
        for (Loan loan: loans){

            if((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.LOW_RISK) == loan.isValid()) {
                if (tempMaximumAgeOfLowRiskLoanedVehicles < DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365)
                    tempMaximumAgeOfLowRiskLoanedVehicles = (int)DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365;
            }
        }
        maximumAgeOfLowRiskLoanedVehicles = tempMaximumAgeOfLowRiskLoanedVehicles;

        return maximumAgeOfLowRiskLoanedVehicles;
    }

    public Loan[] getPersonalRealEstateLoans(){
        for (Loan loan: loans){
            if((loan instanceof RealEstateLoan && (((RealEstateLoan) loan).getPurpose() == String.valueOf(RealEstatePurpose.PERSONAL))) == loan.isValid()){
                RealEstateLoan.personalRealEstateLoans++;
            }
        }
        return loans;
    }
    public  Loan[] getExpiredHighRiskVehicleLoansOfHighestDuration(){

        for (Loan loan: loans){
            if((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.HIGH_RISK) == loan.isValid()){
                tempMaximumAgeOfLowRiskLoanedVehicles = loan.getTermInYears();
                break;
            }
        }

        for (Loan loan: loans){
            if((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.HIGH_RISK && DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).before(new Date())) == loan.isValid()){
                VehicleLoan.expiredHighRiskVehicleLoansCounter++;

                if(tempMaximumAgeOfLowRiskLoanedVehicles < loan.getTermInYears()){
                    tempMaximumAgeOfLowRiskLoanedVehicles = loan.getTermInYears();
                }
            }
        }
        VehicleLoan.highestDuration = tempMaximumAgeOfLowRiskLoanedVehicles;

        return loans;
    }
    //TASK3
    public Loan[] getLowRiskHarvesterLoans(){
        for (Loan loan: loans){
            if((loan instanceof HarvesterLoan && loan.getRiskType() == LoanRiskType.LOW_RISK) == loan.isValid()){
                HarvesterLoan.lowRiskHarvesterLoansCounter++;
            }
        }

        return loans;
    }

    public Loan[] getExpiredLandLoansInReservation() {
        for (Loan loan: loans) {
            if ((loan instanceof LandLoan && DateUtil.addYears(loan.getCreationDate(),loan.getTermInYears()).after(new Date())) == loan.isValid()) {
                LandLoan.expiredLandLoansInReservationCounter++;
            }
        }
        return loans;
    }
    public Loan[] getLoansOfHigherThanAverageDepeciation(){
        return loans;
    }
}
