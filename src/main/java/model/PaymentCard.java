package model;

public class PaymentCard {

    private long number;
    private int expMonth;
    private int expYear;
    private String name;
    private int cvv;
    private int zipCode;

    public PaymentCard(long number, int expMonth, int expYear, String name, int cvv, int zipCode) {
        this.setNumber(number);
        this.setExpMonth(expMonth);
        this.setExpYear(expYear);
        this.setName(name);
        this.setCvv(cvv);
        this.setZipCode(zipCode);
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
