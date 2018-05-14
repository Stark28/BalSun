package trial1;


import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList; 

public class SubstationClass{
	
public static ArrayList substationfn(Document doc1, ArrayList SubstationList)
{
	NodeList substationlist = doc1.getElementsByTagName("cim:Substation");
	System.out.println("***** Substation ***** ");
	String rdfID = null;
	String name; 
	String regionID;
	
	for (int i = 0; i<substationlist.getLength(); i++) 
	{
	Node Subs = substationlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) Subs;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	regionID = element.getElementsByTagName("cim:Substation.Region").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Substation " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("RegionID : " + regionID);
    
    SubstationList.add(rdfID);
    SubstationList.add(name);
    SubstationList.add(regionID);
    
}
	return(SubstationList);
}	 
}
