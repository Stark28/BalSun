package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConnectivityNodecontainerClass{
	
public static ArrayList connect2fn(Document doc1, ArrayList ConnectivitycontainerNodeList)
{
	NodeList connect2list = doc1.getElementsByTagName("cim:ConnectivityNode");
	//System.out.println("***** Terminal ***** ");
	//String conductingequipmentID;
	String ConnectivityNodeContainer;

	
	for (int i = 0; i<connect2list.getLength(); i++) 
	{
	Node terminal = connect2list.item(i);
	int a = i + 1;
	   
	Element element = (Element) terminal;
	//conductingequipmentID = element.getElementsByTagName("cim:Terminal.ConductingEquipment").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	ConnectivityNodeContainer = element.getElementsByTagName("cim:ConnectivityNode.ConnectivityNodeContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	
	System.out.println("Terminal " + a + " : " );
    //System.out.println("Conducting Equipment rdfID : " + conductingequipmentID);
    System.out.println("Connectivity Container ID : " + ConnectivityNodeContainer);
    

   // TerminalconductingList.add(conductingequipmentID);
    ConnectivitycontainerNodeList.add(ConnectivityNodeContainer);
    
	}
	return(ConnectivitycontainerNodeList);
}
}