res = 0
f = open("input.txt")
for passp in f.read().split('\n'):
    words = passp.split(' ')
    print(words)
    s = set()
    wrong = False
    for word in words:
        sorted_word = ''.join(sorted(word))
        if sorted_word in s:
            wrong = True
            break
        s.add(sorted_word)
    if not wrong:
        res += 1
print(res - 1)
