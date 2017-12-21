input = 265149
dirs = [[1, 0], [0, 1], [-1, 0], [0, -1]]

def a(input):
   x = 0
   y = 0
   n = 1
   r = 1
   while n < input:
      dx = dirs[(r-1)%4][0]
      dy = dirs[(r-1)%4][1]
      step = int((r+1)/2)
      for i in range(step):
         y += dy
         x += dx
         n += 1
         if(n == input):
            return abs(x) + abs(y)
      r += 1
   return -1

def b(input):
   vals = [[]]
   for i in range(11):
      vals.append([0 for x in range(11)])
   x = 5
   y = 5
   vals[x][y] = 1
   n = 1
   r = 1
   while n < input:
      dx = dirs[(r-1)%4][0]
      dy = dirs[(r-1)%4][1]
      step = int((r+1)/2)
      for i in range(step):
         x += dx
         y += dy
         n = get(vals, x, y)
         if(n > input):
            return n
         vals[x][y] = n
      r += 1
   return -1
         
def get(vals, x, y):
   sum = 0
   for i in range(x-1, x+2):
      for j in range(y-1, y+2):
         if(not(i==x and j==y)):
            sum += vals[i][j]
   return sum

print("Part A:", a(input))
print("Part B:", b(input))