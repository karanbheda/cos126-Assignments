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
public class Transform2D {


    /**
     * Method description:
     */
    public static double[] copy(double[] array){
        double[] x = new double[array.length];

        for(int i=0; i < array.length; i++)
            x[i] = array[i];

        return x;
    }


    /**
     * Method description:
     */
    public static void scale(double[] x, double[] y, double alpha){
        for(int i=0; i < x.length; i++){
            x[i] = alpha * x[i];
            y[i] = alpha * y[i];
        }
    }


    /**
     * Method description:
     */
    public static void translate(double[] x, double[] y, double dx, double dy){
        for(int i=0; i < x.length; i++){
            x[i] += dx;
            y[i] += dy;
        }
    }

    /**
     * Method description:
     */
    public static void rotate(double[] x, double[] y, double theta){
        for(int i=0; i < x.length; i++){
            x[i] = x[i] * Math.cos(Math.toRadians(theta)) - y[i] * Math.sin(Math.toRadians(theta));
            y[i] = y[i] * Math.cos(Math.toRadians(theta)) + x[i] * Math.sin(Math.toRadians(theta));
        }
    }
}
