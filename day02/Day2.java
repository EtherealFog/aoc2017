
import java.util.*;
import java.io.*;
public class Day2 {
   public static void main(String[] args) throws FileNotFoundException{
      Scanner input = new Scanner(new File("input.txt"));
      //a(input);
      b(input);
   }

   public static void a(Scanner input) {
      int checksum = 0;
      while(input.hasNextLine()) {
         String line = input.nextLine();
         int min = Integer.MAX_VALUE;
         int max = Integer.MIN_VALUE;
         Scanner i2 = new Scanner(line);
         while(i2.hasNextInt()) {
            int curr = i2.nextInt();
            if(curr < min) {
               min = curr;
            }
            if(curr > max) {
               max = curr;
            }
         }
         checksum += max - min;
      }
      System.out.println(checksum);
   }

   public static void b(Scanner input) {
      int checksum = 0;
      while(input.hasNextLine()) {
         String line = input.nextLine();
         Scanner i2 = new Scanner(line);
         List<Integer> list = new LinkedList<Integer>();
         list.add(i2.nextInt());
         while(i2.hasNextInt()) {
            int curr = i2.nextInt();
            for(int i: list) {
               if(i % curr == 0) {
                  checksum += i/curr;
               } else if (curr % i == 0) {
                  checksum += curr/i;
               }
            }
            list.add(curr);
         }
      }
      System.out.println(checksum);
   }
}