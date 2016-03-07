/*
   Programming Project 3
   CSC 15, Spring 2014, 5/09/2014
   Written by: Class ID 1913
   This program asks the user for a text file to read in from,
   reads the statistics within that file and computes the min,
   max, mean, median, mode, and standard deviation using
   an array. The output is shown onscreen or written into
   another file, based on what the user wants.
*/

import java.util.*;
import java.io.*;

public class Stats {
public static void main(String[] args) throws FileNotFoundException {

// Ask user for file name and create File object
Scanner console = new Scanner(System.in);
System.out.print("File name: ");
String fileName = console.nextLine();
File f = new File(fileName);
Scanner in = new Scanner(f);

// Read first integer in the file as the number count and 
// put the rest of the numbers into an array
int numberofNumbers = in.nextInt();
double[] a = new double[numberofNumbers];
for(int i = 0; i < a.length; i++) {
   in.nextLine();
   a[i] = in.nextDouble();
}

// Sort the array to make finding min/max/median easier
// Call the rest of the methods to find all of the statistics
// Note that these methods mirror the instructions given
// by Practice-It, so the mode method will return the lowest
// number if there is a tie.
Arrays.sort(a);
double max = a[a.length-1];
double min = a[0];
double mean = mean(a, numberofNumbers);
double median = median(a, numberofNumbers);
double mode = mode(a);
double stdev = stdev(a);

// Ask user where output should be shown
System.out.print("Type a file name, or push return to output to screen: ");
String outputfileName = console.nextLine();
if(outputfileName.length() == 0) {
   System.out.printf("Max: %.3f", max);
   System.out.println();
   System.out.printf("Min: %.3f", min);
   System.out.println();
   System.out.printf("Mean: %.3f", mean);
   System.out.println();
   System.out.printf("Median: %.3f", median);
   System.out.println();
   System.out.printf("Mode: %.3f", mode);
   System.out.println();
   System.out.printf("Standard deviation: %.3f", stdev);
} else {
   File fout = new File(outputfileName);
   PrintStream out = new PrintStream(fout);
   out.printf("Max: %.3f", max);
   out.println();
   out.printf("Min: %.3f", min);
   out.println();
   out.printf("Mean: %.3f", mean);
   out.println();
   out.printf("Median: %.3f", median);
   out.println();
   out.printf("Mode: %.3f", mode);
   out.println();
   out.printf("Standard deviation: %.3f", stdev);
}

}

// Methods
public static double median(double[] data, int size) {
   double evenMedian1;
   double evenMedian2;
   if (size % 2 == 0) {
      evenMedian1 = data[data.length / 2];
      evenMedian2 = data[(data.length / 2) - 1];
      return (evenMedian1 + evenMedian2) / 2;
   } else {
   return data[data.length / 2];
   }
}

public static double mean(double[] data, int size) {
   double sum = 0;
   for(int i = 0; i < data.length; i++) {
      sum = sum + data[i];
   }
   return sum / size;
}

public static double mode(double[] data) {
   int counter = 0;
   int maxCount = 0;
   double mode = 0;
   double mode2 = 0;
   for (int i = 0; i < data.length; i++) {
      counter = 0;
      for (int j = 0; j < data.length; j++ ) {
         if (data[i] == data[j]) {
            counter = counter + 1;
         }
      }
      if(counter > maxCount) {
         mode = data[i];
         maxCount = counter;
      }
      if(counter == maxCount) {
        mode2 = data[i];
      }
      if(mode2 < mode) {
         mode = mode2;
      }
   }
   return mode;
}

public static double stdev(double[] data) {
   double sum = 0;
   double sum2 = 0;
   for (int i = 0; i<= data.length-1; i++) {
      sum = sum + data[i];
   }
   double average = sum / data.length;
   for (int i = 0; i <= data.length-1; i++) {
      sum2 = sum2 + Math.pow(data[i] - average, 2);
   }
   double stdev = Math.sqrt(sum2/(data.length-1));
   return stdev;
}

}