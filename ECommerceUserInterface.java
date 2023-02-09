//Name: Kai Adams
//Student Number: 501080302
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();  //Makes the system

		Scanner scanner = new Scanner(System.in);  //Allows commands to be read
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try
			{
				String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) //Exits program
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				amazon.createCustomer(name, address); //calls the method

			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
					System.out.print("Order Number: ");
					// Get order number from scanner
					orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					amazon.shipOrder(orderNumber); //does the work to ship

					
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerid = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				customerid = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				amazon.printOrderHistory(customerid); //does method
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productid = "";
				String customerid = "";
				System.out.print("Product Id: ");
			  // Get product Id from scanner
				productid = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				customerid = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				amazon.orderProduct(productid, customerid, ""); //does method

				
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productid = "";
				String customerid = "";
				String options = "";
				System.out.print("Product Id: ");
				// get product id
				productid = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerid = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book forma and store in options string
				options = scanner.nextLine();
				
				// Order product. Check for error mesage set in ECommerceSystem
				// Print order number string if order number is not null
				amazon.orderProduct(productid, customerid, options); //does method

			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productid = "";
				String customerid = "";
				String options = "";
				System.out.print("Product Id: ");
				// get product id
				productid = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerid = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				options = scanner.nextLine();
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				options = options + " " + scanner.nextLine();
				//order shoes
				amazon.orderProduct(productid, customerid, options); //does method

			}
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				orderNumber = scanner.nextLine();
				// cancel order. Check for error
				amazon.cancelOrder(orderNumber); //does method and cancels
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice(); //sorts using collections
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName(); //same
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName(); //same
			}
			else if(action.equalsIgnoreCase("BOOKSBYAUTHOR")) //Sorts books by a particular aauthor
			{
				String author = "";
				System.out.print("Author: ");
				author = scanner.nextLine();

				amazon.authorFilter(author);
			}
			else if(action.equalsIgnoreCase("STATS")) //Prints the number of times each product has been ordered
			{
				amazon.statprinter();
			}
			else if(action.equalsIgnoreCase("ADDTOCART")) // Takes product id and customer id and sometimes productoptions if necerrary and adds the product to that particular customers cart 
			{
				String productid = "";
				System.out.print("Product ID: ");
				productid = scanner.nextLine();
				String customerId = "";
				System.out.print("Customer ID: ");
				customerId = scanner.nextLine();
				String productoptions = "";

				if(amazon.bookorshoecheck(productid) == true) //Only gets called if its a book or a shoe and requires productoptions
				{
					System.out.print("ProductOption: ");
					productoptions = scanner.nextLine();

					amazon.addtocart(productid, customerId, productoptions);
				}
				else
				{
					amazon.addtocart(productid, customerId, productoptions);	
				}

				


			}
			else if(action.equalsIgnoreCase("REMCARTITEM")) // removes item from cart
			{
				String productid = "";
				System.out.print("Product ID: ");
				productid = scanner.nextLine();
				String customerid = "";
				System.out.print("Customer ID: ");
				customerid = scanner.nextLine();

				amazon.removefromcart(customerid, productid);
			}
			else if(action.equalsIgnoreCase("PRINTCART")) //prints all of a customers cart items
			{
				String customerid = "";
				System.out.print("Customer ID: ");
				customerid = scanner.nextLine();
				amazon.printcart(customerid);
			}
			else if(action.equalsIgnoreCase("ORDERITEMS")) // orders all the items in a customers cart
			{
				System.out.print("CustomerID: ");
				String customerid = scanner.nextLine();
				amazon.ordercart(customerid);
			}
			else if(action.equalsIgnoreCase("RATE")) // allows a customer to rate a product after it has been shipped to them
			{
				String customerid;
				System.out.print("Customer ID: ");
				customerid = scanner.nextLine();
				String productid;
				System.out.print("Product ID: ");
				productid =scanner.nextLine();
				Integer rating;
				System.out.print("Enter Rating: ");
				rating = scanner.nextInt();
				amazon.prodrater(productid, rating, customerid);
			}
			
			else if(action.equalsIgnoreCase("PRINTRATECATEGORY")) //prints all of a paticular catagorys products above a certain raiting
			{
				String catagory;
				System.out.print("Enter Category: ");
				catagory = scanner.nextLine();
				Integer rate;
				System.out.print("Enter Rating: ");
				rate = scanner.nextInt();
				amazon.printratecatagory(catagory, rate);
			}
			else if(action.equalsIgnoreCase("PRINTAVGRATE")) //Prints the average rating of all the products
			{
				amazon.avgrateprinter();
			}
			else if (action.equalsIgnoreCase("PRINTBYRATE")) //Prints the number of times the products were given a particular rating
			{
				Integer rate;
				System.out.print("Enter rating to be shown: ");
				rate = scanner.nextInt();
				amazon.printbyrates(rate);
			}
			System.out.print("\n>");
			
			}
			catch(UnknownCustomerException e) //Catches error if user inputs a wrong customer id
			{
				System.out.println(e);
			}
			catch(UnknownProductException e) //Catches error is user inputs a wrong product id
			{
				System.out.println(e);
			}
			catch(InvalidChoiceException e)	//catches error if user inputs any wrong information
			{
				System.out.println(e);
			}
			catch(ProductOutofStockException e) //catches error if a product a customer wants is out of stock
			{
				System.out.println(e);
			}
			catch(InputMismatchException e) //catches error if the user tries to input anything that is not a interger
			{
				System.out.println("This is not an Integer");
			}
			
		}
	}
}
