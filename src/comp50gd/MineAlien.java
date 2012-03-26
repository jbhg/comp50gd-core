package comp50gd;

public class MineAlien extends EnemyShip {

	public MineAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/mine.GIF");
		dx = 0.3;
		dy = 1;
		belowCover = true;
		this.width = 30;
		this.height = 30;
		this.gun_cooling_rate = 400;
		this.shield_strength_current = 5;
		this.coll_shape = new RectEntity(this);
		this.score_value = 10;
	}

	@Override
	public void shoot() 
	{
		double i;
		if( Math.abs(the_game.panel().player_x()-px) < 100 &&
			Math.abs(the_game.panel().player_y()-py) < 100)
		{
			this.the_game.add(new Explosion(this.the_game,
					this.px+(this.width-80)/2, 
					this.py+(this.height-80)/2,
					80));
			for (i=0; i<Math.PI*2; i=i + Math.PI/6)
			{
				this.the_game.add(new ExplodedMine(
						this.the_game, 
						this.px, this.py,
						Math.sin(i)*2,Math.cos(i)*2+1));
				this.the_game.add(new ExplodedMine(
						this.the_game, 
						this.px, this.py,
						Math.sin(i+0.3)*3,Math.cos(i+0.3)*3+1));
			}
			
			/*this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					1,1));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					1.5,2));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					1,3));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					0,3.5));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					 this.px, this.py,
					-1,3));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					-1.5,2));
			this.the_game.add(new ExplodedMine(
					this.the_game, 
					this.px, this.py,
					-1,1));*/
			
			the_game.notifyDeath((EnemyShip)this);
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
		py += dy;
		
		if(py > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);
		
	}

}