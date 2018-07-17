/**
 * Created by bheda on 22-07-2017.
 */
public class GuitarString {
    public static final int SAMPLE_RATE = 44100;
    public static final double DECAY_FACTOR = 0.996;

    private RingBuffer guitar;

    public GuitarString(double frequency){
        guitar = new RingBuffer((int) Math.round(SAMPLE_RATE/frequency));

        for(int i = 0; i < guitar.capacity(); i++)
            guitar.enqueue(0);
    }

    public GuitarString(double[] init){
        guitar = new RingBuffer(init.length);

        for(int i = 0; i < init.length; i++)
            guitar.enqueue(init[i]);
    }

    public int length(){
        return guitar.size();
    }

    public void pluck(){
        for(int i = 0; i < guitar.capacity(); i++)
            guitar.ring[i] = (Math.random() - 0.5);
    }

    public void tic(){
        double temp = guitar.dequeue();
        temp = (temp + guitar.peek())/2;

        guitar.enqueue(temp * DECAY_FACTOR);
    }

    public double sample(){
        return guitar.peek();
    }

    public static void main(String[] args){
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 50; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }
}
