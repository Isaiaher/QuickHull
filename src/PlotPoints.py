# A python script that will visually graph the points from the Quick Hull 
# Java program I wrote. The steps are as follows:
# 1. Run the java program with the points you want
# 2. Run this script and pass the argument of the text file with the original points
# 3. A graph will appear with the convex hull in a red line and the Original points in blue
# Created by: Isaiah Scheel
import sys
import matplotlib.pyplot as plt

#Grabbing the original data file name (The one that you are trying to find the convex hull of)
dataFile = sys.argv[1]
ogData = open("../" + dataFile, "r")

#Creating and initializing the data structures to hold the Original points
x1,y1 = [], []
for l in ogData:
    row = l.split()
    #Need this statement to skip the first line if the txt file that tells how many lines there are
    if(len(row) != 1):
        x1.append(float(row[0]))
        y1.append(float(row[1]))
#Plot the original points
plt.plot(x1,y1, 'bo')

#Open the file that contains the results from the Quick Hull Java program
hullData = open("../PlotResults.txt", "r")

#Creating and initializing the data structures to hold the Hull points
x2,y2 = [], []
for l in hullData:
    print(l)
    row = l.split()
    if(len(row) != 1):
        print("in if stmt")
        x2.append(float(row[0]))
        y2.append(float(row[1]))

#Adding the first point to the end so it creates an enclosure
x2.append(x2[0])
y2.append(y2[0])
#Plot the convex hull as a red line
plt.plot(x2, y2, "r-")
#Show the plot
plt.show()
