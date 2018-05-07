package assignment1;

//import java.sql.*;
//import javax.swing.*;  
//import javax.swing.border.TitledBorder;
//import javax.swing.table.*;
//import javax.swing.table.TableColumn;
//import java.awt.Font;

//import javax.swing.JOptionPane;

//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.Element;

//import java.util.ArrayList;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//import Project1Pack.BaseVoltClass;

//import Project1Pack.BaseVoltClass;
//import Project1Pack.BreakerClass;
//import Project1Pack.GenUnitClass;
//import Project1Pack.LoadClass;
//import Project1Pack.PowTrEndClass;
//import Project1Pack.PowerTransClass;
//import Project1Pack.RegControlClass;
//import Project1Pack.SubstationClass;
//import Project1Pack.SynchMachineClass;
//import Project1Pack.TapChangerClass;
//import Project1Pack.VoltLevelClass;

public class Amain {
	//Creating array list for each CIM object according to individual classes defined per object
		private static List <BaseVoltClass> BaseVoltageList = new ArrayList<BaseVoltClass>();
		private static List <SubstationClass> SubstationList = new ArrayList<SubstationClass>();
		private static List <VoltLevelClass> VoltLevelList = new ArrayList<VoltLevelClass>();
		private static List <GenUnitClass> GenUnitList = new ArrayList<GenUnitClass>();
		private static List <SynchMachineClass> SynchMachineList = new ArrayList<SynchMachineClass>();
		private static List <RegControlClass> RegCtrList = new ArrayList<RegControlClass>();
		private static List <PowerTransClass> PowerTrList = new ArrayList<PowerTransClass>();
		private static List <LoadClass> LoadList = new ArrayList<LoadClass>();
		private static List <PowTrEndClass> TransWindList = new ArrayList<PowTrEndClass>();
		private static List <BreakerClass> BreakerList = new ArrayList<BreakerClass>();
		private static List <TapChangerClass> TapChangerList = new ArrayList<TapChangerClass>();
		//private List <TerminalClass> TerminalList = new ArrayList<TerminalClass>();
		//private List <ConnectivityNodeClass> ConnectNodeList = new ArrayList<ConnectivityNodeClass>();
		//private List <ACLineClass> ACLineList = new ArrayList<ACLineClass>();
		//private List <BusBarClass> BusBarList = new ArrayList<BusBarClass>();
		//private List <ShuntClass> ShuntList = new ArrayList<ShuntClass>();
	
	public static void main(String[] args){
	try {
		File XmlEQFile = new File("Assignment_EQ_reduced.xml");
		File XmlSSHFile = new File("Assignment_SSH_reduced.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		Document doc1 = dBuilder.parse(XmlEQFile);
		Document doc2 = dBuilder.parse(XmlSSHFile);
		
		doc1.getDocumentElement().normalize();
		doc2.getDocumentElement().normalize();
		
		// Extract required data from the EQ file
		NodeList baseVoltList = doc1.getElementsByTagName("cim:BaseVoltage");
		NodeList subList = doc1.getElementsByTagName("cim:Substation");
		NodeList voltList = doc1.getElementsByTagName("cim:VoltageLevel");
		NodeList genList = doc1.getElementsByTagName("cim:GeneratingUnit");
		NodeList syncList = doc1.getElementsByTagName("cim:SynchronousMachine");
		NodeList regList = doc1.getElementsByTagName("cim:RegulatingControl");
		NodeList powTrList = doc1.getElementsByTagName("cim:PowerTransformer");
		NodeList energyConList = doc1.getElementsByTagName("cim:EnergyConsumer");
		NodeList powTrEndList = doc1.getElementsByTagName("cim:PowerTransformerEnd");
		NodeList breakList = doc1.getElementsByTagName("cim:Breaker");
		NodeList tapList = doc1.getElementsByTagName("cim:RatioTapChanger");
		
		// Extract required data from the SSH file
		NodeList syncListSSH = doc2.getElementsByTagName("cim:SynchronousMachine");
		NodeList regListSSH = doc2.getElementsByTagName("cim:RegulatingControl");
		NodeList energyConListSSH = doc2.getElementsByTagName("cim:EnergyConsumer");
		NodeList breakListSSH = doc2.getElementsByTagName("cim:Breaker");
		NodeList tapListSSH = doc2.getElementsByTagName("cim:RatioTapChanger");

		//Base Voltage List
		for (int i = 0; i<baseVoltList.getLength(); i++) {
			BaseVoltClass baseV = new BaseVoltClass();
			baseV.extractNode(baseVoltList.item(i));
			BaseVoltageList.add(baseV);
			}
		//Substation List
        for (int i = 0; i<subList.getLength(); i++) {							
			SubstationClass substation = new SubstationClass();
			substation.extractNode(subList.item(i));
			SubstationList.add(substation);							
			}
        //Voltage Level List
		for (int i = 0; i<voltList.getLength(); i++) {
			VoltLevelClass voltLevel = new VoltLevelClass();
			voltLevel.extractNode(voltList.item(i));
			VoltLevelList.add(voltLevel);
			}
		//Generating Unit List
		for (int i = 0; i<genList.getLength(); i++) {
			GenUnitClass genUnit = new GenUnitClass();
			genUnit.extractNode(genList.item(i));
			GenUnitList.add(genUnit);
			}
		//Synchronous Machine List
		for (int i = 0; i<syncList.getLength(); i++) {
			SynchMachineClass synchMach = new SynchMachineClass();
			synchMach.extractNode(syncList.item(i));
			synchMach.extractNodeSSH(syncListSSH.item(i));
			SynchMachineList.add(synchMach);
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
	catch(Exception e){e.printStackTrace();}
		
	}
	
	//Returning the list of array for each CIM objects
		public List <BaseVoltClass> getBaseVoltList(){
			return BaseVoltageList;
		}
		public List <SubstationClass> getSubList(){
			return SubstationList;
		}
		public List <VoltLevelClass> getVoltList(){
			return VoltLevelList;
		}
		public List <GenUnitClass> getGenUnitList(){
			return GenUnitList;
		}
		public List <SynchMachineClass> getSynchMachList(){
			return SynchMachineList;
		}
		public List <RegControlClass> getRegCtrList(){
			return RegCtrList;
		}
		public List <PowerTransClass> getPowTrList(){
			return PowerTrList;
		}
		public List <LoadClass> getLoadList(){
			return LoadList;
		}
		public List <PowTrEndClass> getTrWindList(){
			return TransWindList;
		}
		public List <BreakerClass> getBreakerList(){
			return BreakerList;
		}
		public List <TapChangerClass> getTapChngList(){
			return TapChangerList;
		}
		

		
	
}
