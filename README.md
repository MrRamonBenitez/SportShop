***ПРИМЕРЫ РЕАЛИЗАЦИИ СВОЙСТВ ХОРОШЕГО КОДА***

**Магические числа:**

*Объявление:*
```java
  private final int MIN_ID = 1;
  private final int MAX_ID = 100;
```
*Использование:*
```java
  this.id = generateIdProduct(MIN_ID, MAX_ID);
        ...    
```
**DRY:**
```java
          switch (choice) {
                case "1" -> workWithCatalog();

                case "2" -> completedCart = workWithCart();

                case "3" -> removeItemFromCart();

                case "4" -> checkout();

                case "5" -> deleteOrder();

                case "6" -> customerReturn();

                case "7" -> arrangeDelivery();

                case "8" -> { return; }
          }
```

**SOLID**

* **Single responsibility**

    Созданные интерфейсы реализуют поведение отдельных объектов:
  * **Product**  -  товар как единица каталога магазина
  * **Client**   -  клиент как единица клиентской базы магазина
  * **CartItem** -  элемент товарной корзины
  * **Cart**     -  сама корзина товаров
  * **Order**    -  заказ, созданный на основе корзины товаров
  * **Orders**   -  список заказов
  * **Clients**  -  список клиентов
  * **Catalog**  -  каталог товаров

    
* **Interface segregation**

  Cоздание корзины (интерфейс **Cart**) разделено на два этапа: 
  * создание элемента корзины (интерфейс **CartItem**);
  * создание самой корзины из этих элементов; 


* **Dependency inversion**

  Логика реализации магазина спортивных товаров зависит не от деталей
реализации классов оформления корзины (**ShopCart**) и заказа (**ShopOrder**),
а только от абстракции этих процессов (от интерфейсов **Cart**, **Order**).