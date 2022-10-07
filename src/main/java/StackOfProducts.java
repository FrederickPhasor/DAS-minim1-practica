public class StackOfProducts {
    private String productID;
    private int amount;

    public StackOfProducts(int amount, String productID){
        this.amount = amount;
        this.productID = productID;
    }
    public int getQuantity() {
        return amount;
    }
    public String getId(){
        return productID;
    }
}
