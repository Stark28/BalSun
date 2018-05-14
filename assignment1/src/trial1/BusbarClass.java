package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BusbarClass{
	
public static ArrayList busbarfn(Document doc1, ArrayList BusbarList)
{
	NodeList busbarlist = doc1.getElementsByTagName("cim:BusbarSection");
	System.out.println("***** Busbar ***** ");
	String rdfID;
	String equipmentContainer;
	

	
	for (int i = 0; i<busbarlist.getLength(); i++) 
	{
	Node busb = busbarlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) busb;
	rdfID = element.getAttribute("rdf:ID");
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Busbar " + a);
	System.out.println("rdfID : " + rdfID);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    
    
    BusbarList.add(rdfID);
    BusbarList.add(equipmentContainer);

    
	}
	return(BusbarList);
}
}