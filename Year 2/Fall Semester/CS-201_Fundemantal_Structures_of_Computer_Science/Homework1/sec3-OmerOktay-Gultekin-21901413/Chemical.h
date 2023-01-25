//
// Omer Oktay Gultekin 21901413
//

#ifndef CHEMICAL_H
#define CHEMICAL_H

#include <string>

using namespace std;

class Chemical {

public:
    int chemicalId;
    string chemType;
    string location;
    int cabinetId;

    Chemical(int chemicalId, string &chemType, string &location, int cabinetId);


    friend ostream &operator<<(ostream &os, const Chemical &chemical);
};

#endif //CHEMICAL_H
