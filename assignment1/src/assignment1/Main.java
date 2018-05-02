package assignment1;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.util.ArrayList;
//import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

// Main class for extracting all the requirement data for importing into SQL database
public class Main {
	//Creating array list for each CIM object according to individual classes defined per object
	private List <BaseVoltClass> BaseVoltList = new ArrayList<BaseVoltClass>();
	private List <SubClass> SubList = new ArrayList<SubClass>();
	private List <VoltLvClass> VoltLvList = new ArrayList<VoltLvClass>();
	private List <GenClass> GenList = new ArrayList<GenClass>();
	private List <SMClass> SMList = new ArrayList<SMClass>();
	private List <RCClass> RCList = new ArrayList<RCClass>();
	private List <PowerTransClass> PowerTransList = new ArrayList<PowerTransClass>();
	private List <EnergyConsClass> EnergyConsList = new ArrayList<EnergyConsClass>();
	private List <TransWindingClass> TransWindingList = new ArrayList<TransWindingClass>();
	private List <BreakerClass> BreakerList = new ArrayList<BreakerClass>();
	private List <TapClass> TapList = new ArrayList<TapClass>();
	//private List <TerminalClass> TerminalList = new ArrayList<TerminalClass>();
	//private List <ConnectivityNodeClass> ConnectNodeList = new ArrayList<ConnectivityNodeClass>();
	//private List <ACLineClass> ACLineList = new ArrayList<ACLineClass>();
	//private List <BusBarClass> BusBarList = new ArrayList<BusBarClass>();
	//private List <ShuntClass> ShuntList = new ArrayList<ShuntClass>();
    	
	public void parsingXml(String EQ, String SSH) {
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
			NodeList basevoltList = doc1.getElementsByTagName("cim:BaseVoltage");
			NodeList subList = doc1.getElementsByTagName("cim:Substation");
			NodeList voltlvList = doc1.getElementsByTagName("cim:VoltageLevel");
			NodeList genList = doc1.getElementsByTagName("cim:GeneratingUnit");
			NodeList smList = doc1.getElementsByTagName("cim:SynchronousMachine");
			
			
		}
	}
    }
}
