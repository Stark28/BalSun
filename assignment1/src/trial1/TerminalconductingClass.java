package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TerminalconductingClass{
	
public static ArrayList terfn(Document doc1, ArrayList TerminalconductingList)
{
	NodeList terminallist = doc1.getElementsByTagName("cim:Terminal");
	System.out.println("***** Terminal ***** ");
	String conductingequipmentID;
	//String connectivityNodeID;

	
	for (int i = 0; i<terminallist.getLength(); i++) 
	{
	Node terminal = terminallist.item(i);
	int a = i + 1;
	   
	Element element = (Element) terminal;
	conductingequipmentID = element.getElementsByTagName("cim:Terminal.ConductingEquipment").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	//connectivityNodeID = element.getElementsByTagName("cim:Terminal.ConnectivityNode").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	
	System.out.println("Terminal " + a + " : " );
    System.out.println("Conducting Equipment rdfID : " + conductingequipmentID);
   // System.out.println("ConnectivityNode rdfID : " + connectivityNodeID);
    

    TerminalconductingList.add(conductingequipmentID);
    //TerminalList.add(connectivityNodeID);
    
	}
	return(TerminalconductingList);
}
}