package comp50gd;

public abstract class EnemyShip extends Ship {

	protected int score_value;
	protected int power_up_ID;
	public int hull;


	public int getHull()
	{
		return this.hull;
	}
	
	public EnemyShip(MainGame game, double x, double y, String img) {
		super(game, x, y, img);
		score_value = 10;
		belowCover = false;
		hull = 30;
		power_up_ID = 1;
	}

	@Override
	protected void die() {
	}

	@Override
	public final void notifyCollision(Collidable other) {
		if (other instanceof PlayerBullet) {
			this.the_game.panel().modifyScore(score_value);
			this.the_game.panel().incrementKills();
			this.the_game.notifyDeath(this);
			this.the_game.add(new Explosion(this.the_game,
					this.px+(this.width-50)/2, 
					this.py+(this.height-50)/2,
					50));
			
			dropPowerUp();
		}
	}
	
	protected void dropPowerUp()
	{
		// Drop power-up?
		if (Math.random()< Settings.POWER_UP_DROP_RATE)
		{
			switch(power_up_ID)
			{
			case 1: // Hull power-up
				this.the_game.add(new PowerUp(this.the_game,
				this.px+(this.width-32)/2, 
				this.py+(this.height-32)/2,
				power_up_ID,
				"comp50gd/img/PowerUpHull.gif"));
				break;
				
			case 2: // Rate of fire power-up
				this.the_game.add(new PowerUp(this.the_game,
				this.px+(this.width-32)/2, 
				this.py+(this.height-32)/2,
				power_up_ID,
				"comp50gd/img/PowerUpCooling.gif"));
				break;
				
			case 3: // Shield re-charge rate power-up
				this.the_game.add(new PowerUp(this.the_game,
				this.px+(this.width-32)/2, 
				this.py+(this.height-32)/2,
				power_up_ID,
				"comp50gd/img/PowerUpShield.gif"));
				break;
			}
		}
	}

}
