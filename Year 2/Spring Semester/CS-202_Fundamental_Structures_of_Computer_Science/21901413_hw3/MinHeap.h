/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: MinHeap Header
*/

#ifndef HW3_MINHEAP_H
#define HW3_MINHEAP_H

#include "Computer.h"// definition of Request

class MinHeap {
public:
    MinHeap(int maxSize);                // default constructor
    // copy constructor and destructor are supplied by the compiler

    bool heapIsEmpty() const;

    void heapInsert(const Computer &newItem, bool priorityType);

    Computer peekHead();

    void heapDelete(Computer &rootItem, bool priorityType);

protected:
    void heapRebuild(int root, bool priorityType);        // Converts the semiheap rooted at
    // index root into a heap
private:
    Computer *computers;        // dynamic array of heap items
    const int MAX_SIZE;
    int size;                // number of heap items
public:
    virtual ~MinHeap();
};

#endif //HW3_MINHEAP_H
