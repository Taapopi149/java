import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class Lab07A {

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
                boolean semanticSuccess = SemanticAnylsis(input);
                if (semanticSuccess) {
                    Intermediate(input);
                    CodeGeneration(input);
                }
                
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
        boolean derivationDone = false;

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
                derivationDone = true;
                 
            }
       }

       if (derivationDone) {
        System.out.println(input);
       }

       return true;
    }

    // ========== Stage 3: Semantic Analysis ==========
    public static boolean SemanticAnylsis(String input){
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

        return !semanticError; 
    }

    // ======================== STAGEA: Intermediate Code Representation (ICR) ================
    public static void Intermediate(String input){
        System.out.println("\n =========== STAGE4: INTERMEDIATE CODE REPRESENTATION (ICR) =============\n");

        String[] tokens = input.replace(";", " ").split(" ");
        
        String storeValues = "";
        int keepTrack = 1;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.matches("[+\\-*/]")) {
                String realnum1 = tokens[i - 1];
                String realnum2 = tokens[i + 1];
                
                storeValues = "t" + keepTrack;
                System.out.println(storeValues + " " + "=" +" "+ realnum1 + " " + token + " " + realnum2 );

                tokens[i + 1] = storeValues;
                keepTrack ++;
            }
        }

    }

    // ================== Stage 5: CODE GENERATION ========================= 

    public static List<String> infixToPostfix (String input) {

        String[] tokens = input.replace(";", "").trim().split(" ");
        Stack<String> operatorStack = new Stack<>();
        List<String> output = new ArrayList<>();

        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);

        for (String token: tokens ) {
            if (token.matches("\\d+")) {
                output.add(token);
            } else if (precedence.containsKey(token)) {
                while (!operatorStack.isEmpty() && precedence.containsKey(operatorStack.peek()) &&
                    precedence.get(operatorStack.peek()) >= precedence.get(token)) {
                        output.add(operatorStack.pop());
                    }                    
                    operatorStack.push(token);
            }

        }

        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }

        output.add(";");
        return output;
    }
    


    public static void CodeGeneration(String input){
        System.out.println("\n=========== STAGE5: CODE GENERATION =============");

        List<String> postfix = infixToPostfix(input);
        Stack<String> stack = new Stack<>();
        int tempCount = 1;

        for (String token : postfix) {

            if (token.equals(";")) break;

            if (token.matches("\\d+")) {
                stack.push(token);

            } else if(token.matches("[+\\-*/]")) {
               
                String right = stack.pop();
                String left = stack.pop();
                String tempvar = "t" + tempCount ++;

                System.out.println("LDA " + left);

                switch (token) {
                    case "+": System.out.println("ADD " + right); break;
                    case "-": System.out.println("SUB " + right); break;
                    case "*": System.out.println("MUL " + right); break;
                    case "/": System.out.println("DIV " + right); break;
                }

                System.out.println("STR " + tempvar);
                stack.push(tempvar);

            }

        }


    }

}