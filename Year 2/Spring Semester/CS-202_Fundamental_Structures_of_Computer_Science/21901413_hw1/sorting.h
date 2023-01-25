/*
* Title: Sorting and Algorithm Efficiency
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 1
* Description: This file contains headers of the global functions
*/

#ifndef SORTING_H
#define SORTING_H

void insertionSort(int *arr, const int size, int &compCount, int &moveCount);

void bubbleSort(int *arr, const int size, int &compCount, int &moveCount);

void mergeSort(int *arr, const int size, int &compCount, int &moveCount);

void quickSort(int *arr, const int size, int &compCount, int &moveCount);

void displayArray(const int *arr, const int size);

void createRandomArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size);

void createAlmostSortedArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size);

void createAlmostUnsortedArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size);

void performanceAnalysis();


#endif //SORTING_H
