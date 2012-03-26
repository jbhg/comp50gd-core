package comp50gd;

public class EnemyBullet extends BattleStuff {

	public EnemyBullet(MainGame game, double x, double y) {
		super(game, x, y);
		this.dy = 6;
		this.width = 3;
		this.height = 8;
		this.shield_inc = -20;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	public EnemyBullet(MainGame game, double x, double y, String ref) {
		super(game, x, y, ref);
		this.dy = 6;
		this.width = 3;
		this.height = 8;
		this.shield_inc = -20;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}
	
	public EnemyBullet(MainGame game, double x, double y, double vertical_speed, double size) {
		this(game, x, y);
		this.dy = vertical_speed;
		this.width = size;
		this.height = size;
	}

	
	@Override
	public void move() {
		py += dy;
		if(py > Settings.WINDOW_HEIGHT || py < 0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other) {
		if(other instanceof PlayerShip)
		{
			this.the_game.notifyDeath(this);
		}
	}
	
}
