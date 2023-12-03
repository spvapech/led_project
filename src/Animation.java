import ledControl.BoardController;

public class Animation {

  BoardController controller;
  protected Bullet bullet;
  protected Position position;
  protected byte directionHorizontal, directionVertical;
  protected boolean shootable = true; 
  protected boolean shooted = false; 
  protected int speed;

  public Animation(BoardController controller, int startX, int startY, Bullet bullet) {
    this.controller = controller;
    this.position = new Position(startX, startY);
    this.bullet = bullet;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public void setBullet(Bullet bullet) {
    this.bullet = bullet;
  }

  public byte getDirectionHorizontal() {
    return directionHorizontal;
  }

  public void setDirectionHorizontal(byte directionHorizontal) {
    this.directionHorizontal = directionHorizontal;
  }
  
  public Position getPosition() {
	    return position;
	  }

  public void setPosition(Position position) {
	    this.position = position;
	  }
  
  public byte getDirectionVertical() {
    return directionVertical;
  }

  public void setDirectionVertical(byte directionVertical) {
    this.directionVertical = directionVertical;
  }

  public void shoot() { 
    if (shootable) {
      shootable = false;
      shooted = true;
      System.out.println("shooted!");
    } else {
      System.out.println("Can't shoot yet !");
    }
  }
  
  public void refreshBullet() { 
	    this.shootable = true;
	    this.shooted = false;
	    this.bullet.setPosition(this.position);
	  }
}