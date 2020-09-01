
/**
 * Write a description of PerimeterRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter(Shape s){
        //Start with totalPerim = 0
        double totalPerim = 0;
        //Start with prevPt = the last point
        Point prevPt = s.getLastPoint();
        //For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
            //Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            //Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            //Update prevPt to be currPt
            prevPt = currPt;
        }
        //totalPerim is answer
        return totalPerim;
    }
    public void testPerimeter(){
        FileResource f = new FileResource();
        Shape s = new Shape(f);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    public static void main(String[] args){
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
