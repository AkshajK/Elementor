import java.awt.Color;
import java.awt.Graphics;


public abstract class Subatomic {
	private Color color;
	private int x, y, radius;
	
	public Subatomic(Color color, int radius){
		this.color = color;
		this.radius = radius;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x, y, radius, radius);
	}
}
