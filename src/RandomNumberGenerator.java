import java.util.Random;
//zufalls Zahl Generator 
public class RandomNumberGenerator {

	public static int getRandomBetween(int lowerBoundary, int upperBoundary) {
		
		Random random = new Random();
		
		return lowerBoundary + random.nextInt(upperBoundary-lowerBoundary +1);
		
	}
}
