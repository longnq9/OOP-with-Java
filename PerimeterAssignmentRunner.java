import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        //Start with total point count = 0
        int count = 0;
        //for each point in the shape
        for (Point currPt : s.getPoints()){
            //Increment the total count
            count++;
        }
        //update total count
        return count;
    }

    public double getAverageLength(Shape s) {
        //totalPerim divide by total count
        double totalPerim = getPerimeter(s);
        int totalCount = getNumPoints(s);
        double averageLength = totalPerim / totalCount;      
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        //get the length in each side
        Point prevPt = null;
        double maxLength = 0;
        for (Point currPt : s.getPoints()){
            if(prevPt != null){
                double length = prevPt.distance(currPt);
                //compare the sides to get the largest
                if(length > maxLength){
                    maxLength = length;
                }
            }
            prevPt = currPt;
        }
        return maxLength;
    }

    public double getLargestX(Shape s) {
        double maxX = 0;
        for (Point currPt : s.getPoints()){
            int currX = currPt.getX();
            if (currX > maxX){
                maxX = currX;
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of point: " + numPoints);
        System.out.println("average length: " + averageLength);
        System.out.println("largest Side: " + largestSide);
        System.out.println("largest X: " + largestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
