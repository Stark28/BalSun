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
	private List <SubstationClass> SubList = new ArrayList<SubstationClass>();
	private List <VoltLevelClass> VoltLvList = new ArrayList<VoltLevelClass>();
	private List <GenUnitClass> GenList = new ArrayList<GenUnitClass>();
	private List <SynchMachineClass> SMList = new ArrayList<SynchMachineClass>();
	private List <RegControlClass> RCList = new ArrayList<RegControlClass>();
	private List <PowerTransClass> PowerTransList = new ArrayList<PowerTransClass>();
	private List <EnergyConsClass> EnergyConsList = new ArrayList<LoadClass>();
	private List <PowTrEndClass> TransWindList = new ArrayList<PowTrEndClass>();
	private List <BreakerClass> BreakerList = new ArrayList<BreakerClass>();
	private List <TapChangerClass> TapChangerList = new ArrayList<TapChangerClass>();
	private List <TerminalClass> TerminalList = new ArrayList<TerminalClass>();
	private List <ConnectivityNodeClass> ConnectNodeList = new ArrayList<ConnectivityNodeClass>();
	private List <ACLineClass> ACLineList = new ArrayList<ACLineClass>();
	private List <BusBarClass> BusBarList = new ArrayList<BusBarClass>();
	private List <ShuntClass> ShuntList = new ArrayList<ShuntClass>();
    	
    }
}
