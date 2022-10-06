import edu.upc.eetac.dsa.models.Order;
import edu.upc.eetac.dsa.models.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductManagerImplTest {

    ProductManager productManager;

    @Before
    public void setUp() {
        productManager = new ProductManagerImpl();
        productManager.addUser("1111111", "Juan", "lopez");
        productManager.addUser("2222222",  "David", "Rincon");
        productManager.addUser("3333333",  "Juan", "Hernández");

        productManager.addProduct("B001", "Coca cola", 2);
        productManager.addProduct("C002", "Café amb gel", 1.5);
        productManager.addProduct("A002", "Donut", 2.25);
        productManager.addProduct("A003", "Croissant", 1.25);
        prepareOrders();
    }

    @After
    public void tearDown() {
        this.productManager = null;
    }

    private void prepareOrders() {
        Order o1 = new Order("1111111");
        o1.addLP(3, "B001");
        o1.addLP(2, "C002");

        Order o2 = new Order("1111111");
        o2.addLP(3, "A002");
        o2.addLP(1, "B001");

        Order o3 = new Order("2222222");
        o3.addLP(3, "B001");
        o3.addLP(2, "A002");


        Assert.assertEquals(0, this.productManager.numOrders());
        this.productManager.addOrder(o1);
        Assert.assertEquals(1, this.productManager.numOrders());
        this.productManager.addOrder(o2);
        Assert.assertEquals(2, this.productManager.numOrders());
        this.productManager.addOrder(o3);
        Assert.assertEquals(3, this.productManager.numOrders());
    }

    @Test
    public void testAddOrder() {
        Assert.assertEquals(3, this.productManager.numUsers());
        Assert.assertEquals(4, this.productManager.numProducts());
        Assert.assertEquals(3, this.productManager.numOrders());
        // ...
        Order o4 = new Order("2222222");
        o4.addLP(3, "B001");
        o4.addLP(2, "A003");
        this.productManager.addOrder(o4);

        Assert.assertEquals(4, this.productManager.numOrders());

    }

    @Test
    public void processOrderTest() {
        Assert.assertEquals(3, this.productManager.numUsers());
        Assert.assertEquals(4, this.productManager.numProducts());
        Assert.assertEquals(3, this.productManager.numOrders());

        Order order1 = this.productManager.processOrder();
        Assert.assertEquals(2, this.productManager.numOrders());
        Assert.assertEquals(3, order1.getLP(0).getQuantity());
        Assert.assertEquals(2, order1.getLP(1).getQuantity());
        Assert.assertEquals(3, this.productManager.numSales("B001"));

        Order order2 = this.productManager.processOrder();
        Assert.assertEquals(1, this.productManager.numOrders());
        Assert.assertEquals(4, this.productManager.numSales("B001"));

        Order order3 = this.productManager.processOrder();
        Assert.assertEquals(0, this.productManager.numOrders());
        Assert.assertEquals(7, this.productManager.numSales("B001"));
    }


    @Test
    public void productsSortByPrice() {
        List<Product> products = this.productManager.productsByPrice();

        Assert.assertEquals("A003", products.get(0).getProductId());
        Assert.assertEquals(1.25, products.get(0).getPrice(), 0);

        Assert.assertEquals("C002", products.get(1).getProductId());
        Assert.assertEquals(1.5, products.get(1).getPrice(), 0);

        Assert.assertEquals("B001", products.get(2).getProductId());
        Assert.assertEquals(2, products.get(2).getPrice(), 0);

        Assert.assertEquals("A002", products.get(3).getProductId());
        Assert.assertEquals(2.25, products.get(3).getPrice(), 0);
    }

    @Test
    public void productsSortByNumSales() {
        processOrderTest();

        List<Product> products = this.productManager.productsBySales();
        Assert.assertEquals("A003", products.get(0).getProductId());
        Assert.assertEquals("Croissant", products.get(0).getDescription());
        Assert.assertEquals(0, products.get(0).getNumSales());

        Assert.assertEquals("C002", products.get(1).getProductId());
        Assert.assertEquals("Café amb gel", products.get(1).getDescription());
        Assert.assertEquals(2, products.get(1).getNumSales());

        Assert.assertEquals("A002", products.get(2).getProductId());
        Assert.assertEquals("Donut", products.get(2).getDescription());
        Assert.assertEquals(5, products.get(2).getNumSales());

        Assert.assertEquals("B001", products.get(3).getProductId());
        Assert.assertEquals("Coca cola", products.get(3).getDescription());
        Assert.assertEquals(7, products.get(3).getNumSales());
    }

    @Test
    public void ordersByUserTest() {
        processOrderTest();
        List<Order> orders1 = this.productManager.ordersByUser("1111111");
        Assert.assertEquals(2, orders1.size());

        List<Order> orders2 = this.productManager.ordersByUser("2222222");
        Assert.assertEquals(1, orders2.size());

    }
}