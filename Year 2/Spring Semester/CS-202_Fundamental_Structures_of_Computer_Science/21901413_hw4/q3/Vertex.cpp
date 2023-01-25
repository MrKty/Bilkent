/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Vertex Implementation which symbolizes airports
*/

#include "iostream"
#include "Vertex.h"

using namespace std;

Vertex::Vertex(int airportNum) {
    this->airportNum = airportNum;
    this->headEdge = NULL;
    this->flightNum = 0;
}

Vertex::Vertex() {
    this->flightNum = 0;
    this->headEdge = NULL;
}

void Vertex::addEdge(int destAirportNum, int duration, bool info, Edge *newEdge) {
    this->flightNum++;
    // To minimize search for already existed destAirportNum, sort them as min to max according to duration
    if (headEdge == NULL) {
        headEdge = newEdge;
    } else {
        Edge *edgePtr = headEdge;
        if (duration < edgePtr->nextDuration->duration) {
            newEdge->nextEdge = edgePtr;
            edgePtr->previousEdge = newEdge;
            this->headEdge = newEdge;
        } else {
            while (edgePtr->nextEdge != NULL && edgePtr->nextEdge->nextDuration->duration < duration) {
                edgePtr = edgePtr->nextEdge;
            }
            if (edgePtr->nextEdge != NULL) {
                if (edgePtr->nextEdge->destAirportNum == destAirportNum) {
                    // edge to given airport already exists, add the new duration to the duration list
                    edgePtr->nextEdge->addDuration(duration);
                    Vertex::deleteEdge(newEdge);
                } else {
                    newEdge->nextEdge = edgePtr->nextEdge;
                    edgePtr->nextEdge = newEdge;
                    newEdge->previousEdge = edgePtr;
                }
            } else {
                edgePtr->nextEdge = newEdge;
                newEdge->previousEdge = edgePtr;

            }
        }
    }
    if (info) {
        cout << "   The number of flights from " << this->airportNum << " is " << this->flightNum << "." << endl;
    }
}

void Vertex::listEdges() {
    Edge *edgePointer = this->headEdge;
    if (edgePointer == NULL) {
        cout << "No path from " << this->airportNum << endl;
    } else {
        while (edgePointer != NULL) {
            Duration *durationPtr = edgePointer->nextDuration;
            while (durationPtr != NULL) {
                cout << "   " << this->airportNum << " to " << edgePointer->destAirportNum << " with a duration of "
                     << durationPtr->duration << "." << endl;
                durationPtr = durationPtr->next;
            }
            edgePointer = edgePointer->nextEdge;
        }
        cout << "   The number of flights is " << this->flightNum << "." << endl;
    }
}

void Vertex::deleteEdge(Edge *edge) {
    if (edge->previousEdge != NULL) {
        edge->previousEdge->nextEdge = edge->nextEdge;
    }
    if (edge->nextEdge != NULL) {
        edge->nextEdge->previousEdge = edge->previousEdge;
    }
    delete edge;
}

Vertex::~Vertex() {
    this->headEdge = NULL;
}


