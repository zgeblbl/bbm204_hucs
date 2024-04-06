//Ozge Bulbul 2220765008
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static String[] reader(String path) {
        int i = 0;
        try {
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws IOException {

        /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");

        String[] demandArray = reader(args[0]);
        String[] parts = demandArray[0].split(" ");
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int demandSum = 0;
        for (int i = 0; i < parts.length; i++) {
            numbers.add(Integer.parseInt(parts[i]));
            demandSum+=Integer.parseInt(parts[i]);
        }
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(numbers);
        OptimalPowerGridSolution optimalPowerGridSolution = powerGridOptimization.getOptimalPowerGridSolutionDP();
        System.out.println("The total number of demanded gigawatts: " + demandSum);
        System.out.println("Maximum number of satisfied gigawatts: " + optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands());
        System.out.println("Hours at which the battery bank should be discharged: " + optimalPowerGridSolution.getHoursToDischargeBatteriesForMaxEfficiency().stream().map(Object::toString).collect(Collectors.joining(", ")));
        System.out.println("The number of unsatisfied gigawatts: " + (demandSum - optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands()));

        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read 
        // the energy demands arriving per hour. Then, use this data to instantiate a 
        // PowerGridOptimization object. You need to call getOptimalPowerGridSolutionDP() method
        // of your PowerGridOptimization object to get the solution, and finally print it to STDOUT.
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");
        /** MISSION ECO-MAINTENANCE BELOW **/
        System.out.println("##MISSION ECO-MAINTENANCE##");
        // TODO: Your code goes here
        String[] lines = reader(args[1]);
        ArrayList<Integer> maintenanceTaskEnergyDemands = new ArrayList<>();
        int numESVs = 0;
        int energyCapacity = 0;
        if (lines != null && lines.length >= 2) {
            String[] esvInfo = lines[0].split(" ");
            numESVs = Integer.parseInt(esvInfo[0]);
            energyCapacity = Integer.parseInt(esvInfo[1]);
            String[] tasksArray = lines[1].split(" ");
            for (String task : tasksArray) {
                maintenanceTaskEnergyDemands.add(Integer.parseInt(task));
            }
        }
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(maintenanceTaskEnergyDemands);
        int maintenanceTasksAssignedToESVs = optimalESVDeploymentGP.getMinNumESVsToDeploy(numESVs, energyCapacity);
        if (maintenanceTasksAssignedToESVs == -1){
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }else{
            System.out.println("The minimum number of ESVs to deploy: " + maintenanceTasksAssignedToESVs);
        }

        for (int i = 0; i<maintenanceTasksAssignedToESVs;i++){
            System.out.println("ESV "+ (i+1) + " tasks: " + optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs().get(i));
        }
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements 
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.
        System.out.print("##MISSION ECO-MAINTENANCE COMPLETED##");
        //System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
