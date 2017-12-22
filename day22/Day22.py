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

ord = {'c':'w', 'w':'i', 'i':'f', 'f':'c'}

def b(lines):
   grid = dict()
   x = -12
   y = 12
   for line in lines:
      x = -12
      for c in line:
         tup = (x, y)
         if c == '#':
            grid[tup] = 'i'
         else:
            grid[tup] = 'c'
         x += 1
      y -= 1
   x = 0
   y = 0
   dir = 0
   count = 0
   for i in range(10000000):
      tup = (x, y)
      if tup not in grid:
         grid[tup] = 'c'
      c = grid[tup]
      if c == 'c':
         dir = (dir + 3) % 4
      elif c == 'w':
         count += 1
      elif c == 'i':
         dir = (dir + 1) % 4
      elif c == 'f':
         dir = (dir + 2) % 4
      x += dirs[dir][0]
      y += dirs[dir][1]
      grid[tup] = ord[c]
   return count
   

with open("input.txt") as f:
   lines = [x.strip() for x in f.readlines()]
   print("Part A:", a(lines))
   print("Part B:", b(lines))

   