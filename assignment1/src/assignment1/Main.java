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
			NodeList rcList = doc1.getElementsByTagName("cim:RegulatingControl");
			NodeList powertransList = doc1.getElementsByTagName("cim:PowerTransformer");
			NodeList energyconsList = doc1.getElementsByTagName("cim:EnergyConsumer");
			NodeList transwinding = doc1.getElementsByTagName("cim:PowerTransformerEnd");
			NodeList breakList = doc1.getElementsByTagName("cim:Breaker");
			NodeList tapList = doc1.getElementsByTagName("cim:RatioTapChanger");
			
			// Extract required data from the SSH file
			NodeList smList2 = doc2.getElementsByTagName("cim:SynchronousMachine");
			NodeList rcList2 = doc2.getElementsByTagName("cim:RegulatingControl");
			NodeList energyconsList2 = doc2.getElementsByTagName("cim:EnergyConsumer");
			NodeList breakList2 = doc2.getElementsByTagName("cim:Breaker");
			NodeList tapList2 = doc2.getElementsByTagName("cim:RatioTapChanger");
			
			// Base Voltage List done
			for (int i = 0; i<basevoltList.getLength(); i++) {
				BaseVoltClass basevolt = new BaseVoltClass();
				basevolt.extractNode(basevoltList.item(i));
				BaseVoltList.add(basevolt);
				}
			
			// Substation List done
			for (int i = 0; i<subList.getLength(); i++) {
				SubClass sub = new SubClass();
				sub.extractNode(subList.item(i));
				SubList.add(sub);
				}
			
			//Voltage Level List done
			for (int i = 0; i<voltlvList.getLength(); i++) {
				VoltLvClass voltLv = new VoltLvClass();
				voltLv.extractNode(voltlvList.item(i));
				VoltLvList.add(voltLv);
				}
			
			//Generating Unit List done
			for (int i = 0; i<genList.getLength(); i++) {
				GenClass gen = new GenClass();
				gen.extractNode(genList.item(i));
				GenList.add(gen);
				}
			
			//Synchronous Machine List
			for (int i = 0; i<smList.getLength(); i++) {
				SMClass sm = new SMClass();
				sm.extractNode(smList.item(i));
				sm.extractNodeSSH(syncListSSH.item(i));
				SMList.add(sm);
				}
			
			//Regulating Control List
			for (int i = 0; i<regList.getLength(); i++) {
				RegControlClass regCtrl = new RegControlClass();
				regCtrl.extractNode(regList.item(i));
				regCtrl.extractNodeSSH(regListSSH.item(i));
				RegCtrList.add(regCtrl);	
				}
			
			//Power Transformer List
			for (int i = 0; i<powTrList.getLength(); i++) {
				PowerTransClass PowTrans = new PowerTransClass();
				PowTrans.extractNode(powTrList.item(i));
				PowerTrList.add(PowTrans);
				}
			
			//Energy Consumer List
			for (int i = 0; i<energyConList.getLength(); i++) {
				LoadClass load = new LoadClass();
				load.extractNode(energyConList.item(i));
				load.extractNodeSSH(energyConListSSH.item(i));
				LoadList.add(load);
				}
			
		    //Power Transformer End (Winding) List
			for (int i = 0; i<powTrEndList.getLength(); i++) {
				PowTrEndClass transEnd = new PowTrEndClass();
				transEnd.extractNode(powTrEndList.item(i));
				TransWindList.add(transEnd);
				}
			
			//Breaker List
			for (int i = 0; i<breakList.getLength(); i++) {
				BreakerClass breaker = new BreakerClass();
				breaker.extractNode(breakList.item(i));
				breaker.extractNodeSSH(breakListSSH.item(i));
				BreakerList.add(breaker);
				}
			
			//Ratio Tap Changer List
			for (int i = 0; i<tapList.getLength(); i++) {
				TapChangerClass tapChng = new TapChangerClass();
				tapChng.extractNode(tapList.item(i));
				tapChng.extractNodeSSH(tapListSSH.item(i));
				TapChangerList.add(tapChng);
			}
			
		}
	  }
    }

