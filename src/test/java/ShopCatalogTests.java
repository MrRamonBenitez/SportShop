import Shop.Interfaces.Catalog;
import Shop.Interfaces.Product;
import Shop.Manufacturer;
import Shop.ShopCatalog;
import Shop.ShopProduct;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopCatalogTests {
    Catalog testCatalog;

    @BeforeAll
    public static void started() { System.out.println("Tests started"); }

    @AfterEach
    public void finished() { System.out.println("Test completed"); }

    @AfterAll
    public static void finishedAll() { System.out.println("Tests completed"); }

    @BeforeEach
    void setUp() { testCatalog = new ShopCatalog(); }

    @Test
    public void addItemTest() {
        //arrange
        int size = 1;
        Product product = new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9);

        //act
        testCatalog.addItem(product);

        //assert
        assertNotNull(testCatalog);
        assertEquals(size, testCatalog.getCatalogSize());
    }

    @Test
    public void removeItemTest() {
        //arrange
        int id;
        int size = 0;
        Product product = new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9);
        testCatalog.addItem(product);
        id = product.getId();

        //act
        testCatalog.removeItem(id);

        //assert
        assertEquals(size, testCatalog.getCatalogSize());
    }

    @Test
    public void checkingItemExistTest() {
        //arrange
        Product product = new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9);
        testCatalog.addItem(product);

        //assert
        assertTrue(testCatalog.checkingItemExist(product));
    }

    @Test
    public void purchaseRecommendationSystemTest() {
        //arrange
        Product product = new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9);
        int rating = product.getRating();
        testCatalog.addItem(product);
        int id = product.getId();

        //act
        testCatalog.purchaseRecommendationSystem(testCatalog.getItem(id));

        //assert
        assertTrue(rating < testCatalog.getItem(id).getRating());

    }
}
