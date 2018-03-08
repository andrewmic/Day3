package swedbank.it.academy.service;

import swedbank.it.academy.domain.*;
import swedbank.it.academy.util.DateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class LoanService implements LoanServiceInterface{

    private Loan[] loans;

    //I don't know who told you that you need to save temp. values in LoanService, but he was wrong! :))
    //LoanService should to have a state, in other words, you should not store temp. data in LoanService fields, instead,
    // you should calculate data in methods and just return in.
    //NOW, if I would call some methods multiple times, I would get different results :)
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
    //This should return subset (Loan array) of only high risk loans
    public Loan[] getHighRiskLoans() {
        //Here is some code to get you on a right track
/*        Loan[] highRiskLoans = new Loan[0];
        int highRiskLoanIndex = 0;
        for (Loan loan : loans) {
            if (loan.getRiskType() == LoanRiskType.HIGH_RISK) {
                //TODO: Expand highRiskLoans by 1 element here
                highRiskLoans[highRiskLoanIndex++] = loan;
            }
        }
        return highRiskLoans;*/

        for (Loan loan : loans) {
            //You don't need to check if it is valid here
            if((loan.getRiskType() == LoanRiskType.HIGH_RISK) == loan.isValid())
                Loan.highRiskCounter++;
        }
        return loans;
    }

    //Calculation is correct, but I would call it second time, I would get different result (averageLoanCost is not being reset)
    public BigDecimal getAverageLoanCost(){
        for (Loan loan : loans)
            averageLoanCost = averageLoanCost.add(loan.getPrice().multiply(BigDecimal.ONE.add(loan.getInterestRate().divide(BigDecimal.valueOf(100)))));

        return averageLoanCost.divide(BigDecimal.valueOf(loans.length)).setScale(1, RoundingMode.CEILING);
    }

    //Calculation is correct
    public BigDecimal getAverageLoanCost(LoanRiskType loanRiskType){
        count = 0;
        averageLoanCost = BigDecimal.ZERO;
        for (Loan loan : loans){
            //You don't need to check if it is valid here
            if((loanRiskType == loan.getRiskType()) == loan.isValid()) {
                count++;
                averageLoanCost = averageLoanCost.add(loan.getPrice().add(loan.getPrice().multiply(loan.getInterestRate().divide(BigDecimal.valueOf(100)))));
            }
        }
        return averageLoanCost.divide(BigDecimal.valueOf(count)).setScale(0, RoundingMode.CEILING);
    }

    public BigDecimal getAverageCostOfHighRiskLoans(){
        //You can re-use method above
        //return getAverageLoanCost(LoanRiskType.HIGH_RISK);
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
        //Wayyyy too complicated
        //See me approach
/*
        BigDecimal maximumPrice = BigDecimal.ZERO; //Let's say that min. possible price is 0
        for (Loan loan : loans) {
            BigDecimal price = loan.getPrice();
            if (loan.isValid() && //Loan.isValid() SHOULD do this check already (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date()))
                price.compareTo(maximumPrice) > 0) {
                maximumPrice = price;
            }
        }
        return maximumPrice;
        */
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

    //This should return only normal risk "VehicleLoan"s. See what I've wrote about "getHighRiskLoans()"
    public Loan[] getNormalRiskVehicleLoans(){
        for (Loan loan: loans) {
            //You don't need to check if it is valid here
            if ((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.NORMAL_RISK) == loan.isValid()) {
                VehicleLoan.normalRiskVehicleLoansCounter++;
            }
        }
        return loans;
    }

    //Again, wayyy too complicated approach. See my comment on getMaximumPriceOfNonExpiredLoans()
    public int getMaximumAgeOfLowRiskLoanedVehicles() {

        for (Loan loan: loans) {
            //You don't need to check if it is valid here
            if ((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.LOW_RISK) == loan.isValid()) {
                //Age should be calculated in VehicleLoan.getAge()
                tempMaximumAgeOfLowRiskLoanedVehicles = ((int)DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365);

                break;
            }
        }
        for (Loan loan: loans){
            //You don't need to check if it is valid here
            if((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.LOW_RISK) == loan.isValid()) {
                //Age should be calculated in VehicleLoan.getAge()
                if (tempMaximumAgeOfLowRiskLoanedVehicles < DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365)
                    tempMaximumAgeOfLowRiskLoanedVehicles = (int)DateUtil.differenceInDays(loan.getCreationDate(),((VehicleLoan) loan).getManufactured())/365;
            }
        }
        maximumAgeOfLowRiskLoanedVehicles = tempMaximumAgeOfLowRiskLoanedVehicles;

        return maximumAgeOfLowRiskLoanedVehicles;
    }

    //This should return only "RealEstateLoan"s with purpose == RealEstatePurpose.PERSONAL. See what I've wrote about "getHighRiskLoans()"
    public Loan[] getPersonalRealEstateLoans(){
        for (Loan loan: loans){
            //You don't need to check if it is valid here
            if((loan instanceof RealEstateLoan && (((RealEstateLoan) loan).getPurpose() == String.valueOf(RealEstatePurpose.PERSONAL))) == loan.isValid()){
                RealEstateLoan.personalRealEstateLoans++;
            }
        }
        return loans;
    }

    //This should return only "VehicleLoan"s with riskType == LoanRiskType.HIGH_RISK && !loan.isValid() && max(loan.getTermInYears()). See what I've wrote about "getHighRiskLoans()"
    public  Loan[] getExpiredHighRiskVehicleLoansOfHighestDuration(){

        for (Loan loan: loans){
            if((loan instanceof VehicleLoan && loan.getRiskType() == LoanRiskType.HIGH_RISK) == loan.isValid()){
                tempMaximumAgeOfLowRiskLoanedVehicles = loan.getTermInYears();
                break;
            }
        }

        for (Loan loan: loans){
            //Loan.isValid() SHOULD do this check already
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
