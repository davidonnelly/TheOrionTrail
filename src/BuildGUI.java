/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextPane;


import java.awt.Color;
import java.awt.Font;

public class BuildGUI extends JFrame {
	private JPanel mapPane;
	private JPanel mainPane;
	private JPanel createPane;
	private JPanel storePane;
	private JPanel statusPane;
	private Ship theShip;
	
	private Encounter myEnco;
	private Instance myInst;
	private BuildGUI frame;
	private Boolean someoneAlive;
	
	/**
	 * returns the frame
	 * @return
	 */
	public BuildGUI getFrame(){
		return frame;
	}
	/**
	 * sets the current encounter
	 * @param en
	 */
	public void setEncounter(Encounter en){
		myEnco = en;
	}
	/**
	 * returns the ship
	 * @return
	 */
	public Ship getShip(){
		return theShip;
	}
	/**
	 * sets the ship
	 * @param newShip
	 */
	public void setShip(Ship newShip){
		theShip = newShip;
	}
	
	/**
	 * returns the instance
	 * @return
	 */
	public Instance getInstance(){
		return myInst;
	}
	
	/**
	 * builds the GUI
	 * @param en
	 */
	public BuildGUI(Encounter en, Ship aShip){
		myEnco = en;
		theShip = aShip;
		myInst = new Instance(this);
		
		setTitle("The Orion Trail");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		try {
			mainBuild("encounter/welcome.txt", "options/welcomeOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void restart(Encounter en, String fileName){
		myEnco = en;
		theShip = new Ship();
		myInst = new Instance(this);
		
		try {
			frame.mainBuild(fileName, "options/endOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays the window
	 */
	public void display(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new BuildGUI(myEnco, theShip);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Displays the main map interface
	 */
	public void mapBuild(){
		mapPane = new JPanel();
		mapPane.setForeground(Color.ORANGE);
		mapPane.setBackground(Color.DARK_GRAY);
		mapPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon space = new ImageIcon("Images/Maps/" + theShip.getCurrentMapName());
		final Image spImg = space.getImage();
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setForeground(new Color(255, 165, 0));
		btnNewButton_1.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setOpaque(true);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);
		
		JButton btnTravelHere = new JButton("STATUS");
		btnTravelHere.setForeground(new Color(255, 165, 0));
		btnTravelHere.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnTravelHere.setContentAreaFilled(false);
        btnTravelHere.setOpaque(true);
        btnTravelHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBuild();
			}
		});
		
		JButton btnTravel = new JButton("TRAVEL");
		btnTravel.setForeground(new Color(255, 165, 0));
		btnTravel.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnTravel.setContentAreaFilled(false);
        btnTravel.setOpaque(true);
        final BuildGUI thisGUI = this;
        btnTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(theShip.getCurrentStar() == Universe.betelgeuse){
					theShip.getGame().win();
				}
				else if(theShip.getCargo().getEnergyCells()<=0){
					theShip.getGame().lose();
				}
				else{
					if(theShip.isAtStar()){
						setEncounter(new EncounterStar(thisGUI));
					}
					else{
						myInst.randomEncounter();
					}
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(mapPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnTravelHere))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnTravel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnTravelHere, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
					.addComponent(btnTravel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
		);
		btnNewButton.setIcon(space);
		
		btnNewButton.addComponentListener(new ComponentAdapter() {
            @Override
            //supposedly scales the image with the button, though not perfectly
            public void componentResized(ComponentEvent e) {
                JButton button1 = (JButton) e.getComponent();
                Dimension size = button1.getSize();
                Insets insets = button1.getInsets();
                size.width -= insets.left + insets.right;
                size.height -= insets.top + insets.bottom;
                if (size.width > size.height) {
                    size.width = -1;
                } else {
                    size.height = -1;
                }
                Image scaled = spImg.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
                button1.setIcon(new ImageIcon(scaled));
            }

        });
		mapPane.setLayout(gl_contentPane);
		
		//setting this to display
		setContentPane(mapPane);
		revalidate();
	}

	
	
	/**
	 * Displays the main encounter window
	 * @param enTextFile This string of a text file is displayed in the main section
	 * @param opTextFile This string of a text file is displayed as the two options
	 * @throws FileNotFoundException
	 */
	public void mainBuild(String enTextFile, String opTextFile) throws FileNotFoundException{
		mainPane = new JPanel();
		mainPane.setForeground(new Color(255, 165, 0));
		mainPane.setBackground(Color.DARK_GRAY);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		//SCANNING IN THE TEXT FILE AND PUTTING IT IN THE THING
		Scanner fileEn = new Scanner(new FileInputStream(enTextFile));
		Scanner fileOp = new Scanner(new FileInputStream(opTextFile));
		
		String textSubstance = fileEn.nextLine();
		while (fileEn.hasNextLine()){
			textSubstance = textSubstance + "\n" + fileEn.nextLine();
		}
		
		String option1Txt = fileOp.nextLine();
		String option2Txt = fileOp.nextLine();
		
		JButton button1 = new JButton(option1Txt);
		button1.setFont(new Font("SimSun", Font.PLAIN, 20));
		button1.setForeground(new Color(255, 165, 0));
		button1.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		//next two lines makes button clear
		button1.setContentAreaFilled(false);
        button1.setOpaque(true);
        
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//WHEN YOU PRESS LEFT BUTTON
				myEnco.option1();
			}
		});
		
