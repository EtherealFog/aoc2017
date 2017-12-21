def checksumA(line):
   sum = 0
   length = len(line) - 1
   for i in range(length):
      if(line[i] == line[(i+1)%length]):
         sum += int(line[i])
   return sum

def checksumB(line):
   sum = 0
   length = len(line) -1
   for i in range(length):
      if(line[i] == line[int((i+length/2)%length)]):
         sum += int(line[i])
   return sum

with open("input.txt") as f:
   line = f.read()
   print("Part A: ", checksumA(line))
   print("Part B: ", checksumB(line))
