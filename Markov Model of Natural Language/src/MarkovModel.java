import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

public class MarkovModel {
    private String text;
    private int k;
    private ST frequencyOfKgram;
    private ST precedenceOfKgram;
    private final int RADIX = 10;
    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k){
        this.text = text;
        this.k = k;
        text = text + text.substring(0, k);
        frequencyOfKgram = new ST();
        precedenceOfKgram = new ST();

        for(int i = 0; i < text.length() - k; i++){
            String kgram = text.substring(i, i + k);

            if(frequencyOfKgram.contains(kgram)){
                frequencyOfKgram.put(kgram, (int) frequencyOfKgram.get(kgram) + 1);

                if(precedenceOfKgram.contains(kgram + text.charAt(i + k)))
                    precedenceOfKgram.put(kgram + text.charAt(i + k), (int) precedenceOfKgram.get(kgram + text.charAt(i + k)) + 1);

                else
                    precedenceOfKgram.put(kgram + text.charAt(i + k), 1);
            }

            else {
                frequencyOfKgram.put(kgram, 1);
                precedenceOfKgram.put(kgram + text.charAt(i + k), 1);
            }

        }
    }

    // returns the order k of this Markov model
    public int order(){
        return k;
    }

    // returns a string representation of the Markov model (as described below)
    public String toString(){
        String result = "";
        String temp = "";
        for (Object key : precedenceOfKgram.keys()){
            if(temp.equals("")){
                result += key.toString().substring(0, order()) + ":";
            }

            else if(!temp.equals(key.toString().substring(0, order()))) {
                result += "\n";
                result += key.toString().substring(0, order()) + ":";
            }

            result += " " + key.toString().charAt(order()) + " " + precedenceOfKgram.get(key.toString());
            temp = key.toString().substring(0, order());
        }

        return result;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) throws IllegalArgumentException{
        try {
            if (kgram.length() != k)
                throw new IllegalArgumentException("kgram exceeds length k");

            if(frequencyOfKgram.contains(kgram))
                return (int) frequencyOfKgram.get(kgram);

        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return 0;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c){
        try {
            if (kgram.length() != k)
                throw new IllegalArgumentException(kgram + c + " exceeds length k");

            if(precedenceOfKgram.contains(kgram + c))
                return (int) precedenceOfKgram.get(kgram + c);

            if(!frequencyOfKgram.contains(kgram))
                throw new IllegalArgumentException(kgram + c + " does not exist as key");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return 0;
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram){
        int[] frequencies = new int[128];

        for(Object key: precedenceOfKgram.keys()){
            if(key.toString().substring(0, order()).equals(kgram)){
                frequencies[key.toString().charAt(order())] = (int) precedenceOfKgram.get(key.toString());
            }
        }

        return Character.forDigit(StdRandom.discrete(frequencies), 10);
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args){
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
    }
}