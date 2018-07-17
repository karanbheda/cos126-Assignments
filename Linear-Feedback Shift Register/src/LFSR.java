import java.util.ArrayList;

/**
 * Created by bheda on 21-07-2017.
 */
public class LFSR {
    public int[] data;
    public int tap;
    public LFSR(String seed, int tap){
        data = new int[seed.length() + 1];
        this.tap = tap;

        String[] numbers = seed.split("");
        for(int i = numbers.length - 1; i > 0; i--){
            data[length() - i] = Integer.parseInt(numbers[i]);
        }

    }

    public int length(){
        return data.length - 1;
    }

    public int bitAt(int i){
        return data[i];
    }

    public String toString(){
        String result = "";
        for(int i = length(); i >= 1; i--)
            result += Integer.toString(bitAt(i));

        return result;
    }

    public int step(){
        int temp = bitAt(length());
        temp = temp ^ bitAt(tap);

        for(int i = length(); i > 1; i--)
            data[i] = data[i - 1];

        data[1] = temp;

        return bitAt(1);
    }

    public int generate(int k){
        int sum = 0;

        for(int i = 0; i < k; i++){
            sum = sum * 2 + step();
        }

        return sum;
    }

    public static void main(String[] args){
        LFSR lfsr = new LFSR("01101000010100010000", 17);
        /*for(int i = 0; i < 10; i++){
            int bit = 0;
            StdOut.println(lfsr + " " + lfsr.generate(8));
        }

        StdOut.println((135 ^ lfsr.generate(8)) + " " + (98 ^ lfsr.generate(8)) + " " + (80 ^ lfsr.generate(8)));
        StdOut.println((141 ^ lfsr.generate(8)) + " " + (104 ^ lfsr.generate(8)) + " " + (86 ^ lfsr.generate(8)));
        StdOut.println((146 ^ lfsr.generate(8)) + " " + (109 ^ lfsr.generate(8)) + " " + (91 ^ lfsr.generate(8)));
        StdOut.println((146 ^ lfsr.generate(8)) + " " + (109 ^ lfsr.generate(8)) + " " + (91 ^ lfsr.generate(8)));*/
    }
}
