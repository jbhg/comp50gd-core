package comp50gd;

public class AndreTheGiantAlien extends EnemyShip {

	private double time_last_shot;
	private double fast_speed = 10;
	private double slow_speed = 4;
	
	
	public AndreTheGiantAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/AirEnemy3.gif");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		this.dx = 1.5;
		this.dy = slow_speed;
		this.px = x;
		this.py = 0;
		this.height = 40;
		this.width = 40;
		this.score_value = 100;
		this.hull = 50;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.abs(this.px - the_game.panel().player_x()) < 20 && py < the_game.panel().player_y()) {
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) + 4, (int) this.py
								+ (int) this.height()));
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) - 4, (int) this.py
								+ (int) this.height()));
				this.time_last_shot = this.the_game.getTimeElapsed();
			}
		}
	}

	@Override
	public void move() {
		if (py < the_game.panel().player_y()) {
			dy = slow_speed;
			if (the_game.panel().player_x() > this.px) {
				this.px = Math.min(the_game.panel().player_x(), px + dx);
			} else {
				this.px = Math.max(the_game.panel().player_x(), px - dx);
			}
		}
		else
		{
			dy = fast_speed;
		}
		py += dy;
		
		if(py > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);
	}

}
