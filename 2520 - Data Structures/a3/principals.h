/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

struct title_principals {
    char *nconst;
    char *tconst;
    char *characters;
};

struct title_principals *getPrincipals(char *path);
