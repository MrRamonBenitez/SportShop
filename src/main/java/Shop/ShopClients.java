package Shop;

import Shop.Interfaces.Client;
import Shop.Interfaces.Clients;

import java.util.HashMap;
import java.util.Map;

public class ShopClients implements Clients {
    Map<String, Client> clients;

    public ShopClients() {
        this.clients = new HashMap<>();
    }

    @Override
    public void addClient(Client client) {
        clients.put(client.getPhoneNumber(), client);
    }

    @Override
    public void removeClient(String key) {
        clients.remove(key);
    }

    @Override
    public Client getClient(String key) {
        return clients.get(key);
    }

    @Override
    public int getClientsSize() {
        return clients.size();
    }
}
