//Name: Kai Adams
//Student Number: 501080302
/*
 * class ProductOrder defines an order of a specific product by a customer
 * 
 * The order number is generated by the system when a ProductOrder object is created.
 * 
 * Also stores any product options chosen by this customer (e.g. paperback book, certain size of a product etc)
 */
public class ProductOrder
{
	private String 		orderNumber;
	private Product 	product;
	private String    productOptions;
	private Customer 	customer;
	
	public ProductOrder(String orderNumber, Product product, Customer customer, String productOptions)
	{
		this.orderNumber = orderNumber;
		this.product = product;
		this.customer = customer;
		this.productOptions = productOptions;
	}

	public String getOrderNumber() //returns an orders order number
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) //gives an order an order number
	{
		this.orderNumber = orderNumber;
	}
	public Product getProduct() //returns the product tht has been ordered
	{
		return product;
	}
	public void setProduct(Product product) //sets what product is being ordered
	{
		this.product = product;
	}
	public Customer getCustomer() //returns the custoer that ordered the product
	{
		return customer;
	}
	public void setCustomer(Customer customer) //sets what custoer ordered the product
	{
		this.customer = customer;
	}
	
	public void print() //prints the products to make it look pretty
	{
		System.out.printf("\nOrder # %3s Customer Id: %3s Product Id: %3s Product Name: %12s Options: %8s", orderNumber, customer.getId(), product.getId(), product.getName(), 
				               productOptions);
	}
	/*
	 * Two ProductOrder objects are equal if they have the same order number string.
	 */
	public boolean equals(Object other) //allows user to compare productorders
	{
		// Compare two ProductOrder objects based on their orderNumber strings
		// Replace the line below (Hint: look at class Product equals())
		ProductOrder otherZ = (ProductOrder) other;
		return this.orderNumber.equals(otherZ.orderNumber);
	}
}
