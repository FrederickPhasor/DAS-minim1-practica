import java.util.ArrayList;

public class Order {
    private String ownerID;
    private ArrayList<StackOfProducts> productsLinesList;
    public Order(String ownerID){
        this.ownerID = ownerID;
        productsLinesList = new ArrayList<StackOfProducts>();
    }
    public String getOwnerID() {
        return ownerID;
    }
    public void addProduct(String productId,int units) {
        StackOfProducts newPL = new StackOfProducts(units, productId);
        productsLinesList.add(newPL);
    }
    public StackOfProducts getStackOfProducts(int i) {
       return productsLinesList.get(i);
    }
    public ArrayList<StackOfProducts> getProductsStacks(){
        return productsLinesList;
    }
}
