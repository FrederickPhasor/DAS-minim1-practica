
public class Product  implements Comparable<Product>{
    private String id;
    private String description;
    private double price;
    private int timesSold = 0;

    public Product(String id, String description, double price){
        this.id = id;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public int getNumSales() {
        return timesSold;
    }
    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price,o.price);
    }
}
