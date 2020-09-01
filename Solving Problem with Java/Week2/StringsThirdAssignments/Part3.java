
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 
import edu.duke.*;
import java.io.*;
import java.lang.*;
public class Part3 {
    public void processGenes(StorageResource sr){
        int count = 0;
        System.out.println("Strings are longer than 9 char: ");
        for (String s : sr.data()){
            if (s.length() > 60){
                System.out.println(s);
                count++;
            }
        }
        System.out.println("Number of Strings are longer than 9 char: " + count);
        
        Part2 p2 = new Part2();
        int countCGr = 0;
        System.out.println("Strings have C-G ratio > 0.35: ");
        for (String s : sr.data()){
            double cgr = p2.cgRatio(s);
            if (cgr > 0.35){
                System.out.println(s);
                countCGr++;
            }
        }
        System.out.println("Number of Strings have C-G ratio > 0.35: " + countCGr);
        
        String longestGene = "";        
        for (String s : sr.data()){
            if (s.length() > longestGene.length()){
                longestGene = s;
            }
        }                        
        System.out.println("Longest string is: " + longestGene + "\nwith length: " + longestGene.length());
    }
    public void testProcessGenes(){
        StorageResource rs = new StorageResource();
        rs.add("ATGGTCTAATAG");
        rs.add("CCTGGCTAACGCTAA");
        rs.add("ATGTAA");
        Part1 p1 = new Part1();
        Part2 p2 = new Part2();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String dna = fr.asString().toUpperCase();
            System.out.println("read " + dna.length() + " characters");       
            StorageResource allGenes = p1.getAllGenes(dna);
            System.out.println("Total genes are " + allGenes.size());
            System.out.println("Number of CTG: " + p2.countCTG(dna));
            processGenes(allGenes);            
        }        
    }
    public static void main(String args[]){
        Part3 DNA = new Part3();
        DNA.testProcessGenes();
        
    }
}
