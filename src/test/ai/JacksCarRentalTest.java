package ai;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ai.MDPPOJacksCarRental.SiteState;

public class JacksCarRentalTest {

	private final static int sizeS = 21*21;
	private final static int sizeA = 11;
	private final SiteState[] states = new SiteState[sizeS];
	private final Integer[] actions = new Integer[sizeA];
	private final double[][][] stateTPM = new double[sizeS][sizeA][sizeS];
	private final double[][] rewards = new double[sizeS][sizeA];
	private final double[][] policy = new double[sizeS][sizeA];
	private static MDPPOJacksCarRental<SiteState, Integer> markovDecisionProcess;

	@BeforeClass
	public static void generatemarkovDecisionProcess() {
		markovDecisionProcess = new MDPPOJacksCarRental<SiteState, Integer>(sizeS, sizeA, 0.5d);

		for (int siteAQty=0; siteAQty<=20; siteAQty++) {
			for (int siteBQty=0; siteBQty<=20; siteBQty++) {
				int stateIndex = siteBQty + siteAQty * 21;
				markovDecisionProcess.setState(stateIndex, new SiteState(siteAQty, siteBQty));

				for (int action_t=0; action_t<=10; action_t++) {
					int action = -5 + action_t;
					markovDecisionProcess.setAction(action_t, action);

					int siteAQtyCOB = siteAQty - action;
					int siteBQtyCOB = siteBQty + action;
				    double siteAReward = 0.0d;
		    	    double siteACarsLeft[] = new double[21];
				    double siteBReward = 0.0d;
		    	    double siteBCarsLeft[] = new double[21];
					if (siteBQtyCOB < 0 || siteBQtyCOB > 20 || siteAQtyCOB < 0 || siteAQtyCOB > 20) {
						siteAQtyCOB = siteAQty;
						siteBQtyCOB = siteBQty;
						action = 0;
					}
					else {
						for (int siteARequests=0; siteARequests<=20; siteARequests++) {
							double probSiteARequests = poissonDistributionFuncion(3, siteARequests);
							int siteARental = Math.min(siteARequests, siteAQtyCOB);
							siteAReward += 10 * siteARental * probSiteARequests;
							for (int siteAReturns=0; siteAReturns<=20; siteAReturns++) {
								double probSiteAReturns = poissonDistributionFuncion(3, siteAReturns);
			    	            int carsLeft = Math.min(siteAQtyCOB - siteARental + siteAReturns, 20);
			    	            siteACarsLeft[carsLeft] += probSiteARequests*probSiteAReturns;
							}
						}
	
						for (int siteBRequests=0; siteBRequests<=20; siteBRequests++) {
							double probSiteBRequests = poissonDistributionFuncion(4, siteBRequests);
							int siteBRental = Math.min(siteBRequests, siteBQtyCOB);
							siteBReward += 10 * siteBRental * probSiteBRequests;
							for (int siteBReturns=0; siteBReturns<=20; siteBReturns++) {
								double probSiteBReturns = poissonDistributionFuncion(2, siteBReturns);
			    	            int carsLeft = Math.min(siteBQtyCOB - siteBRental + siteBReturns, 20);
			    	            siteBCarsLeft[carsLeft] += probSiteBRequests*probSiteBReturns;
							}
						}
					}

					markovDecisionProcess.setReward(stateIndex, action_t, siteAReward + siteBReward - 2*Math.abs(action));
					for (int siteAQtyNew=0; siteAQtyNew<=20; siteAQtyNew++) {
						for (int siteBQtyNew=0; siteBQtyNew<=20; siteBQtyNew++) {
							int stateIndexNew = siteBQtyNew + siteAQtyNew * 21;
							markovDecisionProcess.setProbablilityStateStatePrime(stateIndex, action_t, stateIndexNew, siteACarsLeft[siteAQtyNew] * siteBCarsLeft[siteBQtyNew]);
						}
					}
					
					markovDecisionProcess.setPolicy(stateIndex, action_t, 0.0d);
				}
			}
		}

		markovDecisionProcess.setDiscountFactor(0.9d);

//		for (int a=0; a<=20; a++) {
//			for (int b=0; b<=20; b++) {
//				Sites st = (Sites) mdpJCR.S[b+a*21];
//				System.out.print(String.format(" (%d, %d): {", st.A, st.B));
//				StringJoiner sj = new StringJoiner(", ");
//				for (int c=0; c<=10; c++) {
//					if (mdpJCR.SA[b+a*21][c]> 0.0d) sj.add(String.format("%s: %.14f", mdpJCR.A[c], mdpJCR.R[b+a*21][c]));
//				
//				}
//				System.out.println(String.format("%s},", sj.toString()));
//			}
//		}
	}

