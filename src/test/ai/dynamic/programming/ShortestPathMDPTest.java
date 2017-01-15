package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MDP;
import ai.dynamic.programming.MDP.ValueFunction;

public class ShortestPathMDPTest {

	private final static int sizeS = 16;
	private final static int sizeA = 4;
	private final String[] states = new String[sizeS];
	private final String[] actions = new String[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private final double[][] policy = new double[sizeS][sizeA];
	private static MDP<String, String> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		int sizeS = 16;
		int sizeA = 4;
		markovDecisionProcess = new MDP<String, String>(sizeS, sizeA);

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
		markovDecisionProcess.setProbablilityStateStatePrime(15, 0, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 1, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 2, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 3, 15, 1.0d);

		for (int state_t=1; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				markovDecisionProcess.setReward(state_t, action_t, -1.0d);
				markovDecisionProcess.setPolicy(state_t, action_t, 0.25d);
			}
		}
		
		markovDecisionProcess.savePolicy();
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
		stateTPM[15][0][11] = 1.0d;
		stateTPM[15][1][15] = 1.0d;
		stateTPM[15][2][14] = 1.0d;
		stateTPM[15][3][15] = 1.0d;

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

		markovDecisionProcess.restorePolicy();
		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(policy[state], markovDecisionProcess.getPolicy()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<sizeA; i++) {
				sum += markovDecisionProcess.getPolicy()[state][i];
			}
			if (state != 0) Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}

	@Test
	public void testDiscount10CountK0() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(0);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
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
	}

	@Test
	public void testDiscount10CountK1() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(1);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
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
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount10CountK2() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(2);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.resetStateValues();
		markovDecisionProcess.evaluateValueFunction();
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount10CountK3() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(3);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount10CountK4() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(4);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount10CountK5() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(5);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}

	@Test
	public void testDiscount10CountK6() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(6);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
		Assert.assertEquals( 0.0d, markovDecisionProcess.getStateValue(0), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(1), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(2), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(3), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(4), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(5), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(6), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(7), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(8), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-6.0d, markovDecisionProcess.getStateValue(15), 0.1d);
	}
}
