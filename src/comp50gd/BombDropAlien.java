package comp50gd;

public class BombDropAlien extends EnemyShip {

	private double time_last_shot;
	private double fast_speed = -4;
	private double slow_speed = -2;
	
	
	public BombDropAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/PlayerDefense1.GIF");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		this.dx = 1.5;
		this.dy = slow_speed;
		this.px = x;
		this.py = 800;
		this.height = 40;
		this.width = 30;
		this.belowCover = true;
		this.score_value = 50;
		this.hull = 50;
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
		if (py > 100) 
		{
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
		
		if(py+20 < 0)
			the_game.notifyDeath(this);
	}

}
