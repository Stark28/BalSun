package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RegControlClass  {
	private String rdfID;
	private String name;
    private double targetValue;

	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		rdfID = element.getAttribute("rdf:ID");
		name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();	
		targetValue = Double.parseDouble(element.getElementsByTagName("cim:RegulatingControl.targetValue").item(0).getTextContent());
		return element;
	}
	
	public String getrdfID() {
		return rdfID;
	}
	public String getNomValue() {
		return name;
	}
	public double getTargetValue(){
		return targetValue;
	}

}
