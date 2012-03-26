package comp50gd;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public class TriangleEntity extends CollisionEntity {
	
	private int[] x_coords, y_coords;
	
	public TriangleEntity(Collidable obj) {
		super(obj);
		x_coords = null;
		y_coords = null;
		shape = new Polygon(new int[] { }, new int[] { }, 0);
		// TODO Auto-generated constructor stub
	}

	public TriangleEntity(Collidable obj, int p1_x, int p1_y, int p2_x, int p2_y, int p3_x, int p3_y) {
		super(obj);
		x_coords = new int[] {p1_x, p2_x, p3_x};
		y_coords = new int[] {p1_y, p2_y, p3_y};
		shape = new Polygon(x_coords, y_coords, 3);
		// TODO Auto-generated constructor stub
	}

	public Point2D p1(){return new Point(x_coords[0],y_coords[0]);}
	public Point2D p2(){return new Point(x_coords[1],y_coords[1]);}
	public Point2D p3(){return new Point(x_coords[2],y_coords[2]);}

	
	public boolean isCollision(CollisionEntity other) {
		// Not yet supported.
		throw new UnsupportedOperationException();
	}
	

}
