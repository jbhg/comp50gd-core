package comp50gd;

public class RAlien extends EnemyShip {

	public RAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/AirEnemy1R.gif");
		dx = 6;
		dy = 0.2;
		belowCover = false;
		this.score_value = 200;
		this.hull = 100;
		this.height = 40;
		this.width = 40;
		this.gun_cooling_rate = 150;
		this.shield_strength_current = 5;
		this.coll_shape = new RectEntity(this);
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.25) {
				this.the_game.add(new ExplodingBullet(
						this.the_game, 
						(int) (this.px + this.width / 2-10), (int) this.py
						+ (int) this.height()-10));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}

	}
	
	@Override
	public void move() {
		px += dx;
		py += dy;
		
		if(px > Settings.WINDOW_WIDTH)
			the_game.notifyDeath(this);
	}

}