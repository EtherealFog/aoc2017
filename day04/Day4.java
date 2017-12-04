
import java.util.*;
import java.io.*;
public class Day4 {
   public static void main(String[] args) throws Exception {
      Scanner input = new Scanner(new File("input.txt"));
      a(input);
      input.close();
      input = new Scanner(new File("input.txt"));
      b(input);
   }

   public static void a(Scanner input) {
      int sum = 0;
      while(input.hasNextLine()) {
         String line = input.nextLine();
         Scanner linescan = new Scanner(line);
         int num = 0;
         Set<String> dupes = new HashSet<String>();
         while(linescan.hasNext()) {
            dupes.add(linescan.next());
            num++;
         }
         if(num == dupes.size()) {
            sum++;
         }
      }
      System.out.println(sum);
   }

   public static void b(Scanner input) {
      int sum = 0;
      while(input.hasNextLine()) {
         String line = input.nextLine();
         Scanner linescan = new Scanner(line);
         int num = 0;
         Set<Set<Character>> dupes = new HashSet<Set<Character>>();
         while(linescan.hasNext()) {
            Set<Character> letters = new TreeSet<Character>();
            String word = linescan.next();
            for(char c: word.toCharArray()) {
               letters.add(c);
            }
            dupes.add(letters);
            num++;
         }
         if(num == dupes.size()) {
            sum++;
         }
      }
      System.out.println(sum);
   }
}