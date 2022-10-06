import java.util.Comparator;
public class SortBySales implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return Double.compare(o1.getNumSales(), o2.getNumSales());
    }
}
