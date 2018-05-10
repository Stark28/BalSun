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
			syn.synfn(doc1, doc2, SynchronousList);
			System.out.println("List of Synchronous Machine : " + SynchronousList);
			
		// Regulating Control information check done
			RegulatingClass reg = new RegulatingClass();
			reg.regfn(doc1, doc2, RegulatingList);
			System.out.println("List of Regulating Control : " + SynchronousList);
			
		// Power Transformer information
		    PowerTransClass power = new PowerTransClass();
			power.powerfn(doc1, PowerTransList);
			System.out.println("List of Power Transformer : " + PowerTransList);
			
		 //Energy Consumer information
		    EnergyClass energy = new EnergyClass();
			energy.energyfn(doc1, doc2, EnergyList);
			System.out.println("List of Energy Consumer(Load) : " + EnergyList);
			
		// Power Transformer Winding information check done
		    PowerTransEndClass powerend = new PowerTransEndClass();
			powerend.powerendfn(doc1, PowerTransEndList);
			System.out.println("List of Power Transformer End : " + PowerTransEndList);	
			
		// Breaker information check done
		    BreakerClass breaker = new BreakerClass();
			breaker.breakerfn(doc1, BreakerList);
			System.out.println("List of Breaker : " + BreakerList);	
			
		// Tap Changer information
		    TapClass tap = new TapClass();
			tap.tapfn(doc1,doc2, TapList);
			System.out.println("List of Tap Changer : " + TapList);
			
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
	                    a2[i][0] = j;
	                    a2[i][1] = i;
					}
				}	
			}
		
		//System.out.println(a2);
			for(int i = 0; i < ConnectivitycontainerNodeList.size(); i++) {
				System.out.print("Busbar " + a2[i][0] + " ");
				System.out.println("ConnectivityNode " + a2[i][1]);
				
			}
 
			
		//  
			
	}
	catch(Exception e){
		e.printStackTrace();
		}
	
	}
	
}

