package comp50gd;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class EllipseEntity extends CollisionEntity{

	public EllipseEntity(Collidable obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	public boolean collidesWith(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isCollision(CollisionEntity other) {

		Rectangle me = new Rectangle();

		if (other.shape() instanceof RectEntity) {
			Rectangle him = new Rectangle();
			me.setBounds((int) obj.px(), (int) obj.py(), (int) obj.width(),
					(int) obj.height());
			him.setBounds((int) other.obj.px(), (int) other.obj.py(),
					(int) other.obj.width(), (int) other.obj.height());
			return me.intersects(him);
		} else if (other.shape() instanceof EllipseEntity) {
			Ellipse2D him = new Ellipse2D.Double();
			me.setBounds((int) obj.px(), (int) obj.py(), (int) obj.width(),
					(int) obj.height());
			him.setFrame((int) other.obj.px(), (int) other.obj.py(),
					(int) other.obj.width(), (int) other.obj.height());
			return him.intersects(me);
		} else if (other.shape() instanceof TriangleEntity) {
			//Not yet supported
			
			throw new UnsupportedOperationException();
			
			/*

			boolean collided = false;
			Point2D p1 = ((TriangleEntity) other.shape()).p1;
			Point2D p2 = ((TriangleEntity) other.shape()).p2;
			Point2D p3 = ((TriangleEntity) other.shape()).p3;
			collided = me.intersectsLine(p1.getX(), p1.getY(), p2.getX(), p2
					.getY())
					|| me.intersectsLine(p2.getX(), p2.getY(), p3.getX(), p3
							.getY())
					|| me.intersectsLine(p3.getX(), p3.getY(), p1.getX(), p1
							.getY());
			return collided;
			 */
		} else {
			System.err.println("Other Entity's shape is ambiguous!");
			return false;
		}

	}

}
