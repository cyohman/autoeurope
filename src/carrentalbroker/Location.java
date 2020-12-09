/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalbroker;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author yohman
 */
public class Location {
    
    //2018.02.01, cey, member variables
    private ArrayList<Car> cars;
    
    public Location()
    {
        cars = new ArrayList<Car>();
    }
    
    public void addCar(Car car)
    {
        cars.add(car);
    }
    
    //2018.02.02, cey, false = winter, true = summer
    public Car sort(boolean peakSeason)
    {
        //2018.02.01, cey, Sort by summer pricing based on date
        if(peakSeason)
            Collections.sort(cars, (left, right) -> left.getSummerPrice() - right.getSummerPrice());
        else
            Collections.sort(cars, (left, right) -> left.getWinterPrice() - right.getWinterPrice());
        
        return cars.get(0);
    }
    
}
