package Shop;

import Shop.Interfaces.Cart;
import Shop.Interfaces.CartItem;
import Shop.Interfaces.Product;

import java.util.*;

public class ShopCart implements Cart {
    protected List<CartItem> itemList;
    private int totalCartValue;

    public ShopCart() {
        this.itemList = new ArrayList<>();
        this.totalCartValue = 0;
    }

    @Override
    public int getSize() {
        return itemList.size();
    }

    @Override
    public CartItem getCartItem(int i) {
        return itemList.get(i);
    }

    @Override
    public Product getItem(int id) {
        return itemList.get(id).getProduct();
    }

    @Override
    public void addItem(CartItem item) {
        itemList.add(item);
    }

    @Override
    public void emptyCart() {
        itemList.removeAll(itemList);
    }

    @Override
    public int getTotalCartValue() {
        for (CartItem item : itemList) {
            totalCartValue = totalCartValue + item.getTotalPrice();
        }
        return totalCartValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        itemList.forEach(item -> sb.append(item.toString()).append("\n"));
        sb.append("Общая стоимость: ").append(getTotalCartValue()).append(" руб.\n");
        return sb.toString();
    }
}
