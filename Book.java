//Name: Kai Adams
//Student Number: 501080302
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product  implements Comparable<Book>
{
  private String author;
  private String title;
  private int year;
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS 
     super(name, id , price , 10000, Product.Category.BOOKS);
     this.author = author;
     this.title = title;
     this.paperbackStock = paperbackStock;
     this.hardcoverStock = hardcoverStock;
     this.year = year;

  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
  	if (!(productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover") || productOptions.equalsIgnoreCase("EBook"))) //ensures that its only these that get accepted as book options anything else == errortime
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions) //does as it says gets stock
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
  	if(productOptions.equalsIgnoreCase("Paperback"))
    {
      return paperbackStock;
    }
    if(productOptions.equalsIgnoreCase("Hardcover"))
    {
      return hardcoverStock;
    }
    if(productOptions.equalsIgnoreCase("EBook"))
    {
      return super.getStockCount("EBook");
    }
    else
    {
      return 0;
    }
	}
  
  public void setStockCount(int stockCount, String productOptions) //sets initial stock
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
    if(productOptions.equalsIgnoreCase("Paperback"))
    {
      paperbackStock = stockCount;
    }
    if(productOptions.equalsIgnoreCase("Hardcover"))
    {
      hardcoverStock = stockCount;
    }
    if(productOptions.equalsIgnoreCase("Ebook"))
    {
      super.setStockCount(stockCount, productOptions);
    }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions) //when somethig is bought its stock is reduced
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
     if(productOptions.equalsIgnoreCase("Paperback"))
     {
       paperbackStock--;
     }
     if(productOptions.equalsIgnoreCase("Hardcover"))
     {
       hardcoverStock--;
     }
     if(productOptions.equalsIgnoreCase("Ebook"))
     {
       super.reduceStockCount(productOptions);
     }
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print() //override print for it to look pretty
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video
    super.print();
    System.out.print(" Book Title: " + title);
    System.out.print(" Author: " + author);
    System.out.print(" Year: " + year);
  }
  public String getAuthor() //returns author
  {
    return author;
  }

  public int compareTo(Book other) //used to sort by name(Look at read me)
	{
	
	if (this.year > other.year) return 1;
	else if(this.year < other.year) return -1;
	else return 0;
	}
}
