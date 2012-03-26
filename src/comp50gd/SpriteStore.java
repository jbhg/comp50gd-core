package comp50gd;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Singleton class code based on code in Zapped!
 * 
 * Of note: since this class is a Singleton, it should not be able to be
 * constructed by an external class; for this reason, a constructor MUST be
 * created so that the default, public constructor is not.
 * 
 * @author joelgreenberg
 * 
 */
public final class SpriteStore {

	/**
	 * The static instance of this class.
	 */
	private static SpriteStore single = new SpriteStore();

	/**
	 * The storage of the sprites, implemented as a map from a string (URL) to a
	 * Sprite object.
	 */
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

	/**
	 * If we do not write a constructor, a default constructor will be created.
	 * Thus, a private constructor will be created.
	 */
	private SpriteStore() {
	}

	/**
	 * Returns the instance of the singleton class.
	 * 
	 * @return the singleton SpriteStore object.
	 */
	public static SpriteStore getSpriteStore() {
		return single;
	}

	public boolean pushSprite(String str, Sprite spr) {
		return sprites.put(str, spr) != null;
	}

	public Sprite getSprite(String ref) {
		/*
		 * If we've already got the sprite in the cache then just return the
		 * existing version.
		 */
		if (sprites.get(ref) != null) {
			return sprites.get(ref);
		}
		/*
		 * Otherwise, go away and grab the sprite from the resource loader.
		 */
		BufferedImage sourceImage = null;

		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.
			URL url = this.getClass().getClassLoader().getResource(ref);

			if (url == null) {
				System.err.println("Can't find ref: " + ref);
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("Failed to load: " + ref);
		} catch (IllegalArgumentException ex) {
			System.err.println("IllegalArgument: " + ref);
		}

		Image image;
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		try {
			image = gc.createCompatibleImage(sourceImage.getWidth(),
					sourceImage.getHeight(), Transparency.BITMASK);
			// draw our source image into the accelerated image
			image.getGraphics().drawImage(sourceImage, 0, 0, null);

			// create a sprite, add it the cache then return it
			Sprite sprite = new Sprite(image);
			sprites.put(ref, sprite);

			return sprite;
		} catch (NullPointerException e) {
			return null;
		}

	}

	public boolean deleteSprite(String str) {
		return false;
	}

}
