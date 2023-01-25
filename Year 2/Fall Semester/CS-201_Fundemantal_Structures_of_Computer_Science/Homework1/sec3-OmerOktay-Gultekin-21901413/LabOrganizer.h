//
// Omer Oktay Gultekin 21901413
//

#ifndef LABORGANIZER_H
#define LABORGANIZER_H

#include <string>
#include "Cabinet.h"
#include "Chemical.h"

using namespace std;

class LabOrganizer {
private:
    Cabinet **cabinets;
    int cabinetsSize;
    Chemical **chemicals;
    int chemicalsSize;

public:
    LabOrganizer();

    ~LabOrganizer();

    void addCabinet(int id, int rows, int columns);

    void removeCabinet(int id);

    void listCabinets();

    void cabinetContents(int id);

    void placeChemical(int cabinetId, string location, string chemType, int chemID);

    void findChemical(int id);

    void removeChemical(int id);

};

#endif //LABORGANIZER_H
