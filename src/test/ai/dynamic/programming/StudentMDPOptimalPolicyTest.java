package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MarkovDecisionProcessPolicyOptimal;

public class StudentMDPOptimalPolicyTest {

	private final static int sizeS = 5;
	private final static int sizeA = 5;
	private final String[] states = new String[sizeS];
	private final String[] actions = new String[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private final double[][] policy = new double[sizeS][sizeA];
	private static MarkovDecisionProcessPolicyOptimal<String, String> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		markovDecisionProcess = new MarkovDecisionProcessPolicyOptimal<String, String>(sizeS, sizeA);

		markovDecisionProcess.setState(0, "C1");
		markovDecisionProcess.setState(1, "C2");
		markovDecisionProcess.setState(2, "C3");
		markovDecisionProcess.setState(3, "FB");
		markovDecisionProcess.setState(4, "SL");

		markovDecisionProcess.setAction(0, "ST");
		markovDecisionProcess.setAction(1, "PB");
		markovDecisionProcess.setAction(2, "FB");
		markovDecisionProcess.setAction(3, "QU");
		markovDecisionProcess.setAction(4, "SL");

		markovDecisionProcess.setProbablilityStateStatePrime(0, 0, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(0, 2, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 0, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 4, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 0, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 1, 0, 0.2d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 1, 1, 0.4d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 1, 2, 0.4d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 3, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 2, 3, 1.0d);

		markovDecisionProcess.setReward(0, 0, -2.0d);
		markovDecisionProcess.setReward(0, 2, -1.0d);
		markovDecisionProcess.setReward(1, 0, -2.0d);
		markovDecisionProcess.setReward(1, 4,  0.0d);
		markovDecisionProcess.setReward(2, 0, 10.0d);
		markovDecisionProcess.setReward(2, 1,  1.0d);
		markovDecisionProcess.setReward(3, 2, -1.0d);
		markovDecisionProcess.setReward(3, 3,  0.0d);

		markovDecisionProcess.setPolicy(0, 0, 0.5d);
		markovDecisionProcess.setPolicy(0, 2, 0.5d);
		markovDecisionProcess.setPolicy(1, 0, 0.5d);
		markovDecisionProcess.setPolicy(1, 4, 0.5d);
		markovDecisionProcess.setPolicy(2, 0, 0.5d);
		markovDecisionProcess.setPolicy(2, 1, 0.5d);
		markovDecisionProcess.setPolicy(3, 3, 0.5d);
		markovDecisionProcess.setPolicy(3, 2, 0.5d);
	}

	@Test
	public void testStates() {
		states[0] = "C1";
		states[1] = "C2";
		states[2] = "C3";
		states[3] = "FB";
		states[4] = "SL";
		Assert.assertArrayEquals(states, markovDecisionProcess.getStates());
	}

	@Test
	public void testActions() {
		actions[0] = "ST";
		actions[1] = "PB";
		actions[2] = "FB";
		actions[3] = "QU";
		actions[4] = "SL";
		Assert.assertArrayEquals(actions, markovDecisionProcess.getActions());
	}

	@Test
	public void testTPM() {
		stateTPM[0][0][1] = 1.0d;
		stateTPM[0][2][3] = 1.0d;
		stateTPM[1][0][2] = 1.0d;
		stateTPM[1][4][4] = 1.0d;
		stateTPM[2][0][4] = 1.0d;
		stateTPM[2][1][0] = 0.2d;
		stateTPM[2][1][1] = 0.4d;
		stateTPM[2][1][2] = 0.4d;
		stateTPM[3][3][0] = 1.0d;
		stateTPM[3][2][3] = 1.0d;

		for (int state=0; state<sizeS; state++) {
			for (int action=0; action<sizeA; action++) {
				Assert.assertArrayEquals(stateTPM[state][action], markovDecisionProcess.getStateTransitionProbabilityMatrix()[state][action], 0.001d);
				double sum = 0.0d;
				for (int i=0; i<sizeS; i++) {
					sum += stateTPM[state][action][i];
				}
				Assert.assertTrue(sum == 1.0d || sum == 0.0d); //0.000001d
			}
		}
	}

	@Test
	public void testRewards() {
		rewards[0][0] = -2.0d;
		rewards[0][2] = -1.0d;
		rewards[1][0] = -2.0d;
		rewards[1][4] =  0.0d;
		rewards[2][0] = 10.0d;
		rewards[2][1] =  1.0d;
		rewards[3][2] = -1.0d;
		rewards[3][3] =  0.0d;

		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(rewards[state], markovDecisionProcess.getRewards()[state], 0.001d);
		}
	}

