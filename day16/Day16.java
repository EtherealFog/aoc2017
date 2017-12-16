
import java.io.*;
import java.util.*;
public class Day16 {
   public static final int input1 = 0;
   public static final String input2 = "";
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      String[] moves = in.nextLine().split(",");
      Day16 d = new Day16();
      d.build(moves);
      //d.test();
      d.runA(moves);
      d.runB();
   }

   public Move[] ms;
   public int[] big;
   public void build(String[] moves) {
      ms = new Move[moves.length];
      for(int i = 0; i < moves.length; i++) {
         ms[i] = new Move(moves[i]);
      }
   }

   public void test() {

   }

   public void runA(String[] moves) {
      LinkedList<Character> line = new LinkedList<Character>();
      for(int i = 0; i < 16; i++) {
         line.add((char)('a' + i));
      }
      for(int x = 0; x < 2; x++) {
      for(String s: moves) {
         char move = s.charAt(0);
         String [] args = s.substring(1).split("/");
         if(move == 's') {
            for(int i = 0; i < Integer.parseInt(args[0]); i++) {
               line.addFirst(line.removeLast());
            }
         } else if (move == 'x') {
            swap(Integer.parseInt(args[0]), Integer.parseInt(args[1]), line);
         } else if (move == 'p') {
            swap(line.indexOf(args[0].charAt(0)), line.indexOf(args[1].charAt(0)), line);
         }
      }
      }
      for(char c: line) {
         System.out.print(c);
      }
      LinkedList<Character> line2 = new LinkedList<Character>();
      for(int i = 0; i < 16; i++) {
         line2.add((char)('a' + i));
      }
      System.out.println();
      big = new int[16];
      for(int i = 0; i < 16; i++) {
         big[i] = line.get(i) - 'a';
      }
   }

   public void swap(int a, int b, List<Character> list) {
      char t1 = list.get(Math.max(a, b));
      list.set(Math.max(a, b), list.get(Math.min(a, b)));
      list.set(Math.min(a, b), t1);
   }

   public void runB() {
      LinkedList<Character> line = new LinkedList<Character>();
      Map<String, String> memo = new HashMap<String, String>();
      for(int i = 0; i < 16; i++) {
         line.add((char)('a' + i));
      }
      String prev = stringify(line);
      System.out.println("This'll take a little while");
      for(int x = 0; x < 1000000000; x++) {
         if(memo.containsKey(prev)) {
            prev = memo.get(prev);
         } else {
            line = lineify(prev);
            for(Move m: ms) {
               m.op(line);
            }
            String next = stringify(line);
            memo.put(prev, next);
            prev = next;
         }
      }
      System.out.println();
      System.out.println(prev);
   }  

   private String stringify(List<Character> l) {
      String r = "";
      for(char c: l) {
         r += c;
      }
      return r;
   }

   private LinkedList<Character> lineify(String s) {
      LinkedList<Character> l = new LinkedList<Character>();
      for(int i = 0; i < s.length(); i++) {
         l.add(s.charAt(i));
      }
      return l;
   }

   private void swap(char[] line, int a1, int a2) {
      char temp =  line[a1];
      line[a1] = line[a2];
      line[a2] = temp;
   }

   private void swap(char[] line, char c1, char c2) {
      for(int i = 0; i < line.length; i++) {
         if(line[i] == c1) line[i] = c2;
         else if(line[i] == c2) line[i] = c1;
      }
   }
   
   public class Move {
      public char type;
      public int arg1, arg2;
      public char argc1, argc2;

      public Move(String s) {
         type = s.charAt(0);
         String [] args = s.substring(1).split("/");
         if(type == 's') {
            arg1 = Integer.parseInt(args[0]);
         } else if (type == 'x') {
            arg1 = Integer.parseInt(args[0]);
            arg2 = Integer.parseInt(args[1]);
         } else if (type == 'p') {
            argc1 = args[0].charAt(0);
            argc2 = args[1].charAt(0);
         }
      }

      public void op(LinkedList<Character> l) {
         if(type == 's') {
            for(int i = 0; i < arg1; i++) {
               l.addFirst(l.removeLast());
            }
         } else if(type == 'x') {
            swap(arg1, arg2, l);
         } else {
            swap(l.indexOf(argc1), l.indexOf(argc2), l);
         }
      }
   }
}