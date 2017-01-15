package ai;

import ai.dynamic.programming.MDP;

public class TemporalDifference<S, A> extends MDP<S, A> {
	private double alpha;		// N(s) = N(s) + 1

	public TemporalDifference(int sizeS, int sizeA) {
		this(sizeS, sizeA, 0.0d);
	}

	public TemporalDifference(int sizeS, int sizeA, double discount) {
		this(sizeS, sizeA, discount, 0.1d);
	}

	public TemporalDifference(int sizeS, int sizeA, double discount, double meanStep) {
		super(sizeS, sizeA);
		this.alpha = meanStep;
	}

	public void evaluateValueFuntion(int state_t, int statePrime_t) {
		valueFuntionEverytime(state_t, statePrime_t);
	}

	public void setMeanStep(double delta) {
		alpha = delta;
	}

	public void valueFuntionEverytime(int state_t, int statePrime_t) {
		// V(s) = Vpi(s) as N(s) = inf
		double prevMean = getStateValue(state_t);
		double tdTarget = getRewardPolicy(state_t) + getDiscount() * getStateValue(statePrime_t);
		double tdError = tdTarget - prevMean;
		double mean = prevMean + (alpha * tdError);
		setStateValue(state_t, mean);
	}
}
