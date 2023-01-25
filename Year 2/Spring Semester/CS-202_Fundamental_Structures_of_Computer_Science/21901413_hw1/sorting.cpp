/*
* Title: Sorting and Algorithm Efficiency
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 1
* Description: This file contains implementations of the global sorting functions, global auxiliary functions and file
* scope auxiliary functions
*/

#include <iomanip>
#include <iostream>
#include <cstdlib>
#include "sorting.h"

using namespace std;

// File scope auxiliary functions
void perturbArray(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size) {// Perturb unsorted array
    for (int i = 0; i < size / 20; ++i) {
        // Create random pair
        int rand1 = rand() % size;
        int rand2;
        do {
            rand2 = rand() % size;
        } while (rand1 == rand2);

        // Swap values of random indices
        int temp = arr1[rand1];
        arr1[rand1] = arr1[rand2];
        arr2[rand1] = arr2[rand2];
        arr3[rand1] = arr3[rand2];
        arr4[rand1] = arr4[rand2];

        arr1[rand2] = temp;
        arr2[rand2] = temp;
        arr3[rand2] = temp;
        arr4[rand2] = temp;
    }
}

void displayAnalysis(int arraySizes[], int arraySizeLength, const string &sortAlgorithm, int sortData[],
                     int sortDataInitialPos, double sortTimes[], int sortTimesInitialPos) {
    int positionCnt = sortDataInitialPos;
    int timePosCnt = sortTimesInitialPos;
    cout << "-----------------------------------------------------" << endl;
    cout << "Analysis of " << sortAlgorithm << " Sort" << endl;
    cout << setw(20) << left << "Array Size" << setw(20) << left << "Elapsed Time" << setw(20) << left << "compCount"
         << setw(20) << left << "moveCount" << endl;
    for (int i = 0; i < arraySizeLength; ++i) {
        if (i != 0) {
            positionCnt += 2; // each step take 2 index in data arrays
            timePosCnt += 1; // each step take 1 index in times arrays
        }
        cout << setw(20) << left << arraySizes[i] << setw(20) << left << sortTimes[timePosCnt] << setw(20) <<
             left << sortData[positionCnt] << setw(20) << left << sortData[positionCnt + 1] << endl;
    }
    cout << endl;
}

void swap(int &x, int &y, int &moveCount) {
    int temp = x;
    x = y;
    y = temp;
    moveCount += 3;
}

void merge(int *arr, int first, int mid, int last, int &compCount, int &moveCount) {

    int tempArray[last - first + 1];    // temporary array
    int first1 = first;    // beginning of first subarray
    int last1 = mid;        // end of first subarray
    int first2 = mid + 1;    // beginning of second subarray
    int last2 = last;        // end of second subarray
    int index = 0; // next available location in tempArray

    for (; (first1 <= last1) && (first2 <= last2); ++index) {
        if (arr[first1] < arr[first2]) {
            tempArray[index] = arr[first1];
            ++first1;
        } else {
            tempArray[index] = arr[first2];
            ++first2;
        }
        compCount++;
        moveCount++;
    }

    // finish off the first subarray, if necessary
    for (; first1 <= last1; ++first1, ++index) {
        tempArray[index] = arr[first1];
        moveCount++;
    }

    // finish off the second subarray, if necessary
    for (; first2 <= last2; ++first2, ++index) {
        tempArray[index] = arr[first2];
        moveCount++;
    }

    // copy the result back into the original array
    int arrIndex = first;
    int tempIndex = 0;
    for (; arrIndex <= last; ++arrIndex, ++tempIndex) {
        arr[arrIndex] = tempArray[tempIndex];
        moveCount++;
    }

}  // end merge

void mergesortInternal(int *arr, int first, int last, int &compCount, int &moveCount) {
    if (first < last) {
        int mid = (first + last) / 2;    // index of midpoint

        mergesortInternal(arr, first, mid, compCount, moveCount);

        mergesortInternal(arr, mid + 1, last, compCount, moveCount);

        // merge the two halves
        merge(arr, first, mid, last, compCount, moveCount);
    }
}  // end mergesort

