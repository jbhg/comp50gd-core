/**
 * Two 'Collidable' objects must have a detectable boolean displaying whether they have collided or not.
 */
package comp50gd;
/**
 * 
 * 
 * @author Joel B. Greenberg
 *
 */
public interface Collidable {
	public CollisionEntity getCollisionShape();
	public void notifyCollision(Collidable other);
	public double width();
	public double height();
	public double px();
	public double py();
}
