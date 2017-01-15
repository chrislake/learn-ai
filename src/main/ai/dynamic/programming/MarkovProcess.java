package ai.dynamic.programming;

public class MarkovProcess<S> {
	private final Object[] states;
		// S is a (finite) set of states
	private final double[][] stateTransitionProbabilityMatrix;
		// P is a state transition probability matrix
		// Pss' = P [St+1 = s' | St = s]

	public MarkovProcess(int size) {
		this.states = new Object[size];
		this.stateTransitionProbabilityMatrix = new double[size][size];
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
}
