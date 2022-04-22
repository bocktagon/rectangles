package bock.matthew;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle is represented by its 4 edges: north, east, south, and west. Each edge is represented as a single
 * number, which represents its X or Y coordinate in the plane (horizontal lines take the form Y = n and vertical
 * lines take the form X = n). The space in between these 4 infinite length lines is our rectangle. Each corner of the
 * rectangle can be inferred by concatenating the X and Y lines that intersect at that point.
 */
public class Rectangle {

    private int north;
    private int east;
    private int south;
    private int west;

    public Rectangle(int north, int east, int south, int west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    /**
     * Checks to see if this is a valid rectangle. A valid rectangle has a volume, and has its 4 edges oriented
     * properly with respect to each other (the north edge can't be below the south edge and the east edge can't be
     * to the left of the west edge)
     *
     * @return true if this rectangle is valid
     */
    public boolean isValid() {
        return north > south && east > west;
    }

    /**
     * Finds points where this rectangle intersects with another given rectangle.
     *
     * Since we are looking for the specific points of intersection, we must check all 4 edges against the given
     * rectangle to determine if there are 0, 1, or 2 intersections along that edge. We do this by way of 2 helper
     * methods with logic to check whether this rectangles vertical and horizontal edges intersect with the given
     * rectangle's edges.
     *
     * @param otherRect a Rectangle to compare this one to
     * @return a list of Points representing intersections
     */
    public List<Point> findIntersections(Rectangle otherRect) {

        ArrayList<Point> results = new ArrayList<Point>();
        results.addAll(findHorizontalIntersections(this.north, this.west, this.east, otherRect));
        results.addAll(findHorizontalIntersections(this.south, this.west, this.east, otherRect));
        results.addAll(findVerticalIntersections(this.west, this.north, this.south, otherRect));
        results.addAll(findVerticalIntersections(this.east, this.north, this.south, otherRect));
        return results;
    }

    /**
     * Checks if a line crosses the east and/or west edge(s) of a given rectangle
     *
     * @param yCoord the y coordinate of the horizontal line
     * @param leftBound the leftmost point of the horizontal line
     * @param rightBound the rightmost point of the horizontal line
     * @param otherRect the rectangle to check this line against
     * @return a list of points where this line crosses the given rectangle
     */
    private List<Point> findHorizontalIntersections(int yCoord, int leftBound, int rightBound, Rectangle otherRect) {
        ArrayList<Point> intersections = new ArrayList<Point>();

        // if the line is outside the other rectangle, there is no intersection, return empty
        if(yCoord > otherRect.north || yCoord < otherRect.south) {
            return intersections;
        }

        // Check if the line crosses the west edge of the other rectangle
        if(leftBound < otherRect.west && rightBound > otherRect.west) {
            intersections.add(new Point(otherRect.west, yCoord));
        }

        // Check if the line crosses the east edge of the other rectangle
        if(leftBound < otherRect.east && rightBound > otherRect.east) {
            intersections.add(new Point(otherRect.east, yCoord));
        }

        return intersections;
    }

    /**
     * Checks if a line crosses the north and/or south edge(s) of a given rectangle
     *
     * @param xCoord the x coordinate of the vertical line
     * @param upperBound the uppermost point of the vertical line
     * @param lowerBound the lowermost point of the vertical line
     * @param otherRect the rectangle to compare this line against
     * @return a list of points where this line crosses the given rectangle
     */
    private List<Point> findVerticalIntersections(int xCoord, int upperBound, int lowerBound, Rectangle otherRect) {
        ArrayList<Point> intersections = new ArrayList<Point>();

        // if the line is outside the other rectangle, there is no intersection, return empty
        if(xCoord > otherRect.east || xCoord < otherRect.west) {
            return intersections;
        }

        // Check if the line crosses the north edge of the other rectangle
        if(upperBound > otherRect.north && lowerBound < otherRect.north) {
            intersections.add(new Point(xCoord, otherRect.north));
        }

        // Check if the line crosses the south edge of the other rectangle
        if(upperBound > otherRect.south && lowerBound < otherRect.south) {
            intersections.add(new Point(xCoord, otherRect.south));
        }

        return intersections;
    }

    /**
     * Determines if this rectangle contains another given rectangle.
     *
     * To determine containment, all we need to do is check that all 4 sides of the given rectangle are within this
     * rectangle. Thanks to the way our rectangles are represented, we can do this with a quick comparison of all 4
     * lines.
     *
     * @param otherRect a Rectangle to compare this one to
     * @return true if the given rectangle is contained within this one
     */
    public boolean contains(Rectangle otherRect) {
        return this.north >= otherRect.north
                && this.east >= otherRect.east
                && this.south <= otherRect.south
                && this.west <= otherRect.west;
    }

    /**
     * Determines if this rectangle is adjacent to the given rectangle.
     *
     * Adjacency is determined by first checking to see if one side of the given rectangle shares a line with the
     * opposite side of this rectangle (ie: the south edge of the other rectangle is on the same Y-coordinate as
     * the north edge of this one). If we find such a case, we then check that the edges are actually in contact by
     * comparing the 2 edges connected to the edge we found in step 1 (ie: if the north and south edges are on the same
     * Y-coordinate, they could still not be touching if the other rectangle is too far to the east or west)
     *
     * @param otherRect a Rectangle to compare this one to
     * @return true if the given rectangle is adjacent to this one
     */
    public boolean adjacentTo(Rectangle otherRect) {
        if (this.north == otherRect.south) {
            return !(otherRect.east < this.west) // other rect is not too far west
                && !(otherRect.west > this.east); // other rect is not too far east
        }
        if (this.east == otherRect.west) {
            return !(otherRect.south > this.north) // other rect is not too far north
                && !(otherRect.north < this.south); // other rect is not too far south
        }
        if(this.south == otherRect.north) {
            return !(otherRect.east < this.west) // other rect is not too far west
                    && !(otherRect.west > this.east); // other rect is not too far east
        }
        if (this.west == otherRect.east) {
            return !(otherRect.south > this.north) // other rect is not too far north
                    && !(otherRect.north < this.south); // other rect is not too far south
        }
        return false;
    }

}
