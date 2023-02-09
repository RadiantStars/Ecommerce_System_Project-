//Name: Kai Adams
//Student Number: 501080302
public class CartItem
{
    private Product item;   //this is the product that the customer wants in their cart
    private String productoptions;

    public CartItem(Product item, String productoptions)
    {
        this.item = item;
        this.productoptions = productoptions;
    }
    
    public String getProductOptions() //returns the product in the cart particular product options
    {
        return productoptions;
    }

    public Product getProduct() //returns the product in a customers cart
    {
        return item;
    }

}
