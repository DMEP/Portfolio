#include "ns3/core-module.h"
#include "ns3/network-module.h"
#include "ns3/mobility-module.h"
#include "ns3/config-store-module.h"
#include "ns3/wifi-module.h"
#include "ns3/internet-module.h"
#include "ns3/dsdv-helper.h"
#include "ns3/dsdv-routing-protocol.h"
#include "ns3/delay-jitter-estimation.h"
#include "ns3/v4ping-helper.h"

#include <iostream>
#include <fstream>

NS_LOG_COMPONENT_DEFINE ("DSDV");

using namespace ns3;
using namespace std;

uint32_t received_packets = 0;
long double total_delay = 0;

void ReceivePacket (Ptr<Socket> socket)
{
	ofstream outfile;
	outfile.open("dsdv300n_Simulation_general.txt", ios::app);
	Ptr<Packet> packet;
	Address from;
	while (packet = socket->RecvFrom (from))
	{
		if (packet->GetSize () > 0)
		{
			received_packets++;
			DelayJitterEstimation delay;
			delay.RecordRx(packet);
			total_delay += delay.GetLastDelay().GetSeconds();
			InetSocketAddress iaddr = InetSocketAddress::ConvertFrom (from);
			
			NS_LOG_UNCOND ("--\nRecieved one packet, the number of packets to date: " << received_packets << 
						   " From: " << iaddr.GetIpv4() <<
						   " Delay: " << delay.GetLastDelay().GetSeconds() <<
						   " Size of packet "<< packet -> GetSize() <<
						   " port: " << iaddr.GetPort () << 
						   " At time = " << Simulator::Now ().GetSeconds () << "\n--");

			outfile << "Recieved one packet, socket: "<< iaddr.GetIpv4 ()<< 
					   " port: " << iaddr.GetPort () << 
					   " At time = " << Simulator::Now ().GetSeconds () << 
					   "\n";
			(void) iaddr;
		}
	}
	outfile.close();
}

static void GenerateTraffic (Ptr<Socket> socket, uint32_t pktSize, Ptr<Node> n, uint32_t pktCount, Time pktInterval)
{
	Ptr<Packet> p = Create<Packet> (pktSize);
	DelayJitterEstimation delay;
	delay.PrepareTx(p);
	if (pktCount > 0)
	{
		socket->Send (p);
		Simulator::Schedule (pktInterval, &GenerateTraffic, socket, pktSize, n, 
		pktCount - 1, pktInterval);
	}
	else
	{
		socket->Close ();
	}
}

