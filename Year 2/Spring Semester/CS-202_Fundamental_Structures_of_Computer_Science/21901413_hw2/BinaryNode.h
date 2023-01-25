/*
* Title: Trees
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 2
* Description: BinaryNode class properties and function headers
*/

#ifndef BINARYNODE_H
#define BINARYNODE_H

#include <string>

using namespace std;

typedef int TreeItemType;

class BinaryNode {            // node in the tree
private:
    // Properties
    int size;
    BinaryNode *parentPtr;
    friend class BinarySearchTree; // to access private fields of the BinaryNode

    // Methods
    BinaryNode();
    BinaryNode(const TreeItemType& nodeItem,
               BinaryNode *parent = NULL,
               BinaryNode *left = NULL,
               BinaryNode *right = NULL);
    TreeItemType item;       // data portion
    BinaryNode *leftChildPtr;  // pointer to left child
    BinaryNode *rightChildPtr; // pointer to right child
    ~BinaryNode();
};

#endif //BINARYNODE_H
