/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "common.h"

/*Will get the specific column on the line of text and store it in the variable column*/
char *getColumn(char *entireLine, int columnNumber) {
    /*-1 means not set.*/
    int i, leftIndice = -1, rightIndice = -1, tabCharacter = 0;
    char *column = malloc(1);
    
    /*Go through each line, while adding each correct character to column.*/
    for (i = 0; i < strlen(entireLine); i++) {
        /*If you reached the start of the column*/
        if ((tabCharacter == columnNumber) && (leftIndice == -1)) {
            leftIndice = i;
        }
        /*If you found the column*/
        if (leftIndice != -1) {
            /*If you reached the end of the line*/
            if (entireLine[i] == '\t') {
                rightIndice = i;
            }
            /*Add character to column string if you havent reach the end of the line*/
            if (rightIndice == -1) {
                column = realloc(column, (i - leftIndice + 2));
                column[i - leftIndice] = entireLine[i];
            }
        }
        /*Increment tabcharacter*/
        if (entireLine[i] == '\t') {
            tabCharacter++;
        }
    }
    column[rightIndice - leftIndice] = '\0';
    
    return column;
}

