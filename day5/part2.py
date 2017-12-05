nums = [int(num) for num in open("input.txt").read().split('\n') if num != '']
res = 0
index = 0
while index < len(nums) and index >= 0:
    res += 1
    curIdx = index
    index += nums[index]
    if nums[curIdx] >= 3:
        nums[curIdx] -= 1   
    else:
        nums[curIdx] += 1
print(res)
