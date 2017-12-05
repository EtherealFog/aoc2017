
import java.util.*;
import java.io.*;
public class Day5 {
   public static void main(String[] args) throws Exception{
      Scanner input = new Scanner(new File("input.txt"));
      List<Integer> listA = new ArrayList<Integer>();
      while(input.hasNextLine()) {
         listA.add(Integer.parseInt(input.nextLine()));
      }
      List<Integer> listB = copy(listA);
      a(listA);
      b(listB);
   }

   public static void a(List<Integer> input) {
      int pos = 0;
      int count = 0;
      while(pos >= 0 && pos < input.size()) {
         pos += input.set(pos, input.get(pos) + 1);
         count++;
      }
      System.out.println(count);
   }

   public static void b(List<Integer> input) {
      int pos = 0;
      int count = 0;
      while(pos >= 0 && pos < input.size()) {
         int offset = input.get(pos);
         if(offset > 2) {
            offset--;
         } else {
            offset++;
         }
         pos += input.set(pos, offset);
         count++;
      }
      System.out.println(count);
   }

   public static List<Integer> copy(List<Integer> list) {
      List<Integer> result = new ArrayList<Integer>();
      for(int i: list) {
         result.add(i);
      }
      return result;
   }
}