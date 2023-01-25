//
// Omer Oktay Gultekin 21901413
//

#include "Stack.h"


// default constructor
Stack::Stack() : topPtr(NULL) {}

// copy constructor
Stack::Stack(const Stack &other) {

    if (other.topPtr == NULL)
        topPtr = NULL;  // other stack is empty

    else {
        // copy first node
        topPtr = new StackNode;
        topPtr->item = other.topPtr->item;

        // copy the rest
        StackNode *newPtr = topPtr;
        for (StackNode *otherPtr = other.topPtr->next;
             otherPtr != NULL;
             otherPtr = otherPtr->next) {
            newPtr->next = new StackNode;
            newPtr = newPtr->next;
            newPtr->item = otherPtr->item;
        }
        newPtr->next = NULL;
    }
}

// destructor
Stack::~Stack() {
    // pop until stack is empty
    while (!isEmpty()) {
        pop();
    }
}

bool Stack::isEmpty() const {
    return topPtr == NULL;
}

bool Stack::push(const string &topItem) {

    // create a new node
    StackNode *newPtr = new StackNode;

    // set data to the new node
    newPtr->item = topItem;

    // insert the new node
    newPtr->next = topPtr;
    topPtr = newPtr;

    return true;
}

bool Stack::pop() {

    if (isEmpty())
        return false;
        // stack is not empty; continue to delete top

    else {
        StackNode *temp = topPtr;
        topPtr = topPtr->next;

        // return deleted node to system
        temp->next = NULL;
        delete temp;
        return true;
    }
}

bool Stack::pop(string &topItem) {

    if (isEmpty())
        return false;

        // not empty; retrieve and delete top
    else {
        topItem = topPtr->item;
        StackNode *temp = topPtr;
        topPtr = topPtr->next;

        // return deleted node to system
        temp->next = NULL;
        delete temp;
        return true;
    }
}

bool Stack::getTop(string &topItem)
const {

    if (isEmpty())
        return false;
        // stack is not empty; retrieve top
    else {
        topItem = topPtr->item;
        return true;
    }

}


