import java.util.Scanner;

public class Lab04a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int startNo = scanner.nextInt();
        int endNo = scanner.nextInt();

     
        if (startNo < 0) {
            System.out.println("startNo cannot be less than 0");
            return;
        }
        if (endNo < 0) {
            System.out.println("endNo cannot be less than 0");
            return;
        }
        if (endNo > 9) {
            System.out.println("endNo cannot be greater than 9");
            return;
        }
        if (startNo > endNo) {
            System.out.println("startNo must NOT be greater than endNo");
            return;
        }

        int sumALL = 0;
        for (int i = startNo; i <= endNo; i++) {
            sumALL += i;
        }
        
        System.out.println("Sum of ALL Numbers = " + sumALL);
    }
}

