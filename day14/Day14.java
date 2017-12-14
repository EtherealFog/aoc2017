
import java.util.*;
import java.io.*;
public class Day14 {
   public static final String input2 = "ugkiagan";
   public static void main(String[] args) {
      Day14 d = new Day14();
      d.build();
      d.runA();
      //d.printGrid();
      d.runB();
   }

   private boolean[][] grid;
   public void build() {
      grid = new boolean[128][128];
      for(int i = 0; i < grid.length; i++) {
         grid[i] = row(i);
      }
   }

   public boolean[] row(int n) {
      String input = input2 + "-" + n;
      int[] hash = knotHash(input);
      boolean[] result = new boolean[128];
      for(int i = 0; i < hash.length; i++) {
         int num = hash[i];
         for(int j = 0; j < 8; j++) {
            int bit = num % 2;
            num /= 2;
            result[i * 8 + 7 - j] = bit == 1;
         }
      }
      return result;
   }

   public void runA() {
      int sum = 0;
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid[i].length; j++) {
            if(grid[i][j]) sum++;
         }
      }
      System.out.println("Number of Filled Squares (Part A):\t" + sum);
   }

   public void runB() {
      int sum = 0;
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid[i].length; j++) {
            if(remove(getGroup(i, j))) sum++;
         }
      }
      System.out.println("Number of Individual Groups (Part B):\t" + sum);
   }

   public boolean remove(Set<Point> group) {
      for(Point p: group) {
         grid[p.x][p.y] = false;
      }
      return group.size() > 0;
   }

   public Set<Point> getGroup(int x, int y) {
      Set<Point> result = new HashSet<Point>();
      getGroup(new Point(x, y), result);
      return result;
   }

   public void getGroup(Point p, Set<Point> set) {
      if(set.contains(p)) return;
      if(get(p)) {
         set.add(p);
         getGroup(new Point(p.x, p.y+1), set);
         getGroup(new Point(p.x, p.y-1), set);
         getGroup(new Point(p.x+1, p.y), set);
         getGroup(new Point(p.x-1, p.y), set);
      } 
   }

   public boolean get(Point p) {
      return !(p.x < 0 || p.y < 0 || p.x > grid.length-1 || p.y > grid[0].length-1) && grid[p.x][p.y];
   }

   public class Point {
      public int x, y;

      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }

      public Point() {
         this(0, 0);
      }

      public String toString() {
         return "(" + x + ", " + y + ")";
      }

      public boolean equals(Object other) {
         Point p = (Point) other;
         return x == p.x && y == p.y;
      }

      public int hashCode() {
         return Integer.hashCode(x) * 37 + Integer.hashCode(y);
      }
   }

   public void printGrid() {
      for(boolean[] bb: grid) {
         printRow(bb);
         System.out.println();
      }
   }

   public void printRow(boolean[] row) {
      for(boolean b: row) {
         if(b) {
            System.out.print("#");
         } else {
            System.out.print(".");
         }
      }
   }

   public int[] knotHash(String line) {
      // list for input vals
      List<Integer> nums = new LinkedList<Integer>();
      // string array, all ints in [0, 255]
      int[] a = new int[256];
      for(int i = 0; i < a.length; i++) a[i] = i;
      // for input: go through each character value to use as a 'length'
      for(char c: line.toCharArray()) nums.add((int)c);
      // add all these arbitrary values designated in the problem
      nums.add(17); nums.add(31); nums.add(73); nums.add(47); nums.add(23);
      // initialize skip and index vars
      int skip = 0;
      int ind = 0;
      // basically do part A 64 times in a row, without resetting skip or index vals
      for(int r = 0; r < 64; r++) {
         for(int num: nums) {
            reverse(a, ind, num);
            // wrap index around
            ind = (ind + num + skip) % a.length;
            skip++;
         }
      }
      // XOR each of the 16 chunks of 16 vals together into one 16-length array
      int[] dense = condense(a);
      // make a string out of the hexadecimal representations of all the vals
      return dense;
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