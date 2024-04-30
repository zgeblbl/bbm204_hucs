import java.util.*;

// Class representing the Mission Synthesis
public class MissionSynthesis {

    // Private fields
    private final List<MolecularStructure> humanStructures; // Molecular structures for humans
    private final ArrayList<MolecularStructure> diffStructures; // Anomalies in Vitales structures compared to humans
    private List<Molecule> selectedMoleculesHuman = new ArrayList<>();
    private List<Molecule> selectedMoleculesAnomaly = new ArrayList<>();

    // Constructor
    public MissionSynthesis(List<MolecularStructure> humanStructures, ArrayList<MolecularStructure> diffStructures) {
        this.humanStructures = humanStructures;
        this.diffStructures = diffStructures;
    }

    // Method to synthesize bonds for the serum
    public List<Bond> synthesizeSerum() {
        List<Bond> serum = new ArrayList<>();
        List<Bond> allBonds = new ArrayList<>();
        List<Molecule> selectedMolecules = new ArrayList<>();
        for (MolecularStructure humanStructure : humanStructures){
            int minBondStrength = Integer.MAX_VALUE;
            Molecule selectedMolecule = null;
            for (Molecule molecule : humanStructure.getMolecules()){
                if (molecule.getBondStrength() < minBondStrength){
                    minBondStrength = molecule.getBondStrength();
                    selectedMolecule = molecule;
                }
            }
            selectedMoleculesHuman.add(selectedMolecule);
            selectedMolecules.add(selectedMolecule);
        }
        for (MolecularStructure diffStructure : diffStructures){
            int minBondStrength = Integer.MAX_VALUE;
            Molecule selectedMolecule = null;
            for (Molecule molecule : diffStructure.getMolecules()){
                if (molecule.getBondStrength() < minBondStrength){
                    minBondStrength = molecule.getBondStrength();
                    selectedMolecule = molecule;
                }
            }
            selectedMoleculesAnomaly.add(selectedMolecule);
            selectedMolecules.add(selectedMolecule);
        }


        for (int i = 0; i < selectedMolecules.size(); i++) {
            for (int j = i+1; j < selectedMolecules.size(); j++){
                double strength = (selectedMolecules.get(i).getBondStrength() + selectedMolecules.get(j).getBondStrength()) / 2.0;

                if (Integer.parseInt(selectedMolecules.get(i).getId().substring(1)) > Integer.parseInt(selectedMolecules.get(j).getId().substring(1))){
                    Bond bond = new Bond(selectedMolecules.get(i), selectedMolecules.get(j), strength);
                    allBonds.add(bond);
                }else{
                    Bond bond = new Bond(selectedMolecules.get(j), selectedMolecules.get(i), strength);
                    allBonds.add(bond);
                }
            }
        }
        Collections.sort(allBonds, new Comparator<Bond>() {
            @Override
            public int compare(Bond bond1, Bond bond2) {
                return Double.compare(bond1.getWeight(), bond2.getWeight());
            }
        });
        Set<Molecule> molAdded2Serum = new HashSet<>();
        for (Bond bond : allBonds){
            if (!molAdded2Serum.contains(bond.getFrom()) || !molAdded2Serum.contains(bond.getTo())){
                serum.add(bond);
                molAdded2Serum.add(bond.getFrom());
                molAdded2Serum.add(bond.getTo());
            }
        }
        //Collections.sort(selectedMolecules, Comparator.comparing(Molecule::getId));

        /* YOUR CODE HERE */
        return serum;
    }

    // Method to print the synthesized bonds
    public void printSynthesis(List<Bond> serum) {
        System.out.print("Typical human molecules selected for synthesis: ");
        List<String> humanMoleculeId = new ArrayList<>();
        List<String> anomalyMoleculeId = new ArrayList<>();
        for (Molecule humanMolecule : selectedMoleculesHuman){
            humanMoleculeId.add(humanMolecule.getId());
        }
        for (Molecule anomalyMolecule : selectedMoleculesAnomaly){
            anomalyMoleculeId.add(anomalyMolecule.getId());
        }
        System.out.println(humanMoleculeId);
        System.out.println("Vitales molecules selected for synthesis: " + anomalyMoleculeId);
        System.out.println("Synthesizing the serum...");
        double sumStrength = 0;
        for (Bond bond : serum){
            sumStrength += bond.getWeight();
            System.out.println(String.format("Forming a bond between %s - %s with strength %.2f", bond.getFrom(), bond.getTo(), bond.getWeight()));
        }
        System.out.println(String.format("The total serum bond strength is %.2f", sumStrength));
        /* YOUR CODE HERE */
    }
}
