import java.awt.Color;
import java.awt.Graphics;


public abstract class Subatomic {
	private Color color;
	private int x, y, radius;
	
	public Subatomic(Color color, int radius){
		this.color = color;
		this.radius = radius;
	}
	
	public Subatomic(Color color, int x, int y, int radius){
		this(color, radius);
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	public boolean intersect(Subatomic s) {
		return Math.sqrt(Math.pow(x-s.getX(), 2) + Math.pow(y-s.getY(),  2)) < radius;
	}
}
