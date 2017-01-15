package ai;

import ai.dynamic.programming.MDP;

public class MonteCarloAlpha<S, A> extends MDP<S, A> {
	private double alpha;		// N(s) = N(s) + 1

	public static enum Type {
		ONE_TIME,
		EVERY_TIME
	}
	private Type type = Type.EVERY_TIME;

	public MonteCarloAlpha(int sizeS, int sizeA) {
		this(sizeS, sizeA, 0.0d);
	}

	public MonteCarloAlpha(int sizeS, int sizeA, double discount) {
		this(sizeS, sizeA, discount, 0.1d);
	}

	public MonteCarloAlpha(int sizeS, int sizeA, double discount, double meanStep) {
		super(sizeS, sizeA);
		this.alpha = meanStep;
	}

	public void evaluateValueFuntion(int[] episode_steps) {
		switch(type) {
			case ONE_TIME:
				evaluateValueFuntionOnce(episode_steps);
				break;
			case EVERY_TIME:
				evaluateValueFuntionEverytime(episode_steps);
				break;
		}
	}

	public void setMeanStep(double delta) {
		alpha = delta;
	}

	public void evaluateValueFuntionOnce(int[] episode_steps) {
		// V(s) = Vpi(s) as N(s) = inf
		boolean[] flagged = new boolean[st_length];
		for (int t=0; t<episode_steps.length; t++) {
			int state_t = episode_steps[t];
			if (state_t == -1) return;
			if (flagged[state_t]) continue;
			flagged[state_t] = true;

			double prevMean = getStateValue(state_t);
			double mean = prevMean + (alpha * (getExpectedReturn(episode_steps, t) - prevMean));
			setStateValue(state_t, mean);
		}
	}

	public void evaluateValueFuntionEverytime(int[] episode_steps) {
		// V(s) = Vpi(s) as N(s) = inf
		for (int t=0; t<episode_steps.length; t++) {
			int state_t = episode_steps[t];
			if (state_t == -1) return;

			double prevMean = getStateValue(state_t);
			double mean = prevMean + (alpha * (getExpectedReturn(episode_steps, t) - prevMean));
			setStateValue(state_t, mean);
		}
	}
}
