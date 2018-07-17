/******************************************************************************
 *  Name:    Karan Bheda
 *  NetID:   TaZz
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Represents one body in space that has position(x, y), some velocity (vx, vy)
 *                and mass.
 *
 ******************************************************************************/
public class particle {
    int number;
    double x,y,vx,vy,mass,Fx=0,Fy=0,Ax=0,Ay=0;
    String fileName;
    public static final double GCONSTANT = 6.67 * Math.pow(10,-11);


    /**
     * Method description: Constructor to create object of this class
     * @param number  :  it is the serial number of the body
     * @param x  :  represents x-coordinate of the body
     * @param y  :  represents y-coordinate of the body
     * @param vy  :  represents x-coordinate of velocity of the body
     * @param vy  :  represents y-coordinate of velocity of the body
     * @param mass  : represents mass of the body
     * @param fileName  : corresponding file name for .gif file of the body
     */
    particle(int number, double x, double y, double vx, double vy, double mass, String fileName){
        this.number = number;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.fileName = fileName;
    }


    /**
     * Method description: calculates the force exerted by other particle in X direction
     * @param planet  :  object of class particle which attracts the object under consideration
     * @param distance  :  sqaure of distance between the two particles
     */
    void forceX(particle planet, double distance){
        double temp = (planet.mass * this.mass * GCONSTANT)/distance;
        temp *= (planet.x - this.x)/Math.pow(distance,0.5);

        this.Fx += temp;
    }


    /**
     * Method description: calculates the force exerted by other particle in Y direction
     * @param planet  :  object of class particle which attracts the object under consideration
     * @param distance  :  sqaure of distance between the two particles
     */
    void forceY(particle planet, double distance) {
        double temp = (planet.mass * this.mass * GCONSTANT)/distance;
        temp *= (planet.y - this.y)/Math.pow(distance,0.5);

        this.Fy += temp;
    }


    /**
     * Method description: calculates the acceleration of particle in X direction
     */
    void accelerateX(){
        this.Ax = this.Fx/this.mass;
    }

    /**
     * Method description: calculates the acceleration of particle in Y direction
     */
    void accelerateY(){
        this.Ay = this.Fy/this.mass;
    }


    /**
     * Method description: calculates the velocity of particle in X direction in time interval dt
     * @param dt  :  time interval for which final velocity of particle will be calculated
     */
    void finalVelocityX(double dt){
        this.vx += (dt * this.Ax);
    }


    /**
     * Method description: calculates the velocity of particle in Y direction in time interval dt
     * @param dt  :  time interval for which final velocity of particle will be calculated
     */
    void finalVelocityY(double dt){
        this.vy += (dt * this.Ay);
    }


    /**
     * Method description: calculates the final position of particle in X direction in time interval dt
     * @param dt  :  time interval for which final position of particle will be calculated
     */
    void positionX(double dt){
        this.x += (dt * vx);
    }


    /**
     * Method description: calculates the final position of particle in Y direction in time interval dt
     * @param dt  :  time interval for which final position of particle will be calculated
     */
    void positionY(double dt){
        this.y += (dt * vy);
    }
}
