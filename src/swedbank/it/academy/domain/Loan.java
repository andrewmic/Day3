package swedbank.it.academy.domain;
import java.math.BigDecimal;
import java.util.Date;


public class Loan {

    private Date creationDate;
    private int termInYears;
    private String name;
    //This should be private
    //VehicleLoan should setInterestRate(...) instead of directly accessing it
    protected BigDecimal interestRate;
    private BigDecimal price;
    private LoanRiskType riskType;
    //A Loan should not know anything about other Loans.
    //LoanService.getHighRiskLoans() should count this value internally every time it is called
    public static int highRiskCounter = 0;

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public int getTermInYears() {
        return termInYears;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public BigDecimal getInterestRate(){
        return this.interestRate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice(){
        return this.price;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }
    public LoanRiskType getRiskType() {
        return riskType;
    }

    //I believe this should be calculated value and to a constant :)
    public boolean isValid(){
        return true;
    }

    //Missing methods:
    //calculateInterest()
    //getTotalLoanCost()
}
