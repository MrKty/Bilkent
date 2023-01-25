/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Request Implementation
*/

#include "Request.h"

Request::Request(int id, int priority, int sentTime, int processTime) : id(id), priority(priority),
                                                                        sentTime(sentTime),
                                                                        processTime(processTime) {}

Request::Request() : id(), priority(), sentTime(), processTime() {}

int Request::getPriority() const {
    return priority;
}

int Request::getSentTime() const {
    return sentTime;
}

int Request::getProcessTime() const {
    return processTime;
}


int Request::getId() const {
    return id;
}


/*void Request::linkElement(Request *request) {
    this->next = request;
}*/
/*

Request::~Request() {
    this->next = NULL;
}

Request *Request::deleteRequest() {
    Request* nextRequest; // in linked list
    nextRequest = this->next;
    delete this;
    return nextRequest;
}
*/
