
public class Position implements Comparable<Position> {
	private double positionX;
	private double positionY;
	

	public Position(double x,double y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	

	@Override
	public int compareTo(Position o) {//vergleicht mit anderen positionen von opjekten
		// TODO Auto-generated method stub
		if(o.positionX == this.positionX && o.positionY == this.positionY) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
}
