package assignment1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BaseVoltClass {
	
	private String rdfID;
	private String nominalValue;
	//	
	    public Element extractNode (Node node){
			Element element = (Element) node;
			 rdfID = element.getAttribute("rdf:ID");
			 nominalValue = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();	
			return element;
		}
	 // 
		public String getrdfID() {
			return rdfID;
		}
		
		public String getNomValue() {
			return nominalValue;
		}
		
		
}

