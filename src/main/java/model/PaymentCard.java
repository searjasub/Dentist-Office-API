package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class that manages a Payment Card
 */
public class PaymentCard implements Serializable {

    private static final long serialVersionUID = -2881834379761508174L;

    private String number;
    private int expMonth;
    private int expYear;
    private String cardName;
    private int cvv;
    private int zipCode;

    /**
     * Constructor of the PaymentCard
     * @param number the 16 digit number
     * @param expMonth Expiration month
     * @param expYear Expiration year
     * @param cardName Name on the card
     * @param cvv Security code on the back
     * @param zipCode Owner's zip code
     */
    public PaymentCard(String number, int expMonth, int expYear, String cardName, int cvv, int zipCode) {
        this.setNumber(number);
        this.setExpMonth(expMonth);
        this.setExpYear(expYear);
        this.setCardName(cardName);
        this.setCvv(cvv);
        this.setZipCode(zipCode);
    }

    /**
     * Set the number of the card
     * @param number 16 digit number
     */
    public void setNumber(String number) {
        if(number == null){
            throw new IllegalArgumentException("Input can't be null");
        }
        this.number = number;
    }

    /**
     * Sets the expiration month on the card
     * @param expMonth
     */
    public void setExpMonth(int expMonth) {
        if (expMonth < 0 || expMonth > 12) {
            throw new IllegalArgumentException("Expiration Month is not valid");
        }
        this.expMonth = expMonth;
    }

    /**
     * Sets the expiration year
     * @param expYear any year up to 5 in the future
     */
    public void setExpYear(int expYear) {
        if (expYear < LocalDate.now().getYear() || expYear > LocalDate.now().getYear() + 5) {
            throw new IllegalArgumentException("Expiration Year cannot be negative or bigger than max value");
        }
        this.expYear = expYear;
    }

    /**
     * @return the name printed on the card
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the name on the card
     * @param cardName Owner's name
     */
    public void setCardName(String cardName) {
        if (cardName == null || cardName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be nothing");
        }
        this.cardName = cardName;
    }

    /**
     * Set's the security code
     * @param cvv 3 digit number on the back of the card
     */
    public void setCvv(int cvv) {
        if (cvv < 0 || cvv > 999) {
            throw new IllegalArgumentException("CVV cannot be negative or have 4 numbers");
        }
        this.cvv = cvv;
    }

    /**
     * Set's the zip code on the card
     * @param zipCode 5 digit zip code (US)
     */
    public void setZipCode(int zipCode) {
        if (zipCode < 10000 || zipCode > 99999) {
            throw new IllegalArgumentException("Zip Code cannot be smaller than 10000 or bigger than 99999");
        }
        this.zipCode = zipCode;
    }

    /**
     * Whoever calls the method instead of showing all personal information, it will return only if there is a card on file
     * @return if there is a card on file or not
     */
    @Override
    public String toString() {
        if (!getCardName().isEmpty()) {
            return "Card on file";
        } else {
            return "No card in file";
        }
    }
}
