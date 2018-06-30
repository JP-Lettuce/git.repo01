package ad.fh.albsig.de.$85224;

import java.util.Random;

import org.junit.Test;

public class RandomGeneratorTest {

	private Random rand;
	
	public RandomGeneratorTest() {
		this.seed();
	}
	public int getInt(int pmax) {
		return rand.nextInt(pmax) + 1;
	}
	@Test
	public void seed() {
		long seed = System.currentTimeMillis();
		this.rand = new Random();
		rand.setSeed(seed);
	}
}
