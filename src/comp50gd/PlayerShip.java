package comp50gd;

public class PlayerShip extends Ship {

	public boolean up, down, left, right;
	protected int damaged, shielded, 
		gun_cooling_fast, gun_cooling_slow;
	protected double shield_recovery_fast, shield_recovery_slow;
	public PlayerShip(MainGame game, double x, double y) {
		super(game, (double) Settings.WINDOW_WIDTH / 2 - 40, Settings.WINDOW_HEIGHT - Settings.BOUND_DN - 60, "comp50gd/img/PlayerOffense.GIF", "comp50gd/img/PlayerOffenseDamage.GIF","comp50gd/img/PlayerOffenseShield.GIF");
		up = false;
		down = false;
		left = false;
		right = false;
		
		this.dx *= 7;
		this.dy *= 7;
		
		this.shield_max = 100;
		this.shield_recovery_slow = 0.1;
		this.shield_recovery_fast = 0.3;
		this.shield_recovery_rate = shield_recovery_slow;
		this.shield_strength_current = 100;

		this.hull_max = 100;
		this.hull_recovery_rate = 0;
		this.hull_strength_current = 100;

		this.gun_cooling_slow = 200;
		this.gun_cooling_fast = 50;
		this.gun_cooling_rate = gun_cooling_slow;
		this.time_last_shot = 0;
		
		this.coll_shape = new RectEntity(this);
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub

	}

	/**
	 * Based on a Rectangle shaped ship.
	 */
	public void move() {
		if (up) {
			this.py -= dy;
			if (this.py < 0 + Settings.BOUND_UP)
				this.py = 0 + Settings.BOUND_UP;
		}
		if (down) {
			py += dy;
			if (this.py + this.height >= Settings.WINDOW_HEIGHT
					- Settings.BOUND_DN) {
				py = Settings.WINDOW_HEIGHT - this.height - Settings.BOUND_DN;
				Settings.println("" + py);
			}
		}
		if (left) {
			this.px -= dx;
			if (this.px < 0) {
				this.px = 0;
			}
		}
		if (right) {
			this.px += dx;
			if (this.px + this.width >= Settings.WINDOW_WIDTH) {
				this.px = Settings.WINDOW_WIDTH - this.width;
			}
		}
	}
	
	public void update()
	{
		// Update player sprite selection information
		if (this.damaged>0) this.damaged --;
		if (this.shielded>0) this.shielded --;
		
		// Re-charge shield
		this.shield_strength_current += this.shield_recovery_rate;
		if (this.shield_strength_current > this.shield_max) this.shield_strength_current=this.shield_max;
		
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			this.the_game.add(new PlayerBullet(this.the_game,
					(int) (this.px + this.width / 2)+11, (int) this.py));
			this.the_game.add(new PlayerBullet(this.the_game,
					(int) (this.px + this.width / 2)-14, (int) this.py));
			this.time_last_shot = this.the_game.getTimeElapsed();
		}

	}
	
	public double gunCooled()
	{
		return Math.max(0d, 0 - this.the_game.getTimeElapsed() + this.time_last_shot + this.gun_cooling_rate);
	}
	
	public void coolGun()
	{
		this.time_last_shot = this.the_game.getTimeElapsed();
	}

	public void notifyCollision(Collidable other) {
		if (other instanceof EnemyBullet) {
			if (this.shield_strength_current > 0) 
			{
				this.shielded = 10;
				this.shield_strength_current += ((EnemyBullet) other).getShield_inc();
				if (this.shield_strength_current<0)
				{
					this.hull_strength_current+=this.shield_strength_current;
					this.shield_strength_current =0;
					this.damaged = 10;
				}
			}
			else
			{
				this.hull_strength_current += ((EnemyBullet) other).getShield_inc();
				this.damaged = 10;
			}
			
		}
		if (other instanceof EnemyShip && ((EnemyShip)other).belowCover==false) 
		{
			
			if (this.shield_strength_current > 0) 
			{
				this.shielded = 10;
				this.shield_strength_current -= ((EnemyShip)other).getHull();
				if (this.shield_strength_current<0)
				{
					this.hull_strength_current+=this.shield_strength_current;
					this.shield_strength_current =0;
					this.damaged = 10;
				}
			}
			else
			{
				this.hull_strength_current -= ((EnemyShip)other).getHull();
				this.damaged = 10;
			}
			
			the_game.notifyDeath((EnemyShip)other);
		}
	
		
		if (other instanceof PowerUp) 
		{
			switch (((PowerUp)other).ID)
			{
			case 1:
				this.hull_strength_current = this.hull_max;
				break;
			case 2:
				this.gun_cooling_rate = gun_cooling_fast;
				break;
			case 3:
				this.shield_recovery_rate = shield_recovery_fast;
				break;
			}
			the_game.notifyDeath((PowerUp)other);
		}
	}

}
