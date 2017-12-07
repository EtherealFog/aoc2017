
import java.util.*;
import java.io.*;
public class Day6 {
   public static void main(String[] args) throws Exception{
      Scanner input = new Scanner(new File("input.txt"));
      int[] mem = new int[16];
      //int[] memB = Arrays.copyOf(mem, mem.length);
      for(int i = 0; i < 16; i++) {
         mem[i] = input.nextInt();
      }
      //a(mem);
      b(mem);
   }

   public static void a(int[] mem) {
      Set<Integer> configs = new HashSet<Integer>();
      while(!configs.contains(Arrays.hashCode(mem))) {
         configs.add(Arrays.hashCode(mem));
         // find largest
         int max = 0;
         int index = 0;
         for(int i = 0; i < mem.length; i++) {
            if(mem[i] > max) {
               max = mem[i];
               index = i;
            }
         }
         mem[index] = 0;
         index++;
         // redistribute
         while(max > 0) {
            mem[index%16]++;
            max--;
            index++;
         }
      }
      System.out.println(configs.size());
   }

   public static void b(int[] mem) {
      Set<Integer> configs = new HashSet<Integer>();
      List<Integer> steps = new LinkedList<Integer>();
      while(!configs.contains(Arrays.hashCode(mem))) {
         configs.add(Arrays.hashCode(mem));
         steps.add(Arrays.hashCode(mem));
         // find largest
         int max = 0;
         int index = 0;
         for(int i = 0; i < mem.length; i++) {
            if(mem[i] > max) {
               max = mem[i];
               index = i;
            }
         }
         mem[index] = 0;
         index++;
         // redistribute
         while(max > 0) {
            mem[index%16]++;
            max--;
            index++;
         }
      }
      System.out.println(steps.size() - steps.indexOf(Arrays.hashCode(mem)));
   }
}