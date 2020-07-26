  

import java.util.*;
import java.math.*;
import java.io.*;
class Bank extends StockInvetory
{
    //This is the bank class which inherits the stock class and its properties
    //The bank is basically the collection of stocks and their attributes
        Bank(){
            super();
            this.StockInventory();
            System.out.println("---------------------------------------------");
            System.out.println("      WELCOME TO THE KALUPUR STOCK BANK");
            System.out.println("---------------------------------------------");
         
           
    }




     void show(){
      System.out.println("                    ***********************");
      System.out.println("                    *                     *");
      System.out.println("                    * KALUPUR STOCK BANK  * ");
       System.out.println("                    *                     *");
      System.out.println("                    ***********************\n");
      System.out.println("NO  NAME            PRICE        RATING        SHARE");
      System.out.println("_____________________________________________________");
      for(int i=0;i<10;i++){
       
           String str=Integer.toString(i)+"   ";

           int len;

           str+=this.stocks[i].companyName;
           len=str.length();
           str=str+" ".repeat((20-len));

           str+=this.stocks[i].price;
           len=str.length();
           str=str+" ".repeat(33-len);

           str+=this.stocks[i].stockRating;
           len=str.length();
           str=str+" ".repeat(47-len);

           str+=this.stocks[i].numberOfShares;

           System.out.println(str);
        System.out.println("_____________________________________________________");
       

      }
    }
}

class Holder extends StockInvetory{

                   
                 
    String name;
    int haveStock;
    Holder(String s)
    {           super();
        haveStock=0;
        this.name=s;
        this.emptyTheInventory();

    }
    void buy(int stockno,int quantity,Bank obj){
        if(stockno<10&&stockno>=0&&quantity>0){
            if(obj.stocks[stockno].getNumberOfShares()>=quantity){
              haveStock=1;
                obj.stocks[stockno].updateShare(-quantity);
                this.stocks[stockno].updateShare(quantity);
                System.out.println("The total price you pay is "+(obj.stocks[stockno].price)*quantity+"");
            }
            else{
                System.out.println("######         ERROR        ########");
                System.out.println("bank has only "+obj.stocks[stockno].getNumberOfShares()+" number of "+stocks[stockno].getCompanyName()+"Shares");
            }

        }
        else{
          if(quantity<=0){
              System.out.println("######         ERROR        ########");
              System.out.println("Quantity should be positive");

            }
          if (stockno>10||stockno<0){
             System.out.println("######         ERROR        ########");
       
             System.out.println("THE STOCK  NUMBER IS INAPPROPRIATE");
            }
        }
    }


    void sell(int stockno,int quantity,Bank obj){
     
        if(stockno<10&&stockno>=0&&quantity>0){
            if(this.stocks[stockno].getNumberOfShares()>=quantity){
                obj.stocks[stockno].updateShare(quantity);
                this.stocks[stockno].updateShare(-quantity);
                System.out.println("The total price you get is "+(obj.stocks[stockno].price)*quantity+"");
            }
            else{
                System.out.println("######         ERROR        ########");
                System.out.println("you have only "+this.stocks[stockno].getNumberOfShares()+" number of "+stocks[stockno].getCompanyName()+"Shares");
            }

        }
        else{
            if(quantity<=0){
              System.out.println("######         ERROR        ########");
              System.out.println("Quantity should be positive");

            }
            if(stockno>10||stockno<0){
             System.out.println("######         ERROR        ########");
                      System.out.println("THE STOCK  NUMBER IS INAPPROPRIATE");
            }
            }
        }
   

