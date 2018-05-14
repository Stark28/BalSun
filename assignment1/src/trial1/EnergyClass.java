package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnergyClass{
	
public static ArrayList energyfn(Document doc1,Document doc2, ArrayList EnergyList)
{
	NodeList enerlist = doc1.getElementsByTagName("cim:EnergyConsumer");
	NodeList enerbaselist = doc1.getElementsByTagName("cim:VoltageLevel");
	NodeList ener2list = doc2.getElementsByTagName("cim:EnergyConsumer");
	System.out.println("***** Energy Consumer ***** ");
	String rdfID = null;
	String name; 
	String equipmentContainer;
	String BaseVoltID = null;
	double P = 0;
	double Q = 0;

	
	for (int i = 0; i<enerlist.getLength(); i++) 
	{
	Node Ener = enerlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) Ener;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	for(int j=0; j<enerbaselist.getLength(); j++) {
		Element volt=(Element) enerbaselist.item(j);
		String rdf_ID = volt.getAttribute("rdf:ID");
		if(rdf_ID.equals(equipmentContainer) ) {
			Node basevolt = volt.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0);
    		Element basevolt_ele = (Element) basevolt;
   		BaseVoltID = basevolt_ele.getAttribute("rdf:resource").replaceAll("#","");
		}		
	}
	
	for(int j=0; j<ener2list.getLength(); j++) {
		Element ssh=(Element) ener2list.item(j);
		String rdf_ID = ssh.getAttribute("rdf:about").replaceAll("#", "");
		if(rdf_ID.equals(rdfID) ) {
            P = Double.parseDouble(ssh.getElementsByTagName("cim:EnergyConsumer.p").item(0).getTextContent());
            Q = Double.parseDouble(ssh.getElementsByTagName("cim:EnergyConsumer.q").item(0).getTextContent());
		}		
	}

	
	System.out.println("Energy Consumer " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("P : " + P);
    System.out.println("Q : " + Q);
    System.out.println("Equipmentcontainer rdfID : " + equipmentContainer);
    System.out.println("base Voltage rdfID : " + BaseVoltID);
    
    EnergyList.add(rdfID);
    EnergyList.add(name);
    EnergyList.add(P);
    EnergyList.add(Q);
    EnergyList.add(equipmentContainer);
    EnergyList.add(BaseVoltID);
    
	}
	return(EnergyList);
}
}