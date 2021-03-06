package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MDPPolicyOptimalEval;

public class SmallGridworldPolicyEvalTest {

	private final static int sizeS = 16;
	private final static int sizeA = 4;
	private final String[] states = new String[sizeS];
	private final String[] actions = new String[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private final double[][] policy = new double[sizeS][sizeA];
	private static MDPPolicyOptimalEval<String, String> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		int sizeS = 16;
		int sizeA = 4;
		markovDecisionProcess = new MDPPolicyOptimalEval<String, String>(sizeS, sizeA);

		markovDecisionProcess.setState(0, "1,1"); // terminal
		markovDecisionProcess.setState(1, "1,2");
		markovDecisionProcess.setState(2, "1,3");
		markovDecisionProcess.setState(3, "1,4");
		markovDecisionProcess.setState(4, "2,1");
		markovDecisionProcess.setState(5, "2,2");
		markovDecisionProcess.setState(6, "2,3");
		markovDecisionProcess.setState(7, "2,4");
		markovDecisionProcess.setState(8, "3,1");
		markovDecisionProcess.setState(9, "3,2");
		markovDecisionProcess.setState(10, "3,3");
		markovDecisionProcess.setState(11, "3,4");
		markovDecisionProcess.setState(12, "4,1");
		markovDecisionProcess.setState(13, "4,2");
		markovDecisionProcess.setState(14, "4,3");
		markovDecisionProcess.setState(15, "4,4"); // terminal

		markovDecisionProcess.setAction(0, "UP");
		markovDecisionProcess.setAction(1, "DN");
		markovDecisionProcess.setAction(2, "LF");
		markovDecisionProcess.setAction(3, "RT");

		markovDecisionProcess.setProbablilityStateStatePrime(1, 0, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 1, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 2, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 3, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 0, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 1, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 2, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 3, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 0, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 1, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 2, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 3, 3, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(4, 0, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 1, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 2, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 3, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 0, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 1, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 2, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 3, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 0, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 1, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 2, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 3, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 0, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 1, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 2, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 3, 7, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(8, 0, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 1, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 2, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 3, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 0, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 1, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 2, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 3, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 0, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 1, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 2, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 3, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 0, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 1, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 2, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 3, 11, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(12, 0, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 1, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 2, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 3, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 0, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 1, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 2, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 3, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 0, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 1, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 2, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 3, 15, 1.0d);

		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				markovDecisionProcess.setReward(state_t, action_t, -1.0d);
				markovDecisionProcess.setPolicy(state_t, action_t, 0.25d);
			}
		}
		markovDecisionProcess.setReward(0, 0, 0.0d);
		markovDecisionProcess.setReward(0, 1, 0.0d);
		markovDecisionProcess.setReward(0, 2, 0.0d);
		markovDecisionProcess.setReward(0, 3, 0.0d);
		markovDecisionProcess.setReward(15, 0, 0.0d);
		markovDecisionProcess.setReward(15, 1, 0.0d);
		markovDecisionProcess.setReward(15, 2, 0.0d);
		markovDecisionProcess.setReward(15, 3, 0.0d);

		markovDecisionProcess.setPolicy(0, 0, 0.0d);
		markovDecisionProcess.setPolicy(0, 1, 0.0d);
		markovDecisionProcess.setPolicy(0, 2, 0.0d);
		markovDecisionProcess.setPolicy(0, 3, 0.0d);
		markovDecisionProcess.setPolicy(15, 0, 0.0d);
		markovDecisionProcess.setPolicy(15, 1, 0.0d);
		markovDecisionProcess.setPolicy(15, 2, 0.0d);
		markovDecisionProcess.setPolicy(15, 3, 0.0d);
	}

	@Test
	public void testStates() {
		states[0] = "1,1"; // terminal
		states[1] = "1,2";
		states[2] = "1,3";
		states[3] = "1,4";
		states[4] = "2,1";
		states[5] = "2,2";
		states[6] = "2,3";
		states[7] = "2,4";
		states[8] = "3,1";
		states[9] = "3,2";
		states[10] = "3,3";
		states[11] = "3,4";
		states[12] = "4,1";
		states[13] = "4,2";
		states[14] = "4,3";
		states[15] = "4,4"; // terminal
		Assert.assertArrayEquals(states, markovDecisionProcess.getStates());
	}

	@Test
	public void testActions() {
		actions[0] = "UP";
		actions[1] = "DN";
		actions[2] = "LF";
		actions[3] = "RT";
		Assert.assertArrayEquals(actions, markovDecisionProcess.getActions());
	}

	@Test
	public void testTPM() {
		stateTPM[1][0][1] = 1.0d;
		stateTPM[1][1][5] = 1.0d;
		stateTPM[1][2][0] = 1.0d;
		stateTPM[1][3][2] = 1.0d;
		stateTPM[2][0][2] = 1.0d;
		stateTPM[2][1][6] = 1.0d;
		stateTPM[2][2][1] = 1.0d;
		stateTPM[2][3][3] = 1.0d;
		stateTPM[3][0][3] = 1.0d;
		stateTPM[3][1][7] = 1.0d;
		stateTPM[3][2][2] = 1.0d;
		stateTPM[3][3][3] = 1.0d;

		stateTPM[4][0][0] = 1.0d;
		stateTPM[4][1][8] = 1.0d;
		stateTPM[4][2][4] = 1.0d;
		stateTPM[4][3][5] = 1.0d;
		stateTPM[5][0][1] = 1.0d;
		stateTPM[5][1][9] = 1.0d;
		stateTPM[5][2][4] = 1.0d;
		stateTPM[5][3][6] = 1.0d;
		stateTPM[6][0][2] = 1.0d;
		stateTPM[6][1][10] = 1.0d;
		stateTPM[6][2][5] = 1.0d;
		stateTPM[6][3][7] = 1.0d;
		stateTPM[7][0][3] = 1.0d;
		stateTPM[7][1][11] = 1.0d;
		stateTPM[7][2][6] = 1.0d;
		stateTPM[7][3][7] = 1.0d;

		stateTPM[8][0][4] = 1.0d;
		stateTPM[8][1][12] = 1.0d;
		stateTPM[8][2][8] = 1.0d;
		stateTPM[8][3][9] = 1.0d;
		stateTPM[9][0][5] = 1.0d;
		stateTPM[9][1][13] = 1.0d;
		stateTPM[9][2][8] = 1.0d;
		stateTPM[9][3][10] = 1.0d;
		stateTPM[10][0][6] = 1.0d;
		stateTPM[10][1][14] = 1.0d;
		stateTPM[10][2][9] = 1.0d;
		stateTPM[10][3][11] = 1.0d;
		stateTPM[11][0][7] = 1.0d;
		stateTPM[11][1][15] = 1.0d;
		stateTPM[11][2][10] = 1.0d;
		stateTPM[11][3][11] = 1.0d;

		stateTPM[12][0][8] = 1.0d;
		stateTPM[12][1][12] = 1.0d;
		stateTPM[12][2][12] = 1.0d;
		stateTPM[12][3][13] = 1.0d;
		stateTPM[13][0][9] = 1.0d;
		stateTPM[13][1][13] = 1.0d;
		stateTPM[13][2][12] = 1.0d;
		stateTPM[13][3][14] = 1.0d;
		stateTPM[14][0][10] = 1.0d;
		stateTPM[14][1][14] = 1.0d;
		stateTPM[14][2][13] = 1.0d;
		stateTPM[14][3][15] = 1.0d;

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
		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				rewards[state_t][action_t] = -1.0d;
			}
		}
		rewards[0][0] = 0.0d;
		rewards[0][1] = 0.0d;
		rewards[0][2] = 0.0d;
		rewards[0][3] = 0.0d;
		rewards[15][0] = 0.0d;
		rewards[15][1] = 0.0d;
		rewards[15][2] = 0.0d;
		rewards[15][3] = 0.0d;

		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(rewards[state], markovDecisionProcess.getRewards()[state], 0.001d);
		}
	}

	@Test
	public void testPolicies() {
		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				policy[state_t][action_t] = 0.25d;
			}
		}
		policy[0][0] = 0.0d;
		policy[0][1] = 0.0d;
		policy[0][2] = 0.0d;
		policy[0][3] = 0.0d;
		policy[15][0] = 0.0d;
		policy[15][1] = 0.0d;
		policy[15][2] = 0.0d;
		policy[15][3] = 0.0d;

		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(policy[state], markovDecisionProcess.getPolicy()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<sizeA; i++) {
				sum += policy[state][i];
			}
			if (state != 0 && state != 15) Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}

	@Test
	public void testDiscount00Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true, -1000);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount05Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true, -1000);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-1.94d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-1.98d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-1.92d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-1.97d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-1.97d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-1.92d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-1.98d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.01d);
	}

	@Test
	public void testDiscount09Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true, -1000);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-7.65d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-6.61d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-7.18d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-7.18d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-6.61d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-7.65d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.01d);
	}

	@Test
	public void testDiscount10Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(  0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-18.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-18.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(  0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 0), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 1), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 2), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(1, 0), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(1, 1), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(1, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(1, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(2, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(2, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(2, 2), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(2, 3), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(3, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(3, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(3, 2), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(3, 3), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(4, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(4, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(4, 2), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(4, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(5, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(5, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(5, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(5, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(6, 0), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(6, 1), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(6, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(6, 3), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(7, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(7, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(7, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(7, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(8, 0), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(8, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(8, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(8, 3), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(9, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(9, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(9, 2), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(9, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(10, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(10, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(10, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(10, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(11, 0), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(11, 1), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(11, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(11, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(12, 0), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(12, 1), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(12, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(12, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(13, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(13, 1), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(13, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(13, 3), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(14, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(14, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(14, 2), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(14, 3), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 0), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 1), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 2), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 3), 0.01d);
	}

	public void testDiscount00Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount05Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-1.94d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-1.98d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-1.92d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-1.97d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-1.97d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-1.92d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-1.98d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-1.95d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-1.69d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.01d);
	}

	@Test
	public void testDiscount09Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-7.65d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-6.61d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-7.18d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-7.18d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-6.61d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-7.65d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-7.13d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-5.28d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.01d);
	}

	@Test
	public void testDiscount10Loop() {
		markovDecisionProcess.setUseOptimalPolicy(false, 0.0d);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(  0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-18.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-18.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(  0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 0), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 1), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 2), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(0, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(1, 0), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(1, 1), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(1, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(1, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(2, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(2, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(2, 2), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(2, 3), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(3, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(3, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(3, 2), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(3, 3), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(4, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(4, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(4, 2), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(4, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(5, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(5, 1), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(5, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(5, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(6, 0), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(6, 1), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(6, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(6, 3), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(7, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(7, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(7, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(7, 3), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(8, 0), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(8, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(8, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(8, 3), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(9, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(9, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(9, 2), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(9, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(10, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(10, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(10, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(10, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(11, 0), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(11, 1), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(11, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(11, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(12, 0), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(12, 1), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(12, 2), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(12, 3), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(13, 0), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(13, 1), 0.01d);
		Assert.assertEquals(-23.00d, markovDecisionProcess.getActionValue(13, 2), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(13, 3), 0.01d);
		Assert.assertEquals(-19.00d, markovDecisionProcess.getActionValue(14, 0), 0.01d);
		Assert.assertEquals(-15.00d, markovDecisionProcess.getActionValue(14, 1), 0.01d);
		Assert.assertEquals(-21.00d, markovDecisionProcess.getActionValue(14, 2), 0.01d);
		Assert.assertEquals( -1.00d, markovDecisionProcess.getActionValue(14, 3), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 0), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 1), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 2), 0.01d);
		Assert.assertEquals(  0.00d, markovDecisionProcess.getActionValue(15, 3), 0.01d);
	}

	@Test
	public void testDiscount00Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 3), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 3), 0.1d);
	}

	@Test
	public void testDiscount05Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-1.75d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-1.75d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-1.75d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-1.75d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-1.50d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getStateValue(15), 0.01d);

		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 0), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 1), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 2), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 3), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(1, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(1, 1), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(1, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(1, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(2, 0), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(2, 1), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(2, 2), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(2, 3), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(3, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(3, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(3, 2), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(3, 3), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(4, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(4, 1), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(4, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(4, 3), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(5, 0), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(5, 1), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(5, 2), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(5, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(6, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(6, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(6, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(6, 3), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(7, 0), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(7, 1), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(7, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(7, 3), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(8, 0), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(8, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(8, 2), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(8, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(9, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(9, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(9, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(9, 3), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(10, 0), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(10, 1), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(10, 2), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(10, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(11, 0), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(11, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(11, 2), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(11, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(12, 0), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(12, 1), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(12, 2), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(12, 3), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(13, 0), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(13, 1), 0.001d);
		Assert.assertEquals(-1.875d, markovDecisionProcess.getActionValue(13, 2), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(13, 3), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(14, 0), 0.001d);
		Assert.assertEquals(-1.500d, markovDecisionProcess.getActionValue(14, 1), 0.001d);
		Assert.assertEquals(-1.750d, markovDecisionProcess.getActionValue(14, 2), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(14, 3), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 0), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 1), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 2), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 3), 0.001d);
	}

	@Test
	public void testDiscount09Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(0.9d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(-2.71d, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(-2.71d, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(-2.71d, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(-2.71d, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(-1.90d, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getStateValue(15), 0.01d);

		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 0), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 1), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 2), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(0, 3), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(1, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(1, 1), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(1, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(1, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(2, 0), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(2, 1), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(2, 2), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(2, 3), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(3, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(3, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(3, 2), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(3, 3), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(4, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(4, 1), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(4, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(4, 3), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(5, 0), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(5, 1), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(5, 2), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(5, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(6, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(6, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(6, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(6, 3), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(7, 0), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(7, 1), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(7, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(7, 3), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(8, 0), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(8, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(8, 2), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(8, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(9, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(9, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(9, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(9, 3), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(10, 0), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(10, 1), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(10, 2), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(10, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(11, 0), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(11, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(11, 2), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(11, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(12, 0), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(12, 1), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(12, 2), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(12, 3), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(13, 0), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(13, 1), 0.001d);
		Assert.assertEquals(-3.439d, markovDecisionProcess.getActionValue(13, 2), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(13, 3), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(14, 0), 0.001d);
		Assert.assertEquals(-1.900d, markovDecisionProcess.getActionValue(14, 1), 0.001d);
		Assert.assertEquals(-2.710d, markovDecisionProcess.getActionValue(14, 2), 0.001d);
		Assert.assertEquals(-1.000d, markovDecisionProcess.getActionValue(14, 3), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 0), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 1), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 2), 0.001d);
		Assert.assertEquals( 0.000d, markovDecisionProcess.getActionValue(15, 3), 0.001d);
	}

	@Test
	public void testDiscount10Optimal() {
		markovDecisionProcess.setUseOptimalPolicy(true, -1000.0d);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(1, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(1, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(1, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(2, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(2, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(2, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(3, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(3, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(3, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(3, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(4, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(4, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(4, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(5, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(5, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(7, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(7, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(7, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(8, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(8, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(8, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(10, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(10, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(11, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(11, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(11, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(12, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(12, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(12, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(12, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(13, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(13, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(13, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(14, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(14, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(14, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 3), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 3), 0.1d);
	}

	@Test
	public void testDiscount10CountK0() {
		markovDecisionProcess.setUseCounterPolicy(0, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(2, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(3, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(5, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(6, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(7, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(8, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(9, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(10, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(12, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(13, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 3), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 3), 0.1d);
	}

	@Test
	public void testDiscount10CountK1() {
		markovDecisionProcess.setUseCounterPolicy(1, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(1, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(1, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(1, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(3, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(3, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(3, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(3, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(4, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(4, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(4, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(6, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(6, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(6, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(6, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(9, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(9, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(9, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(9, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(11, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(11, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(11, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(12, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(12, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(12, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(12, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(14, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(14, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(14, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 3), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 3), 0.1d);
	}

	@Test
	public void testDiscount10CountK2() {
		markovDecisionProcess.setUseCounterPolicy(2, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.7d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.7d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.7d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.7d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 3), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(1, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(1, 1), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(1, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(1, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(2, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(2, 1), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(2, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(2, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(3, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(3, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(3, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(3, 3), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(4, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(4, 1), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(4, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(4, 3), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(5, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(5, 1), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(5, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(5, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(6, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(6, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(6, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(6, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(7, 0), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(7, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(7, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(7, 3), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(8, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(8, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(8, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(8, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(9, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(9, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(9, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(9, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(10, 0), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(10, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(10, 2), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(10, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(11, 0), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(11, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(11, 2), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(11, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(12, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(12, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(12, 2), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(12, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(13, 0), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(13, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(13, 2), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(13, 3), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(14, 0), 0.01d);
		Assert.assertEquals(-2.75d, markovDecisionProcess.getActionValue(14, 1), 0.01d);
		Assert.assertEquals(-3.00d, markovDecisionProcess.getActionValue(14, 2), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(14, 3), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 3), 0.01d);
	}

	@Test
	public void testDiscount10CountK3() {
		markovDecisionProcess.setUseCounterPolicy(3, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-2.4d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-2.4d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-2.4d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-2.9d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-2.4d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 3), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(1, 0), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(1, 1), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(1, 2), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(1, 3), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(2, 0), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(2, 1), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(2, 2), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(2, 3), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(3, 0), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(3, 1), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(3, 2), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(3, 3), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(4, 0), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(4, 1), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(4, 2), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(4, 3), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(5, 0), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(5, 1), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(5, 2), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(5, 3), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(6, 0), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(6, 1), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(6, 2), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(6, 3), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(7, 0), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(7, 1), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(7, 2), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(7, 3), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(8, 0), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(8, 1), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(8, 2), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(8, 3), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(9, 0), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(9, 1), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(9, 2), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(9, 3), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(10, 0), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(10, 1), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(10, 2), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(10, 3), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(11, 0), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(11, 1), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(11, 2), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(11, 3), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(12, 0), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(12, 1), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(12, 2), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(12, 3), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(13, 0), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(13, 1), 0.01d);
		Assert.assertEquals(-4.00d, markovDecisionProcess.getActionValue(13, 2), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(13, 3), 0.01d);
		Assert.assertEquals(-3.88d, markovDecisionProcess.getActionValue(14, 0), 0.01d);
		Assert.assertEquals(-3.44d, markovDecisionProcess.getActionValue(14, 1), 0.01d);
		Assert.assertEquals(-3.94d, markovDecisionProcess.getActionValue(14, 2), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(14, 3), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 3), 0.01d);
	}

	@Test
	public void testDiscount10CountK10() {
		markovDecisionProcess.setUseCounterPolicy(10, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-6.1d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-9.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-6.1d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-7.7d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-7.7d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-6.1d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-9.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-8.4d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-6.1d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(0, 3), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(1, 0), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(1, 1), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(1, 2), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(1, 3), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(2, 0), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(2, 1), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(2, 2), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(2, 3), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(3, 0), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(3, 1), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(3, 2), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(3, 3), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(4, 0), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(4, 1), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(4, 2), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(4, 3), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(5, 0), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(5, 1), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(5, 2), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(5, 3), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(6, 0), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(6, 1), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(6, 2), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(6, 3), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(7, 0), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(7, 1), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(7, 2), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(7, 3), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(8, 0), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(8, 1), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(8, 2), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(8, 3), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(9, 0), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(9, 1), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(9, 2), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(9, 3), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(10, 0), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(10, 1), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(10, 2), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(10, 3), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(11, 0), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(11, 1), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(11, 2), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(11, 3), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(12, 0), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(12, 1), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(12, 2), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(12, 3), 0.01d);
		Assert.assertEquals(-9.43d, markovDecisionProcess.getActionValue(13, 0), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(13, 1), 0.01d);
		Assert.assertEquals(-9.96d, markovDecisionProcess.getActionValue(13, 2), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(13, 3), 0.01d);
		Assert.assertEquals(-8.74d, markovDecisionProcess.getActionValue(14, 0), 0.01d);
		Assert.assertEquals(-7.14d, markovDecisionProcess.getActionValue(14, 1), 0.01d);
		Assert.assertEquals(-9.35d, markovDecisionProcess.getActionValue(14, 2), 0.01d);
		Assert.assertEquals(-1.00d, markovDecisionProcess.getActionValue(14, 3), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 0), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 1), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 2), 0.01d);
		Assert.assertEquals( 0.00d, markovDecisionProcess.getActionValue(15, 3), 0.01d);
	}

	@Test
	public void testDiscount10Greedy() {
		markovDecisionProcess.setUseBellmanMatrix(true, -1000);
		markovDecisionProcess.setDiscountFactor(1.0d);
		markovDecisionProcess.evaluatePolicy_Greedy();

		policy[0][0] = 0.25d;
		policy[0][1] = 0.25d;
		policy[0][2] = 0.25d;
		policy[0][3] = 0.25d;
		policy[1][2] = 1.0d;
		policy[2][2] = 1.0d;
		policy[3][1] = 0.5d;
		policy[3][2] = 0.5d;
		policy[4][0] = 1.0d;
		policy[5][2] = 1.0d;
		policy[6][2] = 1.0d;
		policy[7][1] = 1.0d;
		policy[8][0] = 1.0d;
		policy[9][0] = 0.5d;
		policy[9][3] = 0.5d;
		policy[10][1] = 0.5d;
		policy[10][3] = 0.5d;
		policy[11][1] = 1.0d;
		policy[12][3] = 1.0d;
		policy[13][3] = 1.0d;
		policy[14][3] = 1.0d;
		policy[15][0] = 0.25d;
		policy[15][1] = 0.25d;
		policy[15][2] = 0.25d;
		policy[15][3] = 0.25d;
		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(policy[state], markovDecisionProcess.getPolicy()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<sizeA; i++) {
				sum += policy[state][i];
			}
			if (state != 0 && state != 15) Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(15), 0.1d);

		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(0, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(1, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(1, 1), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(1, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(1, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(2, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(2, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(2, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(2, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(3, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(3, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(3, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(3, 3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(4, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(4, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(4, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(4, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(5, 1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(5, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(5, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(6, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(7, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(7, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(7, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(7, 3), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(8, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(8, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(8, 2), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(8, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(9, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(10, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(10, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(10, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(11, 0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(11, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(11, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(11, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(12, 0), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(12, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(12, 2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(12, 3), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(13, 0), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(13, 1), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getActionValue(13, 2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(13, 3), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(14, 0), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getActionValue(14, 1), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getActionValue(14, 2), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getActionValue(14, 3), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 0), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 1), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 2), 0.1d);
		Assert.assertEquals( 0.0d, markovDecisionProcess.getActionValue(15, 3), 0.1d);

	
		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				markovDecisionProcess.setPolicy(state_t, action_t, 0.25d);
			}
		}
		markovDecisionProcess.setPolicy(0, 0, 0.0d);
		markovDecisionProcess.setPolicy(0, 1, 0.0d);
		markovDecisionProcess.setPolicy(0, 2, 0.0d);
		markovDecisionProcess.setPolicy(0, 3, 0.0d);
		markovDecisionProcess.setPolicy(15, 0, 0.0d);
		markovDecisionProcess.setPolicy(15, 1, 0.0d);
		markovDecisionProcess.setPolicy(15, 2, 0.0d);
		markovDecisionProcess.setPolicy(15, 3, 0.0d);
	}
}
