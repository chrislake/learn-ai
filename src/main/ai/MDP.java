package ai;

import java.util.Arrays;

public class MDP<S, A> {
	private final Object[] states;
	private final int st_length;
		// S is a (finite) set of states
	private final Object[] actions;
	private final int at_length;
		// A is a finite set of actions
	private final double[][][] transitions;
		// P is a state transition probability matrix
		// Pass' = P [St+1 = s' | St = s, At = a]
	private double discount;
		// y is a discount factor, y E [0, 1]
	private final double[][] rewards;
		// R is a vector of rewards for each state / action combination
		// Ras = E [Rt+1 | St = s, At = a]
	private final double[][] policy;
	private double[][] savedPolicy;
		// pi is a distribution over actions given states
	private double[] stateValues;
		// v is a vector of values for each state
	private double[][] actionValues;

	public static enum ValueFunction {
		BELLMAN_EQUATION,
		BELLMAN_OPTIMAL,
		BELLMAN_ITERATIVE,
		BELLMAN_MATRIX
	}

	public static enum PolicyFunction {
		GREEDY
	}

	private ValueFunction valueFunction = ValueFunction.BELLMAN_MATRIX;
	private double valueFunctionMax = Double.NEGATIVE_INFINITY;
	private double valueFunctionDelta = 0.000001d;
	private long valueFunctionAttempts = 0;
	private boolean valueFunctionOptimal = false;

	private PolicyFunction policyFunction = PolicyFunction.GREEDY;
	private boolean policyFunctionOptimal = false;

	public MDP(int sizeS, int sizeA) {
		this(sizeS, sizeA, 0.0d);
	}

	public MDP(int sizeS, int sizeA, double discount) {
	    this.st_length = sizeS;
		this.states = new Object[st_length];
	    this.at_length = sizeA;
	    this.actions = new Object[at_length];
	    this.transitions = new double[sizeS][sizeA][sizeS];
	    this.discount = discount;

	    this.rewards = new double[sizeS][sizeA];
	    this.policy = new double[sizeS][sizeA];
	    this.savedPolicy = new double[sizeS][sizeA];
	    this.stateValues = new double[sizeS];
	    this.actionValues = new double[sizeS][sizeA];
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
		for (int t=0; t<st_length; t++) {
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
		for (int t=0; t<at_length; t++) {
			if (getAction(t).equals(action)) return t;
		}
		return -1;
	}
	public void setAction(int t, A action) {
		actions[t] = action;
	}

	public double[][][] getStateTransitionProbabilityMatrix () {
		return transitions;
	}
	public double getProbablilityStateStatePrime (S state, A action, S statePrime) {
		// Pass' = P [St+1 = s' | St = s, At = a]
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		int statePrime_t = getState_t(statePrime);
		return getProbablilityStateStatePrime (state_t, action_t, statePrime_t);
	}
	public double getProbablilityStateStatePrime (int state_t, int action_t, int statePrime_t) {
		// Pss' = P [St+1 = s' | St = s, At = a]
		return transitions[state_t][action_t][statePrime_t];
	}
	public double getProbablilityPolicyStateStatePrime (S state, S statePrime) {
		int state_t = getState_t(state);
		int statePrime_t = getState_t(statePrime);
		return getProbablilityPolicyStateStatePrime (state_t, statePrime_t);
	}
	public double getProbablilityPolicyStateStatePrime (int state_t, int statePrime_t) {
		double return_Ppissp = 0.0d;
		for (int action_t=0; action_t<at_length; action_t++) {
			double policy = getStatePolicy(state_t, statePrime_t);
			return_Ppissp += policy * getProbablilityStateStatePrime(state_t, action_t, statePrime_t);
		}
		return return_Ppissp;
	}
	public void setProbablilityStateStatePrime(int state, int action_t, int statePrime, double probablility) {
		transitions[state][action_t][statePrime] = probablility;
	}

	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
		this.policyFunctionOptimal = false;
		resetStateValues();
	}

	public double[][] getRewards() {
		return rewards;
	}
	public double getReward(S state, A action) {
		// R is a reward function
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return getReward(state_t, action_t);
	}
	public double getReward(int state_t, int action_t) {
		// R is a reward function
		return rewards[state_t][action_t];
	}
	public double getRewardPolicy(S state) {
		int state_t = getState_t(state);
		return getRewardPolicy(state_t);
	}
	public double getRewardPolicy(int state_t) {
		double return_Rpis = 0.0d;
		for (int action_t=0; action_t<at_length; action_t++) {
			double policy = getStatePolicy(state_t, action_t);
			return_Rpis += policy * getReward(state_t, action_t);
		}
		return return_Rpis;
	}
	public void setReward(int state_t, int action_t, double reward) {
		rewards[state_t][action_t] = reward;
	}

