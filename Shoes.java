import javax.lang.model.util.ElementScanner14;

//Name: Kai Adams
//Student Number: 501080302

public class Shoes extends Product
{
    
    private int size;
    private String colour;
    private int[][] stockList= new int [2][5];
    private int stock;
    private String options = size + "" + colour;
    public Shoes(String name, String id, double price, int size, String colour, int stock)
    {
        super(name, id, price, 100, Product.Category.SHOES);
        this.size = size;
        this.colour = colour;
        if(colour.equals("Brown")) 
        {
            stockList[0][size - 6] = stock;
        }
        if(colour.equals("Black"))
        {
            stockList[1][size-6] = stock;
        }
    }
    


    public boolean validOptions(String productOptions) //checks to see if inputted options are valid
    {
        String[] arryofprod = productOptions.split(" "); //to get seperate the size of shoe and colour from the options string 
        size = Integer.parseInt(arryofprod[0]);
        colour = arryofprod[1]; 

        if ((colour.equalsIgnoreCase("Brown") || colour.equalsIgnoreCase("Black")) && ((size == 6) || (size == 7) || (size == 8) || (size == 9) || (size == 10)))
      {
        return true;
      }
      else
      {
        return false;
      }
    }


    public int getStockCount(String productOptions) //returns stock count of a particualr shoe
	{
        String[] arryofprod = productOptions.split(" ");
        size = Integer.parseInt(arryofprod[0]);
        colour = arryofprod[1];

        if(colour.equalsIgnoreCase("Brown"))
        {
            if(size == 6)
            {
                return stockList[0][size-6];
            }
            else if(size == 7)
            {
                return stockList[0][size-6];
            }
            else if(size == 8)
            {
                return stockList[0][size-6];
            }
            else if(size == 9)
            {
                return stockList[0][size-6];
            }
            else if(size == 10)
            {
                return stockList[0][size-6];
            }
            else
            {
                return 0;
            } 
            
        }
        else if(colour.equalsIgnoreCase("Black"))
        {
            if(size == 6)
            {
                return stockList[1][size-6];
            }
            else if(size == 7)
            {
                return stockList[1][size-6];
            }
            else if(size == 8)
            {
                return stockList[1][size-6];
            }
            else if(size == 9)
            {
                return stockList[1][size-6];
            }
            else if(size == 10)
            {
                return stockList[1][size-6];
            }
            else
            {
                return 0;
            } 
        }
        else
        {
            return 0;
        }
	}

    public void setStockCount(int stockCount, String productOptions) //sets the stock count of each shoe
	{
        String[] arryofprod = productOptions.split(" ");
        size = Integer.parseInt(arryofprod[0]);
        colour = arryofprod[1];
        
        if(colour.equalsIgnoreCase("Brown"))
        {
            if(size == 6)
            {
                stockList[0][size-6] = stockCount;
            }
            else if(size == 7)
            {
                stockList[0][size-6] = stockCount;
            }
            else if(size == 8)
            {
                stockList[0][size-6] = stockCount;
            }
            else if(size == 9)
            {
                stockList[0][size-6] = stockCount;
            }
            else if(size == 10)
            {
                stockList[0][size-6] = stockCount;
            }
        }
        else if(colour.equalsIgnoreCase("Black"))
        {
            if(size == 6)
            {
                stockList[1][size-6] = stockCount;
            }
            else if(size == 7)
            {
                stockList[1][size-6] = stockCount;
            }
            else if(size == 8)
            {
                stockList[1][size-6] = stockCount;
            }
            else if(size == 9)
            {
                stockList[1][size-6] = stockCount;
            }
            else if(size == 10)
            {
                stockList[1][size-6] = stockCount;
            } 
        }
	}

    public void reduceStockCount(String productOptions) //reduces stock when a shoe is ordered
	{
        String[] arryofprod = productOptions.split(" ");
        size = Integer.parseInt(arryofprod[0]);
        colour = arryofprod[1];

        if(colour.equals("Brown"))
        {
            stockList[0][size - 6]--;
        }
        else if(colour.equals("Black"))
        {
            stockList[1][size - 6]--;
        }
	}

    public void print() //print to make it look preety
    {
      super.print();
      System.out.print(" Colour: " + colour);
      System.out.print(" Size: " + size);
    }
}
