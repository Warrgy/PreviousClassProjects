/*
 * Name: Cameron Fisher
 * ID: 1057347
 * Email: cfishe08@uoguelph.ca
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>

unsigned long valueName(char *name, int hashSize);
int hash1(char *name, int hashSize);
int shift16(int number);
int shift6(int number);
int hash2(char *ids, int hashSize);
int valueDate(char letter);
int hash3(char *num, int hashSize);

