package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class Revenue {
    private float revenue;
    private long orderDate;

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    @JsonIgnore
    public long getOrderDayUnix() {
        return LocalDate.ofEpochDay(Instant.ofEpochSecond(orderDate)
                        .atZone(ZoneOffset.systemDefault())
                        .toLocalDate()
                        .toEpochDay())
                .atStartOfDay(ZoneOffset.systemDefault())
                .toEpochSecond();
    }

    @JsonIgnore
    public long getOrderByWeek() {
        LocalDate localDate = Instant.ofEpochSecond(orderDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startOfWeek = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return startOfWeek.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    @JsonIgnore
    public long getStartOfMonth() {
        LocalDate localDate = Instant.ofEpochSecond(orderDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        return startOfMonth.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }
}
