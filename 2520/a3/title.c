/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include "title.h"
#include "common.h"

struct title_basics *getTitle(char *path) {
    struct title_basics *movies;
    char *fileName = malloc(strlen(path) + 17);
    char *string = malloc(257);
    char *type, *titleType, *isAdult, *adultType;
    int i, counter = 0, start, end, movieCounter = 0;
    FILE *fp;
    
    strcpy(fileName, path);
    strcat(fileName, "/title.basics.tsv");
    
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
        type = getColumn(string, 1);
        isAdult = getColumn(string, 4);
        
        titleType = strstr(type, "movie");
        adultType = strstr(isAdult, "0");
        if ((titleType != NULL) && (adultType != NULL)) {
            movieCounter++;
        }
        
    }
    movies = malloc(sizeof(struct title_basics) * movieCounter);
    fseek(fp, 0, SEEK_SET);
    fgets(string, 256, fp);
    for (i = 0; i < movieCounter; i++) {
        if (fgets(string, 256, fp) == NULL) {
            break;
        }
        type = getColumn(string, 1);
        isAdult = getColumn(string, 4);
        
        titleType = strstr(type, "movie");
        adultType = strstr(isAdult, "0");
        if ((titleType != NULL) && (adultType != NULL)) {
            /*Get tconst and primary title*/
            
            start = 0;
            for (end = start; string[end] != '\t'; end++);
            movies[counter].tconst = malloc(end - start + 1);
            strncpy(movies[counter].tconst, string+start, end-start);
            movies[counter].tconst[end-start] = '\0';
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            start = end + 1;
            
            for (end = start; string[end] != '\t'; end++);
            
            movies[counter].primaryTitle = malloc(end - start + 1);
            strncpy(movies[counter].primaryTitle, string+start, end-start);
            movies[counter].primaryTitle[end-start] = '\0';
            
            counter++;
        }
        
    }
    movies[counter].tconst = NULL;
    movies[counter].primaryTitle = NULL;
    
    fclose(fp);
    free(type);
    free(isAdult);
    free(string);
    return movies;
}



