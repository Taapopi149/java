import java.util.Scanner;

public class Lab03A {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter StartNo and EndNo: ");

        int startNo = scanner.nextInt();
        int endNo = scanner.nextInt();

        if (startNo < 0){
            System.out.println("startNo cannot be less than 0");
        } else if (endNo < 0){
            System.out.println("endNo cannot be less than 0");
        } else if (endNo > 9) {
            System.out.println("endNo cannot be greater than 9");
        } else if (startNo > endNo){
            System.out.println("startNo must NOT be greater than endNo");
        } else {

            int sumof = sumEven(endNo, startNo);
            System.err.println("sum of odd = " + sumof );
          
        }

    }
    
    public static int sumEven(int endNo, int startNo){
        int sum = 0;
        for (int i = startNo;i< endNo +1;i++ ){
            if(i%2!=0){
                sum = sum + i;
            }
        }

        return sum;
        //System.out.println(sum);
    }
}
