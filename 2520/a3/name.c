/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "name.h"
#include "common.h"

struct name_basics *getName(char *path) {
    struct name_basics *contacts;
    char *fileName = malloc(strlen(path) + 17);
    char *string = malloc(257);
    char *column, *profession;
    int i, counter = 0, start, end, professionCounter = 0;
    FILE *fp;
    
    strcpy(fileName, path);
    strcat(fileName, "/name.basics.tsv");
    
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
        column = getColumn(string, 4);
        
        profession = strstr(column, "actor");
        if (profession != NULL) {
            professionCounter++;
        }
        
        if (profession == NULL) {
            profession = strstr(column, "actress");
            if (profession != NULL) {
                professionCounter++;
            }
        }
    }
    contacts = malloc(sizeof(struct name_basics) * professionCounter);
    fseek(fp, 0, SEEK_SET);
    fgets(string, 256, fp);
    for (i = 0; i < professionCounter; i++) {
        fgets(string, 256, fp);
        column = getColumn(string, 4);
        
        profession = strstr(column, "actor");
        if (profession != NULL) {
            /*Get nconst and primary name*/
            
            start = 0;
            for (end = start; string[end] != '\t'; end++);
            
            contacts[counter].nconst = malloc(end - start + 1);
            strncpy(contacts[counter].nconst, string+start, end-start);
            contacts[counter].nconst[end-start] = '\0';
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            contacts[counter].primaryName = malloc(end - start + 1);
            strncpy(contacts[counter].primaryName, string+start, end-start);
            contacts[counter].nconst[end-start] = '\0';
            
                    
            counter++;
        } else {
            profession = strstr(column, "actress");
            if (profession != NULL) {
                /*Get nconst and primary name*/
                
                start = 0;
                for (end = start; string[end] != '\t'; end++);
                
                contacts[counter].nconst = malloc(end - start + 1);
                strncpy(contacts[counter].nconst, string+start, end-start);
                contacts[counter].nconst[end-start] = '\0';
                
                start = end + 1;
                
                for (end = start; string[end] != '\t'; end++);
                
                contacts[counter].primaryName = malloc(end - start + 1);
                strncpy(contacts[counter].primaryName, string+start, end-start);
                contacts[counter].primaryName[end-start] = '\0';

                counter++;
            }
        }
        
    }
    contacts[counter].primaryName = NULL;
    contacts[counter].nconst = NULL;
    fclose(fp);
    free(column);
    free(string);
    return contacts;
}


