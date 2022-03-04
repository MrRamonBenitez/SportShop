package Shop;

import Shop.Interfaces.Client;
import java.util.Objects;

public class ShopClient implements Client {
    private final String name;
    private String phoneNumber;
    private String address;

    public ShopClient(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopClient that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(getPhoneNumber(),
               that.getPhoneNumber()) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }

    @Override
    public String toString() {
        return name + " Номер моб.телефона: " + phoneNumber + " Адрес доставки: " + address;
    }
}

