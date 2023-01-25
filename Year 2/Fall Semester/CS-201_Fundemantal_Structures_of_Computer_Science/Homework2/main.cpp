// Omer Oktay Gultekin 21901413
#include <iostream>

using namespace std;

int linearSearch(const long[], long, const long[], long);

int binarySearch(const long[], long, const long[], long);

int freqTableSearch(const long[], long, const long[], long, long[]);

void initializeArrays(long n, long m, long *arr1, long *arr2);

int main() {

    double duration;
    clock_t startTime;
    long nValues[] = {50000, 100000, 250000, 500000, 750000, 1000000, 2500000, 5000000, 7500000, 10000000};
    long mValues[] = {1000, 10000};

    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < 10; ++j) {
            long *arr1 = new long[nValues[j]];
            long *arr2 = new long[mValues[i]];
            initializeArrays(nValues[j], mValues[i], arr1, arr2);

            //Linear Search
            startTime = clock();

            linearSearch(arr1, nValues[j], arr2, mValues[i]);

            duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC;
            cout << "Linear Search execution for n = " << nValues[j] << " and m = " << mValues[i] << " took "
                 << duration << " milliseconds." << endl;

            //Binary Search
            startTime = clock();

            binarySearch(arr1, nValues[j], arr2, mValues[i]);

            duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC;
            cout << "Binary Search execution for n = " << nValues[j] << " and m = " << mValues[i] << " took "
                 << duration << " milliseconds." << endl;

            //Freq Table Search
            long *freqTable = new long[arr1[nValues[j] - 1]];
            startTime = clock();

            freqTableSearch(arr1, nValues[j], arr2, mValues[i], freqTable);

            duration = 1000 * double(clock() - startTime) / CLOCKS_PER_SEC;
            cout << "Frequency Table Search execution for n = " << nValues[j] << " and m = " << mValues[i] << " took "
                 << duration << " milliseconds." << endl;
            cout << endl;

            delete[] arr1;
            delete[] arr2;
            delete[] freqTable;
        }
    }

    return 0;
}

void initializeArrays(long n, long m, long *arr1, long *arr2) {
    long cnt = 0;
    for (long i = 0; i < n * 100; i += 10) {
        arr1[cnt] = i;
        cnt++;
        if (cnt == n) {
            break;
        }
    }
    int test = 0;
    for (long i = 0; i < m / 2; i += 1) {
        arr2[i] = arr1[i];
        test++;
    }
    cnt = m / 2;
    for (long i = n - (m / 2); i < n; i += 1) {
        arr2[cnt] = arr1[i];
        cnt++;
    }
}


int linearSearch(const long arr1[], long size1, const long arr2[], long size2) {
    bool found = false;
    for (int i = 0; i < size2; ++i) {
        for (int j = 0; j < size1; ++j) {
            if (arr2[i] == arr1[j]) {
                found = true;
                break;
            }
        }
        if (found) {
            found = false;
        } else {
            return 0;
        }
    }
    return 1;
}

long internalBinarySearch(const long arr[], long l, long r, long x) {
    if (r >= l) {
        long m = l + (r - l) / 2;

        if (arr[m] == x) {
            return m;
        } else if (arr[m] > x) {
            return internalBinarySearch(arr, l, m - 1, x);
        }
        return internalBinarySearch(arr, m + 1, r, x);

    }
    // element is not present in arr
    return -1;
}

int binarySearch(const long arr1[], long size1, const long arr2[], long size2) {
    long found;
    for (int i = 0; i < size2; ++i) {
        found = internalBinarySearch(arr1, 0, size1, arr2[i]);
        if (found == -1) {
            return 0;
        }
    }
    return 1;
}

int freqTableSearch(const long arr1[], long size1, const long arr2[], long size2, long freqTable[]) {
    for (int i = 0; i < size1; ++i) {
        freqTable[arr1[i]]++;
    }
    for (int i = 0; i < size2; ++i) {
        if (freqTable[arr2[i]] > 0) {
            freqTable[arr2[i]]--;
        } else {
            return 0;
        }
    }
    return 1;
}