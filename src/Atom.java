import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Atom {
	private Color color;
	private double dx, dy, ax, ay;
	private final double MAX = 20;
	private int x, y, radius;
	private ArrayList<Subatomic> surrounding;
	
	public Atom(){
		color = Color.BLACK;
		x = 400; y = 300;
		dx = 0; dy = 0;
		radius = 25;
		surrounding = new ArrayList<>();
	}
	
	public void addSubatomic(Subatomic s) {
		surrounding.add(s);
	}
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

	public void draw(Graphics buffer){
		buffer.setColor(color);
		buffer.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
		for(Subatomic s : surrounding) {
			buffer.setColor(s.getColor());
			buffer.fillOval(s.getX()-s.getRadius(), s.getY()-s.getRadius(), 2*s.getRadius(), 2*s.getRadius());
		}
	}
	
	public void update(){
		x = x+(int)dx;
		y = y+(int)dy;
		for(Subatomic s : surrounding) {
			s.setX(s.getX()+(int)dx);
			s.setY(s.getY()+(int)dy);
		}
	}
	
	public boolean intersect(Subatomic particle){
		for(Subatomic s : surrounding) {
			if(s.intersect(particle))
				return true;
		}
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius;
	}
}
