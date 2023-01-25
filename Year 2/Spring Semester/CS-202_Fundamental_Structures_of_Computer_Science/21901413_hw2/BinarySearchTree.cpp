/*
* Title: Trees
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 2
* Description: BinarySearchTree class properties and function headers
*/

#include "BinarySearchTree.h"
#include "BinaryNode.h"
#include <iostream>

// Constructor
BinarySearchTree::BinarySearchTree() {
    this->root = NULL;
}

bool BinarySearchTree::isEmpty() {
    return root == NULL;
}

int BinarySearchTree::getHeight() {
    if (root == NULL) {
        return 0;
    }
    return 1 + findMaxHeight(root);
}

int BinarySearchTree::findMaxHeight(BinaryNode *treePtr) {
    int heightLeft = 0;
    int heightRight = 0;

    if (treePtr->leftChildPtr != NULL) {
        heightLeft = 1 + findMaxHeight(treePtr->leftChildPtr);
    }
    if (treePtr->rightChildPtr != NULL) {
        heightRight = 1 + findMaxHeight(treePtr->rightChildPtr);
    }
    if (heightLeft > heightRight) {
        return heightLeft;
    }
    return heightRight;
}

int BinarySearchTree::getNumberOfNodes() {
    // root has the number of nodes in the subtree it refers to + itself
    if (root != NULL) {
        return this->root->size + 1;
    }
    return 0;
}

bool BinarySearchTree::add(int newEntry) {
    if (newEntry <= 0) {
        return false;
    }
    return addItem(this->root, newEntry);
}

bool BinarySearchTree::addItem(BinaryNode *&treePtr, const int &newItem, BinaryNode *parentPtr) {
    // Position of insertion found; insert after leaf
    if (treePtr == NULL) {
        treePtr = new BinaryNode(newItem, parentPtr, NULL, NULL);
        if (parentPtr != NULL) {
            if (treePtr->item < parentPtr->item) {
                parentPtr->leftChildPtr = treePtr;
            } else {
                parentPtr->rightChildPtr = treePtr;
            }
            BinaryNode *temp = parentPtr;
            while (temp != NULL) {
                temp->size += 1;
                temp = temp->parentPtr;
            }
        }

        return true;
    }

    // Search for the insertion position
    if (newItem == treePtr->item) {
        return false;
    } else if (newItem < treePtr->item) {
        return addItem(treePtr->leftChildPtr, newItem, treePtr);
    } else {
        return addItem(treePtr->rightChildPtr, newItem, treePtr);
    }
}

bool BinarySearchTree::remove(int anEntry) {
    BinaryNode *currNode = returnNode(this->root, anEntry);
    if (currNode != NULL) {
        // Temp Node will be used for first, decreasing size properties of parent Nodes;
        // Second, changing currNode with the successor Node
        // We need to go back to the parent nodes, but it is necessary since we can't sure the tree has anEntry.
        BinaryNode *tempNode = currNode->parentPtr;
        while (tempNode != NULL) {
            tempNode->size -= 1;
            tempNode = tempNode->parentPtr;
        }
        // Case 1: No Child
        if (currNode->leftChildPtr == NULL && currNode->rightChildPtr == NULL) {
            removeNodeFromParent(currNode);
            if (this->root == currNode){
                this->root = NULL;
            }
            delete currNode;
            return true;
            // Case 2: 1 Child
        } else if (currNode->leftChildPtr != NULL && currNode->rightChildPtr == NULL) {
            removeNodeFromParent(currNode, currNode->leftChildPtr);
            currNode->leftChildPtr->parentPtr = currNode->parentPtr;
            if (this->root == currNode){
                this->root = currNode->leftChildPtr;
            }
            delete currNode;
            return true;
        } else if (currNode->rightChildPtr != NULL && currNode->leftChildPtr == NULL) {
            removeNodeFromParent(currNode, currNode->rightChildPtr);
            currNode->rightChildPtr->parentPtr = currNode->parentPtr;
            if (this->root == currNode){
                this->root = currNode->rightChildPtr;
            }
            delete currNode;
            return true;
        } else {
            // Case 3: 2 children
            tempNode = traverseLeft(currNode->rightChildPtr, true);
            // Successor can only have right children
            if (tempNode->rightChildPtr != NULL) {
                removeNodeFromParent(tempNode, tempNode->rightChildPtr);
                tempNode->rightChildPtr->parentPtr = tempNode->parentPtr;
            } else {
                removeNodeFromParent(tempNode);
            }
            currNode->size -= 1;
            currNode->item = tempNode->item;
            delete tempNode;
            return true;
        }
    }
    return false;
}

void BinarySearchTree::removeNodeFromParent(BinaryNode *currNode, BinaryNode *childNode) const {
    if (currNode->parentPtr != NULL){
        if (currNode == currNode->parentPtr->leftChildPtr) {
            if (childNode) {
                currNode->parentPtr->leftChildPtr = childNode;
            } else {
                currNode->parentPtr->leftChildPtr = NULL;
            }
        } else if (currNode == currNode->parentPtr->rightChildPtr) {
            if (childNode) {
                currNode->parentPtr->rightChildPtr = childNode;
            } else {
                currNode->parentPtr->rightChildPtr = NULL;
            }
        }
    }
}

