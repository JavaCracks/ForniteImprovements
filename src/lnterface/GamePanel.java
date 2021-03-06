package lnterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import animation.Animation;
import animation.BufferedImageLoader;
import model.Player;
import model.Weapon;
import threads.PlayerMovementThread;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	public static final String AXE = "./images/weapons/AXE.png";
	public static final String WEAPON1 = "./images/weapons/weapon1.png";
	public static final String WEAPON2 = "./images/weapons/weapon2.png";
	public static final String WEAPON3 = "./images/weapons/weapon3.png";
	public static final String WEAPON4 = "./images/weapons/weapon4.png";
	public static final String WEAPON5 = "./images/weapons/weapon5.png";
	public static final String WEAPON6 = "./images/weapons/weapon6.png";
	public static final String WEAPON7 = "./images/weapons/weapon7.png";
	public static final String WEAPON8 = "./images/weapons/weapon8.png";
	public static final String WEAPON9 = "./images/weapons/weapon9.png";

	public final static String REMOVE = "REMOVE";
	public final static String ADD = "ADD";

	public final static Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./images/backgrounds/game.jpeg");
	public static final Image BOOM = Toolkit.getDefaultToolkit().createImage("./images/logos/shoot.png");
	public static final Image GUN = Toolkit.getDefaultToolkit().createImage("./images/logos/xbox.png");

	private PlayerMovementThread playerMovement;
	private MatchmakingPanel matchmaking;
	private JPanel panelGame;
	private JPanel panelInfo;
	private JList weapons;
	private JLabel players;
	private JLabel ammo;
	private JLabel aux;
	private JLabel axe;
	private JLabel weapon1;
	private JLabel weapon2;
	private JLabel weapon3;
	private JLabel weapon4;
	private JLabel weapon5;
	DefaultListModel<ImageIcon> listModel;
	private JButton button;
	private JButton button1;
	private Animation stand;
	private Animation running;
	private BufferedImage standSprite;
	private BufferedImage runningSprite;
	private boolean run = false;
	private boolean explode = false;
	private Weapon currentWeapon;

	public GamePanel(MatchmakingPanel matchmaking) {

		this.matchmaking = matchmaking;

		setLayout(null);

		BufferedImageLoader loader = new BufferedImageLoader();
		currentWeapon = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().getActualWeapon();

		try {

			standSprite = loader.loadImage("./images/sprites/stand.png");
			runningSprite = loader.loadImage("./images/sprites/running.png");

		} catch (IOException e) {

			e.printStackTrace();

		}

		stand = new Animation(standSprite, 22, 1, 22, 1);
		running = new Animation(runningSprite, 28, 1, 7, 4);

		Border border = BorderFactory.createRaisedBevelBorder();
		TitledBorder border1 = new TitledBorder("");

		button = new JButton(ADD);
		button.addActionListener(this);
		button.setActionCommand(ADD);

		button1 = new JButton(REMOVE);
		button1.addActionListener(this);
		button1.setActionCommand(REMOVE);

		axe = new JLabel();
		weapon1 = new JLabel();
		weapon2 = new JLabel();
		weapon3 = new JLabel();
		weapon4 = new JLabel();
		weapon5 = new JLabel();

		panelGame = new JPanel();
		panelGame.setSize(500, 600);
		panelGame.setBackground(new Color(0, 0, 0, 0));

		panelGame.setBounds(1, 1, 900, 768);
		panelGame.setBorder(border1);
		panelGame.setOpaque(false);

		panelInfo = new JPanel();
		panelInfo.setBackground(new Color(12, 12, 12, 60));

		panelInfo.setBounds(901, 1, 380, 768);
		panelInfo.setLayout(null);

		panelInfo.setBorder(border1);
// Go
		listModel = new DefaultListModel<ImageIcon>();
		weapons = new JList<>(listModel);
		weapons.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		weapons.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		weapons.setVisibleRowCount(-1);
		weapons.setBackground(new Color(12, 12, 12, 60));
		weapons.setBorder(border1);
		JScrollPane listScroller = new JScrollPane(weapons);
		listScroller.setPreferredSize(new Dimension(300, 250));

		aux = new JLabel("---------------------------------------------------");
		aux.setSize(300, 1);
		players = new JLabel("Jugadores restantes: 85", SwingConstants.CENTER);
		players.setHorizontalAlignment(JLabel.CENTER);
		players.setForeground(Color.WHITE);
		players.setFont(new Font("Garamond", 1, 18));

		ammo = new JLabel("Ammo : " + currentWeapon.getAmmo() + "", SwingConstants.CENTER);
		ammo.setHorizontalAlignment(JLabel.CENTER);
		ammo.setForeground(Color.WHITE);
		ammo.setFont(new Font("Garamond", 1, 18));

		Components();

		JList();

		playerMovement = new PlayerMovementThread(this,
				matchmaking.getLobby().getInitialPanel().getMainWindow().getGame());
		playerMovement.start();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void Components() {

		weapons.setBounds(130, 20, 100, 600);
		players.setBounds(0, 550, 400, 200);
		ammo.setBounds(-10, 600, 400, 200);
		button.setBounds(100, 100, 80, 80);
		button1.setBounds(300, 300, 100, 100);

		panelInfo.add(ammo);
		panelInfo.add(players);
		panelInfo.add(weapons);
		// panelInfo.add(axe);

		add(panelGame, BorderLayout.CENTER);
		add(panelInfo, BorderLayout.EAST);

//		add(button);
//		add(button1);
	}

	public JLabel getAmmo() {

		return ammo;

	}

	public Weapon getCurrentWeapon() {

		return currentWeapon;

	}

	public void JList() {

		ImageIcon image = new ImageIcon(AXE);

		Weapon axe = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().getActualWeapon();

//		ImageIcon image1 = new ImageIcon(WEAPON1);
//		ImageIcon image2 = new ImageIcon(WEAPON2);
//		ImageIcon image3 = new ImageIcon(WEAPON3);
//		ImageIcon image4 = new ImageIcon(WEAPON4);
//		ImageIcon image5 = new ImageIcon(WEAPON5);

		listModel.addElement(new ImageIcon(axe.getName()));
//		listModel.addElement(image1);
//		listModel.addElement(image2);
//		listModel.addElement(image3);
//		listModel.addElement(image4);
//		listModel.addElement(image5);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		Player player = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser();

		if (run) {

			running.drawAnimation(g, player.getX(), player.getY() + 30, +5);

		} else if (!run) {

			stand.drawAnimation(g, player.getX(), player.getY(), +5);

		}

		if (explode) {

			g.drawImage(BOOM, player.getX() + 75, player.getY() + 80, null);

		}

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String a = e.getActionCommand();

		if (a.equals(REMOVE)) {
			listModel.removeElementAt(0);
		}
		if (a.equals(ADD)) {
			ImageIcon image5 = new ImageIcon("");
			listModel.add(0, image5);
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {

		int event = k.getKeyCode();

		if (event == KeyEvent.VK_RIGHT) {

			run = true;

			matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().move();

		}

		if (event == KeyEvent.VK_LEFT) {

			run = true;

			matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().back();

		}

		if (event == KeyEvent.VK_SPACE) {

			explode = true;

			Player user = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser();

			user.getActualWeapon().shoot();

			if (currentWeapon.getAmmo() == 0) {

				user.dropWeapon();
				listModel.removeElementAt(0);
				currentWeapon = user.getActualWeapon();

			}

		}

		if (event == KeyEvent.VK_C) {

			Random ran = new Random();

			Weapon[] weapons = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getWeapons();

			int i = ran.nextInt(weapons.length);

			Player user = matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser();

			user.pickWeapon(weapons[i]);

			currentWeapon = weapons[i];

			listModel.add(0, new ImageIcon(user.getActualWeapon().getName()));

		}

	}

	public Animation getStand() {

		return stand;

	}

	public Animation getRunning() {

		return running;

	}

	@Override
	public void keyReleased(KeyEvent k) {

		int event = k.getKeyCode();

		if (event == KeyEvent.VK_RIGHT) {

			run = false;

			matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().move();

			repaint();

		}

		if (event == KeyEvent.VK_LEFT) {

			run = false;
			matchmaking.getLobby().getInitialPanel().getMainWindow().getGame().getUser().back();

		}

		if (event == KeyEvent.VK_SPACE) {

			explode = false;

		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
