package comp50gd;

public class ExplodingBullet extends EnemyBullet {

	public ExplodingBullet(MainGame game, int x, int y) {
		super(game, x, y, "comp50gd/img/bomb.GIF");
		this.dy = 2;
		this.width = 10;
		this.height = 10;
		this.shield_inc = -3;
		this.coll_shape = new RectEntity(this);

		this.shield_inc = -20;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void move() {
		py += dy;
		if(Math.random()>0.99)
		{
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					0,-1));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					2,0));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					3,2));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					2,4));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					0,5));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					-2,4));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					-3,2));
			this.the_game.add(new ExplodedBullet(
					this.the_game, 
					this.px, this.py,
					-2,0));
			this.the_game.notifyDeath(this);
		}
		else if(py > Settings.WINDOW_HEIGHT || py < 0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other) {
		if(other instanceof PlayerShip)
		{
			this.the_game.notifyDeath(this);
		}
	}
	
}
