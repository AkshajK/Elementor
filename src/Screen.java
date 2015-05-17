import java.awt.BorderLayout;
import java.awt.Color;
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
	private JLabel name, chemical, positive, negative, charge, TEMP, score, winlose, time;

	private JPanel game, info, top;
	private double KEYACCELERATION = 2;
	//private int seconds, centiseconds;

	private Rectangle frame;
	

	private HashSet<Subatomic> protons, electrons;
	private String[] elements = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine", "Xenon", "Caesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium", "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium", "Roentgenium", "Copernicium", "Ununtrium", "Flerovium", "Ununpentium", "Livermorium", "Ununseptium", "Ununoctium"};
	private String[] elems = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo"};

	
	public Screen(){
		setSize(800, 600);
		setLayout(new BorderLayout());
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		frame = new Rectangle();
		info = new JPanel();
		
		
		player = new Atom();
		protons = new HashSet<Subatomic>();
		electrons = new HashSet<Subatomic>();
		
		Timer update = new Timer(10, new Listener());
		update.start();
		
		Timer timer = new Timer(1000, new Checker());
		timer.start();
		
		addKeyListener(new Key());
		setFocusable(true);
		//add(game, BorderLayout.CENTER);
		name = new JLabel();
		chemical = new JLabel();
		positive = new JLabel();
		negative = new JLabel();
		charge = new JLabel();
		TEMP = new JLabel();
		
		info.add(chemical);
		info.add(name);
		info.add(positive);
		info.add(negative);
		info.add(charge);
		info.add(TEMP);
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
			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
				player.setdX(player.getdX() - KEYACCELERATION);
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
				player.setdX(player.getdX() + KEYACCELERATION);
			if(e.getKeyCode() == KeyEvent.VK_UP) 
				player.setdY(player.getdY() - KEYACCELERATION);
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
				player.setdY(player.getdY() + KEYACCELERATION);
		}
	}
	
	class Listener implements ActionListener{
		public final double PROBABILITY = 0.05;
		public void actionPerformed(ActionEvent e){
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
			player.update();
			player.draw(buffer, getWidth(), getHeight());
			
			frame.setBounds(player.getX() - getWidth()/2, player.getY() - getHeight()/2, getWidth(), getHeight());
			
			HashSet<Subatomic> copy = new HashSet<Subatomic>(electrons);
			for(Subatomic electron : copy){
				if(frame.contains(electron.getX(), electron.getY())){
					if(player.intersect(electron)) {
						player.incrementElectron();
						electrons.remove(electron);
						System.out.println(electron.getX() + " " + electron.getY());
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
						System.out.println(proton.getX() + " " + proton.getY());
					}
					else proton.draw(buffer, frame.x, frame.y);
				}
			}
			if(player.getProtonNum() == 118){
				win();
			}
			else{
				name.setText(elements[player.getProtonNum()-1]);
				chemical.setText(elems[player.getProtonNum()-1]);
			}
			positive.setText("Protons: " + player.getProtonNum());
			negative.setText("Electrons: " + player.getElectronNum());
			charge.setText("Net charge: " + (player.getProtonNum() - player.getElectronNum()));
			TEMP.setText("x: " + player.getX() + " y: " + player.getY());
			
			DecimalFormat df = new DecimalFormat("#.##");
			time.setText(df.format((Double.parseDouble(time.getText()) + 0.01)));
			score.setText(player.getScore()+"");
			repaint();
		}
		
		public void win(){
			winlose.add(new JLabel("You win!"));
			add(winlose, BorderLayout.NORTH);
		}
	}
	
	class Checker implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//System.out.println(frame.x + " " + frame.y + " " + player.getX() + " " + player.getY());
			if(Math.abs(player.getProtonNum() - player.getElectronNum()) > 2)
				lose();
		}
		
		public void lose(){
			winlose.setText("Game over!");
			winlose.setHorizontalAlignment(SwingConstants.CENTER);
			top.add(winlose, BorderLayout.CENTER);
		}
	}
}