	public void setValueFunction(ValueFunction vf) {
		valueFunction = vf;
		policyFunctionOptimal = false;
		resetStateValues();
		resetActionValues();
	}
	public void setValueFunctionMax(double initialMax) {
		valueFunctionMax = initialMax;
	}
	public void setValueFunctionDelta(double delta) {
		valueFunctionDelta = delta;
		policyFunctionOptimal = false;
		resetStateValues();
		resetActionValues();
	}
	public void setValueFunctionAttempts(long attempts) {
		valueFunctionAttempts = attempts;
		policyFunctionOptimal = false;
		resetStateValues();
		resetActionValues();
	}

	public void evaluateValueFunction() {
		switch(valueFunction) {
			case BELLMAN_EQUATION:
				valueFunctionBellmanEquation();
				break;
			case BELLMAN_MATRIX:
				valueFunctionBellmanMatrix();
				break;
			case BELLMAN_OPTIMAL:
				valueFunctionBellmanOptimal();
				break;
			case BELLMAN_ITERATIVE:
				valueFunctionBellmanIterative();
				break;
		}
	}
	public boolean isValueFunctionOptimal() {
		return valueFunctionOptimal;
	}

	public double[][] getPolicy() {
		return policy;
	}
	public double getStatePolicy(S state, A action) {
		//pi(a|s) = P [At = a | St = s]
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return getStatePolicy(state_t, action_t);
	}
	public double getStatePolicy(int state_t, int action_t) {
		//pi(a|s) = P [At = a | St = s]
		return policy[state_t][action_t];
	}
	public void setPolicy(int state_t, int action_t, double probability) {
		policy[state_t][action_t] = probability;
	}
	public void savePolicy() {
		savedPolicy = Arrays.copyOf(policy, policy.length);
	}
	public void restorePolicy() {
		for (int state_t=0; state_t<st_length; state_t++) {
			for (int action_t=0; action_t<at_length; action_t++) {
				setPolicy(state_t, action_t, savedPolicy[state_t][action_t]);
			}
		}
	}

	public void improvePolicy() {
		switch(policyFunction) {
			case GREEDY:
				improvePolicyGreedy();
				break;
		}
	}
	public boolean isPolicyFunctionOptimal() {
		return policyFunctionOptimal;
	}

	public double[] getStateValues() {
		return stateValues;
	}
	public double getStateValue(S state) {
		int state_t = getState_t(state);
		return getStateValue(state_t);
	}
	public double getStateValue(int state_t) {
		return stateValues[state_t];
	}
	private void setStateValue(int state_t, double value) {
		stateValues[state_t] = value;
	}
	public void resetStateValues() {
		stateValues = new double[st_length];
		valueFunctionOptimal = false;
	}

	public double[][] getActionValues() {
		return actionValues;
	}
	public double[] getStateActionValues(int state_t) {
		return actionValues[state_t];
	}
	public double getActionValue(S state, A action) {
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return getActionValue(state_t, action_t);
	}
	public double getActionValue(int state_t, int action_t) {
//		if (valueFunction.equals(ValueFunction.BELLMAN_MATRIX)) {
//			actionFunction(state_t, action_t);
//		}
		return actionValues[state_t][action_t];
	}
	private void setActionValue(int state_t, int action_t, double value) {
		actionValues[state_t][action_t] = value;
	}
	public void resetActionValues() {
		actionValues = new double[st_length][at_length];
	}

	private void valueFunctionBellmanEquation () {
		// vpi(s) = sum(pi(a|s)qpi(s,a))
		valueFunctionAttempts = 0;
		while(!valueFunctionOptimal) {
			valueFunctionOptimal = true;
			valueFunctionAttempts++;

			for (int state_t=0; state_t<st_length; state_t++) {
				double stateValue = 0.0d;
				double currStateValue = stateValues[state_t];
				for (int action_t=0; action_t<at_length; action_t++) {
					double policy = getStatePolicy(state_t, action_t);
					stateValue += policy * actionFunction(state_t, action_t);
				}
				setStateValue(state_t, stateValue);
				valueFunctionOptimal &= Util.doubleIsSame(currStateValue, getStateValue(state_t), valueFunctionDelta);
			}
		}
	}

