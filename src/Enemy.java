
import ledControl.BoardController;

public class Enemy extends Animation implements Animations{
	
	
	public Enemy(BoardController controller,int startX, int startY, Bullet bullet) {
		super(controller,startX,startY,bullet);
		this.speed = 1;
	}
	
	@Override
	public void moveHorizontally() {//Horizontale Bewegungen 
		// TODO Auto-generated method stub
		if(this.directionHorizontal == 0) {
			this.position.setPositionX(this.position.getPositionX() - this.speed);
		}
		else if(this.directionHorizontal == 1) {
			this.position.setPositionX(this.position.getPositionX() + this.speed);
		}
	}

	 

	@Override
	public void create() {//Rendert und Erstellt Gegner 
		int x = (int) this.position.getPositionX();
	    int y = (int) this.position.getPositionY();

	    controller.setColor(x - 1, y, ColorHolder.red);
	    controller.setColor(x, y, ColorHolder.red);
	    controller.setColor(x + 1, y, ColorHolder.red);
	    controller.setColor(x, y + 1, ColorHolder.red);
		
	}
	
	
	
}
