//
// Omer Oktay Gultekin 21901413
//

#include <iostream>
#include <sstream>
#include <string>
#include "LabOrganizer.h"
#include "Cabinet.h"

using namespace std;

LabOrganizer::LabOrganizer() {
    cabinets = NULL;
    cabinetsSize = 0;
    chemicals = NULL;
    chemicalsSize = 0;
}

LabOrganizer::~LabOrganizer() {
    for (int i = 0; i < cabinetsSize; ++i) {
        delete cabinets[i];
    }
    delete[] cabinets;
    delete[] chemicals; //chemicals 2D array but the cells deleted by above line
}

void LabOrganizer::addCabinet(int id, int rows, int columns) {
    for (int i = 0; i < cabinetsSize; ++i) {
        if (cabinets[i]->getId() == id) {
            cout << "Cannot add the cabinet: ID " << id << " already in the system" << endl;
            return;
        }
    }

    if (rows < 0 || rows > 9 || columns < 0 || columns > 9) {
        cout << "Cannot add the cabinet: dimensions are out of bounds" << endl;
        return;
    }
    //dynamically change the size of cabinet array and add the new cabinet
    LabOrganizer::cabinetsSize++;
    Cabinet **tempCabinetList = new Cabinet *[LabOrganizer::cabinetsSize];
    int position = -1;
    int counter = 0;
    Cabinet *cabinet = new Cabinet(id, rows, columns);
    for (int i = 0; i < cabinetsSize - 1; ++i) {
        // order them by cabinetId in case the future updates of the program wants to use optimized searchs (eg. binary search)
        if (cabinets[i]->getId() > id && position == -1) {
            position = 0;
            tempCabinetList[i] = cabinet;
            counter++;
        }
        tempCabinetList[i + counter] = LabOrganizer::cabinets[i];
    }

    if (position == -1) {
        tempCabinetList[cabinetsSize - 1] = cabinet;
    }

    delete[] LabOrganizer::cabinets;
    LabOrganizer::cabinets = tempCabinetList;
    cout << "Added a cabinet: ID " << id << " and dimensions " << rows << " to " << columns << endl;
}

void LabOrganizer::removeCabinet(int id) {
    for (int i = 0; i < LabOrganizer::cabinetsSize; ++i) {
        if (LabOrganizer::cabinets[i]->getId() == id) {
            if (chemicalsSize > 0) {
                //first, remove the reference of the chemical from the array that keep all chemicals references
                int cnt = 0;
                for (int j = 0; j < chemicalsSize; ++j) {
                    if (chemicals[j]->cabinetId == cabinets[i]->getId()) {
                        cout << "Chemical " << chemicals[j]->chemicalId << " removed from cabinet "
                             << chemicals[j]->cabinetId << endl;
                        cnt++;
                    }
                }
                chemicalsSize -= cnt;
                cnt = 0;
                Chemical **tempChem = new Chemical *[chemicalsSize];
                // skip the one that wanted to remove while copying
                for (int j = 0; j < chemicalsSize + cnt; ++j) {
                    if (chemicals[j]->cabinetId != id) {
                        tempChem[j + cnt] = chemicals[j];
                    } else {
                        cnt--;
                    }
                }
                delete[] chemicals;
                chemicals = tempChem;
            }

            //Remove the actual cabinet
            LabOrganizer::cabinetsSize--;
            Cabinet **temp = new Cabinet *[cabinetsSize];
            for (int j = 0; j < i; ++j) {
                temp[j] = LabOrganizer::cabinets[j];
            }
            for (int j = i + 1; j < LabOrganizer::cabinetsSize + 1; ++j) {
                temp[j - 1] = LabOrganizer::cabinets[j];
            }
            delete LabOrganizer::cabinets[i];
            delete[] LabOrganizer::cabinets;
            LabOrganizer::cabinets = temp;
            cout << "Cabinet " << id << " has been removed" << endl;
            return;
        }
    }
    cout << "Cabinet " << id << " does not exist in the system" << endl;
}

void LabOrganizer::listCabinets() {
    cout << "List of all cabinets:" << endl;
    for (int i = 0; i < LabOrganizer::cabinetsSize; ++i) {
        cout << *LabOrganizer::cabinets[i] << endl;
    }
}

void LabOrganizer::cabinetContents(int id) {
    for (int i = 0; i < cabinetsSize; ++i) {
        if (LabOrganizer::cabinets[i]->getId() == id) {
            ostringstream table;
            ostringstream info;
            info <<
                 "ID: " << id << ", " << cabinets[i]->getRows() << "x" <<
                 cabinets[i]->getColumns() << ", empty: " <<
                 cabinets[i]->getEmptySlots() << ". Chemicals:";
            table << " ";
            for (int j = 1; j <= cabinets[i]->getColumns(); ++j) {
                table << " ";
                table << j;
            }
            table << "\n";
            for (int j = 0; j < cabinets[i]->getRows(); ++j) {
                table << char(65 + j); // to convert uppercase letters
                for (int k = 0; k < cabinets[i]->getColumns(); ++k) {
                    if (LabOrganizer::cabinets[i]->chemicalList[j][k] == NULL) {
                        table << " +";
                    } else {
                        if (LabOrganizer::cabinets[i]->chemicalList[j][k]->chemType == "combustive") {
                            table << " c";
                        } else {
                            table << " r";
                        }
                        info << " " << cabinets[i]->chemicalList[j][k]->location << ": " <<
                             (cabinets[i]->chemicalList[j][k]->chemicalId) << ",";
                    }
                }
                table << "\n";
            }
            string outputInfo = info.str();
            if (outputInfo[outputInfo.size() - 1] == ',') outputInfo.resize(outputInfo.size() - 1);
            cout << outputInfo << endl;
            cout << table.str();
            return;
        }
    }
    cout << "Cabinet " << id << " is not in the system" << endl;
}

