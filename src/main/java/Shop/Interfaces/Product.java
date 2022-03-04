package Shop.Interfaces;

import Shop.Manufacturer;

public interface Product extends Comparable<Product> {
    int getId();

    String getName();

    int getPrice();

    Manufacturer getManufacturer();

    int getRating();

    int getQuantity();

    void setQuantity(int quantity);

    void setRating(int rating);
}
