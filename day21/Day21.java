import java.util.*;
import java.io.*;

public class Day21 {
   public static final String[] start = {".#.", "..#", "###"};
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      build(in);
      //test();
      run();
   }

   public static void build(Scanner in) {
      rules = new HashMap<>();
      while(in.hasNextLine()) {
         String[] gs = in.nextLine().split(" => ");
         String[] s = gs[0].split("/");
         char[][] g = new char[s[0].length()][s[0].length()];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               g[i][j] = s[i].charAt(j);
            }
         }
         Grid val = new Grid(gs[1].split("/"));

         for(int i = 0; i < 4; i++) {
            rules.put(new Grid(g), val);
            rules.put(new Grid(Grid.flipH(g)), val);
            rules.put(new Grid(Grid.flipV(g)), val);
            g = Grid.rot(g);
         }
         
      }
   }

   public static void test() {
      rules = new HashMap<>();
      String s1 = "../.# => ##./#../...";
      String s2 = ".#./..#/### => #..#/..../..../#..#";
      String[] ss = {s1, s2};
      for(String l: ss) {
         String[] gs = l.split(" => ");
         String[] s = gs[0].split("/");
         char[][] g = new char[s[0].length()][s[0].length()];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               g[i][j] = s[i].charAt(j);
            }
         }
         Grid val = new Grid(gs[1].split("/"));
         for(int i = 0; i < 4; i++) {
            rules.put(new Grid(g), val);
            rules.put(new Grid(Grid.flipH(g)), val);
            rules.put(new Grid(Grid.flipV(g)), val);
            //System.out.println(Arrays.deepToString(g));
            g = Grid.rot(g);
         }
      }
      for(Grid g: rules.keySet()) {
         System.out.println(g);
      }

   }

   public static void run() {
      Grid s = new Grid(start);
      //System.out.println(s);      
      for(int i = 0; i < 18; i++) {
         s.inc();
         //System.out.println(s);
         if(i == 4) System.out.println("After 5 Iterations (Part A):  " + s.count());
      }
      System.out.println("After 18 Iterations (Part B): " + s.count());
   }

   public static Map<Grid, Grid> rules;

   public static class Grid {
      public char[][] g;

      public Grid(char[][] g) {
         this.g = g;
      }

      public Grid(String[] s) {
         g = new char[s[0].length()][s[0].length()];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               g[i][j] = s[i].charAt(j);
            }
         }
      }

      public boolean equals(Object o) {
         return Arrays.deepEquals(g, ((Grid)o).g);
      }

      public static char[][] rot(char[][] g) {
         char[][] r = new char[g.length][g.length];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               r[i][j] = g[g.length-1-j][i];
            }
         }
         return r;
      }

      public static char[][] flipH(char[][] g) {
         char[][] r = new char[g.length][g.length];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               r[i][j] = g[i][g.length-j-1];
            }
         }
         return r;
      }

      public static char[][] flipV(char[][] g) {
         char[][] r = new char[g.length][g.length];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               r[i][j] = g[g.length-1-i][j];
            }
         }
         return r;
      }

      public int hashCode() {
         return Arrays.deepHashCode(g);
      }

      public void inc() {
         int n;
         int m;
         if(g.length % 2 == 0) {
            n = g.length / 2;
            m = 2;
         } else {
            n = g.length / 3;
            m = 3;
         }
         char[][] ng = new char[(m+1)*n][(m+1)*n];
         for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
               char[][] sub = new char[m][m];
               for(int x = 0; x < m; x++) for(int y = 0; y < m; y++) sub[x][y] = g[i*m + x][j*m + y];
               Grid newSub = rules.get(new Grid(sub));
               for(int x = 0; x < m+1; x++) for(int y = 0; y < m+1; y++) ng[i*(m+1)+x][j*(m+1) + y] = newSub.g[x][y];
            }
         }
         g = ng;
      }

      public char[][] mult(int n) {
         char[][] newg = new char[g.length*n][g.length*n];
         for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
               for(int i = 0; i < g.length; i++) {
                  for(int j = 0; j < g.length; j++) {
                     newg[x*g.length + i][y*g.length + j] = g[i][j];
                  }
               }
            }
         }
         return newg;
      }

      public Grid copy() {
         char[][] rg = new char[g.length][g.length];
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               rg[i][j] = g[i][j];
            }
         }
         return new Grid(rg);
      }

      public int count() {
         int sum = 0;
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               if(g[i][j] == '#') {
                  sum++;
               }
            }
         }
         return sum;
      }

      public String toString() {
         String r = "";
         for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g.length; j++) {
               r += g[i][j];
            }
            r += "\n";
         }
         return r;
      }
   }
}