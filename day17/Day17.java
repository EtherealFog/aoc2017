
import java.util.*;
import java.io.*;
public class Day17 {
   public static final int input = 329;
   public static void main(String[] args) {
      a();
      b();
   }


   public static void a() {
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(0);
      for(int i = 1; i < 2018; i++) {
         for(int j = 0; j < input % i; j++) {
            q.add(q.remove());
         }
         q.add(i);
      }
      System.out.println(q.peek());
   }

   public static void b() {
      Deque<Integer> q = new ArrayDeque<Integer>();
      q.add(0);
      for(int i = 0; i < 100; i++) System.out.print("_");
      System.out.println();
      for(int i = 1; i <= 50000000; i++) {
         if(i % 500000 == 0) System.out.print("#");
         for(int j = 0; j < input % i; j++) {
            q.offer(q.poll());
         }
         q.offer(i);
      }
      System.out.println();
      while(q.peek() != 0) q.add(q.remove());
      q.add(q.remove());
      System.out.println(q.peek());
   }
}