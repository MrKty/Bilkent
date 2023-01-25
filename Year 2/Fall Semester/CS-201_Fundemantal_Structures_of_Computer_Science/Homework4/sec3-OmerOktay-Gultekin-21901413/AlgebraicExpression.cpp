//
// Omer Oktay Gultekin 21901413
//

#include "AlgebraicExpression.h"
#include "Stack.h"
#include <cstdlib>
#include <sstream>

// It converts an infix expression exp to its equivalent prefix form.
string infix2prefix(const string exp) {
    return postfix2prefix(infix2postfix(exp));
}

// It converts an infix expression exp to its equivalent postfix form.
string infix2postfix(const string exp) {
    string result;
    Stack stack;
    string tempStr;
    for (int i = 0; i < exp.length(); i++) {
        tempStr = exp[i];
        if (isOperator(tempStr)) {
            string top;
            stack.getTop(top);
            while (!stack.isEmpty() && top != "(" && precedence(tempStr) <= precedence(top)) {
                result = result + " " + top;
                stack.pop();
                stack.getTop(top);
            }
            stack.push(tempStr);
            result += " ";
        } else if (exp[i] == '(') {
            stack.push(tempStr);
        } else if (exp[i] == ')') {
            string top;
            bool isEmpty = !(stack.getTop(top));
            while (!isEmpty && top != "(") {
                stack.pop(top);
                result = result + " " + top;
                isEmpty = !(stack.getTop(top));
            }
            stack.pop();
        } else if (exp[i] != ' ') {
            result += exp[i];
        }
    }
    while (!stack.isEmpty()) {
        string top;
        stack.pop(top);
        if (isOperator(top)) {
            result = result + " " + top;
        }
    }
    return result;
}

// It converts a prefix expression exp to its equivalent infix form.
string prefix2infix(const string exp) {
    return postfix2infix(prefix2postfix(exp));
}

// It converts a prefix expression exp to its equivalent postfix form.
string prefix2postfix(const string exp) {
    string reversed;
    reversed = reverseString(exp, reversed);
    Stack stack;
    int i = 0;
    while (i < reversed.length()) {
        string tempStr;
        tempStr = reversed[i];
        if (isOperator(tempStr)) {
            string op1;
            string op2;
            stack.pop(op1);
            stack.pop(op2);
            string str = op1 + " " + op2 + " " + tempStr;
            stack.push(str);
        } else if (tempStr != " ") {
            pushNumToStack(reversed, i, tempStr, stack);
        }
        i++;
    }
    string result;
    stack.getTop(result);
    return result;
}


// It converts a postfix expression exp to its equivalent infix form.
string postfix2infix(const string exp) {
    Stack stack;
    int i = 0;
    while (i < exp.length()) {
        string tempStr;
        tempStr = exp[i];
        if (isOperator(tempStr)) {
            string op1;
            string op2;
            stack.pop(op1);
            stack.pop(op2);
            stack.push("( " + op2 + " " + tempStr + " " + op1 + " )");
        } else if (tempStr != " ") {
            pushNumToStack(exp, i, tempStr, stack);
        }
        i++;
    }
    string result;
    stack.getTop(result);
    return result;
}

// It converts a postfix expression exp to its equivalent prefix form.
string postfix2prefix(const string exp) {
    Stack stack;
    int i = 0;
    while (i < exp.length()) {
        string tempStr;
        tempStr = exp[i];
        if (isOperator(tempStr)) {
            string op1;
            string op2;
            stack.pop(op1);
            stack.pop(op2);
            string expression = tempStr + " " + op2 + " " + op1;
            stack.push(expression);
        } else if (tempStr != " ") {
            pushNumToStack(exp, i, tempStr, stack);
        }
        i++;
    }
    string result;
    stack.getTop(result);
    return result;
}

//It evaluates an infix expression.
double evaluateInfix(const string exp) {
    return evaluatePrefix(infix2prefix(exp));
}

//It evaluates a prefix expression.
double evaluatePrefix(const string exp) {
    string reversed;
    reversed = reverseString(exp, reversed);
    Stack stack;
    int i = 0;
    while (i < reversed.length()) {
        string tempStr;
        tempStr = reversed[i];
        if (isOperator(tempStr)) {
            string op1;
            string op2;
            stack.pop(op1);
            stack.pop(op2);
            double res = doCalculation(reversed[i], strtod(op1.c_str(), NULL),strtod(op2.c_str(), NULL));
            stack.push(doubleToString(res));
        } else if (tempStr != " ") {
            pushNumToStack(reversed, i, tempStr, stack);
        }
        i++;
    }
    string result;
    stack.getTop(result);
    return strtod(result.c_str(), NULL);
}

//It evaluates a postfix expression.
double evaluatePostfix(const string exp) {
    return evaluatePrefix(postfix2prefix(exp));
}

double doCalculation(const char &op, const double &operand1, const double &operand2) {
    if (op == 42) {
        return operand1 * operand2;
    } else if (op == 43) {
        return operand1 + operand2;
    } else if (op == 45) {
        return operand1 - operand2;
    } else if (op == 47) {
        return operand1 / operand2;
    }
}

bool isOperator(const string &op) {
    if (op == "+" || op == "-" || op == "*" || op == "/") {
        return true;
    }
    return false;
}

int precedence(const string &op) {
    if (op == "+" || op == "-") {
        return 1;
    } else if (op == "*" || op == "/") {
        return 2;
    }
    return 0;
}

string &reverseString(const string &exp, string &reversed) {
    string term;
    int i = 0;
    while(i < exp.length()) {
        while(exp[i] != ' ' && i < exp.length()){
            term += exp[i];
            i++;
        }
        reversed = term + " " + reversed;
        term = "";
        if (exp[i] == ' '){
            i++;
        }
    }
    return reversed;
}

string doubleToString(double res) {// output string stream
    ostringstream stream;
    //Add res to stream
    stream << res;
    // Take string from output string stream
    string str = stream.str();
    return str;
}

void pushNumToStack(const string &reversed, int &i, string &tempStr, Stack& stack) {
    i++;
    while(reversed[i] != ' ' && i < reversed.length()){
        tempStr += reversed[i];
        i++;
    }
    stack.push(tempStr);
    i--;
}