package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

  public class VoltLevelClass {
	
	private String rdfID;
	private String name;
	private String subrdfID;
	private String baseVrdfID;
	//	
    public Element extractNode (Node node){	
		Element element = extractNode(node);
		rdfID = element.getAttribute("rdf:ID");
		name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();		
		subrdfID = element.getElementsByTagName("cim:VoltageLevel.Substation").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		baseVrdfID = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
    
	public String getrdfID() {
		return rdfID;
	}
	
	public String getNomValue() {
		return name;
	}
	
    public String getSubrdfID(){
	return subrdfID;
	}

  public String getBaseVrdfID() {
	return baseVrdfID;
	}
}

