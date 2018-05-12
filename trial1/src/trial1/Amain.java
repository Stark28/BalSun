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
		
		ArrayList ACLinerdfIDList = new ArrayList<String>();
		
		ArrayList ACLinebaseList = new ArrayList<String>();
		
		ArrayList ACLinerxbglList = new ArrayList<String>();
		
		ArrayList BusbarList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorrdfIDList = new ArrayList<String>();
		
		ArrayList LinearShuntCompensatorbgList = new ArrayList<String>();
		
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
			
		//	System.out.println("*** Voltage Level ***");
		//	for (int i = 0; i < VoltLevelList.size(); i = i + 4 ) {
		//		String VoltrdfID = (String) VoltLevelList.get(i);
		//		String VoltName = (String) VoltLevelList.get(i+1);
		//		String subrdfID = (String) VoltLevelList.get(i+2);
		//		String baseVoltrdfID = (String) VoltLevelList.get(i+3);
		//		mySQL.VoltageLevelTab(VoltrdfID, VoltName, subrdfID, baseVoltrdfID);
		//		System.out.println("rdfID: " + VoltrdfID +"\n"+ "Name: " + VoltName +"\n"+ "substation_ID: "
		//		+ subrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
		//		}
			
		// Generating Unit information
			GeneratingClass gen = new GeneratingClass();
			gen.genfn(doc1, GeneratingList);
			System.out.println("List of Generating Unit : " + GeneratingList);
			
		// Store the value to SQL database
			
		//	System.out.println("*** Generating Unit ***");
		//	for (int i = 0; i < GeneratingList.size(); i = i + 5 ) {
		//		String GenrdfID = (String) GeneratingList.get(i);
		//		String GenName = (String) GeneratingList.get(i+1);
		//		double GenMaxP = (double) GeneratingList.get(i+2);
		//		double GenMinP = (double) GeneratingList.get(i+3);
		//		String GenEqConID = (String) GeneratingList.get(i+4);
		//		mySQL.GeneratingUnitTab(GenrdfID, GenName, GenMaxP, GenMinP, GenEqConID);
		//		System.out.println("rdfID: " + GenrdfID +"\n"+ "Name: " + GenName +"\n"+
		//		"Maximum Operating Power: " + GenMaxP +"\n"+ "Minimum Operating Power: " + GenMinP +"\n"
		//		+ "Equipment Container ID: " + GenEqConID);
		//		}
			
		// Synchronous Machine information
			SynMachClass syn = new SynMachClass();
			syn.synfn(doc1, doc2, SynchronousList);
			System.out.println("List of Synchronous Machine : " + SynchronousList);
			
		// Store the value to SQL database
			
		//	System.out.println("*** Synchronous Machine ***");
		//	for (int i = 0; i < SynchronousList.size(); i = i + 9) {
		//		String SyncrdfID = (String) SynchronousList.get(i);
		//		String SyncName = (String) SynchronousList.get(i+1);
		//		double SyncRatedS = (double) SynchronousList.get(i+2);
		//		double SyncP = (double) SynchronousList.get(i+3);
		//		double SyncQ = (double) SynchronousList.get(i+4);
		//		String SyncGenUnitID = (String) SynchronousList.get(i+5);
		//		String SyncRegCtrID = (String) SynchronousList.get(i+6);
		//		String SyncEqConID = (String) SynchronousList.get(i+7);
		//		String SyncbasevoltID = (String) SynchronousList.get(i+8);
		//		mySQL.SynchMachineTab(SyncrdfID, SyncName, SyncRatedS, SyncP, SyncQ, SyncGenUnitID, SyncRegCtrID, SyncEqConID,SyncbasevoltID);
		//		System.out.println("rdfID: " + SyncrdfID +"\n"+ "Name: " + SyncName +"\n"+
		//				"rated S: " + SyncRatedS +"\n"+ "Active Power: " + SyncP +"\n"+ "Reactive Power: " + SyncQ
		//				+"\n"+ "Generating Unit ID: " + SyncGenUnitID +"\n"+ "Regulating Control ID: " + SyncRegCtrID
		//				+"\n"+ "Equipment Container ID: " + SyncEqConID);
		//		}
			
			
		// Regulating Control information check done
			RegulatingClass reg = new RegulatingClass();
			reg.regfn(doc1, doc2, RegulatingList);
			System.out.println("List of Regulating Control : " + RegulatingList);
			
			// Store the value to SQL database
			
		//				System.out.println("*** Regulating Control ***");
		//				for (int i = 0; i < RegulatingList.size(); i = i + 3 ) {
		//					String RegrdfID = (String) RegulatingList.get(i);
		//					String RegName = (String) RegulatingList.get(i+1);
		//					double TargetValue = (double) RegulatingList.get(i+2);
		//					mySQL.RegControlTab(RegrdfID, RegName, TargetValue);
		//					System.out.println("rdfID: " + RegrdfID +"\n"+ "Name: " + RegName +"\n"+
		//					"Target Value: " +TargetValue);
		//					}
			
		// Power Transformer information
		    PowerTransClass power = new PowerTransClass();
			power.powerfn(doc1, PowerTransList);
			System.out.println("List of Power Transformer : " + PowerTransList);
		
		// Store the value to SQL database
			
		//	System.out.println("*** Power Transformer ***");
		//	for (int i = 0; i < PowerTransList.size(); i = i + 3 ) {
		//		String TransrdfID = (String) PowerTransList.get(i);
		//		String TransName = (String) PowerTransList.get(i+1);
		//		String TransEqConID = (String) PowerTransList.get(i+2);
		//		mySQL.PowerTransformerTab(TransrdfID, TransName, TransEqConID);
		//		System.out.println("rdfID: " + TransrdfID +"\n"+ "Name: " + TransName +"\n"+
		//		"Equipment Container ID: " +TransEqConID);
		//		}
			
		 //Energy Consumer information
		    EnergyClass energy = new EnergyClass();
			energy.energyfn(doc1, doc2, EnergyList);
			System.out.println("List of Energy Consumer(Load) : " + EnergyList);

			// Store the value to SQL database
			
		//	System.out.println("*** Energy Consumer ***");
		//	for (int i = 0; i < EnergyList.size(); i = i + 6 ) {
		//		String LoadrdfID = (String) EnergyList.get(i);
		//		String LoadName = (String) EnergyList.get(i+1);
		//		double LoadP = (double) EnergyList.get(i+2);
		//		double LoadQ = (double) EnergyList.get(i+3);
		//		String LoadEqConID = (String) EnergyList.get(i+4);
		//		String BasevoltID = (String) EnergyList.get(i+5);
		//		mySQL.EnergyConsumerTab(LoadrdfID, LoadName, LoadP, LoadQ, LoadEqConID, BasevoltID);
		//		System.out.println("rdfID: " + LoadrdfID +"\n"+ "Name: " + LoadName +"\n"+
		//		"Active Power: " +LoadP +"\n"+ "Reactive Power: " + LoadQ +"\n"+
		//		"Equipment Container ID: " +LoadEqConID);
		//		}
			
		// Power Transformer Winding information check done
		    PowerTransEndClass powerend = new PowerTransEndClass();
			powerend.powerendfn(doc1, PowerTransEndList);
			System.out.println("List of Power Transformer End : " + PowerTransEndList);
			
			// Store the value to SQL database
			
		//	System.out.println("*** Power Transformer End (Winding) ***");
		//	for (int i = 0; i < PowerTransEndList.size(); i = i + 6 )  {
		//		String TrWindrdfID = (String) PowerTransEndList.get(i);
		//		String TrWindName = (String) PowerTransEndList.get(i+1);
		//		double TrWindRvalue = (double) PowerTransEndList.get(i+2);
		//		double TrWindXvalue = (double) PowerTransEndList.get(i+3);
		//		String PowTransrdfID = (String) PowerTransEndList.get(i+4);
		//		String baseVoltrdfID = (String) PowerTransEndList.get(i+5);
		//		mySQL.TransformerWindingTab(TrWindrdfID, TrWindName, TrWindRvalue, TrWindXvalue, PowTransrdfID, baseVoltrdfID);
		//		System.out.println("rdfID: " + TrWindrdfID +"\n"+ "Name: " + TrWindName +"\n"+ "Resistance Value: "
		//		+ TrWindRvalue +"\n"+ "Reactance Value: " + TrWindXvalue +"\n"
		//		+ "Transformer_ID: " + PowTransrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
		//		}
			
		// Breaker information check done
		    BreakerClass breaker = new BreakerClass();
			breaker.breakerfn(doc1, BreakerList);
			System.out.println("List of Breaker : " + BreakerList);	
			
			// Store the value to SQL database
			
