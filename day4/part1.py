res = 0
f = open("input.txt")
for passp in f.read().split('\n'):
    words = passp.split(' ')
    s = set()
    for word in words:
        s.add(word)
    if len(s) == len(words):
        res += 1
print(res - 1)
