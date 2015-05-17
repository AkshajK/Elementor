import java.awt.Color;
import java.awt.Graphics;


public class Atom {
	private Color color;
	private int x, y, radius;
	public Atom(){
		color = Color.BLACK;
		x = 400; y = 300;
		radius = 50;
	}
	
	public Atom(Color color, int radius){
		this.color = color;
		this.radius = radius;
	}
	
	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x, y, radius, radius);
	}
}