		JButton btnNewButton = new JButton(option2Txt);
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WHEN YOU PRESS RIGHT BUTTON
				myEnco.option2();
			}
		});
		
		
		final JTextPane textPane = new JTextPane();
		//Resize text with text window
		textPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textPane.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textPane.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		textPane.setForeground(new Color(255, 165, 0));
		textPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		textPane.setText(textSubstance);
		textPane.setEditable(false);
		
		//hopefully centering it
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		GroupLayout gl_contentPane = new GroupLayout(mainPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(79)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
					.addGap(47))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(button1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
		);
		fileEn.close();
		fileOp.close();
		mainPane.setLayout(gl_contentPane);
		
		setContentPane(mainPane);
		revalidate();
	}
	
	
	/**
	 * Another overloading of the mainBuild method
	 * @param enTextFile String name of text file that will be main text
	 * @param opt1 String of option 1
	 * @param opt2 String of option 2
	 * @param i Any int, just added this so that it is different from other method
	 * @throws FileNotFoundException
	 */
	public void mainBuild(String enTextFile, String opt1, String opt2, int i) throws FileNotFoundException{
		mainPane = new JPanel();
		mainPane.setForeground(new Color(255, 165, 0));
		mainPane.setBackground(Color.DARK_GRAY);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		//SCANNING IN THE TEXT FILE AND PUTTING IT IN THE THING
		Scanner fileEn = new Scanner(new FileInputStream(enTextFile));
		
		String textSubstance = fileEn.nextLine();
		while (fileEn.hasNextLine()){
			textSubstance = textSubstance + "\n" + fileEn.nextLine();
		}
		
		String option1Txt = opt1;
		String option2Txt = opt2;
		
		JButton button1 = new JButton(option1Txt);
		button1.setFont(new Font("SimSun", Font.PLAIN, 20));
		button1.setForeground(new Color(255, 165, 0));
		button1.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		//next two lines makes button clear
		button1.setContentAreaFilled(false);
        button1.setOpaque(true);
        
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//WHEN YOU PRESS LEFT BUTTON
				myEnco.option1();
			}
		});
		
		JButton btnNewButton = new JButton(option2Txt);
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WHEN YOU PRESS RIGHT BUTTON
				myEnco.option2();
			}
		});
		
		
		final JTextPane textPane = new JTextPane();
		//Resize text with text window
		textPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textPane.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textPane.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		textPane.setForeground(new Color(255, 165, 0));
		textPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		textPane.setText(textSubstance);
		textPane.setEditable(false);
		
		//hopefully centering it
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		GroupLayout gl_contentPane = new GroupLayout(mainPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(79)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
					.addGap(47))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(button1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
		);
		fileEn.close();
		mainPane.setLayout(gl_contentPane);
		
		setContentPane(mainPane);
		revalidate();
	}
	
	
	
	
	/**
	 * Overload of main file where there is an added modifyer to the text window
	 * @param enTextFile
	 * @param opTextFile
	 * @param modifyer
	 * @throws FileNotFoundException
	 */
	public void mainBuild(String enTextFile, String opTextFile, String modifyer) throws FileNotFoundException{
		mainPane = new JPanel();
		mainPane.setForeground(new Color(255, 165, 0));
		mainPane.setBackground(Color.DARK_GRAY);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		//SCANNING IN THE TEXT FILE AND PUTTING IT IN THE THING
		Scanner fileEn = new Scanner(new FileInputStream(enTextFile));
		Scanner fileOp = new Scanner(new FileInputStream(opTextFile));
		
		String textSubstance = fileEn.nextLine();
		while (fileEn.hasNextLine()){
			textSubstance = textSubstance + "\n" + fileEn.nextLine();
		}
		
		String option1Txt = fileOp.nextLine();
		String option2Txt = fileOp.nextLine();
		
		JButton button1 = new JButton(option1Txt);
		button1.setFont(new Font("SimSun", Font.PLAIN, 20));
		button1.setForeground(new Color(255, 165, 0));
		button1.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		//next two lines makes button clear
		button1.setContentAreaFilled(false);
        button1.setOpaque(true);
        
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//WHEN YOU PRESS LEFT BUTTON
				myEnco.option1();
			}
		});
		
		JButton btnNewButton = new JButton(option2Txt);
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//WHEN YOU PRESS RIGHT BUTTON
				myEnco.option2();
			}
		});
		
		
		final JTextPane textPane = new JTextPane();
		//Resize text with text window
		textPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textPane.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textPane.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		textPane.setForeground(new Color(255, 165, 0));
		textPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		textPane.setText(textSubstance + "\n" + modifyer);
		textPane.setEditable(false);
		
		//hopefully centering it
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		GroupLayout gl_contentPane = new GroupLayout(mainPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(79)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
					.addGap(47))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(button1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
		);
		fileEn.close();
		fileOp.close();
		mainPane.setLayout(gl_contentPane);
		
		setContentPane(mainPane);
		revalidate();
	}
	
	
	
	/**
	 * Displays the character creation menu
	 */
	public void createBuild(){
		createPane = new JPanel();
		createPane.setForeground(Color.ORANGE);
		createPane.setBackground(Color.DARK_GRAY);
		createPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		final JTextArea textField_5 = new JTextArea();
		textField_5.setText("Spike Dian");
		textField_5.setForeground(new Color(255, 165, 0));
		textField_5.setColumns(10);
		textField_5.setBackground(SystemColor.controlDkShadow);
		textField_5.setBackground(SystemColor.controlDkShadow);
		textField_5.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textField_5.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textField_5.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextArea textField_6 = new JTextArea();
		textField_6.setText("Edward Harvent");
		textField_6.setForeground(new Color(255, 165, 0));
		textField_6.setColumns(10);
		textField_6.setBackground(SystemColor.controlDkShadow);
		textField_6.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textField_6.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textField_6.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextArea textField_7 = new JTextArea();
		textField_7.setText("Scarlett Laforet");
		textField_7.setForeground(new Color(255, 165, 0));
		textField_7.setColumns(10);
		textField_7.setBackground(SystemColor.controlDkShadow);
		textField_7.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textField_7.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textField_7.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextArea textField_8 = new JTextArea();
		textField_8.setText("Johnny Russo");
		textField_8.setForeground(new Color(255, 165, 0));
		textField_8.setColumns(10);
		textField_8.setBackground(SystemColor.controlDkShadow);
		textField_8.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textField_8.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textField_8.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextArea textField_9 = new JTextArea();
		textField_9.setText("Faye Hollystone");
		textField_9.setForeground(new Color(255, 165, 0));
		textField_9.setColumns(10);
		textField_9.setBackground(SystemColor.controlDkShadow);
		textField_9.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = textField_9.getSize();
				fontSize = (int)(size.getWidth()/27.8);
				textField_9.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextArea txtrTheAlohaoi = new JTextArea();
		txtrTheAlohaoi.setText("The Aloha-Oi");
		txtrTheAlohaoi.setForeground(new Color(255, 165, 0));
		txtrTheAlohaoi.setColumns(10);
		txtrTheAlohaoi.setBackground(SystemColor.controlDkShadow);
		txtrTheAlohaoi.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtrTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/17);
				txtrTheAlohaoi.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		final JTextPane txtpnChooseYourNames = new JTextPane();
		txtpnChooseYourNames.setText("CUSTOMIZE YOUR CREW:");
		txtpnChooseYourNames.setForeground(new Color(255, 165, 0));
		txtpnChooseYourNames.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnChooseYourNames.setEditable(false);
		
		txtpnChooseYourNames.setBackground(Color.DARK_GRAY);
		StyledDocument doc = txtpnChooseYourNames.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		txtpnChooseYourNames.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtpnChooseYourNames.getSize();
				fontSize = (int)(size.getWidth()/10);
				txtpnChooseYourNames.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		JLabel lblCharacter = new JLabel("Character 1:");
		lblCharacter.setForeground(new Color(255, 165, 0));
		
		JLabel lblCharacter_1 = new JLabel("Character 2:");
		lblCharacter_1.setForeground(new Color(255, 165, 0));
		
		JLabel lblCharacter_2 = new JLabel("Character 3:");
		lblCharacter_2.setForeground(new Color(255, 165, 0));
		
		JLabel lblCharacter_3 = new JLabel("Character 4:");
		lblCharacter_3.setForeground(new Color(255, 165, 0));
		
		JLabel lblCharacter_4 = new JLabel("Character 5:");
		lblCharacter_4.setForeground(new Color(255, 165, 0));
		
		JLabel lblYourShip = new JLabel("Your Ship:");
		lblYourShip.setForeground(new Color(255, 165, 0));
		
		
		JButton btnNewButton = new JButton("ACCEPT");
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//SET THESE TEXT FIELDS TO THE NAMES OF PEOPLE
				theShip.setName(txtrTheAlohaoi.getText()); //ship name
				theShip.addPerson(new Person(textField_5.getText())); //character 1
				theShip.getPeople().get(0).setShip(theShip);
				theShip.addPerson(new Person(textField_6.getText())); //character 2
				theShip.addPerson(new Person(textField_7.getText())); //character 3
				theShip.addPerson(new Person(textField_8.getText())); //character 4
				theShip.addPerson(new Person(textField_9.getText())); //character 5
				
				storeBuild(new Store(1, theShip.getCargo()));
			}
		});
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.setForeground(new Color(255, 165, 0));
		btnQuit.setBackground(SystemColor.controlDkShadow);
		btnQuit.setContentAreaFilled(false);
        btnQuit.setOpaque(true);
        btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
		GroupLayout gl_createPane = new GroupLayout(createPane);
		gl_createPane.setHorizontalGroup(
			gl_createPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_createPane.createSequentialGroup()
					.addGroup(gl_createPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_createPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnChooseYourNames, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_createPane.createSequentialGroup()
							.addGap(396)
							.addComponent(btnQuit, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addGap(48)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_createPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_createPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_createPane.createSequentialGroup()
							.addComponent(lblCharacter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(gl_createPane.createSequentialGroup()
							.addComponent(lblCharacter_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(gl_createPane.createSequentialGroup()
							.addComponent(lblCharacter_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(gl_createPane.createSequentialGroup()
							.addComponent(lblCharacter_3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(gl_createPane.createSequentialGroup()
							.addComponent(lblCharacter_4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
						.addGroup(gl_createPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblYourShip)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtrTheAlohaoi, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)))
					.addGap(32))
		);
		gl_createPane.setVerticalGroup(
			gl_createPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_createPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnChooseYourNames, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(30)
					.addGroup(gl_createPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrTheAlohaoi, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addGroup(gl_createPane.createSequentialGroup()
							.addGap(15)
							.addComponent(lblYourShip, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)))
					.addGap(32)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCharacter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCharacter_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCharacter_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCharacter_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(lblCharacter_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_createPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnQuit, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addGap(0))
		);
		createPane.setLayout(gl_createPane);
		
		setContentPane(createPane);
		revalidate();
	}

	
	
	/**
	 * Displays the interface for a store
	 * @param myStore The store that it is displaying
	 */
	public void storeBuild(final Store myStore){
		storePane = new JPanel();
		storePane.setForeground(new Color(255, 165, 0));
		storePane.setBackground(Color.DARK_GRAY);
		storePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		final JTextPane storeTextPane = new JTextPane();
		storeTextPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		final Color storePaneColor = new Color(255, 165, 0);
		storeTextPane.setForeground(storePaneColor);
		storeTextPane.setBackground(SystemColor.controlDkShadow);
		storeTextPane.setEditable(false);
		//centering
		StyledDocument docStore = storeTextPane.getStyledDocument();
		SimpleAttributeSet centerStore = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerStore, StyleConstants.ALIGN_CENTER);
		docStore.setParagraphAttributes(0, docStore.getLength(), centerStore, false);
		//font adjusting
		storeTextPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = storeTextPane.getSize();
				fontSize = (int)((size.getWidth())/(20 + (getHeight()/30)));
				storeTextPane.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		final String defaultStoreText = "Welcome to this humble Space Store"
										+ "\n----------------------------------------------------------"
										+ "\nHere you can shop to your heart's content." 
										+ "\nIf you have the Woolongs to back it up, anyways."
										+ "\n\nHover over each object to see more information"
										+ "\nand click to purchase it."
										+ "\nHave a nice space day!"
										;
		storeTextPane.setText(defaultStoreText);
		
		final JTextField txtFuel = new JTextField();
		txtFuel.setFont(new Font("SimSun", Font.PLAIN, 15));
		txtFuel.setBorder(null);
		txtFuel.setBackground(Color.DARK_GRAY);
		txtFuel.setForeground(new Color(255, 165, 0));
		txtFuel.setText("FUEL: " + myStore.getCargo().getEnergyCells());
		txtFuel.setEditable(false);
		txtFuel.setColumns(10);
		
		final JTextField txtTang = new JTextField();
		txtTang.setFont(new Font("SimSun", Font.PLAIN, 15));
		txtTang.setBorder(null);
		txtTang.setBackground(Color.DARK_GRAY);
		txtTang.setForeground(new Color(255, 165, 0));
		txtTang.setText("TANG: " + myStore.getCargo().getTangRations());
		txtTang.setEditable(false);
		txtTang.setColumns(10);
		
		final JTextField txtParts = new JTextField();
		txtParts.setFont(new Font("SimSun", Font.PLAIN, 15));
		txtParts.setBorder(null);
		txtParts.setBackground(Color.DARK_GRAY);
		txtParts.setForeground(new Color(255, 165, 0));
		txtParts.setText("PARTS: " + myStore.getCargo().getRepairParts());
		txtParts.setEditable(false);
		txtParts.setColumns(10);
		
		
		final JTextPane txtpnWoolongs = new JTextPane();
		txtpnWoolongs.setText("WOOLONGS:\n" + myStore.getCargo().getWoolongs());
		txtpnWoolongs.setBackground(Color.DARK_GRAY);
		txtpnWoolongs.setForeground(new Color(255, 165, 0));
		txtpnWoolongs.setEditable(false);
		//centering
		StyledDocument doc1 = txtpnWoolongs.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
		//font adjusting
		txtpnWoolongs.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtpnWoolongs.getSize();
				fontSize = (int)(size.getWidth()/12);
				txtpnWoolongs.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		JButton btnRepairParts = new JButton("Repair Part");
		btnRepairParts.setForeground(new Color(255, 165, 0));
		btnRepairParts.setBackground(SystemColor.controlDkShadow);
		btnRepairParts.setContentAreaFilled(false);
        btnRepairParts.setOpaque(true);
        btnRepairParts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//enter stuff
				storeTextPane.setText("Repair Part"
										+ "\n----------------------------------------------------------"
										+ "\nPrice: " + myStore.getPartPrice() 
										+ " Woolongs\nAmmount in Stock: " + myStore.getRepairParts()
										+ "\n\nThese repair parts can be found in shops throughout"
										+ "\nthe universe. They can be used to patch up your ship"
										+ "\nwhen it's damaged."
										);
			}
			public void mouseExited(MouseEvent arg0) {
				storeTextPane.setText(defaultStoreText);
			}
		});
		btnRepairParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buy repair part
				myStore.buyParts();
				txtpnWoolongs.setText("WOOLONGS:\n" + myStore.getCargo().getWoolongs());
				txtParts.setText("Parts: " + myStore.getCargo().getRepairParts());
				storeTextPane.setText("Repair Part"
						+ "\n----------------------------------------------------------"
						+ "\nPrice: " + myStore.getPartPrice() 
						+ " Woolongs\nAmmount in Stock: " + myStore.getRepairParts()
						+ "\n\nThese repair parts can be found in shops throughout"
						+ "\nthe universe. They can be used to patch up your ship"
						+ "\nwhen it's damaged."
						);
				revalidate();
			}
		});
		
		JButton btnFuelCells = new JButton("Fuel Cell");
		btnFuelCells.setForeground(new Color(255, 165, 0));
		btnFuelCells.setBackground(SystemColor.controlDkShadow);
		btnFuelCells.setContentAreaFilled(false);
        btnFuelCells.setOpaque(true);
        btnFuelCells.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				storeTextPane.setText("Fuel Cell"
						+ "\n----------------------------------------------------------"
						+ "\nPrice: " + myStore.getCellPrice() 
						+ " Woolongs\nAmmount in Stock: " + myStore.getEnergyCells()
						+ "\n\nThese fuel cells can be found in shops throughout"
						+ "\nthe universe. They are used to travel between the stars,"
						+ "\nand without them, you're kinda stuck."
						);
			}
			public void mouseExited(MouseEvent arg0) {
				storeTextPane.setText(defaultStoreText);
			}
		});
		btnFuelCells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buy fuel cell
				myStore.buyCell();
				txtpnWoolongs.setText("WOOLONGS:\n" + myStore.getCargo().getWoolongs());
				txtFuel.setText("Fuel: " + myStore.getCargo().getEnergyCells());
				storeTextPane.setText("Fuel Cell"
						+ "\n----------------------------------------------------------"
						+ "\nPrice: " + myStore.getCellPrice() 
						+ " Woolongs\nAmmount in Stock: " + myStore.getEnergyCells()
						+ "\n\nThese fuel cells can be found in shops throughout"
						+ "\nthe universe. They are used to travel between the stars,"
						+ "\nand without them, you're kinda stuck."
						);
				revalidate();
			}
		});
		
		JButton btnTangRations = new JButton("Tang Rations");
		btnTangRations.setForeground(new Color(255, 165, 0));
		btnTangRations.setBackground(SystemColor.controlDkShadow);
		btnTangRations.setContentAreaFilled(false);
        btnTangRations.setOpaque(true);
        btnTangRations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//enter stuff
				storeTextPane.setText("Ration of Tang"
										+ "\n----------------------------------------------------------"
										+ "\nPrice: " + myStore.getTangPrice() 
										+ " Woolongs\nAmmount in Stock: " + myStore.getTangRations()
										+ "\n\nRations of Tang can be found in shops throughout"
										+ "\nthe universe. They are your crew's main food source"
										+ "\nbecause they are just so darn delicious."
										);
			}
			public void mouseExited(MouseEvent arg0) {
				storeTextPane.setText(defaultStoreText);
			}
		});
		btnTangRations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buy tang ration
				myStore.buyTang();
				txtpnWoolongs.setText("WOOLONGS:\n" + myStore.getCargo().getWoolongs());
				txtTang.setText("Tang: " + myStore.getCargo().getTangRations());
				storeTextPane.setText("Ration of Tang"
						+ "\n----------------------------------------------------------"
						+ "\nPrice: " + myStore.getTangPrice() 
						+ " Woolongs\nAmmount in Stock: " + myStore.getTangRations()
						+ "\n\nRations of Tang can be found in shops throughout"
						+ "\nthe universe. They are your crew's main food source"
						+ "\nbecause they are just so darn delicious."
						);
				revalidate();
			}
		});
		
		JButton btnShipUpgrade = new JButton("Ship Upgrade");
		btnShipUpgrade.setForeground(new Color(255, 165, 0));
		btnShipUpgrade.setBackground(SystemColor.controlDkShadow);
		btnShipUpgrade.setContentAreaFilled(false);
        btnShipUpgrade.setOpaque(true);
        btnShipUpgrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//enter stuff
				storeTextPane.setText("Ship Upgrade"
										+ "\n----------------------------------------------------------"
										+ "\nPrice: Unknown" 
										+ "\nAmmount in Stock: " + 0
										+ "\n\nA fabled object of unimaginable power,"
										+ "\nthe ship upgrade is the rarest of the rare."
										+ "\nSo rare in fact, the developer hasn't integrated them yet."
										);
			}
			public void mouseExited(MouseEvent arg0) {
				storeTextPane.setText(defaultStoreText);
			}
		});
		btnShipUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buy ship upgrade
				myStore.buyUpgrade();
				txtpnWoolongs.setText("WOOLONGS:\n" + myStore.getCargo().getWoolongs());
				storeTextPane.setText("Ship Upgrade"
						+ "\n----------------------------------------------------------"
						+ "\nPrice: Unknown" 
						+ "\nAmmount in Stock: " + 0
						+ "\n\nA fabled object of unimaginable power,"
						+ "\nthe ship upgrade is the rarest of the rare."
						+ "\nSo rare in fact, the developer hasn't integrated them yet."
						);
				revalidate();
			}
		});

		
		final JTextPane txtpnTheSpaceStore = new JTextPane();
		txtpnTheSpaceStore.setForeground(new Color(255, 165, 0));
		txtpnTheSpaceStore.setBackground(Color.DARK_GRAY);
		txtpnTheSpaceStore.setText("THE SPACE\nSTORE");
		txtpnTheSpaceStore.setEditable(false);
		//centering
		StyledDocument doc = txtpnTheSpaceStore.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		//font adjusting
		txtpnTheSpaceStore.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtpnTheSpaceStore.getSize();
				fontSize = (int)(size.getWidth()/8);
				txtpnTheSpaceStore.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		JButton btnNewButton_1 = new JButton("ACCEPT");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 13));
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.setForeground(new Color(255, 165, 0));
		btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setOpaque(true);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//move on to map
				mapBuild();
			}
		});
		
		JButton btnNewButton_2 = new JButton("QUIT");
		btnNewButton_2.setFont(new Font("SimSun", Font.PLAIN, 13));
		btnNewButton_2.setBackground(SystemColor.controlDkShadow);
		btnNewButton_2.setForeground(new Color(255, 165, 0));
		btnNewButton_2.setContentAreaFilled(false);
        btnNewButton_2.setOpaque(true);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		GroupLayout gl_storePane = new GroupLayout(storePane);
		gl_storePane.setHorizontalGroup(
			gl_storePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_storePane.createSequentialGroup()
					.addGroup(gl_storePane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_storePane.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_storePane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_storePane.createSequentialGroup()
									.addGroup(gl_storePane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnShipUpgrade, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnTangRations, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnFuelCells, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRepairParts, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addComponent(storeTextPane, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
								.addGroup(gl_storePane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtpnTheSpaceStore, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(txtpnWoolongs, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.addGap(50)
									.addGroup(gl_storePane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtParts, 0, 0, Short.MAX_VALUE)
										.addComponent(txtFuel, 0, 0, Short.MAX_VALUE)
										.addComponent(txtTang, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_storePane.createSequentialGroup()
							.addGap(448)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_storePane.setVerticalGroup(
			gl_storePane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_storePane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_storePane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_storePane.createSequentialGroup()
							.addComponent(txtpnWoolongs, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addGap(23))
						.addComponent(txtpnTheSpaceStore, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
						.addGroup(gl_storePane.createSequentialGroup()
							.addComponent(txtParts, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFuel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTang, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)))
					.addGroup(gl_storePane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_storePane.createSequentialGroup()
							.addGap(14)
							.addComponent(btnRepairParts, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnFuelCells, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnTangRations, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnShipUpgrade, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_storePane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(storeTextPane, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_storePane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(3))
		);
		storePane.setLayout(gl_storePane);
		setContentPane(storePane);
		revalidate();
	}
	
	
	/**
	 * displays the status screen of the ship
	 */
	public void statusBuild(){
		statusPane = new JPanel();
		statusPane.setForeground(new Color(255, 165, 0));
		statusPane.setBackground(Color.DARK_GRAY);
		statusPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		final JTextPane txtTheAlohaoi = new JTextPane();
		txtTheAlohaoi.setBorder(null);
		txtTheAlohaoi.setBackground(Color.DARK_GRAY);
		txtTheAlohaoi.setFont(new Font("SimSun", Font.BOLD, 90));
		txtTheAlohaoi.setForeground(new Color(255, 165, 0));
		txtTheAlohaoi.setText(theShip.getName());
		txtTheAlohaoi.setEditable(false);
		StyledDocument doc1 = txtTheAlohaoi.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
		
		
		final JTextArea textAreaChar1 = new JTextArea();
		textAreaChar1.setBackground(Color.DARK_GRAY);
		textAreaChar1.setText(theShip.getPeople().get(0).getName()+":");
		textAreaChar1.setForeground(new Color(255, 165, 0));
		textAreaChar1.setEditable(false);
		textAreaChar1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaChar1.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaChar2 = new JTextArea();
		textAreaChar2.setBackground(Color.DARK_GRAY);
		textAreaChar2.setText(theShip.getPeople().get(1).getName()+":");
		textAreaChar2.setForeground(new Color(255, 165, 0));
		textAreaChar2.setEditable(false);
		textAreaChar2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaChar2.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaChar3 = new JTextArea();
		textAreaChar3.setBackground(Color.DARK_GRAY);
		textAreaChar3.setText(theShip.getPeople().get(2).getName()+":");
		textAreaChar3.setForeground(new Color(255, 165, 0));
		textAreaChar3.setEditable(false);
		textAreaChar3.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaChar3.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaChar4 = new JTextArea();
		textAreaChar4.setBackground(Color.DARK_GRAY);
		textAreaChar4.setText(theShip.getPeople().get(3).getName()+":");
		textAreaChar4.setForeground(new Color(255, 165, 0));
		textAreaChar4.setEditable(false);
		textAreaChar4.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaChar4.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaChar5 = new JTextArea();
		textAreaChar5.setBackground(Color.DARK_GRAY);
		textAreaChar5.setText(theShip.getPeople().get(4).getName()+":");
		textAreaChar5.setForeground(new Color(255, 165, 0));
		textAreaChar5.setEditable(false);
		textAreaChar5.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaChar5.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaShipStat = new JTextArea();
		textAreaShipStat.setFont(new Font("SimSun", Font.PLAIN, 18));
		textAreaShipStat.setText("Ship Status: " + theShip.getCondition());
		textAreaShipStat.setToolTipText("Hull health: " + theShip.getHullHealth() + "   Tang: " + theShip.getCargo().getTangRations() + "   Fuel Cells: " + theShip.getCargo().getEnergyCells() + "   Repair Parts: " + theShip.getCargo().getRepairParts() + "   Woolongs: " + theShip.getCargo().getWoolongs());
		textAreaShipStat.setBorder(null);
		textAreaShipStat.setForeground(new Color(255, 165, 0));
		textAreaShipStat.setBackground(Color.DARK_GRAY);
		textAreaShipStat.setEditable(false);
		textAreaShipStat.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaShipStat.setFont(new Font("SimSun", Font.BOLD, fontSize)); //font stuff
			}
		});
		
		final JButton btnNewButton = new JButton("GOTO MAP");
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapBuild();
			}
		});
		
        final JTextArea textAreaHealth5 = new JTextArea();
        if(theShip.getPeople().get(4).alive()){
			textAreaHealth5.setToolTipText("Health: " + theShip.getPeople().get(4).getHealth() + "   Food: " + theShip.getPeople().get(4).getFood() + "   Sanity: " + theShip.getPeople().get(4).getSanity());
			textAreaHealth5.setText(theShip.getPeople().get(4).getHealthStatus() + ", " + theShip.getPeople().get(4).getHungerStatus() + ", " + theShip.getPeople().get(4).getSanityStatus());
        }else{
        	textAreaHealth5.setText("DEAD");
        }
        textAreaHealth5.setBackground(Color.DARK_GRAY);
		textAreaHealth5.setForeground(new Color(255, 165, 0));
		textAreaHealth5.setEditable(false);
		textAreaHealth5.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaHealth5.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaHealth4 = new JTextArea();
		if(theShip.getPeople().get(3).alive()){
			textAreaHealth4.setToolTipText("Health: " + theShip.getPeople().get(3).getHealth() + "   Food: " + theShip.getPeople().get(3).getFood() + "   Sanity: " + theShip.getPeople().get(3).getSanity());
			textAreaHealth4.setText(theShip.getPeople().get(1).getHealthStatus() + ", " + theShip.getPeople().get(1).getHungerStatus() + ", " + theShip.getPeople().get(1).getSanityStatus());
		}else{
			textAreaHealth4.setText("DEAD");
		}
		textAreaHealth4.setBackground(Color.DARK_GRAY);
		textAreaHealth4.setForeground(new Color(255, 165, 0));
		textAreaHealth4.setEditable(false);
		textAreaHealth4.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaHealth4.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaHealth3 = new JTextArea();
		if(theShip.getPeople().get(2).alive()){
			textAreaHealth3.setToolTipText("Health: " + theShip.getPeople().get(2).getHealth() + "   Food: " + theShip.getPeople().get(2).getFood() + "   Sanity: " + theShip.getPeople().get(2).getSanity());
			textAreaHealth3.setText(theShip.getPeople().get(2).getHealthStatus() + ", " + theShip.getPeople().get(2).getHungerStatus() + ", " + theShip.getPeople().get(2).getSanityStatus());
		}else{
			textAreaHealth3.setText("DEAD");
		}
		textAreaHealth3.setBackground(Color.DARK_GRAY);
		textAreaHealth3.setForeground(new Color(255, 165, 0));
		textAreaHealth3.setEditable(false);
		textAreaHealth3.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaHealth3.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaHealth2 = new JTextArea();
		if(theShip.getPeople().get(1).alive()){
			textAreaHealth2.setToolTipText("Health: " + theShip.getPeople().get(1).getHealth() + "   Food: " + theShip.getPeople().get(1).getFood() + "   Sanity: " + theShip.getPeople().get(1).getSanity());
			textAreaHealth2.setText(theShip.getPeople().get(1).getHealthStatus() + ", " + theShip.getPeople().get(1).getHungerStatus() + ", " + theShip.getPeople().get(1).getSanityStatus());
		}else{
			textAreaHealth2.setText("DEAD");
		}
		textAreaHealth2.setBackground(Color.DARK_GRAY);
		textAreaHealth2.setForeground(new Color(255, 165, 0));
		textAreaHealth2.setEditable(false);
		textAreaHealth2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaHealth2.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaHealth1 = new JTextArea();
		if(theShip.getPeople().get(0).alive()){
			textAreaHealth1.setToolTipText("Health: " + theShip.getPeople().get(0).getHealth() + "   Food: " + theShip.getPeople().get(0).getFood() + "   Sanity: " + theShip.getPeople().get(0).getSanity());
			textAreaHealth1.setText(theShip.getPeople().get(0).getHealthStatus() + ", " + theShip.getPeople().get(0).getHungerStatus() + ", " + theShip.getPeople().get(0).getSanityStatus());
		}else{
			textAreaHealth1.setText("DEAD");
		}
		textAreaHealth1.setBackground(Color.DARK_GRAY);
		textAreaHealth1.setForeground(new Color(255, 165, 0));
		textAreaHealth1.setEditable(false);
		textAreaHealth1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaHealth1.setFont(new Font("SimSun", Font.PLAIN, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaDisease1 = new JTextArea();
		textAreaDisease1.setToolTipText("Diseases: " + theShip.getPeople().get(0).getDiseases());
		textAreaDisease1.setBackground(Color.DARK_GRAY);
		textAreaDisease1.setText(theShip.getPeople().get(0).getDiseaseStatus());
		textAreaDisease1.setForeground(new Color(255, 165, 0));
		textAreaDisease1.setEditable(false);
		textAreaDisease1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaDisease1.setFont(new Font("SimSun", Font.ITALIC, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaDisease2 = new JTextArea();
		textAreaDisease2.setToolTipText("Diseases: " + theShip.getPeople().get(1).getDiseases());
		textAreaDisease2.setBackground(Color.DARK_GRAY);
		textAreaDisease2.setText(theShip.getPeople().get(1).getDiseaseStatus());
		textAreaDisease2.setForeground(new Color(255, 165, 0));
		textAreaDisease2.setEditable(false);
		textAreaDisease2.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaDisease2.setFont(new Font("SimSun", Font.ITALIC, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaDisease3 = new JTextArea();
		textAreaDisease3.setToolTipText("Diseases: " + theShip.getPeople().get(2).getDiseases());
		textAreaDisease3.setBackground(Color.DARK_GRAY);
		textAreaDisease3.setText(theShip.getPeople().get(2).getDiseaseStatus());
		textAreaDisease3.setForeground(new Color(255, 165, 0));
		textAreaDisease3.setEditable(false);
		textAreaDisease3.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaDisease3.setFont(new Font("SimSun", Font.ITALIC, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaDisease4 = new JTextArea();
		textAreaDisease4.setToolTipText("Diseases: " + theShip.getPeople().get(3).getDiseases());
		textAreaDisease4.setBackground(Color.DARK_GRAY);
		textAreaDisease4.setText(theShip.getPeople().get(3).getDiseaseStatus());
		textAreaDisease4.setForeground(new Color(255, 165, 0));
		textAreaDisease4.setEditable(false);
		textAreaDisease4.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaDisease4.setFont(new Font("SimSun", Font.ITALIC, fontSize)); //font stuff
			}
		});
		
		
		final JTextArea textAreaDisease5 = new JTextArea();
		textAreaDisease5.setToolTipText("Diseases: " + theShip.getPeople().get(4).getDiseases());
		textAreaDisease5.setBackground(Color.DARK_GRAY);
		textAreaDisease5.setText(theShip.getPeople().get(4).getDiseaseStatus());
		textAreaDisease5.setForeground(new Color(255, 165, 0));
		textAreaDisease5.setEditable(false);
		textAreaDisease5.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fontSize = 17;
				Dimension size = txtTheAlohaoi.getSize();
				fontSize = (int)(size.getWidth()/35);
				textAreaDisease5.setFont(new Font("SimSun", Font.ITALIC, fontSize)); //font stuff
			}
		});
		
		
        final JButton eject1 = new JButton("Eject");
		eject1.setForeground(new Color(255, 165, 0));
		eject1.setBackground(SystemColor.controlDkShadow);
		eject1.setContentAreaFilled(false);
        eject1.setOpaque(true);
        eject1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eject player1
				theShip.ejectFromAirlock(theShip.getPeople().get(0));
				textAreaHealth1.setText("DEAD");
				textAreaHealth1.setToolTipText("Floating away into the unkown");
				textAreaDisease1.setText("DEAD");
				someoneAlive = false;
				for(int i=0; i<theShip.getPeople().size(); i++){
					if(theShip.getPeople().get(i).alive()){
						someoneAlive = true;
					}
				}
				if(!someoneAlive){
					theShip.getGame().lose();
				}
			}
		});
		
        final JButton eject2 = new JButton("Eject");
		eject2.setForeground(new Color(255, 165, 0));
		eject2.setBackground(SystemColor.controlDkShadow);
		eject2.setContentAreaFilled(false);
        eject2.setOpaque(true);
        eject2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eject player2
				theShip.ejectFromAirlock(theShip.getPeople().get(1));
				textAreaHealth2.setText("DEAD");
				textAreaHealth2.setToolTipText("Floating away into the unkown");
				textAreaDisease2.setText("DEAD");
				someoneAlive = false;
				for(int i=0; i<theShip.getPeople().size(); i++){
					if(theShip.getPeople().get(i).alive()){
						someoneAlive = true;
					}
				}
				if(!someoneAlive){
					theShip.getGame().lose();
				}
			}
		});
		
        final JButton eject3 = new JButton("Eject");
		eject3.setForeground(new Color(255, 165, 0));
		eject3.setBackground(SystemColor.controlDkShadow);
		eject3.setContentAreaFilled(false);
        eject3.setOpaque(true);
        eject3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eject player3
				theShip.ejectFromAirlock(theShip.getPeople().get(2));
				textAreaHealth3.setText("DEAD");
				textAreaHealth3.setToolTipText("Floating away into the unkown");
				textAreaDisease3.setText("DEAD");
				someoneAlive = false;
				for(int i=0; i<theShip.getPeople().size(); i++){
					if(theShip.getPeople().get(i).alive()){
						someoneAlive = true;
					}
				}
				if(!someoneAlive){
					theShip.getGame().lose();
				}
			}
		});
		
        final JButton eject4 = new JButton("Eject");
		eject4.setForeground(new Color(255, 165, 0));
		eject4.setBackground(SystemColor.controlDkShadow);
		eject4.setContentAreaFilled(false);
        eject4.setOpaque(true);
        eject4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eject player4
				theShip.ejectFromAirlock(theShip.getPeople().get(3));
				textAreaHealth4.setText("DEAD");
				textAreaHealth4.setToolTipText("Floating away into the unkown");
				textAreaDisease4.setText("DEAD");
				someoneAlive = false;
				for(int i=0; i<theShip.getPeople().size(); i++){
					if(theShip.getPeople().get(i).alive()){
						someoneAlive = true;
					}
				}
				if(!someoneAlive){
					theShip.getGame().lose();
				}
			}
		});
		
        final JButton eject5 = new JButton("Eject");
		eject5.setForeground(new Color(255, 165, 0));
		eject5.setBackground(SystemColor.controlDkShadow);
		eject5.setContentAreaFilled(false);
        eject5.setOpaque(true);
        eject5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eject player5
				theShip.ejectFromAirlock(theShip.getPeople().get(4));
				textAreaHealth5.setText("DEAD");
				textAreaHealth5.setToolTipText("Floating away into the unkown");
				textAreaDisease5.setText("DEAD");
				someoneAlive = false;
				for(int i=0; i<theShip.getPeople().size(); i++){
					if(theShip.getPeople().get(i).alive()){
						someoneAlive = true;
					}
				}
				if(!someoneAlive){
					theShip.getGame().lose();
				}
			}
		});
		
		
		
		GroupLayout gl_statusPane = new GroupLayout(statusPane);
		gl_statusPane.setHorizontalGroup(
			gl_statusPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_statusPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_statusPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_statusPane.createSequentialGroup()
							.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTheAlohaoi, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
								.addGroup(gl_statusPane.createSequentialGroup()
									.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textAreaChar1, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(textAreaChar2, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(textAreaChar3, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(textAreaChar5, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(textAreaChar4, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textAreaHealth5, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaHealth4, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaHealth3, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaHealth2, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaHealth1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textAreaDisease1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaDisease2, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaDisease3, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(textAreaDisease4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textAreaDisease5, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
										.addComponent(eject3, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
										.addComponent(eject1, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
										.addComponent(eject2, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
										.addComponent(eject4, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
										.addComponent(eject5, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(0))
						.addGroup(gl_statusPane.createSequentialGroup()
							.addComponent(textAreaShipStat, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton)
							.addGap(21))))
		);
		gl_statusPane.setVerticalGroup(
			gl_statusPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtTheAlohaoi, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statusPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAreaChar1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaHealth1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaDisease1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addComponent(eject1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statusPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAreaChar2, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaHealth2, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaDisease2, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addComponent(eject2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_statusPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_statusPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAreaChar3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaHealth3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaDisease3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addComponent(eject3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statusPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAreaChar4, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaHealth4, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaDisease4, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addComponent(eject4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(19)
					.addGroup(gl_statusPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_statusPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAreaChar5, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaHealth5, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(textAreaDisease5, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addComponent(eject5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(gl_statusPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statusPane.createSequentialGroup()
							.addGap(26)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_statusPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textAreaShipStat, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
					.addContainerGap())
		);
		statusPane.setLayout(gl_statusPane);
		setContentPane(statusPane);
		revalidate();
	}
}
