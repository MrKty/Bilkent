/*
* Title: Trees
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 2
* Description: BinaryNode class function implementations
*/

#include "BinaryNode.h"

BinaryNode::BinaryNode() {
    this->item = -1;
    this->leftChildPtr = NULL;
    this->rightChildPtr = NULL;
    this->parentPtr = NULL;
    this->size = 0;
}

BinaryNode::BinaryNode(const TreeItemType &nodeItem, BinaryNode *parent, BinaryNode *left, BinaryNode *right) {
    this->item = nodeItem;
    this->parentPtr = parent;
    this->leftChildPtr = left;
    this->rightChildPtr = right;
    this->size = 0;
    if (leftChildPtr != NULL){
        size += leftChildPtr->size;
        size += 1; // add leftChild as well
    }
    if (rightChildPtr != NULL){
        size += rightChildPtr->size;
        size += 1; // add rightChild as well
    }
}

// This method DO NOT DELETE parent and child classes.
// It is a responsibility of BinarySearchTree to handle those
BinaryNode::~BinaryNode() {
    if (this != NULL){
        this->parentPtr = NULL;
        this->rightChildPtr = NULL;
        this->leftChildPtr = NULL;
    }
}
