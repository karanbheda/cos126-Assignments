/**
 * Created by bheda on 22-07-2017.
 */
public class RingBuffer {
    public double[] ring;
    private int front, end, n;

    public RingBuffer(int capacity){
        ring = new double[capacity];
        front = 0;
        end = 0;
        n = 0;
    }

    public int capacity(){
        return ring.length;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        if(size() == 0)
            return true;
        else
            return false;
    }

    public boolean isFull(){
        if(size() == capacity())
            return true;
        else
            return false;
    }

    public void enqueue(double x){
        if(isFull())
            StdOut.println("FULL!!");
        else{
            ring[end] = x;
            end = (++end)%capacity();
            n++;
        }

    }

    public double dequeue(){
        if(isEmpty())
            StdOut.println("EMPTY!!");
        else{
            double temp = ring[front];
            front = (++front)%capacity();
            n--;
            return temp;
        }
        return -1;
    }

    public double peek(){
        if(!isEmpty())
            return ring[front];
        else
            return 0;
    }

    public static void main(String[] args){
        RingBuffer rb = new RingBuffer(10);
        rb.enqueue(0.2);
        System.out.println(rb.size());
        rb.enqueue(0.1);
        System.out.println(rb.size());
        rb.enqueue(0.5);
        System.out.println(rb.size());
        rb.enqueue(0.3);
        System.out.println(rb.size());
        rb.enqueue(-0.2);
        System.out.println(rb.size());
        rb.enqueue(0.4);
        System.out.println(rb.size());
        rb.dequeue();
        System.out.println(rb.size());
        rb.dequeue();
        System.out.println(rb.size());
        rb.enqueue(0.3);
        System.out.println(rb.size());
        rb.enqueue(0.3);
        System.out.println(rb.size());
        for(int i = rb.front; i < rb.end; i++){
            StdOut.println(rb.ring[i]);
        }
        StdOut.println(rb.peek());
        /*int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());*/
    }
}
