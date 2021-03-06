package model;

import java.util.Random;

import dataStructures.Stack;

public class Player {

	public static final String[] PLATFORMS = { "PS4", "XBOX", "PC", "NINTENDO" };

	private int ping;
	private String nickname;
	private int level;
	private Stack<Weapon> weapons;
	private int x;
	private int y;
	private String platform;
	private Random ran = new Random();

	public Player(String nickname) {

		this.nickname = nickname;
		platform = PLATFORMS[ran.nextInt(PLATFORMS.length)];

		level = ran.nextInt((101 - 1) + 1);
		ping = ran.nextInt((151 - 1) + 1);

		weapons = new Stack<Weapon>(50, true);
		
		Weapon axe = new Weapon(Weapon.NAMES[0]);
		axe.setAmmo(99999);
		
		pickWeapon(axe);

		x = 10;
		y = 500;

	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void pickWeapon(Weapon weapon) {

		weapons.push(weapon);

	}

	public void dropWeapon() {

		weapons.pop();

	}
	
	
	public Weapon getActualWeapon() {
		
	return weapons.peek();	
		
	}

	public void move() {

		setX(x + 5);

	}

	public void back() {

		setX(x - 5);

	}

	public int getPing() {
		return ping;
	}

	public void setPing(int ping) {
		this.ping = ping;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Stack<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(Stack<Weapon> weapons) {
		this.weapons = weapons;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
