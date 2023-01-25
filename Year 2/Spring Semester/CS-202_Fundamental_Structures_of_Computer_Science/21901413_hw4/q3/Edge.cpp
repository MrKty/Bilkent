/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Edge Implementation which will be used as distance between two airports
*/

#include <cwchar>
#include "Edge.h"

Edge::Edge(int destAirportNum, int duration) {
    this->destAirportNum = destAirportNum;
    this->nextDuration = new Duration(duration);
    this->durationNum = 1;
    this->nextEdge = NULL;
    this->previousEdge = NULL;
    this->visited = false;
}

void Edge::addDuration(int duration) {
    this->durationNum++;
    Duration* newDuration = new Duration(duration);
    // Root can't be Null since constructor create at least one
    Duration* durationPtr = this->nextDuration;

    // Make durations sorted by min to max so that we can use the first value when finding shortest path
    if(duration < durationPtr->duration){
        newDuration->next = durationPtr;
        this->nextDuration = newDuration;
    }else if(duration == durationPtr->duration){
        return;
    }else{
        while(durationPtr->next != NULL && durationPtr->next->duration <= duration){
            durationPtr = durationPtr->next;
        }
        if(durationPtr->next != NULL){
            newDuration->next = durationPtr->next;
            durationPtr->next = newDuration;
        }else{
            durationPtr->next = newDuration;
        }
    }
}

Edge::Edge() {
    this->nextDuration = NULL;
    this->nextEdge = NULL;
    this->previousEdge = NULL;
    this->visited = false;
}

Edge::~Edge() {
    this->nextEdge = NULL;
    this->previousEdge = NULL;
    this->pairEdge = NULL;
    this->deleteDurations();
}

void Edge::deleteDurations() {
    Duration* durationPtr = this->nextDuration;
    Duration* deletePtr;
    while(durationPtr != NULL){
        deletePtr = durationPtr;
        durationPtr = durationPtr->next;
        delete deletePtr;
    }
}