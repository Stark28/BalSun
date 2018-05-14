package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LinearShuntCompensatorvClass{
	
public static ArrayList shunt2fn(Document doc1, ArrayList LinearShuntCompensatorvList)
{
	NodeList shunt = doc1.getElementsByTagName("cim:LinearShuntCompensator");
	System.out.println("***** shunt capacitor volt ***** ");
    double volt = 0;
	
	for (int i = 0; i<shunt.getLength(); i++) 
	{
	Node breaker1 = shunt.item(i);
	Element element = (Element) breaker1;
	int a = i + 1;
	
	volt = Double.parseDouble(element.getElementsByTagName("cim:ShuntCompensator.nomU").item(0).getTextContent());
	

	
	System.out.println("Acline " + a + " : " );
	
    System.out.println("volt : " + volt);


    


    LinearShuntCompensatorvList.add(volt);

    
	}
	return(LinearShuntCompensatorvList);
}
}