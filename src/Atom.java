import java.awt.Color;
import java.awt.Graphics;


public class Atom {
	private Color color;
	private int x, y, radius;
	private int dx, dy, ax, ay;
	public int getaX() {
		return ax;
	}

	public void setaX(int ax) {
		this.ax = ax;
	}

	public int getaY() {
		return ay;
	}

	public void setaY(int ay) {
		this.ay = ay;
	}
	
	public int getRadius() {
		return radius;
	}

	public Atom(){
		color = Color.BLACK;
		x = 400; y = 300;
		dx = 0; dy = 0;
		radius = 25;
	}

	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	public void update(){
		dx += ax;
		dy += ay;
		x += dx;
		y += dy;
	}
	
	public boolean intersect(Subatomic particle){
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius;
	}
}
