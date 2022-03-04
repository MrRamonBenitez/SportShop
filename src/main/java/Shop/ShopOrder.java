package Shop;

import Shop.Interfaces.Cart;
import Shop.Interfaces.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ShopOrder implements Order {
    private final int orderNumber;
    private final Date orderDate;
    private Cart finalCart;
    private final int orderPrice;
    private OrderStatus status;
    private String shippingAddress;
    private Date sendOrderDate;
    private String trackingNumber;

    public ShopOrder(int orderNumber, Cart cart) {
        this.orderNumber = orderNumber;
        this.orderDate = new Date();
        this.finalCart = cart;
        this.orderPrice = finalCart.getTotalCartValue();
        this.status = OrderStatus.DESIGN;
    }

    @Override
    public Cart getFinalCart() {
        return finalCart;
    }

    @Override
    public String getSenderDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(sendOrderDate);
    }

    @Override
    public void setOrderStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    @Override
    public String getOrderStatus() {
        return status.toString();
    }

    @Override
    public void setShippingDate() {
        this.sendOrderDate = new Date();
    }

    @Override
    public void setShippingAddress(String address) {
        this.shippingAddress = address;
    }

    @Override
    public void setOrderTrackingNumber() {
        this.trackingNumber = generatingRandomAlphanumericString();
    }

    @Override
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String generatingRandomAlphanumericString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 14;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return "Заказ № " + orderNumber + " от " + orderDate + ":\n" +
                finalCart.toString() + "\n" +
                "Статус заказа: " + status +
                "Адрес доставки: " + shippingAddress +
                "Дата отправки: " + sendOrderDate +
                "Номер для отслеживания: " + trackingNumber;
    }
}
