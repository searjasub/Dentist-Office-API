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
    	if(number < 0 || number > Long.MAX_VALUE) {
    		throw new IllegalArgumentException("Number cannot be negative or bigger than max value");
    	}
        this.number = number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
    	if(expMonth < 0 || expMonth > 2100000000) {
    		throw new IllegalArgumentException("Expiration Month cannot be negative or bigger than max value");
    	}
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
    	if(expYear < 0 || expYear > 210000000) {
    		throw new IllegalArgumentException("Expiration Year cannot be negative or bigger than max value");
    	}
        this.expYear = expYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	if(name == null || name.trim() == "") {
    		throw new IllegalArgumentException("Name cannot be nothing");
    	}
        this.name = name;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
    	if(cvv < 0 || cvv > 210000000) {
    		throw new IllegalArgumentException("CVV cannot be negative or bigger than max value");
    	}
        this.cvv = cvv;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
    	if(zipCode < 10000 || zipCode > 99999) {
    		throw new IllegalArgumentException("Zip Code cannot be smaller than 10000 or bigger than 99999");
    	}
        this.zipCode = zipCode;
    }
}
