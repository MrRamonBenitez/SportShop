package Shop;

import Shop.Interfaces.Product;
import java.util.Random;

public class ShopProduct implements Product {
    private final int MIN_ID = 1;
    private final int MAX_ID = 100;
    private final int id;
    private final String name;
    private final Manufacturer manufacturer;
    private int price;
    private int quantity;
    private int rating;

    public ShopProduct(String name, Manufacturer manufacturer, int price, int quantity, int rating) {
        this.id = generateIdProduct(MIN_ID, MAX_ID);
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getRating() {
        return rating;
    }

    public int generateIdProduct(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    @Override
    public String toString() {
        return String.format("%-3d %-30s Производитель: %-8s Цена: %-5d руб. Рейтинг: %-3d",
                            id, name, manufacturer, price, rating);
    }

    @Override
    public int compareTo(Product o) {
        return this.rating - o.getRating();
    }
}
