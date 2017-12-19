#include <stdio.h>
using namespace std;

int main () {
   
   FILE * pFile;
   int curr;
   pFile = fopen("input.txt", "r");

   int sheet[16][16];

   for(int i = 0; i < 16; i++) {
      for(int j = 0; j < 16; j++) {
         fscanf(pFile, "%i", &sheet[i][j]);
      }
   }

   rewind(pFile);
   int checksum = 0;
   for(int i = 0; i < 16; i++) {
      int max = 0;
      int min = 10000;
      for(int j = 0; j < 16; j++) {
         if(sheet[i][j] > max) max = sheet[i][j];
         if(sheet[i][j] < min) min = sheet[i][j];
      }
      checksum += max - min;
   }

   printf("Part A Checksum: %d\n", checksum);
   
   checksum = 0;

   for(int i = 0; i < 16; i++) {
      bool found = false;
      for(int j = 0; j < 16; j++) {
         int c = sheet[i][j];
         for(int k = 0; k < 16; k++) {
            int c2 = sheet[i][k];
            if(k != j) {
               if(c % c2 == 0) {
                  checksum += c / c2;
                  found = true;
                  break;
               } else if(c2 % c == 0) {
                  checksum += c2 / c;
                  found = true;
                  break;
               }
            }
         }
         if(found) break;
      }
   }

   printf("Part B Checksum: %d\n", checksum);

   return 0;
}