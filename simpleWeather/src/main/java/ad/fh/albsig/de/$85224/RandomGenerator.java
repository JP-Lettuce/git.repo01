package ad.fh.albsig.de.$85224;

import java.util.Random;


/*
 * class RandomGenerator.
 */
public class RandomGenerator {
	private Random rand;//random tool
	/*
	 * Constructor Randomgenerator.
	 */
	public RandomGenerator() {
		this.seed();
	}
	/*
	 * public method getInt.
	 */
	public int getInt(int pmax) {
		return rand.nextInt(pmax) + 1;
	}

	public void seed() {
		long seed = System.currentTimeMillis();
		this.rand = new Random();
		rand.setSeed(seed);
	}
}
