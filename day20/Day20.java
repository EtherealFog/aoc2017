
import java.util.*;
import java.io.*;
public class Day20 {
   public static void main(String[] args) throws Exception{
      Scanner in = new Scanner(new File("input.txt"));
      build(in);
      List<Particle> copy = copy(particles);
      runA();
      particles = copy;
      runB();
   }


   public static List<Particle> particles = new ArrayList<Particle>();

   public static void build(Scanner in) {
      int count = 0;
      while(in.hasNextLine()) {
         String[] vecs = in.nextLine().split("(>*,* *[pva]=<*)|>");
         //System.out.println(Arrays.toString(vecs));
         int[][] avp = new int[3][3];
         for(int i = 0; i < 3; i++) {
            String[] vec = vecs[3-i].split(",");
            for(int j = 0; j < 3; j++) {
               avp[i][j] = Integer.parseInt(vec[j]);
            }
         }
         particles.add(new Particle(avp, count));
         count++;
      }
   }

   public static void runA() {
      Scanner c = new Scanner(System.in);
      Particle p = null;
      for(int i = 0; i < 500; i++) {
         p = particles.get(0);
         for(Particle p2: particles) {
            if(p2.dist() < p.dist()) p = p2;
         }
         for(Particle p2: particles) p2.step();
      }
      System.out.println("Closest (Part A):\t\t" + p.id);
   }

   public static void runB() {
      Scanner c = new Scanner(System.in);
      for(int i = 0; i < 50; i++) {
         int colls = 0;
         for(int j = 0; j < particles.size(); j++) {
            Particle p = particles.get(j);
            for(Particle p2: collisions(p)) {
               particles.remove(p2);
               colls++;
            }
         }
         for(Particle p: particles) {
            p.step();
         }
      }
      System.out.println("Particles Remaining (Part B):\t" + particles.size());
   }

   public static List<Particle> copy(List<Particle> ps) {
      List<Particle> r = new ArrayList<Particle>();
      for(Particle p: ps) {
         int[][] avp = new int[3][3];
         for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
               avp[i][j] = p.avp[i][j];
            }
         }
         r.add(new Particle(avp, p.id));
      }
      return r;
   }

   public static List<Particle> collisions(Particle p) {
      List<Particle> r = new ArrayList<Particle>();
      //System.out.println("checking " + p);
      for(Particle p2: particles) {
         if(p2 != p && p.coll(p2)) {
            r.add(p2);
            //System.out.println(p2);
         }
      }
      if(r.size() > 0) r.add(p);
      return r;
   }

   public static class Particle {
      public int[][] avp;
      public int id;

      public Particle(int[][] avp, int id) {
         this.avp = avp;
         this.id = id;
      }

      public void step() {
         for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
               avp[i][j] += avp[i-1][j];
            }
         }
      }

      public int dist() {
         int sum = 0;
         for(int i = 0; i < 3; i++) {
            sum += Math.abs(avp[2][i]);
         }
         return sum;
      }

      public String toString() {
         return "Particle #" + id + ": <" + avp[2][0] + ", " + avp[2][1] + ", " + avp[2][2] + ">";
      }

      public boolean coll(Particle o) {
         return avp[2][0] == o.avp[2][0] && avp[2][1] == o.avp[2][1] && avp[2][2] == o.avp[2][2]; 
      }
   }
}