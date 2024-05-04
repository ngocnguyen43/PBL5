package model;


public class Order {
    private String orderId;
    private String userId;
    private String status;
    private String paidDate;
    private final long unix = System.currentTimeMillis() / 1000L;
    private String orderDate = unix + "";
    private String confirmUrlId;

    public String getConfirmUrlId() {
        return confirmUrlId;
    }

    public void setConfirmUrlId(String confirmUrlId) {
        this.confirmUrlId = confirmUrlId;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public static class Builder {
        private String orderId;
        private String userId;
        private String status;
        private String confirmUrlId;
        private String paidDate;

        public Builder(String orderId) {
            this.orderId = orderId;
        }


        public Builder WithUserId(String userId) {
            this.userId = userId;
            return this;
        }


        public Builder WithStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder WithPaidDate(String paidDate) {
            this.paidDate = paidDate;
            return this;
        }

        public Builder WithConfirmUrlId(String confirmUrlId) {
            this.confirmUrlId = confirmUrlId;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setOrderId(this.orderId);
            order.setStatus(this.status);
            order.setUserId(this.userId);
            order.setPaidDate(this.paidDate);
            order.setConfirmUrlId(this.confirmUrlId);

            return order;
        }
    }
}
