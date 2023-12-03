import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

//--------------------------------------------------------------------------------------------
public class itSupportMain {
	
	private static KeyBuffer buffer;
	
	public static void main(String[] args) {
		
		
		//bord groesse 
		BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR,15);
		BoardController boardController = BoardController.getBoardController();
		
		//Erstellt Spieler und Kugel Objekt
		Bullet bulletPlayer = new Bullet(boardController,10,19);//Bullet object von player
		Player thePlayer = new Player(boardController,10,19,bulletPlayer);//player object
		System.out.println("Current player health = " + thePlayer.getHealth());
		//-------------------------------------------------------------------------------
		
		//Liste von Gegner und Kugel Objekte 
		List<Enemy> enemies = new LinkedList<>();
		List<Bullet> enemyBullets = new LinkedList<>();
		//--------------------------------------------------------------------------------
		
		//Erstellt Gegner und Kugel Objekt, und fuegt die Objekte in die jeweiligen Listen
		
		//Gegner 1 Startposition
		Bullet bulletOne = new Bullet(boardController,10,0);
		Enemy enemyOne = new Enemy(boardController,10,0,bulletOne);
		enemies.add(enemyOne);
		enemyBullets.add(bulletOne);
		
		//Gegner 2 Startposition
		Bullet bulletTwo = new Bullet(boardController,5,2);
		Enemy enemyTwo = new Enemy(boardController,5,2,bulletTwo);
		enemies.add(enemyTwo);
		enemyBullets.add(bulletTwo);
		
		//Gegner 3 Startposition
		Bullet bulletThree = new Bullet(boardController,13,4);
		Enemy enemyThree = new Enemy(boardController,13,4,bulletThree);
		enemies.add(enemyThree);
		enemyBullets.add(bulletThree);
		
		//Gegner 4 Startposition
		Bullet bulletFour = new Bullet(boardController,3,6);//
		Enemy enemyFour = new Enemy(boardController,3,6,bulletFour);
		enemies.add(enemyFour);
		enemyBullets.add(bulletFour);
		
		//Gegner 5 Startposition
		Bullet bulletFive = new Bullet(boardController,8,8);
		Enemy enemyFive = new Enemy(boardController,8,8,bulletFive);
		enemies.add(enemyFive);
		enemyBullets.add(bulletFive);
		

		//-------------------------------------------------------------------------------------------------------
		
		EndScreen endScreen = new EndScreen(boardController);//Erstellt Sieger/Verlierer Screen 
		
		boolean end = false;//false Spiel läuft weiter, true Spiel wurde beendet
		byte win = -1;//0 =verloren, 1 =gewonnen, -1 Spiel laeuft weiter
		buffer = boardController.getKeyBuffer();
		
