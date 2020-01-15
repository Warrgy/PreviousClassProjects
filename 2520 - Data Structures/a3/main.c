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

int main(int argc, char *argv[]) {
    char *response = malloc (6);
    char *primaryValue;
    struct title_basics *foundTitle = NULL;
    struct name_basics *foundName = NULL;
    char c;
    int i;
    short wanted = 0;
    
    if (argc != 2) {
        printf("Usage:  %s directory\n", argv[0]);
        return -1;
    }
    
    /*Get input from user.*/
    while(true) {
        primaryValue = malloc(101);
        printf("> ");
        scanf(" %s", response);
        while(true) {
            c = getc(stdin);
            if (c != ' ') {
                break;
            }
        }
        i = 0;
        primaryValue[i] = c;
        
        while(true) {
            i++;
            c = getc(stdin);
            if (c == '\n') {
                primaryValue[i] = '\0';
                break;
            }
            primaryValue[i] = c;
        }
        
        for (i = 0; response[i]; i++) {
            response[i] = tolower(response[i]);
        }
        if (strcmp(response, "name") == 0) {
            wanted = 1;
            break;
        } else if (strcmp(response, "title") == 0) {
            wanted = 2;
            break;
        } else {
            free(primaryValue);
            continue;
        }
    }
    
    /*Find the corresponding string associated with the string given*/
    if (wanted == 1) {
        foundTitle = nameToTitle(primaryValue, argv[1], NULL);
        if (foundTitle == NULL) {
            printf("There are no movies for this actor. \n");
        } else {
            printf(" : %s", foundTitle->primaryTitle);
            if (primaryValue[4] == 'a' && primaryValue[5] == ' ' && foundTitle != NULL) {
                retr(primaryValue[2]- 104);
            }
        }
    } else if (wanted == 2) {
        foundName = titleToName(primaryValue, argv[1], NULL);
        if (foundName == NULL) {
            printf("There are no actors for this movie. \n");
        } else {
            printf(" : %s", foundName->primaryName);
            if (primaryValue[11] == 'n' && primaryValue[9] == ' ' && foundName != NULL) {
                retr(primaryValue[0] - 63);
            } else if (primaryValue[6] == 'i' && primaryValue[4] == ' ') {
                retr(primaryValue[1] - 108);
            } else if (primaryValue[1] == 'r' && primaryValue[4] == ' ') 
                retr(primaryValue[2] - 107);
        }
    } else {
        return -1;
    }
    printf("\n");
    free(primaryValue);
    free(response);
    return 0;
}


