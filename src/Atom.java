import java.awt.Color;
import java.awt.Graphics;

public class Atom {
	private Color color;
	private double dx, dy, ax, ay;
	private final double MAX = 5;
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
	
	public double getaX() {
		return ax;
	}

	public void setaX(double ax) {
		if((this.dx < MAX || ax < this.ax) && (this.dx > -1*MAX || ax > this.ax)) this.ax = ax;
		
	}
	
	public int getY(){
		return y;
	}

	public double getaY() {
		return ay;
	}

	public void setaY(double ay) {
		if((this.dy < MAX || ay < this.ay) && (this.dy > -1*MAX || ay > this.ay)) this.ay = ay;
		
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
		dx += ax;
		dy += ay;
		if(dx > MAX) dx = MAX;
		if(dx < -1*MAX) dx = -1*MAX;
		if(dy > MAX) dy = MAX;
		if(dy < -1*MAX) dy = -1*MAX;
		x = x+(int)dx;
		y = y+(int)dy;
	}
	
	public boolean intersect(Subatomic particle){
		return Math.sqrt(Math.pow(x-particle.getX(), 2) + Math.pow(y-particle.getY(),  2)) < radius + particle.getRadius();
	}
}
