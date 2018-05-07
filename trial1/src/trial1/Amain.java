package trial1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//import Project1Pack.BaseVoltClass;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Amain {
	
	public static void main(String[] args){
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
		ArrayList BaseVoltageList = new ArrayList<String>();
		//NodeList basevoltlist = doc1.getElementsByTagName("cim:BaseVoltage");
		
		ArrayList SubstationList = new ArrayList<String>();
		//NodeList substationlist = doc1.getElementsByTagName("cim:Substation");
		
		
		// Base Voltage information
			BaseVoltClass basevolt = new BaseVoltClass();
			basevolt.basevoltfn(doc1, BaseVoltageList);
			System.out.println("List of Base Voltage : " + BaseVoltageList);
		
		// Substation information
			SubstationClass subs = new SubstationClass();
			subs.substationfn(doc1, SubstationList);
			System.out.println("List of Substation : " + SubstationList);
		
		
	}
	catch(Exception e){
		e.printStackTrace();
		}
	}
}

