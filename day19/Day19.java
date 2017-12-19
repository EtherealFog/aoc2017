
import java.util.*;
import java.io.*;
public class Day19 {
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      List<String> lines = new ArrayList<String>();
      while(in.hasNextLine()) lines.add(in.nextLine());
      // build character array from input
      char[][] grid = new char[lines.size()][lines.get(0).length()];
      for(int i = 0; i < lines.size(); i++) {
         String l = lines.get(i);
         for(int j = 0; j < l.length(); j++) {
            grid[i][j] = l.charAt(j);
         }
      }
      run(grid);
   }

   public static void run(char[][] grid) {
      //directions:     down    right     up     left
      int[][] dirs = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
      int r = 0, c = 0;
      // find starting column
      for(int i = 0; i < grid[0].length; i++) {
         if(grid[0][i] == '|'){
            c = i;
            break;
         } 
      }
      // keep track of current direction of travel
      int[] dir = dirs[0];
      // found the end yet?
      boolean found = false;
      // track path
      StringBuilder path = new StringBuilder();
      // track number of steps
      int step = 0;
      while(!found) {
         step++;
         // move in the current direction
         r += dir[0];
         c += dir[1];
         char next = grid[r][c];
         if(next == '+') { // change direction
            for(int i = 0; i < dirs.length; i++) {
               if(!(dirs[i][0] == -dir[0] || dirs[i][1] == -dir[1])) { // don't go backwards
                  // go in the direction where there's either a letter or a '|' or '-'
                  if(grid[r + dirs[i][0]][c + dirs[i][1]] != ' ') { 
                     dir = dirs[i];
                     break;
                  }
               }
            }
         } else if (Character.isAlphabetic(next)) { // add letter to path
            path.append(next);
         } else if (next == ' ') { // hit the end
            found = true;
         } // if next == '|' or next == '-' direction doesn't need to change; do nothing.
      }
      System.out.println("Path (Part A):  " + path);
      System.out.println("Steps (Part B): " + step);
   }
}