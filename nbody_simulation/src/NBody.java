/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Showing the motion of more than two particles under gravitational force and animating the same.
 *                Here we use various laws of motion to get the result
 *
 ******************************************************************************/
import java.util.*;
import java.io.*;
public class NBody {
    private static ArrayList<particle> particles;
    private static int n;
    private static double T, t;
    private static double radius;

    /**
     * Method description: Constructor to initialize the total time T for which the program should run and
     *                     time interval t at which results are to be displayed
     * @param s1  :  argument value 0 which is the total time T
     * @param s2  :  argument value 1 which is the time interval t
     * @param s3  :  argument value 2 which is the filename from where the data is to be read
     *
     */
    NBody(String s1, String s2, String s3){
        T = Double.parseDouble(s1);
        t = Double.parseDouble(s2);

        readFile(s3);
    }

    public static void main(String[] args ) {

        NBody nbody = new NBody(args[0],args[1],args[2]);

        nbody.run();

        nbody.display();
    }


    /**
     * Method description: this method calls the method calculate at every time interval t until we reach final time T
     *
     */
    void run(){

        for (double i = 0; i < T; i += t) {
            //calculate everything at time i
            calculate();

            draw();
        }
        StdAudio.close();
    }


    /**
     * Method description: used to draw the planets using StdDraw
     *
     */
    void draw(){
        //StdDraw.clear();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"starfield.jpg");
        for(int i=0; i < n; i++){
            StdDraw.picture(particles.get(i).x, particles.get(i).y, particles.get(i).fileName);
        }
        StdDraw.pause(25);
    }


    /**
     * Method description: this method calculates force experienced by all the bodies, their acceleration due to force,
     *                     the final velocity as a result of acceleration and final position in time interval t
     *
     */
    void calculate(){

        for(int i=0; i<n; i++){
            particle planet1 = particles.get(i);

            // calculating force on each particle at time t
            planet1.Fx = 0;
            planet1.Fy = 0;
            for(int j=0; j<n; j++){
                particle planet2 = particles.get(j);
                if(i != j) {
                    double distance = Math.pow(planet1.x - planet2.x, 2) + Math.pow(planet1.y - planet2.y, 2);

                    planet1.forceX(planet2, distance);
                    planet1.forceY(planet2, distance);
                }
            }

            //calculating acceleration of a particle at time t
            planet1.accelerateX();
            planet1.accelerateY();

            //calculate final veloctity of a particle at time t + dt
            planet1.finalVelocityX(t);
            planet1.finalVelocityY(t);

            //calculate final position of a particle after time t + dt
            planet1.positionX(t);
            planet1.positionY(t);

        }

    }

    /**
     * Method description: this method is used to display the final state of the system
     *
     */
    void display(){
        StdOut.println(n);
        StdOut.println(radius);
        for(int i=0; i<n; i++){
            particle temp = particles.get(i);
            StdOut.printf("%f %f %f %f %f %s\n",temp.x,temp.y,temp.vx,temp.vy,temp.mass,temp.fileName);
        }
    }


    /**
     * Method description: this method reads all the data from the file
     * @param fileName  :  it is the name of the file passed in argument. Data is read from this file
     */
    void readFile(String fileName){

        BufferedReader br = null;
        FileReader fr = null;

        try{
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            n = Integer.parseInt(br.readLine());
            radius = Double.parseDouble(br.readLine());

            particles = new ArrayList<>(n);
            String currentLine;
            for(int i=0; i<n; i++){
                currentLine = br.readLine();
                String[] values = currentLine.split(" ");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                double vx = Double.parseDouble(values[2]);
                double vy = Double.parseDouble(values[3]);
                double mass = Double.parseDouble(values[4]);
                String file = values[5];

                particles.add(new particle(i, x, y, vx, vy, mass, file));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(br != null)
                    br.close();

                if(fr != null)
                    fr.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
