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
		
		ArrayList PowerTransEndrxbgList = new ArrayList<String>();
		
		ArrayList BreakerList = new ArrayList<String>();
		
		ArrayList TapList = new ArrayList<String>();
		
		ArrayList TerminalconductingList = new ArrayList<String>();
		
		ArrayList TerminalconnectList = new ArrayList<String>();
		
		ArrayList ConnectivityNodeList = new ArrayList<String>();
		
		ArrayList ConnectivitycontainerNodeList = new ArrayList<String>();
		
		ArrayList ACLinerdfIDList = new ArrayList<String>();
		
		ArrayList ACLinebaseList = new ArrayList<String>();
		
		ArrayList ACLinerxbglList = new ArrayList<String>();
		
		ArrayList BusbarList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorrdfIDList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorbgList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorvList = new ArrayList<String>();
		
		DBSQL mySQL = new DBSQL("root", "1008615szy");
		mySQL.Initiate(); 
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
				mySQL.BaseVoltDB(BaseVrdfID, BaseNom);
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
					mySQL.SubstationDB(subrdfID, subName, subRegionrdfID);
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
		     	mySQL.VoltLevelDB(VoltrdfID, VoltName, subrdfID, baseVoltrdfID);
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
				mySQL.GeneratingDB(GenrdfID, GenName, GenMaxP, GenMinP, GenEqConID);
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
				String SyncbasevoltID = (String) SynchronousList.get(i+8);
				mySQL.SynMachDB(SyncrdfID, SyncName, SyncRatedS, SyncP, SyncQ, SyncGenUnitID, SyncRegCtrID, SyncEqConID, SyncbasevoltID);
				System.out.println("rdfID: " + SyncrdfID +"\n"+ "Name: " + SyncName +"\n"+
						"rated S: " + SyncRatedS +"\n"+ "Active Power: " + SyncP +"\n"+ "Reactive Power: " + SyncQ
						+"\n"+ "Generating Unit ID: " + SyncGenUnitID +"\n"+ "Regulating Control ID: " + SyncRegCtrID
						+"\n"+ "Equipment Container ID: " + SyncEqConID +"\n"+ "Synchoronous Base RDF ID: " + SyncbasevoltID );
				}
			
			
		// Regulating Control information check done
			RegulatingClass reg = new RegulatingClass();
			reg.regfn(doc1, doc2, RegulatingList);
			System.out.println("List of Regulating Control : " + RegulatingList);
			
			// Store the value to SQL database
			
						System.out.println("*** Regulating Control ***");
						for (int i = 0; i < RegulatingList.size(); i = i + 3 ) {
							String RegrdfID = (String) RegulatingList.get(i);
							String RegName = (String) RegulatingList.get(i+1);
							double TargetValue = (double) RegulatingList.get(i+2);
							mySQL.RegDB(RegrdfID, RegName, TargetValue);
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
				mySQL.PowerTransDB(TransrdfID, TransName, TransEqConID);
				System.out.println("rdfID: " + TransrdfID +"\n"+ "Name: " + TransName +"\n"+
				"Equipment Container ID: " +TransEqConID);
				}
			
		 //Energy Consumer information
		    EnergyClass energy = new EnergyClass();
			energy.energyfn(doc1, doc2, EnergyList);
			System.out.println("List of Energy Consumer(Load) : " + EnergyList);

			// Store the value to SQL database
			
			System.out.println("*** Energy Consumer ***");
			for (int i = 0; i < EnergyList.size(); i = i + 6 ) {
				String LoadrdfID = (String) EnergyList.get(i);
				String LoadName = (String) EnergyList.get(i+1);
				double LoadP = (double) EnergyList.get(i+2);
				double LoadQ = (double) EnergyList.get(i+3);
				String LoadEqConID = (String) EnergyList.get(i+4);
				String BasevoltID = (String) EnergyList.get(i+5);
				mySQL.EnergyDB(LoadrdfID, LoadName, LoadP, LoadQ, LoadEqConID, BasevoltID);
				System.out.println("rdfID: " + LoadrdfID +"\n"+ "Name: " + LoadName +"\n"+
				"Active Power: " +LoadP +"\n"+ "Reactive Power: " + LoadQ +"\n"+
				"Equipment Container ID: " +LoadEqConID);
				}
			
		// Power Transformer Winding information check done
		    PowerTransEndClass powerend = new PowerTransEndClass();
			powerend.powerendfn(doc1, PowerTransEndList);
			System.out.println("List of Power Transformer End : " + PowerTransEndList);
			
			PowertransEndrxbgClass powerendrxbg = new PowertransEndrxbgClass();
			powerendrxbg.powerendrxbgfn(doc1, PowerTransEndrxbgList);
			System.out.println("List of Power Transformer End rxbg: " + PowerTransEndrxbgList);
			
			double [][] transrxbg;
			transrxbg = new double[ PowerTransEndrxbgList.size() / 4][6];
			for(int i = 0; i < PowerTransEndrxbgList.size(); i = i + 4) {
				transrxbg[i / 4][0] = i / 4;
				transrxbg[i / 4][1] = (double) PowerTransEndrxbgList.get(i);
				transrxbg[i / 4][2] = (double) PowerTransEndrxbgList.get(i + 1);
				transrxbg[i / 4][3] = (double) PowerTransEndrxbgList.get(i + 2);
				transrxbg[i / 4][4] = (double) PowerTransEndrxbgList.get(i + 3);
				for(int j = 0; j < PowerTransEndList.size(); j = j + 6) {
					for(int k = 0; k < BaseVoltageList.size(); k = k + 2 ) {
						if(i / 4 == j / 6 && PowerTransEndList.get(j + 5).equals(BaseVoltageList.get(k))) {
							transrxbg[i / 4][5] = (double) BaseVoltageList.get(k + 1);
						}
					}
					
				}
			}
			
			for(int i = 0; i < PowerTransEndrxbgList.size() / 4; i++) {
				System.out.println("No. " + transrxbg[i][0] + " r " + transrxbg[i][1] + " x " + transrxbg[i][2] + " b " + transrxbg[i][3] + " g " + transrxbg[i][4]);
			}
			
			double [][] transrxbgnew;
			transrxbgnew = new double[ PowerTransList.size() / 3][6];
			for(int i = 0; i < PowerTransList.size(); i = i + 3 ) {
				for(int j = 0; j < PowerTransEndList.size(); j = j + 6) {
					if(PowerTransList.get(i).equals(PowerTransEndList.get(j + 4))) {
						transrxbgnew[i / 3][0] = i / 3;
						if(transrxbg[j / 6][1] != 0) {
							transrxbgnew[i / 3][1] = transrxbg[j / 6][1];
							transrxbgnew[i / 3][2] = transrxbg[j / 6][2];
							transrxbgnew[i / 3][3] = transrxbg[j / 6][3];
							transrxbgnew[i / 3][4] = transrxbg[j / 6][4];
							transrxbgnew[i / 3][5] = transrxbg[j / 6][5];
						}
					}
				}
			}
			

			
			/*
			System.out.println("Test");
			for(int i = 0; i < PowerTransEndrxbgList.size() / 4; i ++) {
				if(transrxbg[i][0] == 0) {
					System.out.println("No. " + transrxbg[i][0] + " r " + transrxbg[i][1] + " x " + transrxbg[i][2] + " b " + transrxbg[i][3] + " g " + transrxbg[i][4]);
				}
				if(transrxbg[i][0] == 2) {
					System.out.println("No. " + transrxbg[i][0] + " r " + transrxbg[i][1] + " x " + transrxbg[i][2] + " b " + transrxbg[i][3] + " g " + transrxbg[i][4]);
				}
			}
            */
			
			
			// Store the value to SQL database
			
			System.out.println("*** Power Transformer End (Winding) ***");
			for (int i = 0; i < PowerTransEndList.size(); i = i + 6 )  {
				String TrWindrdfID = (String) PowerTransEndList.get(i);
				String TrWindName = (String) PowerTransEndList.get(i+1);
				double TrWindRvalue = (double) PowerTransEndList.get(i+2);
				double TrWindXvalue = (double) PowerTransEndList.get(i+3);
				String PowTransrdfID = (String) PowerTransEndList.get(i+4);
				String baseVoltrdfID = (String) PowerTransEndList.get(i+5);
				mySQL.PowerTransEndDB(TrWindrdfID, TrWindName, TrWindRvalue, TrWindXvalue, PowTransrdfID, baseVoltrdfID);
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
			for (int i = 0; i < BreakerList.size(); i = i + 5 ) {

				String BRrdfID = (String) BreakerList.get(i);
				String BRName = (String) BreakerList.get(i+1);
				boolean BRState = (boolean) BreakerList.get(i+2);
				String BREqConID = (String) BreakerList.get (i+3);
				String BaseVoltage_rdfID = (String) BreakerList.get (i+4);
				mySQL.BreakerDB(BRrdfID, BRName, BRState, BREqConID, BaseVoltage_rdfID);
				System.out.println("rdfID: " + BRrdfID +"\n"+ "Name: " + BRName +"\n"+
				"State: " +BRState +"\n"+ "Equipment Container ID: " +BREqConID +"\n"+ "BaseVoltage RDF: " + BaseVoltage_rdfID);
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
				mySQL.VoltLevelDB(TaprdfID, TapName, TapStep);
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
			
		// ACline information 
		    ACLinerdfClass acrdf = new ACLinerdfClass();
			acrdf.ac1fn(doc1, ACLinerdfIDList);
			System.out.println("List of ACline rdfID : " + ACLinerdfIDList);
			
		    ACLinebaseClass acbase = new ACLinebaseClass();
			acbase.ac2fn(doc1, ACLinebaseList);
			System.out.println("List of ACline base voltage rdfID : " + ACLinebaseList);
			
		    ACLinerxbglClass acrxbgl = new ACLinerxbglClass();
			acrxbgl.ac3fn(doc1, ACLinerxbglList);
			System.out.println("List of rxbgl : " + ACLinerxbglList);
			
		// shunt capacitior check done
			LinearShuntCompensatorrdfClass shunt = new LinearShuntCompensatorrdfClass();
			shunt.shuntfn(doc1, LinearShuntCompensatorrdfIDList);
			System.out.println("List of capacitor : " + LinearShuntCompensatorrdfIDList);	

			LinearShuntCompensatorbgClass shunt1 = new LinearShuntCompensatorbgClass();
			shunt1.shunt1fn(doc1, LinearShuntCompensatorbgList);
			System.out.println("List of capacitor bg : " + LinearShuntCompensatorbgList);	
			
			LinearShuntCompensatorvClass shunt2 = new LinearShuntCompensatorvClass();
			shunt2.shunt2fn(doc1, LinearShuntCompensatorvList);
			System.out.println("List of capacitor volt : " + LinearShuntCompensatorvList);	
			
		//	Find the connectivity node and terminal pairs that matches
			int [][] a1;
			a1 = new int[TerminalconnectList.size()][3] ; 
			for(int i = 0; i < ConnectivityNodeList.size(); i++ ) {
				for(int j = 0; j < TerminalconnectList.size(); j++) {
					if (ConnectivityNodeList.get(i).equals(TerminalconnectList.get(j))) {
                        a1[j][0] = j;
                        a1[j][1] = i;
                        a1[j][2] = 0;

					}
				}
			}
			

		// Find the connectivity node and busbar that matches
			int [][]a2;
			a2 = new int[ConnectivitycontainerNodeList.size()][2];
			for(int i = 0; i < ConnectivitycontainerNodeList.size(); i++ ) {
				for(int j = 0; j < BusbarList.size(); j = j + 2) {
					if(ConnectivitycontainerNodeList.get(i).equals(BusbarList.get(j + 1))) {
	                    a2[i][0] = i;
	                    int a = j / 2;
	                    a2[i][1] = a;
					}
				}	
			}
		
		// System.out.println(a2);
			for(int i = 0; i < ConnectivitycontainerNodeList.size(); i++) {
				System.out.print("ConnectivityNode " + a2[i][0] + " ");
				System.out.println("Busbar " + a2[i][1]);
				
			}
 	
		
		
		
			
		/////////////////////////////  traversal of the elements of the circuit ////////////////////////////////////
			
			// which equipment matches which terminal
			int [][] t1;
			t1 = new int[TerminalconductingList.size()][7] ;
			for (int i = 0; i < TerminalconductingList.size(); i++ ) {
				
				t1[i][4] = a1[i][1];
				if(t1[i][4] >= BusbarList.size() / 2 ) {
					t1[i][6] = BusbarList.size() / 2;
				}else {
					t1[i][6] = t1[i][4];
				}
				t1[i][5] = 0;
				// check for generating unit 1111111111
				for (int j = 0; j < GeneratingList.size(); j ++) {
					if(TerminalconductingList.get(i).equals(GeneratingList.get(j))) {
						t1[i][0] = i;
						t1[i][1] = j;
						t1[i][2] = 1;
					}
				}
				
				// check for synchronous machine 2222222222222222
				for (int j = 0; j < SynchronousList.size(); j ++) {
					if(TerminalconductingList.get(i).equals(SynchronousList.get(j))) {
						t1[i][0] = i;
						t1[i][1] = j;
						t1[i][2] = 2;
					}
				}
				
				// check for AC line 33333333333333333
				for (int j = 0; j < ACLinerdfIDList.size(); j ++) {
					if(TerminalconductingList.get(i).equals(ACLinerdfIDList.get(j))) {
						t1[i][0] = i;
						t1[i][1] = j;
						t1[i][2] = 3;
					}
				}
				
				// check for power transformer PowerTransList 444444444444444444
				for (int j = 0; j < PowerTransList.size(); j = j + 3) {
					if(TerminalconductingList.get(i).equals(PowerTransList.get(j))) {
						int a = j / 3;
						t1[i][0] = i;
						t1[i][1] = a;
						t1[i][2] = 4;
					}
				}
				
				// check for energy consumer EnergyList 555555555555555555
				for (int j = 0; j < EnergyList.size(); j = j + 6) {
					if(TerminalconductingList.get(i).equals(EnergyList.get(j))) {
						int a = j / 6;
						t1[i][0] = i;
						t1[i][1] = a;
						t1[i][2] = 5;
					}
				}
				
				// check for busbar section 666666666666  BusbarList
				for (int j = 0; j < BusbarList.size(); j = j + 2) {
					if(TerminalconductingList.get(i).equals(BusbarList.get(j))) {
						int a = j /2;
						t1[i][0] = i;
						t1[i][1] = a;
						t1[i][2] = 6;
					}
				}
				
				
				//LinearShuntCompensatorrdfIDList 7777777777777777
				for (int j = 0; j < LinearShuntCompensatorrdfIDList.size(); j ++) {
					if(TerminalconductingList.get(i).equals(LinearShuntCompensatorrdfIDList.get(j))) {
						
						t1[i][0] = i;
						t1[i][1] = j;
						t1[i][2] = 7;
					}
				}
				
				//BreakerList 88888888888888888
				for (int j = 0; j < BreakerList.size(); j = j + 5) {
					if(TerminalconductingList.get(i).equals(BreakerList.get(j))) {
						int a = j / 5;
						t1[i][0] = i;
						t1[i][1] = a;
						t1[i][2] = 8;
					}
				}

			}
			
			
           
			// get the terminals that serves the same equipment
			for(int i = 0; i < TerminalconductingList.size(); i++) {
				int a = t1[i][2];
				int c = t1[i][1];
				for(int j = 0; j < TerminalconductingList.size(); j++) {
					int b = t1[j][2];
					int d = t1[j][1];
					if(a == b && i != j && c == d) {
							int e = t1[i][0];
							int f = t1[j][0];
							t1[i][3] = f;
							t1[j][3] = e;						
					}
				//	else {
				//		t1[i][3] = TerminalconductingList.size();
				//	}
				}
			}
			
           for(int i = 0; i < TerminalconductingList.size(); i++) {
        	   if(t1[i][3] == 0) {
        		   t1[i][3] = TerminalconductingList.size();
        		   
        	   }
           }
			
	         
       	
   		//  initial the Y bus matrix
   			Complex zero = new Complex(0,0);
   			Complex [][] ybus = new Complex [BusbarList.size()/2][BusbarList.size()/2];
   			for(int i = 0; i < BusbarList.size()/2; i++) {
   				for(int j = 0; j < BusbarList.size()/2; j++) {
   				ybus[i][j]= zero;
   			}
   			}
   			
           
           double Sbase = 300; // define the base power
           
           // For AC line
			System.out.println("AC line");
			//double[] aclinebus = new double[ACLinebaseList.size() * 2];
			ArrayList aclinebus = new ArrayList<String>(); 
			int x1 = 0;
			for(int i = 0; i < TerminalconductingList.size(); i ++) { 
				
				if(t1[i][2] == 3) {// if the terminal connect to an ac line
					// the terminal number connected to the line is i
					int a = t1[i][3];// another terminal number of this ac line
					t1[i][5] = 1;
					t1[a][5] = 1;// flag turns to 1
					int b = t1[i][4];
					if(t1[i][6] != BusbarList.size() / 2) {// check whether the terminal connect to busbar
						 x1 = t1[i][6];// x is the number of busbar
           			  System.out.print("Hello");
           			  System.out.println(x1);
           			  aclinebus.add(x1);
						
					}else {
                     for(int j = 0; j < TerminalconductingList.size(); j ++) {
                    	 if(t1[j][4] == b && j != i && t1[j][2] == 8) {// check if other terminals in the same CN connect to breaker
                    		 t1[j][5] = 1;
                    		 int c = t1[j][3];
                    		 if(t1[c][6] != BusbarList.size() / 2) {
                    			 int d = t1[c][6];
                    			  x1 = d; // x is the number of busbar
                    			  System.out.print("Hello");
                    			  System.out.println(x1);
                    			  aclinebus.add(x1);
                    		 }
                    	 }
                     }
					}
				}
			}
			
			
			
			// calculate numbers for ac lines
			for(int i = 0; i < aclinebus.size(); i = i + 2) {
				
				int c = (int) aclinebus.get(i);
				int d = (int) aclinebus.get(i + 1);
				
				// get the value of the rxgbl of the acline

				for(int p = 0; p < ACLinerxbglList.size(); p = p + 5) {
					if((i / 2) == (p / 5)) {
						double l = (double) ACLinerxbglList.get(p + 4);
						double r = (double) ACLinerxbglList.get(p);
						double x = (double) ACLinerxbglList.get(p + 1);
						double g = (double) ACLinerxbglList.get(p + 3);
						double b = (double) ACLinerxbglList.get(p + 2);
						
						Complex Z = new Complex(l*r, l*x);
						Complex Y = Z.reciprocal();
						
						Complex Ysh =new Complex(l*g/2 , l*b/2);	
							for(int k = 0; k < BaseVoltageList.size(); k = k + 2) {
								if(BaseVoltageList.get(k).equals(ACLinebaseList.get(p/5))) {
									double v = (double) BaseVoltageList.get(k + 1);
									Complex Ypu = Y.multi((v*v)/Sbase);
									Complex Yshpu = Ysh.multi((v*v)/Sbase);
									
									for(int m = 0; m < BusbarList.size() / 2; m ++) {
										if(m == c) {
											Complex Ytotalpu = Ypu.plus(Yshpu);
											ybus[m][m] = Ytotalpu.plus(ybus[m][m]);
											for(int n = 0; n < BusbarList.size() / 2; n ++) {
												if(n == d) {
													Complex Ytotal2pu = Ypu.plus(Yshpu);
													//System.out.println(n);
													//System.out.println(d);
													ybus[n][n] = Ytotal2pu.plus(ybus[n][n]);
													Complex minus = new Complex(-1.0, 0.0);
													Complex minusY = Ypu.times(minus);
													ybus[m][n]=minusY.plus(ybus[m][n]);
													ybus[n][m]=minusY.plus(ybus[n][m]);
												}
											}
										}
									}
											
								}
							}
						
					}
	
				}
	
			}
			
			
			// For transformer
			System.out.println("Transformer");
			ArrayList transformerbus = new ArrayList<String>(); 
			//double[] transformerbus = new double[PowerTransEndList.size() / 3];
			 int x2 = 0;
			for(int i = 0; i < TerminalconductingList.size(); i ++) { 
				
				if(t1[i][2] == 4) {// if the terminal connect to a transformer
					// the terminal number connected to the line is i
					int a = t1[i][3];// another terminal number of this ac line
					t1[i][5] = 1;
					t1[a][5] = 1;// flag turns to 1
					int b = t1[i][4];
					if(t1[i][6] != BusbarList.size() / 2) {// check whether the terminal connect to busbar
						 x2 = t1[i][6];// x is the number of busbar
						 System.out.println("Hello" + x2);
						 transformerbus.add(x2);
				
					}else {
                    for(int j = 0; j < TerminalconductingList.size(); j ++) {
                   	 if(t1[j][4] == b && j != i && t1[j][2] == 8) {// check if other terminals in the same CN connect to breaker
                   		t1[j][5] = 1;
                   		 int c = t1[j][3];
                   		 if(t1[c][6] != BusbarList.size() / 2) {
                   			 int d = t1[c][6];
                   			  x2 = d; // x is the number of busbar
                   			  System.out.print("Hello");
                   			  System.out.println(x2);
                   			transformerbus.add(x2);
                   		 }
                   	 }
                    }
					}
				}
			}
	       
			// calculate numbers for transformers
			
						for(int i = 0; i < transformerbus.size(); i = i + 2) {
							
							int c = (int) transformerbus.get(i);
							int d = (int) transformerbus.get(i + 1);
							
							// get the value of the rxgbl of the acline  PowerTransList
							for(int p = 0; p < PowerTransList.size() / 3; p++) {
								if((i / 2) == p) {
									double r = transrxbgnew[p][1];
									double x = transrxbgnew[p][2];
									double g = transrxbgnew[p][4];
									double b = transrxbgnew[p][3];
									double v = transrxbgnew[p][5];
									
									Complex Z = new Complex(r, x);
									Complex Y = Z.reciprocal();
									
									Complex Ysh =new Complex(g/2 , b/2);			
												
									Complex Ypu = Y.multi((Math.pow(v, 2)/Sbase));
									Complex Yshpu = Ysh.multi((Math.pow(v, 2)/Sbase));
												
									for(int m = 0; m < BusbarList.size() / 2; m ++) {
										if(m == c) {
											Complex Ytotalpu = Ypu.plus(Yshpu);
											ybus[m][m] = Ytotalpu.plus(ybus[m][m]);
											for(int n = 0; n < BusbarList.size() / 2; n ++) {
												if(n == d) {
													Complex Ytotal2pu = Ypu.plus(Yshpu);
													//System.out.println(n);
													//System.out.println(d);
													ybus[n][n] = Ytotal2pu.plus(ybus[n][n]);
													Complex minus = new Complex(-1.0, 0.0);
													Complex minusY = Ypu.times(minus);
													ybus[m][n]=minusY.plus(ybus[m][n]);
													ybus[n][m]=minusY.plus(ybus[n][m]);
												}
											}
										}
									}
								}
		
							}
				
						}
						
			
			// For Shunt capacitor
			System.out.println("Shunt");
			ArrayList shuntbus = new ArrayList<String>(); 
			//double[] shuntbus = new double[LinearShuntCompensatorrdfIDList.size()];
			 int x3 = 0;
			for(int i = 0; i < TerminalconductingList.size(); i ++) { 
				
				if(t1[i][2] == 7) {// if the terminal connect to a transformer
					// the terminal number connected to the line is i
					//int a = t1[i][3];// another terminal number of this ac line
					t1[i][5] = 1;
					//t1[a][5] = 1;// flag turns to 1
					int b = t1[i][4];
					if(t1[i][6] != BusbarList.size() / 2) {// check whether the terminal connect to busbar
						 x3 = t1[i][6];// x is the number of busbar
						 System.out.println("Hello" + x3);
						 shuntbus.add(x3);
				
					}else {
                   for(int j = 0; j < TerminalconductingList.size(); j ++) {
                  	 if(t1[j][4] == b && j != i && t1[j][2] == 8) {// check if other terminals in the same CN connect to breaker
                  		t1[j][5] = 1;
                  		 int c = t1[j][3];
                  		 if(t1[c][6] != BusbarList.size() / 2) {
                  			 int d = t1[c][6];
                  			  x3 = d; // x is the number of busbar
                  			  System.out.print("Hello");
                  			  System.out.println(x3);
                  			shuntbus.add(x3);
                  		 }
                  	 }
                   }
					

					}
				}
			}
			
			
			// calculate numbers for shunt 
			
            for(int i = 0; i < shuntbus.size(); i++) {
            	
				// get the value of the rxgbl of the acline
				for(int p = 0; p < LinearShuntCompensatorbgList.size(); p = p + 2) {
					if(i == (p / 2)) {
						double g = (double) LinearShuntCompensatorbgList.get(p + 1);
						double b = (double) LinearShuntCompensatorbgList.get(p);
						double v = (double) LinearShuntCompensatorvList.get(i);
						
						Complex Ysh =new Complex(g , b);	
						
									Complex Yshpu = Ysh.multi((v*v)/Sbase);
									
									for(int m = 0; m < BusbarList.size() / 2; m ++) {
										if(m == i) {
											ybus[m][m] = Yshpu.plus(ybus[m][m]);

										}
									}
					}
				}
	
			}
			
			
			
			
	   System.out.println("list of ac line buses numbers " + aclinebus);
	   System.out.println("list of transformer buses numbers " + transformerbus);
	   System.out.println("list of shunt buses numbers " + shuntbus);
		
		System.out.println("Trans RXBG new:");
		for(int i = 0; i < PowerTransList.size() / 3; i++) {
			System.out.println("No. " + transrxbgnew[i][0] + " r " + transrxbgnew[i][1] + " x " + transrxbgnew[i][2] + " b " + transrxbgnew[i][3] + " g " + transrxbgnew[i][4] + " v " + transrxbgnew[i][5]);
		}
	   
				
			// print the matrix of t1
			System.out.println("Matrix t1(terminal to equipment) ");
			System.out.println("generating unit-1; synchronous machine-2; AC line-3; power transformer-4; energy consumer-5; busbar-6; shunt cpmpensator-7; breaker-8");
			for(int i = 0; i < TerminalconductingList.size(); i++) {
				System.out.print("Terminal No. " + t1[i][0]+  " matches ");
				System.out.print( "No." + t1[i][1] + " ");
				System.out.print("Equipment " + t1[i][2]);
				System.out.print(" pairs " + t1[i][3]);
				System.out.print(" CN " + t1[i][4]);
				System.out.print(" flag " + t1[i][5]);
				System.out.println(" bus " + t1[i][6]);
			}
			
		// print Y bus matrix
			for(int i = 0; i < BusbarList.size()/2; i++ ) {
			int a = BusbarList.size();
			System.out.print("[ " );
			for(int j = 0; j < BusbarList.size()/2; j++) {
					System.out.print(ybus[i][j].StringRep() + "        ");
			}
			System.out.println("]");
	      }
			

		}
		
	catch(Exception e){
		e.printStackTrace();
		}
		
	}
	

}


