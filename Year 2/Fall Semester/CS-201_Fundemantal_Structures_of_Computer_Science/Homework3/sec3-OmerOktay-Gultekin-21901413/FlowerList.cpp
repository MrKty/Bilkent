//
// Omer Oktay Gultekin 21901413
//

#include "FlowerList.h"
#include <iostream>

FlowerList::FlowerList() {
    head = NULL;
    size = 0;
}

FlowerList::FlowerList(const FlowerList &aList) {
    size = aList.size;
    if (aList.head != NULL) {
        FlowerNode *node = new FlowerNode();
        for (FlowerNode *cur = aList.head; cur != NULL; cur = cur->next) {
            Flower *f = new Flower(cur->f);
            node->f = *f; // = operator made copy so we can delete f at next line
            delete f;
            if (cur->next != NULL) {
                node->next = new FlowerNode();
            } else {
                node->next = NULL;
            }
            if (cur == aList.head) {
                head = node;
            }
            node = node->next;
        }
    } else {
        head = NULL;
    }

}

FlowerList::~FlowerList() {
    if (head != NULL) {
        deleteNodes(head);
    }
}

bool FlowerList::add(string flowerName) {
    if (checkFlowerExists(flowerName)) {
        return false;
    }
    size++;
    FlowerNode *flowerNode = new FlowerNode();
    Flower *f = new Flower(flowerName);
    flowerNode->f = *f; // = operator made copy so we can delete f at next line
    delete f;
    flowerNode->next = NULL;
    if (head == NULL) {
        head = flowerNode;
    } else {
        FlowerNode *cur = head;
        FlowerNode *prev = head;
        for (; cur != NULL; cur = cur->next) {
            if (Flower::compareStrings(flowerName, cur->f.getFlowerName())) {
                flowerNode->next = cur;
                if (cur == head) {
                    head = flowerNode;
                } else {
                    prev->next = flowerNode;
                }
                break;
            } else if (cur->next == NULL) {
                cur->next = flowerNode;
                break;
            }
            prev = cur;
        }
    }
    return true;
}

bool FlowerList::retrieve(string flowerName, Flower &flower) const {
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->f.getFlowerName() == flowerName) {
            flower = cur->f;
            return true;
        }
    }
    return false;
}


void FlowerList::traverseList() const {
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        cout << cur->f.printFlower() << endl;
    }
}

bool FlowerList::remove(string flowerName) {
    FlowerNode *cur = head;
    FlowerNode *prev = head;
    for (; cur != NULL; cur = cur->next) {
        if (cur->f.getFlowerName() == flowerName) {
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

bool FlowerList::isEmpty() const {
    return size == 0;
}

int FlowerList::getLength() const {
    return size;
}

bool FlowerList::checkFlowerExists(const string &flowerName) {
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->f.getFlowerName() == flowerName) {
            return true;
        }
    }
    return false;
}

void FlowerList::findFeatures(string &featureName) const {
    string result = featureName + " flowers: ";
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->f.checkFeatureExists(featureName)) {
            result += cur->f.getFlowerName() + ", ";
        }
    }
    if (result.length() == featureName.length() + 10) { // + " flowers: "
        result += "there is no such flower";
    } else {
        result = result.substr(0, result.length() - 2); // to delete ", " at the end
    }
    cout << result << endl;
}

void FlowerList::deleteNodes(FlowerList::FlowerNode *node) {
    if (node->next == NULL) {
        delete node;
    } else {
        deleteNodes(node->next);
        delete node;
    }
}

bool FlowerList::addFeature(string &flowerName, string &feature) {
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->f.getFlowerName() == flowerName) {
            if (cur->f.add(feature)) {
                cout << feature << " is added into " << flowerName << endl;
            } else {
                cout << feature << " already exists in " << flowerName << endl;
            }
            return true;
        }
    }
    return false;
}

bool FlowerList::removeFeature(string &flowerName, string &feature) {
    for (FlowerNode *cur = head; cur != NULL; cur = cur->next) {
        if (cur->f.getFlowerName() == flowerName) {
            if (cur->f.remove(feature)) {
                cout << feature << " is removed from " << flowerName << endl;
            } else {
                cout << feature << " doesn't exists in " << flowerName << endl;
            }
            return true;
        }
    }
    return false;
}
