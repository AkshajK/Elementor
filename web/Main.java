import javax.swing.JFrame;
import javax.swing.*;

public class Main extends JApplet {
   public void init() {
      try {
         SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
               
		JFrame frame = new JFrame("Elementor");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Screen());
		frame.setVisible(true);
      getContentPane().add(frame);
            }
          });
      } catch (Exception e) {
         e.printStackTrace(System.out);
      }
   }
}
