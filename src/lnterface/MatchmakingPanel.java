package lnterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import animation.Animation;
import model.Game;
import model.Player;
import threads.MatchmakingThread;

public class MatchmakingPanel extends JPanel implements ActionListener {

	public final static Image BACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("./images/backgrounds/lobbyBackground.jpg");

	public static final Image PLAYER = Toolkit.getDefaultToolkit().createImage("./images/sprites/Skin.png");

	private LobbyPanel lobby;
	private GamePanel game;
	private JPanel aux; // JTable here
	private JPanel auxTwo; // Info Player and loading
	private DefaultTableModel modelPlayers;
	private JTable players;
	private JScrollPane scroll;
	private JButton add;
	private MatchmakingThread matchmaking;
	private int x = 1;

	public MatchmakingPanel(LobbyPanel lobby) {

		setLayout(new BorderLayout());
		this.lobby = lobby;

		game = new GamePanel(this);

		add = new JButton("add");
		add.setActionCommand("agregando");
		add.addActionListener(this);
		matchmaking = new MatchmakingThread(this);
		initAux();

	}

	public MatchmakingThread getMatchmakingThread() {

		return matchmaking;

	}

	public void initAux() {

		Border border = BorderFactory.createRaisedBevelBorder();

		JLabel match = new JLabel("Matchmaking");

		match.setHorizontalAlignment(JLabel.CENTER);
		match.setForeground(Color.WHITE);
		match.setFont(new Font("Garamond", 1, 46));

		aux = new JPanel();
		aux.setBorder(border);
		aux.setLayout(new BorderLayout());
		aux.setBackground(new Color(0, 0, 0, 85));

		JLabel widthTwo = new JLabel(
				"                                                                                                        ");
		widthTwo.setSize(500, 1);

		auxTwo = new JPanel();
//		auxTwo.setLayout(null);

		auxTwo.setBorder(border);

		auxTwo.add(widthTwo);
		auxTwo.setBackground(new Color(0, 0, 0, 60));

		auxTwo.add(add);

		playersTable();

		aux.add(scroll);
		aux.add(match, BorderLayout.NORTH);

		add(aux, BorderLayout.CENTER);
		add(auxTwo, BorderLayout.EAST);

	}

	public void playersTable() {

		String[] columns = { "Nickname", "Level", "Ping", "Platform" };

		modelPlayers = new DefaultTableModel(columns, 0);
		players = new JTable(modelPlayers);
		players.setOpaque(false);
		players.setBackground(new Color(0, 0, 0, 85));
		players.setForeground(Color.WHITE.brighter());
		players.setEnabled(false);
		players.getTableHeader().setReorderingAllowed(false);
		players.setFont(new Font("Garamond", 1, 18));

		scroll = new JScrollPane(players);
		scroll.getViewport().setBackground(new Color(0, 0, 0, 0));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Font font = new Font("Garamond", 4, 50);

		g.drawImage(BACKGROUND, 0, 0, null);
		g.drawImage(PLAYER, 600, -20, null);

		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Loading...", 1000, 670);

		Animation loading = lobby.getInitialPanel().getLoadingAnimation();

		loading.drawAnimation(g, 1030, 500, 0);

		repaint();
	}

	public DefaultTableModel getTableModel() {

		return modelPlayers;

	}

	public void matchmaking() {

		Random ran = new Random();

		Game game = lobby.getInitialPanel().getMainWindow().getGame();
		Player user = game.getUser();

		if (user.getPlatform().equals("PS4")) {

			int r = ran.nextInt(game.getPlayersPlay4().size());

			if (Math.abs(game.getPlayersPlay4().get(r).getLevel() - user.getLevel()) <= 5) {
				if (Math.abs(game.getPlayersPlay4().get(r).getPing() - user.getPing()) <= 80) {
					Object[] row = { game.getPlayersPlay4().get(r).getNickname() + ran.nextInt(9999),
							game.getPlayersPlay4().get(r).getLevel(), game.getPlayersPlay4().get(r).getPing(),
							game.getPlayersPlay4().get(r).getPlatform() };

					modelPlayers.addRow(row);

				}
			}
		}

		if (user.getPlatform().equals("NINTENDO")) {

			int r = ran.nextInt(game.getPlayersNintendo().size());

			if (Math.abs(game.getPlayersNintendo().get(r).getLevel() - user.getLevel()) <= 5) {
				if (Math.abs(game.getPlayersNintendo().get(r).getPing() - user.getPing()) <= 80) {
					Object[] row = { game.getPlayersNintendo().get(r).getNickname() + ran.nextInt(9999),
							game.getPlayersNintendo().get(r).getLevel(), game.getPlayersNintendo().get(r).getPing(),
							game.getPlayersNintendo().get(r).getPlatform() };

					modelPlayers.addRow(row);

				}
			}
		}

		if (user.getPlatform().equals("XBOX")) {

			int r = ran.nextInt(game.getPlayersXbox().size());

			if (Math.abs(game.getPlayersXbox().get(r).getLevel() - user.getLevel()) <= 5) {
				if (Math.abs(game.getPlayersXbox().get(r).getPing() - user.getPing()) <= 80) {
					Object[] row = { game.getPlayersXbox().get(r).getNickname() + ran.nextInt(9999),
							game.getPlayersXbox().get(r).getLevel(), game.getPlayersXbox().get(r).getPing(),
							game.getPlayersXbox().get(r).getPlatform() };

					modelPlayers.addRow(row);

				}
			}
		}

		if (user.getPlatform().equals("PC")) {

			int r = ran.nextInt(game.getPlayersPC().size());

			if (Math.abs(game.getPlayersPC().get(r).getLevel() - user.getLevel()) <= 5) {
				if (Math.abs(game.getPlayersPC().get(r).getPing() - user.getPing()) <= 80) {
					Object[] row = { game.getPlayersPC().get(r).getNickname() + ran.nextInt(9999),
							game.getPlayersPC().get(r).getLevel(), game.getPlayersPC().get(r).getPing(),
							game.getPlayersPC().get(r).getPlatform() };

					modelPlayers.addRow(row);

				}
			}
		}

//		Object[] row = {
//				game.getPlayersOnline().get(ran.nextInt(game.getPlayersOnline().size())).getNickname()
//						+ ran.nextInt(9999),
//				game.getPlayersOnline().get(ran.nextInt(game.getPlayersOnline().size())).getLevel(),
//				game.getPlayersOnline().get(ran.nextInt(game.getPlayersOnline().size())).getPing(),
//				game.getPlayersOnline().get(ran.nextInt(game.getPlayersOnline().size())).getPlatform() };
//
//		modelPlayers.addRow(row);

	}

	public void matchmakingUser() {

		Player player = lobby.getInitialPanel().getMainWindow().getGame().getUser();

		Object[] user = { player.getNickname(), player.getLevel(), player.getPing(), player.getPlatform() };

		modelPlayers.addRow(user);

	}

	public LobbyPanel getLobby() {

		return lobby;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals("agregando")) {

			lobby.getInitialPanel().getMainWindow().remove(this);
			lobby.getInitialPanel().getMainWindow().add(game);
			lobby.getInitialPanel().getMainWindow().refresh();

			JOptionPane.showMessageDialog(null, "Welcome to Fortnite", "Play", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("./images/logos/logo.jpg"));
			JOptionPane.showMessageDialog(null, "To pick the weapons just press C \nTo shoot just press SPACE",
					"Instructions", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./images/logos/logo.jpg"));
		}

	}

}
