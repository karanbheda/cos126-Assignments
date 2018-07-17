public class TextGenerator {
    private int k, T;
    private String input;

    TextGenerator(String a, String b){
        this.k = Integer.parseInt(a);
        this.T = Integer.parseInt(b);
        System.out.println(k + " " + T + " ");

    }

    public static void main(String[] args){
        TextGenerator object = new TextGenerator(args[0], args[1]);
    }
}
