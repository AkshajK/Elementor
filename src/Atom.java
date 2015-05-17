import java.awt.Color;
import java.awt.Graphics;


public class Atom {
	private Color color;
	private int x, y, radius;
	private int dx, dy;
	public Atom(){
		color = Color.BLACK;
		x = 400; y = 300;
		radius = 50;
	}

	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x, y, radius, radius);
	}
	
	public void update(){
		x += dx;
		y += dy;
	}
}
