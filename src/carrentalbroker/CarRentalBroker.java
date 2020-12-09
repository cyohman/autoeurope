/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalbroker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author yohman
 */
public class CarRentalBroker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        //REMOVE 2018.02.01, cey, Printing out the value of the filename
        //System.out.println(args[0]);
        
        String data;
        
        //2018.02.01, cey, Reading in cars.txt flat file DB
        //File Format - Collections of
        //Record
        //IATA Code of Airport Location - 3 letters, ALL CAPS
        //Rows of comma-delimited lists - Car,Winter Price,Summer Price
        //Assume next record begins when a line contains no commas
        //I presume this is self-evident. See cars.txt. Happy to explain in detail. :)
        //In an ideal world this would wrapped in XML/JSON/etc or come from a web service
        
        //2018.02.01, cey, Store everything in a hashtable of locations.
        //Key - IATA code
        //Value - Location object -> Explained in Location.Java
        
        try(FileReader fileReader = new FileReader(args[0]))
        {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Hashtable<String, Location> locations = new Hashtable<String, Location>();
            
            String anIataCode="";
            Location aLocation = null;
            Car aCar;
            
            data = bufferedReader.readLine();
            
            while(data!=null)
            {
                //2018.02.01, cey, IATA code, length 3, all upper case - start of location
                if(data.length()==3 && data.toUpperCase().equals(data))
                {
                    //2018.02.01, cey, New IATA code, time for a new location, instantiated when we start adding cars, & to add 
                    //the previous locatio to the hash
                    
                    if(aLocation!=null)
                    {
                        
                        locations.put(anIataCode, aLocation);
                        aLocation=null;
                        
                    }
                    
                    anIataCode = data;
                    
                    //2018.02.01,cey, \n for spacing purposes
                    //System.out.println("\nLocation: "+data);
                    
                }
                else //2018.02.01, cey, Assume if not a location it's a Car, Winter Price, Summer Price. 
                     //Assumed for speeding up implementation
                {
                    
                    String []record = data.split(",");
                    aCar = new Car(record[0], Integer.parseInt(record[1]), /*Boolean.parseBoolean(record[2]),*/ Integer.parseInt(record[2])/*, Boolean.parseBoolean(record[4])*/);
                    
                    //2018.02.01, cey, Check if this is the 1st car for the location
                    if(aLocation==null)
                        aLocation = new Location();
                    
                    aLocation.addCar(aCar);
                    //System.out.println("Car: " +  record[0]);
                    //System.out.println("Winter Price: " +  record[1]);
                    //System.out.println("Summer Price: " +  record[2]);
                    
                }
                      
                data = bufferedReader.readLine();
                
            }
            
            //2018.02.01, cey, Add orphan location due to the file read op. There ends up being an extra one that just needs to
            // be stuffed-in
            locations.put(anIataCode, aLocation);
            
            System.out.println("Data read in.");
            
            //2018.02.01, cey, Enter IATA code for location. e.g. CDG - Charles De Gaulle, LHR - Heathrow, NRT - Narita, BOS - Logan
            //Assume input is good & no error checking is needed. This is where a web service would come in handy.
            System.out.println("Enter IATA code for Rental Location");
            
            Scanner scanner = new Scanner(System.in);
            String iataCodeInput = scanner.nextLine();
            System.out.println("IATA: " + iataCodeInput + "\n");
            
            //2018.02.01, cey, Retrieve location data for rental location
            Location userSelectedIATALocation = locations.get(iataCodeInput);
            
            System.out.println("Location data retrieved.");
            
            //2018.02.01, cey, Enter data in MMDDYYYY format. Assume the input is a valid date
            System.out.println("Enter data in MMDDYYYY format. e.g. February 1, 2018 is 02012018");
            String userSelectedDateString = scanner.nextLine();
            
            System.out.println("Date: " + userSelectedDateString + "\n");
            //2018.02.01, cey, Would rather use Gregorian Calendar & date checking associated with that object but going to use simple date checking. Can do 
            //Gregorian Calendar implementation upon request.
            //Assume Winter is from October (10) thru March (3). Assume summer is from April (4) thru September (9).
             int month = Integer.parseInt(userSelectedDateString.substring(0, 2));
             
             //2018.02.01, cey, Assume winter
             boolean peakSeason = false;
             
             //2018.02.01, cey, Unless the month establishes otherwise
             if(month >=4  && month <= 9)
                peakSeason = true;
             
             Car bestPriceCar = userSelectedIATALocation.sort(peakSeason);
             int price = (peakSeason) ? bestPriceCar.getSummerPrice() : bestPriceCar.getWinterPrice();
             System.out.println("The best price car for " + iataCodeInput + " on " + userSelectedDateString + " is " + bestPriceCar.getMake()
                                + " for " + price);
                     
                     
             
             
        }
        catch(Exception e)
        {
         
            System.out.println("Could not read file from: "+ args[0]);
            
        }        
    }
    
}
