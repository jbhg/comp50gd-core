package comp50gd;

public class SeekerAlien extends EnemyShip {

	private double time_last_shot;
	private int fleeCounter;

	
	public SeekerAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/PlayerDefense2.GIF");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		this.dx = 1;
		this.dy = 2;
		this.px = x;
		this.py = 0;
		this.height = 50;
		this.width = 30;
		this.fleeCounter = 500;
		this.score_value = 80;
		hull = 50;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.abs(this.px - the_game.panel().player_x()) < 20 && py < the_game.panel().player_y()) {
				this.the_game.add(new EnemyTorp(
						(int) (this.px + this.width / 2)-2, (int) this.py
								+ (int) this.height(), this.the_game));
				
				this.time_last_shot = this.the_game.getTimeElapsed();
			}
		}
	}

	@Override
	public void move() {
		
		if (the_game.panel().player_x() > this.px) 
		{
			this.px = Math.min(the_game.panel().player_x(), px + dx);			
		} 
		else
		{
			this.px = Math.max(the_game.panel().player_x(), px - dx);
		}
		
		if (py < 50 && fleeCounter > 0)
		{
			py += dy;
		}
		
		if (fleeCounter < 0)
		{
			py -= dy;
		}
		
		fleeCounter--;
		
		if(py+20 < 0)
			the_game.notifyDeath(this);
	}

}
