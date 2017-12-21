def checksumA(lines):
   checksum = 0
   for line in lines:
      nums = [int(x.strip()) for x in line.split()]
      min = 10000
      max = 0
      for num in nums:
         if(num < min):
            min = num
         if(num > max):
            max = num
      checksum += max - min
   return checksum

def checksumB(lines):
   checksum = 0
   for line in lines:
      nums = [int(x.strip()) for x in line.split()]
      for i in range(len(nums)):
         for j in range(len(nums)):
            if(i != j and nums[i] % nums[j] == 0):
               checksum += int(nums[i] / nums[j])
   return checksum


with open("input.txt") as f:
   lines = f.readlines()
   print("Part A: ", checksumA(lines))
   print("Part B: ", checksumB(lines))
