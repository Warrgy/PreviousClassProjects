/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

struct name_basics;
struct title_basics;
struct title_principals;

struct tree {
    char *key;
    void *data;
    struct tree *child[2];
};

int compare(const void *x, const void *y);
char *findName(struct tree *root, char *name, bool next, char *previous);
struct tree *tconstPrincipals(struct title_principals *principals);
struct tree *nconstPrincipals(struct title_principals *principals);
struct tree *tconstBasic(struct title_basics *title);
struct tree *primaryTitleBasics(struct title_basics *title);
struct tree *nconstBasics(struct name_basics *names);
struct tree *addPrimaryName(struct name_basics *names);
void addNode(struct tree **root, char *name, char *other);
void freeTree(struct tree *root);
