package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SynchMachineClass {
	private String rdfID;
	private String name;
    private double ratedS, ActivePower, ReactivePower;
    private String GenUnitID, RegControlID;
	//
	public Element extractNode (Node node){	
		Element element = extractNode(node);
		 rdfID = element.getAttribute("rdf:ID");
		 name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();	
		ratedS = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent());
		GenUnitID = element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		RegControlID = element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
		}
	//
	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		ActivePower = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.p").item(0).getTextContent());
		ReactivePower = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.q").item(0).getTextContent());
		return element;
	}
	//
	public String getrdfID() {
	return rdfID;
	}
	
	public String getNomValue() {
	return name;
	}
	public double getRatedS() {
	return ratedS;
	}
	public double getActiveP() {
	return ActivePower;
	}
	public double getReactiveP() {
	return ReactivePower;
	}
	public String getGenUnitID() {
	return GenUnitID;
	}
	public String getRegContID() {
	return RegControlID;
	}
}