package assignment1;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.util.ArrayList;
//import java.util.List;



import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

// Main class for extracting all the requirement data for importing into SQL database
public class main {
	//Creating array list for each CIM object according to individual classes defined per object
	//private List <BaseVoltClass> BaseVoltList = new ArrayList<BaseVoltClass>();
	//private List <SubClass> SubList = new ArrayList<SubClass>();
	//private List <VoltLvClass> VoltLvList = new ArrayList<VoltLvClass>();
	//private List <GenClass> GenList = new ArrayList<GenClass>();
	//private List <SMClass> SMList = new ArrayList<SMClass>();
	//private List <RCClass> RCList = new ArrayList<RCClass>();
	//private List <PowerTransClass> PowerTransList = new ArrayList<PowerTransClass>();
	//private List <EnergyConsClass> EnergyConsList = new ArrayList<EnergyConsClass>();
	//private List <TransWindingClass> TransWindingList = new ArrayList<TransWindingClass>();
	//private List <BreakerClass> BreakerList = new ArrayList<BreakerClass>();
	//private List <TapClass> TapList = new ArrayList<TapClass>();
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
			NodeList transwindingList = doc1.getElementsByTagName("cim:PowerTransformerEnd");
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
				
					Node BaseVol = basevoltlist.items(i);
					
					private String rdfID;
					private double nominalValue;
					//	
					    public Element extractNode (Node BaseVol){
							Element BVelement = (Element) BaseVol;
							rdfID = BVelement.getElementsByTagName("rdf:ID");
							nominalValue = Double.parseDouble(element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent());	
							return BVelement;
						}
					 // 
						public String getrdfID() {
							return rdfID;
						}
						
						public double getNomValue() {
							return nominalValue;
						}
				
				
				//BaseVoltClass basevolt = new BaseVoltClass();
				//basevolt.extractNode(basevoltList.item(i));
				//BaseVoltList.add(basevolt);
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
			
			//Synchronous Machine List done
			for (int i = 0; i<smList.getLength(); i++) {
				SMClass sm = new SMClass();
				sm.extractNode(smList.item(i));
				sm.extractNodeSSH(smList2.item(i));
				// extractnodeSSH ??? what does this mean, can I change the method name here?
				SMList.add(sm);
				}
			
			//Regulating Control List done
			for (int i = 0; i<rcList.getLength(); i++) {
				RCClass rc = new RCClass();
				rc.extractNode(rcList.item(i));
				rc.extractNodeSSH(rcList2.item(i));
				RCList.add(rc);	
				}
			
			//Power Transformer List done
			for (int i = 0; i<powertransList.getLength(); i++) {
				PowerTransClass powertrans = new PowerTransClass();
				powertrans.extractNode(powertransList.item(i));
				PowerTransList.add(powertrans);
				}
			
			//Energy Consumer List done
			for (int i = 0; i<energyconsList.getLength(); i++) {
				EnergyConsClass energycons = new EnergyConsClass();
				energycons.extractNode(energyconsList.item(i));
				energycons.extractNodeSSH(energyconsList2.item(i));
				EnergyConsList.add(energycons);
				}
			
		    //Power Transformer End (Winding) List done
			for (int i = 0; i<transwindingList.getLength(); i++) {
				TransWindingClass transwinding = new TransWindingClass();
				transwinding.extractNode(transwindingList.item(i));
				TransWindingList.add(transwinding);
				}
			
			//Breaker List done
			for (int i = 0; i<breakList.getLength(); i++) {
				BreakerClass breaker = new BreakerClass();
				breaker.extractNode(breakList.item(i));
				breaker.extractNodeSSH(breakList2.item(i));
				BreakerList.add(breaker);
				}
			
			//Ratio Tap Changer List done
			for (int i = 0; i<tapList.getLength(); i++) {
				TapClass tap = new TapClass();
				tap.extractNode(tapList.item(i));
				tap.extractNodeSSH(tapList2.item(i));
				TapList.add(tap);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			}
	  }
	         //Returning the list of array for each CIM objects
	         public List <BaseVoltClass> getBaseVoltList(){
	         return BaseVoltList;
	       }
	         
	         public List <SubClass> getSubList(){
	         return SubList;
	         }
	         
	         public List <VoltLvClass> getVoltLvList(){
	         return VoltLvList;
	         }
	         
	         public List <GenClass> getGenList(){
		     return GenList;
		     }
	         
	         public List <SMClass> getSMList(){
		     return SMList;
		     }
	         
	         public List <RCClass> getRCList(){
		     return RCList;
		     }
	         
	         public List <PowerTransClass> getPowerTransList(){
		     return PowerTransList;
		     }
	         
	         public List <EnergyConsClass> getEnergyConsList(){
		     return EnergyConsList;
		     }
	         
	         public List <TransWindingClass> getTransWindingList(){
		     return TransWindingList;
		     }
	         
	         public List <BreakerClass> getBreakerList(){
		     return BreakerList;
		     }
	         
	         public List <TapClass> getTapList(){
		     return TapList;
		     }
	         
	         
    }

