public class Tour {

    private class Node{
        private Point p;
        private Node next;
    }

    Node first = new Node();
    public Tour(){
        first.next = first;
        first.p = null;
    }                                    // creates an empty tour

    public Tour(Point a, Point b, Point c, Point d){
        Node second = new Node();
        Node third = new Node();
        Node fourth = new Node();

        first.p = a;
        first.next = second;

        second.p = b;
        second.next = third;

        third.p = c;
        third.next = fourth;

        fourth.p = d;
        fourth.next = first;

    }  // creates the 4-point tour a->b->c->d->a (for debugging)

    public int size(){
        if(first.p == null){ return 0; }

        Node temp = first;
        int n = 1;

        do{
            n++;
            temp = temp.next;
        }while(temp.next != first);

        return n;
    }                                    // returns the number of points in this tour

    public double length() {
        if(first.p == null){ return 0; }

        Node temp1 = first;
        Node temp2 = first.next;
        double distance = 0.0;

        do{
           distance += temp1.p.distanceTo(temp2.p);

           temp1 = temp1.next;
           temp2 = temp2.next;
        }while(temp1 != first);

        return distance;
    }                                 // returns the length of this tour

    public String toString(){
        Node temp = first;
        StringBuilder output = new StringBuilder();

        do{
            output.append(temp.p.toString() + "\n");
            temp = temp.next;
        }while(temp != first);

        return output.toString();
    }                                // returns a string representation of this tour

    public   void draw(){
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        Node temp1 = first;
        Node temp2 = first.next;

        do{
            temp1.p.drawTo(temp2.p);

            temp1 = temp1.next;
            temp2 = temp2.next;
        }while(temp1 != first);

    }                                  // draws this tour to standard drawing

    public   void insertNearest(Point p){
        if(size() == 0){
            first.p = p;
            first.next = first;
            return;
        }

        double minDistance = Double.POSITIVE_INFINITY;
        Node closestNode = null;
        Node temp = first;
        double distance;

        do{
            distance = temp.p.distanceTo(p);

            if(distance < minDistance){
                minDistance = distance;
                closestNode = temp;
            }

            temp = temp.next;
        }while(temp != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = closestNode.next;

        closestNode.next = newNode;
    }                    // inserts p using the nearest neighbor heuristic

    public   void insertSmallest(Point p){
        if(size() == 0){
            first.p = p;
            first.next = first;
            return;
        }

        double leastDistance = Double.POSITIVE_INFINITY;
        Node smallestNode = null;
        Node temp = first;
        double distance;

        do{
            distance = temp.p.distanceTo(p) + temp.next.p.distanceTo(p) - temp.p.distanceTo(temp.next.p);

            if(distance < leastDistance){
                leastDistance = distance;
                smallestNode = temp;
            }

            temp = temp.next;
        }while(temp != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = smallestNode.next;

        smallestNode.next = newNode;
    }                   // inserts p using the smallest increase heuristic

    // tests this class by directly calling all constructors and instance methods
    public static void main(String[] args){
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // print the size to standard output
        int size = squareTour.size();
        StdOut.println("Number of points = " + size);

        double length = squareTour.length();
        StdOut.println("Tour length = " + length);

        StdOut.println(squareTour);

        squareTour.draw();
    }
}