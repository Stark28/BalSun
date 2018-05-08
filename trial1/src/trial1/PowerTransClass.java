package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PowerTransClass{
	
public static ArrayList powerfn(Document doc1, ArrayList PowerTransList)
{
	NodeList powerlist = doc1.getElementsByTagName("cim:PowerTransformer");
	System.out.println("***** Power Transformer ***** ");
	String rdfID = null;
	String name; 
	String equipmentContainer;
	
	for (int i = 0; i<powerlist.getLength(); i++) 
	{
	Node power = powerlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) power;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Power Transformer " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    
    PowerTransList.add(rdfID);
    PowerTransList.add(name);
    PowerTransList.add(equipmentContainer);
    
}
	return(PowerTransList);
}	 
}