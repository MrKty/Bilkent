/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: MaxHeap Header
*/


#ifndef HEAP_H
#define HEAP_H

#include "Request.h"// definition of Request

class MaxHeap {
public:
    MaxHeap(int maxSize);				// default constructor
    // copy constructor and destructor are supplied by the compiler

    bool heapIsEmpty() const;
    void heapInsert(const Request& newItem);
    void heapDelete(Request& rootItem);

    virtual ~MaxHeap();

protected:
    void heapRebuild(int root);		// Converts the semiheap rooted at
    // index root into a heap
private:
    Request* requests;	    // dynamic array of heap items
    const int MAX_SIZE;
    int  size;            	// number of heap items
};


#endif