	@Test
	public void testPolicies() {
		policy[0][0] = 0.5d;
		policy[0][2] = 0.5d;
		policy[1][0] = 0.5d;
		policy[1][4] = 0.5d;
		policy[2][0] = 0.5d;
		policy[2][1] = 0.5d;
		policy[3][3] = 0.5d;
		policy[3][2] = 0.5d;

		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(policy[state], markovDecisionProcess.getPolicy()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<sizeA; i++) {
				sum += policy[state][i];
			}
			if (state != 4) Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}

	@Test
	public void testDiscount00Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals(-1.5d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 5.5d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(-0.5d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount05Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-1.68d, markovDecisionProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( 0.52d, markovDecisionProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 6.08d, markovDecisionProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(-1.23d, markovDecisionProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount09Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals(-1.48d, markovDecisionProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( 2.16d, markovDecisionProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 7.02d, markovDecisionProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(-2.12d, markovDecisionProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount10Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(-1.3d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 2.7d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 7.4d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(-2.3d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount00Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals(-1.5d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 5.5d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(-0.5d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount05Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-1.68d, markovDecisionProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( 0.52d, markovDecisionProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 6.08d, markovDecisionProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(-1.23d, markovDecisionProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount09Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals(-1.48d, markovDecisionProcess.getStateValue("C1"), 0.01d);
		Assert.assertEquals( 2.16d, markovDecisionProcess.getStateValue("C2"), 0.01d);
		Assert.assertEquals( 7.02d, markovDecisionProcess.getStateValue("C3"), 0.01d);
		Assert.assertEquals(-2.12d, markovDecisionProcess.getStateValue("FB"), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.01d);
	}

	@Test
	public void testDiscount10Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(-1.3d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 2.7d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals( 7.4d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals(-2.3d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);
	}

	@Test
	public void testDiscount00Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);

		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue("C1", "ST"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue("C1", "FB"), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue("C2", "ST"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("C2", "SL"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getActionValue("C3", "ST"), 0.1d);
		Assert.assertEquals( 1.0d, markovDecisionProcess.getActionValue("C3", "PB"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue("FB", "FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("FB", "QU"), 0.1d);
	}

	@Test
	public void testDiscount05Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 3.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);

		Assert.assertEquals(-0.5d, markovDecisionProcess.getActionValue("C1", "ST"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue("C1", "FB"), 0.1d);
		Assert.assertEquals( 3.0d, markovDecisionProcess.getActionValue("C2", "ST"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("C2", "SL"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getActionValue("C3", "ST"), 0.1d);
		Assert.assertEquals( 3.6d, markovDecisionProcess.getActionValue("C3", "PB"), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue("FB", "FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("FB", "QU"), 0.1d);
	}

	@Test
	public void testDiscount09Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals( 4.3d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 7.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 3.9d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);

		Assert.assertEquals( 4.3d, markovDecisionProcess.getActionValue("C1", "ST"), 0.1d);
		Assert.assertEquals( 2.5d, markovDecisionProcess.getActionValue("C1", "FB"), 0.1d);
		Assert.assertEquals( 7.0d, markovDecisionProcess.getActionValue("C2", "ST"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("C2", "SL"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getActionValue("C3", "ST"), 0.1d);
		Assert.assertEquals( 7.9d, markovDecisionProcess.getActionValue("C3", "PB"), 0.1d);
		Assert.assertEquals( 2.5d, markovDecisionProcess.getActionValue("FB", "FB"), 0.1d);
		Assert.assertEquals( 3.9d, markovDecisionProcess.getActionValue("FB", "QU"), 0.1d);
	}

	@Test
	public void testDiscount10Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 6.0d, markovDecisionProcess.getStateValue("C1"), 0.1d);
		Assert.assertEquals( 8.0d, markovDecisionProcess.getStateValue("C2"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getStateValue("C3"), 0.1d);
		Assert.assertEquals( 6.0d, markovDecisionProcess.getStateValue("FB"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue("SL"), 0.1d);

		Assert.assertEquals( 6.0d, markovDecisionProcess.getActionValue("C1", "ST"), 0.1d);
		Assert.assertEquals( 5.0d, markovDecisionProcess.getActionValue("C1", "FB"), 0.1d);
		Assert.assertEquals( 8.0d, markovDecisionProcess.getActionValue("C2", "ST"), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue("C2", "SL"), 0.1d);
		Assert.assertEquals(10.0d, markovDecisionProcess.getActionValue("C3", "ST"), 0.1d);
		Assert.assertEquals( 9.4d, markovDecisionProcess.getActionValue("C3", "PB"), 0.1d);
		Assert.assertEquals( 5.0d, markovDecisionProcess.getActionValue("FB", "FB"), 0.1d);
		Assert.assertEquals( 6.0d, markovDecisionProcess.getActionValue("FB", "QU"), 0.1d);
	}
}
