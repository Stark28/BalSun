package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ACLinerdfClass{
	
public static ArrayList ac1fn(Document doc1, ArrayList ACLinerdfIDList)
{
	NodeList aclinelist = doc1.getElementsByTagName("cim:ACLineSegment");
	System.out.println("***** AC line rdfID ***** ");
	String rdfID = null;
	
	for (int i = 0; i<aclinelist.getLength(); i++) 
	{
	Node BaseVol = aclinelist.item(i);
	
	Element BVelement = (Element) BaseVol;
	rdfID = BVelement.getAttribute("rdf:ID");
	
	System.out.println("Reference ID : " + rdfID);
    
	ACLinerdfIDList.add(rdfID);
    
}
	return(ACLinerdfIDList);
}	 
}