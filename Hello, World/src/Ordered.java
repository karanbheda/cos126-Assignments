/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Takes 3 integers as command-line arguments.
 *                A boolean variable is true only if the three integers are strictly
 *                in ascending or descending order
 *
 ******************************************************************************/
public class Ordered {
    public static void main(String[] args){
        boolean flag = true;
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        flag = ( a > b ? ( b > c ? true : false) : ( c > b ? true : false));
        System.out.println(flag);
    }
}
