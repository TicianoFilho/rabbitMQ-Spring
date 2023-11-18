package org.librabbitmq.dto;

import java.io.Serializable;
import java.util.Objects;

public class EstoqueDTO implements Serializable {

    private String ean;
    private Integer amount;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "EstoqueDTO{" +
                "ean='" + ean + '\'' +
                ", amount=" + amount +
                '}';
    }
}