void partition(int *arr, int first, int last,
               int &pivotIndex, int &compCount, int &moveCount) {
    // Precondition: theArray[first..last] is an array; first <= last.
    // Postcondition: Partitions theArray[first..last] such that:
    //   S1 = theArray[first..pivotIndex-1] < pivot
    //   theArray[pivotIndex] == pivot
    //   S2 = theArray[pivotIndex+1..last] >= pivot

    int pivot = arr[first]; // first index is pivot
    moveCount++;
    // initially, everything but pivot is in unknown
    int lastS1 = first;           // index of last item in S1
    int firstUnknown = first + 1; // index of first item in unknown

    // move one item at a time until unknown region is empty
    for (; firstUnknown <= last; ++firstUnknown) {
        // Invariant: theArray[first+1..lastS1] < pivot
        //            theArray[lastS1+1..firstUnknown-1] >= pivot

        compCount++;
        // move item from unknown to proper region
        if (arr[firstUnknown] < pivot) {    // belongs to S1
            ++lastS1;
            swap(arr[firstUnknown], arr[lastS1], moveCount);
        }    // else belongs to S2
    }
    // place pivot in proper position and mark its location
    swap(arr[first], arr[lastS1], moveCount);
    pivotIndex = lastS1;
}


void quicksortInternal(int *arr, int first, int last, int &compCount, int &moveCount) {
    // Precondition: arr[first..last] is an array.
    // Postcondition: arr[first..last] is sorted.
    int pivotIndex;

    if (first < last) {
        // create the partition: S1, pivot, S2
        partition(arr, first, last, pivotIndex, compCount, moveCount);

        // sort regions S1 and S2
        quicksortInternal(arr, first, pivotIndex - 1, compCount, moveCount);
        quicksortInternal(arr, pivotIndex + 1, last, compCount, moveCount);
    }
}

// Global auxiliary functions
void displayArray(const int *arr, const int size) {
    cout << "[ ";
    for (int i = 0; i < size - 1; ++i) {
        cout << arr[i] << ", ";
    }
    cout << arr[size - 1] << " ]" << endl;
}

void createRandomArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size) {
    for (int i = 0; i < size; ++i) {
        // values will have values from 0 to RAND_MAX (defined in cstdlib)
        int num = rand();
        arr1[i] = num;
        arr2[i] = num;
        arr3[i] = num;
        arr4[i] = num;
    }
}

void createAlmostSortedArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size) {
    // Create sorted array
    for (int i = 0; i < size; ++i) {
        arr1[i] = i;
        arr2[i] = i;
        arr3[i] = i;
        arr4[i] = i;
    }

    perturbArray(arr1, arr2, arr3, arr4, size);
}

void createAlmostUnsortedArrays(int *&arr1, int *&arr2, int *&arr3, int *&arr4, const int size) {
    // Create unsorted array
    for (int i = size - 1; i >= 0; --i) {
        arr1[size - 1 - i] = i;
        arr2[size - 1 - i] = i;
        arr3[size - 1 - i] = i;
        arr4[size - 1 - i] = i;
    }

    perturbArray(arr1, arr2, arr3, arr4, size);
}

