package comp50gd;

public abstract class Ship extends GameSprite {

	public static int Ship_ID = 0;
	protected boolean belowCover;

	private int id;
	
	/**
	 * TO BE DEPRECATED !!!!!
	 */
	protected double time_last_shot;
	
	/**
	 * The current strength of the hull; if depleted, object dies.
	 */
	protected double hull_strength_current;

	/**
	 * The current strength of the shield; if depleted, the damage goes to the
	 * hull.
	 */
	protected double shield_strength_current;

	/**
	 * The recovery rate of the hull; if zero, no recovery.
	 */
	protected double hull_recovery_rate;

	/**
	 * The recovery rate of the shield; if zero, no recovery.
	 */
	protected double shield_recovery_rate;

	/**
	 * The maximum value for the hull; recovery cannot go above this value.
	 */
	protected double hull_max;

	/**
	 * The maximum value for the shield; recovery cannot go above this value.
	 */
	protected double shield_max;

	/**
	 * The rate of recovery of the ship's gun; if zero, no recovery. If
	 * non-zero, subtracted from the gun_shooting_current variable to simulate
	 * cooling.
	 */
	protected double gun_cooling_rate;

	/**
	 * The current shooting status of the gun. If zero, a shot may be fired.
	 */
	protected double gun_shooting_current;

	public Ship(MainGame game, double x, double y) {
		super(game, x, y);
		hull_strength_current = 0;
		shield_strength_current = 0;
		hull_recovery_rate = 0;
		shield_recovery_rate = 0;
		gun_cooling_rate = 0;
		time_last_shot = 0;
		Ship.Ship_ID++;
		this.id = Ship.Ship_ID;
	}
	
	public Ship(MainGame game, double x, double y, String ref) {
		super(game, x, y, ref);

	}

	public Ship(MainGame game, double x, double y, String ref1, String ref2) {
		super(game, x, y, ref1, ref2);

	}
	
	public Ship(MainGame game, double x, double y, String ref1, String ref2, String ref3) {
		super(game, x, y, ref1, ref2, ref3);

	}
	
	public abstract void shoot();

	public int getID() {
		return this.id;
	}

	public double getCurrentShield() {
		return this.shield_strength_current;
	}

	public double getShieldRecoveryRate() {
		return this.shield_recovery_rate;
	}

	public double getShieldMax() {
		return this.shield_max;
	}

	public double getCurrentHull() {
		return this.hull_strength_current;
	}

	public double getHullRecoveryRate() {
		return this.hull_recovery_rate;
	}

	public double getHullMax() {
		return this.hull_max;
	}

	public double getGunCoolingRate() {
		return this.gun_cooling_rate;
	}

	public double getGunCurrent() {
		return this.gun_shooting_current;
	}
	

}
