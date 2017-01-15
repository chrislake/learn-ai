package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MarkovRewardProcess;

public class StudentMRPTest {

	private final static int size = 7;
	private final String[] states = new String[size];
	private final double[][] stateTPM = new double[size][size];
	private final double[] rewards = new double[size];
	private static MarkovRewardProcess<String> markovRewardProcess;

	@BeforeClass
	public static void generateMarkovRewardProcess() {
		markovRewardProcess = new MarkovRewardProcess<String>(size);

		markovRewardProcess.setState(0, "C1");
		markovRewardProcess.setState(1, "C2");
		markovRewardProcess.setState(2, "C3");
		markovRewardProcess.setState(3, "PS");
		markovRewardProcess.setState(4, "PB");
		markovRewardProcess.setState(5, "FB");
		markovRewardProcess.setState(6, "SL");

		markovRewardProcess.setProbablilityStateStatePrime(0, 1, 0.5d);
		markovRewardProcess.setProbablilityStateStatePrime(0, 5, 0.5d);
		markovRewardProcess.setProbablilityStateStatePrime(1, 2, 0.8d);
		markovRewardProcess.setProbablilityStateStatePrime(1, 6, 0.2d);
		markovRewardProcess.setProbablilityStateStatePrime(2, 3, 0.6d);
		markovRewardProcess.setProbablilityStateStatePrime(2, 4, 0.4d);
		markovRewardProcess.setProbablilityStateStatePrime(3, 6, 1.0d);
		markovRewardProcess.setProbablilityStateStatePrime(4, 0, 0.2d);
		markovRewardProcess.setProbablilityStateStatePrime(4, 1, 0.4d);
		markovRewardProcess.setProbablilityStateStatePrime(4, 2, 0.4d);
		markovRewardProcess.setProbablilityStateStatePrime(5, 0, 0.1d);
		markovRewardProcess.setProbablilityStateStatePrime(5, 5, 0.9d);
		markovRewardProcess.setProbablilityStateStatePrime(6, 6, 1.0d);

		markovRewardProcess.setReward(0, -2.0d);
		markovRewardProcess.setReward(1, -2.0d);
		markovRewardProcess.setReward(2, -2.0d);
		markovRewardProcess.setReward(3, 10.0d);
		markovRewardProcess.setReward(4,  1.0d);
		markovRewardProcess.setReward(5, -1.0d);
		markovRewardProcess.setReward(6,  0.0d);
	}

	@Test
	public void testStates() {
		states[0] = "C1";
		states[1] = "C2";
		states[2] = "C3";
		states[3] = "PS";
		states[4] = "PB";
		states[5] = "FB";
		states[6] = "SL";
		Assert.assertArrayEquals(states, markovRewardProcess.getStates());
	}

	@Test
	public void testTPM() {
		stateTPM[0][1] = 0.5d;
		stateTPM[0][5] = 0.5d;
		stateTPM[1][2] = 0.8d;
		stateTPM[1][6] = 0.2d;
		stateTPM[2][3] = 0.6d;
		stateTPM[2][4] = 0.4d;
		stateTPM[3][6] = 1.0d;
		stateTPM[4][0] = 0.2d;
		stateTPM[4][1] = 0.4d;
		stateTPM[4][2] = 0.4d;
		stateTPM[5][0] = 0.1d;
		stateTPM[5][5] = 0.9d;
		stateTPM[6][6] = 1.0d;

		for (int state=0; state<size; state++) {
			Assert.assertArrayEquals(stateTPM[state], markovRewardProcess.getStateTransitionProbabilityMatrix()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<size; i++) {
				sum += stateTPM[state][i];
			}
			Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}

	@Test
	public void testRewards() {
		rewards[0] = -2.0d;
		rewards[1] = -2.0d;
		rewards[2] = -2.0d;
		rewards[3] = 10.0d;
		rewards[4] =  1.0d;
		rewards[5] = -1.0d;
		rewards[6] =  0.0d;
		Assert.assertArrayEquals(rewards, markovRewardProcess.getRewards(), 0.001d);
	}
	
	@Test
	public void testEpisode1() {
		String[] episode1 = new String[5];
		episode1[0] = "C1";
		episode1[1] = "C2";
		episode1[2] = "C3";
		episode1[3] = "PS";
		episode1[4] = "SL";

		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-2.25d, markovRewardProcess.getExpectedReturn(episode1, 0), 0.01d);
	}

