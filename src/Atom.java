import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Atom {
	private Color color;
	private double dx, dy, ax, ay;
	private final double MAX = 20;
	private int x, y, radius;
	
	public Atom(){
		color = Color.BLACK;
		x = 2000; y = 1500;
		dx = 0; dy = 0;
		radius = 25;
	}
	
	public int getX(){
		return x;
	}
	
	public double getdX() {
		return dx;
	}

	public void setdX(double dx) {
		this.dx = dx;
		if(dx > MAX) dx = MAX;
		if(dx < -1*MAX) dx = MAX;
	}
	
	public int getY(){
		return y;
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

	public void draw(Graphics buffer, int x, int y){
		buffer.setColor(color);
		buffer.fillOval(375, 275, (int)(2*radius), (int)(2*radius));
	}
	
	public void update(){
		x = x+(int)dx;
		y = y+(int)dy;
	}
	
	public boolean intersect(Subatomic particle){
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius + particle.getRadius();
	}
}
