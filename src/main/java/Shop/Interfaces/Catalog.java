package Shop.Interfaces;

public interface Catalog {
    void addItem(Product product);

    void removeItem(int productId);

    void viewCatalog();

    int getCatalogSize();

    void keywordFilter(String keyWord);

    void priceFilter(int minPrice, int maxPrice);

    void brandFilter(String brand);

    void purchaseRecommendationSystem (Product product);

    Product getItem(int id);

    boolean checkingItemExist(Product product);
}
