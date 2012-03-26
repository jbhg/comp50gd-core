package comp50gd;

public class SimpleVerticalDropAlien extends EnemyShip {

	
	public SimpleVerticalDropAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/Enemy3.GIF");
		this.shield_strength_current = 20;
		this.gun_cooling_rate = 500;
		this.dy = 4;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		py += dy;

		if (py > Settings.WINDOW_HEIGHT) {
			py -= Settings.WINDOW_HEIGHT;
		}
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new EnemyBullet(this.the_game,(int) (this.px + this.width / 2) + 4, (int) this.py
								+ (int) this.height(), 8, 4));
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) - 4, (int) this.py
								+ (int) this.height(), 8, 4));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}
		
	}	
	
}
