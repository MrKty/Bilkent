/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Max Heap Implementation
*/

#include "MaxHeap.h"

// Default constructor
MaxHeap::MaxHeap(int maxSize) : size(0), MAX_SIZE(maxSize) {
    requests = new Request[maxSize];
}

bool MaxHeap::heapIsEmpty() const {
    return (size == 0);
}

void MaxHeap::heapInsert(const Request &newItem) {

    if (size >= MAX_SIZE)
        return;

    // Place the new item at the end of the heap
    requests[size] = newItem;

    // Trickle new item up to its proper position
    int place = size;
    int parent = (place - 1) / 2;
    while ((place > 0) && (requests[place].getPriority() > requests[parent].getPriority())) {
        Request temp = requests[parent];
        requests[parent] = requests[place];
        requests[place] = temp;
        place = parent;
        parent = (place - 1) / 2;
    }
    ++size;
}

void MaxHeap::heapDelete(Request &rootItem) {
    if (heapIsEmpty())
        return;
    else {
        rootItem = requests[0];
        requests[0] = requests[--size];
        heapRebuild(0);
    }
}

void MaxHeap::heapRebuild(int root) {
    int child = 2 * root + 1;    // index of root's left child, if any
    if (child < size) {
        // root is not a leaf so that it has a left child
        int rightChild = child + 1;    // index of a right child, if any
        // If root has right child, find larger child
        if ((rightChild < size)) {
            if (requests[rightChild].getPriority() > requests[child].getPriority()) {
                child = rightChild;    // index of larger child
            } else if (requests[rightChild].getPriority() == requests[child].getPriority()) {
                if (requests[rightChild].getId() < requests[child].getId()) {
                    child = rightChild;
                }
            }
        }

        // If root’s item is smaller than larger child, swap values
        if (requests[root].getPriority() < requests[child].getPriority() || (requests[root].getPriority() ==
                                                                             requests[child].getPriority() && requests[child].getId() < requests[root].getId())) {
            Request temp = requests[root];
            requests[root] = requests[child];
            requests[child] = temp;
            // transform the new subtree into a heap
            heapRebuild(child);
        }
    }
}

MaxHeap::~MaxHeap() {
    delete[] requests;
}
