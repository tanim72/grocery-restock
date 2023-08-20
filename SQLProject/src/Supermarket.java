import java.util.*; //arraylist import

import java.io.FileNotFoundException; //exception handling imports
import java.io.FileReader;

import java.sql.Connection;  //sql connection import
import java.sql.DriverManager;  
import java.sql.SQLException; 
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Supermarket {
    private Heaps<Product> trial; //initalize variables for the market stock arraylist/heap, the restock queue, and the SQL connections (respecitvely)
    private Product currDate;
    private ArrayList<Product> currStock;
    private Queue<Product> restockItems = new LinkedList<Product>();
    private Connection conn;
    private Connection conn1;

    public Supermarket(String fileName) { //constructor takes in file name
        trial = new Heaps<Product>(); //make a new heap of type product

        Scanner sc = null;
        try{
            String url = "jdbc:sqlite:/Users/tanishamehta/Documents/SQLProject/stock.db"; //establish location/url of sql connection
            String url2 = "jdbc:sqlite:/Users/tanishamehta/Documents/SQLProject/restock.db";
            // create a connection to the database  
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);  //define the connection (try to get connection)
            conn1 = DriverManager.getConnection(url2);     
            System.out.println("Connection to SQLite has been established.");  //check to see if connection has been established
            try{
                deleteTable(conn1, "Restock"); // delete table to clear data (not going to append, making new table every time)
                deleteTable(conn, "Stock");
            } 
            catch(Exception ignored){
            }
            sc=new Scanner(new FileReader(fileName)); //read through file
            currStock = new ArrayList<Product>();
            currDate = new Product("date", sc.nextLine()); //the current date is the first entry in the file
            while(sc.hasNextLine()) { //while we have next line
                String[] splitLine = sc.nextLine().split(",");
                Product temp = new Product(splitLine[0],splitLine[1]); //split each line (csv style), first entry is product name, second entry is expiry date
                currStock.add(temp); //add to the array list

            }
            currStock = trial.sortUsingHeaps(currStock); //heapify to put in order
            createTable(conn, "Stock"); //create your tables
            createTable(conn1, "Restock"); 
            for(Product c : currStock) {
                insertItem(conn, c.getItemName(), c.getExpiryDate(), "Stock"); //add everything in order from arraylist to your database
            }
            System.out.println("");
            System.out.println("Displaying Database");
            displayDatabase(conn, "Stock");   //display database to check it has been added

        }
        catch(FileNotFoundException e) { //exception handling file
            System.out.println("error: no file found");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e) {  //SQL exception handling
            System.out.println(e.getMessage());  
        }


    }

    public void closeConnection() { //close the connection to close the data base
        try {
            displayDatabase(conn, "Stock");  //first display the final database
            displayDatabase(conn1, "Restock"); 
        }
        catch (SQLException e) {  //SQL error handling
            System.out.println(e.getMessage());  
        }
        finally {  //after try catch, close both the connections for stock and restock
            try {  
                if (conn != null)  
                    conn.close();  
                if(conn1!=null)
                    conn1.close();
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }
    }

    private static void displayDatabase(Connection connection, String tableName)throws SQLException{ //SQL method to display database
        String selectSQL = "SELECT * FROM "+tableName; //find table
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("--------- "+ tableName + " ----------");
        while(rs.next()){ //for every line, display the entry as a string
            System.out.print("Item: "+rs.getString("Product")+",");
            System.out.println(rs.getString("Expiry"));
        }
        System.out.println("--------------------------------------");
    }

    private static void insertItem(Connection connection, String Product, String Expiry, String tableName) throws SQLException{ //SQL method to insert item
        String insertSQL = "INSERT INTO " + tableName + "(Product, Expiry) VALUES (?,?)"; //insert for values product and expiry to be defined later
        PreparedStatement pstmt = connection.prepareStatement(insertSQL);
        pstmt.setString(1,Product); //the product name you want to insert
        pstmt.setString(2,Expiry); //expiry date you want to insert
        pstmt.executeUpdate(); //insert properly

    }

    private static void deleteItem(Connection connection, String Product, String Expiry, String tableName) throws SQLException{//SQL method to delete entry
        String deleteSQL = "DELETE FROM "+ tableName+" WHERE Product = ? AND Expiry = ?"; //delete for values product and expiry to be defined later
        PreparedStatement pstmt = connection.prepareStatement(deleteSQL);
        pstmt.setString(1,Product); //the product name you want to delete
        pstmt.setString(2,Expiry); //expiry date you want to delete

        pstmt.executeUpdate(); //delete properly
    }

    private static void createTable(Connection connection, String tableName) throws SQLException{ //create your SQL database tables
        String createTablesql = ""+ "CREATE TABLE " + tableName +  " ( "+  //name of tables
                                "Product varchar(255), "+ //column name 1
                                "Expiry varchar(255)"+ "); "+""; // column name 2
        Statement stmt = connection.createStatement();
        stmt.execute(createTablesql); //create table
    }

    private static void deleteTable(Connection connection, String tableName) throws SQLException{ //delete entire table SQL
        String deleteTableSQL = "DROP TABLE " + tableName; //name of table you want to delete
        Statement stmt = connection.createStatement();
        stmt.execute(deleteTableSQL); //delete table
    }


    public void removeItem(Product itemToBeRemoved) { //removes an item from the market stock
        restockItems.add(itemToBeRemoved); //adds it to restock pile
        currStock.remove(itemToBeRemoved);
        
        try {
            deleteItem(conn, itemToBeRemoved.getItemName(), itemToBeRemoved.getExpiryDate(), "Stock"); //updates SQL databases
            insertItem(conn1, itemToBeRemoved.getItemName(), itemToBeRemoved.getExpiryDate(), "Restock");
        }
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
    }

    public void addItem(Product itemToBeAdded) { //adds an item to the market stock
        currStock.add(itemToBeAdded);
        System.out.println(itemToBeAdded.getExpiryDate());
        currStock = trial.sortUsingHeaps(currStock); //heapify (put in order)
        try {
            insertItem(conn, itemToBeAdded.getItemName(), itemToBeAdded.getExpiryDate(),"Stock"); //updates SQL database
        }
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        
        
    }

    public void restockAllItems(String expDate) { //removes all items from restock, and adds them back to market stock with new expiry date
        while(restockItems!=null) {
            restockItem(expDate); //updates SQL database
        }
        
    }

    public void restockItem(String expDate) { //removes first expired item from restock pile
        if(restockItems!=null) {
            
            Product itemToBeAdded = restockItems.remove();
            String oldDate = itemToBeAdded.getExpiryDate(); 
            updateDate(); //updates the date of the item
            itemToBeAdded.setDate(expDate);
            currStock.add(itemToBeAdded); //adds back to market stock with new date
            try {
                deleteItem(conn1, itemToBeAdded.getItemName(), oldDate, "Restock"); //updates SQL database
                insertItem(conn, itemToBeAdded.getItemName(), itemToBeAdded.getExpiryDate(), "Stock");
                
            }
            catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }
        }
        
    }

    public ArrayList<Product> getCurrStock() { //returns array list for market stock
        return currStock;
    }

    public Product getCurrDate() { //returns current date
        return currDate;
    }

    public void updateStack() { //if next day is pressed
        updateDate(); //update the current date
        while(currStock.get(0).compareTo(currDate) > 0) { //while our market stock has expired items
            Product temp = currStock.remove(0); //remove them from market stock
            restockItems.add(temp); //add to restock list/queue
            try {
                deleteItem(conn, temp.getItemName(), temp.getExpiryDate(), "Stock"); //update SQL databases
                insertItem(conn1, temp.getItemName(), temp.getExpiryDate(), "Restock");
            }
            catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }
        }

    }

    public void updateDate() { //update the date, if an impossible date is added (similar to product class)
        int day = currDate.getDay();
        int month = currDate.getMonth();
        int year = currDate.getYear();
        day++;
        if(month ==2) {
            if(day>28) {
                if(year%4!=0) {
                    day = 1;
                    month++; //incrememnt month once day exceeds max day
                    if(month>12) {
                        month =1;
                        year++;
                     } //increment year once month exceeds max day for month
                }
                else if(day>29) {
                    day = 1;
                    month++; //increment month once day exceeds max day for month
                    if(month>12) {
                        month =1;
                        year++;
                     } //increment year once month exceeds max day for month
                }
            }
        }
        else if(month ==1 || month ==3 || month ==5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if(day>31) {
                day = 1; 
                month++; //increment month once day exceeds max day for month
                if(month>12){
                    month =1;
                    year++;
                 } //increment year once month exceeds max day for month
            }
        }
        else {
            if(day>30) {
                day = 1;
                month++; //increment month once day exceeds max day for month
                if(month>12) {
                    month =1;
                    year++;
                 } //increment year once month exceeds max day for month
            }
        }

        currDate.setDate(day+"/"+month+ "/"+year); //set the new date string
        }

    public Queue<Product> getQueue() { //return restock item queue
        return restockItems;
    }
    
}
