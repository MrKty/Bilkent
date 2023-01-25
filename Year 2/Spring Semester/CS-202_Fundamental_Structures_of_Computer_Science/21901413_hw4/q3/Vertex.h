/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Vertex Declarations which will be used as airports
*/

#ifndef VERTEX_H
#define VERTEX_H

#include "Edge.h"

class Vertex {
public:
    Vertex();

    Vertex(int airportNum);

    void addEdge(int destAirportNum, int duration, bool info, Edge *newEdge);

    void listEdges();



    static void deleteUnvisitedEdges(Edge *edge);

    int flightNum;
    int airportNum;
    Edge *headEdge;

    virtual ~Vertex();

    static void deleteEdge(Edge *edge);
};


#endif //VERTEX_H
