
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int minIdx = from;
        for(int i = from+1;i<quakeData.size();i++){
            if(quakeData.get(i).getDepth() > quakeData.get(minIdx).getDepth()){
                minIdx = i;
            }
        }
        return minIdx;
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
        
        }
    }
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int j=0;
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkinSortedOrder(in)){ 
                j=i;
                break;
            }
        
        }
        System.out.println("Passes were needed: "+ (j+1));
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
       for(int i= 0;i<70;i++){
           int minIdx = getLargestDepth(in,i);
           QuakeEntry replace1= in.get(i);
           QuakeEntry replace2= in.get(minIdx);
           in.set(i,replace2);
           in.set(minIdx,replace1);
       } 
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,int numSorted){
        for(int i=0;i< quakeData.size()-numSorted-1;i++){
            if((quakeData.get(i)).getMagnitude() > (quakeData.get(i+1)).getMagnitude()){
                QuakeEntry replace1= quakeData.get(i);
                QuakeEntry replace2= quakeData.get(i+1);
                quakeData.set(i,replace2);
                quakeData.set(i+1,replace1);
                
            }
        }
        numSorted++;
    }
    public boolean checkinSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0;i<quakes.size()-1;i++){
            if((quakes.get(i)).getMagnitude() > (quakes.get(i+1)).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        System.out.println("read data for "+in.size()+"quakes");
        for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }
        for(int i=0;i<in.size();i++){
            onePassBubbleSort(in,i);
            System.out.println("Printing quakes after pass "+ i);
                for (QuakeEntry qe: in) { 
                    System.out.println(qe);
                }
        }
    
    }
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        System.out.println("read data for "+in.size()+"quakes");
        
        int i;
        for(i=0;i<in.size();i++){
            onePassBubbleSort(in,i);
            if(checkinSortedOrder(in)){break;}
         
        }
        System.out.println("Passes that were needed: "+ (i+1));
        }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list)
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
