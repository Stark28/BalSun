package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GenUnitClass {
	private String rdfID;
	private String name;
	private double MaxP, MinP;
	private String equipmentContainer;
	//
	public Element extractNode (Node node){	
		Element element = extractNode(node);
		rdfID = element.getAttribute("rdf:ID");
		name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();	
		MaxP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent());
		MinP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent());
		equipmentContainer = element.getElementsByTagName("cim:GeneratingUnit.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
		}
	//
	public String getrdfID() {
		return rdfID;
	}
	
	public String getNomValue() {
		return name;
	}
	public double getMaxP() {
	return MaxP;
	}
	public double getMinP() {
	return MinP;
	}
	public String getEqContID(){
		return equipmentContainer;
	}
}
