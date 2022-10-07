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
        createUsers();
        createProducts();
        createInitialStateOrders();
    }
    @After
    public void tearDown(){
        this.productManager = null;
    }
    @Test
    public void testAddOrder() {
        int currentNumber = this.productManager.numOrders();
        Order newOrder = new Order("2222222");
        newOrder.addProduct(ProductsIDs.Cocacola,3);
        newOrder.addProduct(ProductsIDs.Croissant,2);
        this.productManager.addOrder(newOrder);
        Assert.assertEquals(currentNumber + 1, this.productManager.numOrders());
    }

    @Test
    public void processOrderTest() {
        Order order1 = this.productManager.processNextOrder();
        Assert.assertEquals(2, this.productManager.numOrders());
        Assert.assertEquals(3, order1.getLP(0).getQuantity());
        Assert.assertEquals(2, order1.getLP(1).getQuantity());
        Assert.assertEquals(3, this.productManager.numSales(ProductsIDs.Cocacola));

        Order order2 = this.productManager.processNextOrder();
        Assert.assertEquals(1, this.productManager.numOrders());
        Assert.assertEquals(4, this.productManager.numSales(ProductsIDs.Cocacola));

        Order order3 = this.productManager.processNextOrder();
        Assert.assertEquals(0, this.productManager.numOrders());
        Assert.assertEquals(7, this.productManager.numSales(ProductsIDs.Cocacola));
    }

    private void checkInitialsConditionsAreMet(int numberOfUsers, int numberOfProducts, int numberOfOrders) {
        Assert.assertEquals(numberOfUsers, this.productManager.numUsers());
        Assert.assertEquals(numberOfProducts, this.productManager.numProducts());
        Assert.assertEquals(numberOfOrders, this.productManager.numOrders());
    }

    @Test
    public void productsSortByPrice() {
        List<Product> products = this.productManager.productsByPrice();
        Assert.assertEquals(ProductsIDs.Croissant, products.get(0).getProductId());
        Assert.assertEquals(1.25, products.get(0).getPrice(), 0);
        Assert.assertEquals(ProductsIDs.IcedCoffe, products.get(1).getProductId());
        Assert.assertEquals(1.5, products.get(1).getPrice(), 0);
        Assert.assertEquals(ProductsIDs.Cocacola, products.get(2).getProductId());
        Assert.assertEquals(2, products.get(2).getPrice(), 0);
        Assert.assertEquals(ProductsIDs.Donut, products.get(3).getProductId());
        Assert.assertEquals(2.25, products.get(3).getPrice(), 0);
    }
    @Test
    public void productsSortByNumSales() {

        processOrderTest();
        List<Product> products = this.productManager.productsBySales();
        Assert.assertEquals(ProductsIDs.Croissant, products.get(0).getProductId());
        Assert.assertEquals("Croissant", products.get(0).getName());
        Assert.assertEquals(0, products.get(0).getNumSales());

        Assert.assertEquals(ProductsIDs.IcedCoffe, products.get(1).getProductId());
        Assert.assertEquals("Café amb gel", products.get(1).getName());
        Assert.assertEquals(2, products.get(1).getNumSales());

        Assert.assertEquals(ProductsIDs.Donut, products.get(2).getProductId());
        Assert.assertEquals("Donut", products.get(2).getName());
        Assert.assertEquals(5, products.get(2).getNumSales());

        Assert.assertEquals(ProductsIDs.Cocacola, products.get(3).getProductId());
        Assert.assertEquals("Coca cola", products.get(3).getName());
        Assert.assertEquals(7, products.get(3).getNumSales());
    }
    @Test
    public void ordersByUserTest() {
        processOrderTest();
        List<Order> orders1 = this.productManager.ordersByUser("1111111");
        Assert.assertEquals(1, orders1.size());
        List<Order> orders2 = this.productManager.ordersByUser("2222222");
        Assert.assertEquals(1, orders2.size());

    }
    private void createProducts() {
        productManager.createProduct(ProductsIDs.Cocacola, "Coca cola", 2);
        productManager.createProduct(ProductsIDs.IcedCoffe, "Café amb gel", 1.5);
        productManager.createProduct(ProductsIDs.Donut, "Donut", 2.25);
        productManager.createProduct(ProductsIDs.Croissant, "Croissant", 1.25);
    }
    private void createUsers() {
        productManager.createUser("1111111", "Juan", "lopez");
        productManager.createUser("2222222",  "David", "Rincon");
        productManager.createUser("3333333",  "Juan", "Hernández");
    }
    private void createInitialStateOrders() {
        Order o1 = new Order("1111111");
        o1.addProduct(ProductsIDs.Cocacola,3 );
        o1.addProduct(ProductsIDs.IcedCoffe,2);

        Order o2 = new Order("2222222");
        o2.addProduct(ProductsIDs.Donut,3 );
        o2.addProduct(ProductsIDs.Cocacola,1 );

        Order o3 = new Order("3333333");
        o3.addProduct(ProductsIDs.Cocacola,3 );
        o3.addProduct(ProductsIDs.Donut,2);

        this.productManager.addOrder(o1);
        Assert.assertEquals(1, this.productManager.numOrders());
        this.productManager.addOrder(o2);
        Assert.assertEquals(2, this.productManager.numOrders());
        this.productManager.addOrder(o3);
        Assert.assertEquals(3, this.productManager.numOrders());
    }
}