<<<<<<< HEAD
			System.out.println("*** Breaker ***");
			for (int i = 0; i < BreakerList.size(); i = i + 5 ) {
=======
		/*	System.out.println("*** Breaker ***");
			for (int i = 0; i < BreakerList.size(); i = i + 4 ) {
>>>>>>> e72ec73fc5ac22b2d379b3be4b683b094256ae7e
				String BRrdfID = (String) BreakerList.get(i);
				String BRName = (String) BreakerList.get(i+1);
				boolean BRState = (boolean) BreakerList.get(i+2);
				String BREqConID = (String) BreakerList.get (i+3);
				mySQL.BreakerTab(BRrdfID, BRName, BRState, BREqConID);
				System.out.println("rdfID: " + BRrdfID +"\n"+ "Name: " + BRName +"\n"+
				"State: " +BRState +"\n"+ "Equipment Container ID: " +BREqConID);
				}
			*/
		// Tap Changer information
		    TapClass tap = new TapClass();
			tap.tapfn(doc1,doc2, TapList);
			System.out.println("List of Tap Changer : " + TapList);
			
			// Store the value to SQL database
		/*	
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
			*/
			
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
 	
		//  initial the Y bus matrix
			int [][] y;
			y = new int [BusbarList.size()][BusbarList.size()];
			for(int i = 0; i < BusbarList.size(); i = i + 2) {
				for(int j = 0; j < BusbarList.size(); j = j + 2) {
				y[i][j]=0;
			}
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
			
			

			
			
			/*// start from the generating unit(synchronous machine)
			for (int i = 0; i < TerminalconductingList.size(); i ++) {
				if( t1[i][2] == 2) {
					int a = t1[i][0]; // the terminal number connected to the generating unit
					a1[a][2] = 1;// the flag of the terminal in the a1 matrix turns from 0 to 1
					
					for(int j = 0; j < TerminalconductingList.size(); j++){
						if(a1[j][1] == a1[a][1] && a != j) {// check are there any other terminals that share the same connectivity node with the previous terminal
							if(t1[j][2] == 6) { //check whether the terminals here connected to the busbar
								int b = t1[j][1]; // the first busbar number that will be used in the ybus matrix
								a1[j][2] = 1; // the flag of this terminal turns to 1
							 	
							}
						}
					}
				}
			}
			*/
           
           // For AC line
			System.out.println("AC line");
			double[] aclinebus = new double[ACLinebaseList.size() * 2];
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
                    		 }
                    	 }
                     }
					

					}
				}
			}
			
			// For transformer
			System.out.println("Transformer");
			double[] transformerbus = new double[PowerTransEndList.size() / 3];
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
                   		 }
                   	 }
                    }
					

					}
				}
			}
	
			
			// For Shunt capacitor
			System.out.println("Shunt");
			double[] shuntbus = new double[LinearShuntCompensatorrdfIDList.size()];
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
                  		 }
                  	 }
                   }
					

					}
				}
			}
	
			
			
		/*	//System.out.println(a1);
			System.out.println("a1 matrix");
			for(int i = 0; i < TerminalconnectList.size(); i++) {
				System.out.print("Terminal " + a1[i][0] + " ");
				System.out.print("ConnectivityNode " + a1[i][1] + " ");
				System.out.println("flag of terminal " + a1[i][2]);
			}
			*/
		
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
			for(int i = 0; i < BusbarList.size(); i = i + 2 ) {
			int a = BusbarList.size();
			System.out.print("[ " );
			for(int j = 0; j < BusbarList.size(); j = j + 2) {
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

