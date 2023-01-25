//
// Omer Oktay Gultekin 21901413
//

#ifndef STACK_H
#define STACK_H

#include "string"

using namespace std;

class Stack {

public:
    Stack();

    Stack(const Stack &other);

    ~Stack();

    bool isEmpty() const;

    bool push(const string &topItem);

    bool pop();

    bool pop(string &topItem);

    bool getTop(string &topItem) const;

private:
    struct StackNode {          // a node on the stack
        string item;            // a data on the stack
        StackNode *next;        // points to the next node
    };

    StackNode *topPtr;     // points to the first node in the stack
};


#endif //STACK_H
