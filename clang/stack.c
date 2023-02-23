#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Stack {
  int value;
  struct Stack *previous;
};

/** @Brief Pushes to the top of the stack
 * @param stack the stack to modify
 * @param value the value to push in the stack
 * @return void
 */
void push(struct Stack **stack, int value) {
  struct Stack *new_stack = (struct Stack *)malloc(sizeof(struct Stack *));

  if (new_stack != NULL) {
    new_stack->value = value;
    new_stack->previous = (*stack);
    (*stack) = new_stack;
  }
};

/** @Brief displays the stack values
 * @param stack the stack values to be displayed
 * @return void
 */
void display(struct Stack *stack) {
	printf("\n[");

	while (stack != NULL) {
		printf("%d, ", stack->value);
		stack = stack->previous;
	}

	printf("]");
}

/** @Brief Unstack a data from the stack
 * @param stack the stack that we will unstack
 * @return void
 */
void unstack(struct Stack **stack) {
	if (stack == NULL) {
		return;
	}

	struct Stack *temp = (*stack)->previous;
	free((*stack));
	(*stack) = temp;
}

/** @brief Checks if the stack is empty
 * @param stack the stack to check
 * @return bool
 */
bool is_empty(struct Stack *stack) {
	return stack == NULL;
}

int main() {
  struct Stack *stack = NULL;
  push(&stack, 3);
	push(&stack, 45);
	push(&stack, 86);
	display(stack);

	unstack(&stack);
	display(stack);
  return 0;
}
