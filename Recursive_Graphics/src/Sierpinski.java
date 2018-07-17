import java.awt.*;

/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:
 *
 ******************************************************************************/
public class Sierpinski {
    public static final double UNIT_SIDE = 1;

    public static double height(double length){
        double height = length * Math.pow(3,0.5)/2;
        return height;
    }


    public static void filledTriangle(double x, double y, double length){
        double[] px = {x, x - (length/2), x + (length/2)};
        double[] py = {y, y + height(length), y + height(length)};

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledPolygon(px, py);
    }

    public static void sierpinski(int n, double x, double y, double length){

        if(n == 0)
            return;

        filledTriangle(x, y, length);

        //draw 3 other sierpinski triangles
        sierpinski(n-1, x, y + height(length), length/2);
        sierpinski(n-1, x - (length/2), y, length/2);
        sierpinski(n-1, x + (length/2), y, length/2);
    }


    public static void drawBaseTriangle(){
        double[] x = {0, UNIT_SIDE, UNIT_SIDE/2};
        double[] y = {0, 0, height(UNIT_SIDE)};

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.polygon(x, y);
    }

    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);

        //draw the outline equilateral triangle
        drawBaseTriangle();

        //draw sierpinski traingles
        sierpinski(n,UNIT_SIDE/2,0,UNIT_SIDE/2);

    }
}
