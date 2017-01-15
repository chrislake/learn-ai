package ai.dynamic.programming;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.dynamic.programming.MDP;

public class GridWorldMDPTest {

	private final static int sizeS = 25;
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
		markovDecisionProcess.setState(5, "2,1");
		markovDecisionProcess.setState(6, "2,2");
		markovDecisionProcess.setState(7, "2,3");
		markovDecisionProcess.setState(8, "2,4");
		markovDecisionProcess.setState(9, "2,5");
		markovDecisionProcess.setState(10, "3,1");
		markovDecisionProcess.setState(11, "3,2");
		markovDecisionProcess.setState(12, "3,3");
		markovDecisionProcess.setState(13, "3,4");
		markovDecisionProcess.setState(14, "3,5");
		markovDecisionProcess.setState(15, "4,1");
		markovDecisionProcess.setState(16, "4,2");
		markovDecisionProcess.setState(17, "4,3");
		markovDecisionProcess.setState(18, "4,4");
		markovDecisionProcess.setState(19, "4,5");
		markovDecisionProcess.setState(20, "5,1");
		markovDecisionProcess.setState(21, "5,2");
		markovDecisionProcess.setState(22, "5,3");
		markovDecisionProcess.setState(23, "5,4");
		markovDecisionProcess.setState(24, "5,5");

		markovDecisionProcess.setAction(0, "UP");
		markovDecisionProcess.setAction(1, "DN");
		markovDecisionProcess.setAction(2, "LF");
		markovDecisionProcess.setAction(3, "RT");

