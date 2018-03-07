package swedbank.it.academy.app;
import swedbank.it.academy.domain.*;
import swedbank.it.academy.service.LoanService;

/**
 * Created by p998feq on 2018.03.06.
 */
public class ClientApp {
    public static void main (String[] args){
        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        /*//TASK1
        service.getHighRiskLoans();
        System.out.println("There are " + Loan.highRiskCounter);

        System.out.println(service.getAverageLoanCost());

        System.out.println("NORMAL_RISK: " + service.getAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println("HIGH_RISK: " + service.getAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println("LOW_RISK: " + service.getAverageLoanCost(LoanRiskType.LOW_RISK));

        System.out.println(service.getAverageCostOfHighRiskLoans());

        System.out.println(service.getMaximumPriceOfNonExpiredLoans());
        */

        ///*TASK2
        service.getNormalRiskVehicleLoans();
        System.out.println("There are " + VehicleLoan.normalRiskVehicleLoansCounter);

        System.out.println(service.getMaximumAgeOfLowRiskLoanedVehicles());

        service.getPersonalRealEstateLoans();
        System.out.println("There are " + RealEstateLoan.personalRealEstateLoans);

        service.getExpiredHighRiskVehicleLoansOfHighestDuration();
        System.out.println("There is " + VehicleLoan.expiredHighRiskVehicleLoansCounter + ", and highest duration is " + VehicleLoan.highestDuration );
        //*/

        /*//TASK3
        service.getLowRiskHarvesterLoans();
        System.out.println("There are " + HarvesterLoan.lowRiskHarvesterLoansCounter);
        service.getExpiredLandLoansInReservation();
        System.out.println("There are " + LandLoan.expiredLandLoansInReservationCounter);
        */


    }
    /*
    public static DomainInitializer getInitializer() {
        return new Task1DomainInitializer();
    }
    */
    ///*
    public static DomainInitializer getInitializer() {
        return new Task2DomainInitializer();
    }
    //*/
    /*
    public static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }*/
}
