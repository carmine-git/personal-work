#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Node {
  char value;
  struct Node *next;
};

void initialize(struct Node **node, int number_of_nodes) {
  char input;
  int i = 0;

  do {
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node *));
		printf("Enter node: ");
		scanf_s(" %c", &input);
		temp->value = input;
		temp->next = (* node);
		(* node) = temp;
		i++;
  } while (i < number_of_nodes);
}

bool include(struct Node *node, char value) {
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

void display(struct Node *node) {
  printf("\n[");

  while (node != NULL) {
    printf("%c, ", node->value);
    node = node->next;
  }

  printf("]");
}

int length(struct Node *node) {
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
  initialize(&list, 3);
  display(list);
  return 0;
}
