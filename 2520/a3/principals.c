/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "principals.h"
#include "common.h"

struct title_principals *getPrincipals(char *path) {
    struct title_principals *principals;
    char *fileName = malloc(strlen(path) + 17);
    char *string = malloc(257);
    char *profession, *category;
    int i, counter = 0, start, end, principalsCounter = 0;
    bool getData;
    FILE *fp;
    
    strcpy(fileName, path);
    strcat(fileName, "/title.principals.tsv");
    
    fp = fopen(fileName, "r");
    if (fp == NULL) {
        printf("Unable to open file.\n");
        return NULL;
    }
    
    fgets(string, 256, fp);
    
    while (!feof(fp)) {
        if (fgets(string, 256, fp) == NULL) {
            break;
        }
        category = getColumn(string, 3);
        
        profession = strstr(category, "actor");
        if (profession != NULL)  {
            principalsCounter++;
        } else {
            profession = strstr(category, "actress");
            if (profession != NULL) {
                principalsCounter++;
            }
        }
        
    }
    principals = malloc(sizeof(struct title_principals) * principalsCounter);
    fseek(fp, 0, SEEK_SET);
    fgets(string, 256, fp);
    for (i = 0; i < principalsCounter; i++) {
        getData = false;
        if (fgets(string, 300, fp) == NULL) {
            break;
        }
        category = getColumn(string, 3);
        profession = strstr(category, "actor");
        if (profession != NULL) {
            getData = true;
        } else {
            profession = strstr(category, "actress");
            if (profession != NULL) {
                getData = true;
            }
        }
        if (getData) {
            /*get nconst, tconst, and characters*/

            start = 0;
            for (end = start; string[end] != '\t'; end++);
            principals[counter].tconst = malloc(end - start + 1);
            strncpy(principals[counter].tconst, string+start, end-start);
            principals[counter].tconst[end-start] = '\0';
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            principals[counter].nconst = malloc(end - start + 1);
            strncpy(principals[counter].nconst, string+start, end-start);
            principals[counter].nconst[end-start] = '\0';
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            start = end + 1;
            
            end = 256;
            principals[counter].characters = malloc(end - start + 1);
            strncpy(principals[counter].characters, string+start, end-start);
            principals[counter].characters[end-start] = '\0';
            counter++;
        }
        
    }
    principals[counter].tconst = NULL;
    principals[counter].nconst = NULL;
    fclose(fp);
    free(category);
    free(string);
    return principals;
}
