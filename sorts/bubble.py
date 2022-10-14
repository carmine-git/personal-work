from typing import List


def bubble_sort(tab):
    n = len(tab)
    sort_check = True

    for i in range(n):
        for j in range(0, n - i - 1):
            if tab[j] > tab[j + 1]:
                tab[j], tab[j + 1] = tab[j + 1], tab[j]
                sort_check = False
        if sort_check:
            break
    
    return tab

data = [17,28,39,79,46,13,15,26]
s = bubble_sort(data)
print(s)
