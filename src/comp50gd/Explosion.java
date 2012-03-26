package comp50gd;

public class Explosion extends BattleStuff {
	protected int countdown;
	
	public Explosion(MainGame game, double x, double y, double size) {
		super(game, x, y, "comp50gd/img/Explosion.gif");
		this.dy = 2;
		this.width = size;
		this.height = size;
		this.shield_inc = 0;
		this.countdown = 5;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		py = py + dy;
		countdown--;
		if (countdown <= 0) this.the_game.notifyDeath(this);
	}

	@Override
	public void notifyCollision(Collidable other) {
		//nothing happens on collision
	}

}
