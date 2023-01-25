/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Request Header
*/


#ifndef REQUEST_H
#define REQUEST_H

class Request {
    int id;
    int priority;
    int sentTime;
    int processTime;

public:
    Request(int id, int priority, int sentTime, int processTime);

    int getPriority() const;

    int getSentTime() const;

    int getProcessTime() const;

    int getId() const;

    Request();

};

#endif
