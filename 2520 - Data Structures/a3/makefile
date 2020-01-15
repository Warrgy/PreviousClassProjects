#
# Name: Cameron Fisher
# ID: 1057347
# Email: cfishe08@uoguelph.ca
#

CC = gcc
CFLAGS = -Wall -ansi -pedantic

all: together

common: common.c
	$(CC) $(CFLAGS) -c common.c -o common.o

together: common binary name title principals main assignment3
	$(CC) common.o main.o name.o title.o principals.o binary.o a3.o -o a3

main: main.c
	$(CC) $(CFLAGS) -c main.c -o main.o

binary: binary.c
	$(CC) $(CFLAGS) -c binary.c -o binary.o

name: name.c
	$(CC) $(CFLAGS) -c name.c -o name.o

title: title.c
	$(CC) $(CFLAGS) -c title.c -o title.o

principals: principals.c
	$(CC) $(CFLAGS) -c principals.c -o principals.o

assignment3: a3.c
	$(CC) $(CFLAGS) -c a3.c -o a3.o

clean:
	rm *.o
