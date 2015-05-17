import java.awt.Color;


public class Electron extends Subatomic{
	public Electron(){
		super(Color.RED, 50);
	}
	public Electron(int x, int y){
		super(Color.RED, x, y, 15);
	}
}
