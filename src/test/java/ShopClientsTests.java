import Shop.Interfaces.Client;
import Shop.Interfaces.Clients;

import Shop.ShopClient;
import Shop.ShopClients;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShopClientsTests {
    Clients testClients;

    @BeforeAll
    public static void started() { System.out.println("Tests started"); }

    @AfterEach
    public void finished() { System.out.println("Test completed"); }

    @AfterAll
    public static void finishedAll() { System.out.println("Tests completed"); }

    @BeforeEach
    public void setUp() { testClients = new ShopClients(); }

    @Test
    public void addClientTest() {
        //arrange
        Client client = new ShopClient("Иван Петров", "89765432323", "Москва Лесная 5");
        int size = 1;

        //act
        testClients.addClient(client);

        //assert
        assertNotNull(testClients);
        assertEquals(size, testClients.getClientsSize());

    }

    @Test
    public void removeClientsTest() {
        //arrange
        int size = 0;
        Client client = new ShopClient("Иван Петров", "89765432323", "Москва Лесная 5");
        testClients.addClient(client);
        String key = client.getPhoneNumber();

        //act
        testClients.removeClient(key);

        //assert
        assertEquals(size, testClients.getClientsSize());
    }

}


