package its.id.DBB;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Dynamic Multiple Bouncing Ball");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BallPanel(640, 480, 3));
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
