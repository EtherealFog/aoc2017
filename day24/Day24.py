import time
# global variables
strength_max = 0
length_max = 0
length_max_strength = 0
components = set()

# parse the set of components from the lines from the input file
def build(lines):
   global components
   for line in lines:
      l = line.split("/")
      n1 = int(l[0])
      n2 = int(l[1])
      ns = (n1, n2)
      components.add(ns)

# goes through
def bridge(port=0, used=set()):
   found = False
   global strength_max
   global length_max
   global length_max_strength
   global components
   # only consider components that have not already been used
   for c in (components - used):
      if c[0] == port:
         found = True
         used.add(c)
         bridge(c[1], used)
         used.remove(c)
      elif c[1] == port:
         found = True
         used.add(c)
         bridge(c[0], used)
         used.remove(c)
   if not found: # i.e. no more possible additions to the bridge
      # add up strength of components used to make this bridge
      s = 0
      for c in used:
         s += c[0] + c[1]
      # reassign strength_max if new maximum strength has been found
      strength_max = max(s, strength_max)
      # reassign length_strength_max if new maximum strength has been found for the current max length
      if(len(used) >= length_max):
         length_max_strength = max(s, length_max_strength)
      length_max = max(len(used), length_max)
    
with open("input.txt") as f:
   t = time.time()
   build(f.readlines())
   bridge()
   print("Took " + str(round(time.time()-t, 3)) + " seconds to find:")
   print("Part A: " + str(strength_max)) 
   print("Part B: " + str(length_max_strength))
   
