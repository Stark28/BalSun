package trial1;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Amain 
{
public static void main(String[] args) 
{
   
try {
//import the EQ and SSH files that we are going to use
File XmlFileEQ = new File("Assignment_EQ_reduced.xml");
File XmlFileSSH = new File("Assignment_SSH_reduced.xml");
//Create Document Object and parse them
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc1 = dBuilder.parse(XmlFileEQ);
Document doc2 = dBuilder.parse(XmlFileSSH);
//Extract required data from EQ file
NodeList basevoltlist = doc1.getElementsByTagName("cim:BaseVoltage");
for (int i = 0; i<basevoltlist.getLength(); i++) 
{
Node BaseVol = basevoltlist.item(i);
String rdfID;
double nominalValue; 
    
Element BVelement = (Element) BaseVol;
rdfID = BVelement.getAttribute("rdf:ID");
nominalValue = Double.parseDouble(BVelement.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent()); 
 
System.out.println("Reference ID : " + rdfID);
                System.out.println("Nominal Voltage : " + nominalValue);
}
}
      catch(Exception e)
      {
e.printStackTrace();
      }
}
}
