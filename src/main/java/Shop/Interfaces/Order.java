package Shop.Interfaces;

public interface Order {
    Cart getFinalCart();

    String getOrderStatus();

    String getSenderDate();

    String getTrackingNumber();

    void setOrderStatus(String status);

    void setShippingAddress(String address);

    void setShippingDate();

    void setOrderTrackingNumber();
}
