import java.util.Scanner;

public class Lab03B {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        String[] tokens = input.split(" ");
        int tokenCount = 0;

        System.out.println("\nOutput:\n");
        for (String token : tokens) {
            tokenCount++;
            String attribute = getAttribute(token);
            System.out.println("TOKEN#" + tokenCount + " " + token + " " + attribute);
        }
        
        System.out.println("Total number of Tokens: " + tokenCount);
    }

    private static String getAttribute(String token){
        if (token.matches("[A-D]")) {
            return "Identifier";
        } else if (token.matches("[+\\-*/]")) {
            return "Operator";
        }
        return "Unknown";
    }
}
