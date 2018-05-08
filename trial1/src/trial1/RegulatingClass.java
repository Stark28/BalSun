package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RegulatingClass{
	
public static ArrayList regfn(Document doc1,Document doc2, ArrayList RegulatingList)
{
	NodeList reglist = doc1.getElementsByTagName("cim:RegulatingControl");
	NodeList reg2list = doc2.getElementsByTagName("cim:RegulatingControl");
	System.out.println("***** Regulating Control ***** ");
	String rdfID = null;
	String name; 
	String targetvalue = null;
	
	for (int i = 0; i<reglist.getLength(); i++) 
	{
	Node reg = reglist.item(i);
	   
	Element element = (Element) reg;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	
	for(int j=0; j<reg2list.getLength(); j++) {
		Element ssh=(Element) reg2list.item(j);
		String rdf_ID = ssh.getAttribute("rdf:about").replaceAll("#", "");
		if(rdf_ID.equals(rdfID) ) {
			targetvalue = ssh.getElementsByTagName("cim:RegulatingControl.targetValue").item(0).getTextContent();
		}		
	}

	
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("targetValue : " + targetvalue);
    
    RegulatingList.add(rdfID);
    RegulatingList.add(name);
    RegulatingList.add(targetvalue);
    
}
	return(RegulatingList);
}	 
}