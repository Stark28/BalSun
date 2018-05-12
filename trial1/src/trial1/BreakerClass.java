package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BreakerClass{
	
public static ArrayList breakerfn(Document doc1, ArrayList BreakerList)
{
	NodeList breakerlist = doc1.getElementsByTagName("cim:Breaker");
	NodeList breakerbaselist = doc1.getElementsByTagName("cim:VoltageLevel");
	System.out.println("***** Breaker ***** ");
	String rdfID = null;
	String name; 
	boolean state;
	String equipmentContainer;
	String BaseVoltID = null;

	
	for (int i = 0; i<breakerlist.getLength(); i++) 
	{
	Node breaker1 = breakerlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) breaker1;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	state = Boolean.valueOf(element.getElementsByTagName("cim:Switch.normalOpen").item(0).getTextContent());
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	for(int j=0; j<breakerbaselist.getLength(); j++) {
		Element volt=(Element) breakerbaselist.item(j);
		String rdf_ID = volt.getAttribute("rdf:ID");
		if(rdf_ID.equals(equipmentContainer) ) {
			Node basevolt = volt.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0);
    		Element basevolt_ele = (Element) basevolt;
    		BaseVoltID = basevolt_ele.getAttribute("rdf:resource").replaceAll("#","");
		}		
	}
	
	System.out.println("Breaker " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("State : " + state);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    System.out.println("base Voltage rdfID : " + BaseVoltID);
    
    BreakerList.add(rdfID);
    BreakerList.add(name);
    BreakerList.add(state);
    BreakerList.add(equipmentContainer);
    BreakerList.add(BaseVoltID);
    
	}
	return(BreakerList);
}
}