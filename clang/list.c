#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

// 1
struct Node {
  int value;
  struct Node *next;
};

// 3
struct Node *create_list(struct Node **node, int number_of_nodes) {
  int user_input;
  int iterator = 0;
  int remaining_nodes_to_input = number_of_nodes;

  do {
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node *));
    printf("Please enter the value of the %d-th node (remaining: %d):",
           iterator, remaining_nodes_to_input);
    scanf("%d", &user_input);
    temp->value = user_input;
    temp->next = *node;
    *node = temp;
    iterator++;
    remaining_nodes_to_input--;
  } while (iterator <= number_of_nodes);
}

// 4
void print_list_iteratively(struct Node *node) {
  printf("\n[");

  while (node->next != NULL) {
    printf("%d, ", node->value);
    node = node->next;
  }

  printf("]");
};

void print_list_recursive(struct Node *node) {
  if (node == NULL) {
    return;
  }

  printf("\n");
  printf("%d", node->value);
  print_list_recursive(node->next);
}

// 5
bool search_node_by_value_recursively(struct Node *node, int target_number) {
  if (node == NULL) {
    return false;
  }

  if (node->value == target_number) {
    return true;
  }

  search_node_by_value_recursively(node->next, target_number);
}

// 6
void insert_at_head(struct Node **node, int number_to_add) {
  struct Node *new_node = (struct Node *)malloc(sizeof(struct Node *));
  new_node->value = number_to_add;
  new_node->next = *node;
  *node = new_node;
}

// training
void insert_at_last(struct Node **node, int number_to_add) {
  struct Node *new_node = (struct Node *)malloc(sizeof(struct Node *));
  new_node->value = number_to_add;
  new_node->next = NULL;

  if (*node == NULL) {
    *node = new_node;
  }

  struct Node *last_node = *node;

  while (last_node->next != NULL) {
    last_node = last_node->next;
  }

  last_node->value = number_to_add;
  last_node->next = NULL;
}

// 7
void remove_node_by_value(struct Node **node, int target_number) {
  struct Node *temp;

  if ((*node)->value == target_number) {
    temp = (*node);
    (*node) = (*node)->next;
    free(temp);
  }

  temp = (*node);

  while (temp->next->value != target_number) {
    temp = temp->next;
  }

  temp->next = temp->next->next;
}

// exercice 2
void reverse_list(struct Node **node) {
  struct Node *next;
  struct Node *current = *node;
  struct Node *previous = NULL;

  while (current != NULL) {
    next = current->next;
    current->next = previous;
    previous = current;
    current = next;
  }

  *node = previous;
}

// support pour exercice 3
int length_list(struct Node *node) {
  int count = 0;

  while (node != NULL) {
    node = node->next;
    count++;
  }

  return count;
}

// support pour exercice 3
void swap_nodes(struct Node **current_node, struct Node **next_node) {
  int temp = (*current_node)->value;
  (*current_node)->value = (*next_node)->value;
  (*next_node)->value = temp;
}

// exercice 3
void bubble_sort_list(struct Node **node) {
  bool swapped = false;
  struct Node *node_to_sort = *node;
  int length = length_list(node_to_sort);

  if (node_to_sort == NULL) {
    return;
  }

  for (int i = 0; i <= length; i++) {
    struct Node *node_to_sort = *node;

    while (node_to_sort->next != NULL) {
      if (node_to_sort->value > node_to_sort->next->value) {
        swap_nodes(&node_to_sort, &node_to_sort->next);
        swapped = true;
      }

      node_to_sort = node_to_sort->next;
    }

    if (!swapped) {
      break;
    }
  }
}

// 4
void bind_both_list(struct Node **first_node, struct Node *second_node) {
  struct Node *node_to_bind = *first_node;

  if (node_to_bind == NULL) {
    return;
  }

  while (node_to_bind->next != NULL) {
    node_to_bind = node_to_bind->next;
  }

  node_to_bind->next = second_node;
}

int main() {
  // 2
  struct Node *first_list = NULL;
  struct Node *second_list = NULL;

  create_list(&first_list, 5);
  insert_at_head(&first_list, 78);

  create_list(&second_list, 10);
  insert_at_head(&second_list, 45);
  insert_at_head(&second_list, 23);

  printf("Original lists");
  print_list_iteratively(first_list);
  print_list_iteratively(second_list);

  bubble_sort_list(&first_list);
  bubble_sort_list(&second_list);

  printf("\n\nLists after being bubble sorted");
  print_list_iteratively(second_list);
  print_list_iteratively(first_list);

  printf("\n\nThe first list after binding it to the second list");
  bind_both_list(&first_list, second_list);
  print_list_iteratively(first_list);

  printf("\n\nThe mutated first list after reversing it");
  reverse_list(&first_list);
  print_list_iteratively(first_list);
  return 0;
}
