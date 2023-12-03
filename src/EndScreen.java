import ledControl.BoardController;

public class EndScreen {
	BoardController controller;
	public EndScreen(BoardController controller) {
		this.controller = controller;
	}
	
	public void drawWin() {//zeichnet Siger Bild (smile)
		drawEye(6,4);
		drawEye(12,4);  
 		drawSmilePart1(8,13);
		drawSmilePart2(13,11);
		drawSmilePart2(6,11);


	}
	
	public void drawLose() {//zeichnet verlierer Bild (skull)
		drawEye(6,4);
		drawEye(12,4);
		drawMouth(8,10);
		drawHeadPart1(4,3);
		drawHeadPart2(16,3);	
		drawHedpart3(4,11);
		drawHedpart4(12,11);
	}
	
	public void drawMouth(int x, int y) {
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0; j< 7; j++) {
				if(i == 0) {
					controller.setColor(x + i, y + j, ColorHolder.white);
				}
				else if(i == 1){
					controller.setColor(x + i + 1, y + j, ColorHolder.white);
				}
				else {
					controller.setColor(x + i + 2, y + j, ColorHolder.white);
				}
			}
		}
	}

	public void drawEye(int x, int y) {
		for(int i = 0; i< 3;i+=2) {
			for(int j = 0; j<3;j++) {
				controller.setColor(x + i, y + j, ColorHolder.white);
			}
		}
		controller.setColor(x + 1, y, ColorHolder.white);
		controller.setColor(x + 1, y + 2, ColorHolder.white);
	}

	public void drawHeadPart1(int x, int y) {
		for(int i = 0; i< 13;i++) {
			for(int j = 0; j<9;j++) {
				controller.setColor(4,y + j, ColorHolder.white);
				controller.setColor(x + i,2, ColorHolder.white);
			}
		}
	}
	
	
	public void drawHeadPart2(int x, int y) {
		for(int i = 0; i< 9; i++) {
				controller.setColor(x, y + i, ColorHolder.white);
		}
	}
	
	public void drawHedpart3(int x, int y) {//draws Line
		for(int i = 0; i<4;i++) {
			controller.setColor(x+i, y, ColorHolder.white);
		}
	}
	
	public void drawHedpart4(int x, int y) {//draws Line
		for(int i = 0; i<4;i++) {
			controller.setColor(x+i, y, ColorHolder.white);
		}
	}
	public void drawSmilePart1(int x, int y) {
		for(int i =0;i<5;i++){
			controller.setColor(x+i, y, ColorHolder.white);
		}
	}
	public void drawSmilePart2(int x, int y) {
		for(int i =0;i<2;i++){
			controller.setColor(x+i, y, ColorHolder.white);
		}
}
}
