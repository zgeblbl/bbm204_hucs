import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Class representing the mission of Genesis
public class MissionGenesis {

    // Private fields
    private MolecularData molecularDataHuman; // Molecular data for humans
    private MolecularData molecularDataVitales; // Molecular data for Vitales

    // Getter for human molecular data
    public MolecularData getMolecularDataHuman() {
        return molecularDataHuman;
    }

    // Getter for Vitales molecular data
    public MolecularData getMolecularDataVitales() {
        return molecularDataVitales;
    }

    // Method to read XML data from the specified filename
    // This method should populate molecularDataHuman and molecularDataVitales fields once called
    public void readXML(String filename)  {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            List<Molecule> humanMolecularStructures = new ArrayList<>();
            List<Molecule> vitalesMolecularStructures = new ArrayList<>();
            NodeList humanNodeList = doc.getElementsByTagName("HumanMolecularData");
            Node humanNode = humanNodeList.item(0);
            if (humanNode.getNodeType() == Node.ELEMENT_NODE) {
                Element humanElement = (Element) humanNode;
                NodeList moleculeList = humanElement.getElementsByTagName("Molecule");
                for (int i = 0; i < moleculeList.getLength(); i++) {
                    Node moleculeNode = moleculeList.item(i);
                    if (moleculeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element moleculeElement = (Element) moleculeNode;
                        String id = moleculeElement.getElementsByTagName("ID").item(0).getTextContent();
                        int bondStrength = Integer.parseInt(moleculeElement.getElementsByTagName("BondStrength").item(0).getTextContent());
                        List<String> bonds = new ArrayList<>();
                        NodeList bondsList = moleculeElement.getElementsByTagName("MoleculeID");
                        for (int j = 0; j < bondsList.getLength(); j++) {
                            bonds.add(bondsList.item(j).getTextContent());
                        }
                        Molecule molecule = new Molecule(id, bondStrength, bonds);
                        humanMolecularStructures.add(molecule);
                    }
                }
            }
            NodeList vitalesNodeList = doc.getElementsByTagName("VitalesMolecularData");
            Node vitalesNode = vitalesNodeList.item(0);
            if (vitalesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element vitalesElement = (Element) vitalesNode;
                NodeList moleculeList = vitalesElement.getElementsByTagName("Molecule");
                for (int i = 0; i < moleculeList.getLength(); i++) {
                    Node moleculeNode = moleculeList.item(i);
                    if (moleculeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element moleculeElement = (Element) moleculeNode;
                        String id = moleculeElement.getElementsByTagName("ID").item(0).getTextContent();
                        int bondStrength = Integer.parseInt(moleculeElement.getElementsByTagName("BondStrength").item(0).getTextContent());
                        List<String> bonds = new ArrayList<>();
                        NodeList bondsList = moleculeElement.getElementsByTagName("MoleculeID");
                        for (int j = 0; j < bondsList.getLength(); j++) {
                            bonds.add(bondsList.item(j).getTextContent());
                        }
                        Molecule molecule = new Molecule(id, bondStrength, bonds);
                        vitalesMolecularStructures.add(molecule);
                    }
                }
            }
            molecularDataHuman = new MolecularData(humanMolecularStructures);
            molecularDataVitales = new MolecularData(vitalesMolecularStructures);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
