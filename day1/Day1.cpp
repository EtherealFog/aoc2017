#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main (int argc, char ** argv) {
   ifstream inFile;
   inFile.open("input.txt");
   std::string s;
   inFile >> s;
   int sum = 0;
   for(int i = 0; i < s.size(); i++) {
      if(s[i] == s[(i+s.size()/2)%s.size()]) {
         sum += s[i] - '0';
      }
   }
   cout << sum << endl;
}