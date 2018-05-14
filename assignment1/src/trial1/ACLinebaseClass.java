package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ACLinebaseClass {

	public static ArrayList ac2fn(Document doc1, ArrayList ACLinebaseList)
	{
		NodeList aclinelist = doc1.getElementsByTagName("cim:ACLineSegment");
		System.out.println("***** AC line base voltage ***** ");
		String BaseVoltID = null;

		
		for (int i = 0; i<aclinelist.getLength(); i++) 
		{
		Node acline = aclinelist.item(i);
		   
		Element element = (Element) acline;
		BaseVoltID = element.getElementsByTagName("cim:ConductingEquipment.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		
	    System.out.println("base Voltage rdfID : " + BaseVoltID);
	    
	    ACLinebaseList.add(BaseVoltID);
	    
		}
		return(ACLinebaseList);
	}
	
}
