import java.util.StringTokenizer;

public class Lab02B {
    public static void main(String[] args){
        String s = "my name is khan";

    StringTokenizer st = new StringTokenizer(s, " ");

    while (st.hasMoreTokens()) {
        System.err.println(st.nextToken());
    }

    }
}
