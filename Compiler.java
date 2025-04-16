import java.util.Scanner;

public class Compiler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter String: ");
        String input = scanner.nextLine();

        if(input.equals("99")){
            System.exit(0);
        } else {
            LexicalStage1(input);
            
            if (!SyntaxAnalysis(input)) {
                System.out.println("CONCLUSION--> Wrong expression: " + input  + "No Derivation done!");
            } else {
                SemanticAnylsis(input);
            }
        }

        scanner.close();
    }

  // ================= Stage 1: Lexical Analysis ======================  
    public static void LexicalStage1(String input) {
        String[]tokens = input.split(" ");
        int countTokens = 0;

        System.out.println("\n================== Stage 1: Lexical Analysis ===================\n");
            for (String token: tokens) {
                countTokens ++;
                String tokenAttribute = TokenAttribute(token);
                System.out.println("TOKEN#" + countTokens + " " + token + " " + tokenAttribute);
            }

            System.out.println("Total number of Tokens: " + countTokens);
            System.out.println("GIVEN THE GRAMMAR: E=E1 | E=E1*E2 | E=E1+E2 | E=digit | E={0,1,2,3,4,5,6,7,8,9}");
    }


    public static String TokenAttribute(String token) {
        if (token.matches("\\d+")) {
            return "Identifier";
        } else if (token.matches("[+\\-*/]")) {
            return "Operator";
        } else if (token.equals(";")) {
            return "Symbol";
        }

        return "Unkown";

    }

    // ========== Stage 2: Syntax Analysis ==========
    public static boolean SyntaxAnalysis(String input){
        String[] tokens = input.split(" ");
        boolean hasSyntaxError = false; 

        System.out.println("\n=========== STAGE2: COMPILER TECHNIQUES--> SYNTAX ANALYSIS ==============\n ");
        System.out.println("GET A DERIVATION FOR : " + input);
        
       
        for(int i = 0; i < tokens.length - 1; i++){
            if (tokens[i].matches("\\d+")&& tokens[i +1].matches("\\d+")) {
                System.out.println("SYNTAX ERROR - Missing operator between numbers!");
                hasSyntaxError = true;
            }
        }

        if (!input.endsWith(";")) {
            System.out.println("SYNTAX ERROR - Missing semicolon at the end!");  
            hasSyntaxError = true;  
        }

       if (hasSyntaxError) {
       return false;
       }
       
       String derivation = input.replaceAll("(\\d+)", "E$1");
       System.out.println(derivation);

       for (String token : tokens) {

            if (token.matches("\\d+")) {
                derivation = derivation.replaceFirst("E\\d+", "digit" + token);
                System.out.println(derivation);             
            }
       }

       return true;
    }

    // ========== Stage 3: Semantic Analysis ==========
    public static void SemanticAnylsis(String input){
        String[] tokens = input.split(" ");
        boolean semanticError = false;
    
        System.out.println("\n=========== STAGE3: COMPILER TECHNIQUES--> SEMANTIC ANALYSIS =============\n");

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/") && i +1 < tokens.length) {
                if (tokens[i + 1].equals("0")) {
                    System.out.println("SEMANTIC ERROR - Division by zero is not allowed!");
                    semanticError = true;
                }
            }
        }

        if (!semanticError) {
            System.out.println("CONCLUSION--> This expression: " + input + " is Syntactically and Semantically correct"); 
            System.out.println(" ");          
        }
    }

}