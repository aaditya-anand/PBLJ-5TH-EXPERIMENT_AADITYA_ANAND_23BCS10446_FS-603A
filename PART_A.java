//Sum of Integers Using Autoboxing and Unboxing
import java.util.*;
public class PART_A{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int num = sc.nextInt();   
            list.add(num);           
        }
        int sum = 0;
        for (Integer ele : list) {
            sum += ele;           
        }
        System.out.println("sum: "+ sum);
        sc.close();
    }
}
