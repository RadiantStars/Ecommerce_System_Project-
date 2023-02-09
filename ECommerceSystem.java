//Name: Kai Adams
//Student Number: 501080302
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.xml.catalog.Catalog;



/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    private TreeMap<String,Product>  products = new TreeMap<String,Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    private TreeMap<String, Integer> stats = new TreeMap<String, Integer>(); 
    private TreeMap<String, Integer> onestar = new TreeMap<String, Integer>();
    private TreeMap<String, Integer> twostar = new TreeMap<String, Integer>();
    private TreeMap<String, Integer> threestar= new TreeMap<String, Integer>();
    private TreeMap<String, Integer> fourstar = new TreeMap<String, Integer>();
    private TreeMap<String, Integer> fivestar = new TreeMap<String, Integer>();
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem() //sets up the system for use reads products file, sets up stat and rating maps and adds customers to the system
    {
      try
      {
        prodgetter();
        statmaker();

        String temp = "";

        customers.add(new Customer(temp = generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin",new Cart(temp)));
        customers.add(new Customer(temp = generateCustomerId(),"Prince Humperdinck", "The Castle, Florin",new Cart(temp)));
        customers.add(new Customer(temp = generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine",new Cart(temp)));
        customers.add(new Customer(temp = generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach",new Cart(temp)));

      }
      catch(IOException e)
      {
        System.out.println(e);
        System.exit(1);
      }
    	// Create some customers. Notice how generateCustomerId() method is used

    }
    
    private String generateOrderNumber() //makes a new ordernumber
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId() //makes a new customer id
    {
    	return "" + customerId++;
    }
    
    private String generateProductId() //makes a new product id
    {
    	return "" + productId++;

    }

    
    public void printAllProducts() //prints all the products avaible in the map
    {
    	for (Product product: products.values())
      {
        product.print();
      }
    		
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
      for(Product product: products.values())
      {
        if(product.getCategory().equals(Product.Category.BOOKS) )
        {
          product.print();
        }
      }
    }
    
    // Print all current orders
    public void printAllOrders()
    {
      for(int i = 0; i < orders.size(); i++)
      {
        orders.get(i).print();
      }
    
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
      for(int i = 0; i < shippedOrders.size(); i++ )
      {
        shippedOrders.get(i).print();
      }
    	
    }
    
    // Print all customers
    public void printCustomers()
    {
      for (int i = 0; i < customers.size(); i++)
      {
        customers.get(i).print();
      }
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerid) throws UnknownCustomerException
    {
        // Make sure customer exists - check using customerId
      // If customer does not exist, set errMsg String and return false
      // see video for an appropriate error message string
      // ... code here
      Customer found = customercheck(customerid);
      
      // Print current orders of this customer 
      System.out.println("Current Orders of Customer " + customerid);
      // enter code here
      for(int i = 0; i < orders.size(); i++)
      {
        if(found == null) //if there is not customer it breaks out of loop
        {
          break;
        }
        else if(orders.get(i).getCustomer().equals(found)) //prints only the customers orders
        {
          orders.get(i).print();
        }
      }
      // Print shipped orders of this customer 
      System.out.println("\nShipped Orders of Customer " + customerid);
      //enter code here
      for(int i = 0; i < shippedOrders.size(); i++)
      {
        if(found == null) 
        {
          break;
        }
        else if(shippedOrders.get(i).getCustomer().equals(found))
        {
          shippedOrders.get(i).print();
        }
      }
    }
    
    public void orderProduct(String productid, String customerid, String productoptions) throws InvalidChoiceException,ProductOutofStockException
    {
      	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
      Customer gottencust = customercheck(customerid);
    
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object
      Product gottenprod = productcheck(productid);
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
      if(gottenprod.getCategory() == Product.Category.BOOKS) //checks to see if the catagory of book requested is a valid option
      {
        if ((gottenprod.validOptions(productoptions)) == false)
        {
          throw new InvalidChoiceException("Product Book ProductId " + productId + " Invalid Options: " + productoptions);
        }
      }
      else if(gottenprod.getCategory() == Product.Category.SHOES)  //checks to see if the catagory of shoe requested is a valid option
      {
        if ((gottenprod.validOptions(productoptions)) == false)
        {
          throw new InvalidChoiceException("Product Shoe ProductId " + productId + " Invalid Options: " + productoptions);
        }
      }
      else
      {
        if (!productoptions.equals("")) //prevents the orderbook funcion from ordering an item that is not a book as a book
        {
          throw new InvalidChoiceException("This is not a Book or Shoe");
        }
      }
    
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if(gottenprod.getStockCount(productoptions) == 0) //checks stock sets eror if theres none
      {
        throw new ProductOutofStockException("Product is out of Stock");
      }

      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
      String neworderno = generateOrderNumber();
      ProductOrder neworder = new ProductOrder(neworderno, gottenprod, gottencust, productoptions); //makes new order if it passes all the checks
      gottenprod.reduceStockCount(productoptions); //lowers stock of item that was ordered
      orders.add(neworder); //adds to orders list
      statadder(productid);
      System.out.println("Order #" + neworderno);
    }
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address) throws InvalidChoiceException
    {
        // Check name parameter to make sure it is not null or ""
      // If it is not a valid name, set errMsg (see video) and return false
      // Repeat this check for address parameter
      // Create a Customer object and add to array list
      if(name.equals(null)||name.equals("")) //ensures that the name slot isnt empty and sets error if it is
      {
        throw new InvalidChoiceException("Invalid Customer Name");
      }
      else if(address.equals(null)||address.equals("")) // does same for address
      {
        throw new InvalidChoiceException("Invalid Customer Address");
      }
      else 
      {
        String temp = "";
        customers.add(new Customer(temp = generateCustomerId(),name,address,new Cart(temp))); //if real name and address makes new person exist
      }
    }

    
    public void shipOrder(String orderNumber) throws InvalidChoiceException
    {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return null
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order
    	ProductOrder store = null;
      for (int i = 0; i < orders.size(); i++)
      {
        if (orders.get(i).getOrderNumber().equals(orderNumber)) //ensures the order exists and if it does it removes it from orders and puts it in shippedorders
        {
          store = orders.get(i);
          shippedOrders.add(orders.get(i));
          orders.remove(orders.get(i));
          store.print();
          break;
        }
      }
        if(store == null) //sets error if no exists
        {
          throw new InvalidChoiceException("Order " + orderNumber + " Not Found");
        }
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber) throws InvalidChoiceException
    {

        // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
      for (int i = 0; i < orders.size(); i++)
      {
        if (orders.get(i).getOrderNumber().equals(orderNumber)) //if order is found it removes it
        {
          orders.remove(i);
          return;
        }
      }

      throw new InvalidChoiceException("Order " + orderNumber + " Not Found");//no order means error time
    }
    // prints products by increasing price
    public void sortByPrice()
    {
      ArrayList<Product> all = new ArrayList<Product>();
      for(Product p : products.values())
      {
        all.add(p);
      }
      Collections.sort(all, new ProductPriceCompator() ); //uses sort class i made in the Product.java file i made
      for(Product z : all)
      {
        z.print();
      }
    }
    
    
    // prints products alphabetically by product name
    public void sortByName()
    {
      ArrayList<Product> all = new ArrayList<Product>();
      for(Product p : products.values())
      {
        all.add(p);
      }
      Collections.sort(all, new ProductNameCompator()); //same as above
      for(Product z : all)
      {
        z.print();
      }
    }
    
        
    // sorts customers alphabetically by customer name
    public void sortCustomersByName()
    {
      Collections.sort(customers); //uses comparable method i made in the Customer.java file
    }

    //prints the books written by a particualr author
    public void authorFilter(String author) throws InvalidChoiceException 
    {

        ArrayList<Book> books = new ArrayList<Book>();
        Boolean contains = false;
        for(Product p : products.values())
        {
          if(p.getCategory().equals(Product.Category.BOOKS))
          {
            books.add((Book) p);
          }
        }
        
        Collections.sort(books);
        for(int i = 0; i < books.size(); i++)
        {
          if(books.get(i).getAuthor().equalsIgnoreCase(author))
          {
            books.get(i).print();
            contains = true;
          }
          else if(i == books.size()-1 && contains == false)
          {
            throw new InvalidChoiceException("There are no books by this Author: " + author);
          }
        }
  
    }

    //this reads the products.txt file and adds the the product to the products map
    private void prodgetter() throws IOException
    {
      Scanner scanner = new Scanner( new File("products.txt")); //reads file
      while(scanner.hasNext())
      {
        String [] stocks = {}; //makes empty array
        String [] info = {}; //makes empty array
        String catagory = scanner.nextLine(); //reads in 1st line
        String name = scanner.nextLine(); //reads in 2nd line
        String price = scanner.nextLine(); //reads in 3rd line
        String stock = scanner.nextLine(); //reads in 4th line
        if(stock.contains(" ")) // if the 4th line has a space that means it has two product stocks for books so it is split to isolate each stock individually
        {
          stocks = stock.split(" ");
        }
        String prodinfo = scanner.nextLine(); //reads 5th line
        if(!prodinfo.isBlank()) //if the 5th line is not blank it means it has info for a book or shoe and each piece of info is isolated into its own array 
        {
          info = prodinfo.split(":");
        }
        
        String productid = generateProductId(); //generates new product id to be used 
        
        //depending of the catagory a different method is called to create the product and add it to the products map
        if(catagory.equals("BOOKS"))
        {
          products.put(productid, new Book(name, productid, Double.parseDouble(price),Integer.parseInt(stocks[0]), Integer.parseInt(stocks[1]), info[0], info[1], Integer.parseInt(info[2])));
        }
        else if(catagory.equals("CLOTHING"))
        {
          products.put(productid, new Product(name, productid, Double.parseDouble(price), Integer.parseInt(stock), Product.Category.CLOTHING));
        }
        else if(catagory.equals("FURNITURE"))
        {
          products.put(productid, new Product(name, productid, Double.parseDouble(price), Integer.parseInt(stock), Product.Category.FURNITURE));
        }
        else if(catagory.equals("COMPUTERS"))
        {
          products.put(productid, new Product(name, productid, Double.parseDouble(price), Integer.parseInt(stock), Product.Category.COMPUTERS));
        }
        else if(catagory.equals("SHOES"))
        {
          products.put(productid, new Shoes(name, productid, Double.parseDouble(price), Integer.parseInt(info[0]), info[1], Integer.parseInt(stock)));
        }
      }
    }
    
    //checks to see if the inputed customer id is a real customer and returns it 
    public Customer customercheck(String customerid) throws UnknownCustomerException
    {
      for(Customer c : customers)
      {
        if(c.getId().equals(customerid))
        {
          return c;
        }
      }

      throw new UnknownCustomerException("This customer does not exist: " + customerid);
    }


    //checks to see if the inputted procuct id inputted is a real product and returns it
    public Product productcheck(String productid) throws UnknownProductException
    {
      Product foundprod;
      if(!products.containsKey(productid))
      {
        throw new UnknownProductException("This Product does not exist: " + productid);
      }
      else
      {
        return foundprod = products.get(productid);
      }
    }


    //adds a product to a customers cart
    public void addtocart(String productid, String customerid, String productoptions) throws InvalidChoiceException
    {
        Customer foundcust = customercheck(customerid); //checks for customer
        Product foundprod = productcheck(productid); //checks for product
       
        if(foundprod.validOptions(productoptions) == true) //checks if the inputted options are valid 
        {
          if (foundprod.getStockCount(productoptions)==0) // checks the stock of product
          {
            throw new ProductOutofStockException("Product is out of Stock");
          }
          else
          {
            foundcust.getCart().cartadd(new CartItem (foundprod, productoptions)); //adds item to cart array if all checks are passed
          }
        }
        else
        {
          throw new InvalidChoiceException("This is not a Book or a Shoe: " + productoptions); //thows error if it doesnt
        }


  }

  //checks to see if the inputted product id is a book or shoes. This is used mainly in add to cart to know when to give the option to input product options
  public boolean bookorshoecheck(String productid) throws InvalidChoiceException
  {
      Product foundprod = productcheck(productid); //chesck prodduct

      if(foundprod.getCategory().equals(Product.Category.BOOKS)|| foundprod.getCategory().equals(Product.Category.SHOES)) 
      {
        return true; //retuns true if product is a book or shoe
      }
      else
      {
        return false;
      }
  }
  
  //prints all the products in a customers cart
  public void printcart(String customerid) throws InvalidChoiceException
  {
      Customer foundcust = customercheck(customerid); //checks customer

      if(foundcust.getCart().citems().isEmpty() == true) //if cart is empty it throws error 
      {
        throw new InvalidChoiceException("The cart does not contain any products");
      }
      else
      {
        for(CartItem p : foundcust.getCart().citems()) //loops through the carts items and prints them
        {
          p.getProduct().print();
        }  
      }

  }


  //removes item from customers cart array
  public void removefromcart(String customerid, String productid) throws UnknownCustomerException, UnknownProductException, InvalidChoiceException
  {
    Customer foundcust = customercheck(customerid); //checks customer
    Product foundprod = productcheck(productid); //checks products

    if(foundcust.getCart().citems().isEmpty() == true) //checks to see if cart is empty
    {
      throw new InvalidChoiceException("The cart does not contain any products");
    }

    for(int i = 0; i < foundcust.getCart().citems().size(); i++ ) //loops through cart to see if it can find item and remove it
    {
      if(foundcust.getCart().citems().get(i).getProduct().equals(foundprod))
      {
        foundcust.getCart().removecartitem(foundcust.getCart().citems().get(i));
        break;
      }
      else if(i == foundcust.getCart().citems().size()-1)
      {
        throw new UnknownProductException("Product not in cart");
      }
    }
  }

  //loops through a customers cart and orders each product
  public void ordercart(String customerid) throws InvalidChoiceException
  {
      Customer foundcust = customercheck(customerid); //checks customer
      String productid;
      String productOptions = "";
     
      if(foundcust.getCart().citems().isEmpty() == true) //checks to see if cart is empty
      {
        throw new InvalidChoiceException("The cart does not contain any products");
      }
    
      for(int i = 0; i < foundcust.getCart().citems().size(); i++) //loops through cart
      {
        productid = foundcust.getCart().citems().get(i).getProduct().getId(); //gets each products product id
        

        if(foundcust.getCart().citems().get(i).getProduct().getCategory().equals(Product.Category.BOOKS)) //if product is a book it retrieves the product options
        {
          productOptions = foundcust.getCart().citems().get(i).getProductOptions();
          orderProduct(productid, customerid, productOptions);
        }
        else if(foundcust.getCart().citems().get(i).getProduct().getCategory().equals(Product.Category.SHOES)) //if product is a shoe it retieves the product options
        {
          productOptions = foundcust.getCart().citems().get(i).getProductOptions();
          orderProduct(productid, customerid, productOptions);
        }
        else
        {
          orderProduct(productid, customerid, productOptions); //if neither it orders with a blank string
        }
        removefromcart(customerid, productid); //removes product after it it has been ordered
        i--; //fixes loop when a product is removed
      }

  }

  //makes the maps for stats and ratings
  public void statmaker()
  {
    int value = 0;
    for(Product p : products.values())
    {
      stats.put(p.getId(), value);
      onestar.put(p.getId(), value);
      twostar.put(p.getId(), value);
      threestar.put(p.getId(), value);
      fourstar.put(p.getId(), value);
      fivestar.put(p.getId(), value); 
    }

  }

  //anytime a product is ordered it increase its stat
  public void statadder(String productid)
  {
    stats.put(productid, stats.get(productid)+1);
  }

  //prints the stats map and makes it look pretty
  public void statprinter()
  {
    Map<String,Integer> sortedmap = sortbyvalues(stats);

    for(Map.Entry<String, Integer> pair : sortedmap.entrySet())
    {
      System.out.println("ProductID: " + pair.getKey() + " Times ordered: " + pair.getValue());
    }
  }
  
  //this is a comparator that sorts the stats map by value in decending numerical order 
  public static <K, V extends Comparable <V>> Map<K, V> sortbyvalues(final Map<K, V> map)
  {
    Comparator<K> value = new Comparator<K>() 
    {
      public int compare(K k1, K k2)
      {
        int compare = map.get(k1).compareTo(map.get(k2));
        if(compare == 0)
        {
          return 1;
        }
        else if (compare > 0)
        {
          return -1;
        }
        else
        {
          return 1;
        }
      }
    };

      Map<K, V> sorted = new TreeMap<K, V>(value);
      sorted.putAll(map);
      return sorted;
    
  }

  //allows user to rate individual products
  public void prodrater(String productid ,Integer rating, String customerid) throws InvalidChoiceException, UnknownProductException
  {
    customercheck(customerid); //checks customer

    ratecheck(rating); //checks if rating if correct

    productcheck(productid); //checks product

    if(shipcheck(customerid, productid) == true) //checks if the product the customer is trying to order has been shipped to then increments the rating
    {
      if(rating == 1)
      {
        onestar.put(productid, onestar.get(productid)+1);
      }
      else if(rating == 2)
      {
        twostar.put(productid, twostar.get(productid)+1);
      }
      else if(rating == 3)
      {
        threestar.put(productid, threestar.get(productid)+1);
      }
      else if (rating == 4)
      {
        fourstar.put(productid, fourstar.get(productid)+1);
      }
      else if(rating == 5)
      {
        fivestar.put(productid, fivestar.get(productid)+1);
      }
    }
  }


  //checks if the customers product they are rating has been shipped to them
  public Boolean shipcheck(String customerid, String productid) throws InvalidChoiceException
  {
    for(ProductOrder p : shippedOrders)
    {
      if(p.getCustomer().getId().equals(customerid))
      {
        if(p.getProduct().getId().equals(productid))
        {
          return true;
        }
      }
    }
    throw new InvalidChoiceException("The customer " + customerid + " has not gotten this product yet: " + productid);
  }
  
  //prints the ammount of times a product has recieved a specific rating
  public void printbyrates(Integer rate) throws InvalidChoiceException
  {
    ratecheck(rate);

    if(rate == 1)
    {
      System.out.println("One star ratings");
      rateprinter(onestar);
    }
    else if(rate == 2)
    {
      System.out.println("\n" + "Two star rarings");
      rateprinter(twostar);
    }
    else if(rate == 3)
    { 
    System.out.println("\n" + "Three star rarings");
    rateprinter(threestar);
    }
    else if(rate == 4)
    {
      System.out.println("\n" + "Four star rarings");
      rateprinter(fourstar);
    }
    else if(rate == 5)
    {
    System.out.println("\n" + "Five star rarings");
    rateprinter(fivestar);
    }  
  }

  //makes the rating map loop pretty
  private void rateprinter(TreeMap<String,Integer> map)
  {
    for(Map.Entry<String,Integer> entry : map.entrySet())
    {
      System.out.println("Product ID: " + entry.getKey() + " Number of ratings: " + entry.getValue());
    }
  }

  //returns the average rate of a product
  private double prodavgrate(String productid)
  {
    if(onestar.get(productid) + twostar.get(productid) + threestar.get(productid) + fourstar.get(productid) + fivestar.get(productid) == 0) // checks to see if the denominator is 0 or not before to avoid errors
    {
      return 0;
    }
    else
    {
      double sumOfStars = onestar.get(productid) + twostar.get(productid) + threestar.get(productid) + fourstar.get(productid) + fivestar.get(productid);
      double allRatings = onestar.get(productid)*1 + twostar.get(productid)*2+ threestar.get(productid)*3 + fourstar.get(productid)*4 + fivestar.get(productid)*5;
      Double average = allRatings / sumOfStars; // calculates average
      return average;
    }

  }

  //makes the average print look pretty
  public void avgrateprinter()
  {
      for(String s : onestar.keySet())
      {
        System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
      }
  }

  //user picks a catagory and rating and only products of that catagory and ratings that are above the inputted rating are printed
  public void printratecatagory(String catagory, Integer rate) throws InvalidChoiceException
  {
    ratecheck(rate); //checks rate
    catagorycheck(catagory); //checks if catagory is valid
    for(String s : onestar.keySet()) //uses the keys to loop through the ids
    {
      if(catagory.equalsIgnoreCase("CLOTHING") && productcheck(s).getCategory().equals(Product.Category.CLOTHING)) //ensures that products of the catagory asked for are printed
      {
        if(prodavgrate(s) > rate) //ensures the rate of the product printed is above the inputted rate
        {
          System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
        }
      }
      else if(catagory.equalsIgnoreCase("BOOKS") && productcheck(s).getCategory().equals(Product.Category.BOOKS))
      {
        if(prodavgrate(s) > rate)
        {
          System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
        }
      }
      else if(catagory.equalsIgnoreCase("FURNITURE") && productcheck(s).getCategory().equals(Product.Category.FURNITURE))
      {
        if(prodavgrate(s) > rate)
        {
          System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
        }
      }
      else if(catagory.equalsIgnoreCase("COMPUTERS") && productcheck(s).getCategory().equals(Product.Category.COMPUTERS))
      {
        if(prodavgrate(s) > rate)
        {
          System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
        }
      }
      else if(catagory.equalsIgnoreCase("SHOES") && productcheck(s).getCategory().equals(Product.Category.SHOES))
      {
        if(prodavgrate(s) > rate)
        {
          System.out.println("Product ID: " + s + " Average Rating: " + prodavgrate(s));
        }
      }
    }
  }

  //checks if the inputted catagory is valid
  private void catagorycheck(String catagory)
  {
    if(!(catagory.equalsIgnoreCase("CLOTHING")|| catagory.equalsIgnoreCase("BOOKS") || catagory.equalsIgnoreCase("FURNITURE") || catagory.equalsIgnoreCase("COMPUTERS") || catagory.equalsIgnoreCase("SHOES")))
    {
      throw new InvalidChoiceException("This is not a valid catagory: " + catagory);
    }
  }

  //checks if the inputted rate is valid
  private void ratecheck(Integer rate) throws InvalidChoiceException
  {
    if(rate > 5 || rate < 1)
    {
      throw new InvalidChoiceException("The rating can only be between 1-5 ");
    }
  }
}

//custom exception for when a customer is not found
class UnknownCustomerException extends RuntimeException
{
  public UnknownCustomerException(String message)
  {
    super(message);
  }
}

//custom exception for when a product is not found
class UnknownProductException extends RuntimeException
{
  public UnknownProductException(String message)
  {
    super(message);
  }
}

//custom exception for when the user inputs an invalid choice
class InvalidChoiceException extends RuntimeException
{
  public InvalidChoiceException(String message)
  {
    super(message);
  }
}

//custom exception for when a product is out of stock
class ProductOutofStockException extends RuntimeException
{
  public ProductOutofStockException(String message)
  {
    super(message);
  }
}
