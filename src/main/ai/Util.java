package ai;

import java.util.Arrays;

public class Util {
    public static boolean doubleIsSame(double d1, double d2, double delta) {
    	return !doubleIsDifferent(d1, d2, delta);
    }

    public static boolean doubleIsDifferent(double d1, double d2, double delta) {
        if (Double.compare(d1, d2) == 0) {
            return false;
        }
        if ((Math.abs(d1 - d2) <= delta)) {
            return false;
        }

        return true;
    }

    public static double[][] getIdentityMatrix(int size) {
		double[][] I = new double[size][size];
		for (int rc=0; rc<size; rc++) {
			I[rc][rc] = 1.0d;
		}
		return I;
    }

    public static int[] argMax(double[] array, double initMax, double delta) {
    	double max = initMax;
    	int amx_length = 0;
    	int[] argMax = new int[amx_length];
    	for (int k=0; k < array.length; k++) {
    		if (array[k] > max) {
    			amx_length = 1;
    	    	argMax = new int[amx_length];
    	    	argMax[0] = k;
    		}
    		else {
    			if (Util.doubleIsSame(array[k], max, delta)) {
    				amx_length++;
    				argMax = Arrays.copyOf(argMax, amx_length);
    			}
    	    	argMax[amx_length-1] = k;
    		}
    	}

    	return argMax;
    }
}
