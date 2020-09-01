
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import java.util.*;
public class Part4 {
    public ArrayList<String> getYouTubeURLs(){
        //declare urls variable with ArrayList<String>
        ArrayList<String> urls = new ArrayList<String>();
        //read content from url
        URLResource res = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        //convert content to lowercase
        String contents = res.asString().toLowerCase(); 
        
        int subStringIdx = contents.indexOf("youtube.com");
        int leftIdx = -1;
        int rightIdx = -1;
        
        while (subStringIdx >= 0){
            for (int i = subStringIdx; i>0; i--){
                if (contents.charAt(i) == '"'){
                    leftIdx = i;
                    break;
                }
            }
            
            for (int j = subStringIdx; j < contents.length(); j++){
                if (contents.charAt(j) == '"'){
                    rightIdx = j;
                    break;
                }
            }
            if (leftIdx >=0 && rightIdx >=0){
                urls.add(contents.substring(leftIdx + 1, rightIdx));
            }
            subStringIdx = contents.indexOf("youtube.com", subStringIdx + 1);
        }
        return urls;
    }
    public void testGetYoutubeURLs(){
        ArrayList<String> urls = getYouTubeURLs();
        for (String url : urls){
            System.out.println(url);
        }
    }
}
