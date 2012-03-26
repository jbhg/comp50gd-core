/**
 * The collision entity is the superclass of all shape entities which detect collision.
 */
package comp50gd;

/**
 * @author joelgreenberg
 * 
 */
public abstract class CollisionEntity {
	
	//This entity needs a reference to the object which it simulates.
	protected Collidable obj;

	//This entity also needs a representation. Let the subclasses determine this.
	protected java.awt.Shape shape;
	
	//This entity should have a default orientation, in degrees.
	protected double default_orientation_deg;
	
	//This entity may be able to rotate.
	protected double current_orientation_deg;
	

	public double current_orientation()
	{
		//We check to make sure the current stored value is legal.
		while(this.current_orientation_deg < 0)
			this.current_orientation_deg += 360;
		while(this.current_orientation_deg >= 360)
			this.current_orientation_deg -= 360;
		return this.current_orientation_deg;
	}
	
	public double default_orientation()
	{
		//We check to make sure the current stored value is legal.
		while(this.default_orientation_deg < 0)
			this.default_orientation_deg += 360;
		while(this.default_orientation_deg >= 360)
			this.default_orientation_deg -= 360;
		return this.default_orientation_deg;
	}
	
	
	public CollisionEntity(Collidable obj)
	{
		this.obj = obj;
	}
	
	
	public java.awt.Shape shape() {
		return shape;
	}

	public abstract boolean isCollision(CollisionEntity other);

}
