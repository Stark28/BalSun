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
		
		ArrayList SubstationList = new ArrayList<String>();
		
		ArrayList VoltLevelList = new ArrayList<String>();
		
		ArrayList GeneratingList = new ArrayList<String>();
		
		ArrayList SynchronousList = new ArrayList<String>();

		
		
		// Base Voltage information
			BaseVoltClass basevolt = new BaseVoltClass();
			basevolt.basevoltfn(doc1, BaseVoltageList);
			System.out.println("List of Base Voltage : " + BaseVoltageList);
		
		// Substation information
			SubstationClass subs = new SubstationClass();
			subs.substationfn(doc1, SubstationList);
			System.out.println("List of Substation : " + SubstationList);
		
		// Voltage Level information
			VoltLevelClass volt = new VoltLevelClass();
			volt.voltlevelfn(doc1, VoltLevelList);
			System.out.println("List of Voltage Level : " + VoltLevelList);
			
		// Generating Unit information
			GeneratingClass gen = new GeneratingClass();
			gen.genfn(doc1, GeneratingList);
			System.out.println("List of Generating Unit : " + GeneratingList);
			
		// Synchronous Machine information
			SynMachClass syn = new SynMachClass();
			syn.synfn(doc1, SynchronousList);
			System.out.println("List of Generating Unit : " + GeneratingList);
			
		
	}
	catch(Exception e){
		e.printStackTrace();
		}
	}
}

