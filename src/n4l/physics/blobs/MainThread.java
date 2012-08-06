/**
 * 
 */
package n4l.physics.blobs;

import n4l.physics.utils.AverageCounter;

/**
 * @author xytis
 * 
 * @category Main application
 * 
 *           This class encapsulates the main thread, which controls the refresh
 *           rate, the update sequence, and stop sequence.
 * 
 */
public class MainThread extends Thread {

	private boolean running;

	// desired fps
	private final static int MAX_FPS = 50;
	// the frame period
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;

	private MainWindow mainWindow;

	/**
	 * 
	 */
	public MainThread(MainWindow window) {
		mainWindow = window;
	}

	/**
	 * 
	 */
	public boolean getRunning() {
		return this.running;
	}

	/**
	 * 
	 */
	public MainThread setRunning(boolean running) {
		this.running = running;
		return this;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		System.out.println("Starting game loop");

		AverageCounter fps = new AverageCounter();

		long beginTime; // the time when the cycle begun
		long timeDiff; // the time it took for the cycle to execute
		int sleepTime = 0; // ms to sleep (<0 if we're behind)
		
		double lastTime = System.currentTimeMillis()/1000.0;
		double deltaTime = 0;

		while (running) {
			beginTime = System.currentTimeMillis();
			deltaTime = beginTime/1000.0 - lastTime;
			lastTime = beginTime/1000.0;

			// update game state
			this.mainWindow.update(deltaTime);

			// render state to the screen
			// draws the canvas on the panel
			this.mainWindow.render();

			// calculate how long did the cycle take
			timeDiff = System.currentTimeMillis() - beginTime;

			// calculate sleep time
			sleepTime = (int) (FRAME_PERIOD - timeDiff);

			if (sleepTime > 0) {
				// if sleepTime > 0 we're OK to sleep a bit.s
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
			}

			fps.mark();
			this.mainWindow.setAvgFps(fps.getAverage());
		}
	}

}
