//
// Omer Oktay Gultekin 21901413
//

#ifndef ALGEBRAICEXPRESSION_H
#define ALGEBRAICEXPRESSION_H

#include <iostream>
#include "Stack.h"

using namespace std;


// It converts an infix expression exp to its equivalent prefix form.
string infix2prefix(const string exp);

// It converts an infix expression exp to its equivalent postfix form.
string infix2postfix(const string exp);

// It converts a prefix expression exp to its equivalent infix form.
string prefix2infix(const string exp);

// It converts a prefix expression exp to its equivalent postfix form.
string prefix2postfix(const string exp);

// It converts a postfix expression exp to its equivalent infix form.
string postfix2infix(const string exp);

// It converts a postfix expression exp to its equivalent prefix form.
string postfix2prefix(const string exp);

//It evaluates an infix expression.
double evaluateInfix(const string exp);

//It evaluates a prefix expression.
double evaluatePrefix(const string exp);

//It evaluates a postfix expression.
double evaluatePostfix(const string exp);

//Arithmetic operations
double doCalculation(const char &op, const double &operand1, const double &operand2);

bool isOperator(const string &op);

int precedence(const string &op);

string &reverseString(const string &exp, string &reversed);

string doubleToString(double res);

void pushNumToStack(const string &reversed, int &i, string &tempStr, Stack& stack);

#endif //ALGEBRAICEXPRESSION_H
