package ai;

import java.util.Arrays;

import org.junit.Test;

public final class BlackJackTemporal {
	public static class State {
		int currentSum;
		int dealersCard;
		boolean useableAce;

		State(int sum, int dlr, boolean ace) {
			this.currentSum = sum;
			this.dealersCard = dlr;
			this.useableAce = ace;
		}

		@Override
		public String toString() {
			return String.format("Sum: %d   DLR: %d   ACE: %b", currentSum, dealersCard, useableAce);
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof State)) return false;
			State state = (State) obj;
			return this.currentSum == state.currentSum
					&& this.dealersCard == state.dealersCard
					&& this.useableAce == state.useableAce;
		}
	}

	int[] env_cards = new int[4];

	@Test
	public void play() {
		TemporalDifference<State, String> agent = new TemporalDifference<State, String>(200, 2);

		int state_t = 0;
		for (int sum=12; sum<22; sum++) {
			for (int dlr=1; dlr<11; dlr++) {
				agent.setState(state_t, new State(sum, dlr, false));
				agent.setState(state_t+1, new State(sum, dlr, true));

				if (agent.getState(state_t).currentSum>=20) {
					agent.setPolicy(state_t, 0, 1.0d);
					agent.setPolicy(state_t, 1, 0.0d);
					agent.setPolicy(state_t+1, 0, 1.0d);
					agent.setPolicy(state_t+1, 1, 0.0d);
				}
				else {
					agent.setPolicy(state_t, 0, 0.0d);
					agent.setPolicy(state_t, 1, 1.0d);
					agent.setPolicy(state_t+1, 0, 0.0d);
					agent.setPolicy(state_t+1, 1, 1.0d);
				}
				state_t += 2;
			}
		}
		agent.setAction(0, "STICK");
		agent.setAction(1, "TWIST");
		agent.setDiscount(1.0);
		agent.setMeanStep(0.005);

		int episode = 0;
		while (episode < 500000) {
			State state = getState();
			boolean bust = false;
			while (state.currentSum < 12) state.currentSum += twist();

			state_t = agent.getState_t(state);
			int statePrime_t = agent.getState_t(state);
			double sval = agent.getStatePolicy(state, "STICK");
			double tval = agent.getStatePolicy(state, "TWIST");
			while (tval > sval) {
				int card = twist();
				if (state.currentSum+card > 21) {
					if (state.useableAce) {
						state.currentSum -= 10;
						state.useableAce = false;
					}
					else {
						agent.setReward(state, "TWIST", -1.0d);
						bust = true;
						break;
					}
				}

				state.currentSum += card;
				statePrime_t = agent.getState_t(state);
				agent.evaluateValueFuntion(state_t, statePrime_t);
				state_t = agent.getState_t(state);
				sval = agent.getActionValue(state, "STICK");
				tval = agent.getActionValue(state, "TWIST");
			}
			if (!bust) {
				int dealer = state.dealersCard + env_cards[3];
				while (dealer < 17) dealer += twist();
				if (state.currentSum > dealer) agent.setReward(state, "STICK", 1.0d);
				else if (state.currentSum < dealer) agent.setReward(state, "STICK", -1.0d);
				agent.evaluateValueFuntion(state_t, statePrime_t);
			}

			episode++;
		}

		for (int sum=21; sum>11; sum--) {
			for (int dlr=1; dlr<11; dlr++) {
				System.out.print(agent.getStateValue(new State(sum, dlr, false)));
				if (dlr!=10) System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		for (int sum=21; sum>11; sum--) {
			for (int dlr=1; dlr<11; dlr++) {
				System.out.print(agent.getStateValue(new State(sum, dlr, true)));
				if (dlr!=10) System.out.print("\t");
			}
			System.out.println();
		}
	}

	public int twist() {
		int c_len = env_cards.length;
		env_cards = Arrays.copyOf(env_cards, c_len+1);
		env_cards[c_len] = (int)(Math.random() * 10)+1;
		return env_cards[c_len];
	}

	public State getState() {
		env_cards = new int[4];
		env_cards[0] = (int)(Math.random() * 10)+1;
		env_cards[1] = (int)(Math.random() * 10)+1;
		env_cards[2] = (int)(Math.random() * 10)+1;
		env_cards[3] = (int)(Math.random() * 10)+1;

		int sum = env_cards[0] + env_cards[1];
		int dlr = env_cards[2];
		boolean ace = (env_cards[0] == 1) || (env_cards[1] == 1);
		if (ace) sum+=10;
		State s = new State(sum, dlr, ace);

		return s;
	}
}
