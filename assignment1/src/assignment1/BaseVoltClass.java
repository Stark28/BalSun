package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BaseVoltClass {
	
	private String rdfID;
<<<<<<< HEAD
	private String nominalValue;
	//	
	    public Element extractNode (Node node){
			Element element = (Element) node;
			 rdfID = element.getAttribute("rdf:ID");
			 nominalValue = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();	
=======
	private double nominalValue;
	//	
	    public Element extractNode (Node node){
			Element element = (Element) node;
			rdfID = element.getElementsByTagName("rdf:ID");
			nominalValue = Double.parseDouble(element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent());	
>>>>>>> 7d623729935e71b166cfdf85e4c6f24776c27db2
			return element;
		}
	 // 
		public String getrdfID() {
			return rdfID;
		}
		
<<<<<<< HEAD
		public String getNomValue() {
			return nominalValue;
		}
		
		
}

=======
		public double getNomValue() {
			return nominalValue;
		}
}
>>>>>>> 7d623729935e71b166cfdf85e4c6f24776c27db2
