package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MDP;
import ai.dynamic.programming.MDP.ValueFunction;

public class MazePathMDPTest {

	private final static int sizeS = 65;
	private final static int sizeA = 4;
	private final String[] states = new String[sizeS];
	private final String[] actions = new String[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private final double[][] policy = new double[sizeS][sizeA];
	private static MDP<String, String> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		markovDecisionProcess = new MDP<String, String>(sizeS, sizeA);

		markovDecisionProcess.setState(0, "1,1");
		markovDecisionProcess.setState(1, "1,2");
		markovDecisionProcess.setState(2, "1,3");
		markovDecisionProcess.setState(3, "1,4");
		markovDecisionProcess.setState(4, "1,5");
		markovDecisionProcess.setState(5, "1,6");
		markovDecisionProcess.setState(6, "1,7");
		markovDecisionProcess.setState(7, "1,8");
		markovDecisionProcess.setState(8, "2,1");
		markovDecisionProcess.setState(9, "2,2");
		markovDecisionProcess.setState(10, "2,3");
		markovDecisionProcess.setState(11, "2,4");
		markovDecisionProcess.setState(12, "2,5");
		markovDecisionProcess.setState(13, "2,6");
		markovDecisionProcess.setState(14, "2,7");
		markovDecisionProcess.setState(15, "2,8");
		markovDecisionProcess.setState(16, "3,1");
		markovDecisionProcess.setState(17, "3,2");
		markovDecisionProcess.setState(18, "3,3");
		markovDecisionProcess.setState(19, "3,4");
		markovDecisionProcess.setState(20, "3,5");
		markovDecisionProcess.setState(21, "3,6");
		markovDecisionProcess.setState(22, "3,7");
		markovDecisionProcess.setState(23, "3,8");
		markovDecisionProcess.setState(24, "4,1");
		markovDecisionProcess.setState(25, "4,2");
		markovDecisionProcess.setState(26, "4,3");
		markovDecisionProcess.setState(27, "4,4");
		markovDecisionProcess.setState(28, "4,5");
		markovDecisionProcess.setState(29, "4,6");
		markovDecisionProcess.setState(30, "4,7");
		markovDecisionProcess.setState(31, "4,8");
		markovDecisionProcess.setState(32, "5,1");
		markovDecisionProcess.setState(33, "5,2");
		markovDecisionProcess.setState(34, "5,3");
		markovDecisionProcess.setState(35, "5,4");
		markovDecisionProcess.setState(36, "5,5");
		markovDecisionProcess.setState(37, "5,6");
		markovDecisionProcess.setState(38, "5,7");
		markovDecisionProcess.setState(39, "5,8");
		markovDecisionProcess.setState(40, "6,1");
		markovDecisionProcess.setState(41, "6,2");
		markovDecisionProcess.setState(42, "6,3");
		markovDecisionProcess.setState(43, "6,4");
		markovDecisionProcess.setState(44, "6,5");
		markovDecisionProcess.setState(45, "6,6");
		markovDecisionProcess.setState(46, "6,7");
		markovDecisionProcess.setState(47, "6,8");
		markovDecisionProcess.setState(48, "7,1");
		markovDecisionProcess.setState(49, "7,2");
		markovDecisionProcess.setState(50, "7,3");
		markovDecisionProcess.setState(51, "7,4");
		markovDecisionProcess.setState(52, "7,5");
		markovDecisionProcess.setState(53, "7,6");
		markovDecisionProcess.setState(54, "7,7");
		markovDecisionProcess.setState(55, "7,8");
		markovDecisionProcess.setState(56, "8,1");
		markovDecisionProcess.setState(57, "8,2");
		markovDecisionProcess.setState(58, "8,3");
		markovDecisionProcess.setState(59, "8,4");
		markovDecisionProcess.setState(60, "8,5");
		markovDecisionProcess.setState(61, "8,6");
		markovDecisionProcess.setState(62, "8,7");
		markovDecisionProcess.setState(63, "8,8");
		markovDecisionProcess.setState(64, "GOAL");

		markovDecisionProcess.setAction(0, "UP");
		markovDecisionProcess.setAction(1, "DN");
		markovDecisionProcess.setAction(2, "LF");
		markovDecisionProcess.setAction(3, "RT");

		markovDecisionProcess.setProbablilityStateStatePrime(9, 1, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 3, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 2, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 3, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 2, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 3, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 1, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 2, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 3, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 2, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 3, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 1, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 2, 13, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(16, 3, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 0, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 1, 25, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 2, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 0, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 0, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 1, 30, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(25, 0, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(25, 3, 26, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(26, 1, 34, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(26, 2, 25, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(29, 1, 37, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(29, 3, 30, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(30, 0, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(30, 2, 29, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(34, 0, 26, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(34, 3, 35, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(35, 1, 43, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(35, 2, 34, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(37, 0, 29, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(37, 1, 45, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(41, 1, 49, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(43, 0, 35, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(43, 1, 51, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(45, 0, 37, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(45, 3, 46, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(46, 1, 54, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(46, 2, 45, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(49, 0, 41, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(49, 3, 50, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(50, 2, 49, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(50, 3, 51, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(51, 0, 43, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(51, 2, 50, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(51, 3, 52, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(52, 2, 51, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(54, 0, 46, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(54, 3, 55, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(55, 2, 54, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(55, 3, 64, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(9, 0, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 2, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 0, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 1, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 0, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 1, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 0, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 0, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 1, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 0, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 3, 14, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(16, 0, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 1, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 2, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 3, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 1, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 2, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 3, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 2, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 3, 22, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(25, 1, 25, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(25, 2, 25, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(26, 0, 26, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(26, 3, 26, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(29, 0, 29, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(29, 2, 29, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(30, 1, 30, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(30, 3, 30, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(34, 1, 34, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(34, 2, 34, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(35, 0, 35, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(35, 3, 35, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(37, 2, 37, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(37, 3, 37, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(41, 0, 41, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(41, 2, 41, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(41, 3, 41, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(43, 2, 43, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(43, 3, 43, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(45, 1, 45, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(45, 2, 45, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(46, 0, 46, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(46, 3, 46, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(49, 1, 49, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(49, 2, 49, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(50, 0, 50, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(50, 1, 50, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(51, 1, 51, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(52, 0, 52, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(52, 1, 52, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(52, 3, 52, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(54, 1, 54, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(54, 2, 54, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(55, 0, 55, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(55, 1, 55, 1.0d);

		for (int state_t=0; state_t<sizeS-1; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				markovDecisionProcess.setReward(state_t, action_t, -1.0d);
				markovDecisionProcess.setPolicy(state_t, action_t, 0.25d);
			}
		}
		markovDecisionProcess.savePolicy();
	}

	@Test
	public void testTPM() {
		for (int state=0; state<sizeS; state++) {
			for (int action=0; action<sizeA; action++) {
//				Assert.assertArrayEquals(stateTPM[state][action], markovDecisionProcess.getStateTransitionProbabilityMatrix()[state][action], 0.001d);
				double sum = 0.0d;
				for (int i=0; i<sizeS; i++) {
					sum += markovDecisionProcess.getStateTransitionProbabilityMatrix()[state][action][i];
				}
				Assert.assertTrue(sum == 1.0d || sum == 0.0d); //0.000001d
			}
		}
	}

	@Test
	public void testRewards() {
		for (int state_t=0; state_t<sizeS-1; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				rewards[state_t][action_t] = -1.0d;
			}
		}
		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(rewards[state], markovDecisionProcess.getRewards()[state], 0.001d);
		}
	}

	@Test
	public void testPolicies() {
		for (int state_t=0; state_t<sizeS-1; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				policy[state_t][action_t] = 0.25d;
			}
		}

		markovDecisionProcess.restorePolicy();
		for (int state=0; state<sizeS-1; state++) {
			Assert.assertArrayEquals(policy[state], markovDecisionProcess.getPolicy()[state], 0.001d);
			double sum = 0.0d;
			for (int i=0; i<sizeA; i++) {
				sum += markovDecisionProcess.getPolicy()[state][i];
			}
			if (state != 0) Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}

	@Test
	public void testDiscount10Greedy() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_MATRIX);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		while (!markovDecisionProcess.isPolicyFunctionOptimal()) {
			markovDecisionProcess.improvePolicy();
			markovDecisionProcess.evaluateValueFunction();
		}

//		for (int state_t=0; state_t<sizeS; state_t++) {
//			for (int action_t=0; action_t<sizeA; action_t++) {
//				if (markovDecisionProcess.getStatePolicy(state_t, action_t) > 0.0d) {
//					System.out.print(markovDecisionProcess.getAction(action_t));
//				}
//			}
//			System.out.print("\t");
//			if (state_t % 8 == 7) System.out.println();
//		}
//
//		System.out.println();
//		for (int value_t=0; value_t<sizeS; value_t++) {
//			System.out.print(String.format("%+5.2f", markovDecisionProcess.getStateValue(value_t)));
//			System.out.print("\t");
//			if (value_t % 8 == 7) System.out.println();
//		}
		
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(9, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(10, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(11, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(12, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(13, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(14, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(16, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(17, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(20, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(22, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(25, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(26, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(29, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(30, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(34, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(35, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(37, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(41, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(43, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(45, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(46, 1), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(49, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(50, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(51, 0), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(52, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(54, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(55, 3), 0.1d);

		Assert.assertEquals(-14.0d, markovDecisionProcess.getStateValue(9), 0.1d);
		Assert.assertEquals(-13.0d, markovDecisionProcess.getStateValue(10), 0.1d);
		Assert.assertEquals(-12.0d, markovDecisionProcess.getStateValue(11), 0.1d);
		Assert.assertEquals(-11.0d, markovDecisionProcess.getStateValue(12), 0.1d);
		Assert.assertEquals(-10.0d, markovDecisionProcess.getStateValue(13), 0.1d);
		Assert.assertEquals(-9.0d, markovDecisionProcess.getStateValue(14), 0.1d);
		Assert.assertEquals(-16.0d, markovDecisionProcess.getStateValue(16), 0.1d);
		Assert.assertEquals(-15.0d, markovDecisionProcess.getStateValue(17), 0.1d);
		Assert.assertEquals(-12.0d, markovDecisionProcess.getStateValue(20), 0.1d);
		Assert.assertEquals(-8.0d, markovDecisionProcess.getStateValue(22), 0.1d);
		Assert.assertEquals(-16.0d, markovDecisionProcess.getStateValue(25), 0.1d);
		Assert.assertEquals(-17.0d, markovDecisionProcess.getStateValue(26), 0.1d);
		Assert.assertEquals(-6.0d, markovDecisionProcess.getStateValue(29), 0.1d);
		Assert.assertEquals(-7.0d, markovDecisionProcess.getStateValue(30), 0.1d);
		Assert.assertEquals(-18.0d, markovDecisionProcess.getStateValue(34), 0.1d);
		Assert.assertEquals(-19.0d, markovDecisionProcess.getStateValue(35), 0.1d);
		Assert.assertEquals(-5.0d, markovDecisionProcess.getStateValue(37), 0.1d);
		Assert.assertEquals(-24.0d, markovDecisionProcess.getStateValue(41), 0.1d);
		Assert.assertEquals(-20.0d, markovDecisionProcess.getStateValue(43), 0.1d);
		Assert.assertEquals(-4.0d, markovDecisionProcess.getStateValue(45), 0.1d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getStateValue(46), 0.1d);
		Assert.assertEquals(-23.0d, markovDecisionProcess.getStateValue(49), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(50), 0.1d);
		Assert.assertEquals(-21.0d, markovDecisionProcess.getStateValue(51), 0.1d);
		Assert.assertEquals(-22.0d, markovDecisionProcess.getStateValue(52), 0.1d);
		Assert.assertEquals(-2.0d, markovDecisionProcess.getStateValue(54), 0.1d);
		Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(55), 0.1d);
	}

	@Test
	public void testDiscount10CountK1() {
		markovDecisionProcess.setValueFunction(ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(1);
		markovDecisionProcess.setDiscount(1.0d);
		markovDecisionProcess.evaluateValueFunction();
		markovDecisionProcess.improvePolicy();
		markovDecisionProcess.evaluateValueFunction();
		for (int state_t=0; state_t<sizeS-1; state_t++) {
			Assert.assertEquals(-1.0d, markovDecisionProcess.getStateValue(state_t), 0.1d);
		}
	}
}
