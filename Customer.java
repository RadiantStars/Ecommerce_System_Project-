import javax.lang.model.util.ElementScanner14;

//Name: Kai Adams
//Student Number: 501080302
/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer implements Comparable<Customer>
{
	private String id;  
	private String name;
	private String shippingAddress;
	private Cart cart; //stores customers items until they want them ordered
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
	}
	
	public Customer(String id, String name, String address, Cart cart)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.cart = cart;
	}
	
	public String getId() // gives id
	{
		return id;
	}
	public void setId(String id) //sets id
	{
		this.id = id;
	}
	public String getName() //tells us the customer name
	{
		return name;
	}
	
	public void setName(String name) //does as says sets name
	{
		this.name = name;
	}
	
	public String getShippingAddress() //does as says gives address
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress) //does as says sets address
	{
		this.shippingAddress = shippingAddress;
	}
	
	public void print() //override to look pretty
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	public boolean equals(Object other) //Allows user to compare customers
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

	public Cart getCart() //returns a customers unique cart with their items
	{
		return cart;
	}
	
	public int compareTo(Customer other) //used to sort by name
	{
	
	if (this.name.charAt(0) > other.name.charAt(0)) return 1;
	else if(this.name.charAt(0) < other.name.charAt(0)) return -1;
	else return 0;
	}
	
}
