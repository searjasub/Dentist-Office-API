package model;

import java.io.Serializable;

public class Payment implements Serializable {

    private double amount;
    private Patient patient;
    private Source source;
}
