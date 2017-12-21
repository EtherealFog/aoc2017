
def a(mem):
   seen = set()
   tup = tuple(mem)
   count = 0
   while tup not in seen:
      seen.add(tup)
      index = 0
      max = 0
      for i in range(len(mem)):
         n = mem[i]
         if(n > max):
            max = n
            index = i
      mem[index] = 0
      index += 1
      while(max > 0):
         mem[index%len(mem)] += 1
         max -= 1
         index += 1
      count += 1
      tup = tuple(mem)
   return count


def b(mem):
   seen = []
   tup = tuple(mem)
   count = 0
   while tup not in seen:
      seen.append(tup)
      index = 0
      max = 0
      for i in range(len(mem)):
         n = mem[i]
         if(n > max):
            max = n
            index = i
      mem[index] = 0
      index += 1
      while(max > 0):
         mem[index%len(mem)] += 1
         max -= 1
         index += 1
      count += 1
      tup = tuple(mem)
   return count - seen.index(tup)

with open("input.txt") as f:
   nums = [int(x.strip()) for x in f.read().split("\t")]
   print("Part A:", a(nums))   

with open("input.txt") as f:
   nums = [int(x.strip()) for x in f.read().split("\t")]
   print("Part B:", b(nums))   
      