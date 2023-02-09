V1

In the shoes class a 2d array stores the stock of brown shoes at index 0 and the stock of black shoes at index 1
The stock of size 6 is stored at index 0 in the inner array size 7 at index 1, size 8 at index 2 
size 9 at index 3, size 10 at index 4.

The bonus works it prints only the books written by the author entered in the order of release year (lowest to highest) it needs to be the exact lettering used as the name tho
I added year to the books and stored it using coparable in the Book.java file
I also added to the EcommerceUserInterface to support the booksbyauthor method




V2 ChangeLog

I fixed the assignment 1 bonus it now works with any form of name caps or coms

The assignment 2 bonus is functional (A single customer can review more than once though so they can potentially review bomb a product)

Changed the products storage method to use a map. The product.txt file is also used to make the products now. (fully functional)

If you would like to add a shoe follow the following template (It can only take one Colour and size at a time so multiple shoe products will have to be made for different colours and sizes)
{
    Catagory
    Name
    Price
    Stock
    Size:Colour
}
eg
{
    SHOES
    Super Awesome Shoe
    69.0
    13
    10:Black
}

A stat tracker was made using a map. Anytime a product is ordered it is incremented (fully functional)

Changed the commands Sortbyname and Sortbyprice to Printbyname and Printbyprice in EcommerceUserInterface. They now print the newly sorted arrays instead of just sorting (fully functional)

Added the commands Addtocart, Remcartitem, Printcart, Orderitems, Stats, Rate, Printratecategory, Printavgrate and Printbyrate (Fully functional)
{
    Addtocart: This allows customer to add a product to their cart. It asks the user for customer id, product id and if applicable product options. (Fully functional)

    Remcartitem: This allows the customer to remove a product they no longer want from their cart. It asks for product id and customer id. (Fully functional)

    Printcart: This prints all the products in a customers cart. It asks for the customer id. (Fully functional)

    Orderitems: This orders all the items in a customers cart. It asks for the customer id (Fully functional)

    Stats: This all the stats map in the order of most ordered product to least ordered. It asks for customer id. (Fully functional)

    Rate: This allows the user to rate a product they have orderd after it is shipped to them. It asks for a customer id, product id and a rating. (Fully functional)

    Printratecategory: This allows the user to pick a catagory of product and print the products above an entered rating. It asks for catagory and rate. (Fully functional)

    Printavgrate: This prints the average rating for all products. (Fully Functional)

    Printbyrate: This prints the amount of times all the products have recieved an inputed rating. It asks for a rating. (Fully functional)
}

Replaced the generated error message string with custom exceptions (Fully functional)

Implemented the UnknownCustomerException, UnknownProductException, InvalidChoiceException and ProductOutofStockException. (Fully functional)
{
    UnknownCustomerException: This triggers when the user inputs a customer id that does not exist (Full functional)

    UnknownProductException: This triggers when the user inputs a product id that does not exist (Fully functional)

    InvalidChoiceException: This triggers when the user inputs an invali option (Fully functional)
    
    ProductOutofStockException: This triggers when the user tries to order or add to cart a product that is out of stock (Fully functional)
}

EcommerceUserInterface now catches all exceptions (Fully functional)

Added two new Classes Cart and CartItem (Fully functional)
{
    Cart: This is the arraylist of cartitems that a customer has put in their cart. This is unique to each customer (Fully functional)

    CartItem: This is a Product object with its product options linked to it. (Fully functional)
}

Many other methods were added to make the code easy to read and more reuseable (See code in EcommerceSystem for brief descriptions of added methods)


