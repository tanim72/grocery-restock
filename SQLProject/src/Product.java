

public class Product implements Comparable<Product> { //implement comparable interface
    private String productName; //initalize all the components of a product (name & expiry date)
    private String expiryDate;
    private int day;
    private int month;
    private int year;


    public Product(String productName, String expiryDate) { //constructor
        this.productName = productName; //set name and expiry date
        this.expiryDate = expiryDate;

        String[] dates = expiryDate.split("/");
        day = Integer.parseInt(dates[0]);
        month = Integer.parseInt(dates[1]);
        year = Integer.parseInt(dates[2]); //determine the integer values of expiry date
        if(month>12) //check to see if expiry date is valid input... if month >12, set month back to 12
            month = 12;
        if(year>99)
            year = 99; //if year greater than 99, set year back to 99
        if(month ==2) { 
            if(day>28) { //if february and not a leap year and day >28, set day to 28
                if(year%4!=0) 
                    day = 28;
                else   
                    day = 29; //if february and  a leap year and day >29, set day to 29
            }
            
        }
        else if(month ==1 || month ==3 || month ==5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if(day>31)
                day = 31; //otherwise if #days in month is 31, and day >31, set day to 31
        }
        else {
            if(day>30)
                day = 30; //if #days in month is 30, and day >30, set day to 30

        }

        this.expiryDate = day+"/"+month+ "/"+year; //rewrite new expirydate
        //System.out.println(expiryDate);
            

    }
    public String toString() { //override toString() method
        return "item: " + productName + " expiry date: " + expiryDate; //display product name with expiry date
    }

    public int getDay() { //return expiry day as integer
        return day;
    }
    public int getMonth() { //return expiry month as integer
        return month;
    }
    public int getYear() { //return expiry year as integer
        return year;
    }
    public void setDate(String date) { //if you are setting a new date, accept new expiry date input
        expiryDate = date; 
        String[] dates = expiryDate.split("/");
        day = Integer.parseInt(dates[0]);
        month = Integer.parseInt(dates[1]);
        year = Integer.parseInt(dates[2]); //turn date into integer
        if(month>12)  //repeat what you did in constructor if date input is extreme input
            month = 12;
        if(year>99)
            year = 99;
        if(month ==2) {
            if(day>28) {
                if(year%4!=0) 
                    day = 28;
                else   
                    day = 29;
            }
            
        }
        else if(month ==1 || month ==3 || month ==5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if(day>31)
                day = 31;
        }
        else {
            if(day>30)
                day = 30;

        }

        this.expiryDate = day+"/"+month+ "/"+year; //again, set new expiry date
    }

    public String getItemName() {
        return productName; //return product name
    }
    public String getExpiryDate() {
        return expiryDate; //return expiry date as string
    }

    public int compareTo(Product temp) { //compare which has a later expiry date
        if(this.year<temp.getYear())  //if year is different, check to see who has greater year
            return 1;
        if(this.year>temp.getYear())
            return -1;
        if(this.year==temp.getYear()) {
            if(this.month<temp.getMonth()) // if month is different, but year is same, check to see who has greater month
                return 1;
            if(this.month>temp.getMonth())
                return -1;
            if(this.month==temp.getMonth()){
                if(this.day<temp.getDay()) // if both month and year are same, check to see who has greater day
                    return 1;
                if(this.day>temp.getDay())
                    return -1;
                if(this.day==temp.getDay())
                    return 0;  //return 0 if same expiry date (then organize based on what was added first)        

            }
        }
        return 0;
    }
    public boolean equals(Product temp){
        return this.compareTo(temp) == 0; //return true if two Products have the same expiry date

    }
}
