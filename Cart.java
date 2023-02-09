//Name: Kai Adams
//Student Number: 501080302
import java.util.ArrayList;

public class Cart
{
    private ArrayList<CartItem> cart; //Makes a list of cartitems for each customer
    private String customerid;
    public Cart(String customerid)
    {
      this.cart = new ArrayList<CartItem>(); 
       this.customerid = customerid;
    }
    
    public void cartadd(CartItem item) //adds an item to the list
    {
        cart.add(item);
    }

    public ArrayList<CartItem> citems() //returns a particular customers cart
    {
        return cart;
    }

    public void removecartitem(CartItem item) // removes an item from a particualr customers cart
    {
        cart.remove(item);
    }

}