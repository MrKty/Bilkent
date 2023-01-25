/*
* Title: Sorting and Algorithm Efficiency
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 1
* Description: This file aimed to test sorting functions
*/

#include <iostream>
#include "sorting.h"

using namespace std;


int main() {
    // Create Array
    int arr1[] = {9, 6, 7, 16, 18, 5, 2, 12, 20, 1,
                  16, 17, 4, 11, 13, 8};

    int arr2[] = {9, 6, 7, 16, 18, 5, 2, 12, 20, 1,
                  16, 17, 4, 11, 13, 8};
    int arr3[] = {9, 6, 7, 16, 18, 5, 2, 12, 20, 1,
                  16, 17, 4, 11, 13, 8};
    int arr4[] = {9, 6, 7, 16, 18, 5, 2, 12, 20, 1,
                  16, 17, 4, 11, 13, 8};

    int compCount = 0;
    int moveCount = 0;

    // Sort Array using Insertion Sort
    cout << "-----------------------------------------------------" << endl;
    cout << "Analysis of Insertion Sort:" << endl;
    cout << "Unsorted Array: ";
    displayArray(arr1, 16);
    insertionSort(arr1, 16, compCount, moveCount);
    cout << "Sorted Array: ";
    displayArray(arr1, 16);
    cout << "Key Comparisons: " << compCount << endl << "Number of Data Moves: "
         << moveCount << endl;
    cout << "-----------------------------------------------------" << endl;

    compCount = 0;
    moveCount = 0;

    // Sort Array using Bubble Sort
    cout << "Analysis of Bubble Sort:" << endl;
    cout << "Unsorted Array: ";
    displayArray(arr2, 16);
    bubbleSort(arr2, 16, compCount, moveCount);
    cout << "Sorted Array: ";
    displayArray(arr2, 16);
    cout << "Key Comparisons: " << compCount << endl << "Number of Data Moves: "
         << moveCount << endl;
    cout << "Sorted Array: ";
    displayArray(arr2, 16);
    cout << "-----------------------------------------------------" << endl;

    compCount = 0;
    moveCount = 0;

    // Sort Array using Merge Sort
    cout << "Analysis of Merge Sort:" << endl;
    cout << "Unsorted Array: ";
    displayArray(arr3, 16);
    mergeSort(arr3, 16, compCount, moveCount);
    cout << "Sorted Array: ";
    displayArray(arr3, 16);
    cout << "Key Comparisons: " << compCount << endl << "Number of Data Moves: "
         << moveCount << endl;
    cout << "-----------------------------------------------------" << endl;

    compCount = 0;
    moveCount = 0;

    // Sort Array using Quick Sort
    cout << "Analysis of Quick Sort:" << endl;
    cout << "Unsorted Array: ";
    displayArray(arr4, 16);
    quickSort(arr4, 16, compCount, moveCount);
    cout << "Sorted Array: ";
    displayArray(arr4, 16);
    cout << "Key Comparisons: " << compCount << endl << "Number of Data Moves: "
         << moveCount << endl;
    cout << "-----------------------------------------------------" << endl;


    /*
    cout << "Calling Performance Analysis Function: " << endl;
    cout << endl;
    performanceAnalysis();
    */

    return 0;
}
