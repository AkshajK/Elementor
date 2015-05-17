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
	private int keySpeed = 5; //Number of pixels to move per keypress
	private HashSet<Subatomic> protons, electrons;
	
	public Screen(){
		setSize(800, 600);
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		
		player = new Atom();
		protons = new HashSet<Subatomic>();
		electrons = new HashSet<Subatomic>();
		
		Timer update = new Timer(10, new Listener());
		update.start();
		
		Timer timer = new Timer(10000, new Checker());
		update.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	class Key extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				int tmp = player.getX()-keySpeed;
				if(tmp-player.getRadius()<0) tmp=0;
				player.setX(tmp);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				int tmp = player.getX()+keySpeed;
				if(tmp+player.getRadius()>=getWidth()) tmp=getWidth();
				player.setX(tmp);
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				int tmp = player.getY()-keySpeed;
				if(tmp-player.getRadius()<0) tmp=0;
				player.setY(tmp);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				int tmp = player.getY()+keySpeed;
				if(tmp+player.getRadius()>=getHeight()) tmp=getHeight();
				player.setY(tmp);
			}
		}
	}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(Math.random() < 0){
				if(Math.random() < 0){
					electrons.add(new Electron((int)(Math.random()*760) + 20, (int)(Math.random()*560) + 20));
				}
				else{
					protons.add(new Proton((int)(Math.random()*760) + 20, (int)(Math.random()*560) + 20));
				}
			}
			
			buffer.setColor(Color.WHITE);
			buffer.fillRect(0, 0, getWidth(), getHeight());
			player.draw(buffer);
			
			HashSet<Subatomic> copy = new HashSet<Subatomic>(electrons);
			for(Subatomic electron : copy){
				if(player.intersect(electron))
					electrons.remove(electron);
				else electron.draw(buffer);
			}
			copy = new HashSet<Subatomic>(protons);
			for(Subatomic proton: copy){
				if(player.intersect(proton))
					protons.remove(proton);
				else proton.draw(buffer);
			}
			
			repaint();
		}
	}
	
	class Checker implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
}
