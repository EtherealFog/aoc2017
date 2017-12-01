import java.io.*;
import java.util.*;
public class Day1 {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("input.txt"));
      String in = input.next();
      a(in);
      a8(in);
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

   public static void a8(String in) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      in.chars().forEach(i -> list.add(i - '0'));
      int sum = 0;
      for(int i = 0; i < list.size(); i++) {
         if(list.get(i) == list.get((i + 1) % list.size())) {
            sum += list.get(i);
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