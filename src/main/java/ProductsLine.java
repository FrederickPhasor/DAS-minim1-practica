import java.util.Hashtable;

public class ProductsLine {
    private int amount;
    private String productID;
    public ProductsLine(int amount, String productID){
        this.amount = amount;
        this.productID = productID;
    }
    public int getQuantity() {
        return amount;
    }
    public String GetId(){
        return productID;
    }
}
