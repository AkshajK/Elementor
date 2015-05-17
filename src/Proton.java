import java.awt.Color;


public class Proton extends Subatomic{
	public Proton(){
		super(Color.GREEN, 10);
	}
	public Proton(int x, int y){
		super(Color.GREEN, x, y, 10);
	}
	public Proton(int x, int y, int radius) {
		super(Color.GREEN, x, y, radius);
	}
}
