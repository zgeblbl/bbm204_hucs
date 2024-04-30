import java.util.*;

// Class representing molecular data
public class MolecularData {

    // Private fields
    private final List<Molecule> molecules; // List of molecules

    // Constructor
    public MolecularData(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    // Getter for molecules
    public List<Molecule> getMolecules() {
        return molecules;
    }

    // Method to identify molecular structures
    // Return the list of different molecular structures identified from the input data
    private void dfs(String moleculeId, Set<String> visited, List<String> bondedMolecules) {
        visited.add(moleculeId);
        bondedMolecules.add(moleculeId);
        for (Molecule molecule : molecules) {
            if (molecule.getId().equals(moleculeId)) {
                for (String bondId : molecule.getBonds()) {
                    if (!visited.contains(bondId)) {
                        dfs(bondId, visited, bondedMolecules);
                    }
                }
                break;
            }
        }
    }
    public List<MolecularStructure> identifyMolecularStructures() {
        ArrayList<MolecularStructure> structures = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (Molecule molecule : molecules) {
            boolean added = false;
            if (!visited.contains(molecule.getId())) {
                for (String bond : molecule.getBonds()) {
                    for (Molecule bondMolecule : molecules){
                        if (bond.equals(bondMolecule.getId())) {
                            for (MolecularStructure structure : structures){
                                if (structure.getMolecules().contains(bondMolecule) && !structure.getMolecules().contains(molecule)){
                                    structure.addMolecule(molecule);
                                    added = true;
                                    break;
                                }
                            }
                        }

                    }
                }
                if (!added){
                    List<String> bondedMolecules = new ArrayList<>();
                    dfs(molecule.getId(), visited, bondedMolecules);
                    MolecularStructure structure = new MolecularStructure();
                    for (String id : bondedMolecules){
                        for (Molecule molecule1 : molecules){
                            if (molecule1.getId().equals(id)){
                                structure.getMolecules().add(molecule1);
                            }
                        }
                    }
                    structures.add(structure);
                }
            }
        }
        /* YOUR CODE HERE */
        return structures;
    }

    // Method to print given molecular structures
    public void printMolecularStructures(List<MolecularStructure> molecularStructures, String species) {
        if (species.equals("typical humans")){
            System.out.println(molecularStructures.size()+" molecular structures have been discovered in typical humans.");
            for (int i = 0; i < molecularStructures.size(); i++){
                System.out.println("Molecules in Molecular Structure "+(i+1)+": " + molecularStructures.get(i));
            }
        }
        else if (species.equals("Vitales individuals")){
            System.out.println(molecularStructures.size()+" molecular structures have been discovered in Vitales individuals.");
            for (int i = 0; i < molecularStructures.size(); i++){
                System.out.println("Molecules in Molecular Structure "+(i+1)+": " + molecularStructures.get(i));
            }
        }
        /* YOUR CODE HERE */ 

    }

    // Method to identify anomalies given a source and target molecular structure
    // Returns a list of molecular structures unique to the targetStructure only
    public static ArrayList<MolecularStructure> getVitalesAnomaly(List<MolecularStructure> sourceStructures, List<MolecularStructure> targeStructures) {
        ArrayList<MolecularStructure> anomalyList = new ArrayList<>();
        for (MolecularStructure vitalesStructure : targeStructures) {
            if (!sourceStructures.contains(vitalesStructure)){
                anomalyList.add(vitalesStructure);
            }
        }
        /* YOUR CODE HERE */


        return anomalyList;
    }

    // Method to print Vitales anomalies
    public void printVitalesAnomaly(List<MolecularStructure> molecularStructures) {
        System.out.println("Molecular structures unique to Vitales individuals:");
        for (MolecularStructure structure : molecularStructures){
            System.out.println(structure);
        }
        /* YOUR CODE HERE */ 

    }
}
