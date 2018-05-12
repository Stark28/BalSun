package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PowerTransEndClass{
	
public static ArrayList powerendfn(Document doc1, ArrayList PowerTransEndList)
{
	NodeList powerendlist = doc1.getElementsByTagName("cim:PowerTransformerEnd");
	System.out.println("***** Power Transformer End ***** ");
	String rdfID = null;
	String name; 
	double trans_r;
	double trans_x;
	String transrdfID;
	String basevoltrdfID;
	
	
	for (int i = 0; i<powerendlist.getLength(); i++) 
	{
	Node power = powerendlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) power;
	rdfID = element.getAttribute("rdf:ID");
	name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	trans_r = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent());
	trans_x = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent());
	transrdfID = element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	basevoltrdfID = element.getElementsByTagName("cim:TransformerEnd.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
	
	System.out.println("Power Transformer End " + a + " : " );
	System.out.println("Reference ID : " + rdfID);
    System.out.println("Name : " + name);
    System.out.println("Transformer.r : " + trans_r);
    System.out.println("Transformer.x : " + trans_x);
    System.out.println("Transformer rdfID : " + transrdfID);
    System.out.println("Basevoltge rdfID : " + basevoltrdfID);
    
    PowerTransEndList.add(rdfID);
    PowerTransEndList.add(name);
    PowerTransEndList.add(trans_r);
    PowerTransEndList.add(trans_x);
    PowerTransEndList.add(transrdfID);
    PowerTransEndList.add(basevoltrdfID);
    
    
}
	return(PowerTransEndList);
}	 
}