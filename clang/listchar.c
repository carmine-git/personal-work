#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Node {
  char value;
  struct Node *next;
};

struct Node *create_list(struct Node **node, int number_of_nodes) {
  char user_input;
  int iterator = 0;
  int remaining_nodes_to_input = number_of_nodes;

  for (iterator; iterator <= number_of_nodes; iterator++) {
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node *));
    printf("Please enter the value of the %d-th node (remaining: %d):",
           iterator, remaining_nodes_to_input);

    if (!iterator) {
      scanf("%c", &user_input);
    } else if (iterator) {
      scanf(" %c", &user_input);
    }

    temp->value = user_input;
    temp->next = *node;
    *node = temp;
    remaining_nodes_to_input--;
  }
}

bool is_value_in_list(struct Node *node, char value) {
  bool is_in_list = false;

  while (node != NULL) {
    if (node->value == value) {
      is_in_list = true;
      break;
    }

    node = node->next;
  }

  return is_in_list;
}

void print_list_iteratively(struct Node *node) {
  printf("\n[");

  while (node != NULL) {
    printf("%c, ", node->value);
    node = node->next;
  }

  printf("]");
}

int length_list(struct Node *node) {
  if (node == NULL) {
    return 0;
  }

  int count = 0;

  while (node != NULL) {
    count++;
  }

  return count;
}

// TODO: IMPLEMENT THIS FUNCTION
void remove_duplicates(struct Node **node) { return; }

int main() {
  struct Node *list = NULL;
  create_list(&list, 10);
  print_list_iteratively(list);
  remove_duplicates(&list);
  print_list_iteratively(list);

  return 0;
}