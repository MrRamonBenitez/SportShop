package Shop.Interfaces;

public interface Cart {
    void addItem(CartItem item);

    CartItem getCartItem(int i);

    void emptyCart();

    int getTotalCartValue();

    int getSize();

    Product getItem(int id);
}