		markovDecisionProcess.setProbablilityStateStatePrime(0, 0, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(0, 1, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(0, 2, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(0, 3, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 0, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 1, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 2, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(1, 3, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 0, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 1, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 2, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(2, 3, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 0, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 1, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 2, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(3, 3, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 0, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 1, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 2, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(4, 3, 4, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(5, 0, 0, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 1, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 2, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(5, 3, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 0, 1, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 1, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 2, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(6, 3, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 0, 2, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 1, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 2, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(7, 3, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 0, 3, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 1, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 2, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(8, 3, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 0, 4, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 1, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 2, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(9, 3, 9, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(10, 0, 5, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 1, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 2, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(10, 3, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 0, 6, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 1, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 2, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(11, 3, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 0, 7, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 1, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 2, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(12, 3, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 0, 8, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 1, 18, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 2, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(13, 3, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 0, 9, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 1, 19, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 2, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(14, 3, 14, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(15, 0, 10, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 1, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 2, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(15, 3, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 0, 11, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 1, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 2, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(16, 3, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 0, 12, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 1, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 2, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(17, 3, 18, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(18, 0, 13, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(18, 1, 23, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(18, 2, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(18, 3, 19, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(19, 0, 14, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(19, 1, 24, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(19, 2, 18, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(19, 3, 19, 1.0d);

		markovDecisionProcess.setProbablilityStateStatePrime(20, 0, 15, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 1, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 2, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(20, 3, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(21, 0, 16, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(21, 1, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(21, 2, 20, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(21, 3, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 0, 17, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 1, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 2, 21, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(22, 3, 23, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(23, 0, 18, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(23, 1, 23, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(23, 2, 22, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(23, 3, 24, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(24, 0, 19, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(24, 1, 24, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(24, 2, 23, 1.0d);
		markovDecisionProcess.setProbablilityStateStatePrime(24, 3, 24, 1.0d);

		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				markovDecisionProcess.setReward(state_t, action_t, -1.0d);
				markovDecisionProcess.setPolicy(state_t, action_t, 0.25d);
			}
		}
		markovDecisionProcess.setReward(1, 0, 10.0d);
		markovDecisionProcess.setReward(1, 1, 10.0d);
		markovDecisionProcess.setReward(1, 2, 10.0d);
		markovDecisionProcess.setReward(1, 3, 10.0d);
		markovDecisionProcess.setReward(3, 0, 5.0d);
		markovDecisionProcess.setReward(3, 1, 5.0d);
		markovDecisionProcess.setReward(3, 2, 5.0d);
		markovDecisionProcess.setReward(3, 3, 5.0d);
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
		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				rewards[state_t][action_t] = -1.0d;
			}
		}
		rewards[1][0] = 10.0d;
		rewards[1][1] = 10.0d;
		rewards[1][2] = 10.0d;
		rewards[1][3] = 10.0d;
		rewards[3][0] = 5.0d;
		rewards[3][1] = 5.0d;
		rewards[3][2] = 5.0d;
		rewards[3][3] = 5.0d;

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
	public void testDiscount10KGreedy() {
		markovDecisionProcess.setValueFunction(MDP.ValueFunction.BELLMAN_ITERATIVE);
		markovDecisionProcess.setValueFunctionAttempts(100);
		markovDecisionProcess.setDiscount(0.939d);
		while (!markovDecisionProcess.isValueFunctionOptimal()) {
			markovDecisionProcess.evaluateValueFunction();
			markovDecisionProcess.improvePolicy();

//			for (int state_t=0; state_t<sizeS; state_t++) {
//				for (int action_t=0; action_t<sizeA; action_t++) {
//					if (markovDecisionProcess.getStatePolicy(state_t, action_t) > 0.0d) {
//						System.out.print(markovDecisionProcess.getAction(action_t));
//					}
//				}
//				System.out.print("\t");
//				if (state_t % 5 == 4) System.out.println();
//			}
//			System.out.println();
//			for (int value_t=0; value_t<sizeS; value_t++) {
//				System.out.print(String.format("%+5.2f", markovDecisionProcess.getStateValue(value_t)));
//				System.out.print("\t");
//				if (value_t % 5 == 4) System.out.println();
//			}
//			System.out.println("");
		}

		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(0, 3), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(1, 0), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(1, 1), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(1, 2), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(1, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(2, 2), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(3, 0), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(3, 1), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(3, 2), 0.1d);
		Assert.assertEquals(0.25d, markovDecisionProcess.getStatePolicy(3, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(4, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(5, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(5, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(6, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(7, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(7, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(8, 2), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(9, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(10, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(10, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(11, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(12, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(12, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(13, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(13, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(14, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(14, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(15, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(15, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(16, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(17, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(17, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(18, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(18, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(19, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(19, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(20, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(20, 3), 0.1d);
		Assert.assertEquals(1.0d, markovDecisionProcess.getStatePolicy(21, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(22, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(22, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(23, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(23, 2), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(24, 0), 0.1d);
		Assert.assertEquals(0.5d, markovDecisionProcess.getStatePolicy(24, 2), 0.1d);

		Assert.assertEquals(21.86, markovDecisionProcess.getStateValue(0), 0.01d);
		Assert.assertEquals(24.35, markovDecisionProcess.getStateValue(1), 0.01d);
		Assert.assertEquals(21.86, markovDecisionProcess.getStateValue(2), 0.01d);
		Assert.assertEquals(19.35, markovDecisionProcess.getStateValue(3), 0.01d);
		Assert.assertEquals(17.17, markovDecisionProcess.getStateValue(4), 0.01d);
		Assert.assertEquals(19.53, markovDecisionProcess.getStateValue(5), 0.01d);
		Assert.assertEquals(21.86, markovDecisionProcess.getStateValue(6), 0.01d);
		Assert.assertEquals(19.53, markovDecisionProcess.getStateValue(7), 0.01d);
		Assert.assertEquals(17.34, markovDecisionProcess.getStateValue(8), 0.01d);
		Assert.assertEquals(15.28, markovDecisionProcess.getStateValue(9), 0.01d);
		Assert.assertEquals(17.34, markovDecisionProcess.getStateValue(10), 0.01d);
		Assert.assertEquals(19.53, markovDecisionProcess.getStateValue(11), 0.01d);
		Assert.assertEquals(17.34, markovDecisionProcess.getStateValue(12), 0.01d);
		Assert.assertEquals(15.28, markovDecisionProcess.getStateValue(13), 0.01d);
		Assert.assertEquals(13.35, markovDecisionProcess.getStateValue(14), 0.01d);
		Assert.assertEquals(15.28, markovDecisionProcess.getStateValue(15), 0.01d);
		Assert.assertEquals(17.34, markovDecisionProcess.getStateValue(16), 0.01d);
		Assert.assertEquals(15.28, markovDecisionProcess.getStateValue(17), 0.01d);
		Assert.assertEquals(13.35, markovDecisionProcess.getStateValue(18), 0.01d);
		Assert.assertEquals(11.53, markovDecisionProcess.getStateValue(19), 0.01d);
		Assert.assertEquals(13.35, markovDecisionProcess.getStateValue(20), 0.01d);
		Assert.assertEquals(15.28, markovDecisionProcess.getStateValue(21), 0.01d);
		Assert.assertEquals(13.35, markovDecisionProcess.getStateValue(22), 0.01d);
		Assert.assertEquals(11.53, markovDecisionProcess.getStateValue(23), 0.01d);
		Assert.assertEquals(9.83, markovDecisionProcess.getStateValue(24), 0.01d);
	}
}
