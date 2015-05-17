import java.awt.Color;


public class Electron extends Subatomic{
	public Electron(){
		super(Color.RED, 10);
	}
	public Electron(int x, int y){
		super(Color.RED, x, y, 10);
	}
	public Electron(int x, int y, int radius) {
		super(Color.RED, x, y, radius);
	}
}
