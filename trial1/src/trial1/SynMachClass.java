package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SynMachClass{
	
public static ArrayList synfn(Document doc1,Document doc2, ArrayList SynchronousList)
{
	NodeList synlist = doc1.getElementsByTagName("cim:SynchronousMachine");
	NodeList syn2list = doc2.getElementsByTagName("cim:SynchronousMachine");
	System.out.println("Generating Unit : ");
	String rdfID = null;
	String name; 
	String MaxP;
	String MinP;
	String equipmentContainer;

	
	for (int i = 0; i<generatinglist.getLength(); i++) 
	{
	Node Subs = generatinglist.item(i);
	   
	Element element = (Element) Subs;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	MaxP = element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent();
	MinP = element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent();
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
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