    void show() {

                    try{

                      //FileWriter file=new FileWriter("C:/Users/hekka/Desktop/Bill.txt");
                    //BufferedWriter writer=new BufferedWriter(file);
                   
      System.out.println("\n----------------- stocks of Mr."+this.name+" -----------------\n");
     
      String temp1="\n----------------- stocks of Mr."+this.name+" -----------------\n";
      //writer.write(temp1);
      System.out.println("    NAME            PRICE        RATING        SHARE");
      String temp2="    NAME            PRICE        RATING        SHARE";
      temp2=temp1+temp2;
      temp2+="\n";
      //writer.write(temp2+"\n");
     // writer.newLine();
      System.out.println("_____________________________________________________");
      temp2+="_____________________________________________________"+"\n";
      //writer.write("_____________________________________________________\n");

      //writer.newLine();
      for(int i=0;i<10;i++){
       
          //writer.write("\nAarsh\n");  
        if(this.stocks[i].numberOfShares!=0){
           String str="    ";

           int len;
           //writer.write("Arham");
           str+=this.stocks[i].companyName;
           len=str.length();
           str=str+" ".repeat((20-len));

           str+=this.stocks[i].price;
           len=str.length();
           str=str+" ".repeat(33-len);

           str+=this.stocks[i].stockRating;
           len=str.length();
           str=str+" ".repeat(47-len);

           str+=this.stocks[i].numberOfShares;

           System.out.println(str);
           temp2+=str+"\n";
          // writer.write(str+"\n");
           //writer.newLine();
        System.out.println("_____________________________________________________");
           // writer.write("_____________________________________________________\n");
        temp2+="_____________________________________________________\n";
           // writer.newLine();
          /*for(int ii=0;ii<10;ii++)
            writer.write(ii);
        }
        writer.flush();
        writer.close();*/
                //file.close();
      }
     
         
      }

       FileWriter ff=new FileWriter("./Bill.txt",true);
      BufferedWriter bb=new BufferedWriter(ff);
         PrintWriter writer=new PrintWriter(bb);
         writer.write(temp2);
         writer.close();


      Runtime runtime = Runtime.getRuntime() ;
         Process process=runtime.exec("C:/Windows/notepad.exe ./Bill.txt");
      }
      catch(Exception e)
      {
          System.out.println(e);
      }

    }
     


                 
}


class Year{
    private int year;
    Year(){
        year=1;
    }
    Year(int y){
        year=y;
    }
    int getYear(){
        return this.year;
    }

