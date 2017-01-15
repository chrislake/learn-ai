package ai.dynamic.programming;

import java.util.Arrays;

import ai.Util;

public class MarkovRewardProcess<S> {
	private final Object[] states;
		// S is a (finite) set of states
	private final double[][] stateTransitionProbabilityMatrix;
		// P is a state transition probability matrix
		// Pss' = P [St+1 = s' | St = s]
	private double discountFactor;
		// y is a discount factor, y E [0, 1]
	private final double[] stateRewards;
		// R is a vector of rewards for each state
		// Rs = E [Rt+1 | St = s]
	private double[] stateValues;
		// v is a vector of values for each state

	private boolean useMatrix = true;
	private double valueFunctionPrecision = 0.000001d;
	private int valueFunctionAttempts = 0;

	public MarkovRewardProcess(int size) {
		this(size, 0.0d);
	}
	public MarkovRewardProcess(int size, double discount) {
		this(size, discount, true);
	}
	public MarkovRewardProcess(int size, double discount, boolean useBellmanMatrix) {
		this.states = new Object[size];
		this.stateTransitionProbabilityMatrix = new double[size][size];
		this.discountFactor = discount;

		this.stateRewards = new double[size];
		this.stateValues = new double[size];

		this.useMatrix = useBellmanMatrix;
	}

	@SuppressWarnings("unchecked")
	public S[] getStates() {
		return (S[]) states;
	}
	@SuppressWarnings("unchecked")
	public S getState(int t) {
		return (S) states[t];
	}
	public int getState_t(S state) {
		for (int t=0; t<states.length; t++) {
			if (getState(t).equals(state)) return t;
		}
		return -1;
	}
	public void setState(int t, S state) {
		states[t] = state;
	}

	public double[][] getStateTransitionProbabilityMatrix () {
		return stateTransitionProbabilityMatrix;
	}
	public double getProbablilityStateStatePrime (S state, S statePrime) {
		// Pss' = P [St+1 = s' | St = s]
		int state_t = getState_t(state);
		int statePrime_t = getState_t(statePrime);
		return stateTransitionProbabilityMatrix[state_t][statePrime_t];
	}
	public double getProbablilityStateStatePrime (int state_t, int statePrime_t) {
		// Pss' = P [St+1 = s' | St = s]
		return stateTransitionProbabilityMatrix[state_t][statePrime_t];
	}
	public void setProbablilityStateStatePrime(int state, int statePrime, double probablility) {
		stateTransitionProbabilityMatrix[state][statePrime] = probablility;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}
	public void setDiscountFactor(double discount) {
		discountFactor = discount;
		executeStateValueFunction();
	}

	public double[] getRewards() {
		return stateRewards;
	}
	public double getReward(S state) {
		// R is a reward function
		int state_t = getState_t(state);
		return stateRewards[state_t];
	}
	public double getReward(int state_t) {
		// R is a reward function
		return stateRewards[state_t];
	}
	public void setReward(int t, double reward) {
		stateRewards[t] = reward;
	}

	public void setUseBellmanMatrix(boolean useMatrix) {
		this.useMatrix = useMatrix;
	}

	public double getExpectedReturn(S[] plannedEpisode, int state_t) {
		// v(s) = E[Gt | St=s]
		S[] sub_episode = Arrays.copyOfRange(plannedEpisode, state_t, plannedEpisode.length);
		return getExpectedReturn(sub_episode);
	}

	public double getExpectedReturn(S[] plannedEpisode) {
		// Gt = Rt+1 + yRt+2 + ... = sum(y^k * Rt+k+1) { 0 <= k < inf)
		double return_Gt = 0.0;
		for (int k=0; k<plannedEpisode.length; k++) {
			double reward_t = getReward(plannedEpisode[k]);
			return_Gt += Math.pow(discountFactor, k) * reward_t;
		}

		return return_Gt;
	}

	public void executeStateValueFunction() {
		if (useMatrix) valueFunctionBellmanMatrix();
		else valueFunctionBellmanEquation();
	}
	public double getStateValue(S state) {
		return stateValues[getState_t(state)];
	}
	public double getStateValue(int state_t) {
		return stateValues[state_t];
	}

	private void valueFunctionBellmanEquation () {
		// v(s) = Rs + y * sum(Pss'v(s') { s' in S)
		stateValues = new double[stateValues.length];

		boolean goAgain = true;
		valueFunctionAttempts = 0;
		while(goAgain) {
			goAgain = false;
			valueFunctionAttempts++;

			for (int state_t=0; state_t<states.length; state_t++) {
				double stateValue = 0.0d;
				double currStateValue = stateValues[state_t];
				for (int statePrime_t=0; statePrime_t<states.length; statePrime_t++) {
					stateValue += getProbablilityStateStatePrime(state_t, statePrime_t) * stateValues[statePrime_t];
				}

				stateValues[state_t] = getReward(state_t) + (discountFactor * stateValue);
				goAgain |= Util.doubleIsDifferent(currStateValue, stateValues[state_t], valueFunctionPrecision);
			}
		}
	}

	private void valueFunctionBellmanMatrix() {
		// v = (I - yP)^-1 R
		int len = states.length;
		stateValues = new double[stateValues.length];

		double[][] Imatrix = Util.getIdentityMatrix(len);
		double[][] matrix = new double[len][len];
		for (int row=0; row<len; row++) {
			for (int col=0; col<len; col++) {
				matrix[row][col] = Imatrix[row][col] - discountFactor * stateTransitionProbabilityMatrix[row][col];
			}
		}

		for (int diagRowCol=0; diagRowCol<len; diagRowCol++) {
			double mtxDiagConst = matrix[diagRowCol][diagRowCol];
			if (mtxDiagConst == 0.0d) {
				matrix[diagRowCol][diagRowCol] = 1.0d;	// very dubious!
			}
			else if (mtxDiagConst != 1.0d) {
				for (int col=0; col<len; col++) {
					matrix[diagRowCol][col] /= mtxDiagConst;
					Imatrix[diagRowCol][col] /= mtxDiagConst;
				}
			}

			for (int row=0; row<len; row++) {
				if (row == diagRowCol) continue;
				double mtxRowConst = matrix[row][diagRowCol];
				if (mtxRowConst == 0.0d) continue;
				for (int col=0; col<len; col++) {
					matrix[row][col] = -mtxRowConst * matrix[diagRowCol][col] + matrix[row][col];
					Imatrix[row][col] = -mtxRowConst * Imatrix[diagRowCol][col] + Imatrix[row][col];
				}
			}
		}

		for (int row=0; row<len; row++) {
			for (int col=0; col<len; col++) {
				stateValues[row] += Imatrix[row][col] * getReward(col);
			}
		}
	}
}
