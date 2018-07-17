/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Takes values for red, green and blue as command-line arguments.
 *                Using the given formulae we convert RGB to CMYK and print it.
 *
 ******************************************************************************/
public class RGBtoCMYK {
    public static final double MAX = 255;
    public static void main(String[] args){
        String[] rgbs = {"red","green","blue"};
        String[] cmyks = {"cyan","magenta","yellow"};
        double[] rgb = new double[3];
        double[] cmyk = new double[3];
        for(int i=0; i<3; i++){
            rgb[i] = Double.parseDouble(args[i]);
            System.out.printf("%-20s  =  %s\n",rgbs[i],fmt(rgb[i]));
        }

        double white = Math.max(Math.max(rgb[0]/MAX, rgb[1]/MAX),rgb[2]/MAX);
        for(int i=0; i<3; i++){
            cmyk[i] = (white - (rgb[i]/MAX))/white;
            System.out.printf("%-20s  =  %s\n",cmyks[i],fmt(cmyk[i]));
        }

        double black = 1 - white;

        System.out.printf("%-20s  =  %s\n","black",fmt(black));

    }


    /**
     * Method description: used for formatting the double number
     * @param d  :  it is the double variable to be formatted
     */
    public static String fmt(double d){
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
