/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalbroker;

/**
 *
 * @author yohman
 */
public class Car {
    
    private String make;
    private int winterPrice;
    private boolean winterAvailability;
    private int summerPrice;
    private boolean summerAvailability;
    
    public Car() {
        
        setMake("Car");
        setWinterPrice(50);
        //setWinterAvailability(true);
        setSummerPrice(100);
        //setSummerAvailability(false);
    }
    
    public Car(String make, int winterPrice, /*boolean winterAvailability,*/ int summerPrice/*, boolean summerAvailability*/) {
        
        this.make = make;
        this.winterPrice = winterPrice;
        //this.winterAvailability = winterAvailability;
        this.summerPrice = summerPrice;
        //this.summerAvailability = summerAvailability;
    }

    
    public String getMake() {
        return make;
    }

    public int getWinterPrice() {
        return winterPrice;
    }
    
//    public boolean isWinterAvailability() {
//        return winterAvailability;
//    }

    public int getSummerPrice() {
        return summerPrice;
    }

//    public boolean isSummerAvailability() {
//        return summerAvailability;
//    }

    
    public void setMake(String make) {
        this.make = make;
    }

    public void setWinterPrice(int winterPrice) {
        this.winterPrice = winterPrice;
    }
    
//    public void setWinterAvailability(boolean winterAvailability) {
//        this.winterAvailability = winterAvailability;
//    }

    public void setSummerPrice(int summerPrice) {
        this.summerPrice = summerPrice;
    }
    
//    public void setSummerAvailability(boolean summerAvailability) {
//        this.summerAvailability = summerAvailability;
//    }

   
    
}
