import java.io.*;
import java.util.*;
public class Day1 {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("input.txt"));
      String in = input.next();
      a(in);
      b(in);
   }

   public static void a(String in) {
      int sum = 0;
      for(int i = 0; i < in.length(); i++) {
         if(in.charAt(i) == in.charAt((i + 1)%in.length())) {
            sum += Integer.parseInt(in.substring(i, i+1));
         }
      }
      System.out.println(sum);
   }

   public static void b(String in) {
      int sum = 0;
      for(int i = 0; i < in.length(); i++) {
         if(in.charAt(i) == in.charAt((i + in.length()/2)%in.length())) {
            sum += Integer.parseInt(in.substring(i, i+1));
         }
      }
      System.out.println(sum);
   }

}