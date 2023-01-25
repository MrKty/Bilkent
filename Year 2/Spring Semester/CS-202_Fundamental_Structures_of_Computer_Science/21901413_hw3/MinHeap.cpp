/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Min Heap Header
*/

#include "MinHeap.h"

// Default constructor
MinHeap::MinHeap(int maxSize) : size(0), MAX_SIZE(maxSize) {
    computers = new Computer[maxSize];
}

bool MinHeap::heapIsEmpty() const {
    return (size == 0);
}

// true for time related priority, false for id priority
void MinHeap::heapInsert(const Computer &newItem, bool priorityType) {

    if (size >= MAX_SIZE)
        return;

    // Place the new item at the end of the heap
    computers[size] = newItem;

    // Trickle new item up to its proper position
    int place = size;
    int parent = (place - 1) / 2;
    if (priorityType){
        while ((place > 0) && (computers[place].getFinishTime() < computers[parent].getFinishTime())) {
            Computer temp = computers[parent];
            computers[parent] = computers[place];
            computers[place] = temp;
            place = parent;
            parent = (place - 1) / 2;
        }
    }else{
        while ((place > 0) && (computers[place].getId() < computers[parent].getId())) {
            Computer temp = computers[parent];
            computers[parent] = computers[place];
            computers[place] = temp;
            place = parent;
            parent = (place - 1) / 2;
        }
    }
    ++size;
}

// true for time related priority, false for id priority
void MinHeap::heapDelete(Computer &rootItem, bool priorityType) {
    if (heapIsEmpty())
        return;
    else {
        rootItem = computers[0];
        computers[0] = computers[--size];
        heapRebuild(0, priorityType);
    }
}

// true for time related priority, false for id priority
void MinHeap::heapRebuild(int root, bool priorityType) {
    int child = 2 * root + 1;    // index of root's left child, if any
    if (child < size) {
        // root is not a leaf so that it has a left child
        int rightChild = child + 1;    // index of a right child, if any
        if (priorityType){
            // If root has right child, find smaller child
            if ((rightChild < size) &&
                (computers[rightChild].getFinishTime() < computers[child].getFinishTime()))
                child = rightChild;    // index of larger child

            // If root’s item is larger than larger child, swap values
            if (computers[root].getFinishTime() > computers[child].getFinishTime()) {
                Computer temp = computers[root];
                computers[root] = computers[child];
                computers[child] = temp;
                // transform the new subtree into a heap
                heapRebuild(child, priorityType);
            }
        }else {
            // If root has right child, find smaller child
            if ((rightChild < size) &&
                (computers[rightChild].getId() < computers[child].getId()))
                child = rightChild;    // index of larger child

            // If root’s item is larger than larger child, swap values
            if (computers[root].getId() > computers[child].getId()) {
                Computer temp = computers[root];
                computers[root] = computers[child];
                computers[child] = temp;
                // transform the new subtree into a heap
                heapRebuild(child, priorityType);
            }
        }
    }
}

Computer MinHeap::peekHead() {
    return computers[0];
}

MinHeap::~MinHeap() {
    delete[] computers;
}
