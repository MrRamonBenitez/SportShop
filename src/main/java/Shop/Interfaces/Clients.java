package Shop.Interfaces;

public interface Clients {
    void addClient(Client client);

    void removeClient(String key);

    Client getClient(String key);

    int getClientsSize();
}
