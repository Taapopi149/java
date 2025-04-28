import java.util.Scanner;

public class DecimalToBinary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int decimalInput = scanner.nextInt();
        String binaryOutput = Integer.toBinaryString(decimalInput);
        System.out.println("Binary representation: " + binaryOutput);
        scanner.close();
    }
}