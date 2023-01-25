/*
* Title: Trees
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 2
* Description: This program aims to examine how the height of BST behaves on insertion/deletion
* of random sequence of numbers
*/

#include <cstdlib>
#include "BinarySearchTree.h"
#include "analyze.h"
#include "iostream"
using namespace std;

void analyzeBST(){
    int size = 10000;
    int* randArr = new int[size];
    for (int i = 0; i < size; ++i) {
        // values will have values from 0 to RAND_MAX (defined in cstdlib)
        randArr[i] = rand();
    }
    BinarySearchTree binarySearchTree;
    cout << "Random BST size vs. height (Insertion)" << endl;
    cout << "-----------------------------------------" << endl;
    for (int i = 1; i <= size; ++i) {
        binarySearchTree.add(randArr[i - 1]);

        if (i % 100 == 0){
            cout << binarySearchTree.getNumberOfNodes() << " " << binarySearchTree.getHeight() << endl;
        }
    }
    cout << endl;

    shuffleArray(randArr, size);
    cout << "Random BST size vs. height (Deletion)" << endl;
    cout << "-----------------------------------------" << endl;
    for (int i = 1; i <= size; ++i) {
        binarySearchTree.remove(randArr[i - 1]);
        if (i % 100 == 0){
            cout << binarySearchTree.getNumberOfNodes() << " " << binarySearchTree.getHeight() << endl;
        }
    }
    cout << endl;
    delete []randArr;
}

void shuffleArray(int *&arr, int size) {// shuffle given array
    for (int i = 0; i < size / 2; ++i) {
        // Create random pair
        int rand1 = rand() % size;
        int rand2;
        do {
            rand2 = rand() % size;
        } while (rand1 == rand2);

        // Swap values of random indices
        int temp = arr[rand1];
        arr[rand1] = arr[rand2];
        arr[rand2] = temp;
    }
}