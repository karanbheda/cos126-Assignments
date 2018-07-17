/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Takes 4 double command-line arguments x1,y1,x2,y2 i.e. latitude and longitude of 2 points on Earth.
 *                We find the greatcircle distance between the two points.
 *                Answer is then converted to nautical miles.
 *
 ******************************************************************************/
public class GreatCircle {
    public static void main(String[] args){
        double x1 = Math.toRadians(Double.parseDouble(args[0]));
        double y1 = Math.toRadians(Double.parseDouble(args[1]));
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        double distance = 60*Math.toDegrees(Math.acos(Math.sin(x1) * Math.sin(x2)  +   Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2)));
        System.out.println(distance);
    }
}
