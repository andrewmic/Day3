package swedbank.it.academy.domain;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by p998feq on 2018.03.06.
 */
public class Loan {

    private Date creationDate;
    private int termInYears;
    private String name;
    protected BigDecimal interestRate;
    private BigDecimal price;
    private LoanRiskType riskType;
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

    public boolean isValid(){
        return true;
    }

}
