#include <stdio.h>
#include <math.h>
using namespace std;

int main()
{
   const int input = 265149;

   // 1 1 2 2 3 3 4 4 5 5 
   // 1 2 3 4 5 6 7 8 9 10

   int x = 0, y = 0;

   int dirs[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

   int n = 1;
   int r = 1;

   while(n < input) {
      int dx = dirs[(r-1)%4][0];
      int dy = dirs[(r-1)%4][1];
      int step = (r+1)/2;
      for(int i = 0; i < step && n < input; i++) {
         x += dx;
         y += dy;
         n++;
      }
      r++;
   }
   printf("Part A Distance: %d\n", (abs(x) + abs(y)));

   int grid[11][11];
   r = 1;
   n = 1;
   x = 5;
   y = 5;
   for(int i = 0; i < 11; i++) {
      for(int j = 0; j < 11; j++) grid[x][y] = 0;
   }
   grid[x][y] = 1;
   for(int i = 0; i < 11; i ++) {
      for(int j = 0; j < 11; j++) {
         printf("%12d", grid[i][j]);
      }
      printf("\n");
   }
   while(n < input) {
      int dx = dirs[(r-1)%4][0];
      int dy = dirs[(r-1)%4][1];
      int step = (r+1)/2;
      for(int i = 0; i < step && n < input; i++) {
         x += dx;
         y += dy;
         int sum = 0;
         for(int a = x-1; a <= x+1; a++) {
            for(int b = y-1; b <= y+1; b++) {
               printf("(%2d, %2d) ", a, b);
               sum += grid[a][b];
            }
            printf("\n");
         }
         grid[x][y] = sum;
         n = sum;
      }
      r++;
   }
   for(int i = 0; i < 11; i ++) {
      for(int j = 0; j < 11; j++) {
         printf("%12d", grid[i][j]);
      }
      printf("\n");
   }
   printf("Part B Sum: %d\n", n);

}
