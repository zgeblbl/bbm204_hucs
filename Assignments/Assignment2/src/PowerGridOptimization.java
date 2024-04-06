import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {

        return amountOfEnergyDemandsArrivingPerHour;
    }
    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ D(j), E(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP() {
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        int[] sol = new int[N + 1];
        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();
        sol[0] = 0;
        hours.add(new ArrayList<>());
        for (int j = 1; j <= N; j++) {
            sol[j] = 0;
            int optimalI = 0;
            for (int i = 0; i < j; i++) {
                int temp = (int) (sol[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), Math.pow(j-i, 2)));
                if (temp > sol[j]) {
                    sol[j] = temp;
                    optimalI = i;
                }
            }
            ArrayList<Integer> hoursTemp = new ArrayList<>(hours.get(optimalI));
            hoursTemp.add(j);
            hours.add(hoursTemp);
        }
        return new OptimalPowerGridSolution(sol[N], hours.get(N));
    }

}
