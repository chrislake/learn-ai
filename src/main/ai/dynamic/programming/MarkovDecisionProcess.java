package ai.dynamic.programming;

import java.util.Arrays;

import ai.Util;

public class MarkovDecisionProcess<S, A> {
	private final Object[] states;
		// S is a (finite) set of states
	private final Object[] actions;
		// A is a finite set of actions
	private final double[][][] stateTransitionProbabilityMatrix;
		// P is a state transition probability matrix
		// Pass' = P [St+1 = s' | St = s, At = a]
	private double discountFactor;
		// y is a discount factor, y E [0, 1]
	private final double[][] stateActionRewards;
		// R is a vector of rewards for each state / action combination
		// Ras = E [Rt+1 | St = s, At = a]
	private double[] stateValues;
		// v is a vector of values for each state

	private boolean useMatrix = true;
	private double valueFunctionPrecision = 0.000001d;
	private int valueFunctionAttempts = 0;

	public MarkovDecisionProcess(int sizeS, int sizeA) {
		this(sizeS, sizeA, 0.0d);
	}
	public MarkovDecisionProcess(int sizeS, int sizeA, double discount) {
		this(sizeS, sizeA, discount, true);
	}
	public MarkovDecisionProcess(int sizeS, int sizeA, double discount, boolean useBellmanMatrix) {
	    this.states = new Object[sizeS];
	    this.actions = new Object[sizeA];
	    this.stateTransitionProbabilityMatrix = new double[sizeS][sizeA][sizeS];
	    this.discountFactor = discount;

	    this.stateActionRewards = new double[sizeS][sizeA];
	    this.stateValues = new double[sizeS];

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

	@SuppressWarnings("unchecked")
	public A[] getActions() {
		return (A[]) actions;
	}
	@SuppressWarnings("unchecked")
	public A getAction(int t) {
		return (A) actions[t];
	}
	public int getAction_t(A action) {
		for (int t=0; t<actions.length; t++) {
			if (getAction(t).equals(action)) return t;
		}
		return -1;
	}
	public void setAction(int t, A action) {
		actions[t] = action;
	}

	public double[][][] getStateTransitionProbabilityMatrix () {
		return stateTransitionProbabilityMatrix;
	}
	public double getProbablilityStateStatePrime (S state, A action, S statePrime) {
		// Pass' = P [St+1 = s' | St = s, At = a]
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		int statePrime_t = getState_t(statePrime);
		return stateTransitionProbabilityMatrix[state_t][action_t][statePrime_t];
	}
	public double getProbablilityStateStatePrime (int state_t, int action_t, int statePrime_t) {
		// Pss' = P [St+1 = s' | St = s, At = a]
		return stateTransitionProbabilityMatrix[state_t][action_t][statePrime_t];
	}
	public void setProbablilityStateStatePrime(int state, int action_t, int statePrime, double probablility) {
		stateTransitionProbabilityMatrix[state][action_t][statePrime] = probablility;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}
	public void setDiscountFactor(double discount) {
		discountFactor = discount;
		executeStateValueFunction();
	}

	public double[][] getRewards() {
		return stateActionRewards;
	}
	public double getReward(S state, A action) {
		// R is a reward function
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return stateActionRewards[state_t][action_t];
	}
	public double getReward(int state_t, int action_t) {
		// R is a reward function
		return stateActionRewards[state_t][action_t];
	}
	public void setReward(int state_t, int action_t, double reward) {
		stateActionRewards[state_t][action_t] = reward;
	}

	public void setUseBellmanMatrix(boolean useMatrix) {
		this.useMatrix = useMatrix;
	}

	public double getExpectedReturn(S[] plannedStates, A[] plannedActions, int state_t) {
		// v(s) = Epi[Gt | St=s]
		S[] sub_states = Arrays.copyOfRange(plannedStates, state_t, plannedStates.length);
		A[] sub_actions = Arrays.copyOfRange(plannedActions, state_t, plannedActions.length);
		return getExpectedReturn(sub_states, sub_actions);
	}

	public double getExpectedReturn(S[] plannedStates, A[] plannedActions) {
		// Gt = Rt+1 + yRt+2 + ... = sum(y^k * Rt+k+1) { 0 <= k < inf)
		double return_Gt = 0.0;
		for (int k=0; k<plannedStates.length; k++) {
			S state = plannedStates[k];
			A action = plannedActions[k];
			double reward_t = getReward(state, action);
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
		// v(s) = Ras + y * sum(Pass'v(s') { s' in S)
		stateValues = new double[stateValues.length];

		boolean goAgain = true;
		valueFunctionAttempts = 0;
		while(goAgain) {
			goAgain = false;
			valueFunctionAttempts++;

			for (int state_t=0; state_t<states.length; state_t++) {
				double stateValue = 0.0d;
				double currStateValue = stateValues[state_t];
				for (int action_t=0; action_t<actions.length; action_t++) {
					for (int statePrime_t=0; statePrime_t<states.length; statePrime_t++) {
						stateValue += getProbablilityStateStatePrime(state_t, action_t, statePrime_t) * stateValues[statePrime_t];
					}
					stateValue += getReward(state_t, action_t) + (discountFactor * stateValue);
				}
				stateValues[state_t] = stateValue;
				goAgain |= Util.doubleIsDifferent(currStateValue, stateValues[state_t], valueFunctionPrecision);
			}
		}
	}

	private void valueFunctionBellmanMatrix() {
		// vpi = (I − yPpi)^−1 Rpi
		int len = states.length;
		stateValues = new double[stateValues.length];

		double[][] Imatrix = Util.getIdentityMatrix(len);
		double[][] matrix = new double[len][len];
		for (int row=0; row<len; row++) {
			for (int col=0; col<len; col++) {
				for (int action_t=0; action_t<actions.length; action_t++) {
					matrix[row][col] += discountFactor * stateTransitionProbabilityMatrix[row][action_t][col];
				}
				matrix[row][col] = Imatrix[row][col] - matrix[row][col];
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

		for (int action_t=0; action_t<actions.length; action_t++) {
			for (int row=0; row<len; row++) {
				for (int col=0; col<len; col++) {
					stateValues[row] += Imatrix[row][col] * getReward(col, action_t);
				}
			}
		}
	}
}