bool BinarySearchTree::contains(int anEntry) {
    return NULL != returnNode(this->root, anEntry);
}

void BinarySearchTree::inorderTraverse() {
    inorderInternal(this->root);
    cout << endl;
}

void BinarySearchTree::inorderInternal(BinaryNode *treePtr) {
    if (treePtr != NULL) {
        inorderInternal(treePtr->leftChildPtr);
        cout << treePtr->item << " ";
        inorderInternal(treePtr->rightChildPtr);
    }
}


int BinarySearchTree::getWidth() {
    int height = this->getHeight();
    int *widthArrPtr = new int[height];
    for (int i = 0; i < height; ++i) {
        widthArrPtr[i] = 0;
    }
    int level = 0;
    findWidth(root, widthArrPtr, level);
    int maxWidth = 0;
    for (int i = 0; i < height; ++i) {
        if (maxWidth < widthArrPtr[i]) {
            maxWidth = widthArrPtr[i];
        }
    }
    delete[] widthArrPtr;
    return maxWidth;
}

int BinarySearchTree::findWidth(BinaryNode *treePtr, int *widthArrPtr, int level) {
    if (treePtr != NULL) {
        findWidth(treePtr->leftChildPtr, widthArrPtr, level + 1);
        widthArrPtr[level] += 1;
        findWidth(treePtr->rightChildPtr, widthArrPtr, level + 1);
    }
}

int BinarySearchTree::count(int a, int b) {
    return countInternal(this->root, a, b);
}

int BinarySearchTree::countInternal(BinaryNode *treePtr, int minVal, int maxVal) {
    if (treePtr != NULL) {
        int counter = 0;
        bool greater = false;
        if (treePtr->item >= minVal) {
            counter += countInternal(treePtr->leftChildPtr, minVal, maxVal);
            greater = true;
        }
        if (treePtr->item <= maxVal) {
            counter += countInternal(treePtr->rightChildPtr, minVal, maxVal);
            if (greater) {
                counter += 1; // current node in specified range
            }
        }
        return counter;
    }
    return 0;
}

int BinarySearchTree::select(int anEntry) {
    int finish = 0;
    int index = findIndex(this->root, anEntry, finish) - 1;;
    // Value Not Found
    if (finish == 0) {
        return -1;
    } else {
        return index;
    }
}

int BinarySearchTree::findIndex(BinaryNode *treePtr, int anEntry, int &finish) {
    if (treePtr != NULL) {
        int inc = 0;
        inc += findIndex(treePtr->leftChildPtr, anEntry, finish);

        if (finish != 1) {
            inc += 1;
        }
        if (anEntry == treePtr->item) {
            finish = 1; // Do not increment count anymore
        }
        inc += findIndex(treePtr->rightChildPtr, anEntry, finish);
        return inc;
    }
    return 0;
}

int BinarySearchTree::successor(int anEntry) {
    return successorInternal(this->root, anEntry);
}

BinaryNode *BinarySearchTree::returnNode(BinaryNode *treePtr, const int &anEntry) {
    if (treePtr != NULL) {
        if (treePtr->item == anEntry) {
            return treePtr;
        } else if (anEntry < treePtr->item) {
            return returnNode(treePtr->leftChildPtr, anEntry);
        } else {
            return returnNode(treePtr->rightChildPtr, anEntry);
        }
    }
    // Node not found
    return NULL;
}

int BinarySearchTree::successorInternal(BinaryNode *treePtr, const int &anEntry) {
    BinaryNode *currNode = returnNode(treePtr, anEntry);
    if (currNode) {
        if (currNode->rightChildPtr == NULL) {
            // The successor is the first parent whose value is greater than that node
            BinaryNode *parentPtr = currNode->parentPtr;
            while (parentPtr != NULL) {
                if (currNode->item < parentPtr->item) {
                    int item = parentPtr->item;
                    return item;
                }
                parentPtr = parentPtr->parentPtr;
            }
            // We reach root, than our node was actually the last leaf
            return -2;
        }
        // if there is right child, move that right child and traverse to the left all the way to the end
        return traverseLeft(currNode->rightChildPtr, false)->item; // next successor
    }
    return -1; // currNode is NULL
}

// This method is optimized to use both remove and successorInternal methods
BinaryNode *BinarySearchTree::traverseLeft(BinaryNode *treePtr, bool deleteOp) {
    if (deleteOp) {
        treePtr->size -= 1;
    }
    if (treePtr->leftChildPtr != NULL) {
        return traverseLeft(treePtr->leftChildPtr, deleteOp);
    } else {
        return treePtr;
    }
}


// Destructor
BinarySearchTree::~BinarySearchTree() {
    destroyTree(this->root);
}


// Uses postorder traversal for the destroy operation
// (Visits first the left and right children and then the node)
void BinarySearchTree::destroyTree(BinaryNode *&treePtr) {
    if (treePtr != NULL) {
        destroyTree(treePtr->leftChildPtr);
        destroyTree(treePtr->rightChildPtr);
        delete treePtr;
        treePtr = NULL;
    }
}

