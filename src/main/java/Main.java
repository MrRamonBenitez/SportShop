import Shop.*;
import Shop.Interfaces.*;

import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Catalog sportCatalog = new ShopCatalog();
    private static Orders orders = new ShopOrders();
    private static Cart completedCart = null;
    private static Clients shopClients = new ShopClients();
    private static int orderNumber = 1;

    public static void main(String[] args) {

        createCatalog(sportCatalog);

        out.println("Добро пожаловать в магазин Sport Shop!");
        out.println();

        while (true) {
            out.println("""
                    Выберите пункт меню:
                    1. Просмотр каталога
                    2. Добавить товар в корзину
                    3. Очистить корзину
                    4. Оформить заказ
                    5. Удалить заказ
                    6. Вернуть заказ
                    7. Оформить доставку
                    8. Выход
                    """);
            out.print("-> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> workWithCatalog();

                case "2" -> completedCart = workWithCart();

                case "3" -> removeItemFromCart();

                case "4" -> checkout();

                case "5" -> deleteOrder();

                case "6" -> customerReturn();

                case "7" -> arrangeDelivery();

                case "8" -> {
                    return;
                }
            }
        }
    }


    public static void createCatalog(Catalog shopCatalog){
        shopCatalog.addItem(new ShopProduct("Клюшка хоккейная", Manufacturer.BAUER, 15000, 100, 9));
        shopCatalog.addItem(new ShopProduct("Клюшка хоккейная", Manufacturer.CCM, 11000, 100, 10));
        shopCatalog.addItem(new ShopProduct("Клюшка хоккейная", Manufacturer.ZARYAD, 9000, 100, 7));
        shopCatalog.addItem(new ShopProduct("Клюшка хоккейная", Manufacturer.NORDWAY, 1000, 100, 3));
        shopCatalog.addItem(new ShopProduct("Шлем хоккейный", Manufacturer.BAUER, 7000, 100,7));
        shopCatalog.addItem(new ShopProduct("Шлем хоккейный", Manufacturer.CCM, 23000, 100,10));
        shopCatalog.addItem(new ShopProduct("Перчатки хоккейные", Manufacturer.BAUER, 17000, 100,7));
        shopCatalog.addItem(new ShopProduct("Перчатки хоккейные", Manufacturer.CCM, 14000, 100,10));
        shopCatalog.addItem(new ShopProduct("Бутсы футбольные", Manufacturer.ADIDAS, 7400, 100,10));
        shopCatalog.addItem(new ShopProduct("Бутсы футбольные", Manufacturer.PUMA, 11000, 100,8));
        shopCatalog.addItem(new ShopProduct("Мяч футбольный", Manufacturer.ADIDAS, 5000, 100,7));
        shopCatalog.addItem(new ShopProduct("Мяч футбольный", Manufacturer.PUMA, 2000, 100,5));
        shopCatalog.addItem(new ShopProduct("Перчатки вратарские футбольные", Manufacturer.ADIDAS, 7000, 100,7));
        shopCatalog.addItem(new ShopProduct("Сетка для футбольных ворот", Manufacturer.DEMIX, 800, 100,5));
        shopCatalog.addItem(new ShopProduct("Джемпер футбольный", Manufacturer.DEMIX, 3500, 100, 5));
    }

    public static void workWithCatalog(){
        sportCatalog.viewCatalog();

        while (true) {
            out.println("""
                    Выберите фильтр:
                    1. Ключевые слова
                    2. Цена
                    3. Производитель
                    4. Выход
                    """);
            out.print("-> ");

            String filter = scanner.nextLine();

            switch (filter) {
                case "1" -> {
                    out.print("Введите ключевое слово: ");
                    String keyWord = scanner.nextLine();

                    sportCatalog.keywordFilter(keyWord);
                }
                case "2" -> {
                    out.print("Введите диапазон цен через пробел: ");

                    String priceRange = scanner.nextLine();
                    String[] parts = priceRange.split(" ");
                    int minPrice = Integer.parseInt(parts[0]);
                    int maxPrice = Integer.parseInt(parts[1]);

                    sportCatalog.priceFilter(minPrice, maxPrice);
                }
                case "3" -> {
                    out.println("""
                            Введите наименование производителей из списка -
                            BAUER
                            NORDWAY
                            ZARYAD
                            CCM
                            ADIDAS
                            PUMA
                            DEMIX:
                            """);
                    out.print("-> ");

                    String brand = scanner.nextLine();

                    sportCatalog.brandFilter(brand);
                }
                case "4" -> {
                    return;
                }
            }
        }
    }

    public static Cart workWithCart() {
        Cart newCart = new ShopCart();
        out.println("Выберите товар из каталога:");

        sportCatalog.viewCatalog();

        while (true) {
            out.println("Введите id товара или введите <exit>:");
            String input = scanner.nextLine();

            if ("exit".equals(input)) {
                break;
            } else {
                int productId = Integer.parseInt(input);

                out.println("Введите количество товара:");
                int productVolume = Integer.parseInt(scanner.nextLine());

                CartItem item = new ShopCartItem(sportCatalog.getItem(productId), productVolume);

                sportCatalog                                                                    // уменьшаем количество
                    .getItem(productId)                                                         // товара данной номенклатуры
                    .setQuantity(sportCatalog                                                   //в каталоге магазина на величину
                                    .getItem(productId).getQuantity() - productVolume);         //количество товара, добавленного
                                                                                                //в корзину;
                sportCatalog.purchaseRecommendationSystem(sportCatalog.getItem(productId));     //при добавлении товара в корзину
                                                                                                //повышаем его рейтинг;
                newCart.addItem(item);
            }
        }
        return newCart;
    }

    public static void removeItemFromCart() {
        out.println("Хотите очистить корзину? (да/нет): ");
        String answer = scanner.nextLine();

        if ("да".equals(answer)) {

            assert completedCart != null;

            for (int i = 0; i < completedCart.getSize(); i++) {

                CartItem item = completedCart.getCartItem(i);

                Product product = item.getProduct();

                sportCatalog                                                                           //увеличиваем
                        .getItem(product.getId())                                                      //количество товара
                        .setQuantity(sportCatalog                                                      //в каталоге магазина
                        .getItem(product.getId())                                                      //данной номенклатуры
                        .getQuantity() + item.getCount());                                             //на величину товара
            }                                                                                          //в корзине, потом ее
            completedCart.emptyCart();                                                                 //очищаем
            out.println("Корзина пуста");
        }
    }

    public static void checkout () {
        out.println("Ваша корзина:");
        assert completedCart != null;
        out.println(completedCart);

        out.println("Хотите оформить заказ? (да/нет): ");
        String answer = scanner.nextLine();

        if ("да".equals(answer)) {
            out.print("Введите Ваши данные (имя, фамилия) через пробел: ");
            String name = scanner.nextLine();

            out.println("Введите номер Вашего телефона: ");
            String phoneNumber = scanner.nextLine();

            out.println("Введите Ваш адрес (город, улица, дом, квартира) через пробел: ");
            String address = scanner.nextLine();

            Client newClient = new ShopClient(name, phoneNumber, address);

            shopClients.addClient(newClient);

            Order newOrder = new ShopOrder(orderNumber, completedCart);
            orderNumber++;
            orders.addOrder(orderNumber, newOrder);
        }
    }

    public static void deleteOrder() {
        out.println("Введите номер Вашего заказа: ");
        int number  = Integer.parseInt(scanner.nextLine());

        out.println("Ваш заказ: \n" + orders.getOrder(number));

        out.println("Хотите удалить заказ № " + number + "? (да/нет):");
        String answer = scanner.nextLine();

        if ("да".equals(answer)) {
            orders.removeOrder(number);
            out.println("Ваш заказ удален!");
        }
    }

    public static void customerReturn() {
        out.println("Введите номер Вашего заказа: ");
        int number  = Integer.parseInt(scanner.nextLine());

        if ("SHIPPED".equals(orders.getOrder(number).getOrderStatus())) {
            out.println("Ваш заказ передан в службу доставки. Возврат невозможен!");
            return;
        }

        out.println("Хотите вернуть заказ № " + number + "? (да/нет):");
        String answer = scanner.nextLine();

        if ("да".equals(answer)) {
            orders.returnOrder(number, sportCatalog);

            for (int i = 0; i < orders.getOrder(orderNumber).getFinalCart().getSize(); i++) {

                Product cartItem = orders                                                       // извлекаем из заказа
                        .getOrder(orderNumber)                                                  // значение количества
                        .getFinalCart().getItem(i);                                             // товара каждой номенклатуры
                                                                                                // и увеличиваем на это значение
                if (sportCatalog.checkingItemExist(cartItem)) {                                 // количество товара этой же номенклатуры
                    Product item = sportCatalog                                                 // в каталоге магазина
                            .getItem(cartItem.getId());
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                }
            }

            orders.removeOrder(number);
        }
    }

    public static void arrangeDelivery() {
        out.print("Хотите оформить доставку? (да/нет): ");
        String answer = scanner.nextLine();

        if ("да".equals(answer)) {
            out.println("Введите Ваш номер телефона: ");
            String mapKey  = scanner.nextLine();

            out.print("Адрес доставки совпадает с адресом в Вашем профиле? (да/нет): ");
            answer  = scanner.nextLine();

            if ("да".equals(answer)) {
                out.print("Введите номер Вашего заказа: ");
                int number  = Integer.parseInt(scanner.nextLine());

                orders.getOrder(number - 1)
                        .setShippingDate();
                orders.getOrder(number - 1)
                        .setShippingAddress(shopClients
                        .getClient(mapKey)
                        .getAddress());
                orders.getOrder(number - 1)
                        .setOrderTrackingNumber();
                orders.getOrder(number - 1)
                        .setOrderStatus("SHIPPED");

                out.println("Ваш заказ № " + number + " отправлен " +
                            orders.getOrder(number - 1).getSenderDate());
                out.println("Номер для отслеживания: " +
                            orders.getOrder(number - 1).getTrackingNumber());
            }
            out.print("Введите адрес доставки (через пробел): ");
            String address  = scanner.nextLine();

            out.print("Введите номер Вашего заказа: ");
            int number  = Integer.parseInt(scanner.nextLine());

            orders.getOrder(number - 1)
                    .setShippingDate();
            orders.getOrder(number - 1)
                    .setShippingAddress(address);
            orders.getOrder(number - 1)
                    .setOrderTrackingNumber();
            orders.getOrder(number - 1)
                    .setOrderStatus("SHIPPED");

            out.println("Ваш заказ № " + number + " отправлен " +
                        orders.getOrder(number - 1).getSenderDate());
            out.println("Номер для отслеживания: " +
                        orders.getOrder(number - 1).getTrackingNumber());

        }
    }
}


