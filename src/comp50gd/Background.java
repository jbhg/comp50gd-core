package comp50gd;

public class Background {
	protected Sprite sprite;
	protected double x;
	protected double y;
	protected int height;
	protected double scrollSpeed;
	protected MainGame the_game;
	
	
	public Background(MainGame game, String ref, int height, double scrollSpeed) 
	{
		the_game = game;
		this.height = height;
		this.scrollSpeed = scrollSpeed;
		this.y = -height;
		this.sprite = SpriteStore.getSpriteStore().getSprite(ref);
	}
	
	public Sprite sprite()
	{
		return this.sprite;
	}
	
	public void move()
	{
		y+=this.scrollSpeed;
		if (y>=0) y=-(int)this.height;
	}
}
