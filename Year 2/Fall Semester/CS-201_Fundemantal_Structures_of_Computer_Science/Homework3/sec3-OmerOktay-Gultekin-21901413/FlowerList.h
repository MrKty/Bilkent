//
// Omer Oktay Gultekin 21901413
//

#ifndef FLOWERLIST_H
#define FLOWERLIST_H

#include <string>
#include "Flower.h"


using namespace std;

class FlowerList {
public:
    FlowerList();

    FlowerList(const FlowerList &aList);

    ~FlowerList();

    bool isEmpty() const;

    int getLength() const;

    bool retrieve(string flowerName, Flower &flower) const;

    bool add(string flowerName);

    bool remove(string flowerName);

    bool addFeature(string &flowerName, string &feature);

    bool removeFeature(string &flowerName, string &feature);

    void traverseList() const;

    void findFeatures(string &featureName) const;

private:
    struct FlowerNode {
        Flower f;
        FlowerNode *next;
    };
    int size;
    FlowerNode *head; //the flowers are stored in a sorted singly linear linked list

    bool checkFlowerExists(const string &flowerName);

    void deleteNodes(FlowerNode *node);

};


#endif //FLOWERLIST_H