void performanceAnalysis() {
    int sizes[] = {5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000};
    int creationMode = 1; // 1 - random, 2 - almost sorted, 3 - almost unsorted
    int sortAlg = 1; // 1 - insertion, 2 - bubble, 3 - merge, 4 - quick
    int *insertionData = new int[48]; // 8 array size * 3 creationMode * 2 data (comp count,move count)
    int *bubbleData = new int[48];
    int *mergeData = new int[48];
    int *quickData = new int[48];
    int positionCnt; // to put datas into correct position in sort data arrays above
    double *insertionTimes = new double[24]; // 8 array size * 3 creationMode * 1 data (ms)
    double *bubbleTimes = new double[24];
    double *mergeTimes = new double[24];
    double *quickTimes = new double[24];
    int timesPosCnt; // to put datas into correct position in sort time arrays above
    int *arr1;
    int *arr2;
    int *arr3;
    int *arr4;
    int compCount;
    int moveCount;
    clock_t startTime;
    double duration;
    for (int i = 1; i <= 3; ++i) { // 1 for each creation mode
        positionCnt = 16 * (i - 1);
        timesPosCnt = 8 * (i - 1);
        for (int j = 0; j < 8; ++j) {
            if (j != 0) {
                positionCnt += 2; // each step take 2 index in data arrays
                timesPosCnt++; // each step take 1 index in time arrays
            }
            arr1 = new int[sizes[j]];
            arr2 = new int[sizes[j]];
            arr3 = new int[sizes[j]];
            arr4 = new int[sizes[j]];
            switch (creationMode) {
                case 1:
                    createRandomArrays(arr1, arr2, arr3, arr4, sizes[j]);
                    break;
                case 2:
                    createAlmostSortedArrays(arr1, arr2, arr3, arr4, sizes[j]);
                    break;
                case 3:
                    createAlmostUnsortedArrays(arr1, arr2, arr3, arr4, sizes[j]);
                    break;
            }
            for (int k = 0; k < 4; ++k) { // 1 for each sorting algorithm
                startTime = clock(); // start timer
                switch (sortAlg) {
                    case 1:
                        insertionSort(arr1, sizes[j], compCount, moveCount);
                        duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC; // end timer
                        insertionTimes[timesPosCnt] = duration;
                        insertionData[positionCnt] = compCount;
                        insertionData[positionCnt + 1] = moveCount;
                        sortAlg = 2;
                        break;
                    case 2:
                        bubbleSort(arr2, sizes[j], compCount, moveCount);
                        duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC; // end timer

                        bubbleTimes[timesPosCnt] = duration;
                        bubbleData[positionCnt] = compCount;
                        bubbleData[positionCnt + 1] = moveCount;
                        sortAlg = 3;
                        break;
                    case 3:
                        mergeSort(arr3, sizes[j], compCount, moveCount);
                        duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC; // end timer

                        mergeTimes[timesPosCnt] = duration;
                        mergeData[positionCnt] = compCount;
                        mergeData[positionCnt + 1] = moveCount;
                        sortAlg = 4;
                        break;
                    case 4:
                        quickSort(arr4, sizes[j], compCount, moveCount);
                        duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC; // end timer
                        quickTimes[timesPosCnt] = duration;
                        quickData[positionCnt] = compCount;
                        quickData[positionCnt + 1] = moveCount;
                        sortAlg = 1;
                        break;
                }
                compCount = 0;
                moveCount = 0;
            }
            delete[] arr1;
            delete[] arr2;
            delete[] arr3;
            delete[] arr4;
        }

        creationMode++;
    }

    creationMode = 1;
    for (int i = 0; i < 3; ++i) {
        switch (creationMode) {
            case 1:
                cout << "Analysis using Random Arrays:" << endl;
                break;
            case 2:
                cout << "Analysis using Almost Sorted Arrays:" << endl;
                break;
            case 3:
                cout << "Analysis using Almost Unsorted Arrays:" << endl;
                break;
        }

        displayAnalysis(sizes, 8, "Insertion", insertionData, 16 * (creationMode - 1), insertionTimes,
                        8 * (creationMode - 1));
        displayAnalysis(sizes, 8, "Bubble", bubbleData, 16 * (creationMode - 1), bubbleTimes, 8 * (creationMode - 1));
        displayAnalysis(sizes, 8, "Merge", mergeData, 16 * (creationMode - 1), mergeTimes, 8 * (creationMode - 1));
        displayAnalysis(sizes, 8, "Quick", quickData, 16 * (creationMode - 1), quickTimes, 8 * (creationMode - 1));
        creationMode = creationMode % 3 + 1;
    }
    delete[] insertionData;
    delete[] bubbleData;
    delete[] mergeData;
    delete[] quickData;
    delete[] insertionTimes;
    delete[] bubbleTimes;
    delete[] mergeTimes;
    delete[] quickTimes;
}

// Global functions
void insertionSort(int *arr, const int size, int &compCount, int &moveCount) {
    for (int unsorted = 1; unsorted < size; ++unsorted) {
        int nextItem = arr[unsorted];
        moveCount++;
        int loc = unsorted;
        for (; (loc > 0); --loc) {
            compCount++;
            if (arr[loc - 1] > nextItem) {
                arr[loc] = arr[loc - 1];
                moveCount++;
            } else {
                break;
            }
        }
        arr[loc] = nextItem;
        moveCount++;
    }
}

void bubbleSort(int *arr, const int size, int &compCount, int &moveCount) {
    bool sorted = false;
    for (int pass = 1; (pass < size) && !sorted; pass++) {
        sorted = true;
        for (int index = 0; index < size - pass; index++) {
            int nextIndex = index + 1;
            if (arr[index] > arr[nextIndex]) {
                swap(arr[index], arr[nextIndex], moveCount);
                sorted = false; // signal exchange
            }
            compCount++;
        }
    }
}

void mergeSort(int *arr, const int size, int &compCount, int &moveCount) {
    mergesortInternal(arr, 0, size - 1, compCount, moveCount);
}

void quickSort(int *arr, const int size, int &compCount, int &moveCount) {
    quicksortInternal(arr, 0, size - 1, compCount, moveCount);
}


