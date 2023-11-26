package its.id.DBB;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class BallPanel extends JPanel implements KeyListener {
	private static final int REFRESH_RATE = 165;
	private Ball ball;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
    private static int NUMBER_OF_BALLS = 0;
    private List<Ball> balls;
	
	public BallPanel(final int width, final int height, int balls) {
		BallPanel.NUMBER_OF_BALLS = balls;
		this.areaWidth = width; this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		Random rand = new Random();
		this.balls = new ArrayList<>();
		
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		//untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
		@Override
		public void componentResized(ComponentEvent e) {
			Component c = (Component)e.getSource();
			Dimension dim = c.getSize();
			areaWidth = dim.width;
			areaHeight = dim.height;
			box.set(0, 0, width, height);
			}
		});
		startThread();
		
		addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
	}
	
    private Ball createBall(char keyChar) {
    	Random rand = new Random();
        int radius = 50;
        int x = rand.nextInt(areaWidth - radius * 2 - 20) + radius + 10;
        int y = rand.nextInt(areaHeight - radius * 2 - 20) + radius + 10;
        int speed = 1;
        int angleInDegree = rand.nextInt(360);
        Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        String text = String.valueOf(keyChar);
        return new Ball(x, y, radius, speed, angleInDegree, color, text);
    }
	
	@Override
    public void keyPressed(KeyEvent e) {
		char keyChar = e.getKeyChar();
        //char keyChar = Character.toUpperCase(e.getKeyChar());
        if ((keyChar >= 'a' && keyChar <= 'z') || (keyChar >= '0' && keyChar <= '9') || (keyChar >= 'A' && keyChar <= 'Z')) {
            // Create a new ball with the pressed character and add it to the list
            Ball newBall = createBall(keyChar);
            balls.add(newBall);
        }
	}
      
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					for (Ball ball : balls) {
	                    ball.collide(box);
	                    ball.checkCollision(balls);
					}
					repaint();
					try {
					Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {}
				}
				}
			};
			gameThread.start();
		}
	
					@Override
					public void paintComponent(Graphics g) {
					super.paintComponent(g);
					box.draw(g);
					for (Ball ball : balls) {
			            ball.draw(g);
			        }
		}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
}