		while(true) {//Spiel Schleife 
			if(!end) {
				boardController.resetColors();
				int whichEnemy = 0;
				
				if(enemies.size() == 1)
					whichEnemy = 0;
				else 
					whichEnemy = RandomNumberGenerator.getRandomBetween(0, enemies.size());
				
				int willShoot = RandomNumberGenerator.getRandomBetween(0, 10000);//entscheidet wann ein Gegner angreift 
				
				KeyEvent event = buffer.pop();
				if(event != null) {
					if(event.getID() == java.awt.event.KeyEvent.KEY_PRESSED) {
						switch(event.getKeyCode()) {
						case java.awt.event.KeyEvent.VK_RIGHT://Spieler Steuerung rechts
							thePlayer.setDirectionHorizontal((byte) 1);
							thePlayer.moveHorizontally();
							break;
							
						case java.awt.event.KeyEvent.VK_LEFT: //Spieler Steuerung links
							thePlayer.setDirectionHorizontal((byte)0);
							thePlayer.moveHorizontally();
							break;
						
						case java.awt.event.KeyEvent.VK_SPACE://Spieler Schuss 
							if(thePlayer.shootable) {
								System.out.println("shooting");
								thePlayer.shoot();
							}
							break;
						}
					}
				}
				thePlayer.create();//Spieler wird Erstellt 
				if(!thePlayer.shootable) {
					bulletPlayer.move(true);//!player.shootable cooldown des Spielers
					bulletPlayer.draw(true);
				}
				
				for(int i = 0; i < enemies.size(); i++) {//Gegner Bewegung/ Kugel Ablauf
					if(enemies.get(i).getPosition().getPositionX() + 2 > boardController.getWidth()) {
						enemies.get(i).setDirectionHorizontal((byte) 0);//Richtugswechsel falls das ende des LED Boards ereicht wurde
					}
					else if(enemies.get(i).getPosition().getPositionX() - 2 < 0) {//Richtugswechsel falls das Ende des LED Boards ereicht wurde
						enemies.get(i).setDirectionHorizontal((byte) 1);
					}
					enemies.get(i).moveHorizontally();
					enemies.get(i).create();
					if(enemies.get(i).shootable && i == whichEnemy && willShoot == 50) {//Ueberprüft ob die Eigenschaft "shootable" des aktuellen Gegners wahr ist 
						enemies.get(i).shoot();											//und ob der aktuelle Gegner der Gegner ist, der angreifen soll
					}																	
					else {
						enemyBullets.get(i).move(false);
						enemyBullets.get(i).draw(false);
					}
					if(enemyBullets.get(i).getPosition().getPositionY() > boardController.getHeight()) {//Ueberprüft ob die y-Position der Gegner Kugel größer als 
						enemies.get(i).refreshBullet();											   //die Höhe des LED-Boards ist
					}
					else if(enemyBullets.get(i).hitPlayer(thePlayer)) {//falls man getroffen wird 
						thePlayer.reduceHealth();//leben wird reduziert falls man getroffen wird 
						System.out.println("Current player health = " + thePlayer.getHealth());
						if(thePlayer.getHealth() == 0) {//Wenn spieler kein Leben mehr hat 
							end = true;//Spiel wird beendet
							win = 0;//Spiel wird auf verloren gesetzt
							boardController.resetColors();//LED Board wird gelöscht
						}
						else {
							enemies.get(i).refreshBullet();//kugel wird zuruckgesetzt falls kein gegner getroffen wurde
						}
					}
					
				}
				int wasHit = checkIfEnemyHit(enemies,bulletPlayer);//Gibt den Index des Gegners in der Liste wieder, falls nicht dann MAX_VALUE
				if(wasHit != Integer.MAX_VALUE) {//Ueberprueft ob ein Gegner getroffen wurde
					thePlayer.refreshBullet();//Kugel wird zurueckgesetzt
					enemies.remove(wasHit);//Gegner wird entfernt 
					enemyBullets.remove(wasHit);//Gegner Kugel wird entfernt
					if(enemies.size() == 0) {// Ueberprueft ob es noch Gegner gibt
						end = true;//Spiel wird beendet 
						win = 1;//Spiel Wurde Gewonnen
						boardController.resetColors();
					}
				}
				else if(bulletPlayer.getPosition().getPositionY() < 0){
					thePlayer.refreshBullet();//Kugel wir zuruckgesetzt falls kein Gegner getroffen wurde 
				}

				boardController.updateBoard();
			}
			else {//wenn Spiel Endet
				if(win == (byte) 1) {
					boardController.resetColors();
					endScreen.drawWin();//Sieges Bild (Smile)
					boardController.updateBoard();
					boardController.sleep(5000);//nach 5sec wird Main neu gestartet
					itSupportMain.main(null);
					break;
					
				}
				else if(win == (byte) 0) {
					boardController.resetColors();
					endScreen.drawLose();//Verlierer Bild (Skull)
					boardController.updateBoard();
					boardController.sleep(5000);//nach 5sec wird Main neu gestartet
					itSupportMain.main(null);
					break;
				}
			}
		}

	}
	//Ende Spiel Schleife
	//--------------------------------------------------------------------------------------------------------------------
	
	public static int checkIfEnemyHit(List<Enemy> enemies,Bullet playerBullet) {//gibt index von getroffen Gegner wieder
		for(int i = 0; i<enemies.size(); i++) {
			if(playerBullet.hitEnemy(enemies.get(i))) {
				System.out.println(i);
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}
}
