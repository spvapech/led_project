   import ledControl.BoardController;

public class Bullet {
	
	private final BoardController controller;
	private final Position position;
	private final float speed = 1f;
	
	public Bullet(BoardController controller, int startX, int startY) {
		this.controller = controller;
		this.position = new Position(startX, startY);
	}
	
	public void move(boolean isPlayer) {
		if (isPlayer) {
			position.setPositionY(position.getPositionY() - speed);
		} else {
			position.setPositionY(position.getPositionY() + speed);
		}
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position pos) {
		this.position.setPositionX(pos.getPositionX());
		this.position.setPositionY(pos.getPositionY());
	}
	
	public boolean hitEnemy(Enemy e) { //Ueberprueft ob Gegner getroffen wurde
		for (int i = 0; i < 3; i++) {
			if (this.position.getPositionX() == e.getPosition().getPositionX() + i &&
				this.position.getPositionY() == e.getPosition().getPositionY()) {
				System.out.println("Hit !");
				return true;
			}
		}
		return false;
	}
	
	public boolean hitPlayer(Player p) { //Ueberprueft ob Spieler getroffen wurde
		for (int i = 0; i < 3; i++) {
			if (this.position.getPositionX() == p.getPosition().getPositionX() + i &&
				this.position.getPositionY() == p.getPosition().getPositionY()) {
				System.out.println("Hit !");
				return true;
			}
		}
		return false;
	}
	
	public void draw(boolean isPlayer) {
		if (isPlayer) {
			controller.setColor((int) position.getPositionX(), (int) position.getPositionY(), ColorHolder.green);
		} else {
			controller.setColor((int) position.getPositionX(), (int) position.getPositionY(), ColorHolder.purple);
		}
	}
}
