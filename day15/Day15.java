
import java.util.*;
import java.io.*;
public class Day15 {
   public static final int input1 = 699; // seed for Generator A
   public static final int input2 = 124; // seed for Generator B
   public static final int[] mult = {16807, 48271}; // multipliers for A and B each cycle
   public static final int[] mod = {4, 8}; // divisors for part B
   public static void main(String[] args) throws Exception {
      Day15 d = new Day15();
      //d.test();
      d.build();
      d.runA();
      //d.test();
      d.build();
      d.runB();
   }

   // holds the current values of the two generators
   private long[] gen;
   
   // (re)sets 'gen' to the two seed values for the generators
   public void build() {
      gen = new long[2];
      gen[0] = input1;
      gen[1] = input2;
   }

   // (re)sets 'gen' to the two test case seed values
   public void test() {
      gen = new long[2];
      gen[0] = 65;
      gen[1] = 8921;
   }

   // runs part A
   public void runA() {
      int sum = 0;
      for(int i = 0; i < 40000000; i++) {
         if(stepA()) sum++;
      }
      System.out.println("Part A Result: " + sum);
   }

   // performs one step on each generator, returning 'true' if the 16-bit truncated results are equal
   private boolean stepA() {
      for(int i = 0; i < 2; i++) gen[i] = (gen[i] * mult[i]) % Integer.MAX_VALUE;
      // casting to short values truncates all but the lowest 16 bits
      return (short)gen[0] == (short)gen[1];
   }

   // runs part B
   public void runB() {
      int sum = 0;
      for(int i = 0; i < 5000000; i++) {
         if(stepB()) sum++;
      }
      System.out.println("Part B Result: " + sum);
   }

   // steps each generator until it reaches a value evenly divisible by its value stored in 'mod'.
   // returns 'true' if the 16-bit truncated results are equal.
   private boolean stepB() {
      for(int i = 0; i < 2; i++) { // perform the following on each generator value in 'gen'
         // increment once
         gen[i] = (gen[i] * mult[i]) % Integer.MAX_VALUE;
         // if gen[i] isn't evenly divisible by mod[i], then keep incrementing until it is.
         while(gen[i] % mod[i] != 0) gen[i] = (gen[i] * mult[i]) % Integer.MAX_VALUE;
      }
      // casting to short values truncates all but the lowest 16 bits
      return (short)gen[0] == (short)gen[1];
   }
}