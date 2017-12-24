
reg = {'a':0, 'b':0, 'c':0, 'd':0, 'e':0, 'f':0, 'g':0, 'h':0}
def a(lines):
   index = 0
   code = [x.split() for x in lines]
   multcount = 0
   while(index < len(code)):
      op = code[index][0]
      if(op == 'set'):
         reg[code[index][1]] = get(code[index][2])
      elif(op == 'sub'):
         reg[code[index][1]] -= get(code[index][2])
      elif(op == 'mul'):
         reg[code[index][1]] *= get(code[index][2])
         multcount += 1
      if(op == 'jnz' and get(code[index][1]) != 0):
         index += get(code[index][2])
      else:
         index += 1
   print(reg)
   return multcount

def b(lines):
   index = 0
   code = [x.split() for x in lines]
   count = 0
   while(index < len(code)):
      op = code[index][0]
      if(count % 1000000 == 0):
         print(reg)
      if(op == 'set'):
         reg[code[index][1]] = get(code[index][2])
      elif(op == 'sub'):
         reg[code[index][1]] -= get(code[index][2])
      elif(op == 'mul'):
         reg[code[index][1]] *= get(code[index][2])
      if(op == 'jnz' and get(code[index][1]) != 0):
         index += get(code[index][2])
      else:
         index += 1
      count += 1
   print(reg)
   return reg['h']


def get(input):
   if input.isalpha():
      return reg[input]
   else:
      return int(input)

def isPrime(n):
    for i in range(2,int(n**0.5)+1):
        if n%i==0:
            return False
    return True

def b2():
   h = 0
   for b in range(105700, 122700+1, 17):
      for i in range(2, b):
         if(b % i == 0):
            h+=1
            break
   return h

def sim():
   a = 0
   b = 105700
   c = 122700
   d = 0
   c = 0
   e = 0
   f = 0
   g = 0
   h = 0
   while(True):
      print(b)
      f = 1
      d = 2
      while(True):
         e = 2
         while(True):
            if d * e == b:
               f = 0
            e += 1
            if e == b:
               break
         d += 1
         if b == d:
            break
      if f == 0:
         print(h)
         h += 1
      if b != c:
         b += 17
      else:
         break
   return h

with open("input.txt") as f:
   lines = [x.strip() for x in f.readlines()]
   print(a(lines))
   reg = {'a':1, 'b':0, 'c':0, 'd':0, 'e':0, 'f':0, 'g':0, 'h':0}
   #print(b(lines))
   print(b2())