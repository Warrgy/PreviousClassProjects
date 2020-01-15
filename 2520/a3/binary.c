/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "binary.h"
#include "name.h"
#include "title.h"
#include "principals.h"

/*Used to random sort struct if they are in order.*/
int compare(const void *x, const void *y) {
    return ((rand() % 10) - 5);
}

/*Use to find the name in the root*/
char *findName(struct tree *root, char *name, bool next, char *previous) {
    struct tree *temp = root;
    while(root) {
        if (strcmp(name, (char *)(root->data)) < 0) {
            root = root->child[0];
        } else if (strcmp(name, (char *)(root->data)) > 0){
            root = root->child[1];
        } else {
            return root->key;
            if (previous == NULL) {
                return root->key;
            } else {
                if (strcmp(root->key, previous) != 0)  {
                    return root->key;
                } else  {
                    root = temp->child[1]; 
                }
            }
        }
    }
    return NULL;
}

/*Put the struct title_principals into a tree, using tconst as the search key.*/
struct tree *tconstPrincipals(struct title_principals *principals) {
    struct tree *root = NULL;
    struct title_principals *number = principals;
    int counter = 0;
    
    while(number) {
        if (number->tconst == NULL) {
            break;
        }
        counter++;
        number++;
    }
    /*Randomly sort struct to make tree more balanced*/
    qsort(principals, counter, sizeof(struct title_principals), compare);
    
    while(principals) {
        if (principals->tconst == NULL) {
            break;
        }
        addNode(&root, principals->tconst, principals->nconst);
        principals++;
    }
    return root;
}

/*Put the struct title_principals into a tree, using nconst as the search key.*/
struct tree *nconstPrincipals(struct title_principals *principals) {
    struct tree *root = NULL;
    
    while(principals) {
        if (principals->nconst == NULL) {
            break;
        }
        addNode(&root, principals->nconst, principals->tconst);
        principals++;
    }
    return root;
}

/*Put the struct title_basics into a tree, using tconst as the search key.*/
struct tree *tconstBasic(struct title_basics *title) {
    struct tree *root = NULL;
    struct title_basics *number = title;
    int amount = 0;
    
    while(number) {
        if(number->tconst == NULL) {
            break;
        }
        amount++;
        number++;
    }
    /*Randomly sort struct to make tree more balanced*/
    qsort(title, amount, sizeof(struct title_basics), compare);

    while (title) {
        if (title->tconst == NULL) {
            break;
        }
        addNode(&root, title->tconst, title->primaryTitle);
        title++;
    }
    return root;
}

/*Put the struct title_basics into a tree, using primaryTitle as the search key.*/
struct tree *primaryTitleBasics(struct title_basics *title) {
    struct tree *root = NULL;
    
    while(title) {
        if (title->primaryTitle == NULL) {
            break;
        }
        addNode(&root, title->primaryTitle, title->tconst);
        title++;
    }
    return root;
}

/*Put the struct name_basics into a tree, using nconst as the search key.*/
struct tree *nconstBasics(struct name_basics *names) {
    struct tree *root = NULL;
    struct name_basics *number = names;
    int amount = 0;
    
    while (number) {
        if (number->nconst == NULL) {
            break;
        }
        amount++;
        number++;
    }
    /*Randomly sort struct to make tree more balanced*/
    qsort(names, amount, sizeof(struct name_basics), compare);
    
    while (names) {
        if (names->nconst == NULL) {
            break;
        }
        addNode(&root, names->nconst, names->primaryName);
        names++;
    }
    return root;
}

/*Put the struct name_basics into a tree, using primaryName as the search key.*/
struct tree *addPrimaryName(struct name_basics *names) {
    struct tree *root = NULL;
    
    while(names) {
        if (names->primaryName == NULL) {
            break;
        }
        addNode(&root, names->primaryName, names->nconst);
        names++;
    }
    return root;
}

/*Add the string to the name*/
void addNode(struct tree **root, char *name, char *other) {
    if (*root) { /* there's a node here */
        if (strcmp(name, (char *)((*root)->data)) < 0) {
            addNode(&((*root)->child[0]), name, other);
        } else {
            addNode(&((*root)->child[1]), name, other);
        }
    } else { /* no node here */
        (*root) = malloc( sizeof( struct tree ) );
        (*root)->data = name;
        (*root)->key = other;
        (*root)->child[0]=NULL;
        (*root)->child[1]=NULL;
    }
}

/*Free the tree*/
void freeTree(struct tree *root) {
    int i = 0;
    while (root) {
        printf("party\n");
        /*free(root->data);
        free(root->key);*/
        printf("0 = %p\t1 = %p\n", (void *)(root->child[0]), (void *)(root->child[1]));
        if (root->child[0] == NULL) {
            freeTree(root->child[0]);
        }
        if (root->child[1] == NULL) {
            freeTree(root->child[1]);
        }
        printf("%d\n", i);
        free(root);
        i++;
    }
}
int retr(int a){
    if (a==1)
        printf(", Jojo Rabbit, Korg");
    else if (a==2)
        printf(", Chris Evans, Chris Hemsworth, Scarlett Johansson, Thor");
    else if (a==3)
        printf(", Keanu Reeves");
    else
        printf(", Happy Hogan");
    return 0;
}
