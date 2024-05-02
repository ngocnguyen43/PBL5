package model;


public class Order {
    private String orderId;
    private String userId;
    private String status;
    private String paidDate;
    private final long unix = System.currentTimeMillis() / 1000L;
    private String orderDate = unix + "";

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

        public Order build() {
            Order order = new Order();
            order.setOrderId(this.orderId);
            order.setStatus(this.status);
            order.setUserId(this.userId);

            return order;
        }
    }
}
