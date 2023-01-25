/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Main Program
*/
#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdlib>
#include "Simulation.h"

using namespace std;

int main(int argc, char **argv) {
    string fileName = argv[1];
    double desiredAvgWaitingTime = atof(argv[2]);


    // declaring character array
    char char_array[fileName.length() + 1];
    // copying the contents of the
    // string to char array to pass it to the indata
    strcpy(char_array, fileName.c_str());
    ifstream indata; // indata is like cin
    string line; // variable for input value
    indata.open(char_array); // opens the file

    if (!indata) { // file couldn't be opened
        cerr << "Error: file could not be opened" << endl;
        exit(1);
    }

    int requestSize;
    int counter = 0;
    indata >> requestSize;
    Request *requests = new Request[requestSize];

    while (!indata.eof()) { // keep reading until end-of-file
        int id, priority, sentTime, processTime;
        indata >> id;
        indata >> priority;
        indata >> sentTime;
        indata >> processTime;
        Request request(id, priority, sentTime, processTime);
        requests[counter] = request;
        counter++;
    }
    indata.close();
    Simulation simulation;
    int computerCounter = 1;
    double avgWaitingTime;
    string log;
    do {
        avgWaitingTime = simulation.simulate(computerCounter, requests, requestSize, log);
        computerCounter++;
    } while (avgWaitingTime > desiredAvgWaitingTime &&
             computerCounter <= requestSize); // the second case can occur if you want negative avgWaitingTime
    cout << "Minimum number of computers required:" << computerCounter - 1 << endl;
    cout << endl;
    cout << "Simulation with " << computerCounter - 1 << " computers:" << endl << endl;
    cout << log << endl;
    cout << "Average waiting time: " << avgWaitingTime << " ms" << endl;
    delete[] requests;
    return 0;
}
