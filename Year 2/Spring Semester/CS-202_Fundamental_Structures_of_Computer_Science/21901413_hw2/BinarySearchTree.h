/*
* Title: Trees
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 2
* Description: BinarySearchTree class function implementations
*/

#ifndef BINARYSEARCHTREE_H
#define BINARYSEARCHTREE_H

#include "BinaryNode.h"


class BinarySearchTree {

    // Properties
    BinaryNode *root;

    // Helper Methods
    void destroyTree(BinaryNode *&treePtr);

    int findMaxHeight(BinaryNode *treePtr);

    bool addItem(BinaryNode *&treePtr, const int &newItem, BinaryNode *parentPtr = NULL);

    void inorderInternal(BinaryNode *treePtr);

    int findWidth(BinaryNode *treePtr, int *widthArrPtr, int level);

    int countInternal(BinaryNode *treePtr, int minVal, int maxVal);

    int successorInternal(BinaryNode *treePtr, const int &anEntry);

    BinaryNode *traverseLeft(BinaryNode *treePtr, bool deleteOp);

    BinaryNode *returnNode(BinaryNode *treePtr, const int &anEntry);

    void removeNodeFromParent(BinaryNode *currNode, BinaryNode *childNode = NULL) const;

    int findIndex(BinaryNode *treePtr, int anEntry, int &finish);

public:
    BinarySearchTree();

    bool isEmpty();

    int getHeight();

    int getNumberOfNodes();

    bool add(int newEntry);

    bool remove(int anEntry);

    bool contains(int anEntry);

    void inorderTraverse();

    int getWidth();

    int count(int a, int b);

    int select(int anEntry);

    int successor(int anEntry);

    ~BinarySearchTree();
};

#endif //BINARYSEARCHTREE_H