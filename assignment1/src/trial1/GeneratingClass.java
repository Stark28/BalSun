package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GeneratingClass{
	
public static ArrayList genfn(Document doc1, ArrayList GeneratingList)
{
	NodeList generatinglist = doc1.getElementsByTagName("cim:GeneratingUnit");
	System.out.println("***** Generating Unit ***** ");
	String rdfID = null;
	String name; 
	double MaxP;
	double MinP;
	String equipmentContainer;

	
	for (int i = 0; i<generatinglist.getLength(); i++) 
	{
	Node Gen = generatinglist.item(i);
	int a = i + 1;   
	
	Element element = (Element) Gen;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	MaxP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent());
	MinP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent());
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Generating Unit " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("MaxOperatingP : " + MaxP);
    System.out.println("MinOperatingP : " + MinP);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    
    GeneratingList.add(rdfID);
    GeneratingList.add(name);
    GeneratingList.add(MaxP);
    GeneratingList.add(MinP);
    GeneratingList.add(equipmentContainer);
    
	}
	return(GeneratingList);
}
}