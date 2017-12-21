

def a(nums):
   index = 0
   count = 0
   while(index < len(nums)):
      n = nums[index]
      nums[index] += 1
      index += n
      count += 1
   return count

def b(nums):
   index = 0
   count = 0
   while(index < len(nums)):
      n = nums[index]
      if(n >= 3):
         nums[index] -= 1
      else:
         nums[index] += 1
      index += n
      count += 1
   return count

with open("input.txt") as f:
   nums = [int(x.strip()) for x in f.readlines()]
   print(a(nums))

with open("input.txt") as f:
   nums = [int(x.strip()) for x in f.readlines()]
   print(b(nums))
   