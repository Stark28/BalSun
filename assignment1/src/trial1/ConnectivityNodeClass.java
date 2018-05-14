package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConnectivityNodeClass{
	
public static ArrayList connectfn(Document doc1, ArrayList ConnectivityNodeList)
{
	NodeList connectivitylist = doc1.getElementsByTagName("cim:ConnectivityNode");
	System.out.println("***** Connectivity Node ***** ");
	String rdfID = null;
	
	for (int i = 0; i<connectivitylist.getLength(); i++) 
	{
	Node connect = connectivitylist.item(i);
	int a = i + 1;
	
	Element element = (Element) connect;
	rdfID = element.getAttribute("rdf:ID");
	
	
	System.out.println("Connectivity " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    
    
	ConnectivityNodeList.add(rdfID);
    
}
	return(ConnectivityNodeList);
}	 
}
