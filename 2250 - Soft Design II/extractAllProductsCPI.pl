#!/usr/bin/perl
#
#   This program gets in a CPI csv file and then puts the specific data for All-items into a file.
#
use strict;
use warnings;
use version;   our $VERSION = qv('5.16.0');

#
#   Contributor(s): Cameron Fisher
#
#   Commandline Parameters: 1
#       $ARGV[0] = name of CPI csv file
#
my $i;
my $increment = 0;
my $filename;
my $fh;
my $fileFH;
my @data;
my $writeFile = 'allProductsCPIData.csv';

#Put filename into variable
if ($#ARGV != 0) {
    print "Enter only the CPI CSV file on commmand line.\n";
    exit;
} else {
    $filename = $ARGV[0];
}

open ($fh, '<', $filename) or die "Unable to open file: $!\n";
open ($fileFH, '>', $writeFile) or die "Unable to open write file.\n";

#Tell user that the reading and writing has starting
print "Loading contents into file...\n";

#Loop through the entire csv file
while (my $line = <$fh>) {
    chomp $line;
    
    #Put each section of the current row into array, that will get cleared after every row
    my @data = split (/,/ , $line);
    
    #If there is white space then run loop again.
    if ($data[3] =~ /^\s*$/) {
        print "$line\n";
        next;
    }
    
    #If the data is All-items then print the specific information from CPI to file
    if ($data[3] eq '"All-items"') {
        foreach $i(@data) {
            if (($increment == 0) || ($increment == 1) || ($increment == 2) || ($increment == 4) || ($increment == 10)) {
                print $fileFH $i;
                print $fileFH ',';
            }
            $increment = $increment + 1;
        }
        print $fileFH "\n";
        $increment = 0;
    }
}

#Close files
close $fh or die "Unable to close: $filename\n";
close $fileFH or die "Unable to close: $writeFile\n";

#Tell user it is finished
print "\nFinished.\n";
