/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Computer Class Implementation
*/

#include "Computer.h"

Computer::Computer(int id, int finishTime) : id(id), finishTime(finishTime) {}

int Computer::getId() const {
    return id;
}


int Computer::getFinishTime() const {
    return finishTime;
}


void Computer::setFinishTime(int finishTime) {
    Computer::finishTime = finishTime;
}

Computer::Computer() : id(0), finishTime(0) {}

