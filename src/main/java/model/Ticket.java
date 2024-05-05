package model;

import java.math.BigDecimal;

public class Ticket {
    private String ticketId;
    private String orderId;
    private String userId;
    private BigDecimal totalPrice;
    private BigDecimal price;
    private BigDecimal extraFee;
    private String photo;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(BigDecimal extraFee) {
        this.extraFee = extraFee;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static class Builder {
        private final String ticketId;
        private String orderId;
        private String userId;
        private BigDecimal totalPrice;
        private BigDecimal price;
        private BigDecimal extraFee;
        private String photo;

        public Builder(String ticketId) {
            this.ticketId = ticketId;
        }


        public Builder WithOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder WithUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder WithPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder WithExtraFee(BigDecimal extraFee) {
            this.extraFee = extraFee;
            return this;
        }

        public Builder WithPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();
            ticket.setTicketId(this.ticketId);
            ticket.setOrderId(this.orderId);
            ticket.setTicketId(this.ticketId);
            ticket.setUserId(this.userId);
            ticket.setPrice(this.price);
            ticket.setExtraFee(this.extraFee);
            ticket.setTotalPrice(this.price.add(this.extraFee));
            ticket.setPhoto(this.photo);
            return ticket;
        }

    }
}

