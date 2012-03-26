package comp50gd;

public class SimpleShootingAlien extends EnemyShip {

	
	public SimpleShootingAlien(MainGame game, double x, double y, boolean moveRight) {
		super(game, x, y, "comp50gd/img/Enemy5.GIF");
		dx = 0; //disabled left/right movement cause the sprite sucks
		if (moveRight) dx=-dx;
		dy = 1;
		belowCover = true;
		this.gun_cooling_rate = 400;
		this.shield_strength_current = 5;
		this.coll_shape = new RectEntity(this);
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.50) {
				this.the_game.add(new Bullet360(
						(int) (this.px + this.width / 2), 
						(int) (this.py + this.height / 2), this.the_game));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}

	}
	
	@Override
	public void move() {
		px += dx;
		py += dy;
		
		if(py > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);
	}

}