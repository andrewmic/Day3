package swedbank.it.academy.domain;

public class LandLoan extends RealEstateLoan{

    boolean isInReservation = true;
    public static int expiredLandLoansInReservationCounter = 0;

    public void setInReservation(boolean inReservation) {
        isInReservation = inReservation;
    }
    public boolean isInReservation(){
        return true;
    }
}
