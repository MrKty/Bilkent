/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Edge Declaration which will be used as distance between two airports
*/

#ifndef EDGE_H
#define EDGE_H

#include "Duration.h"


class Edge {
public:
    Edge(int destAirportNum, int duration);
    Edge();
    void addDuration(int duration);
    void deleteDurations();

    virtual ~Edge();
    int destAirportNum;
    Edge* nextEdge;
    Edge* pairEdge;
    Edge* previousEdge;
    int durationNum;
    Duration* nextDuration;
    bool visited;
private:
};


#endif //EDGE_H
