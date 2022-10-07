
public class Product  implements Comparable<Product>{
    private String id;
    private String name;
    private double price;
    private int timesSold = 0;

    public Product(String id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public void purchase(int amount){
        timesSold += amount;
    }
    public String getProductId() {
        return  id ;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getNumSales() {
        return timesSold;
    }
    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price,o.price);
    }
}
