package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BaseVoltClass{
	
public static ArrayList basevoltfn(Document doc1, ArrayList BaseVoltageList)
{
	NodeList basevoltlist = doc1.getElementsByTagName("cim:BaseVoltage");
	System.out.println("***** Base Voltage ***** ");
	String rdfID = null;
	double nominalValue; 
	
	for (int i = 0; i<basevoltlist.getLength(); i++) 
	{
	Node BaseVol = basevoltlist.item(i);
	int a = i + 1;
	
	Element BVelement = (Element) BaseVol;
	rdfID = BVelement.getAttribute("rdf:ID");
	nominalValue = Double.parseDouble(BVelement.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent());
	
	System.out.println("Base Voltage " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Nominal Voltage : " + nominalValue);
    
    
    BaseVoltageList.add(rdfID);
    BaseVoltageList.add(nominalValue);
    
}
	return(BaseVoltageList);
}	 
}
