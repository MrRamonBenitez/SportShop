package Shop;

import Shop.Interfaces.Catalog;
import Shop.Interfaces.Order;
import Shop.Interfaces.Orders;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class ShopOrders implements Orders {
    private List<Order> ordersList;

    public ShopOrders() {
        this.ordersList = new ArrayList<>();
    }

    @Override
    public void addOrder(int orderNumber, Order newOrder) {
        ordersList.add(orderNumber - 1, newOrder);
        newOrder.setOrderStatus("DESIGN");
    }

    @Override
    public void removeOrder(int orderNumber) {
        if (!"SHIPPED".equals(ordersList.get(orderNumber - 1).getOrderStatus())) {
            ordersList.remove(orderNumber - 1);
        } else {
            out.println("Ваш заказ уже отправлен!");
        }
    }

    @Override
    public void returnOrder(int orderNumber, Catalog catalog) {
        getOrder(orderNumber).setOrderStatus("RETURNED");
    }

    @Override
    public int getOrdersSize() {
        return ordersList.size();
    }

    @Override
    public Order getOrder(int number) {
        return ordersList.get(number - 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
