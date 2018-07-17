import java.awt.*;

/**
 * Created by bheda on 21-07-2017.
 */
public class PhotoMagic {
    private static final String FINAL_IMAGE = "encoded.png";

    public static Picture transform(Picture picture, LFSR lfsr){
        Picture encoded = new Picture(picture);

        for(int i = 0; i < picture.width(); i++){
            for(int j = 0; j < picture.height(); j++){
                Color current = picture.get(i, j);

                int red = current.getRed();
                int blue = current.getBlue();
                int green = current.getGreen();

                red = red ^ lfsr.generate(8);
                blue = blue ^ lfsr.generate(8);
                green = green ^ lfsr.generate(8);

                Color newColor = new Color(red, green, blue);
                encoded.set(i, j, newColor);
            }
        }

        return encoded;
    }

    public static void main(String[] args){
        LFSR code = new LFSR(args[1], Integer.parseInt(args[2]));
        Picture picture = new Picture(args[0]);

        Picture newPicture = transform(picture, code);
        newPicture.save(FINAL_IMAGE);
        newPicture.show();
    }
}
