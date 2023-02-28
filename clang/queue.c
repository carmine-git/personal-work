#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Queue {
  int value;
  struct Queue *next;
};

struct pairs {
  struct Queue *positives;
  struct Queue *negatives;
};

/** @brief Threads the queue with different value
 *  @param queue the queue to thread
 *  @param value to value to thread in the queue
 *  @return void
 */
void thread(struct Queue **queue, int value) {
  struct Queue *data = (struct Queue *)malloc(sizeof(struct Queue *));
  data->next = NULL;
  data->value = value;

  if ((*queue) == NULL) {
    (*queue) = data;
    return;
  } else {
    struct Queue *temp = *queue;

    while (temp->next != NULL) {
      temp = temp->next;
    }

    temp->next = data;
  }
}

/** @Brief removes an element from the thread
 * @param queue the queue where we will remove an element
 * @return void
 */
void unthread(struct Queue **queue) {
  if ((*queue) == NULL) {
    return;
  }

  struct Queue *temp = (*queue)->next;
  free((*queue));
  (*queue) = temp;
}

/** @brief dispalys the queue
 * @peram queue the queue to display
 * @return void
 */
void display(struct Queue *queue) {
  if (queue == NULL) {
    return;
  }

  printf("\n[");

  while (queue != NULL) {
    printf("%d, ", queue->value);
    queue = queue->next;
  }

  printf("]");
}

/** @Brief Check if current queue is empty
 * @param queue the queue to check
 * @return bool
 */
bool is_empty(struct Queue *queue) { return queue == NULL; }

/* @Brief sorts positive and negative numbers in their respective queues
 * @param queue the queue to sort
 * @return struct PositiveAndNegativeQueue
 */
struct pairs sort(struct Queue *queue) {
  struct Queue *negatives = NULL;
  struct Queue *positives = NULL;

  while (queue != NULL) {
    if (queue->value >= 0) {
      thread(&positives, queue->value);
    } else {
      thread(&negatives, queue->value);
    }

    queue = queue->next;
  }
	struct pairs pairs = {positives, negatives};
  return pairs;
}

int main(int argc, char *argv[]) {
  struct Queue *queue = NULL;
	struct pairs pairs;
  thread(&queue, 5);
  thread(&queue, 10);
  thread(&queue, 15);
	thread(&queue, -5);
	thread(&queue, -10);
  pairs = sort(queue);
	display(pairs.positives);
	display(pairs.negatives);
  return 0;
}
