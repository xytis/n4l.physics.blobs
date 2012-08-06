/**
 * 
 */
package n4l.physics.blobs;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import n4l.physics.blobs.container.Blob;
import n4l.physics.blobs.container.BlobContainer;

import n4l.physics.utils.Vector3;


/**
 * @author xytis
 *
 */
public class MainWindow  extends Canvas  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private MainThread thread;

	/**
	 * 
	 */
	private BufferStrategy strategy;

	private String AvgFps;
	
	private BlobContainer blobs = new BlobContainer();

	/**
	 * 
	 */
	public MainWindow() {

		AvgFps = new String();

		JFrame container = new JFrame("Arena");

		JPanel panel = (JPanel) container.getContentPane();

		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		setBounds(0, 0, 800, 600);
		panel.add(this);

		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Let this class call it's own repaints.
		setIgnoreRepaint(true);

		// Register key listener, for user input.
		//addKeyListener(new KeyInputHandler());

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		//Map map = new Map(this);

		//DrawableRegistry.getInstance().registerContext(map);

		//map.show();

		thread = new MainThread(this);
		thread.setRunning(true);
		thread.start();
		
		Blob blob[] = new Blob[4];
		
		blob[0] = new Blob(new Vector3(300.0,300.0,0), new Vector3(0,0,0));
		blobs.addItem(blob[0]);

		blob[1] = new Blob(new Vector3(300.0,280.0,0), new Vector3(0,0,0));
		blobs.addItem(blob[1]);

		
		blob[2] = new Blob(new Vector3(280.0,280.0,0), new Vector3(0,0,0));
		blobs.addItem(blob[2]);

		blob[3] = new Blob(new Vector3(280.0,300.0,0), new Vector3(0,0,0));
		blobs.addItem(blob[3]);
		
		blob[0].linkTo(blob[1]);		
		blob[1].linkTo(blob[2]);
		blob[2].linkTo(blob[3]);
		blob[3].linkTo(blob[0]);
		

		//Blob old_blob = new Blob(new Vector3(10,420,0));
		//Create blob walls
		//Top & bottom
		/*
		for (int i = 0; i < 40; i++) {
			blob = new Blob(new Vector3(20 + i*11,420,0));
			old_blob.linkTo(blob);
			blobs.addItem(blob);
			old_blob = blob;
		}
		*/
		/*
		for (int i = 0; i < 40; i++) {
			blob = new Blob(new Vector3(20 + i*10,20 + 400,0));
			blobs.addItem(blob);
		}
		for (int i = 1; i < 39; i++) {
			blob = new Blob(new Vector3(20 ,20 + i*10,0));
			blobs.addItem(blob);
		}
		for (int i = 1; i < 39; i++) {
			blob = new Blob(new Vector3(20 + 400 ,20 + i*10,0));
			blobs.addItem(blob);
		}
		*/
	}

	/**
	 * 
	 */
	public void update(double dt) {
		blobs.update(dt);
	}

	/**
	 * 
	 */
	public void render() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		// Blank this buffer out
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		g.drawString(AvgFps,
				(getWidth() - g.getFontMetrics().stringWidth(AvgFps)),
				(getHeight()));
		
		
		blobs.render(g);

		g.dispose();
		strategy.show();
	}

	public void setAvgFps(double average) {
		DecimalFormat df = new DecimalFormat("0.##");
		this.AvgFps = "FPS: " + df.format(average);
	}
}
