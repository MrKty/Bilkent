/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Duration Declaration which will be used as distance values between two airports (may be more than one)
*/

#ifndef DURATION_H
#define DURATION_H


class Duration {
public:
    Duration(int duration);
    int duration;
    Duration* next;
    virtual ~Duration();
};


#endif //DURATION_H
