def sort(tab):
    n = len(tab)
    output = [0] * n
    count = [0] * 10

    for i in range(0, n):
        count[tab[i]] += 1

    for i in range(0, 10):
        count[i] += count[i - 1]

    i = n - 1
    while i >= 0:
        output[count[tab[i]] - 1] = tab[i]
        count[tab[i]] -= 1  # Decrementing so we can allow duplicates
        i -= 1

    for i in range(0, n):
        tab[i] = output[i]

    return tab


data = [4, 2, 2, 8, 3, 3, 1]
print(sort(data))
