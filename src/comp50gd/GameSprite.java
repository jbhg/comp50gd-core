package comp50gd;

public abstract class GameSprite implements Collidable {
	/*
	 * Instance variables for the global sprite object
	 */	
	
	protected CollisionEntity coll_shape;
	protected Sprite sprite[];
	protected MainGame the_game;
	protected double px, py; // position
	protected double dx, dy, vel; // velocity x, y, overall.
	protected double ax, ay, acc; // acceleration x, y, overall.
	protected double width, height;// size of this object.

	public GameSprite(MainGame game, double x, double y) {
		the_game = game;
		this.px = x;
		this.py = y;
		this.sprite = null;

		initialize();
	}
	
	
	public GameSprite(MainGame game, double x, double y, String ref) {
		the_game = game;
		this.px = x;
		this.py = y;
		this.sprite = new Sprite[1];
		this.sprite[0] = SpriteStore.getSpriteStore().getSprite(ref);
		
		this.initialize();
	}
	
	public GameSprite(MainGame game, double x, double y, String ref1, String ref2) {
		the_game = game;
		this.px = x;
		this.py = y;
		this.sprite = new Sprite[2];
		this.sprite[0] = SpriteStore.getSpriteStore().getSprite(ref1);
		this.sprite[1] = SpriteStore.getSpriteStore().getSprite(ref2);
		this.initialize();
	}
	
	public GameSprite(MainGame game, double x, double y, String ref1, String ref2, String ref3) {
		the_game = game;
		this.px = x;
		this.py = y;
		this.sprite = new Sprite[3];
		this.sprite[0] = SpriteStore.getSpriteStore().getSprite(ref1);
		this.sprite[1] = SpriteStore.getSpriteStore().getSprite(ref2);
		this.sprite[2] = SpriteStore.getSpriteStore().getSprite(ref3);
		this.initialize();
	}
	
	private void initialize()
	{
		dx = 0.5;
		dy = 0.5;
		width = 40;
		height = 40;		
	}
	
	public void setGame(MainGame m)
	{
		this.the_game = m;
	}


	/**
	 * Based on code from farrell2k.
	 */
	public abstract void move();
	/**
	 * Based on code from Ming Chow, Tufts A02 M04
	 * 
	 * @param other
	 * @return
	 */
	/*public boolean collidesWith(GameSprite other) {
		
		//me
		if (other.getShape() instanceof RectEntity) {
			Rectangle him = new Rectangle();
			me.setBounds((int) px, (int) py, this.getWidth(), this.getHeight());
			him.setBounds((int) other.px(), (int) other.py(), other.width(),
					other.height());
			return me.intersects(him);
		} else if (other.getShape() instanceof EllipseEntity) {
			Ellipse2D him = new Ellipse2D.Double();
			me.setBounds((int) px, (int) py, other.width(), other.height());
			him.setFrame((int) other.px(), (int) other.py(), other.width(),
					other.height());
			return him.intersects(me);
		} else if (other.getShape() instanceof TriangleEntity) {
			boolean collided = false;
			Point2D p1 = ((TriangleEntity) other.getShape()).p1;
			Point2D p2 = ((TriangleEntity) other.getShape()).p2;
			Point2D p3 = ((TriangleEntity) other.getShape()).p3;
			collided = me.intersectsLine(p1.getX(), p1.getY(), p2.getX(), p2
					.getY())
					|| me.intersectsLine(p2.getX(), p2.getY(), p3.getX(), p3
							.getY())
					|| me.intersectsLine(p3.getX(), p3.getY(), p1.getX(), p1
							.getY());
			return collided;
		} else {
			System.out.println("Other Entity's shape is ambiguous!");
			return false;
		}

		return false;
	}*/

	// actions upon the end of this object.
	protected void die() {
	}

	public double width() {
		return this.width;
	}

	public double height() {
		return this.height;
	}

	public double px() {
		return this.px;
	}

	public double py() {
		return this.py;
	}

	public double dx() {
		return this.dx;
	}

	public double dy() {
		return this.dy;
	}
	
	public abstract int getID();	

	public CollisionEntity getCollisionShape() {
		return coll_shape;
	}

	public boolean collidesWith(GameSprite other)
	{
		return this.coll_shape.isCollision(other.getCollisionShape());
	}
	
	public Sprite sprite(int spriteNum)
	{
		if (this.sprite==null) return null;
		return this.sprite[spriteNum];
	}
	
	public void setPosition(double x, double y)
	{
		this.px = x;
		this.py = y;
	}
	
	public abstract void notifyCollision(Collidable other);

}