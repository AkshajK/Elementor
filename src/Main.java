import javax.swing.JFrame;

public class Main {
	public static JFrame frame;

	public static void main(String[] args){
		restart();
	}
	
	public static void restart(){
		if(frame != null) frame.setVisible(false);
		frame = new JFrame("Elementor");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Screen());
		frame.setVisible(true);
	}
}
