import java.util.*;

public class ProductManagerImpl implements ProductManager {

    Hashtable<String, String> users;
    Hashtable<String, ArrayList<Order>> usersOrders;
    Stack<Order> ordersStack;
    Hashtable<String, Product> products;

    public ProductManagerImpl(){
        usersOrders = new Hashtable<String, ArrayList<Order>>();
        users = new Hashtable<String,String>();
        products = new Hashtable<String,Product>();
        ordersStack = new Stack<Order>();
    }

    public List<Product> productsByPrice() {
        List<Product> nonSortedList = new ArrayList<Product>(products.values());
        Collections.sort(nonSortedList);
        return nonSortedList;
    }

    public List<Product> productsBySales() {
        List<Product> nonSortedList = new ArrayList<Product>(products.values());
        Collections.sort(nonSortedList, new SortBySales());
        return nonSortedList;
    }

    public void addOrder(Order order) {
        String orderID = order.getOrderID();

        ordersStack.push(order);
    }
    public Order processOrder() {
        Order orderToProcess = ordersStack.pop();
        ArrayList<ProductsLine> p = orderToProcess.getProductsLinesList();
        for (ProductsLine pl : p) {
            Product product = products.get(pl.GetId());
            product.purchase(pl.getQuantity());
        }
        String userID = orderToProcess.getOrderID();
        ArrayList<Order> newOrder;
        if (usersOrders.get(userID) == null){
              newOrder =  new ArrayList<Order>();
            newOrder.add(orderToProcess);
            usersOrders.put(userID,newOrder);
        }
        else{
             usersOrders.get(userID).add(orderToProcess);
        }

        return orderToProcess;
    }

    public List<Order> ordersByUser(String userId) {
        return usersOrders.get(userId);
    }

    public void addUser(String s, String name, String surname) {
        users.put(s,name);
        users.put(s,surname);
    }

    public void addProduct(String productId, String description, double price) {
    Product newProduct = new Product(productId, description,price);
    products.put(productId,newProduct);
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