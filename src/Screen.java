import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel{
	private BufferedImage image;
	private Graphics buffer;
	private Atom player;
	private JLabel name, chemical, positive, negative, charge, TEMP;
	private JPanel game, info;
	private final int WIDTH = 4000, HEIGHT = 3000;
	private Rectangle frame;
	private final double KEYACCELERATION = 1; //change in acceleration to move per millisecond
	private HashSet<Subatomic> protons, electrons;
	private int electronNum=1, protonNum=1;
	private String[] elements = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine", "Xenon", "Caesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium", "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium", "Roentgenium", "Copernicium", "Ununtrium", "Flerovium", "Ununpentium", "Livermorium", "Ununseptium", "Ununoctium"};
	private String[] elems = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo"};

	
	public Screen(){
		setSize(800, 600);
		setLayout(new BorderLayout());
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		frame = new Rectangle((WIDTH-getWidth())/2, (HEIGHT-getHeight())/2, getWidth(), getHeight());
		
		game = new JPanel();
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
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	class Key extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				player.setdX(player.getdX() - KEYACCELERATION);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.setdX(player.getdX() + KEYACCELERATION);
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				player.setdY(player.getdY() - KEYACCELERATION);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player.setdY(player.getdY() + KEYACCELERATION);
			}
		}
	}
	
	class Listener implements ActionListener{
		public final double PROBABILITY = 0.005;
		public void actionPerformed(ActionEvent e){
			if(Math.random() < 0.005){
				if(Math.random() < 0.5){
					electrons.add(new Electron((int)(Math.random()*760) + 20 + frame.x, (int)(Math.random()*560) + 20 + frame.y));
				}
				else{
					protons.add(new Proton((int)(Math.random()*760) + 20 + frame.x, (int)(Math.random()*560) + 20 + frame.y));
				}
			}
			
			buffer.setColor(Color.WHITE);
			buffer.fillRect(0, 0, getWidth(), getHeight());
			player.update();
			player.draw(buffer, frame.x, frame.y);
			
			frame.setBounds(player.getX() - getWidth()/2, player.getY() - getHeight()/2, getWidth(), getHeight());
			
			HashSet<Subatomic> copy = new HashSet<Subatomic>(electrons);
			for(Subatomic electron : copy){
				if(frame.contains(electron.getX(), electron.getY())){
					if(player.intersect(electron)) {
						player.addSubatomic(electron);
						electronNum++;
						electrons.remove(electron);
					}
					else electron.draw(buffer, frame.x, frame.y);
				}
			}
			copy = new HashSet<Subatomic>(protons);
			for(Subatomic proton: copy){
				if(frame.contains(proton.getX(), proton.getY())){
					if(player.intersect(proton)) {
						player.addSubatomic(proton);
						protonNum++;
						protons.remove(proton);
					}
					else proton.draw(buffer, frame.x, frame.y);
				}
			}
			
			name.setText(elements[protonNum-1]);
			chemical.setText(elems[protonNum-1]);
			positive.setText("Protons: " + protonNum);
			negative.setText("Electrons: " + electronNum);
			charge.setText("Net charge: " + (protonNum - electronNum));
			TEMP.setText("x: " + player.getX() + " y: " + player.getY());
			
			repaint();
		}
	}
	
	class Checker implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//System.out.println(frame.x + " " + frame.y + " " + player.getX() + " " + player.getY());
			if(Math.abs(protonNum - electronNum) > 2)
				lose();
		}
		
		public void lose(){
			
		}
	}
}
