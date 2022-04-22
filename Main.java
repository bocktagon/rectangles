package bock.matthew;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    System.out.println("---Intersection Test Cases---");
	    intersectionTestCases();
	    System.out.println();

	    System.out.println("---Containment Test Cases---");
	    containmentTestCases();
	    System.out.println();

	    System.out.println("---Adjacency Test Cases---");
	    adjacencyTestCases();
    }

    private static void intersectionTestCases() {

        // Define edges ahead of time for consistency
        int north = 4;
        int east = 4;
        int south = 1;
        int west = 1;
        Rectangle base = new Rectangle(north, east, south, west);

        // Define all 8 possible points of intersection
        Point northLeftIntersection = new Point(2, north);
        Point northRightIntersection = new Point(3, north);
        Point eastUpperIntersection = new Point(east, 3);
        Point eastLowerIntersection = new Point(east, 2);
        Point southLeftIntersection = new Point(2, south);
        Point southRightIntersection = new Point(3, south);
        Point westUpperIntersection = new Point(west, 3);
        Point westLowerIntersection = new Point(west, 2);


        // Base cases: second rectangle overlaps an edge of the base rectangle
        System.out.println("northern edge: " + validatePointCollection(
                base.findIntersections(new Rectangle(5, 3, 3, 2)),
                northLeftIntersection, northRightIntersection
        ));

        System.out.println("eastern edge: " + validatePointCollection(
                base.findIntersections(new Rectangle(3, 5, 2, 2)),
                eastUpperIntersection,
                eastLowerIntersection
        ));

        System.out.println("southern edge: " + validatePointCollection(
                base.findIntersections(new Rectangle(3, 3, 0, 2)),
                southLeftIntersection,
                southRightIntersection
        ));

        System.out.println("western edge: " + validatePointCollection(
                base.findIntersections(new Rectangle(3, 3, 2, 0)),
                westUpperIntersection,
                westLowerIntersection
        ));

        // Next case: second rectangle overlaps a corner of the base rectangle
        System.out.println("northeast corner: " + validatePointCollection(
                base.findIntersections(new Rectangle(5, 5, 3, 3)),
                northRightIntersection,
                eastUpperIntersection
        ));

        System.out.println("southeast corner: " + validatePointCollection(
                base.findIntersections(new Rectangle(2, 5, 0, 3)),
                eastLowerIntersection,
                southRightIntersection
        ));

        System.out.println("southwest corner: " + validatePointCollection(
                base.findIntersections(new Rectangle(2, 2, 0, 0)),
                southLeftIntersection,
                westLowerIntersection
        ));

        System.out.println("northwest corner: " + validatePointCollection(
                base.findIntersections(new Rectangle(5, 2, 3, 0)),
                westUpperIntersection,
                northLeftIntersection
        ));

        // Final case: the second rectangle is long enough to overlap both edges of the base rectangle
        System.out.println("west and east edges (4 points): " + validatePointCollection(
                base.findIntersections(new Rectangle(3, 5, 2, 0)),
                westUpperIntersection,
                westLowerIntersection,
                eastUpperIntersection,
                eastLowerIntersection
        ));

        System.out.println("north and south edges (4 points): " + validatePointCollection(
                base.findIntersections(new Rectangle(5, 3, 0, 2)),
                northLeftIntersection,
                northRightIntersection,
                southLeftIntersection,
                southRightIntersection
        ));
    }

    private static void containmentTestCases() {
        Rectangle base = new Rectangle(10, 10, 5, 5);
        Rectangle contained = new Rectangle(9, 9, 6, 6);
        Rectangle intersect = new Rectangle(12, 9, 6, 6);
        Rectangle disjoint = new Rectangle(4, 4, 0, 0);

        System.out.print("Rectangle B is contained within rectangle A: ");
        System.out.println(validate(base.contains(contained), true));

        System.out.print("Rectangle C intersects with rectangle A, no containment: ");
        System.out.println(validate(base.contains(intersect), false));

        System.out.print("Rectangle D is completely separate from rectangle A, no containment: ");
        System.out.println(validate(base.contains(disjoint), false));
    }

    private static void adjacencyTestCases() {
        Rectangle base = new Rectangle(10, 10, 5, 5);

        System.out.println("-north edge");
        System.out.println("subline: " + validate(base.adjacentTo(new Rectangle(11, 9, 10, 6)), true));
        System.out.println("proper: " + validate(base.adjacentTo(new Rectangle(11, 10, 10, 5)), true));
        System.out.println("partial: " + validate(base.adjacentTo(new Rectangle(11, 12, 10, 6)), true));
        System.out.println("no shared edge: " + validate(base.adjacentTo(new Rectangle(11, 9, 11, 6)), false));
        System.out.println("too far west: " + validate(base.adjacentTo(new Rectangle(11, 4, 10, 0)), false));
        System.out.println("too far east: " + validate(base.adjacentTo(new Rectangle(11, 12, 10, 11)), false));

        System.out.println("-east edge");
        System.out.println("subline: " + validate(base.adjacentTo(new Rectangle(9, 11, 6, 10)), true));
        System.out.println("proper: " + validate(base.adjacentTo(new Rectangle(10, 11, 5, 10)), true));
        System.out.println("partial: " + validate(base.adjacentTo(new Rectangle(11, 11, 6, 10)), true));
        System.out.println("no shared edge: " + validate(base.adjacentTo(new Rectangle(10, 12, 5, 11)), false));
        System.out.println("too far north: " + validate(base.adjacentTo(new Rectangle(12, 11, 11, 10)), false));
        System.out.println("too far south: " + validate(base.adjacentTo(new Rectangle(4, 11, 3, 10)), false));

        System.out.println("-south edge");
        System.out.println("subline: " + validate(base.adjacentTo(new Rectangle(5, 9, 4, 6)), true));
        System.out.println("proper: " + validate(base.adjacentTo(new Rectangle(5, 10, 4, 5)), true));
        System.out.println("partial: " + validate(base.adjacentTo(new Rectangle(5, 12, 4, 6)), true));
        System.out.println("no shared edge: " + validate(base.adjacentTo(new Rectangle(4, 10, 3, 5)), false));
        System.out.println("too far west: " + validate(base.adjacentTo(new Rectangle(5, 4, 4, 0)), false));
        System.out.println("too far east: " + validate(base.adjacentTo(new Rectangle(5, 12, 4, 11)), false));

        System.out.println("-west edge");
        System.out.println("subline: " + validate(base.adjacentTo(new Rectangle(9, 5, 6, 4)), true));
        System.out.println("proper: " + validate(base.adjacentTo(new Rectangle(10, 5, 5, 4)), true));
        System.out.println("partial: " + validate(base.adjacentTo(new Rectangle(11, 5, 6, 4)), true));
        System.out.println("no shared edge: " + validate(base.adjacentTo(new Rectangle(10, 4, 5, 3)), false));
        System.out.println("too far north: " + validate(base.adjacentTo(new Rectangle(12, 5, 11, 4)), false));
        System.out.println("too far south: " + validate(base.adjacentTo(new Rectangle(4, 5, 3, 4)), false));
    }

    private static String validate(boolean condition, boolean expectedResult) {
        if(condition == expectedResult) return "PASS";
        else return "FAIL";
    }

    private static String validatePointCollection(List<Point> actuals, Point... expected) {
        if(actuals.size() != expected.length) {
            return "FAIL - wrong number of intersections";
        }

        for(Point point : expected) {
            if(!actuals.contains(point)) {
                return "FAIL - improper intersection";
            }
        }

        return "PASS";
    }
}