	private void valueFunctionBellmanOptimal () {
		// v*(s) = max(q*(s, a)) {a in A}
		valueFunctionAttempts = 0;
		while(!valueFunctionOptimal) {
			valueFunctionOptimal = true;
			valueFunctionAttempts++;

			for (int state_t=0; state_t<st_length; state_t++) {
				double max = valueFunctionMax;
				double currStateValue = stateValues[state_t];
				for (int action_t=0; action_t<at_length; action_t++) {
					double optimal = actionFunction(state_t, action_t);
					max = optimal > max ? optimal : max;
				}
				setStateValue(state_t, max);
				valueFunctionOptimal &= Util.doubleIsSame(currStateValue, getStateValue(state_t), valueFunctionDelta);
			}
		}
	}

	private void valueFunctionBellmanIterative () {
		// vk+1(s) = sum(pi(a|s)Ras + y * sum(Pass'vk(s')  {s' in S}))
		for (int k=0; k<valueFunctionAttempts; k++) {
			valueFunctionOptimal = true;
			double stateValues_bk[] = Arrays.copyOf(stateValues, st_length);

			for (int state_t=0; state_t<st_length; state_t++) {
				double stateValue = 0.0d;
				double currStateValue = stateValues[state_t];
				for (int action_t=0; action_t<at_length; action_t++) {
					double policy = getStatePolicy(state_t, action_t);
					double reward = getReward(state_t, action_t);
					double actionValue = 0.0d;
					for (int statePrime_t=0; statePrime_t<st_length; statePrime_t++) {
						double return_Pass = getProbablilityStateStatePrime(state_t, action_t, statePrime_t);
						actionValue += return_Pass * stateValues_bk[statePrime_t];
					}
					setActionValue(state_t, action_t, reward + (discount * actionValue));
					stateValue += policy * getActionValue(state_t, action_t);
				}
				setStateValue(state_t, stateValue);
				valueFunctionOptimal &= Util.doubleIsSame(currStateValue, getStateValue(state_t), valueFunctionDelta);
			}
		}
	}

	private void valueFunctionBellmanMatrix() {
		// vpi = (I - yPpi)^-1 Rpi
		int len = st_length;
		resetStateValues();

		double[][] Imatrix = Util.getIdentityMatrix(len);
		double[][] matrix = new double[len][len];
		for (int row=0; row<len; row++) {
			for (int col=0; col<len; col++) {
				for (int action_t=0; action_t<at_length; action_t++) {
					matrix[row][col] += discount * getProbablilityStateStatePrime(row, action_t, col) * getStatePolicy(row, action_t);
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

		for (int action_t=0; action_t<at_length; action_t++) {
			for (int row=0; row<len; row++) {
				for (int col=0; col<len; col++) {
					setStateValue(row, getStateValue(row) + Imatrix[row][col] * getReward(col, action_t) * getStatePolicy(col, action_t));
				}
//				actionFunction(row, action_t);
			}
		}
		
		valueFunctionOptimal = true;
	}

	private double actionFunction (int state_t, int action_t) {
		// q(s, a) = Ras + y * sum(Pass'v(s')  {s' in S}
		double actionValue = 0.0d;
		for (int statePrime_t=0; statePrime_t<st_length; statePrime_t++) {
			actionValue += getProbablilityStateStatePrime(state_t, action_t, statePrime_t) * getStateValue(statePrime_t);
		}
		actionValue = getReward(state_t, action_t) + (discount * actionValue);
		setActionValue(state_t, action_t, actionValue);
		return actionValue;
	}


	private void improvePolicyGreedy() {
		// pi'(s) = argmax qpi(s, a) {a in A}
		if (policyFunctionOptimal) return;
		policyFunctionOptimal = true;
		valueFunctionOptimal = false;

		for (int state_t=0; state_t<st_length; state_t++) {
			double[] prevPolicy = policy[state_t];
			policy[state_t] = new double[at_length];

			double[] av = getStateActionValues(state_t);
			if (valueFunction.equals(ValueFunction.BELLMAN_MATRIX)) {
				for (int action_t=0; action_t<at_length; action_t++) {
					av[action_t] = actionFunction(state_t, action_t);
				}
			}

			int[] argMax = Util.argMax(av, valueFunctionMax, valueFunctionDelta);
			int amx_length = argMax.length;
			for (int k=0; k<amx_length; k++) {
				int action_t=argMax[k];
				setPolicy(state_t, action_t, 1.0d/amx_length);
				
			}

			for (int action_t=0; action_t<at_length; action_t++) {
				policyFunctionOptimal &= Util.doubleIsSame(prevPolicy[action_t], getStatePolicy(state_t, action_t), valueFunctionDelta);
			}
		}
		resetStateValues();
	}


}
