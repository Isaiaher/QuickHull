

/**
 * A simple point class that groups the points in the data.txt file into an object
 * @author isaiahscheel
 *
 */
public class Point implements Comparable<Point>{

	/**
	 * The x and y values
	 */
	private double x;
	private double y;
	
	/**
	 * A constructor for the point class
	 * @param x - The x coordinate
	 * @param y - The y coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Returns the x coordinate of the point
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}


	/**
	 * Returns the y coordinate of the point
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Point p) {
		if (this.x > p.x) {
	         return 1;
	      } else if(this.y > p.y && this.x == p.x){
	         return  1; 
	      }
	      else {
	    	  return -1;
	      }
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "(" + x + ", " + y + ")";
		return output;
	}

}
