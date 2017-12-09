
import java.util.*;
import java.io.*;
public class Day9 {
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      StringBuilder input = new StringBuilder(in.nextLine());
      new Day9(input);
   }

   public Day9(StringBuilder input) {
      int garbage = 0;
      int index = 0;
      // remove all escape chars ('!') and escaped chars
      while(index < input.length()) {
         char curr = input.charAt(index);
         if(curr == '!') {
            input.deleteCharAt(index);
            input.deleteCharAt(index);
         } else {
            index++;
         }
      }
      // remove (and, for part B, count) all the garbage
      index = 0;
      while(index < input.length()) {
         char curr = input.charAt(index);
         if(curr == '<') {
            while(input.charAt(index) != '>') {
               input.deleteCharAt(index);
               garbage++;
            }
            garbage--;
            input.deleteCharAt(index);
         }
         index++;
      }
      index = 0;
      System.out.println("Part A (Score): " + score(input));
      System.out.println("Part B (Trash): " + garbage);
   }

   // find the score of a stream, provided garbage and escape chars have been removed.
   public int score(StringBuilder input) {
      int sum = 0;
      int depth = 0;
      for(char c: input.toString().toCharArray()) {
         if(c == '{') {
            depth++;
         } else if(c == '}') {
            sum += depth;
            depth--;
         }
      }
      return sum;
   }
}