//
// Omer Oktay Gultekin 21901413
//

#ifndef CABINET_H
#define CABINET_H

#include "Chemical.h"

class Cabinet {

    int cabinetId;
    int rows;
    int columns;
    int emptySlots;

public:
    Chemical ***chemicalList;

    Cabinet(int id, int rows, int columns);

    ~Cabinet();

    int getRows() const;


    int getColumns() const;

    int getEmptySlots() const;

    int getId() const;

    bool isChemicalCanPlaced(const string &chemType, int row, int column);

    string findNearestPossiblePlaces(const string &chemType, int row, int column, int depth);

    void removeChemical(string location);

    friend ostream &operator<<(ostream &os, const Cabinet &cabinet);

    void setEmptySlots(int emptySlots);
};


#endif //CABINET_H
