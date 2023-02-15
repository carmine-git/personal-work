def sort(enum):
    n = len(enum)

    for step in range(1, n):
        key = enum[step]
        j = step - 1

        while j >= 0 and key < enum[j]:
            enum[j + 1] = enum[j]
            j -= 1

        enum[j+1] = key

    return enum


data = [9, 1, 6, 3, 4, 2, 5, 8, 3, 7]
print(sort(data))
