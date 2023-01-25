//
// Omer Oktay Gultekin 21901413
//

#include "FlowerLibrary.h"
#include <iostream>

FlowerLibrary::FlowerLibrary() {}

FlowerLibrary::~FlowerLibrary(){}

void FlowerLibrary::addFlower(string name) {
    string lowerName = toLowerCase(name);

    if (flowers.add(lowerName)) {
        cout << lowerName << " has been added into the library." << endl;
    } else {
        cout << lowerName << " cannot be added into the library because it already exists." << endl;
    }
}

void FlowerLibrary::removeFlower(string name) {
    string lowerName = toLowerCase(name);
    if (flowers.remove(lowerName)){
        cout << lowerName << " has been removed from the library." << endl;
    }else{
        cout << lowerName << " cannot be removed because it's not in the library" << endl;
    }
}

void FlowerLibrary::listFlowers() const {
    if (flowers.isEmpty()) {
        cout << "Library is empty." << endl;
    } else {
        flowers.traverseList();
    }
}

void FlowerLibrary::listFeatures(string name) const {
    if (flowers.isEmpty()) {
        cout << "Library is empty." << endl;
    } else {
        Flower flower;
        string lowerName = toLowerCase(name);
        if (flowers.retrieve(lowerName, flower)) {
            cout << flower.printFlower() << endl;
        }else{
            cout << lowerName << " isn't found in library" << endl;
        }
    }
}

void FlowerLibrary::addFeature(string name, string feature) {
    if (flowers.isEmpty()) {
        cout << "Library is empty." << endl;
    } else {
        string lowerName = toLowerCase(name);
        string lowerFeature = toLowerCase(feature);
        if (!flowers.addFeature(lowerName, lowerFeature)) {
            cout << lowerName << " isn't found in library" << endl;
        }
    }
}

void FlowerLibrary::removeFeature(string name, string feature) {
    if (flowers.isEmpty()) {
        cout << "Library is empty." << endl;
    } else {
        string lowerName = toLowerCase(name);
        string lowerFeature = toLowerCase(feature);
        if (!flowers.removeFeature(lowerName, lowerFeature)){
            cout << lowerName << " isn't found in library" << endl;
        }
    }
}

void FlowerLibrary::findFlowers(string feature) const {
    if (flowers.isEmpty()) {
        cout << "Library is empty." << endl;
    } else {
        string lowerFeature = toLowerCase(feature);
        flowers.findFeatures(lowerFeature);
    }
}

string FlowerLibrary::toLowerCase(string str) {
    string res;
    for (int i = 0; i < str.length(); ++i) {
        if (str[i] >= 65 && str[i] <= 90) {
            res += char(str[i] + 32);
        } else {
            res += str[i];
        }
    }
    return res;
}