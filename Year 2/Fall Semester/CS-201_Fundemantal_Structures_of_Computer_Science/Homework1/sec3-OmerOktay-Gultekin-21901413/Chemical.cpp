//
// Omer Oktay Gultekin 21901413
//

#include <iostream>
#include "Chemical.h"

using namespace std;


Chemical::Chemical(int chemicalId, string &chemType, string &location, int cabinetId) {
    this->chemicalId = chemicalId;
    this->chemType = chemType;
    this->location = location;
    this->cabinetId = cabinetId;
}


ostream &operator<<(ostream &os, const Chemical &chemical) {
    os << "Chemical " << chemical.chemicalId << " type " << chemical.chemType;
    return os;
}

