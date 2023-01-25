/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Graph Declaration which will be used as airport management system
*/
#ifndef GRAPH_H
#define GRAPH_H

#include "Vertex.h"
#include "iostream"

using namespace std;

class Graph {
public:
    Graph(int vertexNum);

    void insertEdge(int sourceAirportNum, int destAirportNum, int duration);

    void listVertexEdges(int airportNum);

    void showShortestPath(int srcAirportNum, int destAirportNum);

    void minimizeGraph();

    void deleteGraph();

private:
    int findShortestPath(int srcAirportNum, Vertex currAirport, int destAirportNum, bool &pathFound, string &candidate,
                         string &track, int *&visitedVertexes);

    int vertexNum;
    int totalCost;
    Vertex *vertexes;
public:
    virtual ~Graph();
};

#endif //GRAPH_H
