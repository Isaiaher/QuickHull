/**
 * Isaiah Scheel
 * CSCI 371 - Computer Algorithms
 * Dr. Blaha
 * Lab 3 - Convex Hull
 * 10/12/19
 * Known Bugs: None that I know of.
 * Resources Consulted: Robin Naggi and I worked together. We were in the same room and often helped each other debug our code, but our code is not identical by any means.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A main method class that will take the file data.txt and reads in all the points listed and will output a file, result.txt,
 * and will print out the convex hull points.
 * @author Isaiah Scheel
 *
 */
public class QuickHull {

	static ArrayList<Point> points = new ArrayList<Point>();
	static int num_of_points;
	static ArrayList<Point> extreme_points = new ArrayList<Point>();
	static String dataFile;

	public static void main(String[] args) {
	    dataFile = "data5.txt";
		readFile(dataFile);		//Read the data file
		
		Collections.sort(points);		//Sort the points to find the smallest and largest points
		
	
		Point p1 = points.get(0);
		Point p2 = points.get(num_of_points -1);
		
		ArrayList<Point> s = pointsAbove(p1, p2, points);		//Find the first subset of points above the smallest and largest points
		
		
		findUpperHull(p1,p2,s);		//Call recursive method findUpperHull
		
		s = pointsBelow(p1, p2, points);		//Find subset of points bellow the smallest and largest points
		findLowerHull(p1,p2,s);		//Call recursive method findLowerHull
		
		writePlotPoints();
		
		Collections.sort(extreme_points);		//Sort the extreme points to print in a sorted matter
		
		try {
			writeFile(); //Write to file and print to console
		} catch (IOException e) {
			e.printStackTrace();
		}

		//runPython(dataFile);

	}
	
	private static void writePlotPoints() {
		File plotFile = new File("PlotResults.txt");
		PrintWriter Pointwriter = null;
		try {
			Pointwriter = new PrintWriter(plotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < extreme_points.size(); i++) {
			Point temp = extreme_points.get(i);
			double x = temp.getX();
			double y = temp.getY();
			Pointwriter.print(x + " " + y + "\n");
		}
		Pointwriter.close();
		
	}
	
	
	/**
	 * Method that, despite its name. both write to the results.txt file and also prints to the console
	 * @throws IOException
	 */
	private static void writeFile() throws IOException {
		File file = new File("result.txt");
		//File plotFile = new File("PlotResults.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < extreme_points.size(); i++) {
			Point temp = extreme_points.get(i);
			double x = temp.getX();
			double y = temp.getY();
			System.out.printf("(%.3f,%.3f) \n", x, y);
			writer.printf("(%.3f,%.3f) \n", x, y);
			//Pointwriter.print(x + " " + y + "\n");
		}
		writer.close();
		
	}

