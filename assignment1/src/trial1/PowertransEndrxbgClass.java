package trial1;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PowertransEndrxbgClass{
	
public static ArrayList powerendrxbgfn(Document doc1, ArrayList PowerTransEndrxbgList)
{
	NodeList powerendlist = doc1.getElementsByTagName("cim:PowerTransformerEnd");
	System.out.println("***** Power Transformer End rxbg ***** ");
 
	double trans_r;
	double trans_x;
	double trans_b;
	double trans_g;

	
	
	for (int i = 0; i<powerendlist.getLength(); i++) 
	{
	Node power = powerendlist.item(i);
	int a = i + 1;
	   
	Element element = (Element) power;

	trans_r = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent());
	trans_x = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent());
	trans_b = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.b").item(0).getTextContent());
	trans_g = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.g").item(0).getTextContent());
	
	System.out.println("Power Transformer End " + a + " : " );

    System.out.println("Transformer.r : " + trans_r);
    System.out.println("Transformer.x : " + trans_x);
    System.out.println("Transformer.b : " + trans_b);
    System.out.println("Transformer.g : " + trans_g);


    

    PowerTransEndrxbgList.add(trans_r);
    PowerTransEndrxbgList.add(trans_x);
    PowerTransEndrxbgList.add(trans_b);
    PowerTransEndrxbgList.add(trans_g);


    
    
}
	return(PowerTransEndrxbgList);
}	 
}