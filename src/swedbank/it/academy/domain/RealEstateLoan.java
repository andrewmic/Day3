package swedbank.it.academy.domain;

public class RealEstateLoan extends Loan{
    private String district;
    private float area;
    private String purpose;
    public static int personalRealEstateLoans = 0;


    public void setPurpose(RealEstatePurpose purpose) {
        this.purpose = String.valueOf(purpose);
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public float getArea() {
        return area;
    }

    public String getPurpose() {
        return purpose;
    }


}