    void nextYear(Holder H,Bank B){
        year+=1;
        System.out.println("_____________________________________________________");

        System.out.println(" \n\t \t HAPPPY NEW YEAR      ");
System.out.println("_____________________________________________________");
       
        System.out.println("THE NEW YEAR HAS BEGUN,PLEASE CHECK THE LATEST PRICE OF THE STOCKS");

        for(int i=0;i<10;i++){
            int price_factor=(60-B.stocks[i].getNumberOfShares());
            if(price_factor>0)
            {
                 B.stocks[i].price=(int)(B.stocks[i].price*1.1);
           
       
                H.stocks[i].price=(int)(H.stocks[i].price*1.1);
            }
            else
                            {
                 B.stocks[i].price=(int)(B.stocks[i].price*0.9);
           
       
                H.stocks[i].price=(int)(H.stocks[i].price*0.9);
            }




        }


    }
}

  class Stock {


        //The stock class basically sets the type of stock
    //Information of companyname,stockrating,price, and no of shares


     String companyName;
     String stockRating;
     int price;
    int numberOfShares;

     String getCompanyName()
    {
        return companyName;
    }

     String  getStockRating()
    {
        return stockRating;
    }

     int getPrice()
    {
        return price;
    }

     int getNumberOfShares()  
    {
        return numberOfShares;
    }

    void updateShare(int q){
        this.numberOfShares+=q;
    }



     Stock(String companyName, String stockRating, int price, int numberOfShares)
    {
        super();
        this.companyName = companyName;
        this.stockRating = stockRating;
        this.price = price;
        this.numberOfShares = numberOfShares;
    }

    void showStocks(){
        //A method to show the desired information of a particular stock



        System.out.println("THE NAME OF THE STOCK IS "+this.getCompanyName());

        System.out.println("THE rating OF THE STOCK IS "+this.getStockRating());
        System.out.println("THE price OF THE STOCK IS "+this.getPrice());
        System.out.println("THE quantity OF THE STOCK IS "+this.getNumberOfShares());

    }
}

 class StockInvetory
 {

//Collection of stocks and basically a stock list

    Stock [] stocks;

     StockInvetory()
    {
        stocks = new Stock[10];


    }

void emptyTheInventory()
{
           this.StockInventory();
           int i;
           for(i=0;i<10;i++){
                stocks[i].numberOfShares=0;
           }


}


      void StockInventory() {
    //Just a instance of the method showStocks
           
             /*Scanner console = new Scanner(System.in);

        System.out.println ("Stock's name:");
        String stockName = console.nextLine();

        System.out.println ("Stock's rating");
        String stockRating= console.nextLine();

        System.out.println ("Stock's price:");
        int stockPrice = console.nextInt();

        System.out.println ("Numbers of shares: ");
        int numberShares= console.nextInt();*/
              int i=0;

              stocks[i]= new Stock(("KOTAK_BANK"), ("A+"),2000 , 100);
              i++;
              stocks[i]= new Stock(("RELIANCE"), ("B"), 1400, 100);
              i++;
              stocks[i]= new Stock(("HDFC"), ("A"), 600, 100);
              i++;
              stocks[i]= new Stock(("TATA"), ("C+"), 700, 100);
              i++;
              stocks[i]= new Stock(("INFOSYS"), ("A++"), 1000, 100);
              i++;
              stocks[i]= new Stock(("WOCKHARDT"), ("C+"), 1900, 100);
              i++;
              stocks[i]= new Stock(("WIPRO"), ("B"), 400, 100);
              i++;
              stocks[i]= new Stock(("MRF TYRES"), ("B+"), 1500, 100);
              i++;
              stocks[i]= new Stock(("ASIAN_PAINTS"), ("A"), 1700, 100);
              i++;
              stocks[i]= new Stock(("ONGC"), ("D"), 1400, 100);


        }



   
void Ss(int d)
{
//Shows the information of a particular stock using showstocks method
stocks[d].showStocks();

}

  }




  class B{
          public static int GetInt(Scanner scan){
            while(true){
              try{
                return scan.nextInt();
              }
              catch(InputMismatchException e){
                scan.next();
                System.out.println("######         ERROR        ########");
                System.out.println("please enter an integer try again:");
              }
            }
          }
           
           
            //The main method which will basically provdie the front i.e The Menu driven programme to the customer
            public static void main(String[] args)
            {      
                    int  k=0;
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("                  WELCOME USER\n\nKINDLY ENTER YOUR NAME");
                     Scanner scan=new Scanner(System.in);
                     String namu=scan.nextLine();
                    Bank ban=new Bank();
                    Holder H=new Holder(namu);
                   
                    Year yt=new Year();
                    System.out.println("THE BANK HAS THE FOLLOWING STOCKS\n");
                    ban.show();
                     int x=0;
                   




                   while(x!=5){
                   
                    if(k%5==0&&k!=0)
                    {
                        yt.nextYear(H,ban);
                    }
                    k++;
                     System.out.println("ENTER\n1.VIEW BANK STOCKS\n2.VIEW MY STOCKS\n3.BUY MY STOCKS\n4.SELL MY STOCKS\n5.EXIT FROM THE COMPANY");
                   
                    x=GetInt(scan);
                   
                     while(x>5||x<1){
                      System.out.println("######         ERROR        ########");
                      System.out.println("Please enter correct input ");
                       x=GetInt(scan);

                     }
                     
                    switch(x)
                    {
                        case 1:
                        ban.show();
                        System.out.println("ENTER\n1 FOR PREVIOUS MENU");
                        int j=GetInt(scan);
                        while(j!=1)
                        {
                          System.out.println("######         ERROR        ########");
                          System.out.println("          PLEASE ENTER 1");
                          j=GetInt(scan);
                        }
                        System.out.println("\n");
                        break;

                        case 2:
                        H.show();
                        System.out.println("ENTER\n1 FOR PREVIOUS MENU");
                        j=GetInt(scan);
                        while(j!=1)
                        {
                          System.out.println("######         ERROR        ########");
                          System.out.println("          PLEASE ENTER 1");
                          j=GetInt(scan);
                        }
                                                System.out.println("\n");
                        break;


                        case 3:
                        int z;
                        int q;
                       
                        System.out.println("Please Enter the stock number ");
                        z=GetInt(scan);
                         System.out.println("PLEASE ENTER THE QUANTITY");
                        q=GetInt(scan);

                        H.buy(z,q,ban);
                        System.out.println("ENTER\n1 FOR PREVIOUS MENU");
                        j=GetInt(scan);
                        while(j!=1)
                        {
                          System.out.println("######         ERROR        ########");
                          System.out.println("          PLEASE ENTER 1");
                          j=GetInt(scan);
                        }
                                                System.out.println("\n");
                        break;
                     
                          case 4:
                        int zz;
                        int qq;
                        if(H.haveStock==0)
                        {
                          System.out.println("YOU DON'T HAVE ANY STOCKS");
                          break;
                        }
                        System.out.println("Please Enter the stock number ");
                        zz=GetInt(scan);

                        System.out.println("PLEASE ENTER THE QUANTITY");
                        qq=GetInt(scan);
                        H.sell(zz,qq,ban);
                        System.out.println("ENTER\n1 FOR PREVIOUS MENU");
                        j=GetInt(scan);
                        while(j!=1)
                        {
                          System.out.println("######         ERROR        ########");
                          System.out.println("          PLEASE ENTER 1");
                          j=GetInt(scan);
                        }
                                                System.out.println("\n");
                        break;
                     
                        default:
                        System.out.println("PLEASE ENTER THE PROPER INTEGER");
                  }

                }

                System.out.println("_____________________________________________________");
                System.out.println("THANK YOU FOR DOING BUSINESS WITH US :D");
                System.out.println("    made by                            ");
                System.out.println("  =>AARSH SHAH                    ");
                System.out.println("  =>ARHAM DOSHI                    ");

                   
                   
            }  
        }

    
