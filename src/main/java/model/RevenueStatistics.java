package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class RevenueStatistics {
    private String id;
    private Date date;
    private BigDecimal revenueAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getRevenueAmount() {
        return revenueAmount;
    }

    public void setRevenueAmount(BigDecimal revenueAmount) {
        this.revenueAmount = revenueAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, revenueAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RevenueStatistics that = (RevenueStatistics) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(revenueAmount, that.revenueAmount);
    }

    @Override
    public String toString() {
        return "RevenueStatistics{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", revenueAmount=" + revenueAmount +
                '}';
    }
}