	@Test
	public void testEpisode2() {
		String[] episode2 = new String[6];
		episode2[0] = "C1";
		episode2[1] = "FB";
		episode2[2] = "FB";
		episode2[3] = "C1";
		episode2[4] = "C2";
		episode2[5] = "SL";

		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-3.125d, markovRewardProcess.getExpectedReturn(episode2, 0), 0.001d);
	}

	@Test
	public void testEpisode3() {
		String[] episode3 = new String[8];
		episode3[0] = "C1";
		episode3[1] = "C2";
		episode3[2] = "C3";
		episode3[3] = "PB";
		episode3[4] = "C2";
		episode3[5] = "C3";
		episode3[6] = "PS";
		episode3[7] = "SL";

		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-3.41d, markovRewardProcess.getExpectedReturn(episode3, 0), 0.01d);
	}

	@Test
	public void testEpisode4() {
		String[] episode4 = new String[17];
		episode4[0] = "C1";
		episode4[1] = "FB";
		episode4[2] = "FB";
		episode4[3] = "C1";
		episode4[4] = "C2";
		episode4[5] = "C3";
		episode4[6] = "PB";
		episode4[7] = "C1";
		episode4[8] = "FB";
		episode4[9] = "FB";
		episode4[10] = "FB";
		episode4[11] = "C1";
		episode4[12] = "C2";
		episode4[13] = "C3";
		episode4[14] = "PB";
		episode4[15] = "C2";
		episode4[16] = "SL";

		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-3.20d, markovRewardProcess.getExpectedReturn(episode4, 0), 0.01d);
	}
	
	@Test
	public void testDiscount00Matrix() {
		markovRewardProcess.setUseBellmanMatrix(true);
		markovRewardProcess.setDiscountFactor(0.0d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals( 1.0d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-1.0d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount05Matrix() {
		markovRewardProcess.setUseBellmanMatrix(true);
		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( -2.91d, markovRewardProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( -1.55d, markovRewardProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 1.12d, markovRewardProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(10.00d, markovRewardProcess.getStateValue("PS"), 0.01d);
		Assert.assertEquals( 0.62d, markovRewardProcess.getStateValue("PB"), 0.01d);
		Assert.assertEquals(-2.08d, markovRewardProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount09Matrix() {
		markovRewardProcess.setUseBellmanMatrix(true);
		markovRewardProcess.setDiscountFactor(0.9d);
		Assert.assertEquals(-5.0d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 0.9d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 4.1d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals( 1.9d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-7.6d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount10Matrix() {
		markovRewardProcess.setUseBellmanMatrix(true);
		markovRewardProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(-13.0d, markovRewardProcess.getStateValue("C1"), 1.0d); //slides
		Assert.assertEquals(-12.5d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(  1.5d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(  4.3d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals(  0.8d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-23.0d, markovRewardProcess.getStateValue("FB"), 1.0d); //slides
		Assert.assertEquals(-22.5d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( -0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount00Loop() {
		markovRewardProcess.setUseBellmanMatrix(false);
		markovRewardProcess.setDiscountFactor(0.0d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(-2.0d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals( 1.0d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-1.0d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount05Loop() {
		markovRewardProcess.setUseBellmanMatrix(false);
		markovRewardProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( -2.91d, markovRewardProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( -1.55d, markovRewardProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 1.12d, markovRewardProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(10.00d, markovRewardProcess.getStateValue("PS"), 0.01d);
		Assert.assertEquals( 0.62d, markovRewardProcess.getStateValue("PB"), 0.01d);
		Assert.assertEquals(-2.08d, markovRewardProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount09Loop() {
		markovRewardProcess.setUseBellmanMatrix(false);
		markovRewardProcess.setDiscountFactor(0.9d);
		Assert.assertEquals(-5.0d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 0.9d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 4.1d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals( 1.9d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-7.6d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount10Loop() {
		markovRewardProcess.setUseBellmanMatrix(false);
		markovRewardProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(-13.0d, markovRewardProcess.getStateValue("C1"), 1.0d); //slides
		Assert.assertEquals(-12.5d, markovRewardProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(  1.5d, markovRewardProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(  4.3d, markovRewardProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 10.0d, markovRewardProcess.getStateValue("PS"), 0.1d);
		Assert.assertEquals(  0.8d, markovRewardProcess.getStateValue("PB"), 0.1d);
		Assert.assertEquals(-23.0d, markovRewardProcess.getStateValue("FB"), 1.0d); //slides
		Assert.assertEquals(-22.5d, markovRewardProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( -0.0d, markovRewardProcess.getStateValue("SL"), 0.1d);
	}
}
