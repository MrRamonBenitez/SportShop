import Shop.*;
import Shop.Interfaces.*;
import Shop.Interfaces.Order;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShopOrdersTests {
    Catalog catalog;
    Orders orders;
    Order order;
    int orderNumber;

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @BeforeEach
    public void setUp() {
        catalog = new ShopCatalog();
        Product product1 = new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9);
        Product product2 = new ShopProduct("Перчатки хоккейные", Manufacturer.BAUER, 17000, 100, 7);
        catalog.addItem(product1);
        catalog.addItem(product2);
        CartItem item1 = new ShopCartItem(product1, 3);
        CartItem item2 = new ShopCartItem(product2, 5);
        Cart cart = new ShopCart();
        cart.addItem(item1);
        cart.addItem(item2);
        orders = new ShopOrders();
        orderNumber = 1;
        order = new ShopOrder(orderNumber, cart);
    }

    @Test
    public void addOrderTest() {
        //arrange
        int size = 1;

        //act
        orders.addOrder(orderNumber, order);

        //assert
        assertNotNull(orders);
        assertEquals(size, orders.getOrdersSize());
        assertEquals("DESIGN", order.getOrderStatus());
    }

    @Test
    public void removeOrderTest() {
        //arrange
        int size = 0;
        orders.addOrder(orderNumber, order);

        //act
        orders.removeOrder(orderNumber);

        //assert
        assertEquals(size, orders.getOrdersSize());
    }

    @Test
    public void returnOrderTest() {
        //arrange
        orders.addOrder(orderNumber, order);

        //act
        orders.returnOrder(orderNumber, catalog);

        //assert
        assertEquals("RETURNED", orders.getOrder(orderNumber).getOrderStatus());
    }
}
