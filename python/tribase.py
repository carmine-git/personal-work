def count_sort(tab, base):
    n = len(tab)
    
    # initialize arrays with 0's
    output = [0] * n
    count = [0] * 10

    for i in range(0, n):
        index = tab[i] / base
        count[int(index % 10)] += 1

    for i in range(1, 10):
        count[i] += count[i - 1]

    i = n - 1

    while i >= 0:
        index = tab[i] / base
        output[ count[int(index % 10)] - 1 ] = tab[i]
        count[int(index % 10)] -= 1
        i -= 1 

    for u in range(0, len(tab)):
        tab[u] = output[u]


def radix_sort(arr):
    max_elem = max(arr)
    base = 1

    while max_elem / base > 0:
        count_sort(arr, base)
        base *= 10

    return arr

data = [25,41,63,78,96,15,26,63,45,78,96,32,65,48,75,465,852,159,369,789]
print(radix_sort(data))
