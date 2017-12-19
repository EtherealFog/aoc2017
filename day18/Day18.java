
import java.util.*;
import java.io.*;
public class Day18 {
   public static void main(String[] args) throws Exception{
      Scanner in = new Scanner(new File("input.txt"));
      List<String[]> lines = new ArrayList<String[]>();
      while(in.hasNextLine()) {
         lines.add(in.nextLine().split(" "));
      }
      //a(lines);
      b(lines);
   }

   public static Map<Character, Long> regA = new HashMap<>();
   public static Map<Character, Long> regB = new HashMap<>();
   
   public static Deque<Long> sent = new ArrayDeque<>();
   public static void a(List<String[]> args) {
      int index = 0;
      while(index < args.size()) {
         String[] l = args.get(index);
         switch(l[0]) {
            case "snd": snd('a', l[1]); break;
            case "rcv": rcv('a', l[1]); break;
            case "mod": mod('a', l[1].charAt(0), l[2]); break;
            case "mul": mul('a', l[1].charAt(0), l[2]); break;
            case "add": add('a', l[1].charAt(0), l[2]); break;
            case "set": set('a', l[1].charAt(0), l[2]); break;
         }
         if(l[0].equals("jgz") && get('a', l[1]) > 0) {
            index += get('a', l[2]);
         } else {
            index ++;
         }
      }
   }

   public static Deque<Long> a = new ArrayDeque<>();
   public static Deque<Long> b = new ArrayDeque<>();
   public static void b(List<String[]> args) {
      regA.clear();
      regB.clear();
      regA.put('p', 0l);
      regB.put('p', 1l);
      int ia = 0;
      int ib = 0;
      boolean goA = true;
      boolean goB = true;
      while(ia < args.size() && ib < args.size()) {
         String[] la = args.get(ia);
         String[] lb = args.get(ib);
         if(!goA || !goB) System.out.println("waiting");
         goA = run('a', la);
         if(goA) {
            if(la[0].equals("jgz") && get('a', la[1]) > 0) {
               ia += get('a', la[2]);
            } else {
               ia++;
            }
         }
         goB = run('b', lb);
         if(goB) {
            if(lb[0].equals("jgz") && get('b', lb[1]) > 0) {
               ib += get('b', lb[2]);
            } else {
               ib++;
            }
         }
         if(!goA && !goB) {
            break;
         }
      }
      System.out.println(regA);
      System.out.println(regB);
      System.out.println(asnds);
   }

   public static boolean run(char w, String[] l) {
      boolean rcv = true;
      switch(l[0]) {
         case "snd": snd(w, l[1]); break;
         case "rcv": rcv = rcv(w, l[1]); break;
         case "mod": mod(w, l[1].charAt(0), l[2]); break;
         case "mul": mul(w, l[1].charAt(0), l[2]); break;
         case "add": add(w, l[1].charAt(0), l[2]); break;
         case "set": set(w, l[1].charAt(0), l[2]); break;
      }
      return rcv;
   }
   public static int asnds = 0;
   public static void snd(char w, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      Deque<Long> d;
      if(w == 'a') d = b;
      else d = a;
      System.out.println("Added " + get(w, s) + " to queue " + (w == 'a'? 'b': 'a'));
      d.addLast(get(w, s));
      //System.out.println(d);
      if(w == 'b') asnds++;
   }

   public static boolean rcv(char w, String s) {
      Deque<Long> d;
      if(w == 'a') d = a;
      else d = b;
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(d.isEmpty()) {
         System.out.println("cannot receive at " + w);
         System.out.println(d);
         return false;
      } else {
         System.out.println("Queue " + w + " is " + d.size() + " long.");
         set(w, s.charAt(0), d.remove());
         return true;
      }
   }

   public static void mod(char w, char r, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey(r)) reg.put(r, 0l);
      reg.put(r, get(w, r) % get(w, s));
   }

   public static void set(char w, char r, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey((r))) reg.put(r, 0l);
      reg.put(r, get(w, s) + 0l);
   }

   public static void set(char w, char r, Long s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey((r))) reg.put(r, 0l);
      reg.put(r, s);
   }

   public static void mul(char w, char r, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey(r)) reg.put(r, 0l);
      reg.put(r, get(w, r) * get(w, s));
   }

   public static void add(char w, char r, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey(r)) reg.put(r, 0l);
      reg.put(r, get(w, r) + get(w, s));
   }

   public static long get(char w, char c) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      if(!reg.containsKey(c)) reg.put(c, 0l);
      return reg.get(c);
   }

   public static long get(char w, String s) {
      Map<Character, Long> reg;
      if(w == 'a') reg = regA;
      else reg = regB;
      char c = s.charAt(0);
      if(Character.isAlphabetic(c)) {
         if(!reg.containsKey(c)) reg.put(c, 0l);
         return reg.get(c);
      } else {
         return Integer.parseInt(s);
      }
   }

   public static void b() {

   }
}