package comp50gd;

public class Wall extends GameSprite {

	public Wall(MainGame game, double x, double y) {
		super(game, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyCollision(Collidable other) {
		return;	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
