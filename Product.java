//Name: Kai Adams
//Student Number: 501080302
/*
 * class Product defines a product for sale by the system. 
 * 
 * A product belongs to one of the 5 categories below. 
 * 
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods  
 * 
 * Some products
 */
import java.util.Comparator;
public class Product
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES};
	
	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
	}
	
	public Product(String id)
	{
		this("Product", id, 0, 0, Category.GENERAL);
	}

	public Product(String name, String id, double price, int stock, Category category)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
	}
	/*
	 * This method always returns true in class Product. In subclasses, this method will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions) //checks to see if the inputed product option is valid
	{
		if(!productOptions.equals(null) || !productOptions.equals(""))
		{
			return true;
		}
	
		return false;
		
	}
	
	public Category getCategory() //returns a product category
	{
		return category;
	}
	
	public String getName() //returns the name of a product
	{
		return name;
	}

	public void setName(String name) //names a product
	{
		this.name = name;
	}

	public String getId() //returns the product id of a product
	{
		return id;
	}

	public void setId(String id) //gives a product an productid
	{
		this.id = id;
	}

	public double getPrice() // returns the price of a product
	{
		return price;
	}

	public void setPrice(double price) //gives a product its price
	{
		this.price = price;
	}

	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions) //returns a product stock
	{
		return stockCount;
	}
	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions) //gives a product its stock
	{
		this.stockCount = stockCount;
	}
	/*
	 * Reduce the number of items currently in stock for this product by 1 (called when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOIptions) //reduces stock of a product when it is ordered
	{
		stockCount--;
	}
	
	public void print() //prints a product to make it look pretty
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}
	
	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other) //allows user to compare products
	{
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}

}

class ProductNameCompator implements Comparator<Product> //sorts products by name
{
	public int compare(Product a , Product b)
	{
		if(a.getName().charAt(0) > b.getName().charAt(0)) return 1;
		else if(a.getName().charAt(0) < b.getName().charAt(0)) return -1;
		else return 0;
	}
}

class ProductPriceCompator implements Comparator<Product> //sorts products by price
{
	public int compare(Product a, Product b)
	{
		if(a.getPrice() > b.getPrice()) return 1;
		else if(a.getPrice() < b.getPrice()) return -1;
		else return 0;
	}
}


