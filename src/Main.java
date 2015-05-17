import javax.swing.JFrame;

public class Main {
	public static JFrame frame = new JFrame("Elementor");

	public static void main(String[] args){
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Screen());
		frame.setVisible(true);
	}
	
	public static void restart(){
		frame.setContentPane(new Screen());
	}
}
