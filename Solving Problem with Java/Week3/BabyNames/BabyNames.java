
/**
 * Write a description of BabyNames here.
 * 
 * @author (LongNQ9) 
 * @version (2/9/2020 11:30PM)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.*;
import java.io.*;

public class BabyNames {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord record : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            if(numBorn <= 100){
                System.out.println("Name " + record.get(0) + 
                                   " Gender " + record.get(1) + 
                                   " Number of born " + record.get(2)); 
            }            
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int countA = 0;
        int countB = 0;
        int countG = 0;        
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            countA++;
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                countB++;
            }
            else
            {
                totalGirls += numBorn;
                countG++;
            }
        }
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total boys: " + totalBoys + " Number of Boy's name: " + countB);
        System.out.println("Total girls: " + totalGirls + " Number of Girl's name: " + countG);
    }
    
    public int getRank(int year, String name, String gender){
        String fn = "yob" + year + ".csv";
        int rank = 0;
        boolean found = false;
        FileResource fr = new FileResource("testing/" + fn);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){               
                if(rec.get(0).equals(name)){
                    rank += 1;
                    found = true;
                    break;
                }
                else
                {
                    rank += 1;
                }
            }
        }
        if(found == true ) return rank;
        else return -1;        
    }
    
    public String getName(int year, int rank, String gender){
        String fn = "yob" + year + ".csv";
        FileResource fr = new FileResource("testing/" + fn);        
        boolean found = false;
        String foundName = "";
        int r = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                r += 1;
                if ( r == rank){
                    foundName = rec.get(0);
                    found = true;
                    break;
                }
            }
        }
        if (found == true) return foundName;
        else return "No Name";
        
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " in " + newYear);
    } 
    
    public int yearOfHighestRank(String name, String gender){
        double inf = Double.POSITIVE_INFINITY;
        int highestRank = (int) inf;
        int highestYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String year = f.getName().substring(3,7);
            int rank = getRank(Integer.parseInt(year), name, gender);
            if (rank != -1 && rank <= highestRank){
                highestRank = rank;
                highestYear = Integer.parseInt(year);
            }            
        }                
        return highestYear;
    }
    
    public double getAverageRank(String name, String gender){
        double averageRank = 0;
        int count = 0;
        double sum = 0;
        boolean found = false;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String year = f.getName().substring(3,7);
            int rank = getRank(Integer.parseInt(year), name, gender);
            if (rank != -1){
                sum += rank;
                count++;
                found = true;
            }           
        }
        if (found == true)
        {
            return averageRank = sum / count;
        }
        else
        {
            return -1;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int r = 0;
        int totalBirths = 0;
        String fn = "yob" + year + ".csv";
        FileResource fr = new FileResource("testing/" + fn);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                int numBorn = Integer.parseInt(rec.get(2));
                r = getRank(year, rec.get(0), gender);
                if (r < rank){
                    totalBirths += numBorn;
                }
            }
        }
        if (totalBirths > 0 ) return totalBirths;
        else return -1;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int totalBirths = getTotalBirthsRankedHigher(1990, "Drew","M");
        System.out.println(totalBirths);
    }
    public void testGetAverageRank(){
        double averageRank1 = getAverageRank("Susan","F");
        System.out.println(averageRank1);
        double averageRank2 = getAverageRank("Robert","M");
        System.out.println(averageRank2);
    }
    
    public void testYearOfHighestRank(){
        int highestYear = yearOfHighestRank("Genevieve","F");
        System.out.println(highestYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014,"F");
        whatIsNameInYear("Owen", 1974, 2014,"M");
        //whatIsNameInYear("Ethan", 2012, 2014,"F");
    }
    
    public void testGetName(){
        String foundName = getName(1982, 450,"M");
        System.out.println(foundName);
    }
    public void testGetRank(){
        int rank = getRank(1971,"Frank","M");
        System.out.println(rank);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
