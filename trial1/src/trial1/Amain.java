package trial1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
//import Project1Pack.BaseVoltClass;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Amain {
	
	public static void main(String[] args){
		
		Amain A = new Amain();
		A.parsing();
		
		DBSQL b = new DBSQL("root", "1008615szy");
		//b = DBSQL2();	
		
	}
	
	public void parsing ()	{	
		
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
		
		ArrayList RegulatingList = new ArrayList<String>();
		
		ArrayList PowerTransList = new ArrayList<String>();
		
		ArrayList EnergyList = new ArrayList<String>();

		ArrayList PowerTransEndList = new ArrayList<String>();
		
		ArrayList BreakerList = new ArrayList<String>();
		
		ArrayList TapList = new ArrayList<String>();
		
		ArrayList TerminalconductingList = new ArrayList<String>();
		
		ArrayList TerminalconnectList = new ArrayList<String>();
		
		ArrayList ConnectivityNodeList = new ArrayList<String>();
		
		ArrayList ConnectivitycontainerNodeList = new ArrayList<String>();
		
		ArrayList ACLineList = new ArrayList<String>();
		
		ArrayList BusbarList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorList = new ArrayList<String>();
		
		DBSQL mySQL = new DBSQL("root", "1008615szy");
		mySQL.StartUp(); // starting up the connection with SQL server and create the desired database
		mySQL.createTables(); 
		
		// Base Voltage information
			BaseVoltClass basevolt = new BaseVoltClass();
			basevolt.basevoltfn(doc1, BaseVoltageList);
			System.out.println("List of Base Voltage : " + BaseVoltageList);
			
		// Store the value to SQL database
			
			//System.out.println("*** Base Voltage ***");
			for(int i = 0; i < BaseVoltageList.size(); i = i + 2) {
				String BaseVrdfID = (String) BaseVoltageList.get(i);
				double BaseNom = (double) BaseVoltageList.get(i + 1);
				mySQL.BaseVoltageTab(BaseVrdfID, BaseNom);
				//System.out.println("rdfID: " + BaseVrdfID +"\n"+ "Nominal Value: " + BaseNom);
			}
		
		// Substation information
			SubstationClass subs = new SubstationClass();
			subs.substationfn(doc1, SubstationList);
			System.out.println("List of Substation : " + SubstationList);
			
			// Store the value to SQL database
			
			System.out.println("*** Substation ***");
			for (int i = 0; i < SubstationList.size(); i = i + 3) {
					String subrdfID = (String) SubstationList.get(i);
					String subName = (String) SubstationList.get(i+1);
					String subRegionrdfID = (String) SubstationList.get(i+2);
					mySQL.SubstationTab(subrdfID, subName, subRegionrdfID);
					System.out.println("rdfID: " + subrdfID +"\n"+ "Name: " + subName +"\n"+
					"region_ID: " + subRegionrdfID);
					}
		
		// Voltage Level information
			VoltLevelClass volt = new VoltLevelClass();
			volt.voltlevelfn(doc1, VoltLevelList);
			System.out.println("List of Voltage Level : " + VoltLevelList);
			
			// Store the value to SQL database
			
			System.out.println("*** Voltage Level ***");
			for (int i = 0; i < VoltLevelList.size(); i = i + 4 ) {
				String VoltrdfID = (String) VoltLevelList.get(i);
				String VoltName = (String) VoltLevelList.get(i+1);
				String subrdfID = (String) VoltLevelList.get(i+2);
				String baseVoltrdfID = (String) VoltLevelList.get(i+3);
				mySQL.VoltageLevelTab(VoltrdfID, VoltName, subrdfID, baseVoltrdfID);
				System.out.println("rdfID: " + VoltrdfID +"\n"+ "Name: " + VoltName +"\n"+ "substation_ID: "
				+ subrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
				}
			
		// Generating Unit information
			GeneratingClass gen = new GeneratingClass();
			gen.genfn(doc1, GeneratingList);
			System.out.println("List of Generating Unit : " + GeneratingList);
			
		// Store the value to SQL database
			
			System.out.println("*** Generating Unit ***");
			for (int i = 0; i < GeneratingList.size(); i = i + 5 ) {
				String GenrdfID = (String) GeneratingList.get(i);
				String GenName = (String) GeneratingList.get(i+1);
				double GenMaxP = (double) GeneratingList.get(i+2);
				double GenMinP = (double) GeneratingList.get(i+3);
				String GenEqConID = (String) GeneratingList.get(i+4);
				mySQL.GeneratingUnitTab(GenrdfID, GenName, GenMaxP, GenMinP, GenEqConID);
				System.out.println("rdfID: " + GenrdfID +"\n"+ "Name: " + GenName +"\n"+
				"Maximum Operating Power: " + GenMaxP +"\n"+ "Minimum Operating Power: " + GenMinP +"\n"
				+ "Equipment Container ID: " + GenEqConID);
				}
			
		// Synchronous Machine information
			SynMachClass syn = new SynMachClass();
			syn.synfn(doc1, doc2, SynchronousList);
			System.out.println("List of Synchronous Machine : " + SynchronousList);
			
		// Store the value to SQL database
			
			System.out.println("*** Synchronous Machine ***");
			for (int i = 0; i < SynchronousList.size(); i = i + 9) {
				String SyncrdfID = (String) SynchronousList.get(i);
				String SyncName = (String) SynchronousList.get(i+1);
				double SyncRatedS = (double) SynchronousList.get(i+2);
				double SyncP = (double) SynchronousList.get(i+3);
				double SyncQ = (double) SynchronousList.get(i+4);
				String SyncGenUnitID = (String) SynchronousList.get(i+5);
				String SyncRegCtrID = (String) SynchronousList.get(i+6);
				String SyncEqConID = (String) SynchronousList.get(i+7);
				String BV_rdfID = (String) SynchronousList.get(i+7);
				mySQL.SynchMachineTab(SyncrdfID, SyncName, SyncRatedS, SyncP, SyncQ, SyncGenUnitID, SyncRegCtrID, SyncEqConID, BV_rdfID);
				System.out.println("rdfID: " + SyncrdfID +"\n"+ "Name: " + SyncName +"\n"+
						"rated S: " + SyncRatedS +"\n"+ "Active Power: " + SyncP +"\n"+ "Reactive Power: " + SyncQ
						+"\n"+ "Generating Unit ID: " + SyncGenUnitID +"\n"+ "Regulating Control ID: " + SyncRegCtrID
						+"\n"+ "Equipment Container ID: " + SyncEqConID +"\n"+ "Base Voltage_RDFID: " + BV_rdfID);
				}
			
			
		// Regulating Control information check done
			RegulatingClass reg = new RegulatingClass();
			reg.regfn(doc1, doc2, RegulatingList);
			System.out.println("List of Regulating Control : " + RegulatingList);
			
			// Store the value to SQL database
			
						System.out.println("*** Regulating Control ***");
						for (int i = 0; i < SynchronousList.size(); i = i + 3 ) {
							String RegrdfID = (String) RegulatingList.get(i);
							String RegName = (String) RegulatingList.get(i+1);
							double TargetValue = (double) RegulatingList.get(i+2);
							mySQL.RegControlTab(RegrdfID, RegName, TargetValue);
							System.out.println("rdfID: " + RegrdfID +"\n"+ "Name: " + RegName +"\n"+
							"Target Value: " +TargetValue);
							}
			
		// Power Transformer information
		    PowerTransClass power = new PowerTransClass();
			power.powerfn(doc1, PowerTransList);
			System.out.println("List of Power Transformer : " + PowerTransList);
		
		// Store the value to SQL database
			
			System.out.println("*** Power Transformer ***");
			for (int i = 0; i < PowerTransList.size(); i = i + 3 ) {
				String TransrdfID = (String) PowerTransList.get(i);
				String TransName = (String) PowerTransList.get(i+1);
				String TransEqConID = (String) PowerTransList.get(i+2);
				mySQL.PowerTransformerTab(TransrdfID, TransName, TransEqConID);
				System.out.println("rdfID: " + TransrdfID +"\n"+ "Name: " + TransName +"\n"+
				"Equipment Container ID: " +TransEqConID);
				}
			
		 //Energy Consumer information
		    EnergyClass energy = new EnergyClass();
			energy.energyfn(doc1, doc2, EnergyList);
			System.out.println("List of Energy Consumer(Load) : " + EnergyList);

			// Store the value to SQL database
			
			System.out.println("*** Energy Consumer ***");
			for (int i = 0; i < EnergyList.size(); i = i + 5 ) {
				String LoadrdfID = (String) EnergyList.get(i);
				String LoadName = (String) EnergyList.get(i+1);
				double LoadP = (double) EnergyList.get(i+2);
				double LoadQ = (double) EnergyList.get(i+3);
				String LoadEqConID = (String) EnergyList.get(i+4);
				mySQL.EnergyConsumerTab(LoadrdfID, LoadName, LoadP, LoadQ, LoadEqConID);
				System.out.println("rdfID: " + LoadrdfID +"\n"+ "Name: " + LoadName +"\n"+
				"Active Power: " +LoadP +"\n"+ "Reactive Power: " + LoadQ +"\n"+
				"Equipment Container ID: " +LoadEqConID);
				}
			
		// Power Transformer Winding information check done
		    PowerTransEndClass powerend = new PowerTransEndClass();
			powerend.powerendfn(doc1, PowerTransEndList);
			System.out.println("List of Power Transformer End : " + PowerTransEndList);
			
			// Store the value to SQL database
			
			System.out.println("*** Power Transformer End (Winding) ***");
			for (int i = 0; i < PowerTransEndList.size(); i = i + 6 )  {
				String TrWindrdfID = (String) PowerTransEndList.get(i);
				String TrWindName = (String) PowerTransEndList.get(i+1);
				double TrWindRvalue = (double) PowerTransEndList.get(i+2);
				double TrWindXvalue = (double) PowerTransEndList.get(i+3);
				String PowTransrdfID = (String) PowerTransEndList.get(i+4);
				String baseVoltrdfID = (String) PowerTransEndList.get(i+5);
				mySQL.TransformerWindingTab(TrWindrdfID, TrWindName, TrWindRvalue, TrWindXvalue, PowTransrdfID, baseVoltrdfID);
				System.out.println("rdfID: " + TrWindrdfID +"\n"+ "Name: " + TrWindName +"\n"+ "Resistance Value: "
				+ TrWindRvalue +"\n"+ "Reactance Value: " + TrWindXvalue +"\n"
				+ "Transformer_ID: " + PowTransrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
				}
			
		// Breaker information check done
		    BreakerClass breaker = new BreakerClass();
			breaker.breakerfn(doc1, BreakerList);
			System.out.println("List of Breaker : " + BreakerList);	
			
			// Store the value to SQL database
			
			System.out.println("*** Breaker ***");
			for (int i = 0; i < BreakerList.size(); i = i + 4 ) {
				String BRrdfID = (String) BreakerList.get(i);
				String BRName = (String) BreakerList.get(i+1);
				boolean BRState = (boolean) BreakerList.get(i+2);
				String BREqConID = (String) BreakerList.get (i+3);
				mySQL.BreakerTab(BRrdfID, BRName, BRState, BREqConID);
				System.out.println("rdfID: " + BRrdfID +"\n"+ "Name: " + BRName +"\n"+
				"State: " +BRState +"\n"+ "Equipment Container ID: " +BREqConID);
				}
			
		// Tap Changer information
		    TapClass tap = new TapClass();
			tap.tapfn(doc1,doc2, TapList);
			System.out.println("List of Tap Changer : " + TapList);
			
			// Store the value to SQL database
			
			System.out.println("*** Ratio Tap Changer ***");
			for (int i = 0; i < TapList.size(); i = i + 3) {
				String TaprdfID = (String) TapList.get(i);
				String TapName = (String) TapList.get(i+1);
				double TapStep = (double) TapList.get(i+2);
				mySQL.TapChangerTab(TaprdfID, TapName, TapStep);
				System.out.println("rdfID: " + TaprdfID +"\n"+ "Name: " + TapName +"\n"+
				"Target Value: " +TapStep);
				}
			// SQL DATABASE DONE*************************************************************
			
			
		// Terminal information
		    TerminalconductingClass ter = new TerminalconductingClass();
			ter.terfn(doc1, TerminalconductingList);
			TerminalconnectClass ter2 = new TerminalconnectClass();
			ter2.ter2fn(doc1, TerminalconnectList);
			System.out.println("List of Terminalconducting : " + TerminalconductingList);
			System.out.println("List of Terminalconnect : " + TerminalconnectList);
			//System.out.println("First element of the list of Terminal : " + TerminalList.get(0));

			
		// ConnectivityNode information
		    ConnectivityNodeClass connect = new ConnectivityNodeClass();
			connect.connectfn(doc1, ConnectivityNodeList);
		    ConnectivityNodecontainerClass connect2 = new ConnectivityNodecontainerClass();
			connect2.connect2fn(doc1, ConnectivitycontainerNodeList);
			System.out.println("List of Connectivity Node : " + ConnectivityNodeList);
			System.out.println("List of Connectivity Node : " + ConnectivitycontainerNodeList);
			
		// Busbar information check done
		    BusbarClass bus = new BusbarClass();
			bus.busbarfn(doc1, BusbarList);
			System.out.println("List of Busbar : " + BusbarList);	

			
		//	Find the connectivity node and terminal pairs that matches
			int [][] a1;
			a1 = new int[TerminalconnectList.size()][2] ; 
			for(int i = 0; i < ConnectivityNodeList.size(); i++ ) {
				for(int j = 0; j < TerminalconnectList.size(); j++) {
					if (ConnectivityNodeList.get(i).equals(TerminalconnectList.get(j))) {
						int a = i + 1;
						int b = j + 1;
                        a1[j][0] = j;
                        a1[j][1] = i;
						System.out.println("ConnectivityNode " + i + " matches Terminal " + j );
					}
				}
			}
			
		//System.out.println(a1);
			for(int i = 0; i < TerminalconnectList.size(); i++) {
				System.out.print("Terminal " + a1[i][0] + " ");
				System.out.println("ConnectivityNode " + a1[i][1]);
				
			}
		
		// Find the connectivity node and busbar that matches
			int [][]a2;
			a2 = new int[ConnectivitycontainerNodeList.size()][2];
			for(int i = 0; i < ConnectivitycontainerNodeList.size(); i++ ) {
				for(int j = 0; j < BusbarList.size(); j++) {
					if(ConnectivitycontainerNodeList.get(i).equals(BusbarList.get(j))) {
	                    a2[i][0] = i;
	                    a2[i][1] = j;
					}
				}	
			}
		
		// System.out.println(a2);
			for(int i = 0; i < ConnectivitycontainerNodeList.size(); i++) {
				System.out.print("ConnectivityNode " + a2[i][0] + " ");
				System.out.println("Busbar " + a2[i][1]);
				
			}
 	
		//  initial the Y bus matrix
			int [][] y;
			y = new int [BusbarList.size()][BusbarList.size()];
			for(int i = 0; i < BusbarList.size(); i ++) {
				for(int j = 0; j < BusbarList.size(); j ++) {
				y[i][j]=0;
			}
			}
			
			
		// print Y bus matrix
			for(int i = 0; i < BusbarList.size(); i ++ ) {
			int a = BusbarList.size();
			System.out.print("[ " );
			for(int j = 0; j < BusbarList.size(); j ++) {
					System.out.print(y[i][j] + " ");
			}
			System.out.println("]");
			
	      }
		}

	catch(Exception e){
		e.printStackTrace();
		}
	
	}

}

