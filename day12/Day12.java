
import java.util.*;
import java.io.*;
public class Day12 {
   public static void main(String[] args) throws Exception{
      Scanner in = new Scanner(new File("input.txt"));
      Day12 d = new Day12();
      d.build(in);
      d.run();
   }

   public Map<Integer, Node> nodes;

   public void build(Scanner in) {
      nodes = new TreeMap<Integer, Node>();
      while(in.hasNextLine()) {
         String l = in.nextLine();
         int n = Integer.parseInt(l.substring(0, l.indexOf(" ")));
         String[] conns = l.substring(l.indexOf(">") + 2).split(", ");
         if(!nodes.containsKey(n)) {
            nodes.put(n, new Node(n));
         }
         Node node1 = nodes.get(n);
         for(String s: conns) {
            int nn = Integer.parseInt(s);
            if(!nodes.containsKey(nn)) {
               nodes.put(nn, new Node(nn));
            }
            Node node2 = nodes.get(nn);
            node1.add(node2);
         }
      }
   }

   public Set<Set<Node>> groups;

   public void numGroups() {
      groups = new HashSet<Set<Node>>();
      for(int n: nodes.keySet()) {
         Node node = nodes.get(n);
         if(!grouped(node)) {
            Set<Node> newGroup = new HashSet<Node>();
            node.connections(newGroup);
            groups.add(newGroup);
         }
      }
      System.out.println("Number of Groups:\t\t" + groups.size());
   }

   private boolean grouped(Node n) {
      for(Set<Node> group: groups) {
         if(group.contains(n)) return true;
      }
      return false;
   }

   public void run() {
      // Part A
      System.out.println("Connections in Node-0 Group:\t" + nodes.get(0).connections(new HashSet<Node>()));
      // Part B
      numGroups();
   }

   public class Node {
      public int n;
      public List<Node> pipes;
      public Node(int n) {
         pipes = new ArrayList<Node>();
         this.n = n;
      }

      public String toString() {
         return "" + n;
      }

      public void add(Node n) {
         if(!pipes.contains(n)) {
            pipes.add(n);
            n.add(this);
         }
      }

      public int connections(Set<Node> group) {
         group.add(this);
         int sum = 1;
         for(Node n: pipes) {
            if(!group.contains(n)) {
               sum += n.connections(group);
            }
         }
         return sum;
      }
   }
}