/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Graph Implementation which will be used as airport management system
*/

#include "Graph.h"
#include <sstream>

// To convert int to string
#define SSTR(x) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()


Graph::Graph(int vertexNum) {
    this->vertexNum = vertexNum;
    vertexes = new Vertex[vertexNum];
    for (int i = 0; i < vertexNum; ++i) {
        vertexes[i].airportNum = i;
    }
    this->totalCost = 0;
}

void Graph::insertEdge(int sourceAirportNum, int destAirportNum, int duration) {
    // Add the edge in two way since the graph is undirected
    cout << "Inserted a new flight between " << sourceAirportNum << " and " << destAirportNum << "." << endl;
    Edge *newEdge = new Edge(destAirportNum, duration);
    Edge *newEdgePair = new Edge(sourceAirportNum, duration);
    vertexes[sourceAirportNum].addEdge(destAirportNum, duration, true, newEdge);
    vertexes[destAirportNum].addEdge(sourceAirportNum, duration, false, newEdgePair);

    // Pair edge will be used to simplify minimization process
    if (newEdge != NULL && newEdgePair != NULL) {
        newEdge->pairEdge = newEdgePair;
        newEdgePair->pairEdge = newEdge;
        this->totalCost += duration;
    }
}

void Graph::listVertexEdges(int airportNum) {
    cout << "List of flights from " << airportNum << ":" << endl;
    this->vertexes[airportNum].listEdges();
}

// Auxiliary Helper Method to find Shortest Path
int Graph::findShortestPath(int srcAirportNum, Vertex currAirport, int destAirportNum, bool &pathFound, string &candidate,
                        string &track,
                        int *&visitedVertexes) {
    int min = 0x7fffffff; // INT_MAX
    int candMin;
    Edge *edgePtr = currAirport.headEdge;
    if (edgePtr == NULL) {
        pathFound = false;
        return 0;
    }
    if (currAirport.airportNum == destAirportNum) {
        pathFound = true;
        return 0;
    }

    while (edgePtr != NULL) {
        visitedVertexes[currAirport.airportNum] = 1;
        if (visitedVertexes[edgePtr->destAirportNum] == 1) {
            edgePtr = edgePtr->nextEdge;
            continue;
        }
        edgePtr->visited = true;
        edgePtr->pairEdge->visited = true;
        candidate += "   " + SSTR(currAirport.airportNum) + " to " + SSTR(edgePtr->destAirportNum) +
                     " with a duration " +
                     SSTR(edgePtr->nextDuration->duration) + "\n";
        candMin = findShortestPath(srcAirportNum, this->vertexes[edgePtr->destAirportNum], destAirportNum, pathFound,
                                   candidate,
                                   track, visitedVertexes);
        if (candMin < min && pathFound) { // the shortest path found
            min = edgePtr->nextDuration->duration + candMin;
            track = candidate;
            break;
        } else {
            candidate = "";
        }
        edgePtr = edgePtr->nextEdge;
    }
    edgePtr = currAirport.headEdge;
    while (edgePtr != NULL) {
        if (edgePtr->visited) {
            edgePtr->visited = false;
            edgePtr->pairEdge->visited = false;
            visitedVertexes[edgePtr->destAirportNum] = 0;
        }
        edgePtr = edgePtr->nextEdge;
    }
    return min;
}

void Graph::showShortestPath(int srcAirportNum, int destAirportNum) {
    string track = "";
    string candidate = "";
    bool pathFound = false;
    int *visitedVertexes = new int[this->vertexNum];
    for (int i = 0; i < this->vertexNum; ++i) {
        visitedVertexes[i] = 0;
    }
    int minPath = this->findShortestPath(srcAirportNum, this->vertexes[srcAirportNum], destAirportNum, pathFound,
                                         candidate, track,
                                         visitedVertexes);
    if (track == "") {
        cout << "No paths from " << srcAirportNum << " to " << destAirportNum << "." << endl;
    } else {
        cout << "The shortest path from " << srcAirportNum << " to " << destAirportNum << ":" << endl;
        cout << track;
        cout << "   Total flight duration of path: " << minPath << endl;
    }
    delete[] visitedVertexes;
}

// Uses modified Prim's Algorithm for my Graph structure
void Graph::minimizeGraph() {
    int visitedVertexes[this->vertexNum];
    for (int i = 0; i < this->vertexNum; ++i) {
        visitedVertexes[i] = 0;
    }
    int unvisitedFirstVertexIndex = 1; // Airport 0 is assumed as visited

    Edge *lastVisitedEdges[this->vertexNum];

    for (int i = 0; i < this->vertexNum; ++i) {
        lastVisitedEdges[i] = this->vertexes[i].headEdge;
    }
    int minimizedCost = 0;
    while (unvisitedFirstVertexIndex < this->vertexNum) {
        int i = 0;
        int min = 0x7fffffff; // INT_MAX
        int minEdgeVertex = 0;
        while (i < unvisitedFirstVertexIndex) {
            if (lastVisitedEdges[visitedVertexes[i]] == NULL) {
                i++;
                continue;
            }
            if (lastVisitedEdges[visitedVertexes[i]]->visited) {
                lastVisitedEdges[visitedVertexes[i]] = lastVisitedEdges[visitedVertexes[i]]->nextEdge;
                continue;
            }
            bool notVisited = true;
            for (int j = 0; j < unvisitedFirstVertexIndex; ++j) {
                if (visitedVertexes[j] == lastVisitedEdges[visitedVertexes[i]]->destAirportNum) {
                    // Vertex already visited
                    notVisited = false;
                    break;
                }
            }
            if (!notVisited) {
                i++;
                continue;
            }
            if (lastVisitedEdges[i]->nextDuration->duration < min) {
                min = lastVisitedEdges[visitedVertexes[i]]->nextDuration->duration;
                minEdgeVertex = visitedVertexes[i];
            }
            i++;
        }
        visitedVertexes[unvisitedFirstVertexIndex] = lastVisitedEdges[minEdgeVertex]->destAirportNum;
        lastVisitedEdges[minEdgeVertex]->visited = true;
        lastVisitedEdges[minEdgeVertex]->pairEdge->visited = true;
        lastVisitedEdges[minEdgeVertex] = lastVisitedEdges[minEdgeVertex]->nextEdge;
        unvisitedFirstVertexIndex++;
        minimizedCost += min;
    }
    // Delete unvisited graphs and reset the visited property of edges
    for (int i = 0; i < this->vertexNum; ++i) {
        Edge *curEdge = this->vertexes[i].headEdge;
        while (curEdge != NULL) {
            if (!curEdge->visited) {
                Edge *deletedEdge = curEdge;
                curEdge = curEdge->nextEdge;
                this->vertexes[i].flightNum--;
                Vertex::deleteEdge(deletedEdge);
            } else {
                curEdge->visited = false;
                curEdge = curEdge->nextEdge;
            }
        }
    }

    cout << "Total cost of operations before minimization: " << SSTR(this->totalCost) << endl;
    cout << "Total cost of operations after minimization: " << SSTR(minimizedCost) << endl;
    this->totalCost = minimizedCost;
}

Graph::~Graph() {
    delete[] this->vertexes;
}

void Graph::deleteGraph() {
    // For each vertex, delete the edges
    for (int i = 0; i < this->vertexNum; ++i) {
        Edge *tempPtr = this->vertexes[i].headEdge;
        while (tempPtr != NULL) {
            Edge *deletedPtr = tempPtr;
            tempPtr = tempPtr->nextEdge;
            Vertex::deleteEdge(deletedPtr);
        }
    }
}