	/**
	 * Reads in the file data.txt and stores the points in a Point class in an ArrayList of Points
	 */
	public static void readFile(String data) {
		String tempx, tempy;
		float x, y;
		Point p;
		File file = new File(data);
		Scanner scan = null;
		
		try {
			 scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		num_of_points = scan.nextInt();
		
		for(int i = 0; i < num_of_points; i++) {
			tempx = scan.next();
			x = Float.parseFloat(tempx);
			tempy = scan.next();
			y = Float.parseFloat(tempy);
			p = new Point(x,y);
			
			points.add(p);
		}
	}
	
	
	/**
	 * Finds the points above the line p1 to p2 with the given points in the subset array
	 * @param p1 the left most point of the line
	 * @param p2 the right most point of the line
	 * @param array the subset of points you want to test
	 * @return - returns a subset of points that are above the line
	 */
	public static ArrayList<Point> pointsAbove(Point p1, Point p2, ArrayList<Point> array) {
		ArrayList<Point> s = new ArrayList<Point>();
		int size = array.size();
		for(int i = 0; i < size; i++) {
			Point p3 = array.get(i);
			double determinant;
			determinant = determinate(p1, p2, p3);
			if(determinant > 0) {
				s.add(p3);
			}
		}
		
		return s;
	}
	
	/**
	 * Finds the points below the line p1 to p2 with the given points in the subset array
	 * @param p1 the left most point of the line
	 * @param p2 the right most point of the line
	 * @param array the subset of points you want to test
	 * @return - returns a subset of points that are below the line
	 */
	public static ArrayList<Point> pointsBelow(Point p1, Point p2, ArrayList<Point> array) {
		ArrayList<Point> s = new ArrayList<Point>();
		int size = array.size();
		for(int i = 0; i < size; i++) {
			Point p3 = array.get(i);
			double determinant;
			determinant = determinate(p1, p2, p3);
			if(determinant < 0) {
				s.add(p3);
			}
		}
		
		return s;
	}
	
	/**
	 * Finds the point that is furthest away from the line p1 to p2 
	 * @param p1 the left most point on the line
	 * @param p2 the right most point on the line
	 * @param s The subset of points you want to test
	 * @return - The point that is furthest away
	 */
	public static Point maxPt(Point p1, Point p2, ArrayList<Point> s) {
		int size = s.size();
		double temp_det;
		double det_max = determinate(p1, p2, s.get(0));
		Point maxpt = s.get(0);
		for(int i = 1; i < size; i++) {
			temp_det = determinate(p1, p2, s.get(i));
			if(temp_det > det_max) {
				det_max = temp_det;
				maxpt = s.get(i);
			}
		}
		return maxpt;
	}
	
	/**
	 * Finds the determinate of the given points
	 * @param p1 - Point 1
	 * @param p2 - Point 2
	 * @param p3 - Point 3
	 * @return - The determinate of point 1, 2, and 3
	 */
	public static double determinate(Point p1, Point p2, Point p3) {
		double p1x = p1.getX();
		double p1y = p1.getY();
		double p2x = p2.getX();
		double p2y = p2.getY();
		double p3x = p3.getX();
		double p3y = p3.getY();
		double determinant;
		//System.out.println("point 1 - (" + p1x + "," + p1y + ") " + "point 2 - (" + p2x + "," + p2y + ") " + "point 3 - (" + p3x + "," + p3y + ") ");
		determinant = (p1x * p2y) + (p3x * p1y) + (p2x * p3y) - (p3x * p2y) - (p2x * p1y) - (p1x * p3y);
		return determinant;
	}
	
	/**
	 * Recursive method to find the upperHull. When a point is found, it is added to the extreme_ponts array
	 * @param p1 - Left most point
	 * @param p2 - Right most point
	 * @param s - Subset that the method is focusing on
	 */
	public static void findUpperHull(Point p1, Point p2, ArrayList<Point> s) {
		if(s.size() == 0) {
			extreme_points.add(p1);
		}
		else {
			Point maxpt = maxPt(p1, p2, s);
			ArrayList<Point> s1 = pointsAbove(p1, maxpt, s);
			ArrayList<Point> s2 = pointsAbove(maxpt, p2, s);
			findUpperHull(p1, maxpt, s1);
			findUpperHull(maxpt, p2, s2);
		}
	}
	
	/**
	 * Uses upper hull but switches the points and uses pointsBelow for its first call.
	 * @param p1 The right most point in UpperHull
	 * @param p2 The left most point in UpperHull
	 * @param s The subset the call is focusing on
	 */
	public static void findLowerHull(Point p1, Point p2, ArrayList<Point> s) {
		findUpperHull(p2, p1, s);
	}
	
	/**
	 * A method used for debugging. Will print out the array in an organized fashion
	 * @param array - Any ArrayList of points
	 * @return - A column of points in the ArrayList array
	 */
	public static String printArray(ArrayList<Point> array) {
		String output = "";
		int size = array.size();
		for(int i = 0; i < size; i++) {
			output = output + array.get(i).toString() + "\n";
		}
		return output;
	}
	

}
