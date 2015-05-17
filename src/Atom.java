import java.awt.Color;
import java.awt.Graphics;

public class Atom {
	private Color color;
	private double dx, dy, ax, ay;
	private final double MAX = 3;
	private int x, y, radius;
	private int protonNum=1, electronNum=1;
	
	public Atom(){
		color = Color.BLACK;
		x = 0; y = 0;
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

	public void draw(Graphics buffer, int width, int height){
		buffer.setColor(color);
		buffer.fillOval(width/2-radius, height/2-radius, 2*radius, 2*radius);
	//	drawProtons(width/2, height/2);
	//	drawElectrons(width/2, height/2);
	}
	
	public void update(){
		x = x+(int)dx;
		y = y+(int)dy;
	}
	
	public boolean intersect(Subatomic particle){
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius + particle.getRadius();
	}
}
