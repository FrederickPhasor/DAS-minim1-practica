import java.util.ArrayList;

public class User {
    private String name;
    private String surname;
    private String id;
    ArrayList<Order> myOrders;

    public User(String id, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        myOrders = new ArrayList<Order>();
    }
    public void addOrder(Order newOrder){
        myOrders.add(newOrder);
    }
    public String getName() {
        return name;
    }
}
