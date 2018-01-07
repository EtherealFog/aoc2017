from collections import defaultdict
import time
# zero and one are each a tuple storing 
# (<val to write>, <direction>, <next state>)
def make_switch(zero, one):
   return lambda n : (zero if n == 0 else one)

state_map = {}
num_line = defaultdict(int)
start_state = ""
num_steps = 0

def build(lines):
   global start_state
   global state_map
   global num_steps
   start_state = lines[0].split()[3][0]
   num_steps = int(lines[1].split()[5])
   index = 3
   while(index < len(lines)):
      state = lines[index].split()[2][0]
      z1 = int(lines[index+2].split()[4][0])
      z2 = 1 if (lines[index+3].split()[6] == "right.") else -1
      z3 = lines[index+4].split()[4][0]
      index += 4
      o1 = int(lines[index+2].split()[4][0])
      o2 = 1 if (lines[index+3].split()[6] == "right.") else -1
      o3 = lines[index+4].split()[4][0]
      state_map[state] = make_switch((z1, z2, z3), (o1, o2, o3))
      index += 6

def a():
   index = 0
   state = start_state
   for i in range(num_steps):
      tup = state_map[state](num_line[index])
      num_line[index] = tup[0]
      index += tup[1]
      state = tup[2]
   chksum = 0
   for k in num_line:
      chksum += num_line[k]
   return chksum

with open("input.txt") as f:
   t = time.time()
   build(f.readlines())
   print("Checksum = " + str(a()))
   print("(took " + str(round(time.time()-t, 3)) + " seconds)")


