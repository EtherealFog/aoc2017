
import java.util.*;
import java.io.*;
public class Day7 {
   public static void main(String[] args) throws Exception {
      Scanner input = new Scanner(new File("input.txt"));
      new Day7(input);
   }

   private Map<String, TowerNode> map;

   public Day7(Scanner input) {
      map = new HashMap<String, TowerNode>();
      while(input.hasNextLine()) {
         String line = input.nextLine();
         String[] args = line.split(" -> ");
         String name = args[0].substring(0, args[0].indexOf(" "));
         int weight = Integer.parseInt(args[0].substring(args[0].indexOf("(")+1, args[0].indexOf(")")));
         if(args.length > 1) {
            map.put(name, new TowerNode(weight, args[1].split(", ")));
         } else {
            map.put(name, new TowerNode(weight));
         }
      }
      //can only do one at a time
      //a();
      b();
   }

   public void b() {
      map.get("cqmvs").balance();
   }

   public void a() {
      Set<String> notTop = new HashSet<String>();
      for(String s: map.keySet()) {
         if(map.get(s).subNodes != null) {
            for(String s2: map.get(s).subNodes) {
               notTop.add(s2);
            }
         }
      }
      Set<String> top = map.keySet();
      top.removeAll(notTop);
      for(String s: top) {
         System.out.println("Top Tower is: " + s);
      }
   }

   public class TowerNode {
      public String[] subNodes;
      public int weight;

      public TowerNode(int weight) {
         this.weight = weight;
         subNodes = null;
      }

      public TowerNode(int weight, String... subNodes) {
         this.weight = weight;
         this.subNodes = subNodes;
      }

      public String toString() {
         String r = weight + " ";
         for(String s: subNodes) {
            r += s + ", ";
         }
         return r;
      }

      public int balance() {
         int sum = weight;
         if(subNodes != null) {
            int w1 = map.get(subNodes[0]).balance();
            for(int i = 1; i < subNodes.length; i++) {
               int w = map.get(subNodes[i]).balance();
               if(w != w1) {
                  int diff = w - w1;
                  System.out.println("Fixed weight is: " + (map.get(subNodes[0]).weight += w - w1));
                  w1 += diff;
               }
               sum += w;
            }
            sum += w1;
         }
         return sum;
      }
   }
}