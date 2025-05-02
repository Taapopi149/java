import java.util.Scanner;

public class Lab11_A {
    // Function to convert string to binary
    public static String toBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            // Convert each character to binary using Integer.toBinaryString and format to 7 or 8 bits
            String binChar = Integer.toBinaryString(input.charAt(i));
            // Optional: Pad with zeros to make it 7 or 8 bits (ASCII)
            while (binChar.length() < 7) {
                binChar = "0" + binChar;
            }
            binary.append(binChar).append("  ");
        }
        return binary.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Convert and display result
        System.out.println("\nThe binary of " + input + " is: " + toBinary(input));
        
        scanner.close();
    }
}
