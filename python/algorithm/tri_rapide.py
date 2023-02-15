def partition(tab, low, high):
    pivot = tab[high]
    i = low - 1

    for j in range(low, high):
        if tab[j] <= pivot:
            i += 1
            (tab[i], tab[j]) = (tab[j], tab[i])

    (tab[i + 1], tab[high]) = (tab[high], tab[i + 1])

    return i + 1


def quick_sort(tab, low, high):
    if low < high:
        pi = partition(tab, low, high)
        quick_sort(tab, low, pi - 1)
        quick_sort(tab, pi + 1, high)
    return tab


data = [25, 41, 63, 78, 96, 15, 26, 63, 45, 78, 96, 32, 65, 48, 75]
print(f'Before sort: {data}')

sort = quick_sort(data, 0, len(data) - 1)

print(f'After sort: {sort}')
