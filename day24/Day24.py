
max_strength = 0

def a(lines):
   components = []
   for line in lines:
      l = line.split("/")
      n1 = int(l[0])
      n2 = int(l[1])
      ns = (min(n1, n2), max(n1, n2))
      components.append(ns)
   components.sort()
   bridge_length(components)
   return bridge(components)

def bridge(components, port=0, index=0, used=[]):
   found = False
   strength = 0
   for i in range(index, len(components)):
      c = components[i]
      if(c not in used):
         if c[0] == port:
            found = True
            used.append(c)
            strength = max(strength, bridge(components, c[1], 0, used))
            used.remove(c)
         elif c[1] == port:
            found = True
            used.append(c)
            strength = max(strength, bridge(components, c[0], 0, used))
            used.remove(c)
   if not found:
      s = 0
      for c in used:
         s += c[0] + c[1]
      strength = max(s, strength)
   return strength

length = 0
len_max = 0
def bridge_length(components, port=0, index=0, used=[]):
   found = False
   global length
   global len_max
   for i in range(index, len(components)):
      c = components[i]
      if(c not in used):
         if c[0] == port:
            found = True
            used.append(c)
            length = max(length, bridge_length(components, c[1], 0, used))
            used.remove(c)
         elif c[1] == port:
            found = True
            used.append(c)
            length = max(length, bridge_length(components, c[0], 0, used))
            used.remove(c)
   if not found:
      s = 0
      for c in used:
         s += c[0] + c[1]
      if(len(used) >= length):
         len_max = max(s, len_max)
      length = max(len(used), length)
   return length

with open("input.txt") as f:
   lines = f.readlines()
   print("Part A:", a(lines)) 
   print("Part B:", len_max)
   
