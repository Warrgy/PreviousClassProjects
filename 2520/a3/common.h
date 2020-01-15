/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char *getColumn(char *entireLine, int columnNumber);
struct name_basics *titleToName(char *title, char *path, char *previous);
struct title_basics *nameToTitle(char *name, char *path, char *previous);
int retr(int);
