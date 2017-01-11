package ai;

public class Util {
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
}
