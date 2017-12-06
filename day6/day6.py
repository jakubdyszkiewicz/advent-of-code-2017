def solution(nums):
    states = set()
    while str(nums) not in states:
        states.add(str(nums))
        redistribute(nums, index_of_max(nums))
    return len(states)

def redistribute(nums, index):
    blocks_left = nums[index]
    nums[index] = 0
    while blocks_left > 0:
        index += 1
        if index >= len(nums):
            index = 0
        blocks_left -= 1
        nums[index] += 1

def index_of_max(nums):
    max_idx = 0
    max_val = nums[0]
    for idx, val in enumerate(nums):
        if val > max_val:
            max_val = val
            max_idx = idx
    return max_idx

nums = [int(num) for num in open("input.txt").read().split("\t")]
# part1
print(solution(nums))
# part2
print(solution(nums))
