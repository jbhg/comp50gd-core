package comp50gd;

import javax.swing.*;

/**
 * This class contains the 'main' method of the game, as well as maintains the global variables of the game.
 * 
 * The game is contingent on a boolean 'gameInProgress' being true.
 * 
 * @author Joel B. Greenberg <JoelBGreenberg@gmail.com>
 **/
public class MainGame {

	private JFrame window;
	private ScrollingGamePanelOnePlayer panel;

	public MainGame() {
		window = new JFrame(Settings.TITLEBAR);
		panel = new ScrollingGamePanelOnePlayer();
		window.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT+34);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(panel);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		MainGame game = new MainGame();
		Settings.DEBUG = false;
		game.go();
	}
	
	public void go()
	{
	    panel.startGame(this);
	}
	
	public void add(BattleStuff bs)
	{
		panel.add(bs);
	}
	public void notifyDeath(BattleStuff bs)
	{
		panel.notifyDeath(bs);
	}
	public void notifyDeath(EnemyShip es)
	{
		panel.notifyDeath(es);
	}
	public double getTimeElapsed()
	{
		return panel.getTimeElapsed();
	}
	public void setTitle(String title)
	{
		window.setTitle(title);
	}
	public ScrollingGamePanelOnePlayer panel()
	{
		return panel;
	}

}
