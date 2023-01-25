/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Simulation Class Implementation
*/

#include "Simulation.h"
#include "Computer.h"
#include "MinHeap.h"
#include <iostream>
#include <sstream>

// To convert int to string
#define SSTR(x) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()

using namespace std;

double Simulation::simulate(int numOfComputers, Request *requests, int requestsSize, std::string &log) {
    MaxHeap requestHeap(requestsSize);
    MinHeap availableCompHeap(numOfComputers);
    MinHeap busyCompHeap(numOfComputers);
    log = "";
    double avgWaitingTime = 0;
    int currRequestIndex = 0;
    int time = 0;
    // Initialize computers
    for (int i = 0; i < numOfComputers; ++i) {
        Computer computer(i, 0);
        availableCompHeap.heapInsert(computer, false);
    }

    while (currRequestIndex < requestsSize || !requestHeap.heapIsEmpty()) {
        // First put requests happen from last times to that time
        while (currRequestIndex < requestsSize && requests[currRequestIndex].getSentTime() <= time) {
            requestHeap.heapInsert(requests[currRequestIndex]);
            currRequestIndex++;
        }
        // Put any finished computers to the available computers
        while (!busyCompHeap.heapIsEmpty()) {
            Computer potentialAvailableComp = busyCompHeap.peekHead();
            if (potentialAvailableComp.getFinishTime() <= time) {
                busyCompHeap.heapDelete(potentialAvailableComp, true);
                availableCompHeap.heapInsert(potentialAvailableComp, false);
            } else {
                break;
            }
        }

        // Check if requestHeap is empty
        if (requestHeap.heapIsEmpty() && currRequestIndex < requestsSize) {
            time = requests[currRequestIndex].getSentTime();
        } else {
            while (!availableCompHeap.heapIsEmpty() && !requestHeap.heapIsEmpty()) {
                Computer availableComp;
                Request handledRequest;
                availableCompHeap.heapDelete(availableComp, false);
                requestHeap.heapDelete(handledRequest);
                availableComp.setFinishTime(time + handledRequest.getProcessTime());
                busyCompHeap.heapInsert(availableComp, true);
                this->createLog(availableComp.getId(), handledRequest.getId(), time, handledRequest.getSentTime(), log,
                                avgWaitingTime);
            }

            if (!busyCompHeap.heapIsEmpty()) {
                Computer nextComp = busyCompHeap.peekHead();
                if (currRequestIndex != requestsSize) {
                    time = min(nextComp.getFinishTime(), requests[currRequestIndex].getSentTime());
                } else {
                    time = nextComp.getFinishTime();
                }
            } else {
                if (currRequestIndex != requestsSize) {
                    time = requests[currRequestIndex].getSentTime();
                }
            }
        }
    }

    return avgWaitingTime / requestsSize;
}

void Simulation::createLog(int compId, int requestId, int time, int requestSentTime, string &log,
                           double &avgWaitingTime) {
    int waitingTime = time - requestSentTime;
    log += "Computer ";
    log += SSTR(compId);
    log += " takes request ";
    log += SSTR(requestId);
    log += " at ms ";
    log += SSTR(time);
    log += " (wait: ";
    log += SSTR(waitingTime);
    log += " ms)\n";
    avgWaitingTime += waitingTime;
}

