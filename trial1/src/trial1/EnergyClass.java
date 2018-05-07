package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnergyClass{
	
public static ArrayList energyfn(Document doc1,Document doc2, ArrayList SynchronousList)
{
	NodeList synlist = doc1.getElementsByTagName("cim:SynchronousMachine");
	NodeList syn2list = doc2.getElementsByTagName("cim:SynchronousMachine");
	System.out.println("***** Synchronous Machine ***** ");
	String rdfID = null;
	String name; 
	String ratedS;
	String GenUnitID;
	String RegControlID;
	String equipmentContainer;
	//String BaseVoltID;

	
	for (int i = 0; i<synlist.getLength(); i++) 
	{
	Node Syn = synlist.item(i);
	   
	Element element = (Element) Syn;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	ratedS = element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent();
	GenUnitID = element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	RegControlID = element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	//for(int j=0; j<synlist.getLength(); j++) {
	//	Element volt=(Element) synlist.item(j);
	//	String rdf_ID = volt.getAttribute("rdf:ID");
	//	if(rdf_ID.equals(equipmentContainer) ) {
	//		Node basevolt = volt.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0);
    //		Element basevolt_ele = (Element) basevolt;
    //		BaseVoltID = basevolt_ele.getAttribute("rdf:resource");
	//	}		
	//}
	
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("ratedS : " + ratedS);
    System.out.println("GeneratingUnit rdfID : " + GenUnitID);
    System.out.println("RegulatingControl rdfID : " + RegControlID);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    //System.out.println("base Voltage rdfID : " + BaseVoltID);
    
    SynchronousList.add(rdfID);
    SynchronousList.add(name);
    SynchronousList.add(ratedS);
    SynchronousList.add(GenUnitID);
    SynchronousList.add(RegControlID);
    SynchronousList.add(equipmentContainer);
    
	}
	return(SynchronousList);
}
}