int main (int argc, char *argv[])
{
	bool verbose = false;
	double startTime = 0.0;
	uint32_t numPackets = 100; // The number of packets that are sent
	
	std::string phyMode ("DsssRate11Mbps");
	
	uint32_t packetSize = 1024;
	uint32_t periodicUpdateInterval = 15;
	long double interval = 1;
	uint32_t numNodes = 300; 
	uint32_t speed = 2; 
	
	ofstream outfile;
	ofstream outfile2;
	
	outfile.open("dsdv300n_Simulation_general.txt", ios::app);
	outfile << "Simulator Parameters" << endl
			<< "Routing Protocol : DSDV" << endl
			<< "Number of Nodes: " << numNodes << endl
			<< "Speed: " << speed << " m/s" << endl
			<< "The number of packets that are sent From each node : " << numPackets << endl
			<< "Data rate: " << ((packetSize/interval)*8)/1024 << " Mbps" << endl;
	outfile2.open("dsdv300n_Simulation_results.txt", ios::app);
	outfile2 << "----Simulator Parameters----" << endl
			 << "Routing Protocol : DSDV" << endl
			 << "Number of Nodes: " << numNodes << endl
			 << "Speed: " << speed << " m/s" << endl
			 << "The number of packets that are sent From each node: " << numPackets <<endl
		  	 << "Data rate: " << ((packetSize/interval)*8)/1024 << " Mbps" << endl;
	
	CommandLine cmd;
	cmd.AddValue ("phyMode", "Wifi Phy mode", phyMode);
	cmd.AddValue ("packetSize", "Size of packets sent", packetSize);
	cmd.AddValue ("numPackets", "Number of total packets sent", numPackets);
	cmd.AddValue ("periodicUpdateInterval", "Time updates / Periodic updates", periodicUpdateInterval);
	cmd.AddValue ("startTime", "Simulator start time", startTime);
	cmd.AddValue ("verbose", "Turn on all components of the tool logs", verbose);
	cmd.Parse (argc, argv);
	
	Time interPacketInterval = Seconds (interval);
	Config::SetDefault ("ns3::WifiRemoteStationManager::FragmentationThreshold", StringValue ("2200"));
	Config::SetDefault ("ns3::WifiRemoteStationManager::RtsCtsThreshold", StringValue ("2200"));
	Config::SetDefault ("ns3::WifiRemoteStationManager::NonUnicastMode", StringValue (phyMode));
	
	NodeContainer networkNodes;
	networkNodes.Create (numNodes);
	
	WifiHelper wifi;
	if (verbose)
	{
		wifi.EnableLogComponents ();
	}
	wifi.SetStandard (WIFI_PHY_STANDARD_80211b);
	
	/** Wifi PHY **/
	/**************************************************************************/
	YansWifiPhyHelper wifiPhy = YansWifiPhyHelper::Default ();
	wifiPhy.Set ("RxGain", DoubleValue (5));
	wifiPhy.Set ("TxGain", DoubleValue (5));
	wifiPhy.Set ("CcaMode1Threshold", DoubleValue (0.0));
	/**************************************************************************/
	
	/** wifi channel **/
	YansWifiChannelHelper wifiChannel;
	wifiChannel.SetPropagationDelay ("ns3::ConstantSpeedPropagationDelayModel");
	wifiChannel.AddPropagationLoss ("ns3::FriisPropagationLossModel");
	Ptr<YansWifiChannel> wifiChannelPtr = wifiChannel.Create ();
	wifiPhy.SetChannel (wifiChannelPtr);
	
	/** MAC layer **/
	NqosWifiMacHelper wifiMac = NqosWifiMacHelper::Default ();
	wifi.SetRemoteStationManager ("ns3::ConstantRateWifiManager", 
								  "DataMode", StringValue (phyMode),
								  "ControlMode", StringValue (phyMode));
	wifiMac.SetType ("ns3::AdhocWifiMac");
	NetDeviceContainer devices = wifi.Install (wifiPhy, wifiMac, networkNodes);
	MobilityHelper mobility;
	
	ObjectFactory pos;
	pos.SetTypeId ("ns3::RandomRectanglePositionAllocator");
	pos.Set ("X", StringValue ("ns3::UniformRandomVariable[Min=0.0|Max=1000.0]"));
	pos.Set ("Y", StringValue ("ns3::UniformRandomVariable[Min=0.0|Max=1000.0]"));
	
	std::ostringstream speedConstantRandomVariableStream;
	speedConstantRandomVariableStream << "ns3::ConstantRandomVariable[Constant=" << speed << "]";
	
	Ptr <PositionAllocator> taPositionAlloc = pos.Create ()->GetObject <PositionAllocator> ();
	mobility.SetMobilityModel ("ns3::RandomWaypointMobilityModel", 
								"Speed", StringValue (speedConstantRandomVariableStream.str ()),
	 							"Pause", StringValue ("ns3::ConstantRandomVariable[Constant=2.0]"), 
	 							"PositionAllocator", PointerValue (taPositionAlloc));
	mobility.SetPositionAllocator (taPositionAlloc);
	mobility.SetMobilityModel ("ns3::ConstantPositionMobilityModel");
	mobility.Install (networkNodes);
	
	//** Routing Protocol (DSDV) **/
	DsdvHelper dsdv;
	dsdv.Set ("PeriodicUpdateInterval", TimeValue (Seconds (periodicUpdateInterval)));
	
	/** Internet stack **/
	InternetStackHelper internet;
	internet.SetRoutingHelper(dsdv);
	internet.Install (networkNodes);
	
	/** Network Adresses **/
	Ipv4AddressHelper ipv4;
	NS_LOG_INFO ("Assign IP Addresses.");
	ipv4.SetBase ("10.1.0.0", "255.255.0.0");
	Ipv4InterfaceContainer i = ipv4.Assign (devices);
	TypeId tid = TypeId::LookupByName ("ns3::UdpSocketFactory");
	Ptr<Socket> recvSink;
	recvSink = Socket::CreateSocket (networkNodes.Get (0), tid);
	InetSocketAddress local = InetSocketAddress (Ipv4Address::GetAny (), 80);
	recvSink->Bind (local);
	recvSink->SetRecvCallback (MakeCallback (&ReceivePacket));
	Ptr<Socket> source;
	for(uint32_t count1 = 1; count1 < numNodes; count1++)
	{
		source = Socket::CreateSocket (networkNodes.Get (count1), tid);
		InetSocketAddress remote = InetSocketAddress (Ipv4Address::GetBroadcast (), 80);
		source->SetAllowBroadcast (true);
		source->Connect (remote);
	}
	for(uint32_t count2 = 1; count2 < numNodes; count2++)
	{
		Simulator::Schedule (Seconds(startTime), &GenerateTraffic, source, 
			packetSize, networkNodes.Get(count2), numPackets, interPacketInterval);
	}
	Simulator::Stop (Seconds (30.0));
	AsciiTraceHelper ascii;
	wifiPhy.EnableAsciiAll (ascii.CreateFileStream ("dsdv300n.tr"));
	Simulator::Run ();
	Simulator::Destroy();

	NS_LOG_UNCOND ("Number of packets sent: " << numPackets*(numNodes - 1) << 
					" packet. \nNumber of packet recieved: " << received_packets << 
					" packet. \nPacket recieved: " << double(received_packets*100)/(numPackets*(numNodes - 1)) <<
					"%. \nAverage Delay: " << total_delay/received_packets << 
					" seconds. \nThroughput: " << double ((received_packets*packetSize)/30)*8/1024 << 
					" Kbps.\nSimulator end");

	outfile2 << "----Simulator Results----" << endl <<
				"Number of packet sent: " << numPackets*(numNodes - 1) << endl
				<< "Number of packet recieved: " << received_packets << " packet. " << endl << 
				"Packet recieved: " << double(received_packets*100)/(numPackets*(numNodes -	1)) << "%." << endl << 
				"Average Delay: " << total_delay/received_packets << " seconds." << endl << 
				"Throughput: " << double((received_packets*packetSize)/30)*8/1024 << " Kbps." << endl;
	
	outfile << "Simulator end" << endl << endl;
	outfile2 << "Simulator end" << endl << endl <<
	"---------------------------------------------------------" << endl << endl;
	outfile.close();
	outfile2.close();
	received_packets = 0;
	total_delay = 0;
	return 0;
}