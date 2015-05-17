import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel{
	private BufferedImage image;
	private Graphics buffer;
	private Atom player;
	private HashSet<Subatomic> particles;
	
	public Screen(){
		setSize(800, 600);
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		
		player = new Atom();
		particles = new HashSet<Subatomic>();
		Timer timer = new Timer(10, new Listener());
		timer.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	class Key extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
					
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				
			}
		}
	}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			buffer.setColor(Color.WHITE);
			buffer.fillRect(0, 0, getWidth(), getHeight());
			player.draw(buffer);
			repaint();
		}
	}
}
