//
// Omer Oktay Gultekin 21901413
//

#ifndef FLOWERLIBRARY_H
#define FLOWERLIBRARY_H


#include "FlowerList.h"

class FlowerLibrary {
public:
    FlowerLibrary();

    ~FlowerLibrary();

    void addFlower(string name);

    void removeFlower(string name);

    void listFlowers() const;

    void listFeatures(string name) const;

    void addFeature(string name, string feature);

    void removeFeature(string name, string feature);

    void findFlowers(string feature) const;

private:
    FlowerList flowers;

    static string toLowerCase(string str);
};

#endif //FLOWERLIBRARY_H
