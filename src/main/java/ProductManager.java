
import java.util.List;

public interface ProductManager {
    public List<Product> productsByPrice();
    public List<Product> productsBySales();
    public void addOrder(Order order);
    public Order processNextOrder();
    public List<Order> ordersByUser(String userId);
    /////////////////////////////////////////////
    ////////////////////////////////////////////
    public void createUser(String s, String name, String surname);
    public void createProduct(String productId, String name, double price);

    public Product getProduct(String productId);

    public int numUsers();
    public int numProducts();

    public int numOrders();

    public int numSales(String b001);
}
