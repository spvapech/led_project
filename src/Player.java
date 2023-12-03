import ledControl.BoardController;

public class Player extends Animation implements Animations{
	
	private int health = 5;
	
	
		public Player(BoardController controller, int startX, int startY, Bullet bullet) {
			super(controller,startX,startY,bullet);
			this.speed = 1;
		}
		
		@Override
		public void moveHorizontally() {// Horizontale bewegungen des Spielers
			// TODO Auto-generated method stub
				  if (directionHorizontal == 0 && position.getPositionX() - 2 > 0) {
				    position.setPositionX(position.getPositionX() - speed);
				    if (shootable) {
				      bullet.setPosition(position); // kugel bewegung wenn nicht geschossen wird 
				    }
				  } else if (directionHorizontal == 1 && position.getPositionX() + 2 < controller.getWidth()) {
				    position.setPositionX(position.getPositionX() + speed);
				    if (shootable) {
				      bullet.setPosition(position); // kugel bewegung wenn nicht geschossen wird 
				    }
				  }
				}
		
		public int getHealth() {
			return health;
		}

		public void setHealth(int health) {
			this.health = health;
		}
		
		public void reduceHealth() {// redutiert Spieler Leben um 1 
			this.setHealth(this.getHealth()-1);
		}
		
		@Override
		public void create() {//Spieler Farben
			// TODO Auto-generated method stub

				if(getHealth()==1) {//Zeigt letztes Leben an 
					controller.setColor((int)position.getPositionX() - 1,(int)position.getPositionY() , ColorHolder.red);
					controller.setColor((int)position.getPositionX(),(int) position.getPositionY(), ColorHolder.red);
					controller.setColor((int)position.getPositionX() + 1,(int)position.getPositionY() , ColorHolder.red);
					controller.setColor((int)position.getPositionX(),(int) position.getPositionY()-1, ColorHolder.green);
				}
					else {
						controller.setColor((int)position.getPositionX() - 1,(int)position.getPositionY() , ColorHolder.green);
						controller.setColor((int)position.getPositionX(),(int) position.getPositionY(), ColorHolder.green);
						controller.setColor((int)position.getPositionX() + 1,(int)position.getPositionY() , ColorHolder.green);
						controller.setColor((int)position.getPositionX(),(int) position.getPositionY()-1, ColorHolder.green);				
		   }
		 }
		
		
}
		
