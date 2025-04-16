import java.util.Scanner;

public class Lab02A {

    public static void main(String[] args) {

        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Number and Second number: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        scanner.close();

        if (num1 < 0) {
            System.out.println("startNo cannot be less than 0");
        } else if (num2 < 0) {
            System.out.println("endNo cannot be less than 0");
        } else if (num2 > 9) {
            System.out.println("endNo can not be greater the 9");
        } else if (num1 > num2) {
            System.out.println("startNo must NOT be greater than endNo");
        } else {

            for (int i = num1; i < num2 + 1; i++) {
                if (i % 2 == 0) {
                    sum = i + sum;
                }
            }
        }

        System.out.println(sum);

    }
}
