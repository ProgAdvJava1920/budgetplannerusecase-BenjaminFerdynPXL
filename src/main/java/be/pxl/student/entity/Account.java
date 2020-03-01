package be.pxl.student.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Account {

    private String IBAN;
    private String name;
    private List<Payment> payments;

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


}
