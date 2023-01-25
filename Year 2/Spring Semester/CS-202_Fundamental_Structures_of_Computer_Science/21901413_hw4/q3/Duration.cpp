/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Duration Implementation which will be used as distance values between two airports (may be more than one)
*/
#include <cwchar>
#include "Duration.h"

Duration::Duration(int duration) {
    this->duration = duration;
    this->next = NULL;
}

Duration::~Duration() {
    this->next = NULL;
}


