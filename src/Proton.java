import java.awt.Color;


public class Proton extends Subatomic{
	public Proton(){
		super(Color.RED, 50);
	}
	public Proton(int x, int y){
		super(Color.RED, x, y, 20);
	}
}
