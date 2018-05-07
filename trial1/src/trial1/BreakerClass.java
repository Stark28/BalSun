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
	System.out.println("***** Breaker ***** ");
	String rdfID = null;
	String name; 
	String state;
	String equipmentContainer;

	
	for (int i = 0; i<breakerlist.getLength(); i++) 
	{
	Node breaker1 = breakerlist.item(i);
	   
	Element element = (Element) breaker1;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	state = element.getElementsByTagName("cim:Switch.normalOpen").item(0).getTextContent();
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("State : " + state);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    
    BreakerList.add(rdfID);
    BreakerList.add(name);
    BreakerList.add(state);
    BreakerList.add(equipmentContainer);
    
	}
	return(BreakerList);
}
}