void LabOrganizer::placeChemical(int cabinetId, string location, string chemType, int chemID) {
    for (int i = 0; i < LabOrganizer::cabinetsSize; ++i) {
        if (LabOrganizer::cabinets[i]->getId() == cabinetId) {
            if (chemicals != NULL) {
                // Check the existance of Chemical in the system
                for (int j = 0; j < chemicalsSize; ++j) {
                    if (chemicals[j]->chemicalId == chemID) {
                        cout << "Chemical with ID " << chemID << " already exists in the system" << endl;
                        return;
                    }
                }
            }
            int row = (location[0] - 65);
            int column = location[1] - '0' - 1;
            if (cabinets[i]->isChemicalCanPlaced(chemType, row, column)) {
                LabOrganizer::cabinets[i]->chemicalList[row][column] = new Chemical(chemID, chemType, location,
                                                                                    cabinetId);
                LabOrganizer::cabinets[i]->setEmptySlots(cabinets[i]->getEmptySlots() - 1);
                chemType[0] = char(chemType[0] - 32);
                cout << chemType << " chemical with ID " << chemID << " has been placed at location " << location
                     << " in cabinet " << cabinets[i]->getId() << endl;

                // Place the reference into the array that keep all references of the chemicals in the system
                chemicalsSize++;
                if (chemicalsSize == 1) {
                    chemicals = new Chemical *[1];
                    chemicals[0] = LabOrganizer::cabinets[i]->chemicalList[row][column];
                } else {
                    Chemical **temp = new Chemical *[chemicalsSize];
                    for (int j = 0; j < chemicalsSize - 1; ++j) {
                        temp[j] = chemicals[j];
                    }
                    temp[chemicalsSize - 1] = LabOrganizer::cabinets[i]->chemicalList[row][column];
                    delete[] chemicals;
                    chemicals = temp;
                }
                return;
            } else {
                if (cabinets[i]->chemicalList[row][column] != NULL) {
                    cout << "Location " << location << " in cabinet " << cabinetId
                         << " is already occupied.";
                } else {
                    cout << "Location " << location << " in cabinet " << cabinetId
                         << " is not suitable for a combustive chemical.";
                }
            }
            int counter = 1;
            string possiblePlaces = cabinets[i]->findNearestPossiblePlaces(chemType, row, column, counter);
            while (possiblePlaces.empty()) {
                counter++;
                possiblePlaces = cabinets[i]->findNearestPossiblePlaces(chemType, row, column, counter);
                if (counter > cabinets[i]->getRows() && counter > cabinets[i]->getColumns() ){
                    cout << " There is no suitable position!" << endl;
                    return;
                }
            }
            cout << " Nearest possible locations for this chemical:" << possiblePlaces << endl;
            return;
        }
    }
    cout << "Cabinet " << cabinetId << " is not in the system" << endl;
}

void LabOrganizer::findChemical(int id) {
    for (int i = 0; i < chemicalsSize; ++i) {
        if (chemicals[i]->chemicalId == id) {
            cout << "Chemical " << id << " is at location " << chemicals[i]->location << " in cabinet "
                 << chemicals[i]->cabinetId << endl;
            return;
        }
    }
    cout << "Chemical " << id << " is not in the system" << endl;
}

void LabOrganizer::removeChemical(int id) {
    for (int i = 0; i < chemicalsSize; ++i) {
        if (chemicals[i]->chemicalId == id) {
            for (int j = 0; j < cabinetsSize; ++j) {
                if (cabinets[j]->getId() == chemicals[i]->cabinetId) {
                    cout << "Chemical " << chemicals[i]->chemicalId << " removed from cabinet "
                         << chemicals[i]->cabinetId << endl;
                    cabinets[j]->removeChemical(chemicals[i]->location);
                    chemicalsSize--;

                    //Remove the reference of the chemical from array that keep references of the chemicals in the system
                    Chemical **temp = new Chemical *[chemicalsSize];
                    // skip the one that wanted to remove while copying
                    for (int k = 0; k < i; ++k) {
                        temp[k] = chemicals[k];
                    }
                    for (int k = i + 1; k < chemicalsSize + 1; ++k) {
                        temp[k - 1] = chemicals[k];
                    }
                    delete[] chemicals;
                    chemicals = temp;
                    return;
                }
            }
            return;
        }
    }
    cout << "Chemical " << id << " is not in the system" << endl;
}
