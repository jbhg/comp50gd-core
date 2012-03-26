package comp50gd;

public class PowerUp extends BattleStuff {
	protected int countdown;
	public int ID;
	
	public PowerUp(MainGame game, double x, double y, int ID, String ref) {
		super(game, x, y, ref);
		this.dy = 2;
		this.width = 32;
		this.height = 32;
		this.coll_shape = new RectEntity(this);
		this.ID = ID;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		py = py + dy;
		if (py > Settings.WINDOW_HEIGHT) this.the_game.notifyDeath(this);
	}

	@Override
	public void notifyCollision(Collidable other) {
		//nothing happens on collision
	}

}

