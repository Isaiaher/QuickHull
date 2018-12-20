# QuickHull
This is a program that finds the convex hull of the points from a file that you have. The file hsa to be formatted a certain way, I have several to referance. The java program has one spot you need to change the name of the file to the points you would like. The Java program will print out the points on the convex hull and then also output two files, result.txt and plotResults.txt. result.txt is just a file version of what is outputed with the parenthesis and commas and all, plotResults.txt is a stripped down version for the python script to read easier.


# Getting Started

## Step 1:  Download the repo and change the file
To get started, download the repo and go into QuickHull.java on line 33 and change the name of the file to the file you are wanting to use the convex hull program on. 

## Step 2: Run the Java Program
QuickHull.java does have a main so run it and get the two files explained above. Now you have the points of the convex hull, if you want a visual, continue to step 3.

## Step 3: Run the Python Script
Run the python script by going to the directory in the command line and running 

'''
python PlotPoints.py data4.txt
'''

This command was for data4.txt but change that for whatever data file you are using. This script will then pull up a graph with the original points as blue dots and the convex Hull as a red line.

# Results
Here are some images of the python script using the points my Java program created. Each title is based off the name of the text file used so check those out for more info.

## Data.txt

![Data set 1](https://github.com/Isaiaher/QuickHull/blob/master/Images/Data.png)


## Data2.txt

![Data set 2](https://github.com/Isaiaher/QuickHull/blob/master/Images/Data2.png)


## Data3.txt

![Data set 3](https://github.com/Isaiaher/QuickHull/blob/master/Images/Data3.png)


## Data4.txt

![Data set 4](https://github.com/Isaiaher/QuickHull/blob/master/Images/Data4.png)


## Data5.txt

![Data set 5](https://github.com/Isaiaher/QuickHull/blob/master/Images/Data5.png)


