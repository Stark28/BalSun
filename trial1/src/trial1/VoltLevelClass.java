package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VoltLevelClass{
	
public static ArrayList voltlevelfn(Document doc1, ArrayList VoltLevelList)
{
	NodeList voltlevellist = doc1.getElementsByTagName("cim:VoltageLevel");
	System.out.println("***** Voltage Level ***** ");
	String rdfID = null;
	String name; 
	String subrdfID;
	String baseVrdfID;
	
	for (int i = 0; i<voltlevellist.getLength(); i++) 
	{
	Node Voltlv = voltlevellist.item(i);
	   
	Element element = (Element) Voltlv;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	subrdfID = element.getElementsByTagName("cim:VoltageLevel.Substation").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	baseVrdfID = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("Substation rdfID : " + subrdfID);
    System.out.println("Basevoltage rdfID : " + baseVrdfID);
    
    VoltLevelList.add(rdfID);
    VoltLevelList.add(name);
    VoltLevelList.add(subrdfID);
    VoltLevelList.add(baseVrdfID);
	}
	return(VoltLevelList);
}
}
