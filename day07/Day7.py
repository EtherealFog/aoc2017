map = {}
weights = {}

def a(lines):
   #fjkfpm (69) -> kohxzh, liwvq, eqkio, xvoyybs
   for line in lines:
      halves = line.split(" -> ")
      firsth = halves[0].replace("(", "").replace(")", "").split()
      name = firsth[0]
      weight = int(firsth[1])
      weights[name] = weight
      if len(halves) > 1:
         links = halves[1].split(", ")
         map[name] = links
   children = set()
   parents = set().union(map.keys())
   for p in parents:
      if p in map:
         children = children.union(map[p])
   print(parents - children)

def weight(name):
   if name in map:
      sum = weights[name]
      for name2 in map[name]:
         sum += weight(name2)
      return sum
   else:
      return weights[name]

def b():
   


with open("input.txt") as f:
   lines = [x.strip() for x in f.readlines()]
   a(lines)
