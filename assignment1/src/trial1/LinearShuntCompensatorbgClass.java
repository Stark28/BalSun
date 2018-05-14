package trial1;


	import java.util.ArrayList;

	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;

	public class LinearShuntCompensatorbgClass{
		
	public static ArrayList shunt1fn(Document doc1, ArrayList LinearShuntCompensatorbgList)
	{
		NodeList shunt = doc1.getElementsByTagName("cim:LinearShuntCompensator");
		System.out.println("***** shunt capacitor ***** ");
	    double b = 0;
	    double g = 0;


		
		for (int i = 0; i<shunt.getLength(); i++) 
		{
		Node breaker1 = shunt.item(i);
		Element element = (Element) breaker1;
		int a = i + 1;
		
		b = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.bPerSection").item(0).getTextContent());
		g = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.gPerSection").item(0).getTextContent());
	
		
		System.out.println("Acline " + a + " : " );
		
	    System.out.println("b : " + b);
	    System.out.println("g : " + g);

	    

	    LinearShuntCompensatorbgList.add(b);
	    LinearShuntCompensatorbgList.add(g);

	    
		}
		return(LinearShuntCompensatorbgList);
	}
	}