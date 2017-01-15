package ai;

import ai.dynamic.programming.MDP;

public class MonteCarlo<S, A> extends MDP<S, A> {
	private final int[] stateCounts;		// N(s) = N(s) + 1

	public static enum Type {
		ONE_TIME,
		EVERY_TIME
	}
	private Type type = Type.EVERY_TIME;

	public MonteCarlo(int sizeS, int sizeA) {
		super(sizeS, sizeA);
		this.stateCounts = new int[sizeS];
	}

	public void evaluateValueFuntion(int[] episode_steps) {
		switch(type) {
			case ONE_TIME:
				break;
			case EVERY_TIME:
				evaluateValueFuntionEverytime(episode_steps);
				break;
		}
	}

	public void evaluateValueFuntionEverytime(int[] episode_steps) {
		// V(s) = Vpi(s) as N(s) = inf
		for (int t=0; t<episode_steps.length; t++) {
			int state_t = episode_steps[t];
			if (state_t == -1) return;

			stateCounts[state_t]++;
			double prevMean = getStateValue(state_t);
			double mean = prevMean + ((getExpectedReturn(episode_steps, t) - prevMean) / stateCounts[state_t]);
			setStateValue(state_t, mean);
		}
	}
}
