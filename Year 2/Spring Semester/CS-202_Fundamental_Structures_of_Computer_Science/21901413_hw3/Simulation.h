/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Simulation Header
*/

#ifndef HW3_SIMULATION_H
#define HW3_SIMULATION_H

#include <string>
#include "MaxHeap.h"

using namespace std;

class Simulation {
public:

    double simulate(int numOfComputers, Request *requests, int requestsSize, string &log);
    void createLog(int compId, int requestId, int time, int requestSentTime, string& log, double& avgWaitingTime);
};

#endif //HW3_SIMULATION_H
