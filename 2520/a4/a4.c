/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "a4.h"

/*This function was found on zyBooks section 5.7*/
unsigned long valueName(char *name, int hashSize) {
    unsigned long initialValue = 5381;
    int const hashMultiplier = 33;
    int i;
    
    for (i = 0; name[i] != '\0'; i++) {
        initialValue = hashMultiplier * initialValue + name[i];
    }
    return initialValue % hashSize;
}

/*Hash function for last_name*/
int hash1(char *name, int hashSize) {
    unsigned long number = 0, new = 0;
    
    number = valueName(name, hashSize);
    
    while(number) {
        new = (number + (new % hashSize)) % hashSize;
        number /= hashSize;
    }
    return (int)new;
}

int shift16(int number) {
    return number << 16;
}

int shift6(int number) {
    return number << 6;
}

/*Hash function for license_no*/
int hash2(char *ids, int hashSize) {
    unsigned long number = 0, temp = 0;
    int i;
    
    /*Use operations to create a number for each value of the license number*/
    for (i = 0; ids[i] != '\0'; i++) {
        temp = ids[i];
        temp += shift6(number);
        temp += shift16(number);
        temp -= number;
        number = temp;
    }
    /*Use hash function 'magic' numbers, as well as other operations to shorten collisions and enhance uniqueness for the given license number*/
    number = (~number * 33 * 5381) & 0x7FFFFFFF;
    number %= hashSize;
    return (int)number;
}

int valueDate(char letter) {
    return (int)(letter - 48);
}

/*Hash function for issue_date*/
int hash3(char *date, int hashSize) {
    int i, century = 0;
    unsigned long number = 0, new = 0;
    
    for (i = 0; date[i] != '\0'; i++) {
        /*Account for the different centuries*/
        if (i == 7) {
            if ((date[i] == '0') || (date[i] == '1')) {
                century = 1;
            }
            /*Skip the '/' and the 7th column*/
        } else if ((date[i] != '/') && (i != 6)) {
            number += valueDate(date[i]);
            /*make sure next data is not \0 before multiplying*/
            if (date[i + 1] != '\0') {
                number *= 10;
            }
        }
    }
    /*Account for the century in the hundred thousands column*/
    if (century == 1) {
        number += 100000;
    }
    while(number) {
        new = (number + (new % hashSize)) % hashSize;
        number /= hashSize;
    }
    return (int)new;
}

