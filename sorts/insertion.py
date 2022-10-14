# iteration
def insertion_sort(tab):
    for i in range(1, len(tab)):
        key = tab[i]
        j = i - 1

        while j >= 0 and tab[j] > key:
            tab[j + 1] = tab[j]
            j -= 1

    print("sorted")
    tab[j + 1] = key
    
    return tab

# driver code

data = [7,8,4,6,3,2,1,7,9,5,6,3,1,8]
if insertion_sort(tab=data) == sorted(key=data):
    print('sorted')
else:
    print('nope')