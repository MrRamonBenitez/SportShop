package Shop;

import Shop.Interfaces.CartItem;
import Shop.Interfaces.Product;

public class ShopCartItem implements CartItem {
    private final Product product;
    private final int count;
    private final int totalPrice;

    public ShopCartItem(Product product, int count) {
        this.product = product;
        this.count = count;
        this.totalPrice = product.getPrice() * count;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format("%-30s Цена: %-5d руб. Количество: %-3d шт. Стоимость: %-6d %n",
                             product.getName(), product.getPrice(), count, totalPrice);
    }
}
