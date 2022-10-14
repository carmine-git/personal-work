from cgitb import reset


def count_sort(tab, n):
    res = [0 for _ in tab]

    for i in range(len(res) - 1):
        res[tab[i]] += 1

    print(res)
    return res

data = [1,3,2,1,1,3,3,2,2,4,1,1,3,3,4]
if count_sort(data, 10) == sorted(data):
    print('sorted')
else:
    print("nope")