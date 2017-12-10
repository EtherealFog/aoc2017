
import java.util.*;
import java.io.*;
public class Day10 {
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      String line = in.nextLine();
      new Day10(line);
   }

   public Day10(String line) {
      a(line);
      b(line);
   }

   public void a(String line) {
      String[] vals = line.split(",");
      List<Integer> nums = new ArrayList<Integer>();
      for(String s : vals) nums.add (Integer.parseInt(s));
      int[] string = new int[256];
      for(int i = 0; i < string.length; i++) string[i] = i;
      int skip = 0;
      int ind = 0;
      for(int num: nums) {
         reverse(string, ind, num);
         ind = (ind + num + skip) % string.length;
         skip++;
      }
      System.out.println("Part A Answer (product of first two elements): " + string[0]*string[1]);
   }

   public void b(String line) {
      List<Integer> nums = new LinkedList<Integer>();
      int[] a = new int[256];
      for(int i = 0; i < a.length; i++) a[i] = i;
      for(char c: line.toCharArray()) nums.add((int)c);
      nums.add(17); nums.add(31); nums.add(73); nums.add(47); nums.add(23);
      int skip = 0;
      int ind = 0;
      for(int r = 0; r < 64; r++) {
         for(int num: nums) {
            reverse(a, ind, num);
            ind = (ind + num + skip) % a.length;
            skip++;
         }
      }
      int[] dense = condense(a);
      System.out.println("Part B Answer (knot hash): " + result);
   }

   public static final String hex = "0123456789abcdef";
   public static String hex(int[] bytes) {
      String result = "";
      for(int i = 0; i < bytes.length; i++) {
         result += hex.charAt(dense[i]/16);
         result += hex.charAt(dense[i]%16);
      }
      return result;
   }

   public static int[] condense(int[] a) {
      int[] r = new int[16];
      for(int i = 0; i < a.length; i+= 16) {
         int p = a[i];
         for(int j = 1; j < 16; j++) {
            p = p ^ a[i+j];
         }
         r[i/16] = p;
      }
      return r;
   }

   public static void reverse(int[] a, int index, int length) {
      for(int i = 0; i < length/2; i++) {
         int ind1 = (index + i)%a.length;
         int temp = a[ind1];
         int ind = (index + length - i - 1) % a.length;
         a[ind1] = a[ind];
         a[ind] = temp;
      }
   }
}