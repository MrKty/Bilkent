/*
* Title: CS202 Spring 2022
* Author: Omer Oktay Gultekin
* ID: 21901413
* Section: 1
* Assignment: 4
* Description: Main code to run program from given input file at command line.
*/
#include <iostream>
#include <fstream>
#include <cstring>
#include "Graph.h"

int main(int argc, char **argv) {
    string fileName = argv[1];
    // declaring character array
    char char_array[fileName.length() + 1];
    // copying the contents of the
    // string to char array to pass it to the indata
    strcpy(char_array, fileName.c_str());
    ifstream indata; // indata is like cin
    string line; // variable for input value
    indata.open(char_array); // opens the file
    if (!indata) { // file couldn't be opened
        cerr << "Error: file could not be opened" << endl;
        return 1;
    }

    int vertexSize;
    int lineNum = 0;
    char opcode;
    int arg1;
    int arg2;
    int arg3;
    indata >> vertexSize;
    indata >> lineNum;
    Graph graph(vertexSize);
    while (!indata.eof()) { // keep reading until end-of-file
        indata >> opcode;
        if (opcode == 'I'){
            indata >> arg1;
            indata >> arg2;
            indata >> arg3;
            graph.insertEdge(arg1, arg2, arg3);
        }else if (opcode == 'S'){
            try{
                indata >> arg1;
                indata >> arg2;
                graph.showShortestPath(arg1, arg2);
            }catch (int i){
                cout << "Error occurred in shortest path algorithm" << endl;
            }

        }else if (opcode == 'L'){
            indata >> arg1;
            graph.listVertexEdges(arg1);
        }else if (opcode == 'M'){
            graph.minimizeGraph();
        }
    }
    indata.close();
    graph.deleteGraph();
    return 0;
}
