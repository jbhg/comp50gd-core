package comp50gd;


public abstract class BattleStuff extends GameSprite {
/**
 * Sets default settings for a BattleStuff object, with a shield impact of 1 and no other hits.
 * @param x
 * @param y
 * @param game
 */
	public BattleStuff(MainGame game, double x, double y) {
		super(game, x, y);
		destroy_on_contact = false;
		shield_inc = 0;
		hull_inc = 0;
		gun_cooling_inc = 0;
		score_inc = 0;
		lives_inc = 0;
		// TODO Auto-generated constructor stub
	}

	public BattleStuff(MainGame game, double x, double y, String ref) {
		super(game, x, y, ref);
		destroy_on_contact = false;
		shield_inc = 0;
		hull_inc = 0;
		gun_cooling_inc = 0;
		score_inc = 0;
		lives_inc = 0;
		// TODO Auto-generated constructor stub
	}
	
	protected boolean destroy_on_contact;

	public boolean isDestroy_on_contact() {
		return destroy_on_contact;
	}

	public double getShield_inc() {
		return shield_inc;
	}

	public double getHull_inc() {
		return hull_inc;
	}

	public double getGun_cooling_inc() {
		return gun_cooling_inc;
	}

	public int getScore_inc() {
		return score_inc;
	}

	public int getLives_inc() {
		return lives_inc;
	}

	public void setDestroy_on_contact(boolean destroyOnContact) {
		destroy_on_contact = destroyOnContact;
	}

	protected double shield_inc, hull_inc, gun_cooling_inc;
	protected int score_inc, lives_inc;
	
	public int getID(){return -1;}

}
