package ai;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentMDPTest {

	private final static int sizeS = 5;
	private final static int sizeA = 5;
	private final String[] states = new String[sizeS];
	private final String[] actions = new String[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private static MarkovDecisionProcess<String, String> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		markovDecisionProcess = new MarkovDecisionProcess<String, String>(sizeS, sizeA);

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
		stateTPM[0][2][4] = 1.0d;
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
	public void testEpisode1() {
		String[] states1 = new String[4];
		states1[0] = "C1";
		states1[1] = "C2";
		states1[2] = "C3";
		states1[3] = "SL";

		String[] actions1 = new String[4];
		actions1[0] = "ST";
		actions1[1] = "ST";
		actions1[2] = "ST";
		actions1[3] = "SL";

		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-0.5d, markovDecisionProcess.getExpectedReturn(states1, actions1, 0), 0.1d);
	}

	@Test
	public void testEpisode2() {
		String[] states1 = new String[6];
		states1[0] = "C1";
		states1[1] = "FB";
		states1[2] = "FB";
		states1[3] = "C1";
		states1[4] = "C2";
		states1[5] = "SL";

		String[] actions1 = new String[6];
		actions1[0] = "FB";
		actions1[1] = "FB";
		actions1[2] = "QU";
		actions1[3] = "ST";
		actions1[4] = "SL";
		actions1[5] = "SL";

		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-1.75d, markovDecisionProcess.getExpectedReturn(states1, actions1, 0), 0.01d);
	}

	@Test
	public void testEpisode3() {
		String[] states1 = new String[6];
		states1[0] = "C1";
		states1[1] = "C2";
		states1[2] = "C3";
		states1[3] = "C2";
		states1[4] = "C3";
		states1[5] = "SL";

		String[] actions1 = new String[6];
		actions1[0] = "ST";
		actions1[1] = "ST";
		actions1[2] = "PB";
		actions1[3] = "ST";
		actions1[4] = "SL";
		actions1[5] = "SL";

		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-3.0d, markovDecisionProcess.getExpectedReturn(states1, actions1, 0), 0.1d);
	}

	@Test
	public void testEpisode4() {
		String[] states1 = new String[15];
		states1[0] = "C1";
		states1[1] = "FB";
		states1[2] = "FB";
		states1[3] = "C1";
		states1[4] = "C2";
		states1[5] = "C3";
		states1[6] = "C1";
		states1[7] = "FB";
		states1[8] = "FB";
		states1[9] = "FB";
		states1[10] = "C1";
		states1[11] = "C2";
		states1[12] = "C3";
		states1[13] = "C2";
		states1[14] = "SL";

		String[] actions1 = new String[15];
		actions1[0] = "FB";
		actions1[1] = "FB";
		actions1[2] = "QU";
		actions1[3] = "ST";
		actions1[4] = "ST";
		actions1[5] = "PB";
		actions1[6] = "FB";
		actions1[7] = "FB";
		actions1[8] = "FB";
		actions1[9] = "QU";
		actions1[10] = "ST";
		actions1[11] = "ST";
		actions1[12] = "PB";
		actions1[13] = "SL";
		actions1[14] = "SL";

		markovDecisionProcess.setDiscountFactor(0.5d);
		Assert.assertEquals(-1.874d, markovDecisionProcess.getExpectedReturn(states1, actions1, 0), 0.001d);
	}

	@Test
	public void testDiscount00Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.0d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount05Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.5d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount09Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.9d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount10Matrix() {
		markovDecisionProcess.setUseBellmanMatrix(true);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(1.0d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount00Loop() {
		markovDecisionProcess.setUseBellmanMatrix(false);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.0d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount05Loop() {
		markovDecisionProcess.setUseBellmanMatrix(false);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.5d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount09Loop() {
		markovDecisionProcess.setUseBellmanMatrix(false);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(0.9d);		// FAIL - INCOMPLETE
	}

	@Test
	public void testDiscount10Loop() {
		markovDecisionProcess.setUseBellmanMatrix(false);	// FAIL - INCOMPLETE
//		markovDecisionProcess.setDiscountFactor(1.0d);		// FAIL - INCOMPLETE
	}
}
