package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

	public class PowerTransClass {
        private String rdfID;
        private String name;
    	private String equipmentContainer;
    	
    	public Element extractNode (Node node){
    		Element element =extractNode(node);
    		 rdfID = element.getAttribute("rdf:ID");
    		 name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();	
    		 equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
    		return element;
    	}
    	public String getrdfID() {
    		return rdfID;
    	}
    	
    	public String getNomValue() {
    		return name;
    	}
    	
    	public String getEqContID(){
    		return equipmentContainer;
    	}
	}
