import java.util.ArrayList;

public class Order {
    private String orderID;
    private ArrayList<ProductsLine> productsLinesList;
    public Order(String id){
        this.orderID = id;
        productsLinesList = new ArrayList<ProductsLine>();
    }
    public String getOrderID() {
        return orderID;
    }
    public void addLP(int units, String productId) {
        ProductsLine newPL = new ProductsLine(units, productId);
        productsLinesList.add(newPL);
    }
    public ProductsLine getLP(int i) {
       return productsLinesList.get(i);
    }
    public ArrayList<ProductsLine> getProductsLinesList(){
        return productsLinesList;
    }
}
