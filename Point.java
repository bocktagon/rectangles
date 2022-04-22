package bock.matthew;

/**
 * A point is an X,Y coordinate in 2D space.
 */
public class Point {
    public final int X;
    public final int Y;

    Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Point)) {
            return false;
        }

        Point otherPoint = (Point) obj;
        return this.X == otherPoint.X && this.Y == otherPoint.Y;
    }
}
