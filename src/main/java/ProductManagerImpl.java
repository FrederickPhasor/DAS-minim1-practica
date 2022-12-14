import java.util.*;

public class ProductManagerImpl implements ProductManager {

    Hashtable<String, User> users;
    Stack<Order> ordersStack;
    Hashtable<String, Product> products;
    public ProductManagerImpl(){
        users = new Hashtable<String,User>();
        products = new Hashtable<String,Product>();
        ordersStack = new Stack<Order>();
    }
    public List<Product> productsByPrice() {
        List<Product> nonSortedList = new ArrayList<Product>(products.values());
        Collections.sort(nonSortedList);
        List<Product> sortedList = nonSortedList;
        return sortedList;
    }
    public List<Product> productsBySales() {
        List<Product> nonSortedList = new ArrayList<Product>(products.values());
        SortBySales sortCriteria =new SortBySales() ;
        nonSortedList.sort(sortCriteria);
        List<Product> sortedList = nonSortedList;
        return nonSortedList;
    }

    public void addOrder(Order order) {
        String orderID = order.getOwnerID();
        ordersStack.push(order);
    }
    public Order processNextOrder() {
        Order orderToProcess = ordersStack.pop();
        RegisterSales(orderToProcess);
        RegisterUserPurchase(orderToProcess);
        return orderToProcess;
    }

    private void RegisterUserPurchase(Order orderToProcess) {
        String userID = orderToProcess.getOwnerID();
        User owner = users.get(userID);
        owner.addOrder(orderToProcess);
    }

    private void RegisterSales(Order orderToProcess) {
        ArrayList<StackOfProducts> stacksList = orderToProcess.getProductsStacks();
        for (StackOfProducts stack : stacksList) {
            Product product = products.get(stack.getProductID());
            int amount = stack.getQuantity();
            product.purchase(amount);
        }
    }

    public List<Order> ordersByUser(String userId) {
        return users.get(userId).myOrders;
    }

    public void createUser(String id, String name, String surname) {
        User newUser = new User(id,name,surname);
        users.put(id,newUser);
    }

    public void createProduct(String productId, String name, double price) {
        Product newProduct = new Product(productId, name, price);
        products.put(productId, newProduct);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }
    public int numUsers() {
        return users.size();
    }

    public int numProducts() {
        return products.size();
    }

    public int numOrders() {
        return ordersStack.size();
    }
    public int numSales(String productID) {
        return products.get(productID).getNumSales();
    }
}