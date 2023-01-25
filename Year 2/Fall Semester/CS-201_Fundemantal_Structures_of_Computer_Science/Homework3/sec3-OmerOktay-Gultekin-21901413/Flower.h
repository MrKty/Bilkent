//
// Omer Oktay Gultekin 21901413
//

#ifndef FLOWER_H
#define FLOWER_H

#include <string>
#include <ostream>

using namespace std;

class Flower {
public:
    Flower();

    Flower(string flowerName);

    Flower(const Flower &aFlower);

    ~Flower();

    bool isEmpty() const;

    int getLength() const;

    bool add(string feature);

    bool remove(string feature);

    string printFlower() const;

    const string &getFlowerName() const;

    bool checkFeatureExists(const string &featureName);

    bool static compareStrings(const string &string1, const string &string2);

    Flower &operator=(const Flower &rhs);

private:
    struct FeatureNode {
        string feature;
        FeatureNode *next;
    };
    int size;
    string flowerName;
    FeatureNode *head; //the features are stored in a sorted singly linear linked list

    void deleteNodes(FeatureNode *node);
};

#endif // FLOWER_H
