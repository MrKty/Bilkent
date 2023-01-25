//
// Omer Oktay Gultekin 21901413
//

#include <sstream>
#include <string>
#include "Cabinet.h"

using namespace std;

Cabinet::Cabinet(int id, int rows, int columns) : cabinetId(id), rows(rows), columns(columns) {
    Cabinet::chemicalList = new Chemical **[rows];
    for (int i = 0; i < rows; ++i) {
        chemicalList[i] = new Chemical *[columns];
        for (int j = 0; j < columns; ++j) {
            chemicalList[i][j] = NULL;
        }
    }
    Cabinet::emptySlots = rows * columns;
}

Cabinet::~Cabinet() {
    for (int i = 0; i < rows; ++i) {
        if (chemicalList[i] != NULL) {
            for (int j = 0; j < columns; ++j) {
                if (chemicalList[i][j] != NULL) {
                    delete chemicalList[i][j];
                }
            }
            delete[] chemicalList[i];
        }
    }
    if (chemicalList != NULL) {
        delete[] chemicalList;
    }
}

int Cabinet::getRows() const {
    return rows;
}

int Cabinet::getId() const {
    return cabinetId;
}

int Cabinet::getColumns() const {
    return columns;
}


int Cabinet::getEmptySlots() const {
    return emptySlots;
}

void Cabinet::setEmptySlots(int emptySlots) {
    Cabinet::emptySlots = emptySlots;
}


bool Cabinet::isChemicalCanPlaced(const string &chemType, int row, int column) {
    if (chemicalList[row][column] == NULL) {
        if (chemType == "combustive") {
            // Look around the given location to determine combustive can be placed or not
            for (int i = row - 1; i <= row + 1; ++i) {
                // check matrix boundaries
                if (i < rows && i >= 0) {
                    for (int j = column - 1; j <= column + 1; ++j) {
                        // check matrix boundaries and current location
                        if (j < columns && j >= 0 && !(j == column && i == row)) {
                            if (chemicalList[i][j] != NULL && chemicalList[i][j]->chemType == "combustive") {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        // chemical either redundant or placable combustive to given location
        return true;
    }
    // Place Not Empty
    return false;
}

string Cabinet::findNearestPossiblePlaces(const string &chemType, int row, int column, int depth) {
    string positions = "";

    //Only search given depth rectangle
    for (int i = row - depth; i <= row + depth; ++i) {
        // check matrix boundaries
        if (i < rows && i >= 0) {
            for (int j = column - depth; j <= column + depth; ++j) {
                int absRow = row - i;
                if (absRow < 0) {
                    absRow = -absRow;
                }
                int absCol = column - j;
                if (absCol < 0) {
                    absCol = -absCol;
                }
                // check matrix boundaries and current location is not exceed the depth
                if (j < columns && j >= 0 && !(absRow < depth && absCol < depth)) {
                    if (isChemicalCanPlaced(chemType, i, j)) {
                        positions += " ";
                        positions += char(65 + i); // convert to the uppercase letter
                        int i = j + 1;
                        ostringstream str1;
                        str1 << i;
                        positions += str1.str() + ",";
                    }
                }
            }
        }
    }
    // delete additional comma at the end
    if (positions[positions.size() - 1] == ',') positions.resize(positions.size() - 1);
    return positions;
}

void Cabinet::removeChemical(string location) {
    int row = location[0] - 'A';
    int column = location[1] - '1';
    delete chemicalList[row][column];
    chemicalList[row][column] = NULL;
    emptySlots++;
}

ostream &operator<<(ostream &os, const Cabinet &cabinet) {
    os << "ID: " << cabinet.cabinetId << ", Dim: " << cabinet.rows << "x" << cabinet.columns
       << ", Number of empty slots: "
       << cabinet.emptySlots;
    return os;
}


