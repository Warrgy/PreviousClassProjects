#!/usr/bin/perl
#
#   Packages and modules
#
use strict;
use warnings;
use version;         our $VERSION = qv('5.16.0');   # This is the version of Perl to be used
use Statistics::R;

my $infilename;
my $pdffilename;

#
#   Check that you have the right number of parameters
#
if ($#ARGV != 1 ) {
   print "Usage: plotAccommodation.pl <input file name> <pdf file name>\n" or
      die "Print failure\n";
   exit;
} else {
   $infilename = $ARGV[0];
   $pdffilename = $ARGV[1];
}  

print "input file = $infilename\n";
print "pdf file = $pdffilename\n";

# Create a communication bridge with R and start R
my $R = Statistics::R->new();

# Name the PDF output file for the plot  
#my $Rplots_file = "./Rplots_file.pdf";

# Set up the PDF file for plots
$R->run(qq`pdf("$pdffilename" , paper="letter")`);

# Load the plotting library
$R->run(q`library(ggplot2)`);

# read in data from a CSV file
$R->run(qq`data <- read.csv("$infilename")`);

# plot the data as a line plot with each point outlined
$R->run(q`ggplot(data, aes(x=Year, y=Cost, colour=Accommodation, group=Accommodation)) + geom_line() + geom_point(size=2) + ggtitle("Accommodation Trends")`);
# close down the PDF device
$R->run(q`dev.off()`);

$R->stop();
