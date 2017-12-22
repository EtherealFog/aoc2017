import time
dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]

def a(lines):
   grid = dict()
   x = -12
   y = 12
   for line in lines:
      x = -12
      for c in line:
         tup = (x, y)
         grid[tup] = (c == '#')
         x += 1
      y -= 1
   x = 0
   y = 0
   dir = 0
   count = 0
   for i in range(10000):
      tup = (x, y)
      if tup not in grid:
         grid[tup] = False
      if(grid[tup]):
         dir = (dir + 1) % 4
      else:
         dir = (dir + 3) % 4
         count += 1
      x += dirs[dir][0]
      y += dirs[dir][1]
      grid[tup] = not grid[tup]
   return count

# {0==weak,  1==infected,  2==flagged,  3==clean}

def b(lines):
   grid = [[3]*399 for x in range(399)]
   x = 188
   y = 212
   for line in lines:
      x = 188
      for c in line:
         if c == '#':
            grid[x][y] = 1
         x += 1
      y -= 1
   x = 200
   y = 200
   dir = 0
   count = 0
   for i in range(10000000):
      c = grid[x][y]
      if c == 0:
         count += 1
      dir = (dir + c) % 4
      grid[x][y] = (c + 1) % 4
      x += dirs[dir][0]
      y += dirs[dir][1]
   return count
   

with open("input.txt") as f:
   lines = [x.strip() for x in f.readlines()]
   t = time.time()
   print("Part A:", a(lines))
   print("Part B:", b(lines))
   print("Done in:", time.time() - t)

   