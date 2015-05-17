import java.awt.Color;
import java.awt.Graphics;


public abstract class Subatomic {
	private Color color;
	private int x, y, radius;
	
	public Subatomic(int r, int g, int b, int radius){
		this(400, 300, r, g, b, radius);
	}
	
	public Subatomic(Color color, int radius){
		this.color = color;
		this.radius = radius;
	}
	
	public Subatomic(int x, int y, int r, int g, int b, int radius){
		color = new Color(r, g, b);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void draw(Graphics buffer){
		drawCircle(buffer);
	}
	
	private void drawCircle(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x, y, radius, radius);
	}
}
