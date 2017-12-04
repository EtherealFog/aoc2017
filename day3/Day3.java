
import java.util.*;
import java.io.*;
public class Day3 {
   public static void main(String[] args) {
      int input = 265149;
      a(input);
      b(input);
   }

   public static void a(int input) {
      int r = (int)Math.round(Math.sqrt(input));
      int diff = input - r * r;
      int x = 0;
      int y = 0;
      if(r % 2 == 0) {
         x = -r/2 + 1;
         y = r/2;
         if(diff < 0) {
            x -= diff;
         } else {
            y -= diff;
         }
      } else {
         x = r/2;
         y = -r/2;
         if(diff < 0) {
            x += diff;
         } else {
            y += diff;
         }
      }
      System.out.println("x: " + x + " y: " + y);
      System.out.println((Math.abs(x) + Math.abs(y)));
   }

   public static void b(int input) {
      int[][] vals = new int[17][17];
      int[][] dirs = {new int[] {1, 0},
                      new int[] {0, 1},
                      new int[] {-1, 0},
                      new int[] {0, -1}};
      vals[9][9] = 1;
      int x = 9;
      int y = 9;
      int step = 1;
      while(vals[x][y] < input) {
         int dx = dirs[(step-1)%4][0];
         int dy = dirs[(step-1)%4][1];
         for(int i = 0; i < (step + 1) / 2; i++) {
            x += dx;
            y += dy;
            put(vals, x, y);
            if(vals[x][y] > input) {
               System.out.println(vals[x][y]);
               //print(vals);
               break;
            }
         }
         step++;
      }
   }

   private static void put(int[][] vals, int x, int y) {
      vals[x][y] = vals[x][y-1] + vals[x][y+1] + vals[x+1][y] + vals[x-1][y] + vals[x+1][y+1] + vals[x+1][y-1] + vals[x-1][y-1] + vals[x-1][y+1];
   }

   private static void print(int[][] vals) {
      for(int i = 0; i < vals.length; i++) {
         for(int j = 0; j < vals[i].length; j++) {
            System.out.print(vals[i][j] + "\t");
         }
         System.out.println();
      }
   }
}