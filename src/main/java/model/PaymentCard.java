package model;

import java.io.Serializable;

public class PaymentCard implements Serializable {

    private String number;
    private int expMonth;
    private int expYear;
    private String cardName;
    private int cvv;
    private int zipCode;

    public PaymentCard(String number, int expMonth, int expYear, String cardName, int cvv, int zipCode) {
        this.setNumber(number);
        this.setExpMonth(expMonth);
        this.setExpYear(expYear);
        this.setCardName(cardName);
        this.setCvv(cvv);
        this.setZipCode(zipCode);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
//    	if(number < 0 || number > Long.MAX_VALUE) {
//    		throw new IllegalArgumentException("Number cannot be negative or bigger than max value");
//    	}
        this.number = number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        if (expMonth < 0 || expMonth > 2100000000) {
            throw new IllegalArgumentException("Expiration Month cannot be negative or bigger than max value");
        }
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        if (expYear < 0 || expYear > 210000000) {
            throw new IllegalArgumentException("Expiration Year cannot be negative or bigger than max value");
        }
        this.expYear = expYear;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        if (cardName == null || cardName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be nothing");
        }
        this.cardName = cardName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        if (cvv < 0 || cvv > 210000000) {
            throw new IllegalArgumentException("CVV cannot be negative or bigger than max value");
        }
        this.cvv = cvv;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        if (zipCode < 10000 || zipCode > 99999) {
            throw new IllegalArgumentException("Zip Code cannot be smaller than 10000 or bigger than 99999");
        }
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        if (!getCardName().isEmpty()) {
            return "Card on file";
        } else {
            return "No card in file";

        }
    }
}
