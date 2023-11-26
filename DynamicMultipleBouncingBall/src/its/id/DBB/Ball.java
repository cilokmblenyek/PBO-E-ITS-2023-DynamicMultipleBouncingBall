package its.id.DBB;

    // Import Java AWT library
    import java.awt.*;
    import java.util.List;

    public class Ball {
        // Define class attributes
        float x, y;
        float speedX, speedY;
        float radius;
        private Color color;
        private String huruf;

        // Constructor to initialize Ball object
        public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color, String text) {
	            this.x = x;
	            this.y = y;
	            this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
	            this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
	            this.radius = radius;
	            this.color = color;
	            this.huruf = text;
	        }
	
	        // Method to draw the ball
	        public void draw(Graphics g) {
	            g.setColor(color);
	            g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
	            
	            // Adjust the position to center the text inside the ball
	            FontMetrics fontMetrics = g.getFontMetrics();
	            int textX = (int) (x - fontMetrics.stringWidth(huruf) / 2);
	            int textY = (int) (y + fontMetrics.getAscent() / 2);

	            g.setColor(Color.WHITE);
	            g.drawString(huruf, textX, textY);
	        }
	        
	        public void checkCollision(List<Ball> balls) {
	            for (Ball otherBall : balls) {
	                if (otherBall != this && collideball(otherBall)) {
	                    // Handle collision logic here
	                    // For simplicity, let's just reverse the direction
	                    speedX = -speedX;
	                    speedY = -speedY;
	                }
	            }
	        }

	        private boolean collideball(Ball otherBall) {
	            double dx = x - otherBall.x;
	            double dy = y - otherBall.y;
	            double distance = Math.sqrt(dx * dx + dy * dy);
	            return distance < radius + otherBall.radius;
	        }

	
	        // Method to handle ball collisions with boundaries
	        public void collide(BallArea box){
	            float ballMinX = box.minX + radius;
	            float ballMinY = box.minY + radius;
	            float ballMaxX = box.maxX - radius;
	            float ballMaxY = box.maxY - radius;
	
	            // Update ball position based on current speed
	            x += speedX;
	            y += speedY;
	
	            if (x < ballMinX) {
	            	speedX = -speedX;
	            	x = ballMinX;
	            	} else if (x > ballMaxX) {
	            	speedX = -speedX;
	            	x = ballMaxX;
	            	}
	            	if (y < ballMinY) {
	            	speedY = -speedY;
	            	y = ballMinY;
	            	} else if (y > ballMaxY) {
	            	speedY = -speedY;
	            	y = ballMaxY;
	            	}
	        }
}
