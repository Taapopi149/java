import java.util.Scanner;

public class Lab05B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("ENTER STRING: ");
            String input = scanner.nextLine();
            if (input.equals("//")) {
                System.out.println("Program terminated.");
                break;
            }
            analyzeString(input);
        }
        scanner.close();
    }

    private static void analyzeString(String input) {
        boolean hasOperator = false;
        boolean hasSyntaxError = false;
        boolean hasSemanticError = false;
        char prevChar = ' ';
        
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (Character.isLetter(prevChar)) {
                    System.out.println("SEMANTIC ERROR - Two identifiers cannot be together!");
                    hasSemanticError = true;
                }
            } else if (isOperator(ch)) {
                hasOperator = true;
                if (isOperator(prevChar)) {
                    System.out.println("SEMANTIC ERROR - Two operators cannot be together!");
                    hasSemanticError = true;
                }
            } else if (Character.isDigit(ch)) {
                System.out.println("SYNTAX ERROR - Numbers are not allowed!");
                hasSyntaxError = true;
            } else {
                System.out.println("SYNTAX ERROR - Special characters are not allowed!");
                hasSyntaxError = true;
            }
            prevChar = ch;
        }
        
        if (!hasOperator) {
            System.out.println("SEMANTIC ERROR - Invalid string! No operator present.");
            hasSemanticError = true;
        }
        
        if (hasSyntaxError || hasSemanticError) {
            System.out.println("CONCLUSION--> Wrong expression: " + input + " No Derivation done! PLS RE-ENTER A VALID STRING");
        } else {
            System.out.println("Expression is valid.");
            lexicalAnalysis(input);
        }
    }
    
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    
    private static void lexicalAnalysis(String input) {
        System.out.println("======STAGE1: LEXICAL ANALYSIS-Scanner");
        int tokenCount = 1;
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                System.out.println("TOKEN#" + tokenCount++ + " " + ch + " identifier");
            } else if (isOperator(ch)) {
                System.out.println("TOKEN#" + tokenCount++ + " " + ch + " operator");
            }
        }
        System.out.println("Total number of Tokens: " + (tokenCount - 1));
    }
}
