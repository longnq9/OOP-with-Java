
/**
 * Write a description of Part1 here.
 * 
 * @author LongNQ9 
 * @version 1/9/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part1 {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.indexOf(exportOfInterest) != -1){ //(export.contains(exportOfInterest))
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void countryInfo(CSVParser parser, String country){                
        for (CSVRecord record : parser){               
            String ctry = record.get("Country");
            if (ctry.equals(country)){
                System.out.println(ctry + ": " + record.get("Exports") + ": "+record.get("Value (dollars)"));                                
            }
            else 
            {
                System.out.println("Not Found");                
            }            
        }        
    }
    public void listExportsTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String item = record.get("Exports");
            if (item.contains(exportItem1) && item.contains(exportItem2)){
                System.out.println(record.get("Country"));            
            }
        }        
    }
    public int numberOfExports(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String item = record.get("Exports");
            if (item.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String amou = record.get("Value (dollars)");
            if (amou.length() > amount.length() + 1){
                System.out.println(record.get("Country") + " " + amou);
            }
        }
    }
    public void tester(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();        
            //listExporters(parser, "coffee");
            //countryInfo(parser, "Nauru");            
            //listExportsTwoProducts(parser, "cotton", "flowers");
            //System.out.println(numberOfExports(parser, "cocoa"));
            bigExporters(parser, "$999,999,999,999");
        }        
    }
}
