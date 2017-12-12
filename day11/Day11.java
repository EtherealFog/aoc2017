
import java.util.*;
import java.io.*;
public class Day11 {
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      Scanner console = new Scanner(System.in);
      String[] steps = in.nextLine().split(",");
      Day11 a = new Day11();
      a.run(steps);
   }

   public  void run(String[] steps) {
      HexPoint p = new HexPoint();
      int max = 0;
      for(String s: steps) {
         move(p, s);
         int d = dist(p);
         if(d > max) {
            max = d;
         }
      }
      System.out.println("Part A (Final Distance): " + dist(p));
      System.out.println("Part B (Max Distance): " + max);
   }

   public int dist(HexPoint p) {
      return (Math.abs(p.x) + Math.abs(p.y) + Math.abs(p.z)) / 2;
   }

   public void move(HexPoint p, String dir) {
      switch(dir) {
         case "n": p.y++; p.z--; return;
         case "ne": p.x++; p.z--; return;
         case "se": p.x++; p.y--; return;
         case "s": p.y--; p.z++; return;
         case "sw": p.x--; p.z++; return;
         case "nw": p.x--; p.y++; return;
      }
   }

   public class HexPoint {
      public int x, y, z;
   }
}