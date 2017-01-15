package ai.dynamic.programming;

import java.util.Arrays;

import ai.Util;

public class MDPPOJacksCarRental<S, A> {
	public static class SiteState {
		public int A = 0;
		public int B = 0;

		SiteState (int a, int b) {
			this.A = a;
			this.B = b;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof SiteState)) return false;
			SiteState siteState = (SiteState) obj;
			return this.A == siteState.A && this.B == siteState.B;
		}
	}

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
	private final double[][] policy;
		// pi is a distribution over actions given states
	private double[] stateValues;
		// v is a vector of values for each state

	private boolean useMatrix = true;
	private boolean useOptimal = true;
	private boolean useCount = false;
	private double valueFunctionPrecision = 0.000001d;
	private double valueInitMax = 0.0d;
	private int valueFunctionAttempts = 0;

	private boolean optimalPolicy = false;

	public MDPPOJacksCarRental(int sizeS, int sizeA) {
		this(sizeS, sizeA, 0.0d);
	}
	public MDPPOJacksCarRental(int sizeS, int sizeA, double discount) {
		this(sizeS, sizeA, discount, true);
	}
	public MDPPOJacksCarRental(int sizeS, int sizeA, double discount, boolean useBellmanMatrix) {
		this.states = new Object[sizeS];
		this.actions = new Object[sizeA];
		this.stateTransitionProbabilityMatrix = new double[sizeS][sizeA][sizeS];
		this.discountFactor = discount;

		this.stateActionRewards = new double[sizeS][sizeA];
		this.policy = new double[sizeS][sizeA];
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
		// Pass' = P [St+1 = s' | St = s, At = a]
		return stateTransitionProbabilityMatrix[state_t][action_t][statePrime_t];
	}
	public double getProbablilityPolicyStateStatePrime (S state, S statePrime) {
		double return_Ppissp = 0.0d;
		for (int action_t=0; action_t<actions.length; action_t++) {
			A action = getAction(action_t);
			double policy = getStatePolicy(state, action);
			return_Ppissp += policy * getProbablilityStateStatePrime(state, action, statePrime);
		}
		return return_Ppissp;
	}
	public double getProbablilityPolicyStateStatePrime (int state_t, int statePrime_t) {
		double return_Ppissp = 0.0d;
		for (int action_t=0; action_t<actions.length; action_t++) {
			double policy = getStatePolicy(state_t, statePrime_t);
			return_Ppissp += policy * getProbablilityStateStatePrime(state_t, action_t, statePrime_t);
		}
		return return_Ppissp;
	}
	public void setProbablilityStateStatePrime(int state, int action_t, int statePrime, double probablility) {
		stateTransitionProbabilityMatrix[state][action_t][statePrime] = probablility;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}
	public void setDiscountFactor(double discount) {
		discountFactor = discount;
		optimalPolicy = false;
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
	public double getRewardPolicy(S state) {
		double return_Rpis = 0.0d;
		for (int action_t=0; action_t<actions.length; action_t++) {
			A action = getAction(action_t);
			double policy = getStatePolicy(state, action);
			return_Rpis += policy * getReward(state, action);
		}
		return return_Rpis;
	}
	public double getRewardPolicy(int state_t) {
		double return_Rpis = 0.0d;
		for (int action_t=0; action_t<actions.length; action_t++) {
			double policy = getStatePolicy(state_t, action_t);
			return_Rpis += policy * getReward(state_t, action_t);
		}
		return return_Rpis;
	}
	public void setReward(int state_t, int action_t, double reward) {
		stateActionRewards[state_t][action_t] = reward;
	}

	public void setUseBellmanMatrix(boolean useMatrix) {
		this.useMatrix = useMatrix;
	}
	public void setUseOptimalPolicy(boolean useOptimal, double optimalStartMax) {
		this.useMatrix = false;
		this.useOptimal = useOptimal;
		this.useCount = false;
		this.valueInitMax = optimalStartMax;
	}
	public void setUseCounterPolicy(int attempts) {
		useMatrix = false;
		useOptimal = false;
		useCount = true;
		valueFunctionAttempts = attempts;
	}

	public double getExpectedReturn(S[] plannedEpisode, int state_t) {
		// vpi(s) = Epi[Gt | St=s]
		S[] sub_episode = Arrays.copyOfRange(plannedEpisode, state_t, plannedEpisode.length);
		return getExpectedReturn(sub_episode);
	}

	public double getExpectedReturn(S[] plannedEpisode) {
		// Gt = Rt+1 + yRt+2 + ... = sum(y^k * Rt+k+1) { 0 <= k < inf)
		double return_Gt = 0.0;
		for (int k=0; k<plannedEpisode.length; k++) {
			S state = plannedEpisode[k];
			double reward_t = getRewardPolicy(state);
			return_Gt += Math.pow(discountFactor, k) * reward_t;
		}

		return return_Gt;
	}

	public double[][] getPolicy() {
		return policy;
	}
	public double getStatePolicy(S state, A action) {
		//pi(a|s) = P [At = a | St = s]
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return policy[state_t][action_t];
	}
	public double getStatePolicy(int state_t, int action_t) {
		//pi(a|s) = P [At = a | St = s]
		return policy[state_t][action_t];
	}
	public void setPolicy(int state_t, int action_t, double probability) {
		policy[state_t][action_t] = probability;
	}

	public void evaluatePolicy_Greedy() {
		// pi'(s) = argmax qpi(s, a) {a in A}
		if (optimalPolicy) return;
		optimalPolicy = true;
		for (int state_t=0; state_t<states.length; state_t++) {
			double[] policyOld = policy[state_t];
			policy[state_t] = new double[actions.length];

			double maxValue = valueInitMax;
			int maxCount = 0;
			for (int action_t=0; action_t<actions.length; action_t++) {
				double actionValue = getActionValue(state_t, action_t);
				if (actionValue > maxValue) {
					maxValue = actionValue;
					maxCount = 1;
					for(int action_tp=0; action_tp<actions.length; action_tp++) {
						policy[state_t][action_tp] = 0.0;
					}
					policy[state_t][action_t] = 1.0;
				}
				else {
					if (!Util.doubleIsDifferent(actionValue, maxValue, valueFunctionPrecision)) {
						maxCount++;
						for(int action_tp=0; action_tp<actions.length; action_tp++) {
							if (policy[state_t][action_tp] > 0.0) policy[state_t][action_tp] = 1.0d/maxCount;
						}
						policy[state_t][action_t] = 1.0/maxCount;
					}
				}
			}
			for (int action_t=0; action_t<actions.length; action_t++) {
				optimalPolicy &= policyOld[action_t] == policy[state_t][action_t];
			}
		}

		executeStateValueFunction();
	}

//	@SuppressWarnings("unchecked")
//	public double vpi(S s, int steps) {
//		// vpi(s) = Epi[Gt | St=s ]
//		Object[] sub_episode = new Object[steps];
//		sub_episode[0] = s;
//		A a = getA(0);
//		S sp = findSp(s, a);
//		for (int t=1; t<steps; t++) {
//			a = getA(0);
//			sp = findSp(sp, a);
//			sub_episode[t] = sp;
//		}
//		return Gt((S[])sub_episode);
//	}
//
//	public S findSp(S s, A a) {
//		int st = 0;
//		for (; st<S.length; st++) {
//			S St = getS(st);
//			if (St.equals(s)) break;
//		}
//
//		int at = 0;
//		for (; at<A.length; at++) {
//			A At = getA(at);
//			if (At.equals(a)) break;
//		}
//
//		double r = Math.random() + 0.000001d;
//		double sum = 0.0d;
//		for (int sp=0; sp<S.length; sp++) {
//			double pr = P[st][at][sp];
//			if (pr == 0.0d) continue;
//
//			if (pr == 1.0d) {
//				return getS(sp);
//			}
//			else {
//				if (sum < r && r <= sum+pr) {
//					return getS(sp);
//				}
//			}
//		}
//
//		return s;
//	}
//
//	@SuppressWarnings("unchecked")
//	public double qpi(S s, A a, int steps) {
//		// qpi(s, a) = Epi [Gt | St = s, At = a]
//		Object[] sub_episode = new Object[steps];
//		sub_episode[0] = s;
//		S sp = findSp(s, a);
//		for (int t=1; t<steps; t++) {
//			A ap = getA(0);
//			sp = findSp(sp, ap);
//			sub_episode[t] = sp;
//		}
//		return Gt((S[])sub_episode);
//	}

	public void executeStateValueFunction() {
		if (useMatrix) valueFunctionBellmanMatrix();
		else {
			if (useOptimal) valueFunctionBellmanOptimal();
			else if (useCount) valueFunctionBellmanEquationK();
			else valueFunctionBellmanEquation();
		}
	}
	public double getStateValue(S state) {
		return stateValues[getState_t(state)];
	}
	public double getStateValue(int state_t) {
		return stateValues[state_t];
	}
	public double getActionValue(S state, A action) {
		int state_t = getState_t(state);
		int action_t = getAction_t(action);
		return actionFunctionBellmanOptimal (state_t, action_t);
	}
	public double getActionValue(int state_t, int action_t) {
		return actionFunctionBellmanOptimal (state_t, action_t);
	}

	private void valueFunctionBellmanEquation () {
		// vpi(s) = sum(pi(a|s)qpi(s,a))
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
					double policy = getStatePolicy(state_t, action_t);
					stateValue += policy * actionFunctionBellmanEquation(state_t, action_t);
				}
				stateValues[state_t] = stateValue;
				goAgain |= Util.doubleIsDifferent(currStateValue, stateValues[state_t], valueFunctionPrecision);
			}
		}
	}

	private double actionFunctionBellmanEquation (int state_t, int action_t) {
		// qpi(s, a) = Ras + y * sum(Pass'v(s')  {s' in S}
		double stateValue = 0.0d;
		for (int statePrime_t=0; statePrime_t<states.length; statePrime_t++) {
			stateValue += getProbablilityStateStatePrime(state_t, action_t, statePrime_t) * stateValues[statePrime_t];
		}
		return getReward(state_t, action_t) + (discountFactor * stateValue);
	}

	private void valueFunctionBellmanOptimal () {
		// v*(s) = max(q*(s, a)) {a in A}
		stateValues = new double[stateValues.length];

		boolean goAgain = true;
		valueFunctionAttempts = 0;
		while(goAgain) {
			goAgain = false;
			valueFunctionAttempts++;

			for (int state_t=0; state_t<states.length; state_t++) {
				double max = valueInitMax;
				double currStateValue = stateValues[state_t];
				for (int action_t=0; action_t<actions.length; action_t++) {
					double optimal = actionFunctionBellmanOptimal(state_t, action_t);
					max = optimal > max ? optimal : max;
				}
				stateValues[state_t] = max;
				goAgain |= Util.doubleIsDifferent(currStateValue, stateValues[state_t], valueFunctionPrecision);
			}
		}
	}

	private double actionFunctionBellmanOptimal (int state_t, int action_t) {
		// q*(s, a) = Ras + y * sum(Pass'v*(s')  {s' in S}
		double stateValue = 0.0d;
		for (int statePrime_t=0; statePrime_t<states.length; statePrime_t++) {
			stateValue += getProbablilityStateStatePrime(state_t, action_t, statePrime_t) * stateValues[statePrime_t];
		}
		return getReward(state_t, action_t) + (discountFactor * stateValue);
	}

	private void valueFunctionBellmanEquationK () {
		// vk+1(s) = sum(pi(a|s)Ras + y * sum(Pass'vk(s')  {s' in S}))
		stateValues = new double[stateValues.length];

		for (int k=0; k<valueFunctionAttempts; k++) {
			double stateValues_bk[] = Arrays.copyOf(stateValues, stateValues.length);
			for (int state_t=0; state_t<states.length; state_t++) {
				double stateValue = 0.0d;
				for (int action_t=0; action_t<actions.length; action_t++) {
					double policy = getStatePolicy(state_t, action_t);
					double reward = getReward(state_t, action_t);
					double actionValue = 0.0d;
					for (int statePrime_t=0; statePrime_t<states.length; statePrime_t++) {
						double return_Pass = getProbablilityStateStatePrime(state_t, action_t, statePrime_t);
						actionValue += return_Pass * stateValues_bk[statePrime_t];
					}
					stateValue += policy * (reward + discountFactor * actionValue);
				}
				stateValues[state_t] = stateValue;
			}
		}
	}

	private void valueFunctionBellmanMatrix() {
		// vpi = (I - yPpi)^-1 Rpi
		int len = states.length;
		stateValues = new double[stateValues.length];

		double[][] Imatrix = Util.getIdentityMatrix(len);
		double[][] matrix = new double[len][len];
		for (int row=0; row<len; row++) {
			for (int col=0; col<len; col++) {
				for (int action_t=0; action_t<actions.length; action_t++) {
					matrix[row][col] += discountFactor * stateTransitionProbabilityMatrix[row][action_t][col] * getStatePolicy(row, action_t);
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
					stateValues[row] += Imatrix[row][col] * getReward(col, action_t) * getStatePolicy(col, action_t);
				}
			}
		}
	}
}
