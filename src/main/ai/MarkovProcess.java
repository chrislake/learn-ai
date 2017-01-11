package ai;

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
		return (S[]) this.states;
	}
	@SuppressWarnings("unchecked")
	public S getState(int t) {
		return (S) this.states[t];
	}
	public int getState_t(S state) {
		for (int t=0; t<this.states.length; t++) {
			if (getState(t).equals(state)) return t;
		}
		return -1;
	}
	public void setState(int t, S state) {
		this.states[t] = state;
	}

	public double[][] getStateTransitionProbabilityMatrix () {
		return this.stateTransitionProbabilityMatrix;
	}
	public double getProbablilityStateStatePrime (S state, S statePrime) {
		// Pss' = P [St+1 = s' | St = s]
		int t = getState_t(state);
		int tp = getState_t(statePrime);
		return this.stateTransitionProbabilityMatrix[t][tp];
	}
	public double getProbablilityStateStatePrime (int state_t, int statePrime_t) {
		// Pss' = P [St+1 = s' | St = s]
		return this.stateTransitionProbabilityMatrix[state_t][statePrime_t];
	}
	public void setProbablilityStateStatePrime(int state, int statePrime, double probablility) {
		this.stateTransitionProbabilityMatrix[state][statePrime] = probablility;
	}
}
