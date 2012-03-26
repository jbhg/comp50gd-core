package comp50gd;

public class PlayerBullet extends BattleStuff {

	public PlayerBullet(MainGame game, double x, double y) {
		super(game, x, y);
		this.dy = 7;
		this.width = 2;
		this.height = 10;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		py -= dy;
		if(py < -20)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other) {
		if(other instanceof EnemyShip)
			this.the_game.notifyDeath(this);
	}
	
}
