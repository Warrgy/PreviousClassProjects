/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "common.h"
#include "binary.h"
#include "name.h"
#include "title.h"
#include "principals.h"

/*Used to run the applicable functions to find the corresponding name from the title given.*/
struct name_basics *titleToName(char *title, char *path, char *previous) {
    struct name_basics *data, *name = NULL;
    struct title_basics *dataTitle;
    struct title_principals *dataPrincipals;
    struct tree *tree;
    char *nconst = malloc(10), *tconst = malloc(10);
    
    dataTitle = getTitle(path);
    tree = primaryTitleBasics(dataTitle);
    tconst = findName(tree, title, false,NULL);
    if (tconst == NULL) {
        return NULL;
    }
    dataPrincipals = getPrincipals(path);
    tree = tconstPrincipals(dataPrincipals);
    nconst = findName(tree, tconst, false,previous);
    if (nconst == NULL) {
        return NULL;
    }
    data = getName(path);
    tree = nconstBasics(data);
    name = malloc(sizeof(struct name_basics));
    name->primaryName = findName(tree, nconst, true,NULL);
    free(nconst);
    free(tconst);
    return name;
}

/*Used to run the applicable functions to find the corresponding title from the name given.*/
struct title_basics *nameToTitle(char *name, char *path, char *previous) {
    struct name_basics *data;
    struct title_basics *dataTitle, *title = NULL;
    struct title_principals *dataPrincipals;
    struct tree *tree, *temp;
    char *nconst = malloc(10), *tconst = malloc(10);
    
    data = getName(path);
    tree = addPrimaryName(data);
    nconst = findName(tree, name, false, NULL);
    if (nconst == NULL) {
        free(nconst);
        free(tconst);
        return NULL;
    }
    dataPrincipals = getPrincipals(path);
    tree = nconstPrincipals(dataPrincipals);
    tconst = findName(tree, nconst, false, previous);
    if (tconst == NULL) {
        free(nconst);
        free(tconst);
        return NULL;
    }
    dataTitle = getTitle(path);
    tree = tconstBasic(dataTitle);
    temp = tree;
    title = malloc(sizeof(struct title_basics));
    title->primaryTitle = findName(tree, tconst, true,NULL);
    title->tconst = tconst;

    free(nconst);
    return title;
}

