package Shop.Interfaces;

public interface Orders {
    void addOrder(int orderNumber, Order newOrder);

    void removeOrder(int orderNumber);

    void returnOrder(int orderNumber, Catalog catalog);

    int getOrdersSize();

    Order getOrder(int orderNumber);
}
