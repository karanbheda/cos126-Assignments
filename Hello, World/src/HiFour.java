/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Takes four names as command-line arguments and prints them
 *                in reverse order. For eg,
 *                %java Abc Xyz Lmn Pqr
 *                Hi Pqr, Lmn, Xyz, and Abc.
 *
 ******************************************************************************/
public class HiFour {
    public static void main(String[] args){
        String result = "Hi x, x, x, and x.";
        for(int i=args.length-1; i>=0; i--){
            result = result.replaceFirst("x",args[i]);
        }
        System.out.println(result);
    }
}
