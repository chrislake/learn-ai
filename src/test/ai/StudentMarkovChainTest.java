package ai;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentMarkovChainTest {
	
	private final static int size = 7;
	private final String[] states = new String[size];
	private final double[][] stateTPM = new double[size][size];
	private static MarkovProcess<String> markovProcess;

	@BeforeClass
	public static void generateMarkovProcess() {
		markovProcess = new MarkovProcess<String>(size);

		markovProcess.setState(0, "C1");
		markovProcess.setState(1, "C2");
		markovProcess.setState(2, "C3");
		markovProcess.setState(3, "PS");
		markovProcess.setState(4, "PB");
		markovProcess.setState(5, "FB");
		markovProcess.setState(6, "SL");

		markovProcess.setProbablilityStateStatePrime(0, 1, 0.5d);
		markovProcess.setProbablilityStateStatePrime(0, 5, 0.5d);
		markovProcess.setProbablilityStateStatePrime(1, 2, 0.8d);
		markovProcess.setProbablilityStateStatePrime(1, 6, 0.2d);
		markovProcess.setProbablilityStateStatePrime(2, 3, 0.6d);
		markovProcess.setProbablilityStateStatePrime(2, 4, 0.4d);
		markovProcess.setProbablilityStateStatePrime(3, 6, 1.0d);
		markovProcess.setProbablilityStateStatePrime(4, 0, 0.2d);
		markovProcess.setProbablilityStateStatePrime(4, 1, 0.4d);
		markovProcess.setProbablilityStateStatePrime(4, 2, 0.4d);
		markovProcess.setProbablilityStateStatePrime(5, 0, 0.1d);
		markovProcess.setProbablilityStateStatePrime(5, 5, 0.9d);
		markovProcess.setProbablilityStateStatePrime(6, 6, 1.0d);
	}

	@Test
	public void testState() {
		states[0] = "C1";
		states[1] = "C2";
		states[2] = "C3";
		states[3] = "PS";
		states[4] = "PB";
		states[5] = "FB";
		states[6] = "SL";
		Assert.assertArrayEquals(states, markovProcess.getStates());
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
			Assert.assertArrayEquals(stateTPM[state], markovProcess.getStateTransitionProbabilityMatrix()[state], 0.1d);
			double sum = 0.0d;
			for (int i=0; i<size; i++) {
				sum += stateTPM[state][i];
			}
			Assert.assertEquals(sum, 1.0d, 0.000001d);
		}
	}
}
