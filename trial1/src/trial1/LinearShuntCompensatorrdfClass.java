package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LinearShuntCompensatorrdfClass{
	
public static ArrayList shuntfn(Document doc1, ArrayList LinearShuntCompensatorrdfIDList)
{
	NodeList shuntlist = doc1.getElementsByTagName("cim:LinearShuntCompensator");
	System.out.println("***** shunt capacitior rdfID ***** ");
	String rdfID = null;
	
	for (int i = 0; i<shuntlist.getLength(); i++) 
	{
	Node BaseVol = shuntlist.item(i);
	
	Element BVelement = (Element) BaseVol;
	rdfID = BVelement.getAttribute("rdf:ID");
	
	System.out.println("Reference ID : " + rdfID);
    
	LinearShuntCompensatorrdfIDList.add(rdfID);
    
}
	return(LinearShuntCompensatorrdfIDList);
}	 
}