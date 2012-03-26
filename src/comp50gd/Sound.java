/**
 * Code is the same as was used in Zapped!
 * 
 * The code handles sound adequately and we felt that re-implementing it would be unnecessary as this represents a complete set of operations one would wish to do on a sound object.
 * 
 */

package comp50gd;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable {
	private final int bufSize = 16384;
	private AudioFormat format;
	private AudioInputStream audioInputStream;
	private SourceDataLine line;
	private Thread thread;
	private URL soundURL;

	public Sound(String fName) {
		soundURL = this.getClass().getClassLoader().getResource(fName);
	}

	/**
	 * starts the playing of the song.
	 */
	public void start() {
		thread = new Thread(this);
		thread.setName("Playback");
		thread.start();
	}

	/**
	 * stops the playing of the song.
	 */
	public void stop() {
		thread = null;
	}

	private void shutDown(String message) {
		if (thread != null) {
			thread = null;
		}
	}

	/**
	 * fulfills the requirement of the Runnable interface.
	 */
	public void run() {
		// Make sure we have something to play
		if (soundURL != null) {
			// Set an AudioInputStream of the desired format for playback
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundURL);
				format = audioInputStream.getFormat();
			} catch (UnsupportedAudioFileException e) {
				System.err.println("Error with creating audio input stream! " + e.toString());
			} catch (Exception e) {
				System.err.println("Error with creating audio input stream! " + e.toString());
			}
			AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, audioInputStream);
			try {
				if (playbackInputStream == null) {
					shutDown("Unable to convert stream of format "
							+ audioInputStream + " to format " + format);
					return;
				}

				// Define the required attributes for our line, and make sure a
				// compatible line is supported.
				DataLine.Info info = new DataLine.Info(SourceDataLine.class,
						format);
				if (!AudioSystem.isLineSupported(info)) {
					shutDown("Line matching " + info + " not supported.");
					return;
				}

				// Get and open the source data line for playback.
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(format, bufSize);
				} catch (LineUnavailableException ex) {
					shutDown("Unable to open the line: " + ex);
					return;
				}

				// Play back the captured audio data
				int frameSizeInBytes = format.getFrameSize();
				int bufferLengthInFrames = line.getBufferSize() / 8;
				int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
				byte[] data = new byte[bufferLengthInBytes];
				int numBytesRead = 0;

				// Start the source data line
				line.start();
				while (thread != null) {
					try {
						if ((numBytesRead = playbackInputStream.read(data)) == -1) {
							break;
						}
						int numBytesRemaining = numBytesRead;
						while (numBytesRemaining > 0) {
							numBytesRemaining -= line.write(data, 0,
									numBytesRemaining);
						}
					} catch (Exception e) {
						shutDown("Error during playback: " + e);
						break;
					}
				}

				// We reached the end of the stream. let the data play out, then
				// stop and close the line.
				if (thread != null) {
					line.drain();
				}
				line.stop();
				line.close();
				line = null;
				shutDown(null);
			} catch (Exception e) {
				shutDown("Unable to create playback steam: " + e);
			}
		}
	}
}
