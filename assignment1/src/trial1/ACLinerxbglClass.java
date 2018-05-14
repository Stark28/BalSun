package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ACLinerxbglClass{
	
public static ArrayList ac3fn(Document doc1, ArrayList ACLinerxbglList)
{
	NodeList acline = doc1.getElementsByTagName("cim:ACLineSegment");
	System.out.println("***** Breaker ***** ");
    double r = 0;
    double x = 0;
    double b = 0;
    double g = 0;
    double l = 0;

	
	for (int i = 0; i<acline.getLength(); i++) 
	{
	Node breaker1 = acline.item(i);
	Element element = (Element) breaker1;
	int a = i + 1;
	
	r = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.r").item(0).getTextContent());
	x = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.x").item(0).getTextContent());
	b = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.bch").item(0).getTextContent());
	g = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.gch").item(0).getTextContent());
	l = Double.parseDouble(element.getElementsByTagName("cim:Conductor.length").item(0).getTextContent());
	
	System.out.println("Acline " + a + " : " );
	System.out.println("r : " + r);
    System.out.println("x : " + x);
    System.out.println("b : " + b);
    System.out.println("g : " + g);
    System.out.println("l : " + l);
    
    ACLinerxbglList.add(r);
    ACLinerxbglList.add(x);
    ACLinerxbglList.add(b);
    ACLinerxbglList.add(g);
    ACLinerxbglList.add(l);
    
	}
	return(ACLinerxbglList);
}
}