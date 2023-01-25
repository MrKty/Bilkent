/*
* Title: Heaps, Priority Queues
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 3
* Description: Computer Class Header
*/

#ifndef COMPUTER_H
#define COMPUTER_H


class Computer {
    int id;
    int finishTime;
public:
    Computer(int id, int finishTime);

    Computer();

    int getId() const;

    int getFinishTime() const;

    void setFinishTime(int finishTime);

};


#endif //HW3_COMPUTER_H
