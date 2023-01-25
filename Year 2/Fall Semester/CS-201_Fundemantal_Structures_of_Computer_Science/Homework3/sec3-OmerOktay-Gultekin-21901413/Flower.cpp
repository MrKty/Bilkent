//
// Omer Oktay Gultekin 21901413
//

#include "Flower.h"

Flower::Flower() {
    size = 0;
    flowerName = "";
    head = NULL;
}

Flower::Flower(string flowerName) {
    size = 0;
    Flower::flowerName = flowerName;
    head = NULL;
}

Flower::Flower(const Flower &aFlower) {
    size = aFlower.size;
    flowerName = aFlower.flowerName;
    if (aFlower.head != NULL) {
        FeatureNode *node = new FeatureNode();
        for (FeatureNode *cur = aFlower.head; cur != NULL; cur = cur->next) {
            node->feature = cur->feature;
            if (cur->next != NULL) {
                node->next = new FeatureNode();
            } else {
                node->next = NULL;
            }
            if (cur == aFlower.head) {
                head = node;
            }
            node = node->next;
        }
    } else {
        head = NULL;
    }
}

Flower::~Flower() {
    if (head != NULL) {
        deleteNodes(head);
    }
}

bool Flower::add(string feature) {
    if (checkFeatureExists(feature)) {
        return false;
    }
    size++;
    FeatureNode *node = new FeatureNode();
    node->feature = feature;
    node->next = NULL;
    if (head == NULL) {
        head = node;
    } else {
        FeatureNode *cur = head;
        FeatureNode *prev = head;
        for (; cur != NULL; cur = cur->next) {
            if (Flower::compareStrings(feature, cur->feature)) {
                node->next = cur;
                if (cur == head) {
                    head = node;
                } else {
                    prev->next = node;
                }
                break;
            } else if (cur->next == NULL) {
                cur->next = node;
                break;
            }
            prev = cur;
        }
    }
    return true;
}

int Flower::getLength() const {
    return size;
}

bool Flower::isEmpty() const {
    return size == 0;
}

string Flower::printFlower() const {
    string result = flowerName + ": ";
    if (isEmpty()) {
        result += "No feature";
    } else {
        for (FeatureNode *cur = head; cur != NULL; cur = cur->next) {
            result += cur->feature;
            if (cur->next != NULL) {
                result += ", ";
            }
        }
    }
    return result;
}

bool Flower::remove(string feature) {
    FeatureNode *cur = head;
    FeatureNode *prev = head;
    for (; cur != NULL; cur = cur->next) {
        if (cur->feature == feature) {
            if (cur == head) {
                head = cur->next;
            } else {
                prev->next = cur->next;
                cur->next = NULL;
            }
            delete cur;
            size--;
            return true;
        }
        prev = cur;
    }
    return false;
}

const string &Flower::getFlowerName() const {
    return flowerName;
}

//if string1 is comes first alphabetically return true; otherwise return false
bool Flower::compareStrings(const string &string1, const string &string2) {
    for (int i = 0; i < string1.length(); ++i) {
        if (i >= string2.length()) {
            //string2 comes first alphabetically
            return false;
        }
        if (string1[i] >= 97 && string1[i] <= 122) {
            if (string2[i] >= 97 && string2[i] <= 122) {
                if (string1[i] < string2[i]) {
                    return true;
                } else if (string2[i] < string1[i]) {
                    return false;
                }
            } else {
                //string2 has space means comes first alphabetically
                return false;
            }
        } else {
            if (string2[i] >= 97 && string2[i] <= 122) {
                //string1 has space means comes first alphabetically
                return true;
            }
        }
    }
    if (string1.length() < string2.length()) {
        //string1 comes first alphabetically
        return true;
    }
    return true; // if string1 and string2 equals
}

bool Flower::checkFeatureExists(const string &featureName) {
    for (FeatureNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->feature == featureName) {
            return true;
        }
    }
    return false;
}

void Flower::deleteNodes(FeatureNode *node) {
    if (node->next == NULL) {
        delete node;
    } else {
        deleteNodes(node->next);
        delete node;
    }
}


Flower &Flower::operator=(const Flower &aFlower) {
    size = aFlower.size;
    flowerName = aFlower.flowerName;
    if (aFlower.head != NULL) {
        FeatureNode *node = new FeatureNode();
        for (FeatureNode *cur = aFlower.head; cur != NULL; cur = cur->next) {
            node->feature = cur->feature;
            if (cur->next != NULL) {
                node->next = new FeatureNode();
            } else {
                node->next = NULL;
            }
            if (cur == aFlower.head) {
                head = node;
            }
            node = node->next;
        }
    } else {
        head = NULL;
    }
    return *this;
}





