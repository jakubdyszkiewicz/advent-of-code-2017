def solution(step):
    edge = 1
    horizontal = 0
    while edge * edge < step:
        edge += 2
        horizontal += 1
    previous_edge = edge - 2
    pos_in_square = step - pow(previous_edge, 2)
    pos_in_edge = pos_in_square % (edge - 1)
    vertical = abs((edge - 1) / 2 - pos_in_edge)
    return vertical + horizontal

print(solution(12))
print(solution(23))
print(solution(26))
print(solution(1024))
print(solution(368078))
