public class AutoGuitar {
    public static final String ALLSTRINGS = "abcdefghijklmnopqrstuvwxyz1234567890-= ";
    public static final String FILENAME = "input.txt";
    public static double CONCERT_A = 440.0;

    public static void main(String[] args){
        GuitarString[] allGuitarStrings = new GuitarString[ALLSTRINGS.length()];

        for(int i = 0; i < allGuitarStrings.length; i++){
            allGuitarStrings[i] = new GuitarString(CONCERT_A * Math.pow(2, (double)(i - 24)/12));
        }
        StdDraw.show();
        In inputFile = new In(FILENAME);
        while(inputFile.hasNextLine()){

            String line = inputFile.readLine();
            String[] lettersInLine = line.split("");

            for (String c: lettersInLine) {

                int index = ALLSTRINGS.indexOf(c);

                if (index != -1)
                    allGuitarStrings[index].pluck();
                else
                    System.out.println("WRONG KEY!!");


                double sample = 0;

                for (int i = 0; i < allGuitarStrings.length; i++)
                    sample += allGuitarStrings[i].sample();

                StdAudio.play(sample);

                for (int i = 0; i < allGuitarStrings.length; i++)
                    allGuitarStrings[i].tic();

            }
        }
    }
}
