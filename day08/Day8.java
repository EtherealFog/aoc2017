
import java.util.*;
import java.io.*;
public class Day8 {
   public static void main(String[] args) throws Exception{
      Scanner input = new Scanner(new File("input.txt"));
      List<String[]> code = new LinkedList<String[]>();
      while(input.hasNextLine()) {
         code.add(input.nextLine().split(" "));
      }
      runA(code);
      runB(code);
   }

   public static Map<String, Integer> register = new HashMap<String, Integer>();
   public static int max = Integer.MIN_VALUE;

   public static void runA(List<String[]> code) {
      run(code);
      max = Integer.MIN_VALUE;
      for(String key: register.keySet()) {
         get(key);
      }
      System.out.println("Max Value (part A): " + max);
   }
   //0  1   2  3  4 5  6
   //y inc 497 if n <= 3
   public static void runB(List<String[]> code) {
      register.clear();
      max = Integer.MIN_VALUE;
      run(code);
      System.out.println("Max Value (part B): " + max);
   }

   public static void run(List<String[]> code) {
      for(String[] args: code) {
         if(cond(args[4], args[5], Integer.parseInt(args[6]))) {
            if(args[1].equals("inc")) {
               inc(args[0], Integer.parseInt(args[2]));
            } else {
               dec(args[0], Integer.parseInt(args[2]));
            }
         }
      }
   }

   public static boolean cond(String reg, String comp, int val) {
      switch(comp) {
         case "<": return get(reg) < val;
         case ">": return get(reg) > val;
         case "==": return get(reg) == val;
         case "!=": return get(reg) != val;
         case "<=": return get(reg) <= val;
         case ">=": return get(reg) >= val;
         default: System.out.println("issue"); return false;
      }
   }

   public static void dec(String reg, int val) {
      inst(reg);
      register.put(reg, get(reg) - val);
   }

   public static int get(String reg) {
      inst(reg);
      int r = register.get(reg);
      if(r > max) {
         max = r;
      }
      return r;
   }

   public static void inc(String reg, int val) {
      inst(reg);
      register.put(reg, get(reg) + val);
   }

   public static void inst(String reg) {
      if(!register.containsKey(reg)) {
         register.put(reg, 0);
      }
   }
}