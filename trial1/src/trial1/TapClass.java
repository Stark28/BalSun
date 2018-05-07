package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TapClass{
	
public static ArrayList tapfn(Document doc1, ArrayList TapList)
{
	NodeList taplist = doc1.getElementsByTagName("cim:RatioTapChanger");
	System.out.println("***** Tap Changer ***** ");
	String rdfID = null;
	String name; 
	//String step;
	
	for (int i = 0; i<taplist.getLength(); i++) 
	{
	Node Tap = taplist.item(i);
	   
	Element element = (Element) Tap;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	//step = element.getElementsByTagName("cim:TapChanger.step").item(0).getTextContent();
	
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    //System.out.println("Step : " + step);
    
    TapList.add(rdfID);
    TapList.add(name);
    //TapList.add(step);
    
}
	return(TapList);
}	 
}
