public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int SAMPLE_RATE = 44100;
    public static double CONCERT_A = 440.0;

    public static void main(String[] args){
        GuitarString[] allStrings = new GuitarString[KEYBOARD.length()];

        for(int i = 0; i < allStrings.length; i++){
            allStrings[i] = new GuitarString(CONCERT_A * Math.pow(2, (double)(i - 24)/12));
        }

        while(true){

            if (StdDraw.hasNextKeyTyped()) {

                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);

                if(index != -1)
                    allStrings[index].pluck();
                else
                    System.out.println("WRONG KEY!!");

            }

            double sample = 0;

            for(int i = 0; i < allStrings.length; i++)
                sample += allStrings[i].sample();

            StdAudio.play(sample);

            for(int i = 0; i < allStrings.length; i++)
                allStrings[i].tic();
        }
    }
}
