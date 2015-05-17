import java.awt.Color;
import java.awt.Graphics;


public class Atom {
	private Color color;
	private double x, y, radius;
	private double dx, dy, ax, ay;
	private final double MAX = 20;
	public double getdX() {
		return dx;
	}

	public void setdX(double dx) {
		this.dx = dx;
		if(dx > MAX) dx = MAX;
		if(dx < -1*MAX) dx = MAX;
	}

	public double getdY() {
		return dy;
	}

	public void setdY(double dy) {
		this.dy = dy;
		if(dy > MAX) dy = MAX;
		if(dy < -1*MAX) dy = MAX;
	}
	
	public double getRadius() {
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
		buffer.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
	}
	
	public void update(){
		x += dx;
		y += dy;
	}
	
	public boolean intersect(Subatomic particle){
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius;
	}
}
