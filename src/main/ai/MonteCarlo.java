package ai;

public class MonteCarlo {
	private long stateCount = 0;						// N(s) = N(s) + 1
	private double stateIncrementalReturn = 0.0d;		// S(s) = S(s) + Gt
	private double stateValue = 0.0d;					// V(s) = S(s) / N(s)
		// V(s) = Vpi(s) as N(s) = inf
	
}