	private static double poissonDistributionFuncion(int lamda, int qty) {
		long factorial = 1;
		for (int n=qty; n>0; n--) {
			factorial *= n;
		}
		
		return (Math.pow(lamda, qty) * Math.exp(-lamda)) / (double)factorial;
	}

	@Test
	public void testStates() {
		for (int siteAQty=0; siteAQty<=20; siteAQty++) {
			for (int siteBQty=0; siteBQty<=20; siteBQty++) {
				int stateIndex = siteBQty + siteAQty * 21;
				states[stateIndex] = new SiteState(siteAQty, siteBQty);
			}
		}
		Assert.assertArrayEquals(states, markovDecisionProcess.getStates());
	}

	@Test
	public void testActions() {
		actions[0] = -5;
		actions[1] = -4;
		actions[2] = -3;
		actions[3] = -2;
		actions[4] = -1;
		actions[5] = 0;
		actions[6] = 1;
		actions[7] = 2;
		actions[8] = 3;
		actions[9] = 4;
		actions[10] = 5;
		Assert.assertArrayEquals(actions, markovDecisionProcess.getActions());
	}

	@Test
	public void testTPM() {
//		double[][][] stateTPM_l = Arrays.copyOf(markovDecisionProcess.getStateTransitionProbabilityMatrix(), sizeS);
//		for (int state_t=0; state_t<sizeS; state_t++) {
//			for (int action_t=0; action_t<sizeA; action_t++) {
//				for (int statePrime_t=0; statePrime_t<sizeS; statePrime_t++) {
//					if (Util.doubleIsDifferent(stateTPM_l[state_t][action_t][statePrime_t], 0.0d, 0.001)) {
//						SiteState s = markovDecisionProcess.getState(state_t);
//						SiteState sp = markovDecisionProcess.getState(statePrime_t);
//						System.out.println(String.format("%d,%d\t->\t%d,%d\t\t%.3f", s.A, s.B, sp.A, sp.B, stateTPM_l[state_t][action_t][statePrime_t]));
//					}
//				}
//			}
//			System.out.println();
//		}
		double poisson[][] = new double[21][4];
		for (int rq_rt=0;rq_rt<21;rq_rt++) {
			poisson[rq_rt][0] = poissonDistributionFuncion(3, rq_rt);
			poisson[rq_rt][1] = poisson[rq_rt][0];
			poisson[rq_rt][2] = poissonDistributionFuncion(4, rq_rt);
			poisson[rq_rt][3] = poissonDistributionFuncion(2, rq_rt);
//			System.out.println(String.format("%.15f\t%.15f\t%.15f\t%.15f", poisson[rq_rt][0], poisson[rq_rt][1], poisson[rq_rt][2], poisson[rq_rt][3]));
		}
//		double sum = 0.0;
//		for (int a=0;a<21;a++) {
//			for (int b=0;b<21;b++) {
//				for (int c=0;c<21;c++) {
//					for (int d=0;d<21;d++) {
//						sum += poisson[a][0] * poisson[b][1] * poisson[c][2] * poisson[d][3];
//					}
//				}
//			}
//		}
//		System.out.println(sum);

		double probsReqRetA[][] = new double[21][21];
		double probsReqRetB[][] = new double[21][21];
		for (int requestsB=0;requestsB<21;requestsB++) {
			for (int returnsB=0;returnsB<21;returnsB++) {
				probsReqRetB[requestsB][returnsB] = poisson[requestsB][2] * poisson[returnsB][3];
			}
		}

		double probsReqRetAB[][][][] = new double[21][21][21][21];
		for (int requestsA=0;requestsA<21;requestsA++) {
			for (int returnsA=0;returnsA<21;returnsA++) {
				probsReqRetA[requestsA][returnsA] = poisson[requestsA][0] * poisson[returnsA][1];

				for (int requestsB=0;requestsB<21;requestsB++) {
					for (int returnsB=0;returnsB<21;returnsB++) {
						probsReqRetAB[requestsA][returnsA][requestsB][returnsB] = probsReqRetA[requestsA][returnsA] * probsReqRetB[requestsB][returnsB];
					}
				}
			}
		}

		for (int siteA=0;siteA<21;siteA++){
			for (int siteB=0;siteB<21;siteB++){
				int stateIndex = siteB + siteA * 21;
				for (int action_t=0;action_t<sizeA;action_t++){
					Integer action = markovDecisionProcess.getAction(action_t);
					if (siteA-action >= 0 && siteA-action <= 20 && siteB+action >= 0 && siteB+action <= 20) {

						int _a = siteA-action;
						int _b = siteB+action;
						for (int requestsA=0;requestsA<21;requestsA++) {
							int rent_a = Math.min(requestsA, _a);
							for (int returnsA=0;returnsA<21;returnsA++) {
								int __a = Math.min(_a - rent_a + returnsA, 20);
								for (int requestsB=0;requestsB<21;requestsB++) {
									int rent_b = Math.min(requestsB, _b);
									for (int returnsB=0;returnsB<21;returnsB++) {
										int __b = Math.min(_b - rent_b + returnsB, 20);

										int statePrimeIndex = __b + __a * 21;
										stateTPM[stateIndex][action_t][statePrimeIndex] += probsReqRetAB[requestsA][returnsA][requestsB][returnsB];
									}
								}
							}
						}
					}
				}
			}
		}

		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				Assert.assertArrayEquals(stateTPM[state_t][action_t], markovDecisionProcess.getStateTransitionProbabilityMatrix()[state_t][action_t], 0.001d);

				SiteState state = markovDecisionProcess.getState(state_t);
				Integer action = markovDecisionProcess.getAction(action_t);
				
				double sum = 0.0d;
				for (int statePrime_t=0; statePrime_t<sizeS; statePrime_t++) {
					sum += stateTPM[state_t][action_t][statePrime_t];
//					System.out.print(String.format("%.5f\t", stateTPM[state_t][action_t][statePrime_t]));
				}

				if (state.A-action >= 0 && state.A-action <= 20 && state.B+action >= 0 && state.B+action <= 20) {
					Assert.assertTrue(String.format("%d,%d\t%d\t->\t%.5f", state.A, state.B, action, sum), !Util.doubleIsDifferent(sum, 1.0, 0.000001d));
				}
				else {
					Assert.assertTrue(!Util.doubleIsDifferent(sum, 0.0, 0.000001d));
				}
			}
		}
	}

	@Test
	public void testRewards() {
//		double[][] rewards_l = Arrays.copyOf(markovDecisionProcess.getRewards(), sizeS);
//		for (int state_t=0; state_t<sizeS; state_t++) {
//			for (int action_t=0; action_t<sizeA; action_t++) {
//				if (Util.doubleIsDifferent(rewards_l[state_t][action_t], 0.0d, 0.001)) {
//					SiteState state = markovDecisionProcess.getState(state_t);
//					Integer action = markovDecisionProcess.getAction(action_t);
//					System.out.println(String.format("%d,%d\t%d\t->\t%.10f", state.A, state.B, action, rewards_l[state_t][action_t]));
//				}
//			}
//			System.out.println();
//		}

		double poisson[][] = new double[21][4];
		for (int rq_rt=0;rq_rt<21;rq_rt++) {
			poisson[rq_rt][0] = poissonDistributionFuncion(3, rq_rt);
			poisson[rq_rt][1] = poisson[rq_rt][0];
			poisson[rq_rt][2] = poissonDistributionFuncion(4, rq_rt);
			poisson[rq_rt][3] = poissonDistributionFuncion(2, rq_rt);
//			System.out.println(String.format("%.15f\t%.15f\t%.15f\t%.15f", poisson[rq_rt][0], poisson[rq_rt][1], poisson[rq_rt][2], poisson[rq_rt][3]));
		}

		for (int siteA=0;siteA<21;siteA++){
			for (int siteB=0;siteB<21;siteB++){
				int stateIndex = siteB + siteA * 21;
				for (int action_t=0;action_t<sizeA;action_t++){
					Integer action = markovDecisionProcess.getAction(action_t);
					double rewardsAB[] = new double[2];
					if (siteA-action >= 0 && siteA-action <= 20 && siteB+action >= 0 && siteB+action <= 20) {
						int _a = siteA-action;
						int _b = siteB+action;
						double rewardsA[] = new double[21];
						double rewardsB[] = new double[21];
						for (int requests=0;requests<21;requests++) {
							int rent_a = Math.min(requests, _a);
							int rent_b = Math.min(requests, _b);
							rewardsA[requests] = 10*rent_a*poisson[requests][0];
							rewardsB[requests] = 10*rent_b*poisson[requests][2];
							rewardsAB[0] += rewardsA[requests];
							rewardsAB[1] += rewardsB[requests];
//							if (siteA==0 && siteB==5 && action.equals(-2))
//								System.out.println(String.format("%d\t%.15f\t%d\t%.15f", rent_a, rewardsA[requests], rent_b, rewardsB[requests]));
						}
//						if (siteA==0 && siteB==5 && action.equals(-2)) {
//							System.out.println(String.format("\t%.15f\t\t%.15f", rewardsAB[0], rewardsAB[1]));
//							System.out.println(String.format("\t\t\t\t\t%.15f", rewards[stateIndex][action_t]));
//						}
						rewards[stateIndex][action_t] = rewardsAB[0] + rewardsAB[1] - Math.abs(2*action);
					}
				}
			}
		}
		
		for (int state=0; state<sizeS; state++) {
			Assert.assertArrayEquals(rewards[state], markovDecisionProcess.getRewards()[state], 0.001d);
		}
	}

	@Test
	public void testPolicies() {
		for (int state_t=0; state_t<sizeS; state_t++) {
			Assert.assertArrayEquals(policy[state_t], markovDecisionProcess.getPolicy()[state_t], 0.001d);
			double sum = 0.0d;
			for (int action_t=0; action_t<sizeA; action_t++) {
				sum += policy[state_t][action_t];
			}
			Assert.assertEquals(sum, 0.0d, 0.000001d);
		}
	}

	@Test
	public void testDiscount10Greedy() {
		markovDecisionProcess.setUseBellmanMatrix(true);
		//markovDecisionProcess.setDiscountFactor(1.0d);

		printStateValues();
		printPolicy();
		System.out.println();
		System.out.println();
		markovDecisionProcess.evaluatePolicy_Greedy();
		printStateValues();
		printPolicy();
		System.out.println();
		System.out.println();
		markovDecisionProcess.evaluatePolicy_Greedy();
		printStateValues();
		printPolicy();
		System.out.println();
		System.out.println();
		markovDecisionProcess.evaluatePolicy_Greedy();
		printStateValues();
		printPolicy();
		System.out.println();
		System.out.println();
		markovDecisionProcess.evaluatePolicy_Greedy();
		printStateValues();
		printPolicy();
	}

	public void printStateValues() {
		for (int value_t=0; value_t<sizeS; value_t++) {
			System.out.print(String.format("%+5.2f", markovDecisionProcess.getStateValue(value_t)));
			System.out.print("\t");
			if (value_t%21 == 20) System.out.println();
		}
	}

	public void printPolicy() {
		for (int state_t=0; state_t<sizeS; state_t++) {
			for (int action_t=0; action_t<sizeA; action_t++) {
				if (markovDecisionProcess.getStatePolicy(state_t, action_t) > 0.0d) {
					System.out.print(markovDecisionProcess.getAction(action_t));
				}
			}
			System.out.print("\t");
			if (markovDecisionProcess.getState(state_t).B == 20) System.out.println();
		}
	}
}
