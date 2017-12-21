
def a(lines):
   sum = 0
   for line in lines:
      words = [x.strip() for x in line.split()]
      unique = set()
      for word in words:
         unique.add(word)
      if(len(words) == len(unique)):
         sum += 1
   return sum

def b(lines):
   sum = 0
   for line in lines:
      words = [x.strip() for x in line.split()]
      sets = set()
      for word in words:
         wset = set()
         for c in word:
            wset.add(c)
         sets.add(frozenset(wset))
      if(len(sets) == len(words)):
         sum += 1
   return sum


with open("input.txt") as f:
   lines = f.readlines()
   print("Part A:", a(lines))
   print("Part B:", b(lines))

