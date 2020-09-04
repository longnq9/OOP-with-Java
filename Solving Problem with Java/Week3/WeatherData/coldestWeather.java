
/**
 * Write a description of codestWeather here.
 * 
 * @author (LongNQ9) 
 * @version (2/9/2020 8:24PM)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.lang.*;
import java.text.*;

public class coldestWeather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser){
            if (coldestSoFar == null){
                coldestSoFar = currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }
            }
        }        
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature(){

        String filename = "";
        CSVRecord coldestRecord = null;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);        
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());                                   
            
             
            
            if (coldestRecord == null){
                coldestRecord = currentRecord;
                filename = f.getAbsolutePath();
            }
            else
            {
                double recordTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(currentRecord.get("TemperatureF"));  
                if (recordTemp < coldestTemp){
                    coldestRecord = currentRecord;
                    filename = f.getAbsolutePath();                    
                }
            }                        
        }
        return filename;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        
        for(CSVRecord currentRecord : parser){
            if(currentRecord.get("Humidity").equals("N/A")){
                continue;
            }
            lowestHumidity = getLowestofTwo(currentRecord,lowestHumidity);
        }        
        return lowestHumidity;        
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentLowest = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidity = getLowestofTwo(currentLowest,lowestHumidity);
        }
        return lowestHumidity;
    }
    
    public CSVRecord getLowestofTwo(CSVRecord currentLowest, CSVRecord lowestHumidity){
        if (lowestHumidity == null){
                lowestHumidity = currentLowest;                
            }
            else
            {
                double currentHumidityTemp = Double.parseDouble(currentLowest.get("Humidity"));
                double lowestHumidityTemp = Double.parseDouble(lowestHumidity.get("Humidity"));
                if (currentHumidityTemp < lowestHumidityTemp){
                    lowestHumidity = currentLowest;
                }                
            }
        return lowestHumidity;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double averageTemp = 0.0;
        double sum = 0.0;
        int count = 0;
                
        CSVRecord record = null;
        
        for(CSVRecord currentRecord : parser){
            double averageRecord = Double.parseDouble(currentRecord.get("TemperatureF"));
            if (averageRecord == -9999)
            {
                return averageTemp;
            }
            else            
            {
                sum += averageRecord;
                count++;
            }
        }            
        return averageTemp = sum / count;       
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value){
        
        double averageTemp = 0.0;
        double sum = 0;
        int count = 0;
        
        for(CSVRecord currentRecord : parser){
            double averageRecord = Double.parseDouble(currentRecord.get("TemperatureF"));
            double humidity = Double.parseDouble(currentRecord.get("Humidity"));
            if (humidity > value)
            {
                sum += averageRecord;
                count++;
            }
        }   
        return averageTemp = sum /(double) count;
    } 
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();        
        double averageTemp = averageTemperatureInFile(fr.getCSVParser());        
        //DecimalFormat df = new DecimalFormat("###.##");
        if (averageTemp != 0){
            //System.out.println("The averager temperature is " + df.format(averageTemp));
            System.out.println("The averager temperature is " + averageTemp);
        }
        else
        {
            //System.out.println("No temperatures with that humidity" + df.format(averageTemp));
            System.out.println("No temperatures with that humidity" + averageTemp);
        }
       
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();        
        double averageTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80.0);      
        //DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("The averager temperature is " + averageTemp);
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("The lowest humidity is " + lowestHumidity.get("Humidity") 
                            + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public void testLowestHumidity(){
        FileResource fr = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("The lowest humidity is " + lowestHumidity.get("Humidity") 
                            + " at " + lowestHumidity.get("DateUTC"));
    }
   
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest weather is " + coldest.get("TemperatureF") 
                            + " at " + coldest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String fn = fileWithColdestTemperature();
        File apath = new File(fn);
        FileResource fr = new FileResource(fn);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());        
        System.out.println(apath.getName()); //get filename
        System.out.println("The coldest weather is " + coldest.get("TemperatureF") 
                            + " at " + coldest.get("DateUTC"));
    }    
}
