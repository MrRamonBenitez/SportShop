package Shop;

import Shop.Interfaces.Catalog;
import Shop.Interfaces.Product;

import java.util.*;
import static java.lang.System.out;

public class ShopCatalog implements Catalog {
    private final Map<Integer, Product> catalog;

    public ShopCatalog() {
        this.catalog = new HashMap<>();
    }

    @Override
    public void addItem(Product product) {
        catalog.put(product.getId(), product);
    }

    @Override
    public void removeItem(int productId) {
        catalog.remove(productId);
    }

    @Override
    public void viewCatalog() {
        for (int keyItem : catalog.keySet()) {
            out.println(catalog.get(keyItem));
        }
        out.println();
    }

    public int getCatalogSize() {
        return catalog.size();
    }

    public Product getItem(int id) {
        if (catalog.containsKey(id)) {
            return catalog.get(id);
        }
        return null;
    }

    public boolean checkingItemExist(Product product) {
        return catalog.containsValue(product);
    }

    public void keywordFilter(String keyWord) {
        catalog.entrySet().stream()
                .filter(item -> item.getValue().getName().contains(keyWord))
                .sorted(Map.Entry.<Integer, Product>comparingByValue().reversed())
                .map(item -> item.getValue().toString())
                .forEach(out::println);
    }

    public void priceFilter(int minPrice, int maxPrice) {
        catalog.entrySet().stream()
                .filter(item -> item.getValue().getPrice() > minPrice &&
                        item.getValue().getPrice() < maxPrice)
                .sorted(Map.Entry.<Integer, Product>comparingByValue().reversed())
                .map(item -> item.getValue().toString())
                .forEach(out::println);
    }

    public void brandFilter(String brand) {
        catalog.entrySet().stream()
                .filter(item -> brand.equals(item
                                             .getValue()
                                             .getManufacturer()
                                             .toString()))
                .sorted(Map.Entry.<Integer, Product>comparingByValue().reversed())
                .map(item -> item.getValue().toString())
                .forEach(out::println);
    }

    public void purchaseRecommendationSystem(Product product) {
        product.setRating(product.getRating() + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int keyItem : catalog.keySet()) {
            sb.append(catalog.get(keyItem)).append("\n");
        }
        return sb.toString();
    }
}
