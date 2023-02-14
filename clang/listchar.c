#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Node
{
    char value;
    struct Node *next;
};

struct Node *create_list(struct Node **node, int number_of_nodes)
{
    char user_input;
    int iterator = 0;
    int remaining_nodes_to_input = number_of_nodes;

    for (iterator; iterator <= number_of_nodes; iterator++)
    {
        struct Node *temp = (struct Node *)malloc(sizeof(struct Node *));
        printf("Please enter the value of the %d-th node (remaining: %d):",
               iterator, remaining_nodes_to_input);

        if (!iterator)
        {
            scanf("%c", &user_input);
        }
        else if (iterator)
        {
            scanf(" %c", &user_input);
        }

        temp->value = user_input;
        temp->next = *node;
        *node = temp;
        remaining_nodes_to_input--;
    }
}

void print_list_iteratively(struct Node *node)
{
    printf("\n[");

    while (node != NULL)
    {
        printf("%c, ", node->value);
        node = node->next;
    }

    printf("]");
}

int main()
{
    struct Node *list = NULL;
    create_list(&list, 5);
    print_list_iteratively(list);

    return 0;
}