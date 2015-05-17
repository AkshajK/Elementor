import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Screen extends JPanel{
	private BufferedImage image;
	private Graphics buffer;
	private Atom player;
	private JLabel name, chemical, positive, negative, charge, score, winlose, time;
	private boolean up=false,down=false,left=false,right=false, playing;

	private JPanel game, info, top, particles, element;
	private double KEYACCELERATION = 1;

	private Rectangle frame;
	

	private HashSet<Subatomic> protons, electrons;
	private String[] elements = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine", "Xenon", "Caesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium", "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium", "Roentgenium", "Copernicium", "Ununtrium", "Flerovium", "Ununpentium", "Livermorium", "Ununseptium", "Ununoctium"};
	private String[] elems = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo"};

	
	public Screen(){
		playing = true;
		setSize(800, 600);
		setLayout(new BorderLayout());
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		frame = new Rectangle();
		info = new JPanel();
		element = new JPanel();
		particles = new JPanel();
		element.setLayout(new BorderLayout());
		
		player = new Atom();
		protons = new HashSet<Subatomic>();
		electrons = new HashSet<Subatomic>();
		
		Timer update = new Timer(10, new Listener());
		update.start();
		
		Timer timer = new Timer(1000, new Checker());
		timer.start();
		
		Timer keyPress = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if(left) player.setdX(player.getdX() - KEYACCELERATION);
				if(right) player.setdX(player.getdX() + KEYACCELERATION);
				if(up) player.setdY(player.getdY() - KEYACCELERATION);
				if(down) player.setdY(player.getdY() + KEYACCELERATION);
					
			}
		});
		keyPress.start();
		
		addKeyListener(new Key());
		setFocusable(true);
		name = new JLabel();
		chemical = new JLabel();
		positive = new JLabel();
		negative = new JLabel();
		charge = new JLabel();
		
		info.setLayout(new BorderLayout());
		element.add(chemical, BorderLayout.WEST);
		element.add(name, BorderLayout.EAST);
		particles.setLayout(new BorderLayout());
		particles.add(positive, BorderLayout.EAST);
		particles.add(negative, BorderLayout.WEST);
		info.add(charge, BorderLayout.CENTER);
		info.add(element, BorderLayout.WEST);
		info.add(particles, BorderLayout.EAST);
		add(info, BorderLayout.SOUTH);
		
		top = new JPanel();
		top.setLayout(new BorderLayout());
		score = new JLabel("0");
		top.add(score, BorderLayout.WEST);
		winlose = new JLabel();
		top.add(winlose, BorderLayout.CENTER);
		time = new JLabel("0.00");
		top.add(time, BorderLayout.EAST);
		add(top, BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	class Key extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(playing){
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					left=true;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
					right=true;
				if(e.getKeyCode() == KeyEvent.VK_UP) 
					up=true;
				if(e.getKeyCode() == KeyEvent.VK_DOWN) 
					down=true;
			}
			else{
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					Main.restart();
				}
			}
		}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				left=false;
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
				right=false;
			if(e.getKeyCode() == KeyEvent.VK_UP) 
				up=false;
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
				down=false;
		}
	}
	
	class Listener implements ActionListener{
		public final double PROBABILITY = 0.05;
		public void actionPerformed(ActionEvent e){
			if(playing){
				if(Math.random() < PROBABILITY){
					if(Math.random() < 0.5){
						electrons.add(new Electron((int)(Math.random()*3*getWidth()) - getWidth() + frame.x, (int)(Math.random()*3*getHeight()) - getHeight() + frame.y));
					}
					else{
						protons.add(new Proton((int)(Math.random()*3*getWidth()) - getWidth() + frame.x, (int)(Math.random()*3*getHeight()) - getHeight() + frame.y));
					}
				}
				
				buffer.setColor(Color.WHITE);
				buffer.fillRect(0, 0, getWidth(), getHeight());
				buffer.setColor(Color.GRAY);
				player.update();
				player.draw(buffer, getWidth(), getHeight());
				
				frame.setBounds(player.getX() - getWidth()/2, player.getY() - getHeight()/2, getWidth(), getHeight());
				
				HashSet<Subatomic> copy = new HashSet<Subatomic>(electrons);
				for(Subatomic electron : copy){
					if(frame.contains(electron.getX(), electron.getY())){
						if(player.intersect(electron)) {
							player.incrementElectron();
							electrons.remove(electron);
						}
						else electron.draw(buffer, frame.x, frame.y);
					}
				}
				copy = new HashSet<Subatomic>(protons);
				for(Subatomic proton: copy){
					if(frame.contains(proton.getX(), proton.getY())){
						if(player.intersect(proton)) {
							player.incrementProton();
							protons.remove(proton);
						}
						else proton.draw(buffer, frame.x, frame.y);
					}
				}
				if(player.getProtonNum() == 118){
					win();
				}
				else{
					name.setText(elements[player.getProtonNum()-1] + " (" + elems[player.getProtonNum() - 1] + ")");
					name.setFont(new Font("Sans Serif", Font.BOLD, 20));
				}
				//particles.setText("Protons: " + player.getProtonNum() + " Electrons: " + player.getElectronNum());
				if(player.getProtonNum() - player.getElectronNum() > 0){
					charge.setText("Net charge: " + "+" + (player.getProtonNum() - player.getElectronNum()));
				}
				else{
					charge.setText("Net charge: " + (player.getProtonNum() - player.getElectronNum()));
				}
				positive.setText("Protons: " + player.getProtonNum());
				positive.setFont(new Font("Sans Serif", Font.BOLD, 20));
				negative.setText("Electrons: " + player.getElectronNum() + "   ");
				negative.setFont(new Font("Sans Serif", Font.BOLD, 20));
				charge.setFont(new Font("Sans Serif", Font.BOLD, 30));
				charge.setHorizontalAlignment(SwingConstants.CENTER);

				
				DecimalFormat df = new DecimalFormat("#.##");
				time.setText(df.format((Double.parseDouble(time.getText()) + 0.01)));
				score.setText(player.getScore()+"");
				//winlose.setText("Net charge: " + (player.getProtonNum() - player.getElectronNum()));
				time.setFont(new Font("Sans Serif", Font.BOLD, 30));
	//			winlose.setFont(new Font("Sans Serif", Font.BOLD, 30));
	//			winlose.setHorizontalAlignment(SwingConstants.CENTER);
				score.setFont(new Font("Sans Serif", Font.BOLD, 30));
				repaint();
			}
			else{
				
			}
		}
		
		public void win(){
			winlose.add(new JLabel("You win!"));
			playing = false;
		}
	}
	
	class Checker implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(Math.abs(player.getProtonNum() - player.getElectronNum()) > 2)
				lose();
		}
		
		public void lose(){
			winlose.setText("Game over!");
			winlose.setHorizontalAlignment(SwingConstants.CENTER);
			top.add(winlose, BorderLayout.CENTER);
			playing = false;
		}
	}
}
